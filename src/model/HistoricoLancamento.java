/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import model.lancamentos.Despesa;

import model.lancamentos.Lancamento;
import model.lancamentos.Receita;

/**
 *
 * @author csoler
 */
public class HistoricoLancamento {

    private Lancamento lancamento;
    private BigDecimal saldoAnterior;
    private BigDecimal saldoPosterior;

    public HistoricoLancamento() {
    }

    public HistoricoLancamento(Lancamento lancamento, BigDecimal saldoAnterior) {
        this.lancamento = lancamento;
        setSaldoAnterior(saldoAnterior);
        if (this.lancamento instanceof Receita) {
            setSaldoPosterior(getSaldoAnterior().add(lancamento.getValor()));
        } else if (this.lancamento instanceof Despesa) {
            setSaldoPosterior(getSaldoAnterior().subtract(lancamento.getValor()));
        }

    }

    public Lancamento getLancamento() {
        return lancamento;
    }

    public void setLancamento(Lancamento lancamento) {
        this.lancamento = lancamento;
    }

    public BigDecimal getSaldoAnterior() {
        return saldoAnterior;
    }

    public void setSaldoAnterior(BigDecimal saldoAnterior) {
        this.saldoAnterior = saldoAnterior;
    }

    public BigDecimal getSaldoPosterior() {
        return saldoPosterior;
    }

    public void setSaldoPosterior(BigDecimal saldoPosterior) {
        this.saldoPosterior = saldoPosterior;
    }
}
