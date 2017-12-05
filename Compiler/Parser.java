//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 2 "inicioCT.y"
	import java.io.*;
	import java.util.*;
//#line 20 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short IDENTIFICADOR=257;
public final static short NUMEROS=258;
public final static short STRING=259;
public final static short INCLUSAO_ARQUIVO=260;
public final static short IMPRIMA=261;
public final static short ABRE_CHAVES=262;
public final static short FECHA_CHAVES=263;
public final static short ABRE_COLCHETES=264;
public final static short FECHA_COLCHETES=265;
public final static short ABRE_PARENTESES=266;
public final static short FECHA_PARENTESES=267;
public final static short ATRIBUICAO=268;
public final static short FUNCAO_PRINCIPAL=269;
public final static short FUNCAO=270;
public final static short INCLUIR=271;
public final static short INTEIRO=272;
public final static short REAL=273;
public final static short CARACTER=274;
public final static short RETORNAR=275;
public final static short DIVISAO=276;
public final static short MULTIPLICACAO=277;
public final static short SUBTRACAO=278;
public final static short SOMA=279;
public final static short PERCENT=280;
public final static short LACO_PARA=281;
public final static short LACO_ENQUANTO=282;
public final static short LACO_FACA=283;
public final static short CASO=284;
public final static short BEGIN_CASO=285;
public final static short OPCAO=286;
public final static short FIM_OPCAO=287;
public final static short ATE=288;
public final static short SE=289;
public final static short SENAO=290;
public final static short PONTO_VIRGULA=291;
public final static short IGUAL=292;
public final static short MENOR=293;
public final static short MAIOR=294;
public final static short SOMA_APOS=295;
public final static short RANDOM=296;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    1,    1,    2,    3,    4,    5,    5,
    5,    5,    5,    5,    5,    5,    5,    5,    5,    5,
    5,    5,    5,    5,    5,    5,    5,   15,   15,   15,
   15,   15,   15,   15,   15,   15,   15,   16,   12,   12,
   12,   12,   13,   13,   14,   14,   17,   17,   18,   18,
    6,    6,    6,    6,   10,   10,   10,   10,   10,   11,
   11,   11,   11,   11,   11,   11,   11,   11,   11,   11,
   19,    7,    7,    7,    7,    7,    8,    8,    8,    8,
    9,    9,    9,
};
final static short yylen[] = {                            2,
    1,    2,    2,    2,    0,    4,    8,    2,    1,    2,
    1,    2,    1,    2,    1,    2,    1,    2,    1,    2,
    1,    2,    1,    2,    1,    2,    1,    5,    3,    8,
    6,    9,    6,    9,    9,   10,   10,    4,   16,   16,
   15,   15,    7,    8,    6,    7,    9,   10,    5,    6,
    3,    3,    8,   12,    3,    3,    3,    5,    3,    1,
    1,    1,    1,    1,    1,    1,    1,    2,    2,    2,
    2,    2,    2,    5,    5,    5,    2,    2,    2,    0,
    2,    2,    2,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    1,    0,    0,    0,    0,    0,
    0,    0,    0,    8,    3,    4,    2,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   27,    0,    0,    0,    0,    0,    0,   77,   78,
   79,    0,    0,    0,   71,   26,    0,    0,    0,   81,
   82,   83,    0,    0,    0,    0,    0,    6,   12,   10,
   16,   18,   20,   24,   22,   14,    0,    0,    0,    0,
    0,    0,   52,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   60,   61,
   62,   63,   64,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   70,   68,   69,   55,   56,   57,
    0,   59,   75,   76,   74,    0,    0,    0,    0,    0,
    0,    0,   28,    0,    0,    0,    0,    0,   45,    0,
    0,    0,    0,    0,    0,    0,    7,    0,   58,    0,
   46,   43,    0,    0,    0,    0,    0,    0,   53,    0,
   44,    0,    0,    0,   30,    0,    0,    0,   47,    0,
    0,    0,    0,   32,    0,    0,    0,   48,    0,   37,
   36,    0,    0,    0,    0,   54,    0,    0,    0,    0,
    0,   38,    0,   50,   41,    0,   39,
};
final static short yydgoto[] = {                          4,
    5,    6,    7,    8,   29,   30,   31,   13,   32,   73,
   98,   33,   34,   35,   36,  180,   37,  171,   38,
};
final static short yysindex[] = {                      -210,
 -256, -173, -252,    0,    0, -210, -210, -210,  171, -237,
 -234, -195, -264,    0,    0,    0,    0, -255,  171, -192,
 -187, -185, -141, -216, -164, -188, -163, -157, -206,  171,
  171,    0,  171,  171,  171,  171,  171,  171,    0,    0,
    0, -173, -146, -227,    0,    0, -151, -150, -144,    0,
    0,    0, -132, -225,   65, -131, -232,    0,    0,    0,
    0,    0,    0,    0,    0,    0, -140, -137,  185,  185,
 -167,  185,    0, -123, -122, -114, -124,  185, -119, -139,
 -112, -110,  185,  185, -109,  185, -107, -108,    0,    0,
    0,    0,    0, -136, -135, -134, -181,  -99,  185,  -91,
  -97,  -96,  -88,  -78,  -80,  -83, -104,  -72, -165,  -71,
  -77,  -70,  171,  -68,    0,    0,    0,    0,    0,    0,
 -225,    0,    0,    0,    0,  -95,   94, -225,  -69,  -67,
  -73,  -64,    0,  -63,  -55,  -57,  -56,  -44,    0,  -49,
  -52, -225,  -50,  -46,  -43,  -42,    0, -219,    0,  185,
    0,    0,  -41,  -37,  171,  171,  123,  -36,    0,  -39,
    0, -207,  -34,  -29,    0,  -26,  -30,  -48,    0,  -17,
  -23,  -45,  -45,    0,  -21,  -11,  -90,    0,  -15,    0,
    0,  -18,  -47,  171,  171,    0,  -13,  -32,   -7,   -5,
  -28,    0,  152,    0,    0,    3,    0,
};
final static short yyrindex[] = {                       263,
    0,    5,    0,    0,    0,  263,  263,  263,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0, -260,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0, -258,
 -235,    0, -214, -209, -205, -200, -197, -196,    0,    0,
    0,    2,    0,    0,    0,    0, -177, -142,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0, -111,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  -82,    0,    0,    0,    0,    0,
    0,    0,    0, -215, -213, -211,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  190,  195,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  -51,    0,  -22,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    7,   36,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    9,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
  116,    0,    0,    0,  -19,    0,    0,  231,    0,  -53,
  -62,    0,    0,    0,    0,  101,    0,   84,    0,
};
final static int YYTABLESIZE=489;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         46,
   79,   42,   25,   86,   11,    9,   97,   14,   43,  100,
   59,   60,   44,   61,   62,   63,   64,   65,   66,   39,
  109,  110,   40,  112,   83,   84,   25,    9,   11,   69,
   70,   69,   78,   71,   85,   81,  121,  158,   71,   45,
   71,   65,   65,   66,   66,   67,   67,  159,   15,   53,
   65,    9,   66,   17,   67,  169,   58,   19,    1,    2,
    3,   41,   23,   72,   47,   21,   13,  137,   72,   48,
   72,   49,   15,   55,  141,  118,  119,   17,  170,   72,
   65,   19,   66,   72,   67,   72,   23,  160,  153,   21,
   13,  118,  131,  135,   72,   72,   72,   72,   10,   11,
   12,   54,   56,   72,   72,   72,   72,  140,   57,   72,
   68,   72,   74,   75,   73,   50,   51,   52,   73,   76,
   73,   15,   16,   17,   77,   82,   87,   88,   99,   73,
   73,   73,   73,  101,  102,  163,  164,  166,   73,   73,
   73,   73,  103,  104,   73,   51,   73,  105,  106,   51,
  107,   51,  111,  108,  113,  115,  116,  117,  120,  114,
   51,   51,   51,   51,  188,  189,  122,  123,  124,   51,
   51,   51,   51,  196,   29,   51,  125,   51,   29,  126,
   29,  127,  128,  129,  130,  133,  132,  134,  136,   29,
   29,   29,   29,  144,  184,  138,  142,  143,   29,   29,
   29,   29,  145,  146,   29,   33,   29,  147,  148,   33,
  149,   33,  150,  151,  152,  155,  154,  168,  156,  157,
   33,   33,   33,   33,  162,  161,  175,  167,  172,   33,
   33,   33,   33,  173,   31,   33,  174,   33,   31,  178,
   31,  177,  176,  182,  179,  183,  185,  187,  186,   31,
   31,   31,   31,  190,  191,  192,  193,  170,   31,   31,
   31,   31,    5,   35,   31,  197,   31,   35,   80,   35,
   80,   49,   67,  181,  194,    0,    0,    0,   35,   35,
   35,   35,    0,    0,    0,    0,    0,   35,   35,   35,
   35,    0,   34,   35,    0,   35,   34,    0,   34,    0,
    0,    0,    0,    0,    0,    0,    0,   34,   34,   34,
   34,    0,    0,    0,    0,    0,   34,   34,   34,   34,
    0,   18,   34,    0,   34,   19,    0,   80,    0,    0,
    0,    0,    0,    0,    0,    0,   20,   21,   22,   23,
    0,    0,    0,    0,    0,   24,   25,   26,   27,    0,
   18,    0,    0,   28,   19,    0,  139,    0,    0,    0,
    0,    0,    0,    0,    0,   20,   21,   22,   23,    0,
    0,    0,    0,    0,   24,   25,   26,   27,    0,   18,
    0,    0,   28,   19,    0,  165,    0,    0,    0,    0,
    0,    0,    0,    0,   20,   21,   22,   23,    0,    0,
    0,    0,    0,   24,   25,   26,   27,    0,   18,    0,
    0,   28,   19,    0,  195,    0,    0,    0,    0,    0,
    0,    0,    0,   20,   21,   22,   23,   18,    0,    0,
    0,   19,   24,   25,   26,   27,    0,    0,    0,    0,
   28,    0,   20,   21,   22,   23,    0,    0,    0,    0,
    0,   24,   25,   26,   27,    0,    0,    0,    0,   28,
   89,   90,   91,   92,   93,   56,   56,   56,   56,   56,
   57,   57,   57,   57,   57,    0,   94,   95,   96,    0,
    0,   56,   56,   56,    0,    0,   57,   57,   57,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         19,
   54,  266,  263,   57,  263,  262,   69,  260,  264,   72,
   30,   31,  268,   33,   34,   35,   36,   37,   38,  257,
   83,   84,  257,   86,  257,  258,  287,  263,  287,  257,
  258,  257,  258,  266,  267,   55,   99,  257,  266,  295,
  266,  257,  258,  257,  258,  257,  258,  267,  263,  266,
  266,  287,  266,  263,  266,  263,  263,  263,  269,  270,
  271,  257,  263,  296,  257,  263,  263,  121,  296,  257,
  296,  257,  287,  262,  128,  257,  258,  287,  286,  257,
  296,  287,  296,  261,  296,  263,  287,  150,  142,  287,
  287,  257,  258,  113,  272,  273,  274,  275,  272,  273,
  274,  266,  266,  281,  282,  283,  284,  127,  266,  287,
  257,  289,  264,  264,  257,  257,  258,  259,  261,  264,
  263,    6,    7,    8,  257,  257,  267,  265,  296,  272,
  273,  274,  275,  257,  257,  155,  156,  157,  281,  282,
  283,  284,  257,  268,  287,  257,  289,  267,  288,  261,
  263,  263,  262,  264,  262,  292,  292,  292,  258,  268,
  272,  273,  274,  275,  184,  185,  258,  265,  265,  281,
  282,  283,  284,  193,  257,  287,  265,  289,  261,  258,
  263,  262,  266,  288,  257,  263,  258,  258,  257,  272,
  273,  274,  275,  267,  285,  291,  266,  265,  281,  282,
  283,  284,  267,  267,  287,  257,  289,  263,  266,  261,
  267,  263,  257,  263,  267,  262,  267,  257,  262,  262,
  272,  273,  274,  275,  262,  267,  257,  264,  263,  281,
  282,  283,  284,  263,  257,  287,  263,  289,  261,  263,
  263,  259,  291,  265,  290,  257,  262,  295,  267,  272,
  273,  274,  275,  267,  287,  263,  262,  286,  281,  282,
  283,  284,    0,  257,  287,  263,  289,  261,  267,  263,
  266,  263,   42,  173,  191,   -1,   -1,   -1,  272,  273,
  274,  275,   -1,   -1,   -1,   -1,   -1,  281,  282,  283,
  284,   -1,  257,  287,   -1,  289,  261,   -1,  263,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  272,  273,  274,
  275,   -1,   -1,   -1,   -1,   -1,  281,  282,  283,  284,
   -1,  257,  287,   -1,  289,  261,   -1,  263,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  272,  273,  274,  275,
   -1,   -1,   -1,   -1,   -1,  281,  282,  283,  284,   -1,
  257,   -1,   -1,  289,  261,   -1,  263,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  272,  273,  274,  275,   -1,
   -1,   -1,   -1,   -1,  281,  282,  283,  284,   -1,  257,
   -1,   -1,  289,  261,   -1,  263,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  272,  273,  274,  275,   -1,   -1,
   -1,   -1,   -1,  281,  282,  283,  284,   -1,  257,   -1,
   -1,  289,  261,   -1,  263,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  272,  273,  274,  275,  257,   -1,   -1,
   -1,  261,  281,  282,  283,  284,   -1,   -1,   -1,   -1,
  289,   -1,  272,  273,  274,  275,   -1,   -1,   -1,   -1,
   -1,  281,  282,  283,  284,   -1,   -1,   -1,   -1,  289,
  276,  277,  278,  279,  280,  276,  277,  278,  279,  280,
  276,  277,  278,  279,  280,   -1,  292,  293,  294,   -1,
   -1,  292,  293,  294,   -1,   -1,  292,  293,  294,
};
}
final static short YYFINAL=4;
final static short YYMAXTOKEN=296;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"IDENTIFICADOR","NUMEROS","STRING","INCLUSAO_ARQUIVO","IMPRIMA",
"ABRE_CHAVES","FECHA_CHAVES","ABRE_COLCHETES","FECHA_COLCHETES",
"ABRE_PARENTESES","FECHA_PARENTESES","ATRIBUICAO","FUNCAO_PRINCIPAL","FUNCAO",
"INCLUIR","INTEIRO","REAL","CARACTER","RETORNAR","DIVISAO","MULTIPLICACAO",
"SUBTRACAO","SOMA","PERCENT","LACO_PARA","LACO_ENQUANTO","LACO_FACA","CASO",
"BEGIN_CASO","OPCAO","FIM_OPCAO","ATE","SE","SENAO","PONTO_VIRGULA","IGUAL",
"MENOR","MAIOR","SOMA_APOS","RANDOM",
};
final static String yyrule[] = {
"$accept : inicio",
"inicio : programa",
"programa : inclusao programa",
"programa : funcao_principal programa",
"programa : funcao programa",
"programa :",
"funcao_principal : FUNCAO_PRINCIPAL ABRE_CHAVES comandos FECHA_CHAVES",
"funcao : FUNCAO declaracao_funcao ABRE_PARENTESES declaracao_funcao FECHA_PARENTESES ABRE_CHAVES comandos FECHA_CHAVES",
"inclusao : INCLUIR INCLUSAO_ARQUIVO",
"comandos : declaracao",
"comandos : declaracao comandos",
"comandos : atribuicao",
"comandos : atribuicao comandos",
"comandos : incremento",
"comandos : incremento comandos",
"comandos : laco_para",
"comandos : laco_para comandos",
"comandos : laco_faca",
"comandos : laco_faca comandos",
"comandos : laco_enquanto",
"comandos : laco_enquanto comandos",
"comandos : caso",
"comandos : caso comandos",
"comandos : se",
"comandos : se comandos",
"comandos : IMPRIMA",
"comandos : IMPRIMA comandos",
"comandos : retornar",
"se : SE ABRE_PARENTESES FECHA_PARENTESES ABRE_CHAVES FECHA_CHAVES",
"se : SE ABRE_PARENTESES FECHA_PARENTESES",
"se : SE ABRE_PARENTESES expressao operador NUMEROS FECHA_PARENTESES ABRE_CHAVES FECHA_CHAVES",
"se : SE ABRE_PARENTESES expressao operador NUMEROS FECHA_PARENTESES",
"se : SE ABRE_PARENTESES expressao operador NUMEROS FECHA_PARENTESES ABRE_CHAVES comandos FECHA_CHAVES",
"se : SE ABRE_PARENTESES IDENTIFICADOR operador NUMEROS FECHA_PARENTESES",
"se : SE ABRE_PARENTESES NUMEROS operador NUMEROS FECHA_PARENTESES ABRE_CHAVES comandos FECHA_CHAVES",
"se : SE ABRE_PARENTESES IDENTIFICADOR operador NUMEROS FECHA_PARENTESES ABRE_CHAVES comandos FECHA_CHAVES",
"se : SE ABRE_PARENTESES NUMEROS operador NUMEROS FECHA_PARENTESES ABRE_CHAVES comandos FECHA_CHAVES senao",
"se : SE ABRE_PARENTESES IDENTIFICADOR operador NUMEROS FECHA_PARENTESES ABRE_CHAVES comandos FECHA_CHAVES senao",
"senao : SENAO ABRE_CHAVES comandos FECHA_CHAVES",
"laco_para : LACO_PARA ABRE_PARENTESES IDENTIFICADOR ATRIBUICAO NUMEROS PONTO_VIRGULA IDENTIFICADOR operador IDENTIFICADOR PONTO_VIRGULA IDENTIFICADOR SOMA_APOS FECHA_PARENTESES ABRE_CHAVES comandos FECHA_CHAVES",
"laco_para : LACO_PARA ABRE_PARENTESES IDENTIFICADOR ATRIBUICAO NUMEROS PONTO_VIRGULA IDENTIFICADOR operador IDENTIFICADOR PONTO_VIRGULA IDENTIFICADOR SOMA_APOS FECHA_PARENTESES ABRE_CHAVES comandos FECHA_CHAVES",
"laco_para : LACO_PARA ABRE_PARENTESES IDENTIFICADOR ATRIBUICAO NUMEROS PONTO_VIRGULA IDENTIFICADOR operador IDENTIFICADOR PONTO_VIRGULA IDENTIFICADOR SOMA_APOS FECHA_PARENTESES ABRE_CHAVES FECHA_CHAVES",
"laco_para : LACO_PARA ABRE_PARENTESES IDENTIFICADOR ATRIBUICAO NUMEROS PONTO_VIRGULA IDENTIFICADOR operador IDENTIFICADOR PONTO_VIRGULA IDENTIFICADOR SOMA_APOS FECHA_PARENTESES ABRE_CHAVES FECHA_CHAVES",
"laco_faca : LACO_FACA ABRE_CHAVES FECHA_CHAVES ATE ABRE_PARENTESES expressao FECHA_PARENTESES",
"laco_faca : LACO_FACA ABRE_CHAVES comandos FECHA_CHAVES ATE ABRE_PARENTESES expressao FECHA_PARENTESES",
"laco_enquanto : LACO_ENQUANTO ABRE_PARENTESES expressao FECHA_PARENTESES ABRE_CHAVES FECHA_CHAVES",
"laco_enquanto : LACO_ENQUANTO ABRE_PARENTESES expressao FECHA_PARENTESES ABRE_CHAVES comandos FECHA_CHAVES",
"caso : CASO ABRE_PARENTESES IDENTIFICADOR ABRE_COLCHETES IDENTIFICADOR FECHA_COLCHETES FECHA_PARENTESES ABRE_CHAVES FECHA_CHAVES",
"caso : CASO ABRE_PARENTESES IDENTIFICADOR ABRE_COLCHETES IDENTIFICADOR FECHA_COLCHETES FECHA_PARENTESES ABRE_CHAVES opcao FECHA_CHAVES",
"opcao : OPCAO STRING BEGIN_CASO comandos FIM_OPCAO",
"opcao : OPCAO STRING BEGIN_CASO comandos FIM_OPCAO opcao",
"atribuicao : IDENTIFICADOR ATRIBUICAO NUMEROS",
"atribuicao : IDENTIFICADOR ATRIBUICAO expressao",
"atribuicao : IDENTIFICADOR ABRE_COLCHETES IDENTIFICADOR FECHA_COLCHETES ATRIBUICAO IDENTIFICADOR ABRE_PARENTESES FECHA_PARENTESES",
"atribuicao : IDENTIFICADOR ABRE_COLCHETES IDENTIFICADOR FECHA_COLCHETES ATRIBUICAO IDENTIFICADOR ABRE_PARENTESES IDENTIFICADOR ABRE_COLCHETES IDENTIFICADOR FECHA_COLCHETES FECHA_PARENTESES",
"expressao : IDENTIFICADOR operador IDENTIFICADOR",
"expressao : IDENTIFICADOR operador NUMEROS",
"expressao : NUMEROS operador NUMEROS",
"expressao : ABRE_PARENTESES RANDOM operador expressao FECHA_PARENTESES",
"expressao : RANDOM operador NUMEROS",
"operador : DIVISAO",
"operador : MULTIPLICACAO",
"operador : SUBTRACAO",
"operador : SOMA",
"operador : PERCENT",
"operador : IGUAL",
"operador : MENOR",
"operador : MAIOR",
"operador : MENOR IGUAL",
"operador : MAIOR IGUAL",
"operador : IGUAL IGUAL",
"incremento : IDENTIFICADOR SOMA_APOS",
"declaracao : INTEIRO IDENTIFICADOR",
"declaracao : REAL IDENTIFICADOR",
"declaracao : CARACTER IDENTIFICADOR ABRE_COLCHETES IDENTIFICADOR FECHA_COLCHETES",
"declaracao : INTEIRO IDENTIFICADOR ABRE_COLCHETES IDENTIFICADOR FECHA_COLCHETES",
"declaracao : REAL IDENTIFICADOR ABRE_COLCHETES IDENTIFICADOR FECHA_COLCHETES",
"declaracao_funcao : INTEIRO IDENTIFICADOR",
"declaracao_funcao : REAL IDENTIFICADOR",
"declaracao_funcao : CARACTER IDENTIFICADOR",
"declaracao_funcao :",
"retornar : RETORNAR IDENTIFICADOR",
"retornar : RETORNAR NUMEROS",
"retornar : RETORNAR STRING",
};

