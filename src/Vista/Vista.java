package Vista;

import Analisis.AnalisisLexico;
import Analisis.AnalisisSintactico;
import Modelo.Token;
import Semantico.Analizador_Sem;
import Semantico.PintaCelda;
import Semantico.TokendeTabla;
import Semantico.Variable;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Vista extends javax.swing.JFrame {

    ArrayList<Token> tmp;
    ArrayList<TokendeTabla> tdt, r;
    public boolean isCorrect;

    public Vista() {
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        spEditor = new javax.swing.JScrollPane();
        taEditor = new javax.swing.JTextArea();
        jtbPaneles = new javax.swing.JTabbedPane();
        pnlConsola = new javax.swing.JScrollPane();
        taConsola = new javax.swing.JTextArea();
        pnlLexico = new javax.swing.JScrollPane();
        tbLexico = new javax.swing.JTable();
        pnlSintactico = new javax.swing.JScrollPane();
        tbSintactico = new javax.swing.JTable();
        pnlSemantico = new javax.swing.JScrollPane();
        tbSemantico = new javax.swing.JTable();
        pnlIntermedio = new javax.swing.JScrollPane();
        taIntermedio = new javax.swing.JTextArea();
        pnlOptimizar = new javax.swing.JScrollPane();
        taOptimizar = new javax.swing.JTextArea();
        pnlEnsamblador = new javax.swing.JScrollPane();
        taEnsamblador = new javax.swing.JTextArea();
        lbEditor = new javax.swing.JLabel();
        barraMenu = new javax.swing.JMenuBar();
        mnuArchivo = new javax.swing.JMenu();
        itmAbrir = new javax.swing.JMenuItem();
        itmGuardarCodigo = new javax.swing.JMenuItem();
        itmGuardarAnalisis = new javax.swing.JMenuItem();
        itmSalir = new javax.swing.JMenuItem();
        itmAbrirTest = new javax.swing.JMenuItem();
        mnuAnalisis = new javax.swing.JMenu();
        itmLexico = new javax.swing.JMenuItem();
        itmSintactico = new javax.swing.JMenuItem();
        itmSemantico = new javax.swing.JMenuItem();
        itmIntermedio = new javax.swing.JMenuItem();
        itmOptimizar = new javax.swing.JMenuItem();
        itmEnsamblador = new javax.swing.JMenuItem();
        mnuAyuda = new javax.swing.JMenu();
        itmAyuda = new javax.swing.JMenuItem();
        itmAcerca = new javax.swing.JMenuItem();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        jMenuItem6.setText("jMenuItem6");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(Strings.name);
        setResizable(false);

        taEditor.setColumns(20);
        taEditor.setRows(5);
        spEditor.setViewportView(taEditor);

        taConsola.setColumns(20);
        taConsola.setRows(5);
        pnlConsola.setViewportView(taConsola);

        jtbPaneles.addTab("Consola", pnlConsola);

        tbLexico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null}
            },
            new String [] {
                "Lexema", "Etiqueta"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tbLexico.setEnabled(false);
        pnlLexico.setViewportView(tbLexico);

        jtbPaneles.addTab("Léxico", pnlLexico);

        tbSintactico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null}
            },
            new String [] {
                "Entrada", "Transicion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tbSintactico.setEnabled(false);
        pnlSintactico.setViewportView(tbSintactico);

        jtbPaneles.addTab("Sintáctico", pnlSintactico);

        tbSemantico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "1", "2", "3", "4"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tbSemantico.setEnabled(false);
        pnlSemantico.setViewportView(tbSemantico);

        jtbPaneles.addTab("Semántico", pnlSemantico);

        taIntermedio.setColumns(20);
        taIntermedio.setRows(5);
        pnlIntermedio.setViewportView(taIntermedio);

        jtbPaneles.addTab("Intermedio", pnlIntermedio);

        taOptimizar.setColumns(20);
        taOptimizar.setRows(5);
        pnlOptimizar.setViewportView(taOptimizar);

        jtbPaneles.addTab("Optimizar", pnlOptimizar);

        taEnsamblador.setColumns(20);
        taEnsamblador.setRows(5);
        pnlEnsamblador.setViewportView(taEnsamblador);

        jtbPaneles.addTab("Ensamblador", pnlEnsamblador);

        lbEditor.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbEditor.setText("Editor");

        mnuArchivo.setText("Archivo");

        itmAbrir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        itmAbrir.setText("Abrir código");
        itmAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmAbrirActionPerformed(evt);
            }
        });
        mnuArchivo.add(itmAbrir);

        itmGuardarCodigo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        itmGuardarCodigo.setText("Guardar código");
        itmGuardarCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmGuardarCodigoActionPerformed(evt);
            }
        });
        mnuArchivo.add(itmGuardarCodigo);

        itmGuardarAnalisis.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        itmGuardarAnalisis.setText("Guardar análisis");
        itmGuardarAnalisis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmGuardarAnalisisActionPerformed(evt);
            }
        });
        mnuArchivo.add(itmGuardarAnalisis);

        itmSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        itmSalir.setText("Salir");
        itmSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmSalirActionPerformed(evt);
            }
        });
        mnuArchivo.add(itmSalir);

        itmAbrirTest.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        itmAbrirTest.setText("Abrir test");
        itmAbrirTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmAbrirTestActionPerformed(evt);
            }
        });
        mnuArchivo.add(itmAbrirTest);

        barraMenu.add(mnuArchivo);

        mnuAnalisis.setText("Análisis");

        itmLexico.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        itmLexico.setText("Lexico");
        itmLexico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmLexicoActionPerformed(evt);
            }
        });
        mnuAnalisis.add(itmLexico);

        itmSintactico.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        itmSintactico.setText("Sintáctico");
        itmSintactico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmSintacticoActionPerformed(evt);
            }
        });
        mnuAnalisis.add(itmSintactico);

        itmSemantico.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        itmSemantico.setText("Semántico");
        itmSemantico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmSemanticoActionPerformed(evt);
            }
        });
        mnuAnalisis.add(itmSemantico);

        itmIntermedio.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        itmIntermedio.setText("Intermedio");
        itmIntermedio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmIntermedioActionPerformed(evt);
            }
        });
        mnuAnalisis.add(itmIntermedio);

        itmOptimizar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        itmOptimizar.setText("Optimizar");
        itmOptimizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmOptimizarActionPerformed(evt);
            }
        });
        mnuAnalisis.add(itmOptimizar);

        itmEnsamblador.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F6, 0));
        itmEnsamblador.setText("Ensamblador");
        itmEnsamblador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmEnsambladorActionPerformed(evt);
            }
        });
        mnuAnalisis.add(itmEnsamblador);

        barraMenu.add(mnuAnalisis);

        mnuAyuda.setText("Ayuda");

        itmAyuda.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        itmAyuda.setText("Ayuda");
        itmAyuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmAyudaActionPerformed(evt);
            }
        });
        mnuAyuda.add(itmAyuda);

        itmAcerca.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        itmAcerca.setText("Acerca");
        itmAcerca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmAcercaActionPerformed(evt);
            }
        });
        mnuAyuda.add(itmAcerca);

        barraMenu.add(mnuAyuda);

        setJMenuBar(barraMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbEditor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(spEditor, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtbPaneles, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jtbPaneles, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(lbEditor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spEditor, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void itmLexicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmLexicoActionPerformed
        tmp = new AnalisisLexico().analizarLexico(taEditor.getText());
        DefaultTableModel dtm;
        String[] column = {"Lexema", "Etiqueta"};
        String[][] dtm_content = new String[tmp.size()][2];
        for (int i = 0; i < tmp.size(); i++) {
            dtm_content[i][0] = tmp.get(i).getLexema();
            dtm_content[i][1] = tmp.get(i).getNumero();
        }
        dtm = new DefaultTableModel(dtm_content, column);
        tbLexico.setModel(dtm);

        jtbPaneles.setSelectedIndex(1);
    }//GEN-LAST:event_itmLexicoActionPerformed

    private void itmSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmSalirActionPerformed
        System.exit(EXIT_ON_CLOSE);
    }//GEN-LAST:event_itmSalirActionPerformed

    private void itmGuardarCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmGuardarCodigoActionPerformed
        BufferedWriter escribe;
        try {
            JFileChooser file = new JFileChooser(System.getProperty("user.dir"));
            file.showSaveDialog(this);
            File archivo = file.getSelectedFile();
            if (archivo != null) {
                escribe = new BufferedWriter(new FileWriter(archivo + ".txt"));
                String[] data = taEditor.getText().split("\n");
                for (String data1 : data) {
                    escribe.write(data1);
                    escribe.newLine();
                }
                escribe.close();
            } else {
                JOptionPane.showMessageDialog(this, "El archivo no se guardo.");
            }
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }//GEN-LAST:event_itmGuardarCodigoActionPerformed

    private void itmGuardarAnalisisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmGuardarAnalisisActionPerformed
        BufferedWriter escribe;
        try {
            JFileChooser file = new JFileChooser(System.getProperty("user.dir"));
            file.showSaveDialog(this);
            File archivo = file.getSelectedFile();
            if (archivo != null) {
                escribe = new BufferedWriter(new FileWriter(archivo + ".txt"));
                for (int i = 0; i < tmp.size(); i++) {
                    escribe.write(tmp.get(i).getNumero() + "\t" + tmp.get(i).getLexema());
                    escribe.newLine();
                }
                escribe.close();
            } else {
                JOptionPane.showMessageDialog(this, "El archivo no se guardo.");
            }
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }//GEN-LAST:event_itmGuardarAnalisisActionPerformed

    private void itmAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmAbrirActionPerformed
        String text = "";
        JFileChooser file = new JFileChooser(System.getProperty("user.dir"));
        file.showOpenDialog(this);
        File archivo = file.getSelectedFile();
        if (archivo != null) {
            FileReader Fichero = null;
            try {
                taEditor.setText("");
                Fichero = new FileReader(archivo);
                BufferedReader read = new BufferedReader(Fichero);
                while ((text = read.readLine()) != null) {
                    taEditor.append(text + "\n");
                }
                read.close();
            } catch (IOException ioe) {
                System.out.println(ioe);
            }
        }
    }//GEN-LAST:event_itmAbrirActionPerformed

    private void itmAyudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmAyudaActionPerformed
        new Ayuda().setVisible(true);
    }//GEN-LAST:event_itmAyudaActionPerformed

    private void itmSintacticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmSintacticoActionPerformed
        itmLexicoActionPerformed(evt);//analizamos el lexico

        try {
            //Sintáctico
            ArrayList<Token> sin;
            sin = new AnalisisSintactico().analizarSintactica(tmp, taConsola, this);
            DefaultTableModel dtm;
            String[] column = {"Transicion", "Entrada"};
            String[][] dtm_content = new String[sin.size()][2];
            for (int i = 0; i < sin.size(); i++) {
                dtm_content[i][0] = sin.get(i).getNumero();
                dtm_content[i][1] = sin.get(i).getLexema();
            }
            dtm = new DefaultTableModel(dtm_content, column);
            tbSintactico.setModel(dtm);

            //Semántico
            ArrayList<Variable> sem = AnalisisSintactico.variables;
            DefaultTableModel dtm2;
            String[] column2 = {"Lexema", "TipoDato", "Valor", "Declarada", "Utilizada", "Duplicada"};
            String[][] dtm_content2 = new String[sin.size()][6];
            for (int i = 0; i < sem.size(); i++) {
                dtm_content2[i][0] = sem.get(i).getVariable();
                dtm_content2[i][1] = sem.get(i).getTipoDato();
                dtm_content2[i][2] = sem.get(i).getValor();
                dtm_content2[i][3] = String.valueOf(sem.get(i).isDeclarada());
                dtm_content2[i][4] = String.valueOf(sem.get(i).isUtilizada());
                dtm_content2[i][5] = String.valueOf(sem.get(i).isDuplicada());
            }
            dtm2 = new DefaultTableModel(dtm_content2, column2);
            tbSemantico.setModel(dtm2);
            tbSemantico.setDefaultRenderer(Object.class, new PintaCelda());

            if (isCorrect) {
                JOptionPane.showMessageDialog(this, "El fragmento de codigo es correcto.");
            } else {
                JOptionPane.showMessageDialog(this, "El fragmento de codigo es incorrecto.");
            }

            jtbPaneles.setSelectedIndex(2);
        } catch (IOException e) {

        }
    }//GEN-LAST:event_itmSintacticoActionPerformed

    private void itmAcercaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmAcercaActionPerformed
        new Acerca().setVisible(true);
    }//GEN-LAST:event_itmAcercaActionPerformed

    private void itmSemanticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmSemanticoActionPerformed
        itmSintacticoActionPerformed(evt);
        jtbPaneles.setSelectedIndex(3);

