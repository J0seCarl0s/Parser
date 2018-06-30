
import java.util.HashMap;

public interface Gramatica extends Terminales, NoTerminales{
    /**
     * Cada produccion es representada por un arreglo. El conjunto de todas
     * las producciones conforma otro arreglo. Solo se almacena la parte derecha
     * de las producciones
     */
    public static final int[][] PRODUCCIONES = {
        //0. S  <TIPO_ACCESP> class id { <DEFCLASE> } MAS_S
        {_TIPO_ACCESO, PAL_RESERV_CLASS, ID, LLAVES_APER, _DEFCLASE, LLAVES_CERR, _MAS_S},
        
        //1. MAS_S  class id { <DEFCLASE> } MAS_S
        {_TIPO_ACCESO, PAL_RESERV_CLASS, ID, LLAVES_APER, _DEFCLASE, LLAVES_CERR, _MAS_S},
        //2. MAS_S  λ
        {},
        //3. DEFCLASE  <ATRIB> ; <DEFCLASE>
        {_ATRIB, PUNTOYCOMA, _DEFCLASE},                                   
        //4.	DEFCLASE <METODO> <DEFCLASE>
        {_METODO, _DEFCLASE},
        //5.	DEFCLASE  λ
        {},
        //6.	ATRIB  <TIPO_VAR> <Z> <MAS_Z> 
        {_TIPO_VAR, _Z, _MAS_Z},
        //7.	Z  id <ARRAY> <ASIG>
        {ID, _ARRAY, _ASIG},
        //8.	MAS_Z  , <Z> <MAS_Z>
        {COMA, _Z, _MAS_Z},
        //9.	MAS_Z  λ
        {},
        //10.	ASIG  = <OPERACION>
        {IGUAL, _OPERACION},
        //11.	ASIG λ
        {},
        //12.	METODO  <TIPO_ACCESO> <E>
        {_TIPO_ACCESO, _E},
        //13.	E  <TIPO_MET> id ( <PARAMETROS> ) { <DESARROLLO>}
        {_TIPO_MET , ID, PARENTESIS_APER, _PARAMETROS, 
                PARENTESIS_CERR, LLAVES_APER, _DESARROLLO, LLAVES_CERR },
        //14.	PARAMETROS  <TIPO_VAR> id <MAS_PARAMETROS>
        {_TIPO_VAR, ID, _MAS_PARAMETROS},
        //15.	PARAMETROS  λ
        {},
        //16.	MAS_PARAMETROS  , <TIPO_VAR> id <MAS_PARAMETROS>
        {COMA, _TIPO_VAR, ID, _MAS_PARAMETROS},
        //17.	MAS_PARAMETROS  λ
        {},
        //18.	DESARROLLO  id <LLAMADA> ; <DESARROLLO>
        {ID, _LLAMADA, PUNTOYCOMA, _DESARROLLO},
        //19.	DESARROLLO  <PRIMITIVO> <Z> <MAS_Z> ; <DESARROLLO>
        {_PRIMITIVO, _Z, _MAS_Z, PUNTOYCOMA, _DESARROLLO},
        //20.	DESARROLLO  <BUCLE> <DESARROLLO>
        {_BUCLE, _DESARROLLO},
        //21.	DESARROLLO  <IF> <DESARROLLO> 
        {_IF, _DESARROLLO},
        //22.	DESARROLLO  return <OPERACION> ;
        {PAL_RESERV_RETURN, _OPERACION, PUNTOYCOMA},
        //23.	DESARROLLO  λ
        {},
        //24.	LLAMADA  <Z> <MAS_Z>
        {_Z,_MAS_Z},
        //25.	LLAMADA  <X> 
        {_X},
        //26.	X  ( <P> ) <MAS_T>
        {PARENTESIS_APER, _P, PARENTESIS_CERR, _MAS_T},
        //27.	X   <ARRAY> <MAS_K>
        {_ARRAY, _MAS_K},
        //28.	MAS_K  . id <X> 
        {PUNTO, ID, _X},
        //29.	MAS_K  <ASIG>
        {_ASIG},
        //30.	P  <OPERACION> <MAS_P>
        {_OPERACION, _MAS_P},
        //31.	P  λ
        {},
        //32.	MAS_P  , <OPERACION > <MAS_P>
        {COMA, _OPERACION, _MAS_P},
        //33.	MAS_P  λ 
        {},
        //34.	BUCLE  while (<OPERACION>) {<DESARROLLO>}
        {PAL_RESERV_WHILE, PARENTESIS_APER, _OPERACION, PARENTESIS_CERR
                ,LLAVES_APER, _DESARROLLO, LLAVES_CERR},
        //35.	BUCLE  do {<DESARROLLO>} while(<OPERACION>) ;
        {PAL_RESERV_DO,LLAVES_APER, _DESARROLLO, LLAVES_CERR, PAL_RESERV_WHILE,
                PARENTESIS_APER, _OPERACION, PARENTESIS_CERR, PUNTOYCOMA},
        //36.	BUCLE  for(<PRIM_FOR> ; <SEG_FOR> ; <TER_FOR>){<DESARROLLO>}
        {PAL_RESERV_FOR, PARENTESIS_APER, _PRIM_FOR, PUNTOYCOMA,
                _SEG_FOR, PUNTOYCOMA, _TER_FOR, PARENTESIS_CERR, LLAVES_APER,
                _DESARROLLO, LLAVES_CERR},
        //37.	PRIM_FOR  <ATRIB>
        {_ATRIB},
        //38.	PRIM_FOR  λ 
        {},
        //39.	SEG_FOR  <OPERACION> <MAS_SEG_FOR>
        {_OPERACION, _MAS_SEG_FOR},
        //40.	SEG_FOR  λ
        {},
        //41.	TER_FOR  <ASIG_FOR>
        {_ASIG_FOR},
        //42.	TER_FOR  λ
        {},
        //43.	ASIG_FOR  id = <OPERACION> <MAS_ASIG_FOR>
        {ID, IGUAL, _OPERACION, _MAS_ASIG_FOR},
        //44.	MAS_ASIG_FOR  , <ASIG_FOR>
        {COMA, _ASIG_FOR},
        //45.	MAS_ASIG_FOR  λ
        {},
        //46.	IF  if (<OPERACION>) {<DESARROLLO>} <ELSE>
        {PAL_RESERV_IF, PARENTESIS_APER, _OPERACION, PARENTESIS_CERR,
                LLAVES_APER, _DESARROLLO, LLAVES_CERR, _ELSE},
        //47.	ELSE  else <OTRO_IF>
        {PAL_RESERV_ELSE, _OTRO_IF},
        //48.	ELSE  λ
        {},
        //49.	OTRO_IF  IF
        {_IF},
        //50.	OTRO_IF  {<DESARROLLO>} 
        {LLAVES_APER, _DESARROLLO, LLAVES_CERR},
        //51.	OPERACION  <CONSTANTE> <MAS_OPERACION>
        {_CONSTANTE, _MAS_OPERACION},
        //52.	OPERACION  (<OPERACIÓN>) <MAS_OPERACION> 
        {PARENTESIS_APER,_OPERACION, PARENTESIS_CERR, _MAS_OPERACION},
        //53.	MAS_OPERACION  <OPERADOR> <OPERACION>
        {_OPERADOR, _OPERACION},
        //54.	MAS_OPERACION  λ 
        {},
        //55.	ARRAY  [ <OPERACION> ] <ARRAY>
        {CORCHETES_APER, _OPERACION, CORCHETES_CERR, _ARRAY},
        //56.	ARRAY  λ
        {},
        //57.	TIPO_VAR  id
        {ID},
        //58.	TIPO_VAR  <PRIMITIVO>
        {_PRIMITIVO},
        //59.	PRIMITIVO  int 
        {PAL_RESERV_INT},
        //60.	PRIMITIVO  double 
        {PAL_RESERV_DOUBLE},
        //61.	PRIMITIVO  char 
        {PAL_RESERV_CHAR},
        //62.	PRIMITIVO  String 
        {PAL_RESERV_STRING},
        //63.	PRIMITIVO  bool 
        {PAL_RESERV_BOOLEAN},
        //64.	TIPO_MET  <TIPO_VAR>
        {_TIPO_VAR},
        //65.	TIPO_MET  void 
        {PAL_RESERV_VOID},
        //66.	OPERADOR  + 
        {OPERADOR_SUMA},
        //67.	OPERADOR  - 
        {OPERADOR_RESTA},
        //68.	OPERADOR  * 
        {OPERADOR_MULT},
        //69.	OPERADOR  / 
        {OPERADOR_DIV},
        //70.	OPERADOR  > 
        {OPERADOR_MAYOR},
        //71.	OPERADOR  >= 
        {OPERADOR_MAYORIGUAL},
        //72.	OPERADOR  < 
        { OPERADOR_MENOR},
        //73.	OPERADOR  <= 
        {OPERADOR_MENORIGUAL},
        //74.	OPERADOR  == 
        {OPERADOR_IGUALDAD},
        //75.	OPERADOR  &
        {OPERADOR_CONJUNCION},
        //76.	OPERADOR  |
        {OPERADOR_DISYUNCION},
        //77.	CONSTANTE  constEnt 
        {CONST_ENTERO},
        //78.	CONSTANTE  constReal
        {CONST_REAL},
        //79.	CONSTANTE  constString
        {CONST_STRING},
        //80.	CONSTANTE  constChar
        {CONST_CHAR},
        //81.   CONSTANTE true
        {PAL_RESERV_TRUE},
        //82.	CONSTANTE false
        {PAL_RESERV_FALSE},
        //83.	CONSTANTE new id (<P>)
        {PAL_RESERV_NEW, ID, PARENTESIS_APER, _P, PARENTESIS_CERR},
        //84.	CONSTANTE null 
        {PAL_RESERV_NULL},
        //85.	CONSTANTE <T>
        {_T},
        //86.	T  id <M> <MAS_T>
        {ID,_M,_MAS_T},
        //87.	MAS_T  . <T> 
        {PUNTO, _T},
        //88.	MAS_T  λ 
        {},
        //89.	M  ( <P> ) 
        {PARENTESIS_APER, _P, PARENTESIS_CERR},
        //90.	M   <ARRAY>
        {_ARRAY},
        //91.	TIPO_ACCESO  public 
        {PAL_RESERV_PUBLIC},
        //92.	TIPO_ACCESO  private 
        {PAL_RESERV_PRIVATE},
        //93.	MAS_SEG_FOR  , <OPERACIÓN> <MAS_SEG_FOR> 
        {COMA, _OPERACION, _MAS_SEG_FOR},
        //94.	MAS_SEG_FOR  λ
        {}

    };
    
