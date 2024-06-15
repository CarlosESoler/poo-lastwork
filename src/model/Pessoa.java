/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.lancamentos.Despesa;
import model.lancamentos.Receita;
import java.time.LocalDate;
import java.util.*;
import javax.swing.JOptionPane;

/**
 *
 * @author csoler
 */
public class Pessoa {

    private String nome;
    private final HashMap<Integer, Receita> receitas;
    private final HashMap<Integer, Despesa> despesas;
    private final LinkedHashMap<Integer, HistoricoLancamento> historico;
    private ContaBancaria conta;

    public Pessoa(String nome) throws IllegalArgumentException {
        historico = new LinkedHashMap<>();
        despesas = new HashMap<>();
        receitas = new HashMap<>();

        if (nome.isBlank() || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser nulo");
        }
        
        if (nome.length() >= 15) {
            throw new IllegalArgumentException("Nome não pode ter mais que 15 dígitos");
        }
        
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser nulo");
        }
        int resposta = JOptionPane.showConfirmDialog(null, "Você tem certeza que deseja alterar o nome da conta?", "Confirmação", JOptionPane.OK_CANCEL_OPTION);

        if (resposta == JOptionPane.OK_OPTION) {
            this.nome = nome;
        }
    }

    public HashMap<Integer, Receita> getReceitasMap() {
        return receitas;
    }

    public HashMap<Integer, Despesa> getDespesasMap() {
        return despesas;
    }

    public List<Receita> getReceitas() {
        return receitas.values().stream().toList();
    }

    public List<Despesa> getDespesas() {
        return despesas.values().stream().toList();
    }

    public LinkedHashMap<Integer, HistoricoLancamento> getHistorico() {
        return historico;
    }

    public void adicionarHistoricoLancamento(Integer id, HistoricoLancamento historico) {
        this.historico.put(id, historico);
    }

    public ContaBancaria getConta() {
        return conta;
    }

    public void setConta(ContaBancaria conta) {
        if (conta == null) {
            throw new IllegalArgumentException("Conta não pode ser nula");
        }
        this.conta = conta;
    }

    public void adicionarReceita(Integer id, Receita receita) {

        if (receita == null) {
            throw new IllegalArgumentException("Receita não pode ser nula!");
        }

        if (id == null) {
            throw new IllegalArgumentException("O ID da receita deve ser preenchido!");
        }

        if (receitas.containsKey(id)) {
            throw new IllegalArgumentException("Já existe uma receita com o este ID!");
        }

        if (despesas.containsKey(id)) {
            throw new IllegalArgumentException("ID inserida já associada a uma despesa!");
        }

        receitas.put(id, receita);
    }

    public void adicionarDespesa(Integer id, Despesa despesa) {

        if (id == null) {
            throw new IllegalArgumentException("O ID da despesa não pode ser nulo");
        }
        if (despesa == null) {
            throw new IllegalArgumentException("Despesa não pode ser nula");
        }
        if (despesas.containsKey(id)) {
            throw new IllegalArgumentException("Já existe uma despesa com o mesmo ID");
        }

        if (receitas.containsKey(id)) {
            throw new IllegalArgumentException("ID inserida já associada a uma receita!");
        }

        this.despesas.put(id, despesa);
    }

    public void removerTodasReceitas() {
        this.receitas.clear();
    }

    public void removerTodasDespesa() {
        this.despesas.clear();
    }

    public void apagarHistorico() {
        this.historico.clear();
    }

    public Map<Integer, Receita> exibirDemonstrativoReceitasAtuais() {
        // Lista para armazenar os IDs ordenados
        List<Integer> sortedIds = new ArrayList<>();

        // Mapa para armazenar as receitas (supondo que receitas é o seu mapa original)
        Map<Integer, Receita> receitas = getReceitasMap();

        // Filtrar e ordenar os IDs
        for (Map.Entry<Integer, Receita> entry : receitas.entrySet()) {
            Integer id = entry.getKey();
            Receita r = entry.getValue();
            if (r.getDataLancamento().isBefore(LocalDate.now())
                    || r.getDataLancamento().isEqual(LocalDate.now())) {
                sortedIds.add(id);
            }
        }

        // Ordenar os IDs
        Collections.sort(sortedIds);

        // Construir o mapa ordenado com base nos IDs ordenados
        Map<Integer, Receita> sortedReceitas = new LinkedHashMap<>();
        for (Integer id : sortedIds) {
            sortedReceitas.put(id, receitas.get(id));
        }

        return sortedReceitas;
    }

    public Map<Integer, Receita> exibirDemonstrativoReceitasFuturas() {
        // Lista para armazenar os IDs ordenados
        List<Integer> sortedIds = new ArrayList<>();

        // Mapa para armazenar as receitas (supondo que receitas é o seu mapa original)
        Map<Integer, Receita> receitas = getReceitasMap();

        // Filtrar e ordenar os IDs
        for (Map.Entry<Integer, Receita> entry : receitas.entrySet()) {
            Integer id = entry.getKey();
            Receita r = entry.getValue();
            if (r.getDataLancamento().isAfter(LocalDate.now())) {
                sortedIds.add(id);
            }
        }

        // Ordenar os IDs
        Collections.sort(sortedIds);

        // Construir o mapa ordenado com base nos IDs ordenados
        Map<Integer, Receita> sortedReceitas = new LinkedHashMap<>();
        for (Integer id : sortedIds) {
            sortedReceitas.put(id, receitas.get(id));
        }

        return sortedReceitas;
    }

    public Map<Integer, Despesa> exibirDemonstrativoDespesasAtuais() {
        // Lista para armazenar os IDs ordenados
        List<Integer> sortedIds = new ArrayList<>();

        // Mapa para armazenar as despesas
        Map<Integer, Despesa> despesas = getDespesasMap(); // Suponha que despesas é o seu mapa original

        // Filtrar e ordenar os IDs
        for (Map.Entry<Integer, Despesa> entry : despesas.entrySet()) {
            Integer id = entry.getKey();
            Despesa d = entry.getValue();
            if (d.getDataLancamento().isBefore(LocalDate.now())
                    || d.getDataLancamento().isEqual(LocalDate.now())) {
                sortedIds.add(id);
            }
        }

        // Ordenar os IDs
        Collections.sort(sortedIds);

        // Construir o mapa ordenado com base nos IDs ordenados
        Map<Integer, Despesa> sortedDespesas = new LinkedHashMap<>();
        for (Integer id : sortedIds) {
            sortedDespesas.put(id, despesas.get(id));
        }

        return sortedDespesas;
    }

    public Map<Integer, Despesa> exibirDemonstrativoDespesasFuturas() {
        // Lista para armazenar os IDs ordenados
        List<Integer> sortedIds = new ArrayList<>();

        // Mapa para armazenar as despesas
        Map<Integer, Despesa> despesas = getDespesasMap(); // Suponha que despesas é o seu mapa original

        // Filtrar e ordenar os IDs
        for (Map.Entry<Integer, Despesa> entry : despesas.entrySet()) {
            Integer id = entry.getKey();
            Despesa d = entry.getValue();
            if (d.getDataLancamento().isAfter(LocalDate.now())) {
                sortedIds.add(id);
            }
        }

        // Ordenar os IDs
        Collections.sort(sortedIds);

        // Construir o mapa ordenado com base nos IDs ordenados
        Map<Integer, Despesa> sortedDespesas = new LinkedHashMap<>();
        for (Integer id : sortedIds) {
            sortedDespesas.put(id, despesas.get(id));
        }

        return sortedDespesas;
    }

    public void removerReceita(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("O ID não pode ser nulo");
        }
        if (!receitas.containsKey(id)) {
            throw new IllegalArgumentException("Receita não encontrada");
        }
        receitas.remove(id);
    }

    public void removerDespesa(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("O ID não pode ser nulo");
        }
        if (!despesas.containsKey(id)) {
            throw new IllegalArgumentException("Despesa não encontrada");
        }
        despesas.remove(id);
    }
}
