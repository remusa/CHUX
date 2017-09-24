/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Postfijo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 
 * @author rms
 */
public class TestPostfijo {
    
    public static void main(String[] args) throws IOException {
        ArrayList<String> res1 = new ArrayList();
        String prueba1 = "z=a+b";
        String prb1[] = prueba1.split("");
        res1.addAll(Arrays.asList(prb1));
        Postfijo p1 = new Postfijo(res1);
        res1 = p1.getPosfijo();
        System.out.println("Prueba 1: " + prueba1 + "\nPostfijo 1: " + res1 + "\n");
    }

}
