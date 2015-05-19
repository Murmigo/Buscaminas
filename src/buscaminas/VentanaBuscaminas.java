/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Guillermo Abarca
 */
public class VentanaBuscaminas extends javax.swing.JFrame {
    
//primero resuelvo la vista para que pinte una pantalla con botones
    int filas = 10;
    int columnas = 20;
    int numMinas = 25;
    int cuentaMinas = 0;
    boolean gameOver = false;
    
    Casilla[][] arrayBotones = new Casilla[filas][columnas];
    Random rand = new Random();
    
    private void botonPulsado(MouseEvent e)
    {
        Casilla b = (Casilla) e.getComponent();
        if(gameOver ==false){
            if(e.getButton() == MouseEvent.BUTTON1)
            {
                if(b.mina == 0 && b.numMinas != 0){
                    b.setText(""+b.numMinas);
                    b.enable(false);
                }
                if(b.mina == 0 && b.numMinas==0)
                   descubreMinas(b);

                if(b.mina == 1){
                    b.setBackground(Color.red);
                    mostrarMinas();
                    gameOver = true;
                }
            }else if(e.getButton() == MouseEvent.BUTTON3)
            {
                if(b.getText() == "")
                    b.setText("&");
                else if(b.getText() == "&")
                    b.setText("?");
                else if(b.getText() == "?")
                    b.setText("");
        }
        }
    }
    private void checkeaNumMinas(Casilla c)
    {
        if(arrayBotones[c.x][c.y].mina !=1){
            for(int i = c.x-1;i<=c.x+1; i++){
                for(int j = c.y-1; j <= c.y+1; j++){
                    if(i>=0 && i <filas && j >=0 && j<columnas){
                        if(arrayBotones[i][j].mina == 1){
                                c.numMinas++;
                        }
                    }
                }
            }
        }
    }
    private void colocarMinas()
    {
        while(cuentaMinas <= numMinas)
        {
            for(int i=0; i<filas; i++){
               for(int j=0;j<columnas;j++){
                    if(cuentaMinas <= numMinas && rand.nextInt(1000) < 2 && arrayBotones[i][j].mina == 0){
                        arrayBotones[i][j].mina=1;
                        cuentaMinas++;
                    }
               }
           }
        }
    }
     private void compruebaMinas()
    {
        for(int i=0; i< filas; i++){
               for(int j=0;j<columnas;j++){
                   checkeaNumMinas(arrayBotones[i][j]);
                }
        }
    
    }
    //Metodo auxiliar que muestre la colocacion de las minas
    private void mostrarMinas()
    {
        for(int i=0; i< filas; i++){
               for(int j=0;j<columnas;j++){
                    if(arrayBotones[i][j].mina == 1){
                        arrayBotones[i][j].setText("M");
                        arrayBotones[i][j].setBackground(Color.red);
                    }
                }
        }
    
    }
    private void compruebaLimites(int fila, int columna)
    {
        //COMPRUEBA SI HAY NUMEROS AL LADO DE LAS CASILLAS QUE VALEN 0
        if(fila >=0 && columna >=0 && fila < filas && columna < columnas)
        {
            if(arrayBotones[fila][columna].numMinas!=0){
                //arrayBotones[fila][columna].setBackground(Color.darkGray);
                arrayBotones[fila][columna].setText(""+ arrayBotones[fila][columna].numMinas);
                //arrayBotones[fila][columna].enable(false);
                
                
            }
        }
    }
    
    private void sigueDescubriendoIMenos(int fila, int columna)
    {
        while(fila>=0 && columna >=0 && fila < filas && columna < columnas && arrayBotones[fila][columna].numMinas==0)
        {
                arrayBotones[fila][columna].setBackground(Color.darkGray);
                fila--;
                
                compruebaLimites(fila,columna);        
        }
    }
    private void sigueDescubriendoJMas(int fila, int columna)
    {
        while(fila>=0 && columna >=0 && fila < filas && columna < columnas && arrayBotones[fila][columna].numMinas==0)
        {
                arrayBotones[fila][columna].setBackground(Color.darkGray);
                columna++;
                
                compruebaLimites(fila,columna);
                
        }
    }
    private void sigueDescubriendoIMas(int fila, int columna)
    {
        while(fila>=0 && columna >=0 && fila < filas && columna < columnas && arrayBotones[fila][columna].numMinas==0)
        {
                arrayBotones[fila][columna].setBackground(Color.darkGray);
                fila++;
                
                compruebaLimites(fila,columna);
                
        }
    }
    private void sigueDescubriendoJMenos(int fila, int columna)
    {
        while(fila>=0 && columna >=0 && fila < filas && columna < columnas && arrayBotones[fila][columna].numMinas==0)
        {
                arrayBotones[fila][columna].setBackground(Color.darkGray);
                columna--;
                
                compruebaLimites(fila,columna);
                
        }
    }
                
