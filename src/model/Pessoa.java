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

/**
 * Classe: essa classe registra o nome, a conta bancária, as despesas e as
 * receitas de uma pessoa
 */
public class Pessoa {

    private String nome;
    private final HashMap<Integer, Receita> receitas;
    private final HashMap<Integer, Despesa> despesas;
    private final LinkedHashMap<Integer, HistoricoLancamento> historico;
    private ContaBancaria conta;

    /**
     * Construtor: inicializa a classe registro o nome da pessoa
     *
     * @param nome do tipo String
     * @throws IllegalArgumentException caso o nome o esteja em branco, ou
     * possua mais de 15 dígitos
     */
    public Pessoa(String nome) throws IllegalArgumentException {
        historico = new LinkedHashMap<>();
        despesas = new HashMap<>();
        receitas = new HashMap<>();

        setNome(nome);
    }

    /**
     * Getter: capta o nome da pessoa
     *
     * @return nome do tipo String
     */
    public String getNome() {
        return nome;
    }

    /**
     * Setter: registra o nome da pessoa
     *
     * @param nome do tipo String
     */
    public void setNome(String nome) {
        if (nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser nulo");
        }
        if (nome.length() >= 15) {
            throw new IllegalArgumentException("Nome não pode ter mais que 15 dígitos");
        }

        this.nome = nome;
    }

    /**
     * Getter: capta as receitas lançadas e e seus respectivos IDs
     *
     * @return HashMap de receitas
     */
    public HashMap<Integer, Receita> getReceitasMap() {
        return receitas;
    }

    /**
     * Getter: capta as despesas lançadas e e seus respectivos IDs
     *
     * @return HashMap de despesas
     */
    public HashMap<Integer, Despesa> getDespesasMap() {
        return despesas;
    }

    /**
     * Getter: capta somente as receitas lançadas
     *
     * @return List de receitas
     */
    public List<Receita> getReceitas() {
        return receitas.values().stream().toList();
    }

    /**
     * Getter: capta somente as despesas lançadas
     *
     * @return List de despesas
     */
    public List<Despesa> getDespesas() {
        return despesas.values().stream().toList();
    }

    /**
     * Getter: capta todo o histórico de lançamentos de realizados
     *
     * @return LinkedHashMap de histórico
     */
    public LinkedHashMap<Integer, HistoricoLancamento> getHistorico() {
        return historico;
    }

    /**
     * Método que adiciona os registros dos lançamentos em um HashMap de
     * histórico
     *
     * @param id do tipo Integer
     * @param historico do tipo HistoricoLancamento
     */
    public void adicionarHistoricoLancamento(Integer id, HistoricoLancamento historico) {
        this.historico.put(id, historico);
    }

    /**
     * Método que apaga o histórico
     */
    public void apagarHistorico() {
        this.historico.clear();
    }

    /**
     * Getter: Método que retorna a conta bancária
     *
     * @return conta do tipo ContaBancaria
     */
    public ContaBancaria getConta() {
        return conta;
    }

    /**
     * Setter: Método que registra a conta bancária
     *
     * @param conta do tipo ContaBancaria
     */
    public void setConta(ContaBancaria conta) {
        if (conta == null) {
            throw new IllegalArgumentException("Conta não pode ser nula");
        }
        this.conta = conta;
    }

    /**
     * Método que adiciona o lançamento de receita associado a pessoa
     *
     * @param id do tipo Integer
     * @param receita do tipo Receita
     */
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

    /**
     * Método que adiciona o lançamento de despesa associado a pessoa
     *
     * @param id do tipo Integer
     * @param receita do tipo Despesa
     */
    public void adicionarDespesa(Integer id, Despesa despesa) {

        if (despesa == null) {
            throw new IllegalArgumentException("Despesa não pode ser nula");
        }

        if (id == null) {
            throw new IllegalArgumentException("O ID da despesa não pode ser nulo");
        }

        if (despesas.containsKey(id)) {
            throw new IllegalArgumentException("Já existe uma despesa com o mesmo ID");
        }

        if (receitas.containsKey(id)) {
            throw new IllegalArgumentException("Já existe uma receita com o mesmo ID");
        }

        this.despesas.put(id, despesa);
    }

    /**
     * Método que remove o lançamento de receita associado a pessoa a partir do
     * ID da receita
     *
     * @param id do tipo Integer
     * @param receita do tipo Receita
     */
    public void removerReceita(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("O ID não pode ser nulo");
        }
        if (!receitas.containsKey(id)) {
            throw new IllegalArgumentException("Receita não encontrada");
        }
        receitas.remove(id);
    }

    /**
     * Método que remove o lançamento de despesa associado a pessoa a partir do
     * ID da despesa
     *
     * @param id do tipo Integer
     * @param receita do tipo Despesa
     */
    public void removerDespesa(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("O ID não pode ser nulo");
        }
        if (!despesas.containsKey(id)) {
            throw new IllegalArgumentException("Despesa não encontrada");
        }
        despesas.remove(id);
    }

    /**
     * Método que remove todos os lançamentos de receita associado a pessoa
     */
    public void removerTodasReceitas() {
        this.receitas.clear();
    }

    /**
     * Método que remove todos os lançamentos de despesa associado a pessoa
     */
    public void removerTodasDespesa() {
        this.despesas.clear();
    }

    /**
     * Método que exibe todas as receitas até o dia atual ordenadas por ID
     *
     * @return HashMap sortedReceitas
     */
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

    /**
     * Método que exibe todas as receitas após o dia atual ordenadas por ID
     *
     * @return HashMap sortedReceitas
     */
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

    /**
     * Método que exibe todas as despesas até o dia atual ordenadas por ID
     *
     * @return HashMap sortedDespesas
     */
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

    /**
     * Método que exibe todas as despesas após o dia atual ordenadas por ID
     *
     * @return HashMap sortedDespesas
     */
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
}
