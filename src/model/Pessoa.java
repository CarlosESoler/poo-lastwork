/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.lancamentos.Despesa;
import model.lancamentos.Lancamento;
import model.lancamentos.Receita;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

/**
 *
 * @author csoler
 */
public class Pessoa implements Serializable {
    @Serial
    private static final long serialVersionUID = 2L;
    private String nome;
    private List<Receita> receitas = new ArrayList<>();
    private List<Despesa> despesa = new ArrayList<>();
    private ContaBancaria conta;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if(nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio");
        }
        this.nome = nome;
    }

    public List<Receita> getReceitas() {
        return receitas;
    }

    public void setReceitas(List<Receita> receitas) {
        this.receitas = receitas;
    }

    public List<Despesa> getDespesa() {
        return despesa;
    }

    public void setDespesa(List<Despesa> despesa) {
        this.despesa = despesa;
    }

    public ContaBancaria getConta() {
        return conta;
    }

    public void setConta(ContaBancaria conta) {
        if(conta == null) {
            throw new IllegalArgumentException("Conta não pode ser nula");
        }
        this.conta = conta;
    }

    public void adicionarReceita(Receita receita) {
        if(receita == null) {
            throw new IllegalArgumentException("Receita não pode ser nula");
        }
        receitas.add(receita);
        conta.addHistoricoDeLancamentos(receita);
    }

    public void adicionarDespesa(Despesa despesa) {
        if(despesa == null) {
            throw new IllegalArgumentException("Despesa não pode ser nula");
        }
        this.despesa.add(despesa);
        conta.addHistoricoDeLancamentos(despesa);
    }

    public List<Lancamento> listaLancamentosPorData() {
        List<Despesa> sortedDespesas = new ArrayList<>(despesa);
        List<Receita> sortedReceitas = new ArrayList<>(receitas);

        sortedDespesas.sort(Comparator.comparing(Lancamento::getDataLancamento));
        sortedReceitas.sort(Comparator.comparing(Lancamento::getDataLancamento));

        List<Lancamento> sortedLancamentos = new ArrayList<>();
        sortedLancamentos.addAll(sortedDespesas);
        sortedLancamentos.addAll(sortedReceitas);

        return sortedLancamentos;
    }
}