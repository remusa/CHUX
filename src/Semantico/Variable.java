package Semantico;

/**
 *
 * @author Javier
 */
public class Variable {

    private String variable;
    private String tipoDato;
    private String valor;
    private boolean declarada;
    private boolean utilizada;
    private boolean duplicada;

    public Variable(String variable) {
        this.variable = variable;
    }

    public Variable(String variable, String tipoDato, String valor, boolean declarada, boolean utilizada, boolean duplicada) {
        this.variable = variable;
        this.tipoDato = tipoDato;
        this.valor = valor;
        this.declarada = declarada;
        this.utilizada = utilizada;
        this.duplicada = duplicada;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public String getTipoDato() {
        return tipoDato;
    }

    public void setTipoDato(String tipoDato) {
        this.tipoDato = tipoDato;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public boolean isDeclarada() {
        return declarada;
    }

    public void setDeclarada(boolean declarada) {
        this.declarada = declarada;
    }

    public boolean isUtilizada() {
        return utilizada;
    }

    public void setUtilizada(boolean utilizada) {
        this.utilizada = utilizada;
    }

    public boolean isDuplicada() {
        return duplicada;
    }

    public void setDuplicada(boolean duplicada) {
        this.duplicada = duplicada;
    }

}
