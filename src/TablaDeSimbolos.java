
import java.util.HashMap;

public class TablaDeSimbolos {
    public static enum enumTipos{
        TIPO_INT, TIPO_FLOAT, TIPO_CHAR, TIPO_STRING;
    }
    
    private class Tipo{
        enumTipos miTipo;

        public Tipo(enumTipos miTipo) {
            this.miTipo = miTipo;
        }
        
    }
    
    private HashMap<Integer,String> ts = null;
    private int n;
    
    
    public TablaDeSimbolos(){
        ts = new HashMap<>();
        n=0;
    }
    
    public void aniadirID(String nombre){
        ts.put(n, nombre);
        n++;
    }
    
    public String obtenerInformacionSimbolo(int n){
        return ts.get(n);
    }
}
