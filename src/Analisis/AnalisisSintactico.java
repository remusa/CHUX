package Analisis;

import Modelo.Automata;
import Modelo.Token;
import Postfijo.Evaluacion;
import Postfijo.Postfijo;
import Semantico.Variable;
import Vista.Vista;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import javax.swing.JTextArea;

public class AnalisisSintactico {

    private ArrayList<String> ComparaNT;
    private ArrayList etiquetas;
    private Hashtable Transicion;
    private Hashtable id_NT;
    private Hashtable NT_Content;
    private Hashtable NT_Vacio;
    
    public static ArrayList<Variable> variables = new ArrayList<>();
    
    //Nombre de las producciones
    String[] NT = {
        "inicio",
        
        "sentencias1",
        "sentencias2",
        "sentencias-",
        "acciones1",
        "acciones2",
        "acciones3",
        "VACIO",
        "acciones'1",
        "acciones'2",
        "OM",
        "OM-",
        "escritura",
        "declaracion",
        "repeticion",
        "condicion",
        "con'-",
        "con'",
        "opcion1",
        "opcion2",
        "opcion3",
        "OM'",
        "funcion",
        "fun'",
        "fun'-",
        "par",
        "par'",
        "par'-",
        "repeticion2",
        "for",
        "repeticion3",
        "funcion-",
        "par-",
        //cadenas
        "opcion", //34

        //declaraciones múltiples
        "declaraciones'-", //35
        "declaraciones'", //36
        "declaracionesbi", //37
        "declaracionesbi-", //38

        //llamadas a funciones
        "sentencias", //39
        "llamada", //40

        //put
        "salida1", //41
        "salida2", //42
    };

    //Contenido de las producciones
    String[][] Content = {
        {"19", "1", "2", "3", "4", "sentencias", "5", "funcion", "19"}, //1
        
        {"declaraciones"}, //2
        {"acciones"}, //3
        {"-"}, //4
        {"50", "8", "acciones'", "7", "sentencias"}, //5
        {"escritura"}, //6
        {"repeticion"}, //7
        {"VACIO"},
        {"opcion", "opmat", "sentencias"}, //8
        {"9", "2", "6", "3", "sentencias"}, //9
        {"15", "opmat'"},
        {"-"},
        {"10", "11", "2", "salida", "3", "7", "sentencias"},
        //        {"6", "50", "7", "sentencias"},
        {"6", "50", "declaraciones'", "7", "sentencias"},
        {"12", "2", "condicion", "3", "4", "sentencias", "5", "sentencias"},
        {"opcion", "13", "opcion", "con'"},
        {"-"},
        {"14", "condicion"},
        {"50"},
        {"52"},
        {"53"},
        {"opcion", "opmat"},
        {"50", "2", "par", "3", "4", "sentencias", "5", "fun'"},
        {"funcion"},
        {"-"},
        {"6", "50", "par'"},
        {"16", "par"},
        {"-"},
        {"17", "2", "for", "3", "4", "sentencias", "5", "sentencias"},
        {"6", "50", "8", "opcion", "7", "condicion", "7", "50", "8", "opcion", "opmat"},
        {"18", "4", "sentencias", "5", "12", "2", "condicion", "3", "7", "sentencias"},
        {"-"},
        {"-"},
        //cadenas
        {"51"},
        //declaraciones múltiples
        {"-"}, //35
        {"16", "50", "declaracionesbi"}, //36
        {"16", "50", "declaraciones'"}, //37
        {"-"}, //38

        //llamadas a funciones
        {"llamada"}, //39
        {"20", "50", "2", "par", "3", "7", "sentencias"}, //40

        //salida
        {"51"}, //41
        {"50"}, //42
    };

