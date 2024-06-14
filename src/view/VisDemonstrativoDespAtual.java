package view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import model.Pessoa;
import model.lancamentos.Despesa;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
/**
 *
 * @author Gamer
 */
public class VisDemonstrativoDespAtual extends javax.swing.JDialog {

    private Pessoa pessoaMostraDemAtual;

    /**
     * Creates new form VisDemonstrativoDespAtual
     */
    public VisDemonstrativoDespAtual(java.awt.Frame parent, boolean modal, Pessoa pessoa) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        pessoaMostraDemAtual = pessoa;
        exibirDemonstrativoDespAtual();
    }

    public void exibirDemonstrativoDespAtual() {
        StringBuilder conteudo = new StringBuilder();
        List<Integer> idsDespesas = new ArrayList<>(pessoaMostraDemAtual.getReceitasMap().keySet());

// Ordene as chaves
        Collections.sort(idsDespesas);

// Itere sobre as chaves ordenadas
        for (Integer id : idsDespesas) {
            Despesa rc = pessoaMostraDemAtual.getDespesasMap().get(id);
            conteudo.append("====================================\n");
            conteudo.append("ID: ").append(id).append("\n");
            conteudo.append("Data: ").append(rc.getDataLancamento()).append("\n");
            conteudo.append("Tipo: ").append(rc.getTipoDespesa()).append("\n");
            conteudo.append("Valor: ").append(rc.getValor()).append("\n");
        }


        tArea.setText(conteudo.toString());
        tArea.setEditable(false);
        tArea.setCaretPosition(0);
    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Despesas Até o Momento");

        jScrollPane1.setName("spReceitasAtuais"); // NOI18N

        tArea.setEditable(false);
        tArea.setColumns(20);
        tArea.setRows(5);
        jScrollPane1.setViewportView(tArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(182, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(5, 5, 5)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                    .addGap(5, 5, 5)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(339, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(37, 37, 37)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
                    .addGap(7, 7, 7)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VisDemonstrativoDespAtual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VisDemonstrativoDespAtual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VisDemonstrativoDespAtual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VisDemonstrativoDespAtual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Pessoa pessoa;

                try {
                    pessoa = new Pessoa(args[0]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.err.println("Erro: Argumentos de linha de comando insuficientes.");
                    return;
                }

                PessoaGUI pessoaGUI = new PessoaGUI(args[0], args[1]);
                pessoaGUI.setVisible(true);
                VisDemonstrativoDespAtual dialog = new VisDemonstrativoDespAtual(new javax.swing.JFrame(), true, pessoa);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea tArea;
    // End of variables declaration//GEN-END:variables
}
