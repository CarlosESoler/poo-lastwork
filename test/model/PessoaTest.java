/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.util.Map;
import static model.interfaces.TipoDespesa.RESIDENCIA;
import static model.interfaces.TipoDespesa.SAUDE;
import static model.interfaces.TipoReceita.SALARIO;
import model.lancamentos.Despesa;
import model.lancamentos.Receita;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author richa
 */
public class PessoaTest {
    
    public PessoaTest() {
    }

    @Test
    public void test01_adicionarHistoricoLancamento(){
        Pessoa fulano = new Pessoa("Fulano");
        HistoricoLancamento historico = new HistoricoLancamento();
        
        fulano.adicionarHistoricoLancamento(1, historico);
        
        assertNotNull(fulano.getHistorico().get(1));
    }
    
    @Test
    public void test02_adicionarReceita() {
        
        Pessoa fulano = new Pessoa("Fulano");
        ContaBancaria conta = new ContaBancaria("1");
        
        fulano.setConta(conta);
        conta.setTitular(fulano);
        
        LocalDate data = LocalDate.of(2024, 6, 17);
        Receita receita = new Receita(data, SALARIO, "1000");
        
        fulano.adicionarReceita(1, receita);

        assertNotNull(fulano.getReceitasMap().get(1));
    }
    
    @Test
    public void test03_adicionarDespesa() {
        
        Pessoa fulano = new Pessoa("Fulano");
        ContaBancaria conta = new ContaBancaria("1");
        
        fulano.setConta(conta);
        conta.setTitular(fulano);
        
        LocalDate data = LocalDate.of(2024, 6, 17);
        Despesa despesa = new Despesa(data, SAUDE, "1000");
        
        fulano.adicionarDespesa(1, despesa);

        assertNotNull(fulano.getDespesasMap().get(1));
    }
    
    @Test
    public void test04_removerTodasReceitas() {
        
        Pessoa fulano = new Pessoa("Fulano");
        ContaBancaria conta = new ContaBancaria("1");
        
        fulano.setConta(conta);
        conta.setTitular(fulano);
        
        LocalDate data = LocalDate.of(2024, 6, 17);
        Receita receita = new Receita(data, SALARIO, "1000");
        
        fulano.adicionarReceita(1, receita);
        fulano.removerTodasReceitas();

        assertNull(fulano.getReceitasMap().get(1));
    }
    
    @Test
    public void test05_removerTodasDespesa() {
        
        Pessoa fulano = new Pessoa("Fulano");
        ContaBancaria conta = new ContaBancaria("1");
        
        fulano.setConta(conta);
        conta.setTitular(fulano);
        
        LocalDate data = LocalDate.of(2024, 6, 17);
        Despesa despesa = new Despesa(data, SAUDE, "1000");
        
        fulano.adicionarDespesa(1, despesa);
        fulano.removerTodasDespesa();

        assertNull(fulano.getDespesasMap().get(1));
    }
    
    @Test
    public void test06_apagarHistorico(){
        Pessoa fulano = new Pessoa("Fulano");
        HistoricoLancamento historico = new HistoricoLancamento();
        
        fulano.adicionarHistoricoLancamento(1, historico);
        fulano.apagarHistorico();
        
        assertNull(fulano.getHistorico().get(1));
    }
    
    @Test
    public void test07_exibirDemonstrativoReceitasAtuais() {
        
        Pessoa fulano = new Pessoa("Fulano");
        ContaBancaria conta = new ContaBancaria("1");
        
        fulano.setConta(conta);
        conta.setTitular(fulano);
        
        LocalDate data = LocalDate.of(2024, 6, 17);
        LocalDate data2 = LocalDate.of(2050, 6, 17);
        
        Receita receita = new Receita(data, SALARIO, "1000");
        Receita receita2 = new Receita(data, SALARIO, "5000");
        Receita receita3 = new Receita(data2, SALARIO, "50000");
        
        fulano.adicionarReceita(2, receita);
        fulano.adicionarReceita(1, receita2);
        fulano.adicionarReceita(3, receita3);

        Map<Integer, Receita> resultado = fulano.exibirDemonstrativoReceitasAtuais();
        
        int[] expectedOrder = {1, 2};
        int index = 0;
        for (Integer id : resultado.keySet()) {
            assertEquals((Integer) expectedOrder[index++], id);
        }
    }
    
