/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Postfijo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author rms
 */
public class Evaluacion {

    public static final ArrayList<String> operators = new ArrayList<>();
    private final HashMap<String, String> dataTypes = new HashMap<>();

    public Evaluacion() {
        addOperators();
        addDataTypes();
    }

    private void addOperators() {
        operators.add("^");
        operators.add("*");
        operators.add("/");
        operators.add("+");
        operators.add("-");
        operators.add(">");
        operators.add("<");
        operators.add(">=");
        operators.add("<=");
        operators.add("!=");
        operators.add("OR");
        operators.add("AND");
        operators.add("NOT");
    }

    private void addDataTypes() {
        dataTypes.put("int,int", "int");
        dataTypes.put("int,double", "double");
        dataTypes.put("int,boolean", "-");
        dataTypes.put("int,string", "string");
        dataTypes.put("int,char", "string");

        dataTypes.put("double,int", "double");
        dataTypes.put("double,double", "double");
        dataTypes.put("double,boolean", "-");
        dataTypes.put("double,string", "string");
        dataTypes.put("double,char", "string");

        dataTypes.put("boolean,int", "-");
        dataTypes.put("boolean,double", "-");
        dataTypes.put("boolean,boolean", "boolean");
        dataTypes.put("boolean,string", "-");
        dataTypes.put("boolean,char", "-");

        dataTypes.put("string,int", "string");
        dataTypes.put("string,double", "string");
        dataTypes.put("string,boolean", "-");
        dataTypes.put("string,string", "string");
        dataTypes.put("string,char", "string");

        dataTypes.put("char,int", "string");
        dataTypes.put("char,double", "string");
        dataTypes.put("char,boolean", "-");
        dataTypes.put("char,string", "string");
        dataTypes.put("char,char", "string");
    }

    public static void main(String[] args) throws IOException {
        ArrayList<String> exp = new ArrayList<>();
        exp.add("x");
        exp.add("2");
        exp.add("4");
        exp.add("^");
        exp.add("=");

        ArrayList<String> types = new ArrayList<>();
        types.add("int");
        types.add("int");
        types.add("double");
        types.add("^");
        types.add("=");

        Evaluacion eva = new Evaluacion();
        eva.evaluation(exp, types);
    }

    /**
    Método para evaluar expresión en postfijo
    @param exp 
    @param types 
    @return  
     */
    public String evaluation(ArrayList<String> exp, ArrayList<String> types) {
        System.out.println("exp " + exp + "\ndataTypes" + types);
        String res = "";

        for (int i = 0; i < exp.size(); i++) {
            if (operators.contains(exp.get(i))) { //es un operador
                String op = exp.get(i);

                String tipoX = types.get(i - 2);
                String tipoY = types.get(i - 1);
                String tipoZ = "";
                
                if (dataTypes.containsKey(tipoX + "," + tipoY)) {
                    tipoZ = dataTypes.get(tipoX + "," + tipoY); //obtener el tipo
                    System.out.println("tipoZ " + tipoZ);
                }

                Object x = exp.get(i - 2);
                Object y = exp.get(i - 1);
                Object z = evaluationOperator(op, x, y);

//                System.out.println("Op: " + op + "\nX: " + x + "\nY: " + y + "\nR: " + z);
                i = i - 2;
                exp.remove(i);
                exp.remove(i);
                exp.remove(i);

                types.remove(i);
                types.remove(i);
                types.remove(i);

                exp.add(i, z.toString()); //se agrega el resultado
            } //finalizamos
            else if ("=".equals(exp.get(i))) {
                res = exp.get(i - 1);
            }

        }
        System.out.println("res " + res);
        return "";
    }

    /**
    Método para evaluar números con operadores
    @param op
    @param x
    @param y
    @return 
     */
    private Object evaluationOperator(String op, Object x, Object y) {
        Object temp = null;
        switch (op) {
            case "^":
                temp = Math.pow(Double.parseDouble((String) x), Double.parseDouble((String) y));
                break;
            case "*":
                temp = Double.parseDouble((String) x) * Double.parseDouble((String) y);
                break;
            case "/":
                temp = Double.parseDouble((String) x) / Double.parseDouble((String) y);
                break;
            case "+":
                temp = Double.parseDouble((String) x) + Double.parseDouble((String) y);
                break;
            case "-":
                temp = Double.parseDouble((String) x) - Double.parseDouble((String) y);
                break;
        }
        return temp;
    }

}
