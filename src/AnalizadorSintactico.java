
import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;

public class AnalizadorSintactico{
    public static int ANALISIS_CORRECTO = 0;
    public static int ERROR_SINTACTICO = 1;
    public static int ERROR_LEXICO = 2;
    
    private AnalizadorLexico analizadorLexico = null;
    private HashMap<Integer,String> imagenesToken = null;
    private ArbolDeAnalisis arbol = null;
    private TablaDeSimbolos tablaSimbolos = null;
    
    private TAS tas = null;

    public AnalizadorSintactico(String cadena) {
        tablaSimbolos = new TablaDeSimbolos();
        analizadorLexico = new AnalizadorLexico(cadena);
        tas = new TAS();
        imagenesToken = Gramatica.imagenesDeTokens();
    }
    
    public int hacerAnalisisSintactico(){
        Stack<Integer> pila = new Stack<>();
        pila.add(Terminales.FIN_DE_CADENA);
        pila.add(NoTerminales._S);
        arbol = new ArbolDeAnalisis(NoTerminales._S);
        
        int tokenActual = analizadorLexico.siguienteToken();
        
        while(tokenActual!=Terminales.FIN_DE_CADENA || tas.existeInterseccion(pila.peek(), tokenActual)){
            if(tokenActual == AnalizadorLexico.NO_IDENTIFICADO){
                System.out.println("ERROR LEXICO");
                return ERROR_LEXICO;
            }
            
            int cimaPila = pila.peek();
            System.out.println(obtenerMemoria(pila.iterator(), tokenActual));
            if(cimaPila == tokenActual){
                pila.pop();
                tokenActual = analizadorLexico.siguienteToken();
            }else if(tas.existeInterseccion(cimaPila, tokenActual)){
                pila.pop();
                int[] produccion = tas.obtenerProduccion(cimaPila, tokenActual);
                arbol.aniadirDerivacion(produccion);
            }else{
                System.out.println("ERROR SINTACTICO");
                return ERROR_SINTACTICO;
            }
        }
        if(!pila.empty() && pila.peek() == Terminales.FIN_DE_CADENA) pila.pop();
        
        System.out.println(obtenerMemoria(pila.iterator(), tokenActual));
        return pila.empty() ? ANALISIS_CORRECTO : ERROR_SINTACTICO;
    }
    
   private String obtenerMemoria(Iterator<Integer> itPila, int tokenActual){
        StringBuilder memoria = new StringBuilder();
        memoria.append("Pila: ¿ ");
        itPila.forEachRemaining((t)->memoria.append(imagenesToken.get(t)).append(" , "));
        memoria.delete(memoria.length()-3, memoria.length()-1);
        memoria.append(" ? TOKEN: ").append(imagenesToken.get(tokenActual));
        return memoria.toString();
    }
   
    public int ultimaLineaLeida(){
        return analizadorLexico.getLineaActual();
    }
    
    public ArbolDeAnalisis getArbolDeAnalisis(){
        return arbol;
    }
   
    public static void main(String[] args) {
        String prueba = "public class x {"
                + " int x[3+2][a] = y[3] + 2;"
                + "public int sumar(int x, int y){"
                + " for(double c=4;c<2;c=c+3){"
                + " if(a>2){} else {"
                + "     a = 32;} "
                + "}"
                + "return a;"
                + "}"
                + "}"
                + "class y {x var = new x(); public void y(int a,double b, String cad){"
                + "Object ob = new Object();"
                + " this.a = a; this.b = b; this.cad = cad + this.b + b.toString().length;"
                + " metodo();"
                + "}"
                + "}";
        
        AnalizadorSintactico analizador = new AnalizadorSintactico(prueba);
        System.out.println(analizador.hacerAnalisisSintactico());
        System.out.println("ARBOL");
        System.out.println(analizador.getArbolDeAnalisis());
    }
}
