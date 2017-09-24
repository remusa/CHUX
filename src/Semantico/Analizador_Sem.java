package Semantico;

import Analisis.*;
import Modelo.Automata;
import Modelo.Token;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

public class Analizador_Sem {

    private ArrayList etiquetas;   //ALMACENA LAS ETIQUETAS
    private Hashtable tokens_fijos;    //ALMACENA LA TABLA DE TOKEN'S FIJOS
    private StringTokenizer tk;         //EL SEPARADOR
    private final String[] separadores_aux = {"(", ")", "{", "}", ";", ":", "=", "+", "-", "*", "/"}; //SEPARADORES AUXILIARES
    private ArrayList<Token> toRet;
    private ArrayList<TokendeTabla> toRetSem;
    private ArrayList<Variable> arrSem = new ArrayList();
    private ArrayList<Variable> arrSemaux = new ArrayList();
    private ArrayList<Variable> arrSemaux2 = new ArrayList();
    private ArrayList<Variable> variable = new ArrayList();
    public ArrayList<Variable> resul = new ArrayList();
    private ArrayList<Variable> prim = new ArrayList();
    public boolean cambia = false;
    private int i, l;

    public Analizador_Sem() {
        CargarTF();
        etiquetas = new ArrayList();
    }

    private void CargarTF() {//CARGA LA TABLA DE TOKEN'S FIJOS
        tokens_fijos = new Hashtable();
        tokens_fijos.put("inicio", 1);
        tokens_fijos.put("(", 2);
        tokens_fijos.put(")", 3);
        tokens_fijos.put("{", 4);
        tokens_fijos.put("}", 5);
        tokens_fijos.put("int", 6);
        tokens_fijos.put("double", 6);
        tokens_fijos.put("string", 6);
        tokens_fijos.put("char", 6);
        tokens_fijos.put(";", 7);
        tokens_fijos.put("=", 8);
        tokens_fijos.put("read", 9);
        tokens_fijos.put("put", 10);
        tokens_fijos.put(":", 11);
        tokens_fijos.put("while", 12);
        tokens_fijos.put(">", 13);
        tokens_fijos.put("<", 13);
        tokens_fijos.put(">=", 13);
        tokens_fijos.put("<=", 13);
        tokens_fijos.put("==", 13);
        tokens_fijos.put("!=", 13);
        tokens_fijos.put("and", 14);
        tokens_fijos.put("or", 14);
        tokens_fijos.put("+", 15);
        tokens_fijos.put("-", 15);
        tokens_fijos.put("*", 15);
        tokens_fijos.put("/", 15);
        tokens_fijos.put("++", 15);
        tokens_fijos.put(",", 16);
        tokens_fijos.put("for", 17);
        tokens_fijos.put("do", 18);
        tokens_fijos.put("#", 19);
        tokens_fijos.put("call", 20);
    }

