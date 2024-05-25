/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.lancamentos;

import model.interfaces.TipoDespesa;

import java.math.BigInteger;
import java.time.LocalDate;

/**
 *
 * @author csoler
 */
public class Despesa extends Lancamento {
    TipoDespesa tipoDespesa;

    public Despesa(BigInteger valor, LocalDate dataLancamento, TipoDespesa tipoDespesa) {
        super(valor, dataLancamento);
        this.tipoDespesa = tipoDespesa;
    }

    public TipoDespesa getTipoDespesa() {
        return tipoDespesa;
    }

    public void setTipoDespesa(TipoDespesa tipoDespesa) {
        this.tipoDespesa = tipoDespesa;
    }
}
