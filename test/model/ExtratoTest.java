/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static model.interfaces.TipoDespesa.ALIMENTACAO;
import static model.interfaces.TipoDespesa.TRANSPORTE;
import model.interfaces.TipoReceita;
import model.lancamentos.Despesa;
import model.lancamentos.Receita;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author richa
 */
public class ExtratoTest {
    
    public ExtratoTest() {
    }

    /**
     *
     */
    @Test
    public void test01_apagarArquivo() {
        
        Pessoa fulano = new Pessoa("Fulano");
        Extrato extrato = new Extrato(fulano);
        
        extrato.salvarDados();
        extrato.apagarArquivo();

        assertNull(extrato.getArquivo());

    }
    
    @Test
    public void test02_salvarDados() throws IOException {

        Pessoa titular = new Pessoa("Fulano");
        Extrato extrato = new Extrato(titular);
        String arquivoCSV = "lancamentos.csv";

        Receita receita = new Receita(LocalDate.now(), TipoReceita.SALARIO, "1000");
        Receita receita2 = new Receita(LocalDate.now().plusDays(1), TipoReceita.SALARIO, "200");
        Despesa despesa = new Despesa(LocalDate.now(), ALIMENTACAO, "100");
        Despesa despesa2 = new Despesa(LocalDate.now().plusDays(1), TRANSPORTE, "50");

        titular.adicionarReceita(1, receita);
        titular.adicionarReceita(2, receita2);
        titular.adicionarDespesa(3, despesa);
        titular.adicionarDespesa(4, despesa2);

        extrato.salvarDados();

        assertTrue(extrato.getArquivo().exists());

        try (BufferedReader br = new BufferedReader(new FileReader(arquivoCSV))) {

            String linha = br.readLine();
            assertEquals("\"Valor\",\"Data\",\"Categoria\",\"Tipo\",\"ID\"", linha);

            linha = br.readLine();
            assertEquals("1000," + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ",SALARIO,Receita,1", linha);
            linha = br.readLine();
            assertEquals("200," + LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ",SALARIO,Receita,2", linha);

            linha = br.readLine();
            assertEquals("100," + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ",ALIMENTACAO,Despesa,3", linha);
            linha = br.readLine();
            assertEquals("50," + LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ",TRANSPORTE,Despesa,4", linha);
        }
        
    }
    
    @Test
    public void test03_CarregarDados() throws IOException {

        Pessoa titular = new Pessoa("Fulano");
        ContaBancaria conta = new ContaBancaria("1");
        Extrato extrato = new Extrato(titular);
        String arquivoCSV = "lancamentos.csv";
        
        conta.setTitular(titular);
        titular.setConta(conta);

        Receita receita = new Receita(LocalDate.now(), TipoReceita.SALARIO, "1000");
        Receita receita2 = new Receita(LocalDate.now().plusDays(1), TipoReceita.SALARIO, "200");
        Despesa despesa = new Despesa(LocalDate.now(), ALIMENTACAO, "100");
        Despesa despesa2 = new Despesa(LocalDate.now().plusDays(1), TRANSPORTE, "50");

        titular.adicionarReceita(1, receita);
        titular.adicionarReceita(2, receita2);
        titular.adicionarDespesa(3, despesa);
        titular.adicionarDespesa(4, despesa2);

        extrato.salvarDados();
        
        titular.removerTodasDespesa();
        titular.removerTodasReceitas();
        
        extrato.carregarDados();

        try (BufferedReader br = new BufferedReader(new FileReader(arquivoCSV))) {

            String linha = br.readLine();
            assertEquals("\"Valor\",\"Data\",\"Categoria\",\"Tipo\",\"ID\"", linha);

            linha = br.readLine();
            assertEquals("1000," + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ",SALARIO,Receita,1", linha);
            linha = br.readLine();
            assertEquals("200," + LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ",SALARIO,Receita,2", linha);

            linha = br.readLine();
            assertEquals("100," + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ",ALIMENTACAO,Despesa,3", linha);
            linha = br.readLine();
            assertEquals("50," + LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ",TRANSPORTE,Despesa,4", linha);
        }
        
    } 
    
}