    public ArrayList<TokendeTabla> analizarSemantico(String compare) throws InterruptedException {//METODO PRINCIPAL, el parametro COMPARE recibe el texto a analizar.
        etiquetas.add(compare);//CARGAMOS EL TEXTO COMPLETO A LA LISTA DE ETIQUETAS PARA SEPARARLO DESPUES
        ArrayList aux = new ArrayList();//INICIALIZAMOS UNA LISTA AUXILIAR PARA 
        tk = new StringTokenizer(etiquetas.get(0).toString(), "\n");//PRIMERO VAMOS A SEPARAR POR EL SALTO DE LINEA, HACEMOS REFERENCIA A LA PRIMERA
        //POSICION DE LA LISTA DE ETIQUETAS PARA SEPARAR TODO EL TEXTO POR SALTO DE LINEA PRIMERO, EL RESULTADO SE ALMACENA DIRECTAMENTE EN "TK"
        while (tk.hasMoreElements()) {//RECORREMOS "TK" PARA PASAR TODO SU CONTENIDO A LA LISTA AUXILIAR PARA NO PERDER EL TEXTO ORIGINAL AUN
            aux.add(tk.nextElement());//NEXTELEMENT RECORRE UNO A UNO LOS ELEMENTOS DEL STRINGTOKENIZER
        }
        etiquetas = aux;//ahora nuestras nuevas etiquetas seran las guardades en AUX, osea las separadas por salto de linea
        aux = new ArrayList();//reiniciamos la lista auxiliar para utilizarla de nuevo
        for (String separadores_aux1 : separadores_aux) {//AHORA VAMOS A SEPARAR CON LOS SEPARADORES AUXILIARES UNO A UNO
            for (Object etiqueta : etiquetas) {//CON EL SEPARADOR "i" SEPARAREMOS CADA UNA DE LAS ETIQUETAS ALMACENADAS EN LA LISTA
                //ASI ASEGURAMOS QUE SE SEPARE ABSOLUTAMENTE TODO
                tk = new StringTokenizer(etiqueta.toString(), separadores_aux1, true);//AQUI UTILIZAMOS EL SEPARADOR AUXILIAR EN CUESTION
                //EL "TRUE" INDICA QUE VAMOS A MANTENER TAMBIÉN AL SEPARADOR ENCONTRADO COMO UN ELEMENTO DE LA LISTA DE ETIQUETAS
                while (tk.hasMoreElements()) {
                    aux.add(tk.nextToken());//AQUI PASAMOS LO QUE CONTENGA EL TOKENIZER A LA LISTA AUXILIAR
                }
            }
            etiquetas = aux;//AHORA NUESTRA LISTA SE VERA MODIFICADA CON LA NUEVA SEPARACION
            aux = new ArrayList();//REINICIAMOS LA LISTA AUXILIAR
        }
        
        //hacer un arraylist q inicie en cero y vea si hay una variable inicializada en esta parte ya tenemos de etiquetas de 
        //arriba todo separado(tokens) y podremos ver si le sige palabras reservadas y cosas por el estilo
        for (Object etiqueta : etiquetas) {//ESTA PARTE ES PARA SEPARAR CORRECTAMENTE LAS CADENAS DE TEXTO, ES DECIR, SI NO SE CIERRAN LAS COMILLAS
            //NO TOMARA TODA LA LINEA, SOLO TOMARA HASTA EL SIGUIENTE SEPARADOR NATURAL
            if (!(etiqueta.toString().charAt(0) == '"')) {//SI UNA ETIQUETA COMIENZA CON COMILLAS ENTONCES DEBEMOS VERIFICAR QUE ESTA ESTE 
                //CORRECTAMENTE EXPRESADA, ES DECIR QUE EL FINAL SEAN LAS COMILLAS
                tk = new StringTokenizer(etiqueta.toString());//VAMOS A SEPARAR CONTEMPLANDO ESPACIOS, SALTOS DE LINEA, ETC. 
                while (tk.hasMoreElements()) {//MANDAMOS TODO AL AUXILIAR
                    aux.add(tk.nextElement());
                }
            } else {
                aux.add(etiqueta);//SI NO INICIA CON COMILLAS VAMOS A PASAR LA ETIQUETA SIN DISCRIMINAR
            }
        }
        
        etiquetas = analizarSemantico(aux);//AHORA LLAMAMOS AL METODO SECUNDARIO ANTES DEL RETORNO
        return etiquetas;
    }

