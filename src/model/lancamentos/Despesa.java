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
 * Classe: essa classe refere-se ao registro de uma despesa
 * Herda métodos diretamente de Lancamento
 */
public class Despesa extends Lancamento {
    TipoDespesa tipoDespesa;
    
    /**
     * Construtor: inicializa as variáveis referentes a data, tipo e valor da despesa
     * @param dataLancamento do tipo LocalDate
     * @param tipoDespesa do tipo TipoDespesa
     * @param valor do tipo String
     */
    public Despesa(LocalDate dataLancamento, TipoDespesa tipoDespesa, String valor) {
        super(dataLancamento, valor);
        setTipoDespesa(tipoDespesa);
    }

    /**
     * Getter: capta o tipo da despesa
     * @return tipoDespesa do tipo TipoDespesa
     */
    public TipoDespesa getTipoDespesa() {
        return tipoDespesa;
    }
    
/**
 * Setter: define o tipo de despesa
 * @param tipoDespesa do tipo TipoDespesa
 */
    public void setTipoDespesa(TipoDespesa tipoDespesa) {
        if(tipoDespesa == null) {
            throw new IllegalArgumentException("Tipo de despesa não pode ser nulo!");
        }
        this.tipoDespesa = tipoDespesa;
    }
}