    public AnalisisSintactico() {
        etiquetas = new ArrayList(); //inicializar etiquetas        

        //1 <NT> -> CONTENIDO
        //Esto se dividio en dos tablas:
        /*
        1 <NT>
        
        y
        
        <NT> -> CONTENIDO
        
        Debido a las restricciones de hashtable, también se podría crear un TDA para manejarlo mas eficientemente, pero la verdad me dio flojera.
        
         */
        Cargar_id_NT();//Carga la tabla (Numero de no terminal, Nombre de no terminal)
        Cargar_NT_Content();//Carga la tabla (Nombre del no terminal, Su contenido)

        CargarTrans();//Carga la tabla de transiciones
        ComparaNT = new ArrayList<>();//Este va a almacenar la parte izquierda de las comparaciones del proceso
        ComparaNT.add("S0");//inicia con S0 este es el primer no terminal que se recorre, es como en el proceso de Benja        
    }

    private void CargarTrans() {//Paso para obtener id "5"
        Transicion = new Hashtable();
        Transicion.put("S0,19", "1");
        
        Transicion.put("inicio,1", "1");
        Transicion.put("declaraciones,6", "14");

        Transicion.put("acciones,50", "5");
        Transicion.put("acciones,12", "7");
        Transicion.put("acciones,17", "7");
        Transicion.put("acciones,18", "7");
        Transicion.put("acciones,10", "6");

        Transicion.put("acciones',53", "9");
        Transicion.put("acciones',52", "9");
        Transicion.put("acciones',50", "9");
        Transicion.put("acciones',9", "10");

        Transicion.put("sentencias,50", "3");
        Transicion.put("sentencias,12", "3");
        Transicion.put("sentencias,10", "3");
        Transicion.put("sentencias,17", "3");
        Transicion.put("sentencias,18", "3");
        Transicion.put("sentencias,6", "2");
        Transicion.put("sentencias,7", "4");
        Transicion.put("sentencias,5", "4");

        Transicion.put("escritura,10", "13");

        Transicion.put("repeticion,12", "15");
        Transicion.put("repeticion,17", "29");
        Transicion.put("repeticion,18", "31");

        Transicion.put("condicion,53", "16");
        Transicion.put("condicion,52", "16");
        Transicion.put("condicion,50", "16");

        Transicion.put("con',14", "18");
        Transicion.put("con',3", "17");
        Transicion.put("con',7", "17");

        Transicion.put("opcion,53", "21");
        Transicion.put("opcion,52", "20");
        Transicion.put("opcion,50", "19");

        Transicion.put("opmat,50", "12");
        Transicion.put("opmat,3", "12");
        Transicion.put("opmat,15", "11");
        Transicion.put("opmat,12", "12");
        Transicion.put("opmat,10", "12");
        Transicion.put("opmat,7", "12");
        Transicion.put("opmat,6", "12");

        Transicion.put("opmat',53", "22");
        Transicion.put("opmat',52", "22");
        Transicion.put("opmat',50", "22");

        Transicion.put("funcion,50", "23");

        Transicion.put("fun',50", "24");

        Transicion.put("par,6", "26");

        Transicion.put("par',16", "27");
        Transicion.put("par',3", "28");

        Transicion.put("for,6", "30");

        Transicion.put("par,3", "33");

        Transicion.put("fun',19", "25");

        Transicion.put("funcion,19", "32");

        //cadenas
        Transicion.put("acciones',51", "9");
        Transicion.put("opcion,51", "34");

        //declaraciones múltiples
        Transicion.put("declaraciones',7", "35");
        Transicion.put("declaraciones',16", "36");

        Transicion.put("declaracionesbi,7", "38");
        Transicion.put("declaracionesbi,16", "37");

        //llamadas a funciones
        Transicion.put("sentencias,20", "39");
        Transicion.put("llamada,20", "40");

        //salida
        Transicion.put("salida,51", "41");
        Transicion.put("salida,50", "42");
    }

    private void Cargar_id_NT() {//paso para obtener NT 
        id_NT = new Hashtable();
        for (int i = 0; i < NT.length; i++) {
            id_NT.put(Integer.toString(i + 1), NT[i]);//CARGAMOS IDENTIFICADOR DE NT CON SU RESPECTIVO NOMBRE
        }
    }

