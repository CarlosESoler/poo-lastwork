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
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Arrays;
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
    private BigInteger saldo;
    private Pessoa titular;
    private final Map<Lancamento, List<BigInteger>> historicoLancamentos = new HashMap<>();

    public ContaBancaria(String numero, BigInteger saldo, Pessoa titular) {
        setNumero(numero);
        setSaldo(saldo);
        setTitular(titular);
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        if(numero == null || numero.isBlank()) {
            throw new IllegalArgumentException("Número da conta não pode ser nulo ou vazio");
        }
        this.numero = numero;
    }

    public BigInteger getSaldo() {
        return saldo;
    }

    private void setSaldo(BigInteger saldo) {
        if(saldo == null || saldo.compareTo(BigInteger.ZERO) < 0) {
            throw new IllegalArgumentException("Saldo não pode ser nulo ou negativo");
        }
        this.saldo = saldo;
    }

    public Pessoa getTitular() {
        return titular;
    }

    public void setTitular(Pessoa titular) {
        if(titular == null) {
            throw new IllegalArgumentException("Titular da conta não pode ser nulo");
        }
        this.titular = titular;
    }

    public BigInteger somaSaldo(BigInteger valor) {
        if(valor == null || valor.compareTo(BigInteger.ZERO) < 0) {
            throw new IllegalArgumentException("Valor não pode ser nulo ou negativo");
        }
        return this.saldo = this.saldo.add(valor);
    }

    public BigInteger subtraiSaldo(BigInteger valor) {
        if(valor == null || valor.compareTo(BigInteger.ZERO) < 0) {
            throw new IllegalArgumentException("Valor não pode ser nulo ou negativo");
        }
        return this.saldo = this.saldo.subtract(valor);
    }

    public void addHistoricoDeLancamentos(Lancamento lancamento) {

        // TODO - Refactor this code
        if(lancamento instanceof Despesa) {
            this.historicoLancamentos.put(lancamento, Arrays.asList(getSaldo(), this.subtraiSaldo(lancamento.getValor())));
            return;
        }
        this.historicoLancamentos.put(lancamento, Arrays.asList(getSaldo(), this.somaSaldo(lancamento.getValor())));
    }

    public void exibirHistoricoDeLancamentos() {
        for (Map.Entry<Lancamento, List<BigInteger>> entry : historicoLancamentos.entrySet()) {
            System.out.println("Lançamento: " +
                    entry.getKey().getClass().getSimpleName() + " - Valor: " +
                    entry.getKey().getValor() + " - Data: " +
                    entry.getKey().getDataLancamento() + " - Saldo Anterior: " +
                    entry.getValue().get(0) + " - Saldo Atual: " + entry.getValue().get(1));
        }
    }

    public BigInteger consultaSaldoAtual() {
        BigInteger saldoAtual = getSaldo();

        for (Receita r : titular.getReceitas()) {
            if (r.getDataLancamento().isBefore(LocalDate.now())
                    || r.getDataLancamento().isEqual(LocalDate.now())) {
                saldoAtual = saldoAtual.add(r.getValor());
            }
        }

        for (Despesa d : titular.getDespesa()) {
            if (d.getDataLancamento().isBefore(LocalDate.now())
                    || d.getDataLancamento().isEqual(LocalDate.now())) {
                saldoAtual = saldoAtual.subtract(d.getValor());
            }
        }
        return saldoAtual;
    }

    public BigInteger consultaSaldoIndependentePeriodo() {
        BigInteger saldoAtual = getSaldo();

        for (Receita r : titular.getReceitas()) {
            saldoAtual = saldoAtual.add(r.getValor());
        }

        for (Despesa d : titular.getDespesa()) {
            saldoAtual = saldoAtual.subtract(d.getValor());
        }
        return saldoAtual;
    }
}
