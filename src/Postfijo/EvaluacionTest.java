/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Postfijo;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 
 * @author rms
 */
public class EvaluacionTest {
    
    public static void main(String[] args) throws IOException {
        ArrayList<String> exp = new ArrayList<>();
        exp.add("x");
        exp.add("2");
        exp.add("4");
        exp.add("^");
        exp.add("=");
        
        Evaluacion eva = new Evaluacion();
//        eva.evaluation(exp);
    }

}
