/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static model.interfaces.TipoDespesa.ALIMENTACAO;
import static model.interfaces.TipoDespesa.TRANSPORTE;
import model.interfaces.TipoReceita;
import static model.interfaces.TipoReceita.FERIAS;
import static model.interfaces.TipoReceita.SALARIO;
import model.lancamentos.Despesa;
import model.lancamentos.Receita;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author richa
 */
public class ExtratoTest {
    
    public ExtratoTest() {
    }

    /**
     *
     */
    @Test
    public void test01_apagarArquivo() {
        
        Pessoa fulano = new Pessoa("Fulano");
        Extrato extrato = new Extrato(fulano);
        
        extrato.salvarDados();
        extrato.apagarArquivo();

        assertNull(extrato.getArquivo());

    }
    
    
    
}
