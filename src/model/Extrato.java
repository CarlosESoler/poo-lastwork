

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import model.interfaces.TipoDespesa;
import model.interfaces.TipoReceita;
import model.lancamentos.Despesa;
import model.lancamentos.Receita;

/**
 *
 * @author Gamer
 */
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
        String arquivoCSV = "lancamentos.csv"; // Nome do arquivo CSV (pode ser configurado)
        String separadorCSV = ","; // Separador de campos no CSV

        try (BufferedReader br = new BufferedReader(new FileReader(arquivoCSV))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] campos = linha.split(separadorCSV);

                if (campos.length >= 4) { // Verifica se a linha tem pelo menos 4 campos
                    double valor = Double.parseDouble(campos[0]);

                    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/mm/yyyy");
                    LocalDate localDate = LocalDate.parse(campos[1], format);

                    String categoria = campos[2];
                    String tipo = campos[3]; // "Receita" ou "Despesa"

                    if (tipo.equalsIgnoreCase("Receita")) {
                        TipoReceita tpReceita = TipoReceita.valueOf(categoria.toUpperCase());
                        titular.adicionarReceita(new Receita(localDate, tpReceita, tipo));
                    } else if (tipo.equalsIgnoreCase("Despesa")) {
                        TipoDespesa tpDespesa = TipoDespesa.valueOf(categoria.toUpperCase());
                        titular.adicionarDespesa(new Despesa(localDate, tpDespesa, tipo));
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar dados do arquivo CSV: " + e.getMessage());
        }
    }

    public void salvarDados() {
        String arquivoCSV = "lancamentos.csv"; // Nome do arquivo CSV (pode ser configurado)
        String separadorCSV = ","; // Separador de campos no CSV

        try (PrintWriter writer = new PrintWriter(arquivoCSV)) {
            for (Receita receita : titular.retornarReceitas()) {
                writer.println(String.format("%s,%s,%s,%s",
                        receita.getValor(),
                        receita.getDataLancamento(),
                        receita.getTipoReceita(),
                        "Receita"));
            }

            for (Despesa despesa : titular.retornarDespesas()) {
                writer.println(String.format("%s,%s,%s,%s",
                        despesa.getValor(),
                        despesa.getDataLancamento(),
                        despesa.getTipoDespesa(),
                        "Despesa"));
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados no arquivo CSV: " + e.getMessage());
        }
    }
}
