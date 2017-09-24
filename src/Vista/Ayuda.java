
package Vista;

public class Ayuda extends javax.swing.JFrame {

    public Ayuda() {
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ayuda");

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(242, 242, 249));
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("Análisis léxico.\n\nPara generar el análisis, debe introducir el código\nfuente en el área de texto.\n\nAnálisis sintáctico.\n\nPara generar el análisis, debe introducir el código\nfuente en el área de texto. Se generara\nautomaticamente el analisis léxico debido a que\neste es necesario para el análisis sintáctico.\n\nGuardar.\n\nEl sistema cuenta con dos opciones, una guardará un\narchivo con el código fuente y otra el análisis\nlexema-etiqueta.\n\nAbrir.\n\nPodrá abrir ambos archivos en el área de texto.\n\nExcepciones:\n- Separar por espacio los operadores relacionales \nde las variables o números a comparar.");
        jTextArea1.setEnabled(false);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
