package agencias;

import java.util.HashMap;
import java.util.Map;

public class Agencia {

    private String numAgencia;
    
    public static Map<String, Agencia> mapaDeAgencias = new HashMap<>();
    
    public Agencia() {
        
    }
    
    public Agencia(String numAgencia) {
        this.numAgencia = numAgencia;
    }

    public String getNumAgencia() {
        return numAgencia;
    }

    public void setNumAgencia(String numAgencia) {
        this.numAgencia = numAgencia;
    }

    @Override
    public String toString() {
        return numAgencia;
    }     
}