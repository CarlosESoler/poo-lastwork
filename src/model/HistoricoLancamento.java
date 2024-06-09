/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashMap;
import model.lancamentos.Lancamento;

/**
 *
 * @author csoler
 */
public class HistoricoLancamento {
    
    HashMap<Integer, Lancamento> historico = new HashMap<>();
    
    public void addHistorico(Lancamento lancamento) {
        historico.put((int) (Math.random() * 100), lancamento);
    }
    
    public void removeHistorico(int id) throws IllegalAccessException {
        if(!historico.containsKey(id)) throw new IllegalAccessException("Nenhum historico foi encontrado");
        
        Lancamento l = historico.get(id);
    }
    
}
