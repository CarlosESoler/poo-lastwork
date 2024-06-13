/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.lancamentos.Despesa;
import model.lancamentos.Lancamento;
import model.lancamentos.Receita;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author csoler
 */
public class ContaBancaria implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String numero;
    private BigDecimal saldo = BigDecimal.ZERO;
    private Pessoa titular;
    private final Map<Lancamento, List<BigDecimal>> historicoLancamentos = new HashMap<>();

    //Construtor: inicializa settando o número da conta bancária
    public ContaBancaria(String numero) {
        setNumero(numero);
    }

    //Setter: define o número da conta bancária
    public void setNumero(String numero) {
        if ("".equals(numero) || numero.isBlank()) {
            throw new IllegalArgumentException("Número da conta não pode ser nulo ou vazio");
        }
        this.numero = numero;
    }

    //Capta o número da conta bancária
    public String getNumero() {
        return numero;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    private void setSaldo(BigDecimal saldo) {
        if (saldo == null || saldo.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Saldo não pode ser nulo ou negativo");
        }
        this.saldo = saldo;
    }

    //Getter: Capta o titular da conta
    public Pessoa getTitular() {
        return titular;
    }

    //Setter: define o titular da conta
    public void setTitular(Pessoa titular) {
        if (titular == null) {
            throw new IllegalArgumentException("Titular da conta não pode ser nulo");
        }
        this.titular = titular;
    }

    //Adiciona saldo à conta
    public BigDecimal somaSaldo(BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Valor não pode ser nulo ou negativo");
        }
        return this.saldo = this.saldo.add(valor);
    }

    //Subtrai saldo da conta
    public BigDecimal subtraiSaldo(BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Valor não pode ser nulo ou negativo");
        }
        return this.saldo = this.saldo.subtract(valor);
    }

    //Mostra o valor do saldo até o momento
    public BigDecimal consultaSaldoAtual() {
        BigDecimal saldoAtual = getSaldo();

        for (Receita r : getTitular().retornarReceitas()) {
            if (r.getDataLancamento().isBefore(LocalDate.now())
                    || r.getDataLancamento().isEqual(LocalDate.now())) {
                saldoAtual = saldoAtual.add(r.getValor());
            }
        }

        for (Despesa d : getTitular().retornarDespesas()) {
            if (d.getDataLancamento().isBefore(LocalDate.now())
                    || d.getDataLancamento().isEqual(LocalDate.now())) {
                saldoAtual = saldoAtual.subtract(d.getValor());
            }
        }
        return saldoAtual;
    }

    //Mostra o saldo geral (atual + futuro)
    public BigDecimal consultaSaldoIndependentePeriodo() {
        BigDecimal saldoAtual = getSaldo();

        for (Receita r : getTitular().retornarReceitas()) {
            saldoAtual = saldoAtual.add(r.getValor());
        }

        for (Despesa d : getTitular().retornarDespesas()) {
            saldoAtual = saldoAtual.subtract(d.getValor());
        }
        return saldoAtual;
    }

    //Mostra o valor das receitas até o momento
    public BigDecimal consultarValorReceitasAtual() {
        BigDecimal valorReceitas = getSaldo();
        for (Receita r : getTitular().retornarReceitas()) {
            if (r.getDataLancamento().isBefore(LocalDate.now())
                    || r.getDataLancamento().isEqual(LocalDate.now())) {
                valorReceitas = valorReceitas.add(r.getValor());
            }
        }
        return valorReceitas;
    }

    //Mostra o valor das despesas até o momento
    public BigDecimal consultarValorDespesasAtual() {
        BigDecimal valorDespesas = getSaldo();
        for (Despesa r : getTitular().retornarDespesas()) {
            if (r.getDataLancamento().isBefore(LocalDate.now())
                    || r.getDataLancamento().isEqual(LocalDate.now())) {
                valorDespesas = valorDespesas.add(r.getValor());
            }
        }
        return valorDespesas;
    }

    //Mostra o valor das receitas futuras
    public BigDecimal consultarValorReceitasFuturo() {
        BigDecimal valorReceitas = getSaldo();
        for (Receita r : getTitular().retornarReceitas()) {
            if (r.getDataLancamento().isAfter(LocalDate.now())) {
                valorReceitas = valorReceitas.add(r.getValor());
            }
        }
        return valorReceitas;
    }

    //Mostra o valor das despesas futuras
    public BigDecimal consultarValorDespesasFuturo() {
        BigDecimal valorDespesas = getSaldo();
        for (Despesa r : getTitular().retornarDespesas()) {
            if (r.getDataLancamento().isAfter(LocalDate.now())) {
                valorDespesas = valorDespesas.add(r.getValor());
            }
        }
        return valorDespesas;
    }

    //Mostra o valor das receitas do mês corrente
    public BigDecimal consultarValorReceitasMensal() {
        BigDecimal valorReceitas = getSaldo();
        for (Receita r : getTitular().retornarReceitas()) {
            if (r.getDataLancamento().getMonth() == (LocalDate.now().getMonth())) {
                valorReceitas = valorReceitas.add(r.getValor());
            }
        }
        return valorReceitas;
    }

    //Mostra o valor das despesas do mês corrente
    public BigDecimal consultarValorDespesasMensal() {
        BigDecimal valorDespesas = getSaldo();
        for (Despesa r : getTitular().retornarDespesas()) {
            if (r.getDataLancamento().getMonth() == (LocalDate.now().getMonth())) {
                valorDespesas = valorDespesas.add(r.getValor());
            }
        }
        return valorDespesas;
    }
    
    //Exibe o histórico de lançamentos
    public void exibirHistoricoDeLancamentos() {
        for (Map.Entry<Lancamento, List<BigDecimal>> entry : historicoLancamentos.entrySet()) {
            System.out.println("Lançamento: " +
                    entry.getKey().getClass().getSimpleName() + " - Valor: " +
                    entry.getKey().getValor() + " - Data: " +
                    entry.getKey().getDataLancamento() + " - Saldo Anterior: " +
                    entry.getValue().get(0) + " - Saldo Atual: " + entry.getValue().get(1));
        }
    }
}
