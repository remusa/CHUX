package Modelo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;

/**
 * 
 * @author rms
 */
public class AutomataR {

    private final String cadena1;
    private final char[] cadena;
    private final int[][] transicion;
    private final int noFilas, noColumnas;
    private final ArrayList<Character> alfabeto;

    public AutomataR() {
        this.cadena1 = "";
        this.cadena = null;
        this.transicion = new int[1][2];
        this.noFilas = transicion.length;
        this.noColumnas = transicion[0].length;
        this.alfabeto = new ArrayList<>();
    }

    /**
    
    @param alfa Alfabeto recibido
    @param cad Cadena recibida
    @param trans Tabla de transición recibida
     */
    public AutomataR(String alfa, String cad, int[][] trans) {
        this.cadena1 = cad;
        this.alfabeto = new ArrayList<>();
        this.cadena = cad.toCharArray();
        this.transicion = trans;
        for (int i = 0; i < alfa.length(); i++) {   //llenamos ArrayList alfabeto
            this.alfabeto.add(alfa.charAt(i));
        }
        noFilas = transicion.length;
        noColumnas = transicion[0].length;
    }

    /**
    Método para analizar los lexemas si no se encuentran en la tabla de tokens fijos mediante sentencias if
    @param lexema Lexema que se va a analizar, ya se buscó previamente que no perteneciera a la tabla de tokens fijos
    @return 
     */
    public int automatusPrime(String lexema) {
        char caracter = lexema.charAt(0);

        // es número
        if (caracter >= 0 && caracter <= 9) {
            if (lexema.contains(".")) {
                return 32; //flotante
            } else {
                return 31; //entero
            }
        } // es cadena
        else if (caracter == '"') {
            if (lexema.charAt(lexema.length() - 1) == '"' && lexema.length() > 1) { //cadenas que abren y cadenas que cierran
                return 30; //cadena
            }
            return 100; //error

        } // es letra: mayúsculas (65-90) minúsculas (97-122)
        else if ((caracter >= 65 && caracter <= 90) || (caracter >= 97 && caracter <= 122)) {
            return 32;
        } // errores
        else {
            return 100;
        }
    }

    /**
    Método principal del autómata, recibe una tabla de transición y checa el estado final en el que termina
    @param lexema 
     */
    public void automata(String lexema) {
        // Imprimimos
//        imprimir();

        // Variables
        char caracterActual;
        int filaEstado = 0; //empezamos en el estado 0
        int columnaIndice; //indice-columna a la que pertenece en la tabla de transición (alfabeto en la parte de arriba)
        int contador = 0;

        // Recorrido del lexema
        while (contador < cadena.length) {
            caracterActual = cadena[contador]; //obtenemos el caracter a comparar
            System.out.println("Estado: [s" + filaEstado + "," + caracterActual + "]");
            if (contador == cadena.length - 1) {
                // Estado final
                System.out.println("Estado final: [s" + filaEstado + "]");
            }
            else if (alfabeto.contains(caracterActual)) { //checamos que el caracter obtenido pertenezca al alfabeto permitido
                columnaIndice = alfabeto.indexOf(caracterActual); //(indice de alfabeto = columna) checamos posicion del caracter en el alfabeto
                filaEstado = transicion[filaEstado][columnaIndice]; //[fila][columna] de tabla de transición (estado=fila)
                contador++;
            } else {
                System.out.println("El caracter '" + caracterActual + "' no es parte del alfabeto permitido");
                break;
            }
        }

        // Estado de aceptación
        if (transicion[filaEstado][noColumnas - 1] == 1) { //checamos si el estado en el que termina es aceptado
            System.out.println("La cadena '" + cadena1 + "' es válida" + "\n____________________________" + "\n");
        } else {
            System.out.println("La cadena '" + cadena1 + "' no es válida" + "\n____________________________" + "\n");
        }
    }

    /**
    Método para imprimir en consola los datos recibidos
     */
    public void imprimir() {
        // Alfabeto
        System.out.println("Alfabeto: " + alfabeto);

        // Cadena
        System.out.println("Cadena: '" + cadena1 + "'");

        // Tabla de transición
        System.out.println("Tabla de transición: ");
        for (int i = 0; i < noFilas; i++) {   //recorremos filas
            for (int j = 0; j < transicion[i].length; j++) {    //recorremos columnas
                System.out.printf("%4d", transicion[i][j]);
            }
            System.out.println();
        }
    }

//    public static void main(String[] args) throws IOException {
//        String alfabeto = "abc";
//        String cadena = "aaabc";        //aceptada
//        int transicion[][] = {
//            {1, 0, 0, 0},
//            {1, 2, 0, 0},
//            {2, 2, 2, 1}
//        };
//
//        Automata aut = new Automata(alfabeto, cadena, transicion);
//        aut.automata();
//    }
    
}
