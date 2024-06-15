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
import java.util.UUID;

/**
 *
 * @author csoler
 */
public class Lancamento {

    private BigDecimal valor;
    private LocalDate dataLancamento;

    public Lancamento(LocalDate dataLancamento, String valor) throws IllegalArgumentException {
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
            if (valor.isEmpty()) {
                throw new IllegalArgumentException("Valor do lançamento deve ser preenchido!");
            }
            BigDecimal valorConvertido = new BigDecimal(valor);
            if (valorConvertido.compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException("Valor do lançamento inserido não pode ser negativo!");
            }
            this.valor = valorConvertido;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Valor do lançamento inválido!");
        }
    }
}
