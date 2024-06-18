/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.lancamentos.Despesa;
import model.lancamentos.Lancamento;
import model.lancamentos.Receita;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author csoler
 */
public class ContaBancaria {

    private String numero;
    private BigDecimal saldo = BigDecimal.ZERO;
    private Pessoa titular;

    //Construtor: inicializa settando o número da conta bancária
    public ContaBancaria(String numero) {
        
        if (numero.isEmpty() || numero.isBlank()) {
            throw new IllegalArgumentException("Número da conta não pode ser nulo ou vazio");
        }
        
        if (numero.length() >= 10) {
            throw new IllegalArgumentException("Número da conta não pode ter mais que 10 dígitos");
        }
        
        this.numero = numero;
    }

    //Setter: define o número da conta bancária
    public void setNumero(String numero) {
        if (numero.isBlank()) {
            throw new IllegalArgumentException("Número da conta deve ser preenchido!");
        }
        
        int resposta = JOptionPane.showConfirmDialog(null, "Você tem certeza que deseja alterar o número da conta?", "Confirmação", JOptionPane.OK_CANCEL_OPTION);

        if (resposta == JOptionPane.OK_OPTION) {
            this.numero = numero;
        } 
    }

    //Capta o número da conta bancária
    public String getNumero() {
        return numero;
    }

    //Getter: Capta o titular da conta
    public Pessoa getTitular() {
        return titular;
    }

    //Setter: define o titular da conta
    public void setTitular(Pessoa titular) {
        if (titular == null) {
            throw new IllegalArgumentException("Titular da conta não pode ser nulo");
        }
        this.titular = titular;
    }

    //Adiciona saldo à conta
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

    //Subtrai saldo da conta
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

    public BigDecimal getSaldo() {
        return saldo;
    }

    //Mostra o valor do saldo até o momento
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

    //Mostra o saldo geral (atual + futuro)
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

    //Mostra o valor das receitas até o momento
    public BigDecimal consultarValorReceitasAtual() {

        BigDecimal valorReceitas = BigDecimal.ZERO;
        for (Receita r : getTitular().getReceitas()) {
            if (isDataMenorOuIgual(r.getDataLancamento())) {
                valorReceitas = valorReceitas.add(r.getValor());
            }
        }
        return valorReceitas;
    }

    //Mostra o valor das despesas até o momento
    public BigDecimal consultarValorDespesasAtual() {
        BigDecimal valorDespesas = BigDecimal.ZERO;
        for (Despesa d : getTitular().getDespesas()) {
            if (isDataMenorOuIgual(d.getDataLancamento())) {
                valorDespesas = valorDespesas.subtract(d.getValor());
            }
        }
        return valorDespesas;
    }

    //Mostra o valor das receitas futuras
    public BigDecimal consultarValorReceitasFuturo() {
        BigDecimal valorReceitas = BigDecimal.ZERO;
        for (Receita r : getTitular().getReceitas()) {
            if (r.getDataLancamento().isAfter(LocalDate.now())) {
                valorReceitas = valorReceitas.add(r.getValor());
            }
        }
        return valorReceitas;
    }

    //Mostra o valor das despesas futuras
    public BigDecimal consultarValorDespesasFuturo() {

        BigDecimal valorDespesas = BigDecimal.ZERO;
        for (Despesa r : getTitular().getDespesas()) {
            if (r.getDataLancamento().isAfter(LocalDate.now())) {
                valorDespesas = valorDespesas.subtract(r.getValor());
            }
        }
        return valorDespesas;
    }

    public BigDecimal consultarValorReceitasMensal() {
        BigDecimal valorReceitas = BigDecimal.ZERO;
        for (Receita r : getTitular().getReceitas()) {
            if (r.getDataLancamento().getMonth() == (LocalDate.now().getMonth())) {
                valorReceitas = valorReceitas.add(r.getValor());
            }
        }
        return valorReceitas;
    }

    public BigDecimal consultarValorDespesasMensal() {
        BigDecimal valorDespesas = BigDecimal.ZERO;
        for (Despesa r : getTitular().getDespesas()) {
            if (r.getDataLancamento().getMonth() == (LocalDate.now().getMonth())) {
                valorDespesas = valorDespesas.subtract(r.getValor());
            }
        }
        return valorDespesas;
    }

    public boolean isDataMenorOuIgual(LocalDate data) {
        return data.isBefore(LocalDate.now()) || data.isEqual(LocalDate.now());

    }
}