          private void sigueDescubriendo(int i, int j)
          {
                sigueDescubriendoIMenos(i, j);
                sigueDescubriendoIMas(i,j);
                sigueDescubriendoJMenos(i, j);
                sigueDescubriendoJMas(i, j);        
          }  
          private void probarArrayList2(ArrayList<Casilla> arraylistCasillas)
          {
              for(int i = 0; i<arraylistCasillas.size(); i++){
                  if(arraylistCasillas.get(i).numMinas ==0){
                        
                     arraylistCasillas.get(i).setBackground(Color.darkGray);
                  }
                     compruebaLimites(arraylistCasillas.get(i).x-1,arraylistCasillas.get(i).y);
                     compruebaLimites(arraylistCasillas.get(i).x+1,arraylistCasillas.get(i).y);
                     compruebaLimites(arraylistCasillas.get(i).x,arraylistCasillas.get(i).y-1);
                     compruebaLimites(arraylistCasillas.get(i).x,arraylistCasillas.get(i).y+1);
                     arraylistCasillas.remove(i);
              }
          }
          private void probarArrayList(int fila, int columna)
          { 
              ArrayList<Casilla> arraylistCasillas = new ArrayList<Casilla>();
              for(int i = fila -1; i<=fila +1; i++){
                    for(int j = columna-1; j <= columna+1; j++){
                        if(i>=0 && j >= 0 && i<filas && j < columnas && (arrayBotones[i][j].getBackground() != Color.darkGray && arrayBotones[i][j].numMinas == 0)){ 
                            arraylistCasillas.add(arrayBotones[i][j]);
                        }
                    }
              }
              probarArrayList2(arraylistCasillas);
          }
    //Probando la recursividad
    private void descubreMinas(Casilla c)
    {   
        ArrayList<Casilla> arraylistCasillas = new ArrayList<Casilla>();
            arraylistCasillas.add(c);
        
           while (arraylistCasillas.size() > 0){
                Casilla b = arraylistCasillas.get(0);
                for (int k=-1; k<2; k++){
                    for (int m=-1; m<2; m++){
                        if ((b.x + k >= 0)&&
                            (b.y + m >= 0)&&
                            (b.x + k < filas) &&
                            (b.y + m < columnas)){
                            //si el boton de esa posición está habilitado 
                            //es que no lo he chequeado todavia
                            if (arrayBotones[b.x + k][b.y + m].isEnabled()){
                               if (arrayBotones[b.x + k][b.y + m].numMinas == 0){
                                   arrayBotones[b.x + k][b.y + m].setEnabled(false);
                                   compruebaLimites(b.x + k+1,b.y + m);
                                   compruebaLimites(b.x + k-1,b.y + m);
                                   compruebaLimites(b.x + k,b.y + m-1);
                                   compruebaLimites(b.x + k,b.y + m+1);
                                   arraylistCasillas.add(arrayBotones[b.x + k][b.y + m]);
                               } 
                            }
                        }    
                    }
                }
                arraylistCasillas.remove(b);
            }
    }
    private void pintaBotones()
    {
        for(int i=0; i<filas; i++)
        {
            for(int j=0;j<columnas;j++)
            {
                Casilla b = new Casilla(i,j);
                b.setBorder(null);
                
                
                //Añadir evento
                b.addMouseListener(new MouseAdapter() {
                                                        @Override
                                                            public void mousePressed(MouseEvent evt)
                                                            {
                                                                //metodo a llamar cuando pulse el boton
                                                                botonPulsado(evt);                                                            }
                                                            });
                //Añadir boton al array
                arrayBotones[i][j] = b;
                //Añadir el boton a la pantalla
                getContentPane().add(arrayBotones[i][j]);  
            }
        }
        colocarMinas();
        compruebaMinas();
    }
    /**
     * Creates new form VentanaBuscaminas
     */
    public VentanaBuscaminas() {
        initComponents();
        setSize(1024,768);
        //JFrame usará un layout de rejilla
        getContentPane().setLayout(new GridLayout(filas, columnas));
        pintaBotones();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
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
            java.util.logging.Logger.getLogger(VentanaBuscaminas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaBuscaminas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaBuscaminas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaBuscaminas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaBuscaminas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
