package Modelo;

import java.util.Hashtable;

public class Automata {

    //65-90
    //97-122
    private Hashtable num = new Hashtable();

    public Automata() {
        for (int i = 0; i < 10; i++) {
            num.put(Integer.toString(i).charAt(0), i);
        }
    }

    public int AutomataGral(String compare) {
        char init = compare.charAt(0);
        if (init == '"') { //cadena
            if (compare.charAt(compare.length() - 1) == '"' && compare.length() > 1) {
                return 51;
            }
            return 101;
        } else if (init == '´') { //caracter
            if (compare.charAt(compare.length() - 1) == '´' && compare.length() == 1) {
                return 54;
            }
            return 101;
        } else if (num.containsKey(init)) {//número                        
            if (compare.contains(".")) {//double
                if (compare.charAt(compare.length() - 1) == '.') {
                    return 103;
                }
                try {
                    Double.parseDouble(compare);
                    return 53;
                } catch (NumberFormatException e) {
                    return 103;
                }
            } else {//entero
                try {
                    Integer.parseInt(compare);
                    return 52;
                } catch (NumberFormatException e) {
                    return 102;
                }
            }
        } else if ((init >= 65 && init <= 90) || (init >= 97 && init <= 122)) {
            char tmp;
            for (int i = 0; i < compare.length(); i++) {
                tmp = compare.charAt(i);
                if (!((tmp >= 65 && tmp <= 90) || (tmp >= 97 && tmp <= 122))) {
                    return 100;
                }
            }
            return 50;
        } else {
            return 104;
        }
    }
}
