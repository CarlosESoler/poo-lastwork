/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.lancamentos;

import java.math.BigDecimal;
import model.interfaces.TipoDespesa;

import java.math.BigDecimal;
import java.time.LocalDate;


/**
 *
 * @author csoler
 */
public class Despesa extends Lancamento {
    TipoDespesa tipoDespesa;

    public Despesa(LocalDate dataLancamento, TipoDespesa tipoDespesa, String valor) {
        super(dataLancamento, valor);
        setTipoDespesa(tipoDespesa);
    }

    public TipoDespesa getTipoDespesa() {
        return tipoDespesa;
    }

    public void setTipoDespesa(TipoDespesa tipoDespesa) {
        if(tipoDespesa == null) {
            throw new IllegalArgumentException("Tipo de despesa não pode ser nulo");
        }
        this.tipoDespesa = tipoDespesa;
    }
}
