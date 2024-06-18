/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package model;

import java.math.BigDecimal;
import java.time.LocalDate;
import static model.interfaces.TipoDespesa.RESIDENCIA;
import static model.interfaces.TipoDespesa.SAUDE;
import static model.interfaces.TipoDespesa.TRANSPORTE;
import static model.interfaces.TipoReceita.SALARIO;
import model.lancamentos.Despesa;
import model.lancamentos.Receita;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author richa
 */
public class ContaBancariaTest {

    public ContaBancariaTest() {
    }

    @Test
    public void test01_somaSaldo() {

        ContaBancaria conta = new ContaBancaria("1");

        conta.somaSaldo("1000");
        conta.somaSaldo("2000");
        conta.somaSaldo("3000");

        BigDecimal montanteResultado = new BigDecimal("6000");

        assertEquals(montanteResultado, conta.getSaldo());

    }

    @Test
    public void test02_subtraiSaldo() {

        ContaBancaria conta = new ContaBancaria("1");

        conta.somaSaldo("10000");
        conta.subtraiSaldo("2000");
        conta.subtraiSaldo("3000");

        BigDecimal montanteResultado = new BigDecimal("5000");

        assertEquals(montanteResultado, conta.getSaldo());

    }

    @Test
    public void test03_consultaSaldoAtual() {

        Pessoa fulano = new Pessoa("Fulano");
        ContaBancaria conta = new ContaBancaria("1");

        fulano.setConta(conta);
        conta.setTitular(fulano);

        LocalDate data = LocalDate.of(2024, 6, 17);
        LocalDate data3 = LocalDate.of(2024, 7, 17);

        Receita receita = new Receita(data, SALARIO, "1000");
        Despesa despesa = new Despesa(data, SAUDE, "500");
        Receita receita2 = new Receita(data3, SALARIO, "5000");

        fulano.adicionarReceita(1, receita);
        fulano.adicionarDespesa(2, despesa);
        fulano.adicionarReceita(3, receita2);

        BigDecimal montanteResultado = new BigDecimal("500");

        assertEquals(montanteResultado, conta.consultaSaldoAtual());

    }

    @Test
    public void test04_consultaSaldoAtual() {

        Pessoa fulano = new Pessoa("Fulano");
        ContaBancaria conta = new ContaBancaria("1");

        fulano.setConta(conta);
        conta.setTitular(fulano);

        LocalDate data = LocalDate.of(2024, 6, 17);
        LocalDate data2 = LocalDate.of(2024, 7, 17);

        Receita receita = new Receita(data, SALARIO, "1000");
        Receita receita2 = new Receita(data2, SALARIO, "5000");

        fulano.adicionarReceita(1, receita);
        fulano.adicionarReceita(2, receita2);

        BigDecimal montanteResultado = new BigDecimal("6000");

        assertEquals(montanteResultado, conta.consultaSaldoIndependentePeriodo());

    }

    @Test
    public void test05_consultarValorReceitasAtual() {

        Pessoa fulano = new Pessoa("Fulano");
        ContaBancaria conta = new ContaBancaria("1");

        fulano.setConta(conta);
        conta.setTitular(fulano);

        LocalDate data = LocalDate.of(2024, 6, 17);
        LocalDate data2 = LocalDate.of(2024, 7, 17);

        Receita receita = new Receita(data, SALARIO, "1000");
        Receita receita2 = new Receita(data, SALARIO, "2000");
        Receita receita3 = new Receita(data, SALARIO, "3000");
        Receita receita4 = new Receita(data2, SALARIO, "5000");

        fulano.adicionarReceita(1, receita);
        fulano.adicionarReceita(2, receita2);
        fulano.adicionarReceita(3, receita3);
        fulano.adicionarReceita(4, receita4);

        BigDecimal montanteResultado = new BigDecimal("6000");

        assertEquals(montanteResultado, conta.consultarValorReceitasAtual());

    }

    @Test
    public void test06_consultarValorDespesasAtual() {

        Pessoa fulano = new Pessoa("Fulano");
        ContaBancaria conta = new ContaBancaria("1");

        fulano.setConta(conta);
        conta.setTitular(fulano);

        LocalDate data = LocalDate.of(2024, 6, 17);
        LocalDate data2 = LocalDate.of(2024, 7, 17);

        Despesa despesa = new Despesa(data, SAUDE, "500");
        Despesa despesa2 = new Despesa(data, TRANSPORTE, "100");
        Despesa despesa3 = new Despesa(data, RESIDENCIA, "1400");
        Despesa despesa4 = new Despesa(data2, SAUDE, "1000");

        fulano.adicionarDespesa(1, despesa);
        fulano.adicionarDespesa(2, despesa2);
        fulano.adicionarDespesa(3, despesa3);
        fulano.adicionarDespesa(4, despesa4);

        BigDecimal montanteResultado = new BigDecimal("-2000");

        assertEquals(montanteResultado, conta.consultarValorDespesasAtual());

    }

