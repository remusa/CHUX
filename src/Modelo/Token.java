package Modelo;

public class Token {

    private final String lexema;
    private final String numero;

    public Token(String n, String lex) {
        lexema = lex;
        numero = n;
    }

    public String getLexema() {
        return lexema;
    }

    public String getNumero() {
        return numero;
    }

}