//#line 173 "inicioCT.y"

	// Referencia ao JFlex
	private Yylex lexer;

	/* Interface com o JFlex */
	private int yylex(){
		int yyl_return = -1;
		try {
			yyl_return = lexer.yylex();
		} catch (IOException e) {
			System.err.println("Erro de IO: " + e);
		}
		return yyl_return;
	}

	/* Reporte de erro */
	public void yyerror(String error){
		System.err.println("Error: " + error);
	}

	// Interface com o JFlex eh criado no construtor
	public Parser(Reader r){
		lexer = new Yylex(r, this);
	}

	// Main
	public static void main(String[] args){
		try{ 
			Parser yyparser = new Parser(new FileReader(args[0]));
			yyparser.yyparse();
			} catch (IOException ex) {
				System.err.println("Error: " + ex);
			}
	}
//#line 486 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 68 "inicioCT.y"
{ System.out.println(val_peek(0).sval); }
break;
case 2:
//#line 70 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "\n" + val_peek(0).sval; }
break;
case 3:
//#line 71 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "\n" + val_peek(0).sval; }
break;
case 4:
//#line 72 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "\n" + val_peek(0).sval; }
break;
case 5:
//#line 73 "inicioCT.y"
{ yyval.sval = ""; }
break;
case 6:
//#line 75 "inicioCT.y"
{ yyval.sval = "int main() {\n " + val_peek(1).sval + "}\n"; }
break;
case 7:
//#line 77 "inicioCT.y"
{yyval.sval = val_peek(6).sval + " (" +  val_peek(4).sval + ") {\n " + val_peek(1).sval + "}\n"; }
break;
case 8:
//#line 79 "inicioCT.y"
{ yyval.sval = "#include " + val_peek(0).sval; }
break;
case 9:
//#line 81 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 10:
//#line 82 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + " " + val_peek(0).sval; }
break;
case 11:
//#line 83 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 12:
//#line 84 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + " " + val_peek(0).sval; }
break;
case 13:
//#line 85 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 14:
//#line 86 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + " " + val_peek(0).sval; }
break;
case 15:
//#line 87 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 16:
//#line 88 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + " " + val_peek(0).sval; }
break;
case 17:
//#line 89 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 18:
//#line 90 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + " " + val_peek(0).sval; }
break;
case 19:
//#line 91 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 20:
//#line 92 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + " " + val_peek(0).sval; }
break;
case 21:
//#line 93 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 22:
//#line 94 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + " " + val_peek(0).sval; }
break;
case 23:
//#line 95 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 24:
//#line 96 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + " " + val_peek(0).sval; }
break;
case 25:
//#line 97 "inicioCT.y"
{ yyval.sval = "printf" + val_peek(0).sval + ";\n"; }
break;
case 26:
//#line 98 "inicioCT.y"
{ yyval.sval = "printf" + val_peek(1).sval + ";\n" + val_peek(0).sval; }
break;
case 28:
//#line 101 "inicioCT.y"
{ yyval.sval = "if ( ) {\n }\n"; }
break;
case 29:
//#line 102 "inicioCT.y"
{ yyval.sval = "if ( )\n"; }
break;
case 30:
//#line 103 "inicioCT.y"
{ yyval.sval = "if (" + val_peek(5).sval + val_peek(4).sval + val_peek(3).sval + ") {\n }\n"; }
break;
case 31:
//#line 104 "inicioCT.y"
{ yyval.sval = "if (" + val_peek(3).sval + val_peek(2).sval + val_peek(1).sval + ")\n"; }
break;
case 32:
//#line 105 "inicioCT.y"
{ yyval.sval = "if (" + val_peek(6).sval + val_peek(5).sval + val_peek(4).sval + ") {\n " + val_peek(1).sval + "}\n"; }
break;
case 33:
//#line 106 "inicioCT.y"
{ yyval.sval = "if (" + val_peek(3).sval + val_peek(2).sval + val_peek(1).sval + ")\n"; }
break;
case 34:
//#line 107 "inicioCT.y"
{ yyval.sval = "if (" + val_peek(6).sval + val_peek(5).sval + val_peek(4).sval + ") {\n " + val_peek(1).sval + "}\n"; }
break;
case 35:
//#line 108 "inicioCT.y"
{ yyval.sval = "if (" + val_peek(6).sval + val_peek(5).sval + val_peek(4).sval + ") {\n " + val_peek(1).sval + "}\n"; }
break;
case 36:
//#line 109 "inicioCT.y"
{ yyval.sval = "if (" + val_peek(7).sval + val_peek(6).sval + val_peek(5).sval + ") {\n " + val_peek(2).sval + "} " + val_peek(0).sval; }
break;
case 37:
//#line 110 "inicioCT.y"
{ yyval.sval = "if (" + val_peek(7).sval + val_peek(6).sval + val_peek(5).sval + ") {\n " + val_peek(2).sval + "} " + val_peek(0).sval; }
break;
case 38:
//#line 112 "inicioCT.y"
{ yyval.sval = "else {\n" + val_peek(1).sval + "}\n"; }
break;
case 39:
//#line 114 "inicioCT.y"
{ yyval.sval = "for (" + val_peek(13).sval + " = " + val_peek(11).sval + "; " + val_peek(9).sval + val_peek(8).sval + val_peek(7).sval + "; " + val_peek(5).sval + "++) {\n " + val_peek(1).sval + " }\n"; }
break;
case 40:
//#line 115 "inicioCT.y"
{ yyval.sval = "for (" + val_peek(13).sval + " = " + val_peek(11).sval + "; " + val_peek(9).sval + val_peek(8).sval+ val_peek(7).sval + "; " + val_peek(5).sval + "++) {\n " + val_peek(1).sval + " }\n"; }
break;
case 41:
//#line 116 "inicioCT.y"
{ yyval.sval = "for (" + val_peek(12).sval + " = " + val_peek(10).sval + "; " + val_peek(8).sval + val_peek(7).sval + val_peek(6).sval + "; " + val_peek(4).sval + "++) {\n }\n"; }
break;
case 42:
//#line 117 "inicioCT.y"
{ yyval.sval = "for (" + val_peek(12).sval + " = " + val_peek(10).sval + "; " + val_peek(8).sval + val_peek(7).sval + val_peek(6).sval + "; " + val_peek(4).sval + "++) {\n }\n"; }
break;
case 43:
//#line 119 "inicioCT.y"
{ yyval.sval = "do {\n } while (" + val_peek(1).sval+ ");\n"; }
break;
case 44:
//#line 120 "inicioCT.y"
{ yyval.sval = "do {\n" + val_peek(5).sval + " } while (" + val_peek(1).sval+ ");\n"; }
break;
case 45:
//#line 122 "inicioCT.y"
{ yyval.sval = "while (" + val_peek(3).sval+ ") {\n}\n"; }
break;
case 46:
//#line 123 "inicioCT.y"
{ yyval.sval = "while (" + val_peek(4).sval+ ") {\n" + val_peek(1).sval + "}\n"; }
break;
case 47:
//#line 125 "inicioCT.y"
{ yyval.sval = "switch (" + val_peek(6).sval + "[" + val_peek(4).sval + "]) {\n }\n"; }
break;
case 48:
//#line 126 "inicioCT.y"
{ yyval.sval = "switch (" + val_peek(7).sval + "[" + val_peek(5).sval + "]) {\n" + val_peek(1).sval + "}\n"; }
break;
case 49:
//#line 128 "inicioCT.y"
{ yyval.sval = "case " + val_peek(3).sval + ":\n" + val_peek(1).sval + "break;\n"; }
break;
case 50:
//#line 129 "inicioCT.y"
{ yyval.sval = "case " + val_peek(4).sval + ":\n" + val_peek(2).sval + "break;\n" + val_peek(0).sval; }
break;
case 51:
//#line 131 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + " = " + val_peek(0).sval + ";\n"; }
break;
case 52:
//#line 132 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + " = " + val_peek(0).sval + ";\n"; }
break;
case 53:
//#line 133 "inicioCT.y"
{ yyval.sval = val_peek(7).sval + "[" + val_peek(5).sval + "] = " + val_peek(2).sval + "();\n"; }
break;
case 54:
//#line 135 "inicioCT.y"
{ yyval.sval = val_peek(11).sval + "[" + val_peek(9).sval + "] = " + val_peek(6).sval + "(" + val_peek(4).sval+ "[" + val_peek(2).sval + "]);\n"; }
break;
case 55:
//#line 137 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + val_peek(1).sval + val_peek(0).sval; }
break;
case 56:
//#line 138 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + val_peek(1).sval + val_peek(0).sval; }
break;
case 57:
//#line 139 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + val_peek(1).sval + val_peek(0).sval; }
break;
case 58:
//#line 140 "inicioCT.y"
{ yyval.sval = "( rand() " + val_peek(2).sval + val_peek(1).sval + ")"; }
break;
case 59:
//#line 141 "inicioCT.y"
{ yyval.sval = "rand() " + val_peek(1).sval + val_peek(0).sval; }
break;
case 60:
//#line 143 "inicioCT.y"
{ yyval.sval = "/"; }
break;
case 61:
//#line 144 "inicioCT.y"
{ yyval.sval = "*"; }
break;
case 62:
//#line 145 "inicioCT.y"
{ yyval.sval = "-"; }
break;
case 63:
//#line 146 "inicioCT.y"
{ yyval.sval = "+"; }
break;
case 64:
//#line 147 "inicioCT.y"
{ yyval.sval = "%"; }
break;
case 65:
//#line 148 "inicioCT.y"
{ yyval.sval = "="; }
break;
case 66:
//#line 149 "inicioCT.y"
{ yyval.sval = "<"; }
break;
case 67:
//#line 150 "inicioCT.y"
{ yyval.sval = ">"; }
break;
case 68:
//#line 151 "inicioCT.y"
{ yyval.sval = "<="; }
break;
case 69:
//#line 152 "inicioCT.y"
{ yyval.sval = ">="; }
break;
case 70:
//#line 153 "inicioCT.y"
{ yyval.sval = "=="; }
break;
case 71:
//#line 155 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "++;\n"; }
break;
case 72:
//#line 157 "inicioCT.y"
{  yyval.sval = "int " + val_peek(0).sval + ";\n"; }
break;
case 73:
//#line 158 "inicioCT.y"
{  yyval.sval = "float " + val_peek(0).sval + ";\n"; }
break;
case 74:
//#line 159 "inicioCT.y"
{  yyval.sval = "char " + val_peek(3).sval + "[" + val_peek(1).sval + "];\n"; }
break;
case 75:
//#line 160 "inicioCT.y"
{  yyval.sval = "int " + val_peek(3).sval + "[" + val_peek(1).sval + "];\n"; }
break;
case 76:
//#line 161 "inicioCT.y"
{  yyval.sval = "float " + val_peek(3).sval + "[" + val_peek(1).sval + "];\n"; }
break;
case 77:
//#line 163 "inicioCT.y"
{  yyval.sval = "int " + val_peek(0).sval; }
break;
case 78:
//#line 164 "inicioCT.y"
{  yyval.sval = "float " + val_peek(0).sval; }
break;
case 79:
//#line 165 "inicioCT.y"
{  yyval.sval = "char " + val_peek(0).sval; }
break;
case 80:
//#line 166 "inicioCT.y"
{ yyval.sval = ""; }
break;
case 81:
//#line 168 "inicioCT.y"
{ yyval.sval = "return " + val_peek(0).sval + ";\n"; }
break;
case 82:
//#line 169 "inicioCT.y"
{ yyval.sval = "return " + val_peek(0).sval + ";\n"; }
break;
case 83:
//#line 170 "inicioCT.y"
{ yyval.sval = "return " + val_peek(0).sval + ";\n"; }
break;
//#line 963 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
