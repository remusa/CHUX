package Semantico;

public class TokendeTabla {

    private final String token;
    private final String nombre;
    private final boolean declarada;
    private final boolean utilizada;
    private final boolean duplicada;

    public TokendeTabla(String token, String nombre, boolean declarada, boolean utilizada, boolean duplicada) {
        this.token = token;
        this.nombre = nombre;
        this.declarada = declarada;
        this.utilizada = utilizada;
        this.duplicada = duplicada;
    }

    public String getToken() {
        return token;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isDeclarada() {
        return declarada;
    }

    public boolean isUtilizada() {
        return utilizada;
    }
    
    public boolean isDuplicada() {
        return duplicada;
    }

}
