/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.lancamentos.Despesa;
import model.lancamentos.Receita;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Classe: essa classe refere-se a criação de um conta bancária, sendo incializada com o registro do número da conta
 */
public class ContaBancaria {

    private int numero;
    private BigDecimal saldo = BigDecimal.ZERO;
    private Pessoa titular;

    /**
     * Construtor: inicializa o objeto registrando o n[umero da conta
     * @param numero do tipo  String 
     */
    public ContaBancaria(String numero) {

        setNumero(numero);
    }

    /**
     * Setter: define o número da conta bancária convertendo-o para o tipo int
     * @param numero do tipo String
     */
    public void setNumero(String numero) {
        if (numero.isEmpty() || numero.isBlank()) {
            throw new IllegalArgumentException("Número da conta não pode ser nulo ou vazio");
        }

        if (numero.length() >= 10) {
            throw new IllegalArgumentException("Número da conta não pode ter mais que 10 dígitos");
        }

        try {
            this.numero = Integer.parseInt(numero);

        } catch (NumberFormatException ex) {
            throw new NumberFormatException("Número da conta deve ter apenas dígitos!");
        }

    }
    
    

    /**
     * Getter: Capta o número da conta bancária
     * @return numero da conta bancária como int
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Getter: Capta o titular da conta
     * @return titular da conta bancária como String
     */
    public Pessoa getTitular() {
        return titular;
    }

    /**
     * Setter: define o titular da conta
     * @param titular da conta do tipo Pessoa
     */
    public void setTitular(Pessoa titular) {
        if (titular == null) {
            throw new IllegalArgumentException("Titular da conta não pode ser nulo");
        }
        this.titular = titular;
    }

     /**
     * Getter: Capta o o saldo da conta
     * @return saldo da conta bancária como BigDecimal
     */
    public BigDecimal getSaldo() {
        return saldo;
    }
    

    /**
     * Método sem retorno que soma saldo à conta bancária
     * @param valor do tipo String
     */
    public void somaSaldo(String valor) {
        try {
            if (valor.isEmpty()) {
                throw new IllegalArgumentException("Valor do lançamento deve ser preenchido!");
            }
            BigDecimal valorConvertido = new BigDecimal(valor);
            if (valorConvertido.compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException("Valor do lançamento inserido não pode ser negativo!");
            }
            this.saldo = this.saldo.add(valorConvertido);

        } catch (NumberFormatException e) {
            throw new NumberFormatException("Valor do lançamento inválido!");
        }
    }

    /**
     * Método sem retorno que subtrai saldo à conta bancária
     * @param valor do tipo String
     */
    public void subtraiSaldo(String valor) {
        try {
            if (valor.isEmpty()) {
                throw new IllegalArgumentException("Valor do lançamento deve ser preenchido!");
            }
            BigDecimal valorConvertido = new BigDecimal(valor);
            if (valorConvertido.compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException("Valor do lançamento inserido não pode ser negativo!");
            }
            this.saldo = this.saldo.subtract(valorConvertido);

        } catch (NumberFormatException e) {
            throw new NumberFormatException("Valor do lançamento inválido!");
        }
    }

    /**
     * Método que percorre as receitas e despesas 
     * @return saldo até o dia atual em BigDecimal 
     */
    public BigDecimal consultaSaldoAtual() {
        BigDecimal saldoAtual = BigDecimal.ZERO;

        for (Receita r : getTitular().getReceitas()) {
            if (isDataMenorOuIgual(r.getDataLancamento())) {
                saldoAtual = saldoAtual.add(r.getValor());
            }
        }

        for (Despesa d : getTitular().getDespesas()) {
            if (isDataMenorOuIgual(d.getDataLancamento())) {
                saldoAtual = saldoAtual.subtract(d.getValor());
            }
        }
        return saldoAtual;
    }

    /**
     * Método que retorna o valor do saldo independente do período
     * @return alor do saldo independente do período em BigDecimal
     */
    public BigDecimal consultaSaldoIndependentePeriodo() {
        BigDecimal saldoAtual = BigDecimal.ZERO;

        for (Receita r : getTitular().getReceitas()) {
            saldoAtual = saldoAtual.add(r.getValor());
        }

        for (Despesa d : getTitular().getDespesas()) {
            saldoAtual = saldoAtual.subtract(d.getValor());
        }
        return saldoAtual;
    }

    /**
     * Método que retorna o valor das receitas até o dia da consulta
     * @return valor das receitas até o dia atual em BigDecimal 
     */
    public BigDecimal consultarValorReceitasAtual() {

        BigDecimal valorReceitas = BigDecimal.ZERO;
        for (Receita r : getTitular().getReceitas()) {
            if (isDataMenorOuIgual(r.getDataLancamento())) {
                valorReceitas = valorReceitas.add(r.getValor());
            }
        }
        return valorReceitas;
    }

    /**
     * Método que retorna o valor das despesas até o dia da consulta
     * @return valor das despesas até o dia atual em BigDecimal 
     */
    public BigDecimal consultarValorDespesasAtual() {
        BigDecimal valorDespesas = BigDecimal.ZERO;
        for (Despesa d : getTitular().getDespesas()) {
            if (isDataMenorOuIgual(d.getDataLancamento())) {
                valorDespesas = valorDespesas.subtract(d.getValor());
            }
        }
        return valorDespesas;
    }

    /**
     * Método que retorna o valor das receitas futuras
     * @return valor das receitas independente do período em BigDecimal 
     */
    public BigDecimal consultarValorReceitasFuturo() {
        BigDecimal valorReceitas = BigDecimal.ZERO;
        for (Receita r : getTitular().getReceitas()) {
            if (r.getDataLancamento().isAfter(LocalDate.now())) {
                valorReceitas = valorReceitas.add(r.getValor());
            }
        }
        return valorReceitas;
    }

    /**
     * Método que retorna o valor das despesas futuras
     * @return valor das despesas independente do período em BigDecimal 
     */
    public BigDecimal consultarValorDespesasFuturo() {

        BigDecimal valorDespesas = BigDecimal.ZERO;
        for (Despesa r : getTitular().getDespesas()) {
            if (r.getDataLancamento().isAfter(LocalDate.now())) {
                valorDespesas = valorDespesas.subtract(r.getValor());
            }
        }
        return valorDespesas;
    }
    
    /**
     * Método que retorna o valor das receitas do mês corrente
     * @return valor das receitas do mês corrente
     */
    public BigDecimal consultarValorReceitasMensal() {
        BigDecimal valorReceitas = BigDecimal.ZERO;
        for (Receita r : getTitular().getReceitas()) {
            if (r.getDataLancamento().getMonth() == (LocalDate.now().getMonth())) {
                valorReceitas = valorReceitas.add(r.getValor());
            }
        }
        return valorReceitas;
    }
    
    /**
     * Método que retorna o valor das despesas do mês corrente
     * @return valor das despesas do mês corrente
     */
    public BigDecimal consultarValorDespesasMensal() {
        BigDecimal valorDespesas = BigDecimal.ZERO;
        for (Despesa r : getTitular().getDespesas()) {
            if (r.getDataLancamento().getMonth() == (LocalDate.now().getMonth())) {
                valorDespesas = valorDespesas.subtract(r.getValor());
            }
        }
        return valorDespesas;
    }
    
    /**
     * Método que confere se a data providenciada é igual ou anterior ao dia da consulta
     * @param data do tipo LocalDate
     * @return true ou false
     */
    public boolean isDataMenorOuIgual(LocalDate data) {
        return data.isBefore(LocalDate.now()) || data.isEqual(LocalDate.now());

    }
}
