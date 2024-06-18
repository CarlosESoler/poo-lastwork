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
 * Classe: essa classe refere-se à gravação do histórico dos lançamentos realizados 
 */
public class HistoricoLancamento {

    private Lancamento lancamento;
    private BigDecimal saldoAnterior;
    private BigDecimal saldoPosterior;
    
     /**
     * Construtor: Apenas um construtor vazio!
     */
    public HistoricoLancamento() {
    }

    /**
     * Construtor: inicializa as variáveis referentes a lançamento e saldo anterior ao lançamento atual
     * @param lancamento do tipo Lancamento
     * @param saldoAnterior do tipo BigDecimal
     */
    public HistoricoLancamento(Lancamento lancamento, BigDecimal saldoAnterior) {
        this.lancamento = lancamento;
        setSaldoAnterior(saldoAnterior);
        if (this.lancamento instanceof Receita) {
            setSaldoPosterior(getSaldoAnterior().add(lancamento.getValor()));
        } else if (this.lancamento instanceof Despesa) {
            setSaldoPosterior(getSaldoAnterior().subtract(lancamento.getValor()));
        }

    }

    /**
     * Getter: capta o lançamento realizado
     * @return lancamento do tipo Lancamento
     */
    public Lancamento getLancamento() {
        return lancamento;
    }

    /**
     * Setter: registra o lançamento
     * @param lancamento do tipo Lancamento
     */
    public void setLancamento(Lancamento lancamento) {
        this.lancamento = lancamento;
    }

    /**
     * Getter: capta o saldo anterior ao lançamento atual
     * @return saldoAnterior do tipo BigDecimal
     */
    public BigDecimal getSaldoAnterior() {
        return saldoAnterior;
    }

    /**
     * Setter: registra o saldo anterior ao lançamento atual
     * @param saldoAnterior do tipo BigDecimal
     */
    public void setSaldoAnterior(BigDecimal saldoAnterior) {
        this.saldoAnterior = saldoAnterior;
    }

    /**
     * Getter: capta o saldo anterior após o registro do lançamento atual
     * @return saldoPosterior do tipo BigDecimal
     */
    public BigDecimal getSaldoPosterior() {
        return saldoPosterior;
    }

    /**
     * Setter: define o saldo posterior após o registro do lançamento atual
     * @param saldoPosterior do tipo BigDecimal
     */
    public void setSaldoPosterior(BigDecimal saldoPosterior) {
        this.saldoPosterior = saldoPosterior;
    }
}