    @Test
    public void test07_consultarValorReceitasFuturo() {

        Pessoa fulano = new Pessoa("Fulano");
        ContaBancaria conta = new ContaBancaria("1");

        fulano.setConta(conta);
        conta.setTitular(fulano);

        LocalDate data = LocalDate.of(2024, 6, 17);
        LocalDate data2 = LocalDate.of(2024, 7, 17);

        Receita receita = new Receita(data, SALARIO, "1000");
        Receita receita2 = new Receita(data2, SALARIO, "2000");
        Receita receita3 = new Receita(data2, SALARIO, "3000");
        Receita receita4 = new Receita(data2, SALARIO, "5000");

        fulano.adicionarReceita(1, receita);
        fulano.adicionarReceita(2, receita2);
        fulano.adicionarReceita(3, receita3);
        fulano.adicionarReceita(4, receita4);

        BigDecimal montanteResultado = new BigDecimal("10000");

        assertEquals(montanteResultado, conta.consultarValorReceitasFuturo());

    }

    @Test
    public void test08_consultarValorDespesasFuturo() {

        Pessoa fulano = new Pessoa("Fulano");
        ContaBancaria conta = new ContaBancaria("1");

        fulano.setConta(conta);
        conta.setTitular(fulano);

        LocalDate data = LocalDate.of(2024, 6, 17);
        LocalDate data2 = LocalDate.of(2024, 7, 17);

        Despesa despesa = new Despesa(data, SAUDE, "500");
        Despesa despesa2 = new Despesa(data2, TRANSPORTE, "100");
        Despesa despesa3 = new Despesa(data2, RESIDENCIA, "1400");
        Despesa despesa4 = new Despesa(data2, SAUDE, "1000");

        fulano.adicionarDespesa(1, despesa);
        fulano.adicionarDespesa(2, despesa2);
        fulano.adicionarDespesa(3, despesa3);
        fulano.adicionarDespesa(4, despesa4);

        BigDecimal montanteResultado = new BigDecimal("-2500");

        assertEquals(montanteResultado, conta.consultarValorDespesasFuturo());

    }

    @Test
    public void test09_consultarValorReceitasMensal() {

        Pessoa fulano = new Pessoa("Fulano");
        ContaBancaria conta = new ContaBancaria("1");

        fulano.setConta(conta);
        conta.setTitular(fulano);

        LocalDate data = LocalDate.of(2024, 6, 17);
        LocalDate data2 = LocalDate.of(2024, 7, 17);
        LocalDate data3 = LocalDate.of(2024, 5, 17);

        Receita receita = new Receita(data2, SALARIO, "1000");
        Receita receita2 = new Receita(data, SALARIO, "2000");
        Receita receita3 = new Receita(data, SALARIO, "3000");
        Receita receita4 = new Receita(data, SALARIO, "5000");
        Receita receita5 = new Receita(data3, SALARIO, "5000");

        fulano.adicionarReceita(1, receita);
        fulano.adicionarReceita(2, receita2);
        fulano.adicionarReceita(3, receita3);
        fulano.adicionarReceita(4, receita4);
        fulano.adicionarReceita(5, receita5);

        BigDecimal montanteResultado = new BigDecimal("10000");

        assertEquals(montanteResultado, conta.consultarValorReceitasMensal());

    }

    @Test
    public void test10_consultarValorDespesasMensal() {

        Pessoa fulano = new Pessoa("Fulano");
        ContaBancaria conta = new ContaBancaria("1");

        fulano.setConta(conta);
        conta.setTitular(fulano);

        LocalDate data = LocalDate.of(2024, 6, 17);
        LocalDate data2 = LocalDate.of(2024, 7, 17);
        LocalDate data3 = LocalDate.of(2024, 5, 17);

        Despesa despesa = new Despesa(data2, SAUDE, "500");
        Despesa despesa2 = new Despesa(data, TRANSPORTE, "100");
        Despesa despesa3 = new Despesa(data, RESIDENCIA, "1400");
        Despesa despesa4 = new Despesa(data, SAUDE, "1000");
        Despesa despesa5 = new Despesa(data3, SAUDE, "1000");

        fulano.adicionarDespesa(1, despesa);
        fulano.adicionarDespesa(2, despesa2);
        fulano.adicionarDespesa(3, despesa3);
        fulano.adicionarDespesa(4, despesa4);
        fulano.adicionarDespesa(5, despesa5);

        BigDecimal montanteResultado = new BigDecimal("-2500");

        assertEquals(montanteResultado, conta.consultarValorDespesasMensal());

    }

    @Test
    public void test10_isDataMenorOuIgual() {

        ContaBancaria conta = new ContaBancaria("1");

        LocalDate data = LocalDate.of(2024, 6, 17);

        assertTrue(conta.isDataMenorOuIgual(data));

    }

    @Test
    public void test11_isDataMenorOuIgual() {

        ContaBancaria conta = new ContaBancaria("1");

        LocalDate data = LocalDate.of(2050, 6, 17);

        assertFalse(conta.isDataMenorOuIgual(data));

    }

}
