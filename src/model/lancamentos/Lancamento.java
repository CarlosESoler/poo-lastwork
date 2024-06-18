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
 * Classe: essa classe refere-se ao registro de lançamentos genéricos
 */
public class Lancamento {

    private BigDecimal valor;
    private LocalDate dataLancamento;

    /**
     * Construtor: inicializa as variáveis referentes a data e valor do lançamento
     * @param dataLancamento do tipo LocalDate
     * @param valor do tipo String
     * @throws IllegalArgumentException 
     */
    public Lancamento(LocalDate dataLancamento, String valor) throws IllegalArgumentException {
        setDataLancamento(dataLancamento);
        setValor(valor);
    }

    /**
     * Getter: capta a data de lançamento
     * @return dataLancamento do tipo LocalDate
     */
    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    /**
     * Setter: Registra a data de lançamento 
     * @param dataLancamento do tipo LocalDate
     */
    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    /**
     * Getter: capta o valor do lançamento
     * @return valor do tipo BigDecimal
     */
    public BigDecimal getValor() {
        return valor;
    }
    
    /**
     * Setter: registra o valor do lançamento recebido em String e registra-o em BigDecimal
     * @param valor do tipo String
     */
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