    private void Cargar_NT_Content() {//paso para obtener content (vector)
        NT_Content = new Hashtable();
        NT_Vacio = new Hashtable();
        for (int i = 0; i < NT.length; i++) {//String -> String[]
            NT_Content.put(NT[i], Content[i]);
        }
        NT_Vacio.put("sentencias", "-");
        NT_Vacio.put("opmat", "-");
        NT_Vacio.put("con'", "-");
        NT_Vacio.put("par'", "-");
        NT_Vacio.put("fun'", "-");
        NT_Vacio.put("funcion", "-");

        //declaraciones múltiples
        NT_Vacio.put("declaraciones'", "-");
        NT_Vacio.put("declaracionesbi", "-");

        //llamada a funciones
//        NT_Vacio.put("llamada'", "-");
    }

    public ArrayList<Token> analizarSintactica(ArrayList tokens, JTextArea ta, Vista vista) throws IOException {
        vista.isCorrect = true;//ESTABLECEMOS QUE EL TEXTO ES CORRECTO POR AHORA, PERO LO VAMOS A VERIFICAR
        ta.setText("");// VACIAMOS EL TEXTO DE LA VISTA
        ArrayList<Token> toRet = new ArrayList<>();//LISTA A RETORNAR
        ArrayList<String> et = obtenerEt(tokens);//OBTENEMOS SOLAMENTE LOS NUMEROS DE LAS ETIQUETAS
        ArrayList<Token> programa = tokens;
        
        variables = new ArrayList<>();
        String tipoDato = "";
        boolean declarandoVariables = false;
        boolean dentrodeExpresion = false;
        boolean obteniendoValorVariable = false;
        ArrayList<String> arrValores = new ArrayList<>();
        ArrayList<String> arrTiposDato = new ArrayList<>();
        String variableActual = "";
        
        while (!et.isEmpty() && !ComparaNT.isEmpty()) {//VAMOS A VERIFICAR MIENTRAS QUE LA LISTA DE ETIQUETAS(DERECHA) Y LA DE COMPARACION (IZQUIERDA) NO ESTEN VACIAS
            String line = "";//CADENA AUXILIAR PARA CONCATENAR EL CONTENIDO DE LOS NO TERMINALES
            for (String ComparaNT1 : ComparaNT) {//VAMOS A RECORRER LA LISTA IZQUIERDA, OSEA LA DE COMPARACION PARA CONCATENARLA TODA EN LA LINEA
                line = line + " " + ComparaNT1;
            }
            line = line + "  -  ";//AGREGAMOS ALGO PARA SEPARAR
            for (String et1 : et) {//VAMOS A RECORRER LA LISTA DERECHA PARA CONCATENAR LOS NUMEROS DE LAS ETIQUETAS O TOKENS
                line = line + " " + et1;
            }
            ta.setText(ta.getText() + "\n" + line + "\n");//PEGAMOS LO OBTENIDO EN LA CAJA DE TEXTO PARA MOSTRAR EL PROCESO
            
            String NT_inicial = ComparaNT.get(ComparaNT.size() - 1);//ESTE VA A SER EL NO TERMINAL INICIAL, OSEA EL DE LA DERECHA [S0 IF SUMA] OSEA SUMA
            
            //NO TERMINAL
            try {//VEAMOS SI EL NO TERMINAL INICIAL ES UN NUMERO, PARA PODERLO COMPARAR CON LA ETIQUETA DE LA LISTA DE ETIQUETAS
                int num = Integer.parseInt(NT_inicial);// TRATAMOS DE HACER LA CONVERSIONA  ENTERO, SI NO SE PUEDE MANDARA AL CATCH
                int compare = Integer.parseInt(et.get(0));//ESTO SUCEDE SI EN REALIDAD ES UN NUMERO, CONVERTIMOS LA PRIMERA ETIQUETA 
                //[ 4 6 7 3] OSEA 4 (IZQUIERDA) PARA COMPARARLAS
                
                //----------------------------------------------------------------------------------------
                //  ANÁLISIS SEMÁNTICO
                //----------------------------------------------------------------------------------------
                
                //Obtener tipo de dato
                if (compare == 6 && dentrodeExpresion == false) {
                    tipoDato = programa.get(0).getLexema();
                    declarandoVariables = true;
                }
                
                //Obtener valor (51=cadena, 52=entero, 53=double, 54=caracter)
                if (compare == 8) { //8 "="
                    obteniendoValorVariable = true;
                    arrValores.clear();
                    
                    arrTiposDato.clear();
                    
                    //checar que valor concuerde con tipo
                }
                
                //Obtener valor
                if (obteniendoValorVariable) {
                    arrValores.add(programa.get(0).getLexema());
                    
                    arrTiposDato.add(et.get(0));

                    int indexVariable = 0;
                    for (int i = 0; i < variables.size(); i++) {
                        if (variables.get(i).getVariable().equals(variableActual)) {
                            indexVariable = i;
                        }
                    }
                    
                    ArrayList<String> postfijo = new ArrayList<>();
                    String valorEvaluacion = "";
                    
                    if (!arrValores.get(0).equals(variableActual)) {
                        arrValores.add(0, variableActual);
                        
                        arrTiposDato.add(0, tipoDato);
                    } else if (arrValores.get(arrValores.size() - 1).equals(";")) {
                        arrValores.remove(arrValores.size() - 1);

                        arrTiposDato.remove(arrTiposDato.size() - 1);
                    }
                    
                    Postfijo objPostfijo = new Postfijo(arrValores);
                    postfijo = objPostfijo.getPosfijo();
                    
                    Evaluacion eva = new Evaluacion();
                    ArrayList<String> arrTiposDatosPostfijo = new ArrayList<>();
                    for (String aux : postfijo) {
                        System.out.println("aux " + aux);
                        
                        if (Evaluacion.operators.contains(aux) || aux.equals("=")) {
                            arrTiposDatosPostfijo.add(aux);
                        } else {
                            int tipo = new Automata().AutomataGral(aux);
                            switch (tipo) {
                                case 50:
                                    arrTiposDatosPostfijo.add(tipoDato);
                                    break;
                                case 51:
                                    arrTiposDatosPostfijo.add("string");
                                    break;
                                case 54:
                                    arrTiposDatosPostfijo.add("char");
                                    break;
                                case 53:
                                    arrTiposDatosPostfijo.add("double");
                                    break;
                                case 52:
                                    arrTiposDatosPostfijo.add("int");
                                    break;
                                default:
                                    arrTiposDatosPostfijo.add("error");
                            }
                        }
                        
                    }
                
//                    String res = eva.evaluation(postfijo, arrTiposDatosPostfijo);
                    
                    System.out.println("var: " + variableActual 
                            + "\narrValores: " + arrValores 
                            + "\narrTiposDato: " + arrTiposDato 
                            + "\npostfijo: " + postfijo 
                            + "\narrTiposDatosPostfijo: " + arrTiposDatosPostfijo);
                    
                    variables.get(indexVariable).setValor(postfijo.toString());
                }
                
                //Termina la declaración de variables si llega ";"
                if (compare == 7) {
                    declarandoVariables = false;
                    obteniendoValorVariable = false;
                }

                //Inicia expresión "("
                if (compare == 2) {
                    ta.setText(ta.getText() + "\nINICIA EXPRESION\n");
                    dentrodeExpresion = true;
                    
                    //agregar a posfijo
                }

                //Termina expresión ")"
                if (compare == 3) {
                    ta.setText(ta.getText() + "\nTERMINA EXPRESION\n");
                    dentrodeExpresion = false;
                    
                    //evaluar posfijo
                }

                //Es una variable
                if (compare == 50) {
                    variableActual = programa.get(0).getLexema();
                    arrValores.add(0, variableActual);
                    
                    arrTiposDato.add(0, tipoDato);
                    
                    //VARIABLE DUPLICADA
                    boolean variableDuplicada = false;
                    int indexDuplicada = 0;
                    for (int i = 0; i < variables.size(); i++) {
                        if (variables.get(i).getVariable().equals(programa.get(0).getLexema())) {
                            variableDuplicada = true;
                            indexDuplicada = i;
                        }
                    }
                    
                    if (declarandoVariables && variableDuplicada) {
                        variables.get(indexDuplicada).setDuplicada(true);
                    }
                    
                    else if (declarandoVariables && !variableDuplicada) {
                        Variable var = new Variable(programa.get(0).getLexema(), tipoDato, arrValores.toString(), true, false, variableDuplicada);
                        variables.add(var);
                        arrValores.clear();
                        
                        arrTiposDato.clear();
                    }
                    
                    //VARIABLE NO DECLARADA
                    else if (!declarandoVariables && !variableDuplicada) {
                        tipoDato = "-";
                        arrValores.clear();
                        
                        arrTiposDato.clear();
                        
                        Variable var = new Variable(programa.get(0).getLexema(), tipoDato, arrValores.toString(), false, false, variableDuplicada);
                        variables.add(var);
                        obteniendoValorVariable = false;
                    }

                    //VARIABLE UTILIZADA
                    else if (dentrodeExpresion) {
                        for (int i = 0; i < variables.size(); i++) {
                            if (variables.get(i).getVariable().equals(programa.get(0).getLexema())) {
                                variables.get(i).setUtilizada(true);
                                ta.setText(ta.getText() + "\n\tSE UTILIZÓ " + variables.get(i).getVariable());
                            }
                        }
                    }
                }
                
                if (num != compare) {// SI SON DISTINTAS ENTONCES EL ANALISIS DETERMINARA QUE ES INCORRECTO EL TEXTO INGRESADO
                    ComparaNT.remove(ComparaNT.size() - 1);// ELIMINARA AMBAS CON EL FIN DE SEGUIR VERIFICANDO EL RESTO, YA QUE POSIBLEMENTE SEA EL UNICO ERROR
                    et.remove(0);//AQUI TAMBIEN ELIMINA
                    
                    programa.remove(0);
                    
                    ta.setText(ta.getText() + "\n" + "¡ERROR! Tiene : " + compare + " Esperado: " + num);//AVISAMOS QUE HUBO UN ERROR
                    vista.isCorrect = false;//DESDE ESTE MOMENTO EL TEXTO ES INCORRECTO
                } else {//SI NO SON DISTINTOS, OSEA QUE SON IGUALES, ENTONCES SOLO LAS ELIMINAMOS PARA PROSEGUIR CON EL ANALISIS, AUN ES CORRECTO EL TEXTO
                    ComparaNT.remove(ComparaNT.size() - 1);
                    et.remove(0);
                    
                    programa.remove(0);
                }
                toRet.add(new Token(Integer.toString(num), Integer.toString(compare)));//CARGAMOS EL PAR DE NUMEROS A LA LISTA PARA MOSTRAR LAS COMPARACIONES
                //QUE SE HICIERON A LO LARGO DEL PROCESO EN LA TABLA
            }   

            //TERMINAL 
            catch (NumberFormatException x) {//ENTONCES SI LLEGAMOS AL CATCH, NO ERA UN NUMERO, SINO UN TERMINAL Y DEBEMOS ENTRAR A LA TABLA DE TRANSICIONES
                String trans = NT_inicial + "," + et.get(0);//VAMOS A OBTENER LA COORDENADA, DE LA FORMA -> (<NT>, ETIQUETA O NUMERO)                
                //NT_INICIAL SE CARGA CON EL ELEMENTO MAS A LA DERECHA DE LA LISTA DE COMPARACION Y ET.GET(0) CON EL PRIMER NUMERO
//                System.out.println(NT_inicial + " NT");

                if (Transicion.containsKey(trans)) {//SI LA COORDENADA OBTENIDA EXISTE EN LAS QUE DECLARAMOS, ENTONCES PROSEGUIMOS   
//                    System.out.println(trans + " encontrado");
                    String id = Transicion.get(trans).toString();//OBTENEMOS EL NUMERO DE NO TERMINAL PARA BUSCARLO EN SU TABLA CORRESPONDIENTE
                    if (id_NT.containsKey(id)) {//SI ESE NUMERO DEL NO TERMINAL REALMENTE EXISTE, ENTONCES PROSEGUIMOS
//                        System.out.println(id);
                        String NT_aux = id_NT.get(id).toString();//OBTENEMOS EL NOMBRE DEL NO TERMINAL, DE MODO QUE PODAMOS DIFERENCIAR NT1 DE NT2 POR EJEMPLO
                        if (NT_Content.containsKey(NT_aux)) {//SI ESE NOMBRE DEL NO TERMINAL EXISTE EN LA TABLA DE (NT, CONTENIDO) PROSEGUIMOS 
//                            System.out.println(NT_aux);
                            String[] content = (String[]) NT_Content.get(NT_aux);//AHORA OBTENEMOS EL CONTENIDO DEL NO TERMINAL QUE ES UN VECTOR DE STRING                                           
                            String NTi = ComparaNT.get(ComparaNT.size() - 1);//AHORA OBTENEMOS EL ULTIMO NO TERMINAL QUE COMPARAMOS DE LA LISTA ORIGINAL
                            String ETi = et.get(0);//OBTENEMOS LA PRIMER ETIQUETA
                            toRet.add(new Token(NTi, ETi));//CARGAMOS EL PAR COMPARADO A LA LISTA PARA MOSTRARLO EN LA TABLA DE COMPARACIONES EN LA VISTA
                            ComparaNT.remove(ComparaNT.size() - 1);//AHORA ELIMINAMOS EL ULTIMO NO TERMINAL QUE COMPARAMOS, YA QUE SERA REEMPLAZADO POR SU CONTENIDO
                            for (int j = content.length - 1; j >= 0; j--) {//VAMOS A RECORRER EL CONTENIDO DEL NO TERMINAL PARA CARGARLO AL REVÉS, SEGUN EL PROCESO
                                if (!content[j].equals("-")) {//SI SI CONTENIDO NO ES "-" CONTINUAMOS, YA QUE EL "-" INDICA UN VACIO, Y SI ES UN VACIO PUES NO ES NECESARIO CARGAR NADA
                                    ComparaNT.add(content[j]);//CARGAMOS EL CONTENIDO UNO A UNO, OJO! VA DEL ULTIMO AL PRIMERO
                                } else {
                                    break;//ESTE ES EL CASO EN QUE ENCUENTRA UN VACIO, YA QUE EL VACIO SE PIERDE DIRECTAMENTE
                                }
                            }
                        } else {
                            vista.isCorrect = false;//AVISAMOS A LA VISTA QUE EL TEXTO NO ES CORRECTO
                            break;
                        }
                    } else {
                        vista.isCorrect = false;//AVISAMOS A LA VISTA QUE EL TEXTO NO ES CORRECTO
                        break;
                    }
                } else {
                    vista.isCorrect = false;//AVISAMOS A LA VISTA QUE EL TEXTO NO ES CORRECTO
                    break;
                }
            }
        }
        if (!et.isEmpty() || !ComparaNT.isEmpty()) {
            vista.isCorrect = false;//AVISAMOS A LA VISTA QUE EL TEXTO NO ES CORRECTO
        }
        return toRet;
    }

    private ArrayList<String> obtenerEt(ArrayList<Token> input) {//OBTENEMOS LAS ETIQUETAS, OSEA SOLO EL NUMERO PARA INICIAR CON EL PROCESO DE ANALISIS SINTACTICO
        ArrayList<String> toRet = new ArrayList();
        input.stream().forEach((input1) -> {
            toRet.add(input1.getNumero());
        });
        return toRet;
    }

}
