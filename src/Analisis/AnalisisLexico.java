package Analisis;

import Modelo.Automata;
import Modelo.Token;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.StringTokenizer;

public class AnalisisLexico {

    private ArrayList etiquetas;   //ALMACENA LAS ETIQUETAS
    private Hashtable tokens_fijos;    //ALMACENA LA TABLA DE TOKEN'S FIJOS
    private StringTokenizer tk;         //EL SEPARADOR
    private final String[] separadores_aux = {"(", ")", "{", "}", ";", ":", "=", "+", "-", "*", "/", ","}; //SEPARADORES AUXILIARES

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
//        tokens_fijos.put("++", 15);
        tokens_fijos.put(",", 16);
        tokens_fijos.put("for", 17);
        tokens_fijos.put("do", 18);
        tokens_fijos.put("#", 19);
        tokens_fijos.put("call", 20);
    }

    public AnalisisLexico() {
        CargarTF();
        etiquetas = new ArrayList();
    }

    public ArrayList<Token> analizarLexico(String compare) {//METODO PRINCIPAL, el parametro COMPARE recibe el texto a analizar.
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
        etiquetas = analizarLexico(aux);//AHORA LLAMAMOS AL METODO SECUNDARIO ANTES DEL RETORNO
        return etiquetas;
    }

    private ArrayList analizarLexico(ArrayList tmp) {//METODO SECUNDARIO
        ArrayList<Token> toRet = new ArrayList();//INICIALIZAMOS LA LISTA A RETORNAR
        for (Object tmp1 : tmp) {//VAMOS A RECORRER "TMP" QUE SERAN LAS ETIQUETAS RECIBIDAS DEL METODO PRINCIPAL
            String aux = tmp1.toString();//GUARDAMOS LA DESCRIPCION DEL TOKEN EN LA VARIABLE AUXILIAR
            if (tokens_fijos.containsKey(aux)) {//SI ESTE TOKEN EXISTE EN LA TABLA DE TOKEN'S FIJOS
                toRet.add(new Token(tokens_fijos.get(aux).toString(), aux));//ENTONCES SE CARGA DIRECTAMENTE A LA LISTA DE ETIQUETAS
            } else {
                toRet.add(new Token(Integer.toString(new Automata().AutomataGral(aux)), aux));//SI NO SE MANDA AL AUTOMATA PARA CHECAR QUE ES
            }
        }
        return toRet;
    }

}
