/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.lancamentos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 *
 * @author csoler
 */
public class Lancamento {

    private BigDecimal valor;
    private LocalDate dataLancamento;

    public Lancamento(LocalDate dataLancamento, String valor) throws IllegalArgumentException, NumberFormatException{
        setDataLancamento(dataLancamento);
        setValor(valor);
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;

    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(String valor) {
        try {
            if (valor.equals("")) {
                throw new IllegalArgumentException("Valor não pode ser vazio ou nulo.");
            }
            BigDecimal valorConvertido = new BigDecimal(valor);
            if (valorConvertido.compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException("Valor inserido deve ser maior que zero!");
            }
            this.valor = valorConvertido;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Valor inserido deve ser um número válido.");
        }
    }
}
