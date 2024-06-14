import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import model.Pessoa;
import model.interfaces.TipoDespesa;
import model.interfaces.TipoReceita;
import model.lancamentos.Despesa;
import model.lancamentos.Receita;

public class Extrato {
    private Pessoa titular;

    public Extrato(Pessoa titular) {
        this.titular = titular;
    }

    public void apagarArquivo() {
        String arquivoCSV = "lancamentos.csv";
        File arquivo = new File(arquivoCSV);
        if (arquivo.exists()) {
            if (arquivo.delete()) {
                JOptionPane.showMessageDialog(null, "Arquivo apagado com sucesso!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Falha ao apagar o arquivo CSV!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "O arquivo CSV não existe!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Métodos para carregar e salvar dados (implementação depende da forma de persistência)
    public void carregarDados() {
        String arquivoCSV = "lancamentos.csv";
        String separadorCSV = ",";

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(arquivoCSV));
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] campos = linha.split(separadorCSV);

                if (campos.length >= 4) {
                    String valor = campos[0];

                    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate localDate = LocalDate.parse(campos[1], format);

                    String categoria = campos[2];
                    String tipo = campos[3];

                    if (tipo.equalsIgnoreCase("Receita")) {
                        TipoReceita tpReceita = TipoReceita.valueOf(categoria.toUpperCase());
                        titular.adicionarReceita(Integer.parseInt(campos[4]), new Receita(localDate, tpReceita, valor));
                    } else if (tipo.equalsIgnoreCase("Despesa")) {
                        TipoDespesa tpDespesa = TipoDespesa.valueOf(categoria.toUpperCase());
                        titular.adicionarDespesa(Integer.parseInt(campos[4]), new Despesa(localDate, tpDespesa, valor));
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar dados do arquivo CSV: " + e.getMessage());
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void salvarDados() {
        String arquivoCSV = "lancamentos.csv";
        String separadorCSV = ",";

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileWriter(arquivoCSV));
            for (Receita receita : titular.getReceitas()) {
                writer.println(String.format("%s,%s,%s,%s,%d",
                        receita.getValor(),
                        receita.getDataLancamento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        receita.getTipoReceita(),
                        "Receita",
                        titular.getIdReceitas()));
            }

            for (Despesa despesa : titular.getDespesas()) {
                writer.println(String.format("%s,%s,%s,%s",
                        despesa.getValor(),
                        despesa.getDataLancamento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        despesa.getTipoDespesa(),
                        "Despesa",
                        titular.getIdDespesas()));
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados no arquivo CSV: " + e.getMessage());
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