    private ArrayList analizarSemantico(ArrayList tmp) throws InterruptedException {//METODO SECUNDARIO
        arrSem = new ArrayList();
        toRet = new ArrayList();//INICIALIZAMOS LA LISTA A RETORNAR
        for (Object tmp1 : tmp) {//VAMOS A RECORRER "TMP" QUE SERAN LAS ETIQUETAS RECIBIDAS DEL METODO PRINCIPAL
            String aux = tmp1.toString();//GUARDAMOS LA DESCRIPCION DEL TOKEN EN LA VARIABLE AUXILIAR
            if (tokens_fijos.containsKey(aux)) {//SI ESTE TOKEN EXISTE EN LA TABLA DE TOKEN'S FIJOS
                toRet.add(new Token(tokens_fijos.get(aux).toString(), aux));//ENTONCES SE CARGA DIRECTAMENTE A LA LISTA DE ETIQUETAS
            } else {
                toRet.add(new Token(Integer.toString(new Automata().AutomataGral(aux)), aux));//SI NO SE MANDA AL AUTOMATA PARA CHECAR QUE ES
            }
        }
        toRetSem = new ArrayList();

        for (int i = 0; i < toRet.size(); i++) {
            if (toRet.get(i).getNumero().equals("50")) { //si es variable
                arrSem.add(new Variable(toRet.get(i).getLexema()));//este array almacena todas las variables
                variable.add(new Variable(toRet.get(i).getLexema()));
                if (toRet.get(i - 1).getNumero().equals("6")) {
                    //checamos
                    toRetSem.add(new TokendeTabla(
                            toRet.get(i - 1).getLexema(), 
                            toRet.get(i).getLexema(), 
                            true,   //isDeclarada
                            cambia, //isUtilizada
                            false   //isDuplicada
                    ));//DEC,uti
                    
//                    if (toRet.get(i).getLexema() == toRet.get(i).getLexema()) {
//                         System.out.println("arrSem. " + arrSem.get(i).getVariable());
//                        JOptionPane.showMessageDialog(null, "LA VARIABLE: " + toRet.get(i).getLexema() + " YA FUE DECLARADA");
//                    }
                    arrSemaux.add(new Variable(toRet.get(i).getLexema()));//alamacena variables si hay un tipo atras de ella
                }
            }
        }

        for (int k = 0; k < arrSem.size(); k++) {//para que quite todos los elementos una y otra vez
            //quitar variables repetidos del arrayList 
            for (int i = 0; i < arrSem.size(); i++) {
                arrSem.get(i).getVariable();
                for (int j = i + 1; j < arrSem.size(); j++) {
                    arrSem.get(j).getVariable();
                    if (arrSem.get(i).getVariable().equals(arrSem.get(j).getVariable())) {
                        arrSem.remove(j);
                    }
                }
            }
        }

        for (int j = 0; j < arrSem.size(); j++) {////array de variables sin repetir
            arrSemaux2.add(new Variable(arrSem.get(j).getVariable()));
            System.out.println("arrSem. " + arrSem.get(j).getVariable());
        }
        for (int k = 0; k < arrSemaux.size(); k++) {////array de variables declaradas
            arrSemaux2.add(new Variable(arrSemaux.get(k).getVariable()));
            System.out.println("arrSemaux. " + arrSemaux.get(k).getVariable());
        }
        for (int j = 0; j < arrSemaux2.size(); j++) {//array de variables con arrSem y arrSemaux
            System.out.println("arrSemaux2 " + arrSemaux2.get(j).getVariable());
        }

        for (int k = 0; k < 40; k++) {//para que quite todos los elementos una y otra vez
            //quitar variables repetidos del arrayList y la variable que se repitio
            for (int i = 0; i < arrSemaux2.size(); i++) {
                arrSemaux2.get(i).getVariable();
                for (int j = i + 1; j < arrSemaux2.size(); j++) {
                    arrSemaux2.get(j).getVariable();
                    if (arrSemaux2.get(i).getVariable().equals(arrSemaux2.get(j).getVariable())) {
                        arrSemaux2.remove(j);
                        arrSemaux2.remove(i);
                    }
                }
            }
        }

        for (int j = 0; j < arrSemaux2.size(); j++) {//array de variables sin declarar
            System.out.println("arrSemaux2....... " + arrSemaux2.get(j).getVariable());
//            JOptionPane.showMessageDialog(null, "ERROR LA VARIABLE:  " + arrSemaux2.get(j).getVariable() + "  NO ESTA DECLARADA", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
        for (int j = 0; j < variable.size(); j++) {
            System.out.println("vn: " + variable.get(j).getVariable());
        }

        for (int k = 0; k < variable.size(); k++) {//para que quite todos los elementos una y otra vez
            //quitar variables repetidos del arrayList 
            for (int i = 0; i < variable.size(); i++) {
                variable.get(i).getVariable();
                for (int j = i + 1; j < variable.size(); j++) {
                    variable.get(j).getVariable();
                    if (variable.get(i).getVariable().equals(variable.get(j).getVariable())) {
                        prim.add(new Variable(variable.get(i).getVariable()));
                        variable.remove(j);
                    }
                }
            }
        }

//          for (int j = 0; j < prim.size(); j++) {
//              System.out.println("prim: "+prim.get(j).getVariable());
//        }

        for (int n = 0; n < prim.size(); n++) {
//            System.out.println("variable: " + prim.get(n).getVariable());
            for (int i = 0; i < toRet.size(); i++) {
                if (toRet.get(i).getNumero().equals("50")) {
                    if (toRet.get(i - 1).getNumero().equals("6")) {
                        if (toRet.get(i).getLexema() == prim.get(n).getVariable()) {
                            resul.add(new Variable(prim.get(n).getVariable()));
                            cambia = true;
                        }
                    }
                }
            }

        }

//        Thread.sleep(2000);
        for (int j = 0; j < resul.size(); j++) {
            for (int k = 0; k < toRetSem.size(); k++) {
                if (toRetSem.get(k).getNombre().contains(resul.get(j).getVariable())) {
                    toRetSem.set(k, new TokendeTabla(toRetSem.get(k).getToken(), resul.get(j).getVariable(), cambia, cambia, false));
                }
            }
        }
        return toRetSem;
    }

}