//        try {
//            tdt = new Analizador_Sem().analizarSemantico(taEditor.getText());
//        } catch (InterruptedException ex) {
//            Logger.getLogger(Vista.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        DefaultTableModel dtms;
//        String[] column = {"LEXEMA", "TIPO", "DECLARADA", "UTILIZADA", "DUPLICADA"};//quitar inicializado
//        String[][] dtms_content = new String[tdt.size()][5];
//        for (int i = 0; i < tdt.size(); i++) {
//            dtms_content[i][0] = tdt.get(i).getNombre();
//            dtms_content[i][1] = tdt.get(i).getToken();
//            dtms_content[i][2] = String.valueOf(tdt.get(i).isDeclarada());
//            dtms_content[i][3] = String.valueOf(tdt.get(i).isUtilizada());
//            dtms_content[i][4] = String.valueOf(tdt.get(i).isDuplicada());
//        }
//        dtms = new DefaultTableModel(dtms_content, column);
//        tbSemantico.setModel(dtms);
//        tbSemantico.setDefaultRenderer(Object.class, new PintaCelda());
        
//        for (int i = 0; i < tdt.size(); i++) {
//            if (tdt.get(i).isUtilizada() == false) {
//                JOptionPane.showMessageDialog(null, "LA VARIABLE: { " + tdt.get(i).getNombre() + " } NO SE UTILIZA", "ERROR ", JOptionPane.ERROR_MESSAGE);
//            }
//        }
    }//GEN-LAST:event_itmSemanticoActionPerformed

    private void itmIntermedioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmIntermedioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itmIntermedioActionPerformed

    private void itmEnsambladorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmEnsambladorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itmEnsambladorActionPerformed

    private void itmOptimizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmOptimizarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itmOptimizarActionPerformed

    private void itmAbrirTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmAbrirTestActionPerformed
        String text = "";
        File archivo = new File("1.txt");
        if (archivo != null) {
            FileReader Fichero = null;
            try {
                taEditor.setText("");
                Fichero = new FileReader(archivo);
                BufferedReader read = new BufferedReader(Fichero);
                while ((text = read.readLine()) != null) {
                    taEditor.append(text + "\n");
                }
                read.close();
            } catch (IOException ioe) {
                System.out.println(ioe);
            }
        }
        
        itmSemanticoActionPerformed(evt);
    }//GEN-LAST:event_itmAbrirTestActionPerformed

    public static void main(String args[]) {
        new Vista().setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar barraMenu;
    private javax.swing.JMenuItem itmAbrir;
    private javax.swing.JMenuItem itmAbrirTest;
    private javax.swing.JMenuItem itmAcerca;
    private javax.swing.JMenuItem itmAyuda;
    private javax.swing.JMenuItem itmEnsamblador;
    private javax.swing.JMenuItem itmGuardarAnalisis;
    private javax.swing.JMenuItem itmGuardarCodigo;
    private javax.swing.JMenuItem itmIntermedio;
    private javax.swing.JMenuItem itmLexico;
    private javax.swing.JMenuItem itmOptimizar;
    private javax.swing.JMenuItem itmSalir;
    private javax.swing.JMenuItem itmSemantico;
    private javax.swing.JMenuItem itmSintactico;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JTabbedPane jtbPaneles;
    private javax.swing.JLabel lbEditor;
    private javax.swing.JMenu mnuAnalisis;
    private javax.swing.JMenu mnuArchivo;
    private javax.swing.JMenu mnuAyuda;
    private javax.swing.JScrollPane pnlConsola;
    private javax.swing.JScrollPane pnlEnsamblador;
    private javax.swing.JScrollPane pnlIntermedio;
    private javax.swing.JScrollPane pnlLexico;
    private javax.swing.JScrollPane pnlOptimizar;
    private javax.swing.JScrollPane pnlSemantico;
    private javax.swing.JScrollPane pnlSintactico;
    private javax.swing.JScrollPane spEditor;
    private javax.swing.JTextArea taConsola;
    private javax.swing.JTextArea taEditor;
    private javax.swing.JTextArea taEnsamblador;
    private javax.swing.JTextArea taIntermedio;
    public javax.swing.JTextArea taOptimizar;
    private javax.swing.JTable tbLexico;
    private javax.swing.JTable tbSemantico;
    private javax.swing.JTable tbSintactico;
    // End of variables declaration//GEN-END:variables
}