    public static HashMap<Integer,String> imagenesDeTokens(){
        HashMap<Integer,String> imagenes = 
                new HashMap<>(NUMERO_DE_NO_TERMINALES + NUMERO_DE_TERMINALES);
        imagenes.put(ID,"Id");
        imagenes.put(CONST_ENTERO,"Const. Ent");
        imagenes.put(CONST_REAL,"Const. Real");
        imagenes.put(CONST_STRING,"Const. String");
        imagenes.put(CONST_CHAR,"Const. Char");
        imagenes.put(PAL_RESERV_INT,"int");
        imagenes.put(PAL_RESERV_DOUBLE,"double");
        imagenes.put(PAL_RESERV_CHAR,"char");
        imagenes.put(PAL_RESERV_STRING,"String");
        imagenes.put(PAL_RESERV_BOOLEAN,"bool");
        imagenes.put(PAL_RESERV_VOID,"void");
        imagenes.put(PAL_RESERV_DO,"do");
        imagenes.put(PAL_RESERV_WHILE,"while");
        imagenes.put(PAL_RESERV_TRUE,"true");
        imagenes.put(PAL_RESERV_FALSE,"false");
        imagenes.put(PAL_RESERV_IF,"if");
        imagenes.put(PAL_RESERV_ELSE,"else");
        imagenes.put(PAL_RESERV_RETURN,"return");
        imagenes.put(PAL_RESERV_PUBLIC,"public");
        imagenes.put(PAL_RESERV_PRIVATE,"private");
        imagenes.put(PAL_RESERV_NEW,"new");
        imagenes.put(PAL_RESERV_NULL,"null");
        imagenes.put(PAL_RESERV_CLASS,"class");
        imagenes.put(PAL_RESERV_FOR,"for");
        imagenes.put(OPERADOR_SUMA,"+");
        imagenes.put(OPERADOR_RESTA,"-");
        imagenes.put(OPERADOR_MULT,"*");
        imagenes.put(OPERADOR_DIV,"/");
        imagenes.put(OPERADOR_MAYOR,">");
        imagenes.put(OPERADOR_MAYORIGUAL,">=");
        imagenes.put(OPERADOR_MENOR,"<");
        imagenes.put(OPERADOR_MENORIGUAL,"<=");
        imagenes.put(IGUAL,"=");
        imagenes.put(OPERADOR_IGUALDAD,"==");
        imagenes.put(OPERADOR_CONJUNCION,"&");
        imagenes.put(OPERADOR_DISYUNCION,"|");
        imagenes.put(PUNTOYCOMA,";");
        imagenes.put(PARENTESIS_APER,"(");
        imagenes.put(PARENTESIS_CERR,")");
        imagenes.put(LLAVES_APER,"{");
        imagenes.put(LLAVES_CERR,"}");
        imagenes.put(CORCHETES_APER,"[");
        imagenes.put(CORCHETES_CERR,"]");
        imagenes.put(COMA,"COMA");
        imagenes.put(PUNTO,"PUNTO");
        imagenes.put(FIN_DE_CADENA,"$");
        
        imagenes.put(_S,"<S>");
        imagenes.put(_MAS_S,"<MAS_S>");
        imagenes.put(_DEFCLASE,"<DEF_CLASE>");
        imagenes.put(_ATRIB,"<ATRIB>");
        imagenes.put(_Z,"<Z>");
        imagenes.put(_MAS_Z,"<MAS_Z>");
        imagenes.put(_ASIG,"<ASIG>");
        imagenes.put(_METODO,"<METODO>");        
        imagenes.put(_E,"<E>");
        imagenes.put(_PARAMETROS,"<PARAMETROS>");
        imagenes.put(_MAS_PARAMETROS,"<MAS_PARAMETROS>");
        imagenes.put(_DESARROLLO,"<DESARROLLO>");
        imagenes.put(_LLAMADA,"<LLAMADA>");
        imagenes.put(_X,"<X>");
        imagenes.put(_MAS_K,"<MAS_K>");
        imagenes.put(_P,"<P>");
        imagenes.put(_MAS_P,"<MAS_P>");
        imagenes.put(_BUCLE,"<BUCLE>");
        imagenes.put(_PRIM_FOR,"<PRIM_FOR>");
        imagenes.put(_SEG_FOR,"<SEG_FOR>");
        imagenes.put(_TER_FOR,"<TER_FOR>");
        imagenes.put(_ASIG_FOR,"<ASIG_FOR>");
        imagenes.put(_IF,"<IF>");
        imagenes.put(_ELSE,"<ELSE>");
        imagenes.put(_OTRO_IF,"<OTRO_IF>");
        imagenes.put(_OPERACION,"<OPERACION>");
        imagenes.put(_MAS_OPERACION,"<MAS_OPERACION>");
        imagenes.put(_ARRAY,"<ARRAY>");
        imagenes.put(_TIPO_VAR,"<TIPO_VAR>");
        imagenes.put(_PRIMITIVO,"<PRIMITIVO>");
        imagenes.put(_TIPO_MET,"<TIPO_MET>");
        imagenes.put(_OPERADOR,"<OPERADOR>");
        imagenes.put(_CONSTANTE,"<CONSTANTE>");
        imagenes.put(_T,"<T>");
        imagenes.put(_MAS_T,"<MAS_T>");
        imagenes.put(_M,"<M>");
        imagenes.put(_TIPO_ACCESO,"<TIPO_ACCESO>");
        
        return imagenes;
    }
}
