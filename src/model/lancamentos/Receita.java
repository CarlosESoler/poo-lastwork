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
 * Classe: essa classe refere-se ao registro de uma receita 
 * Herda métodos diretamente de Lancamento
 */
public class Receita extends Lancamento {
    TipoReceita tipoReceita;
    
    /**
     * Construtor: inicializa as variáveis referentes a data, tipo e valor da receita
     * @param dataLancamento do tipo LocalDate
     * @param tipoReceita do tipo TipoReceita
     * @param valor do tipo String
     */
    public Receita(LocalDate dataLancamento, TipoReceita tipoReceita, String valor) {
        super(dataLancamento, valor);
        setTipoReceita(tipoReceita);
    }

    /**
     * Getter: capta o tipo da receita
     * @return tipoReceita do tipo TipoReceita
     */
    public TipoReceita getTipoReceita() {
        return tipoReceita;
    }

    /**
     * Setter: define o tipo de receita
     * @param tipoReceita do tipo TipoReceita
     */
    public void setTipoReceita(TipoReceita tipoReceita) {
        if (tipoReceita == null) {
            throw new NullPointerException("O tipo de receita não pode ser nulo!");
        }
        this.tipoReceita = tipoReceita;
    }
}
