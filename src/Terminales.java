/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Josecarlos
 */
public interface Terminales {
    public static final int NUMERO_DE_TERMINALES = 45;
            
    public static final int FIN_DE_CADENA = -1;
    public static final int ID = 100;
    public static final int CONST_ENTERO = 101;
    public static final int CONST_REAL = 102;
    public static final int CONST_CHAR = 103;
    public static final int CONST_STRING = 104;
    public static final int PAL_RESERV_INT = 200;
    public static final int PAL_RESERV_DOUBLE = 201;
    public static final int PAL_RESERV_CHAR = 202;
    public static final int PAL_RESERV_STRING = 203;
    public static final int PAL_RESERV_BOOLEAN = 204;
    public static final int PAL_RESERV_VOID = 205;
    public static final int PAL_RESERV_DO = 206;
    public static final int PAL_RESERV_WHILE = 207;
    public static final int PAL_RESERV_TRUE = 208;
    public static final int PAL_RESERV_FALSE = 209;
    public static final int PAL_RESERV_IF = 210;
    public static final int PAL_RESERV_ELSE = 211;
    public static final int PAL_RESERV_RETURN = 212;
    public static final int PAL_RESERV_PUBLIC = 213;
    public static final int PAL_RESERV_PRIVATE = 214;
    public static final int PAL_RESERV_NEW = 215;
    public static final int PAL_RESERV_CLASS = 216;
    public static final int PAL_RESERV_NULL = 217;
    public static final int PAL_RESERV_FOR = 218;
    public static final int OPERADOR_SUMA = 300;
    public static final int OPERADOR_RESTA = 301;
    public static final int OPERADOR_MULT = 302;
    public static final int OPERADOR_DIV = 303;
    public static final int OPERADOR_MAYOR = 304;
    public static final int OPERADOR_MAYORIGUAL = 305;
    public static final int OPERADOR_MENOR = 306;
    public static final int OPERADOR_MENORIGUAL = 307;
    public static final int IGUAL = 308;
    public static final int OPERADOR_IGUALDAD = 309;
    public static final int OPERADOR_CONJUNCION = 310;
    public static final int OPERADOR_DISYUNCION = 311;
    public static final int PUNTOYCOMA = 400;
    public static final int PARENTESIS_APER = 401;
    public static final int PARENTESIS_CERR = 402;
    public static final int LLAVES_APER = 500;
    public static final int LLAVES_CERR = 501;
    public static final int CORCHETES_APER = 502;
    public static final int CORCHETES_CERR = 503;
    public static final int COMA = 504;
    public static final int PUNTO = 505;
    
    static public int buscarPalabra(String palabra){
        String palabrasReservadas[] = {"int","double","char","String","boolean",
            "void","do","while","true","false","if","else","return","public",
            "private","new","class","null","for"};
        
        for(int i=0;i<palabrasReservadas.length;i++){
            if(palabrasReservadas[i].equals(palabra)) return 200+i;
        }
        return 100;
    }
}
