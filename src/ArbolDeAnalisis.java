
import java.util.HashMap;
import java.util.LinkedList;

/*El siguiente es un arbol enhebrado a la derecha*/
public class ArbolDeAnalisis implements Gramatica{
    private NodoAnalisis raiz;
    private NodoAnalisis nodoActual;
    
    private class NodoAnalisis{
        private int tokenSimbolo;
        private NodoAnalisis primerHijo;
        private NodoAnalisis sig;

        public NodoAnalisis(int tokenSimbolo, NodoAnalisis sgte) {
            this.tokenSimbolo = tokenSimbolo;
            this.primerHijo = null;
            this.sig = sgte;
        }

        public void aniadirDerivado(int tokenSimbolo){
            if(this.primerHijo!=null){
                NodoAnalisis actual = this.primerHijo;
                while(actual.sig!=this){
                    actual = actual.sig;
                }
                actual.sig = new NodoAnalisis(tokenSimbolo, this);
            }else{
                this.primerHijo = new NodoAnalisis(tokenSimbolo, this);
            }
            
        }
    }
    
    
    public ArbolDeAnalisis(int primero){
        raiz = new NodoAnalisis(primero,null);
        nodoActual = raiz;
    }
    
    public void aniadirDerivacion(int[] tokens){
        for(int n:tokens){
            nodoActual.aniadirDerivado(n);
        }
        NodoAnalisis aux = nodoActual.primerHijo!=null ? nodoActual.primerHijo : nodoActual.sig;
        while(aux!=null && (!esNoTerminal(aux.tokenSimbolo) || aux == nodoActual)) aux = aux.sig;
        
        nodoActual = aux;
        if(aux==null)
            System.out.println("ESTE ES EL FIN DEL ARBOL");
    }

    /*El recorrido es postfijo*/
    @Override
    public String toString() {
        HashMap<Integer,String> imagenesToken = Gramatica.imagenesDeTokens();
        NodoAnalisis padreActual = raiz;
        NodoAnalisis aux = null;
        StringBuilder sb = new StringBuilder("ArbolDeAnalisis{");
        
        return sb.toString();
    }
    
    
}
