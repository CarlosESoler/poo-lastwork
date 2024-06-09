/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import model.lancamentos.Lancamento;

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
        setSaldoPosterior(lancamento.getValor().add(saldoAnterior));
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