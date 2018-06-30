public interface NoTerminales {
    public static final int NUMERO_DE_NO_TERMINALES = 39;
    
    public static final int _S = 1038;
    public static final int _MAS_S = 1000;
    public static final int _DEFCLASE = 1001;
    public static final int _ATRIB = 1002;
    public static final int _Z = 1003;
    public static final int _MAS_Z = 1004;
    public static final int _ASIG = 1005;
    public static final int _METODO = 1006;
    public static final int _E =1007;
    public static final int _PARAMETROS = 1008;
    public static final int _MAS_PARAMETROS =1009;
    public static final int _DESARROLLO = 1010;
    public static final int _LLAMADA =1011;
    public static final int _X = 1012;
    public static final int _MAS_K = 1013;
    public static final int _P =1014;
    public static final int _MAS_P = 1015;
    public static final int _BUCLE = 1016;
    public static final int _PRIM_FOR =1017;
    public static final int _SEG_FOR = 1018;
    public static final int _TER_FOR = 1019;
    public static final int _ASIG_FOR = 1020;
    public static final int _MAS_ASIG_FOR = 1021;
    public static final int _IF = 1022;
    public static final int _ELSE = 1023;
    public static final int _OTRO_IF = 1024;
    public static final int _OPERACION = 1025;
    public static final int _MAS_OPERACION =1026;
    public static final int _ARRAY = 1027;
    public static final int _TIPO_VAR = 1028;
    public static final int _PRIMITIVO = 1029;
    public static final int _TIPO_MET = 1030;
    public static final int _OPERADOR = 1031;
    public static final int _CONSTANTE = 1032;
    public static final int _T = 1033;
    public static final int _MAS_T = 1034;
    public static final int _M = 1035;
    public static final int _TIPO_ACCESO = 1036;
    public static final int _MAS_SEG_FOR = 1037;
    
    public default boolean esNoTerminal(int token){
        return token>=1000 && token<=1038;
    }
}
