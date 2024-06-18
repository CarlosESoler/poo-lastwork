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

/**
 * Classe: essa classe refere-se a construção do extrato com base no estado do
 * objeto Pessoa
 */
public class Extrato {

    private Pessoa titular;
    private String arquivoLancamentos;
    private File arquivo;

    /**
     * Construtor: inicializa a variável titular do tipo Pessoa a fim de obter o
     * estado de seus lançamentos
     *
     * @param titular do tipo Pessoa
     */
    public Extrato(Pessoa titular) {
        this.titular = titular;
        arquivoLancamentos = "lancamentos.csv";
        try {
            arquivo = new File(arquivoLancamentos);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao criar arquivo CSV: " + e.getMessage());
        }
    }

    /**
     * Getter: capta o arquivo
     *
     * @return arquivo do tipo File
     */
    public File getArquivo() {
        return arquivo;
    }

    /**
     * Método que apaga o arquivo caso ele exista e não esteja aberto na área de
     * trabalho
     */
    public void apagarArquivo() {
        if (this.arquivo.delete()) {
            JOptionPane.showMessageDialog(null, "Arquivo apagado com sucesso!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Falha ao apagar o arquivo CSV!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    /**
     * Método que carrega os dados do arquivo caso ele exista, ou seja, deve-se
     * primerio executar o comando de salvar dados
     */
    public void carregarDados() {

        String separadorCSV = ",";
        try (BufferedReader br = new BufferedReader(new FileReader(arquivoLancamentos))) {

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
            JOptionPane.showMessageDialog(null, "Foram feitos lançamentos na plataforma com IDs já contidos no arquivo. \nRecomenda-se remover os lançamentos duplicados na plataforma ou alterar os IDs no documento!");
        }
    }

    /**
     * Método que salva os dados de despesas e receitas inseridas pelo usuário,
     * caso haja uma inserção
     */
    public void salvarDados() {
        String arquivoCSV = "lancamentos.csv";
        String separadorCSV = ",";

        try (PrintWriter escrever = new PrintWriter(new FileWriter(arquivoCSV))) {
            // Escreve o cabeçalho
            escrever.println("\"Valor\",\"Data\",\"Categoria\",\"Tipo\",\"ID\"");

            List<Integer> idsLancamentos = new ArrayList<>(titular.getHistorico().keySet());
            // Escreve as receitas
            for (Integer id : idsLancamentos) {

                if (titular.getReceitasMap().containsKey(id)) {
                    Receita rc = titular.getReceitasMap().get(id);
                    escrever.println(String.format("%s,%s,%s,Receita,%d",
                            rc.getValor(),
                            rc.getDataLancamento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                            rc.getTipoReceita(),
                            id));
                } else {
                    Despesa dc = titular.getDespesasMap().get(id);
                    escrever.println(String.format("%s,%s,%s,Despesa,%d",
                            dc.getValor(),
                            dc.getDataLancamento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                            dc.getTipoDespesa(),
                            id));
                }
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar dados no arquivo CSV");
        }
    }
}
