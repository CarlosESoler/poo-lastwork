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
    private final HashMap<Integer, Receita> receitas = new HashMap<>();
    private final HashMap<Integer, Despesa> despesas = new HashMap<>();
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
        return receitas.values().stream().toList();
    }

    public List<Despesa> getDespesas() {
        return despesas.values().stream().toList();
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

    public void adicionarReceita(Integer id, Receita receita){
        if(receita == null) {
            throw new IllegalArgumentException("Receita não pode ser nula");
        }
        new HistoricoLancamento(receita, getConta().getSaldo());
        receitas.put(id, receita);
    }

    public void adicionarDespesa(Integer id, Despesa despesa) {
        if(despesa == null) {
            throw new IllegalArgumentException("Despesa não pode ser nula");
        }
        new HistoricoLancamento(despesa, getConta().consultaSaldoAtual());
        this.despesas.put(id, despesa);
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

    public void removerReceita(Integer id) {
        if(id == null) {
            throw new IllegalArgumentException("O ID não pode ser nulo");
        }
        if(!receitas.containsKey(id)) {
            throw new IllegalArgumentException("Receita não encontrada");
        }
        receitas.remove(id);
    }

    public void removerDespesa(Integer id) {
        if(id == null) {
            throw new IllegalArgumentException("Despesa não pode ser nula");
        }
        if(!despesas.containsKey(id)) {
            throw new IllegalArgumentException("Receita não encontrada");
        }
        despesas.remove(id);
    }
}
