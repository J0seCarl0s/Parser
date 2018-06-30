
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class TAS implements Gramatica{
    private HashMap<Interseccion,Integer> tabla;
    
    private class Interseccion{
        private int noTerminal;
        private int terminal;

        public Interseccion(int noTerminal, int terminal) {
            this.noTerminal = noTerminal;
            this.terminal = terminal;
        }
        
        @Override
        public boolean equals(Object otro){
            if(otro instanceof Interseccion){
                return this.noTerminal == ((Interseccion) otro).noTerminal && 
                        this.terminal == ((Interseccion) otro).terminal;
            }else return false;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 23 * hash + this.noTerminal;
            hash = 23 * hash + this.terminal;
            return hash;
        }

        @Override
        public String toString() {
            return "Interseccion{" + "noTerminal=" + noTerminal + ", terminal=" + terminal + '}';
        }
        
        
    }
    
    public TAS(){
        iniciarTabla();
    }
    
    
    public boolean existeInterseccion(int noTerminal, int terminal){
        return tabla.containsKey(new Interseccion(noTerminal, terminal));
    }
    
    public int[] obtenerProduccion(int noTerminal, int terminal){
        Interseccion interseccion = new Interseccion(noTerminal, terminal);
        if(tabla.containsKey(interseccion)){
            int numProduc = tabla.get(interseccion);
            return PRODUCCIONES[numProduc];
        }
        return null;
    }
    
    private void iniciarTabla(){
        tabla = new HashMap<>(244);
        tabla.put(new Interseccion(_S, PAL_RESERV_PUBLIC),0);
        tabla.put(new Interseccion(_S, PAL_RESERV_PRIVATE),0);
        tabla.put(new Interseccion(_MAS_S, PAL_RESERV_PUBLIC),1);
        tabla.put(new Interseccion(_MAS_S, PAL_RESERV_PRIVATE),1);
        tabla.put(new Interseccion(_MAS_S, FIN_DE_CADENA),2);
        tabla.put(new Interseccion(_DEFCLASE, ID),3);
        tabla.put(new Interseccion(_DEFCLASE, PAL_RESERV_INT),3);
        tabla.put(new Interseccion(_DEFCLASE, PAL_RESERV_DOUBLE),3);
        tabla.put(new Interseccion(_DEFCLASE, PAL_RESERV_CHAR),3);
        tabla.put(new Interseccion(_DEFCLASE, PAL_RESERV_STRING),3);
        tabla.put(new Interseccion(_DEFCLASE, PAL_RESERV_BOOLEAN),3);
        tabla.put(new Interseccion(_DEFCLASE, PAL_RESERV_PUBLIC),4);
        tabla.put(new Interseccion(_DEFCLASE, PAL_RESERV_PRIVATE),4);
        tabla.put(new Interseccion(_DEFCLASE, LLAVES_CERR),5);
        tabla.put(new Interseccion(_ATRIB, ID),6);
        tabla.put(new Interseccion(_ATRIB, PAL_RESERV_INT),6);
        tabla.put(new Interseccion(_ATRIB, PAL_RESERV_DOUBLE),6);
        tabla.put(new Interseccion(_ATRIB, PAL_RESERV_CHAR),6);
        tabla.put(new Interseccion(_ATRIB, PAL_RESERV_STRING),6);
        tabla.put(new Interseccion(_ATRIB, PAL_RESERV_BOOLEAN),6);
        tabla.put(new Interseccion(_Z, ID),7);
        tabla.put(new Interseccion(_MAS_Z, COMA),8);
        tabla.put(new Interseccion(_MAS_Z, PUNTOYCOMA),9);
        tabla.put(new Interseccion(_ASIG, IGUAL),10);
        tabla.put(new Interseccion(_ASIG, COMA),11);
        tabla.put(new Interseccion(_ASIG, PUNTOYCOMA),11);
        tabla.put(new Interseccion(_METODO, PAL_RESERV_PUBLIC),12);
        tabla.put(new Interseccion(_METODO, PAL_RESERV_PRIVATE),12);
        tabla.put(new Interseccion(_E, ID),13);
        tabla.put(new Interseccion(_E, PAL_RESERV_INT),13);
        tabla.put(new Interseccion(_E, PAL_RESERV_DOUBLE),13);
        tabla.put(new Interseccion(_E, PAL_RESERV_CHAR),13);
        tabla.put(new Interseccion(_E, PAL_RESERV_STRING),13);
        tabla.put(new Interseccion(_E, PAL_RESERV_BOOLEAN),13);
        tabla.put(new Interseccion(_E, PAL_RESERV_VOID),13);
        tabla.put(new Interseccion(_E, ID),13);
        tabla.put(new Interseccion(_PARAMETROS, PAL_RESERV_INT),14);
        tabla.put(new Interseccion(_PARAMETROS, PAL_RESERV_DOUBLE),14);
        tabla.put(new Interseccion(_PARAMETROS, PAL_RESERV_STRING),14);
        tabla.put(new Interseccion(_PARAMETROS, PAL_RESERV_CHAR),14);
        tabla.put(new Interseccion(_PARAMETROS, PAL_RESERV_BOOLEAN),14);
        tabla.put(new Interseccion(_PARAMETROS, PARENTESIS_CERR), 15);
        tabla.put(new Interseccion(_MAS_PARAMETROS, COMA), 16);
        tabla.put(new Interseccion(_MAS_PARAMETROS, PARENTESIS_CERR), 17);
        tabla.put(new Interseccion(_DESARROLLO, ID), 18);
        tabla.put(new Interseccion(_DESARROLLO, PAL_RESERV_INT), 19);
        tabla.put(new Interseccion(_DESARROLLO, PAL_RESERV_DOUBLE), 19);
        tabla.put(new Interseccion(_DESARROLLO, PAL_RESERV_CHAR), 19);
        tabla.put(new Interseccion(_DESARROLLO, PAL_RESERV_STRING), 19);
        tabla.put(new Interseccion(_DESARROLLO, PAL_RESERV_BOOLEAN), 19);
        tabla.put(new Interseccion(_DESARROLLO, PAL_RESERV_DO), 20);
        tabla.put(new Interseccion(_DESARROLLO, PAL_RESERV_WHILE), 20);
        tabla.put(new Interseccion(_DESARROLLO, PAL_RESERV_FOR), 20);
        tabla.put(new Interseccion(_DESARROLLO, PAL_RESERV_IF), 21);
        tabla.put(new Interseccion(_DESARROLLO, PAL_RESERV_RETURN), 22);
        tabla.put(new Interseccion(_DESARROLLO, LLAVES_CERR), 23);
        tabla.put(new Interseccion(_LLAMADA, ID), 24);
        tabla.put(new Interseccion(_LLAMADA, IGUAL), 25);
        tabla.put(new Interseccion(_LLAMADA, PUNTOYCOMA), 25);
        tabla.put(new Interseccion(_LLAMADA, PARENTESIS_APER), 25);
        tabla.put(new Interseccion(_LLAMADA, CORCHETES_APER), 25);
        tabla.put(new Interseccion(_LLAMADA, PUNTO), 25);
        tabla.put(new Interseccion(_X, IGUAL), 27);
        tabla.put(new Interseccion(_X, PUNTOYCOMA), 27);
        tabla.put(new Interseccion(_X, PARENTESIS_APER), 26);
        tabla.put(new Interseccion(_X, CORCHETES_APER), 27);
        tabla.put(new Interseccion(_X, PUNTO), 27);
        tabla.put(new Interseccion(_MAS_K, PUNTO), 28);
        tabla.put(new Interseccion(_MAS_K, IGUAL), 29);
        tabla.put(new Interseccion(_MAS_K, PUNTOYCOMA), 29);
        tabla.put(new Interseccion(_P, ID), 30);
        tabla.put(new Interseccion(_P, CONST_ENTERO), 30);
        tabla.put(new Interseccion(_P, CONST_CHAR), 30);
        tabla.put(new Interseccion(_P, CONST_REAL), 30);
        tabla.put(new Interseccion(_P, CONST_STRING), 30);
        tabla.put(new Interseccion(_P, PAL_RESERV_TRUE), 30);
        tabla.put(new Interseccion(_P, PAL_RESERV_FALSE), 30);
        tabla.put(new Interseccion(_P, PAL_RESERV_NEW), 30);
        tabla.put(new Interseccion(_P, PAL_RESERV_NULL), 30);
        tabla.put(new Interseccion(_P, PARENTESIS_APER), 30);
        tabla.put(new Interseccion(_P, PARENTESIS_CERR), 31);
        tabla.put(new Interseccion(_MAS_P, COMA), 32);
        tabla.put(new Interseccion(_MAS_P, PARENTESIS_CERR), 33);
        tabla.put(new Interseccion(_BUCLE, PAL_RESERV_DO), 35);
        tabla.put(new Interseccion(_BUCLE, PAL_RESERV_WHILE), 34);
        tabla.put(new Interseccion(_BUCLE, PAL_RESERV_FOR), 36);
        tabla.put(new Interseccion(_PRIM_FOR, PAL_RESERV_INT), 37);
        tabla.put(new Interseccion(_PRIM_FOR, PAL_RESERV_DOUBLE), 37);
        tabla.put(new Interseccion(_PRIM_FOR, PAL_RESERV_CHAR), 37);
        tabla.put(new Interseccion(_PRIM_FOR, PAL_RESERV_STRING), 37);
        tabla.put(new Interseccion(_PRIM_FOR, PAL_RESERV_BOOLEAN), 37);
        tabla.put(new Interseccion(_PRIM_FOR, PUNTOYCOMA), 38);
        tabla.put(new Interseccion(_SEG_FOR, ID), 39);
        tabla.put(new Interseccion(_SEG_FOR, CONST_ENTERO), 39);
        tabla.put(new Interseccion(_SEG_FOR, CONST_CHAR), 39);
        tabla.put(new Interseccion(_SEG_FOR, CONST_REAL), 39);
        tabla.put(new Interseccion(_SEG_FOR, CONST_STRING), 39);
        tabla.put(new Interseccion(_SEG_FOR, PAL_RESERV_TRUE), 39);
        tabla.put(new Interseccion(_SEG_FOR, PAL_RESERV_FALSE), 39);
        tabla.put(new Interseccion(_SEG_FOR, PAL_RESERV_NEW), 39);
        tabla.put(new Interseccion(_SEG_FOR, PAL_RESERV_NULL), 39);
        tabla.put(new Interseccion(_SEG_FOR, PARENTESIS_APER), 39);
        tabla.put(new Interseccion(_SEG_FOR, PUNTOYCOMA), 40);
        tabla.put(new Interseccion(_TER_FOR, ID), 41);
        tabla.put(new Interseccion(_TER_FOR, PARENTESIS_CERR), 42);
        tabla.put(new Interseccion(_ASIG_FOR, ID), 43);
        tabla.put(new Interseccion(_MAS_ASIG_FOR, COMA), 44);
        tabla.put(new Interseccion(_MAS_ASIG_FOR, PARENTESIS_CERR), 45);
        tabla.put(new Interseccion(_IF, PAL_RESERV_IF), 46);
        tabla.put(new Interseccion(_ELSE, ID), 48);
        tabla.put(new Interseccion(_ELSE, PAL_RESERV_INT), 48);
        tabla.put(new Interseccion(_ELSE, PAL_RESERV_DOUBLE), 48);
        tabla.put(new Interseccion(_ELSE, PAL_RESERV_CHAR), 48);
        tabla.put(new Interseccion(_ELSE, PAL_RESERV_STRING), 48);
        tabla.put(new Interseccion(_ELSE, PAL_RESERV_BOOLEAN), 48);
        tabla.put(new Interseccion(_ELSE, PAL_RESERV_DO), 48);
        tabla.put(new Interseccion(_ELSE, PAL_RESERV_WHILE), 48);
        tabla.put(new Interseccion(_ELSE, PAL_RESERV_IF), 48);
        tabla.put(new Interseccion(_ELSE, PAL_RESERV_RETURN), 48);
        tabla.put(new Interseccion(_ELSE, PAL_RESERV_FOR), 48);
        tabla.put(new Interseccion(_ELSE, PAL_RESERV_ELSE), 47);
        tabla.put(new Interseccion(_OTRO_IF, PAL_RESERV_IF), 49);
        tabla.put(new Interseccion(_OTRO_IF, LLAVES_APER), 50);
        tabla.put(new Interseccion(_OPERACION, ID), 51);
        tabla.put(new Interseccion(_OPERACION, CONST_ENTERO), 51);
        tabla.put(new Interseccion(_OPERACION, CONST_CHAR), 51);
        tabla.put(new Interseccion(_OPERACION, CONST_REAL), 51);
        tabla.put(new Interseccion(_OPERACION, CONST_STRING), 51);
        tabla.put(new Interseccion(_OPERACION, PAL_RESERV_TRUE), 51);
        tabla.put(new Interseccion(_OPERACION, PAL_RESERV_FALSE), 51);
        tabla.put(new Interseccion(_OPERACION, PAL_RESERV_NEW), 51);
        tabla.put(new Interseccion(_OPERACION, PAL_RESERV_NULL), 51);
        tabla.put(new Interseccion(_OPERACION, PARENTESIS_APER), 52);
        tabla.put(new Interseccion(_MAS_OPERACION, OPERADOR_SUMA), 53);
        tabla.put(new Interseccion(_MAS_OPERACION, OPERADOR_RESTA), 53);
        tabla.put(new Interseccion(_MAS_OPERACION, OPERADOR_MULT), 53);
        tabla.put(new Interseccion(_MAS_OPERACION, OPERADOR_DIV), 53);
        tabla.put(new Interseccion(_MAS_OPERACION, OPERADOR_MAYOR), 53);
        tabla.put(new Interseccion(_MAS_OPERACION, OPERADOR_MAYORIGUAL), 53);
        tabla.put(new Interseccion(_MAS_OPERACION, OPERADOR_MENOR), 53);
        tabla.put(new Interseccion(_MAS_OPERACION, OPERADOR_MENORIGUAL), 53);
        tabla.put(new Interseccion(_MAS_OPERACION, OPERADOR_IGUALDAD), 53);
        tabla.put(new Interseccion(_MAS_OPERACION, OPERADOR_CONJUNCION), 53);
        tabla.put(new Interseccion(_MAS_OPERACION, OPERADOR_DISYUNCION), 53);
        tabla.put(new Interseccion(_MAS_OPERACION, PUNTOYCOMA), 54);
        tabla.put(new Interseccion(_MAS_OPERACION, PARENTESIS_CERR), 54);
        tabla.put(new Interseccion(_MAS_OPERACION, COMA), 54);
        tabla.put(new Interseccion(_MAS_OPERACION, CORCHETES_CERR), 54);
        tabla.put(new Interseccion(_ARRAY, ID), 56);
        tabla.put(new Interseccion(_ARRAY, OPERADOR_SUMA), 56);
        tabla.put(new Interseccion(_ARRAY, OPERADOR_RESTA), 56);
        tabla.put(new Interseccion(_ARRAY, OPERADOR_MULT), 56);
        tabla.put(new Interseccion(_ARRAY, OPERADOR_DIV), 56);
        tabla.put(new Interseccion(_ARRAY, OPERADOR_MAYOR), 56);
        tabla.put(new Interseccion(_ARRAY, OPERADOR_MAYORIGUAL), 56);
        tabla.put(new Interseccion(_ARRAY, OPERADOR_MENOR), 56);
        tabla.put(new Interseccion(_ARRAY, OPERADOR_MENORIGUAL), 56);
        tabla.put(new Interseccion(_ARRAY, IGUAL), 56);
        tabla.put(new Interseccion(_ARRAY, OPERADOR_IGUALDAD), 56);
        tabla.put(new Interseccion(_ARRAY, OPERADOR_CONJUNCION), 56);
        tabla.put(new Interseccion(_ARRAY, OPERADOR_DISYUNCION), 56);
        tabla.put(new Interseccion(_ARRAY, PUNTOYCOMA), 56);
        tabla.put(new Interseccion(_ARRAY, PARENTESIS_APER), 56);
        tabla.put(new Interseccion(_ARRAY, PARENTESIS_CERR), 56);
        tabla.put(new Interseccion(_ARRAY, COMA), 56);
        tabla.put(new Interseccion(_ARRAY, CORCHETES_APER), 55);
        tabla.put(new Interseccion(_ARRAY, CORCHETES_CERR), 56);
        tabla.put(new Interseccion(_ARRAY, PUNTO), 56);
        tabla.put(new Interseccion(_TIPO_VAR, ID), 57);
        tabla.put(new Interseccion(_TIPO_VAR, PAL_RESERV_INT), 58);
        tabla.put(new Interseccion(_TIPO_VAR, PAL_RESERV_DOUBLE), 58);
        tabla.put(new Interseccion(_TIPO_VAR, PAL_RESERV_CHAR), 58);
        tabla.put(new Interseccion(_TIPO_VAR, PAL_RESERV_STRING), 58);
        tabla.put(new Interseccion(_TIPO_VAR, PAL_RESERV_BOOLEAN), 58);
        tabla.put(new Interseccion(_PRIMITIVO, PAL_RESERV_INT), 59);
        tabla.put(new Interseccion(_PRIMITIVO, PAL_RESERV_DOUBLE), 60);
        tabla.put(new Interseccion(_PRIMITIVO, PAL_RESERV_CHAR), 61);
        tabla.put(new Interseccion(_PRIMITIVO, PAL_RESERV_STRING), 62);
        tabla.put(new Interseccion(_PRIMITIVO, PAL_RESERV_BOOLEAN), 63);
        tabla.put(new Interseccion(_TIPO_MET, PAL_RESERV_INT), 64);
        tabla.put(new Interseccion(_TIPO_MET, PAL_RESERV_DOUBLE), 64);
        tabla.put(new Interseccion(_TIPO_MET, PAL_RESERV_CHAR), 64);
        tabla.put(new Interseccion(_TIPO_MET, PAL_RESERV_STRING), 64);
        tabla.put(new Interseccion(_TIPO_MET, PAL_RESERV_BOOLEAN), 64);
        tabla.put(new Interseccion(_TIPO_MET, PAL_RESERV_VOID), 65);
        tabla.put(new Interseccion(_OPERADOR, OPERADOR_SUMA), 66);
        tabla.put(new Interseccion(_OPERADOR, OPERADOR_RESTA), 67);
        tabla.put(new Interseccion(_OPERADOR, OPERADOR_MULT), 68);
        tabla.put(new Interseccion(_OPERADOR, OPERADOR_DIV), 69);
        tabla.put(new Interseccion(_OPERADOR, OPERADOR_MAYOR), 70);
        tabla.put(new Interseccion(_OPERADOR, OPERADOR_MAYORIGUAL), 71);
        tabla.put(new Interseccion(_OPERADOR, OPERADOR_MENOR), 72);
        tabla.put(new Interseccion(_OPERADOR, OPERADOR_MENORIGUAL), 73);
        tabla.put(new Interseccion(_OPERADOR, OPERADOR_IGUALDAD), 74);
        tabla.put(new Interseccion(_OPERADOR, OPERADOR_CONJUNCION), 75);
        tabla.put(new Interseccion(_OPERADOR, OPERADOR_DISYUNCION), 76);
        tabla.put(new Interseccion(_CONSTANTE, ID), 85);
        tabla.put(new Interseccion(_CONSTANTE, CONST_ENTERO), 77);
        tabla.put(new Interseccion(_CONSTANTE, CONST_REAL), 78);
        tabla.put(new Interseccion(_CONSTANTE, CONST_STRING), 79);
        tabla.put(new Interseccion(_CONSTANTE, CONST_CHAR), 80);
        tabla.put(new Interseccion(_CONSTANTE, PAL_RESERV_TRUE), 81);
        tabla.put(new Interseccion(_CONSTANTE, PAL_RESERV_FALSE), 82);
        tabla.put(new Interseccion(_CONSTANTE, PAL_RESERV_NEW), 83);
        tabla.put(new Interseccion(_CONSTANTE, PAL_RESERV_NULL), 84);
        tabla.put(new Interseccion(_T, ID), 86);
        tabla.put(new Interseccion(_MAS_T, OPERADOR_SUMA), 88);
        tabla.put(new Interseccion(_MAS_T, OPERADOR_RESTA), 88);
        tabla.put(new Interseccion(_MAS_T, OPERADOR_MULT), 88);
        tabla.put(new Interseccion(_MAS_T, OPERADOR_DIV), 88);
        tabla.put(new Interseccion(_MAS_T, OPERADOR_MAYOR), 88);
        tabla.put(new Interseccion(_MAS_T, OPERADOR_MAYORIGUAL), 88);
        tabla.put(new Interseccion(_MAS_T, OPERADOR_MENOR), 88);
        tabla.put(new Interseccion(_MAS_T, OPERADOR_MENORIGUAL), 88);
        tabla.put(new Interseccion(_MAS_T, OPERADOR_IGUALDAD), 88);
        tabla.put(new Interseccion(_MAS_T, OPERADOR_CONJUNCION), 88);
        tabla.put(new Interseccion(_MAS_T, OPERADOR_DISYUNCION), 88);
        tabla.put(new Interseccion(_MAS_T, PUNTOYCOMA), 88);
        tabla.put(new Interseccion(_MAS_T, PARENTESIS_CERR), 88);
        tabla.put(new Interseccion(_MAS_T, COMA), 88);
        tabla.put(new Interseccion(_MAS_T, CORCHETES_CERR), 88);
        tabla.put(new Interseccion(_MAS_T, PUNTO), 87);
        tabla.put(new Interseccion(_M, OPERADOR_SUMA), 90);
        tabla.put(new Interseccion(_M, OPERADOR_RESTA), 90);
        tabla.put(new Interseccion(_M, OPERADOR_MULT), 90);
        tabla.put(new Interseccion(_M, OPERADOR_DIV), 90);
        tabla.put(new Interseccion(_M, OPERADOR_MAYOR), 90);
        tabla.put(new Interseccion(_M, OPERADOR_MAYORIGUAL), 90);
        tabla.put(new Interseccion(_M, OPERADOR_MENOR), 90);
        tabla.put(new Interseccion(_M, OPERADOR_MENORIGUAL), 90);
        tabla.put(new Interseccion(_M, OPERADOR_IGUALDAD), 90);
        tabla.put(new Interseccion(_M, OPERADOR_CONJUNCION), 90);
        tabla.put(new Interseccion(_M, OPERADOR_DISYUNCION), 90);
        tabla.put(new Interseccion(_M, PUNTOYCOMA), 90);
        tabla.put(new Interseccion(_M, PARENTESIS_CERR), 90);
        tabla.put(new Interseccion(_M, COMA), 90);
        tabla.put(new Interseccion(_M, CORCHETES_CERR), 90);
        tabla.put(new Interseccion(_M, PUNTO), 90);
        tabla.put(new Interseccion(_M, PARENTESIS_APER), 89);
        tabla.put(new Interseccion(_M, CORCHETES_APER), 90);
        tabla.put(new Interseccion(_TIPO_ACCESO, PAL_RESERV_PUBLIC), 91);
        tabla.put(new Interseccion(_TIPO_ACCESO, PAL_RESERV_PRIVATE), 92);
        tabla.put(new Interseccion(_MAS_SEG_FOR, COMA), 93);
        tabla.put(new Interseccion(_MAS_SEG_FOR, PUNTOYCOMA), 94);
       
    }
    
}
