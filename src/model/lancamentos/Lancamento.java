/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.lancamentos;

import java.math.BigInteger;
import java.time.LocalDate;

/**
 *
 * @author csoler
 */
public class Lancamento {
    private BigInteger valor;
    private LocalDate dataLancamento;

    public Lancamento(BigInteger valor, LocalDate dataLancamento) {
       setValor(valor);
       setDataLancamento(dataLancamento);
    }

    public LocalDate getDataLancamento() {
        if(dataLancamento == null) {
            throw new IllegalArgumentException("Data de lançamento não pode ser nula");
        }
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public BigInteger getValor() {
        return valor;
    }

    public void setValor(BigInteger valor) {
        if(valor == null || valor.compareTo(BigInteger.ZERO) < 0) {
            throw new IllegalArgumentException("Valor não pode ser nulo");
        }
        this.valor = valor;
    }
}