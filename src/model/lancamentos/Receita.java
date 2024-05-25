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
        setTipoReceita(tipoReceita);
    }

    public TipoReceita getTipoReceita() {
        return tipoReceita;
    }

    public void setTipoReceita(TipoReceita tipoReceita) {
        if(tipoReceita == null) {
            throw new IllegalArgumentException("Tipo de receita não pode ser nulo");
        }
        this.tipoReceita = tipoReceita;
    }
}
