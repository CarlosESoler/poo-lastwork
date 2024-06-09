/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.lancamentos;

import java.math.BigDecimal;
import model.interfaces.TipoReceita;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author csoler
 */
public class Receita extends Lancamento {
    TipoReceita tipoReceita;

    public Receita(LocalDate dataLancamento, TipoReceita tipoReceita, String valor) {
        super(dataLancamento, valor);
        setTipoReceita(tipoReceita);
    }

    public TipoReceita getTipoReceita() {
        return tipoReceita;
    }

    public void setTipoReceita(TipoReceita tipoReceita) {
        if (tipoReceita == null) {
            throw new NullPointerException("O tipo de receita não pode ser nulo.");
        }
        this.tipoReceita = tipoReceita;
    }
}
