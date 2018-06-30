
public class AnalizadorLexico{
    public static final int NO_IDENTIFICADO = 911;
    public static final char SIMBOLO_FINAL = '$';
    
    private int lineaActual = 1;
    private int indice = 0;
    private String cadena ="";
//    private TAS tabla= new TAS();

    public AnalizadorLexico(String cadena) {
        this.cadena = cadena+SIMBOLO_FINAL;
    }
    
    public int siguienteToken(){
//        System.out.println(cadena);
        char c;
        int estado=0;
        StringBuilder buffer=new StringBuilder();
        
        boolean finLexema = false;
        
        while(true){
            c=cadena.charAt(indice);
//            System.out.println(c+"-"+estado);
            switch(estado){
                case 0:
                    if(Character.isAlphabetic(c)) estado=1;
                    else if(Character.isDigit(c)) estado=2;
                    else if(c=='+') estado = 4;
                    else if(c=='-') estado = 5;
                    else if(c=='*') estado = 6;
                    else if(c=='/') estado = 7;
                    else if(c=='>') estado = 8;
                    else if(c=='<') estado = 10;
                    else if(c=='=') estado = 12;
                    else if(c=='&') estado = 14;
                    else if(c=='|') estado = 15;
                    else if(c==';') estado = 16;
                    else if(c=='(') estado = 17;
                    else if(c==')') estado = 21;
                    else if(c=='{') estado = 22;
                    else if(c=='}') estado = 23;
                    else if(c=='[') estado = 24;
                    else if(c==']') estado = 25;
                    else if(c==',') estado = 26;
                    else if(c=='\'') estado = 27;
                    else if(c=='\"') estado = 30;
                    else if(c=='.') estado = 33;
                    else if(c==' ') estado = 0;
                    else if(c=='\n') {estado = 0; lineaActual++;}
                    else if(c==SIMBOLO_FINAL);
                    else { estado = NO_IDENTIFICADO; finLexema=true;}
                break;
                case 1:
                    if(Character.isAlphabetic(c) || Character.isDigit(c)) estado=1;
                    else finLexema = true;
                break;
                case 2:
                    if(Character.isDigit(c)) estado=2;
                    else if(c=='.') estado = 3;
                    else finLexema = true;
                break;
                case 3:
                    if(Character.isDigit(c))estado=3;
                    else finLexema = true;
                break;
                case 7:
                    if(c=='*') estado =18;
                    else finLexema = true;
                break;
                case 8:
                    if(c=='=') estado=9;
                    else finLexema = true;
                break;
                case 10:
                    if(c=='=') estado=11;
                    else finLexema = true;
                break;
                case 12:
                    if(c=='=') estado=13;
                    else finLexema = true;
                break;
                case 18:
                    if(c=='*') estado =19;
                    else estado=18;
                break;
                case 19:
                    if(c=='/') estado=20;
                    else if(c=='*') estado=19;
                    else estado=18;
                break;
                case 27:
                    if(c!='\'') estado = 28;
                    else finLexema=true;
                    break;
                case 28:
                    if(c=='\'') estado = 29;
                    else finLexema=true;
                    break;
                case 30:
                    if(c!='\"') estado = 31;
                    else finLexema=true;
                    break;
                case 31:
                    if(c=='\"') estado = 32;
                    else estado = 31;
                    break;
                default:
                    finLexema=true;
                break;
            }
//            if(c=='#') finLexema = true;
            if(finLexema){
                if(estado==NO_IDENTIFICADO){
                    buffer.append(c);
                    indice++;
                }
                
                int token = reconocerLexema(estado, buffer.toString());
                if(token!=0) {
                    return token;
                }else{
                    estado=0;
                    buffer = new StringBuilder();
                    finLexema=false;
                }
            }else{
                if(c==SIMBOLO_FINAL) break;
                
                buffer.append(c);
                indice++;
            }
        }
        return Terminales.FIN_DE_CADENA;
    }

    private int reconocerLexema(int estado,String nuevoLexema){
        nuevoLexema = nuevoLexema.trim();
        if(nuevoLexema.length()>0 && nuevoLexema!=String.valueOf(SIMBOLO_FINAL)){
            switch(estado){
                case 1: return Terminales.buscarPalabra(nuevoLexema);
                case 2: return Terminales.CONST_ENTERO;     case 3: return Terminales.CONST_REAL;
                case 4: return Terminales.OPERADOR_SUMA;     case 5: return Terminales.OPERADOR_RESTA;
                case 6: return Terminales.OPERADOR_MULT;     case 7: return Terminales.OPERADOR_DIV;
                case 8: return Terminales.OPERADOR_MAYOR;     case 9: return Terminales.OPERADOR_MAYORIGUAL;
                case 10: return Terminales.OPERADOR_MENOR;    case 11: return Terminales.OPERADOR_MENORIGUAL;
                case 12: return Terminales.IGUAL;    case 13: return Terminales.OPERADOR_IGUALDAD;
                case 14: return Terminales.OPERADOR_CONJUNCION;    case 15: return Terminales.OPERADOR_DISYUNCION;
                case 16: return Terminales.PUNTOYCOMA;    case 17: return Terminales.PARENTESIS_APER;
                case 20: return 0;/*CASO COMENTARIO*/
                case 21: return Terminales.PARENTESIS_CERR;    case 22: return Terminales.LLAVES_APER;
                case 23: return Terminales.LLAVES_CERR;    case 24: return Terminales.CORCHETES_APER;
                case 25: return Terminales.CORCHETES_CERR;    case 26: return Terminales.COMA;
                case 29: return Terminales.CONST_CHAR;    case 32: return Terminales.CONST_STRING;
                case 33: return Terminales.PUNTO;
                default:
                    return NO_IDENTIFICADO;
            }
        }else if(nuevoLexema.length()==0){
            return 0;
        }else{
            return Terminales.FIN_DE_CADENA;
        }
    }

    public int getLineaActual() {
        return lineaActual;
    }
       
}
