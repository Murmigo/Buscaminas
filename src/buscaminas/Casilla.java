/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;

import javax.swing.JButton;

/**
 *
 * @author Guillermo Abarca
 */
public class Casilla extends JButton{
    
        int mina = 0;
        int x, y;
        int numMinas=0;
        
    public Casilla(int _x, int _y){
         x = _x;
         y = _y;
        
    
    }
    
}
