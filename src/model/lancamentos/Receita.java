/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.lancamentos;

import model.interfaces.TipoReceita;

import java.math.BigInteger;
import java.time.LocalDate;

/**
 *
 * @author csoler
 */
public class Receita extends Lancamento {
    TipoReceita tipoReceita;

    public Receita(BigInteger valor, LocalDate dataLancamento, TipoReceita tipoReceita) {
        super(valor, dataLancamento);
        this.tipoReceita = tipoReceita;
    }

    public TipoReceita getTipoReceita() {
        return tipoReceita;
    }

    public void setTipoReceita(TipoReceita tipoReceita) {
        this.tipoReceita = tipoReceita;
    }
}
