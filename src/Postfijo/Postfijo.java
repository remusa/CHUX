/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Postfijo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author rms
 */
public class Postfijo {

    String aux;
    Pila operadores;
    Pila operandos;
    ArrayList<String> lines;
    ArrayList<String> lexema;
    ArrayList<String> res;
    public Map<String, Integer> prioridad = new HashMap<>();

    /**
     * @param lexema
     * @throws java.io.IOException
     */
    public Postfijo(ArrayList<String> lexema) throws IOException {
        this.lexema = lexema;
        operadores = new Pila(lexema.size());
        operandos = new Pila(lexema.size());
        this.getPrioridad();
    }

    public void getPrioridad() {
        prioridad.put("^", 2);

        prioridad.put("*", 3);
        prioridad.put("/", 3);

        prioridad.put("+", 4);
        prioridad.put("-", 4);

        prioridad.put(">", 5);
        prioridad.put("<", 5);
        prioridad.put(">=", 5);
        prioridad.put("<=", 5);
        prioridad.put("!=", 5);

        prioridad.put("OR", 6);
        prioridad.put("AND", 6);
        prioridad.put("NOT", 6);

        prioridad.put("=", 7);
    }

    public ArrayList<String> getPosfijo() {
        for (String lex : lexema) {
            switch (lex) {

                case ")":
                    while (!operadores.empty()
                            && !"(".equals(operadores.stacktop())) {    //mientras no este vacia y operadores no sea un (
                        aux = operadores.pop();
                        operandos.push(aux);                            //cambia lo que esta en la pila operadores a la pila operandos
                    }
                    if (operadores.empty()) {                           //si "operadores" está vacía cuando checamos la pila
                        System.out.println("Expresión Errónea");        //está mal la expresión -> no hay "("
                    } else {
                        operadores.pop();                               //sacamos el paréntesis que abre
                    }
                    break;

                case "(":   //por defecto "(" se mete a la pila de operadores
                    operadores.push(lex);
                    break;

                case "OR":
                case "NOT":
                case "AND":
                case "^":
                case "+":
                case "-":
                case "*":
                case "/":
                case ">":
                case "<":
                case ">=":
                case "<=":
                case "!=":
                case "==":
                case "=":
                    if (operadores.empty()) {                         //si "operadores" está vacía
                        operadores.push(lex);                         //no es necesario checar si es paréntesis o su prioridad
                    } else {                                          //hay que checar si es "(" y su prioridad
                        while (!operadores.empty() //mientras la pila no este vacía
                                && !operadores.stacktop().equals("(") //y operador del tope no es "("
                                && this.prioridad.get(lex) >= this.prioridad.get(operadores.stacktop())) { //checamos que prioridad 
                            //sea mayor o igual al tope
                            //de la pila operadores
                            aux = operadores.pop(); //si es así, se saca el tope de la pila operadores
                            operandos.push(aux);    //y se mete a la pila operandos
                        }
                        operadores.push(lex);       //se mete caracter a la pila operadores
                    }
                    break;

                default:    //si no es operador es operando (se meten a su propia pila por defecto)
                    operandos.push(lex);
                    break;
            }
        }
        while (!operandos.empty()) {            //si la pila operandos no está vacía
            operadores.push(operandos.pop());   //se sacan de "operandos" los caracteres y se meten a "operadores" 
        }
        return mostrarPila(operadores);
    }

    public ArrayList<String> mostrarPila(Pila p) {
        ArrayList<String> posfijo = new ArrayList();
        while (!p.empty()) {
            posfijo.add(p.pop());
        }
        return posfijo;
    }
}