    @Test
    public void test08_exibirDemonstrativoReceitasFuturas() {
        
        Pessoa fulano = new Pessoa("Fulano");
        ContaBancaria conta = new ContaBancaria("1");
        
        fulano.setConta(conta);
        conta.setTitular(fulano);
        
        LocalDate data = LocalDate.of(2024, 6, 17);
        LocalDate data2 = LocalDate.of(2050, 6, 17);
        
        Receita receita = new Receita(data, SALARIO, "1000");
        Receita receita2 = new Receita(data, SALARIO, "5000");
        Receita receita3 = new Receita(data2, SALARIO, "50000");
        
        fulano.adicionarReceita(2, receita);
        fulano.adicionarReceita(1, receita2);
        fulano.adicionarReceita(3, receita3);

        Map<Integer, Receita> resultado = fulano.exibirDemonstrativoReceitasFuturas();
        
        int[] expectedOrder = {3};
        int index = 0;
        for (Integer id : resultado.keySet()) {
            assertEquals((Integer) expectedOrder[index++], id);
        }
    }
    
    @Test
    public void test09_exibirDemonstrativoDespesasAtuais() {
        
        Pessoa fulano = new Pessoa("Fulano");
        ContaBancaria conta = new ContaBancaria("1");
        
        fulano.setConta(conta);
        conta.setTitular(fulano);
        
        LocalDate data = LocalDate.of(2024, 6, 17);
        LocalDate data2 = LocalDate.of(2050, 6, 17);
        
        Despesa despesa = new Despesa(data, SAUDE, "1000");
        Despesa despesa2 = new Despesa(data, SAUDE, "2000");
        Despesa despesa3 = new Despesa(data2, RESIDENCIA, "50000");
        
        fulano.adicionarDespesa(1, despesa);
        fulano.adicionarDespesa(2, despesa2);
        fulano.adicionarDespesa(3, despesa3);

        Map<Integer, Despesa> resultado = fulano.exibirDemonstrativoDespesasAtuais();
        
        int[] expectedOrder = {1, 2};
        int index = 0;
        for (Integer id : resultado.keySet()) {
            assertEquals((Integer) expectedOrder[index++], id);
        }
    }
    
    @Test
    public void test10_exibirDemonstrativoDespesasFuturas() {
        
        Pessoa fulano = new Pessoa("Fulano");
        ContaBancaria conta = new ContaBancaria("1");
        
        fulano.setConta(conta);
        conta.setTitular(fulano);
        
        LocalDate data = LocalDate.of(2024, 6, 17);
        LocalDate data2 = LocalDate.of(2050, 6, 17);
        
        Receita receita = new Receita(data, SALARIO, "1000");
        Receita receita2 = new Receita(data, SALARIO, "5000");
        Receita receita3 = new Receita(data2, SALARIO, "50000");
        
        fulano.adicionarReceita(2, receita);
        fulano.adicionarReceita(1, receita2);
        fulano.adicionarReceita(3, receita3);

        Map<Integer, Despesa> resultado = fulano.exibirDemonstrativoDespesasFuturas();
        
        int[] expectedOrder = {3};
        int index = 0;
        for (Integer id : resultado.keySet()) {
            assertEquals((Integer) expectedOrder[index++], id);
        }
    }
    
    @Test
    public void test11_removerReceita() {
        
        Pessoa fulano = new Pessoa("Fulano");
        ContaBancaria conta = new ContaBancaria("1");
        
        fulano.setConta(conta);
        conta.setTitular(fulano);
        
        LocalDate data = LocalDate.of(2024, 6, 17);
        Receita receita = new Receita(data, SALARIO, "1000");
        
        fulano.adicionarReceita(1, receita);
        fulano.removerReceita(1);

        assertNull(fulano.getReceitasMap().get(1));
    }
    
    @Test
    public void test12_removerDespesa() {
        
        Pessoa fulano = new Pessoa("Fulano");
        ContaBancaria conta = new ContaBancaria("1");
        
        fulano.setConta(conta);
        conta.setTitular(fulano);
        
        LocalDate data = LocalDate.of(2024, 6, 17);
        Despesa despesa = new Despesa(data, SAUDE, "1000");
        
        fulano.adicionarDespesa(1, despesa);
        fulano.removerDespesa(1);

        assertNull(fulano.getDespesasMap().get(1));
    }
    
}
