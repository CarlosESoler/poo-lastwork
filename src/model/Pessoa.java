/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.lancamentos.Despesa;
import model.lancamentos.Lancamento;
import model.lancamentos.Receita;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

/**
 *
 * @author csoler
 */
public class Pessoa implements Serializable {
    private static final long serialVersionUID = 2L;
    private String nome;
    private final List<Receita> receitas = new ArrayList<>();
    private final List<Despesa> despesas = new ArrayList<>();
    private ContaBancaria conta;

    public Pessoa(String nome) throws IllegalArgumentException{
        setNome(nome);
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if(nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser nulo");
        }
        this.nome = nome;
    }

    public List<Receita> getReceitas() {
        return receitas;
    }

    public List<Despesa> getDespesas() {
        return despesas;
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

    public void adicionarReceita(Receita receita){
        if(receita == null) {
            throw new IllegalArgumentException("Receita não pode ser nula");
        }
        receitas.add(receita);
//        conta.addHistoricoDeLancamentos(receita);
    }

    public void adicionarDespesa(Despesa despesa) {
        if(despesa == null) {
            throw new IllegalArgumentException("Despesa não pode ser nula");
        }
        this.despesas.add(despesa);
//        conta.addHistoricoDeLancamentos(despesa);
    }
    
    public void removerTodasReceitas(){
        this.receitas.clear();
    }
    
    public void removerTodasDespesa(){
        this.despesas.clear();
    }
    
    public List<Despesa> exibirDemonstrativoDespesasAtuais (){
        List<Despesa> sortedDespesas = new ArrayList<>();
        for (Despesa r : getDespesas()) {
            if (r.getDataLancamento().isBefore(LocalDate.now())
                    || r.getDataLancamento().isEqual(LocalDate.now())) {
                sortedDespesas.add(r);
            }
        }
        sortedDespesas.sort(Comparator.comparing(Lancamento::getDataLancamento));

        return sortedDespesas;
    }
    
    public List<Receita> exibirDemonstrativoReceitasAtuais() {
        List<Receita> sortedReceita = new ArrayList<>();
            for (Receita r : getReceitas()) {
                if (r.getDataLancamento().isBefore(LocalDate.now()) || r.getDataLancamento().isEqual(LocalDate.now())) {
                    sortedReceita.add(r);
                }
            }
            sortedReceita.sort(Comparator.comparing(Lancamento::getDataLancamento));
        return sortedReceita;
    }
    
    public List<Receita> exibirDemonstrativoReceitasFuturas() {
        List<Receita> sortedReceita = new ArrayList<>();
            for (Receita r : getReceitas()) {
                if (r.getDataLancamento().isAfter(LocalDate.now())) {
                    sortedReceita.add(r);
                }
            }
            sortedReceita.sort(Comparator.comparing(Lancamento::getDataLancamento));
        return sortedReceita;
    }
    
    public List<Despesa> exibirDemonstrativoDespesasFuturas() {
        List<Despesa> sortedDespesa = new ArrayList<>();
            for (Despesa r : getDespesas()) {
                if (r.getDataLancamento().isAfter(LocalDate.now())) {
                    sortedDespesa.add(r);
                }
            }
            sortedDespesa.sort(Comparator.comparing(Lancamento::getDataLancamento));
        return sortedDespesa;
    }

    public void removerReceita(Receita receita) {
        if(receita == null) {
            throw new IllegalArgumentException("Receita não pode ser nula");
        }
        receitas.remove(receita);
    }

    public void removerDespesa(Despesa despesa) {
        if(despesa == null) {
            throw new IllegalArgumentException("Despesa não pode ser nula");
        }
        despesas.remove(despesa);
    }
    
    public List<Lancamento> listaLancamentosPorData() {
        List<Despesa> sortedDespesas = new ArrayList<>(despesas);
        List<Receita> sortedReceitas = new ArrayList<>(receitas);

        sortedDespesas.sort(Comparator.comparing(Lancamento::getDataLancamento));
        sortedReceitas.sort(Comparator.comparing(Lancamento::getDataLancamento));

        List<Lancamento> sortedLancamentos = new ArrayList<>();
        sortedLancamentos.addAll(sortedDespesas);
        sortedLancamentos.addAll(sortedReceitas);

        return sortedLancamentos;
    }
}
