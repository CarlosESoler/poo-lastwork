package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import model.Pessoa;
import model.interfaces.TipoDespesa;
import model.interfaces.TipoReceita;
import model.lancamentos.Despesa;
import model.lancamentos.Receita;

public class Extrato {

    private Pessoa titular;
    private String arquivoCSV;
    private File arquivo;

    public Extrato(Pessoa titular) {
        this.titular = titular;
        arquivoCSV = "lancamentos.csv";
        try {
            arquivo = new File(arquivoCSV);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao criar arquivo CSV: " + e.getMessage());
        }
    }

    public File getArquivo() {
        return arquivo;
    }
    
    public void apagarArquivo() {
        if (this.arquivo.delete()) {
            this.arquivo = null;
            JOptionPane.showMessageDialog(null, "Arquivo apagado com sucesso!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Falha ao apagar o arquivo CSV!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public void carregarDados() {

        String separadorCSV = ",";
        try(BufferedReader br = new BufferedReader(new FileReader(arquivoCSV))) {

            String linha;
            br.readLine(); // Ignora a primeira linha (cabeçalho)

            while ((linha = br.readLine()) != null) {
                String[] campos = linha.split(separadorCSV);

                if (campos.length >= 5) {
                    String valor = campos[0];

                    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate localDate = LocalDate.parse(campos[1], format);

                    String categoria = campos[2];
                    String tipo = campos[3];
                    int id = Integer.parseInt(campos[4]);

                    if (tipo.equalsIgnoreCase("Receita")) {
                        TipoReceita tpReceita = TipoReceita.valueOf(categoria.toUpperCase());
                        Receita adcReceita = new Receita(localDate, tpReceita, valor);
                        titular.adicionarReceita(id, adcReceita);

                        HistoricoLancamento historico = new HistoricoLancamento(adcReceita, titular.getConta().consultaSaldoIndependentePeriodo());
                        titular.adicionarHistoricoLancamento(id, historico);
                    } else if (tipo.equalsIgnoreCase("Despesa")) {
                        TipoDespesa tpDespesa = TipoDespesa.valueOf(categoria.toUpperCase());
                        Despesa adcDespesa = new Despesa(localDate, tpDespesa, valor);
                        titular.adicionarDespesa(id, adcDespesa);

                        HistoricoLancamento historico = new HistoricoLancamento(adcDespesa, titular.getConta().consultaSaldoIndependentePeriodo());
                        titular.adicionarHistoricoLancamento(id, historico);
                    }
                }
            }

            JOptionPane.showMessageDialog(null, "Arquivo carregado com sucesso!");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar dados do arquivo CSV: " + ex.getMessage());
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, "Existem lançamentos na plataforma com IDs duplicados em relação aos IDs contidos no arquivo. Recomenda-se remover os lançamentos duplicados na plataforma ou alterar os IDs no documento!");
        }
    }

    public void salvarDados() {
        String arquivoCSV = "lancamentos.csv";
        String separadorCSV = ",";

        try (PrintWriter writer = new PrintWriter(new FileWriter(arquivoCSV))) {
            // Escreve o cabeçalho
            writer.println("\"Valor\",\"Data\",\"Categoria\",\"Tipo\",\"ID\"");

            List<Integer> idsDespesas = new ArrayList<>(titular.getDespesasMap().keySet());
            Collections.sort(idsDespesas);
            List<Integer> idsReceitas = new ArrayList<>(titular.getReceitasMap().keySet());
            Collections.sort(idsReceitas);

            // Escreve as receitas
            for (Integer id : idsReceitas) {
                Receita rc = titular.getReceitasMap().get(id);
                writer.println(String.format("%s,%s,%s,Receita,%d",
                        rc.getValor(),
                        rc.getDataLancamento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        rc.getTipoReceita(),
                        id));
            }

            // Escreve as despesas
            for (Integer id : idsDespesas) {
                Despesa dc = titular.getDespesasMap().get(id);
                writer.println(String.format("%s,%s,%s,Despesa,%d",
                        dc.getValor(),
                        dc.getDataLancamento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        dc.getTipoDespesa(),
                        id));
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar dados no arquivo CSV");
        }
    }
}
