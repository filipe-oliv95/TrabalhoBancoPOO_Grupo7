package agencias;

public class Agencia {

    private String numAgencia;
    
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