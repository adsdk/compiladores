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
public final static short ABRE_PARENTESES=264;
public final static short FECHA_PARENTESES=265;
public final static short ATRIBUICAO=266;
public final static short FUNCAO_PRINCIPAL=267;
public final static short FUNCAO=268;
public final static short INCLUIR=269;
public final static short INTEIRO=270;
public final static short REAL=271;
public final static short CARACTER=272;
public final static short RETORNAR=273;
public final static short DIVISAO=274;
public final static short MULTIPLICACAO=275;
public final static short SUBTRACAO=276;
public final static short SOMA=277;
public final static short PERCENT=278;
public final static short LACO_PARA=279;
public final static short LACO_ENQUANTO=280;
public final static short LACO_FACA=281;
public final static short ATE=282;
public final static short SE=283;
public final static short SENAO=284;
public final static short PONTO_VIRGULA=285;
public final static short IGUAL=286;
public final static short MENOR=287;
public final static short MAIOR=288;
public final static short SOMA_APOS=289;
public final static short RANDOM=290;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    1,    1,    2,    3,    4,    5,    5,
    5,    5,    5,    5,    5,    5,    5,    5,    5,    5,
    5,    5,    5,   14,   14,   14,   14,   14,   14,   14,
   12,   12,   12,   12,   13,    6,    6,   10,   10,   10,
   10,   10,   11,   11,   11,   11,   11,   11,   11,   11,
   11,   11,   11,   15,    7,    7,    8,    8,    8,    8,
    9,    9,    9,
};
final static short yylen[] = {                            2,
    1,    2,    2,    2,    0,    4,    8,    2,    1,    2,
    1,    2,    1,    2,    1,    2,    1,    2,    1,    2,
    1,    2,    1,    5,    3,    8,    6,    9,    6,    9,
   16,   16,   15,   15,    7,    3,    3,    3,    3,    3,
    5,    3,    1,    1,    1,    1,    1,    1,    1,    1,
    2,    2,    2,    2,    2,    2,    2,    2,    2,    0,
    2,    2,    2,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    1,    0,    0,    0,    0,    0,
    0,    0,    0,    8,    3,    4,    2,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   23,    0,
    0,    0,    0,   57,   58,   59,    0,    0,   54,   22,
   55,   56,   61,   62,   63,    0,    0,    0,    6,   12,
   10,   16,   18,   20,   14,    0,    0,    0,    0,    0,
   37,    0,    0,    0,    0,    0,    0,    0,   43,   44,
   45,   46,   47,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   53,   51,   52,
   38,   39,   40,    0,   42,    0,    0,    0,    0,   24,
    0,    0,    0,    0,    0,    0,   29,    0,    0,    7,
   41,    0,   35,    0,    0,    0,    0,   26,    0,    0,
   30,   28,    0,    0,    0,    0,    0,   33,    0,   31,
};
final static short yydgoto[] = {                          4,
    5,    6,    7,    8,   26,   27,   28,   13,   29,   61,
   78,   30,   31,   32,   33,
};
final static short yysindex[] = {                      -223,
 -260, -214, -239,    0,    0, -223, -223, -223, -117, -233,
 -218, -206, -221,    0,    0,    0,    0, -259, -117, -203,
 -198, -180, -204, -195, -192, -194, -117, -117,    0, -117,
 -117, -117, -117,    0,    0,    0, -214, -254,    0,    0,
    0,    0,    0,    0,    0, -176, -177, -238,    0,    0,
    0,    0,    0,    0,    0, -178, -107, -107, -202, -107,
    0, -175, -188, -107, -107, -161, -107, -159,    0,    0,
    0,    0,    0, -182, -174, -173, -252, -153, -107, -140,
 -138, -143, -241, -136, -152, -131, -117,    0,    0,    0,
    0,    0,    0, -229,    0, -157, -229, -135, -130,    0,
 -128, -121, -107, -122, -124, -120,    0, -116, -114,    0,
    0, -107,    0, -117, -208, -105, -113,    0, -108, -127,
    0,    0, -100, -129, -106, -101, -181,    0,  -98,    0,
};
final static short yyrindex[] = {                       163,
    0,  -86,    0,    0,    0,  163,  163,  163,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  -81,    0,
    0,    0,    0,    0,    0,    0,  -80,  -71,    0,  -70,
  -69,  -68,  -67,    0,    0,    0,  -88,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0, -164,    0,    0,
    0,    0,    0,    0,    0, -147,    0,    0,    0,    0,
    0,    0,    0, -226, -224, -216,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0, -102,  -87,    0,
    0,    0,    0,    0,    0,    0,    0,    0, -132,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
   77,    0,    0,    0,  -19,    0,    0,  160,    0,  -47,
  -42,    0,    0,    0,    0,
};
final static int YYTABLESIZE=201;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         40,
   67,    9,   57,   58,   91,   92,   38,   50,   51,   59,
   52,   53,   54,   55,   77,   91,   98,   80,   64,   65,
   14,   83,   84,   34,   86,   59,   66,   57,  103,   39,
   48,   48,   49,   49,   59,   60,   94,   48,   35,   49,
   50,   50,   37,    1,    2,    3,  104,   50,   18,  106,
   36,   60,   19,   41,  118,   10,   11,   12,   42,   46,
   60,   20,   21,   48,   22,   49,   47,  102,   49,  116,
   23,   48,   24,   50,   25,   18,   43,   44,   45,   19,
   62,  128,   15,   16,   17,   63,   68,   79,   20,   21,
   81,   22,   36,   82,  117,  119,   36,   23,   36,   24,
   85,   25,   87,   88,   93,   36,   36,  129,   36,   25,
  100,   89,   90,   25,   36,   25,   36,   95,   36,   96,
   97,   99,   25,   25,   27,   25,  101,  105,   27,  107,
   27,   25,  112,   25,  108,   25,  109,   27,   27,   18,
   27,  110,  111,   19,  113,  114,   27,  115,   27,  121,
   27,  120,   20,   21,  122,   22,  124,  123,  126,  125,
  127,   23,    5,   24,  130,   25,   69,   70,   71,   72,
   73,   39,   39,   39,   39,   39,   60,   60,   74,   75,
   76,   21,   11,   39,   39,   39,   40,   40,   40,   40,
   40,    9,   15,   17,   19,   13,   56,    0,   40,   40,
   40,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         19,
   48,  262,  257,  258,  257,  258,  266,   27,   28,  264,
   30,   31,   32,   33,   57,  257,  258,   60,  257,  258,
  260,   64,   65,  257,   67,  264,  265,  257,  258,  289,
  257,  258,  257,  258,  264,  290,   79,  264,  257,  264,
  257,  258,  264,  267,  268,  269,   94,  264,  257,   97,
  257,  290,  261,  257,  263,  270,  271,  272,  257,  264,
  290,  270,  271,  290,  273,  290,  262,   87,  263,  112,
  279,  264,  281,  290,  283,  257,  257,  258,  259,  261,
  257,  263,    6,    7,    8,  263,  265,  290,  270,  271,
  266,  273,  257,  282,  114,  115,  261,  279,  263,  281,
  262,  283,  262,  286,  258,  270,  271,  127,  273,  257,
  263,  286,  286,  261,  279,  263,  281,  258,  283,  258,
  264,  258,  270,  271,  257,  273,  258,  285,  261,  265,
  263,  279,  257,  281,  265,  283,  265,  270,  271,  257,
  273,  263,  265,  261,  265,  262,  279,  262,  281,  263,
  283,  257,  270,  271,  263,  273,  257,  285,  265,  289,
  262,  279,    0,  281,  263,  283,  274,  275,  276,  277,
  278,  274,  275,  276,  277,  278,  265,  264,  286,  287,
  288,  263,  263,  286,  287,  288,  274,  275,  276,  277,
  278,  263,  263,  263,  263,  263,   37,   -1,  286,  287,
  288,
};
}
final static short YYFINAL=4;
final static short YYMAXTOKEN=290;
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
"ABRE_CHAVES","FECHA_CHAVES","ABRE_PARENTESES","FECHA_PARENTESES","ATRIBUICAO",
"FUNCAO_PRINCIPAL","FUNCAO","INCLUIR","INTEIRO","REAL","CARACTER","RETORNAR",
"DIVISAO","MULTIPLICACAO","SUBTRACAO","SOMA","PERCENT","LACO_PARA",
"LACO_ENQUANTO","LACO_FACA","ATE","SE","SENAO","PONTO_VIRGULA","IGUAL","MENOR",
"MAIOR","SOMA_APOS","RANDOM",
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
"laco_para : LACO_PARA ABRE_PARENTESES IDENTIFICADOR ATRIBUICAO NUMEROS PONTO_VIRGULA IDENTIFICADOR operador IDENTIFICADOR PONTO_VIRGULA IDENTIFICADOR SOMA_APOS FECHA_PARENTESES ABRE_CHAVES comandos FECHA_CHAVES",
"laco_para : LACO_PARA ABRE_PARENTESES IDENTIFICADOR ATRIBUICAO NUMEROS PONTO_VIRGULA IDENTIFICADOR operador IDENTIFICADOR PONTO_VIRGULA IDENTIFICADOR SOMA_APOS FECHA_PARENTESES ABRE_CHAVES comandos FECHA_CHAVES",
"laco_para : LACO_PARA ABRE_PARENTESES IDENTIFICADOR ATRIBUICAO NUMEROS PONTO_VIRGULA IDENTIFICADOR operador IDENTIFICADOR PONTO_VIRGULA IDENTIFICADOR SOMA_APOS FECHA_PARENTESES ABRE_CHAVES FECHA_CHAVES",
"laco_para : LACO_PARA ABRE_PARENTESES IDENTIFICADOR ATRIBUICAO NUMEROS PONTO_VIRGULA IDENTIFICADOR operador IDENTIFICADOR PONTO_VIRGULA IDENTIFICADOR SOMA_APOS FECHA_PARENTESES ABRE_CHAVES FECHA_CHAVES",
"laco_faca : LACO_FACA ABRE_CHAVES FECHA_CHAVES ATE ABRE_PARENTESES expressao FECHA_PARENTESES",
"atribuicao : IDENTIFICADOR ATRIBUICAO NUMEROS",
"atribuicao : IDENTIFICADOR ATRIBUICAO expressao",
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
"declaracao_funcao : INTEIRO IDENTIFICADOR",
"declaracao_funcao : REAL IDENTIFICADOR",
"declaracao_funcao : CARACTER IDENTIFICADOR",
"declaracao_funcao :",
"retornar : RETORNAR IDENTIFICADOR",
"retornar : RETORNAR NUMEROS",
"retornar : RETORNAR STRING",
};

//#line 138 "inicioCT.y"

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
//#line 378 "Parser.java"
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
//#line 58 "inicioCT.y"
{ System.out.println(val_peek(0).sval); }
break;
case 2:
//#line 60 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "\n" + val_peek(0).sval; }
break;
case 3:
//#line 61 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "\n" + val_peek(0).sval; }
break;
case 4:
//#line 62 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "\n" + val_peek(0).sval; }
break;
case 5:
//#line 63 "inicioCT.y"
{ yyval.sval = ""; }
break;
case 6:
//#line 65 "inicioCT.y"
{ yyval.sval = "int main() {\n " + val_peek(1).sval + "}\n"; }
break;
case 7:
//#line 67 "inicioCT.y"
{yyval.sval = "function " + val_peek(6).sval + " (" +  val_peek(4).sval + ") {\n " + val_peek(1).sval + "}\n"; }
break;
case 8:
//#line 69 "inicioCT.y"
{ yyval.sval = "#include " + val_peek(0).sval; }
break;
case 9:
//#line 71 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 10:
//#line 72 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + " " + val_peek(0).sval; }
break;
case 11:
//#line 73 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 12:
//#line 74 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + " " + val_peek(0).sval; }
break;
case 13:
//#line 75 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 14:
//#line 76 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + " " + val_peek(0).sval; }
break;
case 15:
//#line 77 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 16:
//#line 78 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + " " + val_peek(0).sval; }
break;
case 17:
//#line 79 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 18:
//#line 80 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + " " + val_peek(0).sval; }
break;
case 19:
//#line 81 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 20:
//#line 82 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + " " + val_peek(0).sval; }
break;
case 21:
//#line 83 "inicioCT.y"
{ yyval.sval = "println" + val_peek(0).sval + ";\n"; }
break;
case 22:
//#line 84 "inicioCT.y"
{ yyval.sval = "println" + val_peek(1).sval + ";\n" + val_peek(0).sval; }
break;
case 24:
//#line 87 "inicioCT.y"
{ yyval.sval = "if ( ) {\n }\n"; }
break;
case 25:
//#line 88 "inicioCT.y"
{ yyval.sval = "if ( )\n"; }
break;
case 26:
//#line 89 "inicioCT.y"
{ yyval.sval = "if (" + val_peek(5).sval + val_peek(4).sval + val_peek(3).sval + ") {\n }\n"; }
break;
case 27:
//#line 90 "inicioCT.y"
{ yyval.sval = "if (" + val_peek(3).sval + val_peek(2).sval + val_peek(1).sval + ")\n"; }
break;
case 28:
//#line 91 "inicioCT.y"
{ yyval.sval = "if (" + val_peek(6).sval + val_peek(5).sval + val_peek(4).sval + ") {\n " + val_peek(1).sval + "}\n"; }
break;
case 29:
//#line 92 "inicioCT.y"
{ yyval.sval = "if (" + val_peek(3).sval + val_peek(2).sval + val_peek(1).sval + ")\n"; }
break;
case 30:
//#line 93 "inicioCT.y"
{ yyval.sval = "if (" + val_peek(6).sval + val_peek(5).sval + val_peek(4).sval + ") {\n " + val_peek(1).sval + "}\n"; }
break;
case 31:
//#line 95 "inicioCT.y"
{ yyval.sval = "for (" + val_peek(13).sval + " = " + val_peek(11).sval + "; " + val_peek(9).sval + val_peek(8).sval + val_peek(7).sval + "; " + val_peek(5).sval + "++) {\n " + val_peek(1).sval + " }\n"; }
break;
case 32:
//#line 96 "inicioCT.y"
{ yyval.sval = "for (" + val_peek(13).sval + " = " + val_peek(11).sval + "; " + val_peek(9).sval + val_peek(8).sval+ val_peek(7).sval + "; " + val_peek(5).sval + "++) {\n " + val_peek(1).sval + " }\n"; }
break;
case 33:
//#line 97 "inicioCT.y"
{ yyval.sval = "for (" + val_peek(12).sval + " = " + val_peek(10).sval + "; " + val_peek(8).sval + val_peek(7).sval + val_peek(6).sval + "; " + val_peek(4).sval + "++) {\n }\n"; }
break;
case 34:
//#line 98 "inicioCT.y"
{ yyval.sval = "for (" + val_peek(12).sval + " = " + val_peek(10).sval + "; " + val_peek(8).sval + val_peek(7).sval + val_peek(6).sval + "; " + val_peek(4).sval + "++) {\n }\n"; }
break;
case 35:
//#line 100 "inicioCT.y"
{ yyval.sval = "faca {\n } ate (" + val_peek(1).sval+ ");\n"; }
break;
case 36:
//#line 102 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + " = " + val_peek(0).sval + ";\n"; }
break;
case 37:
//#line 103 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + " = " + val_peek(0).sval + ";\n"; }
break;
case 38:
//#line 105 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + val_peek(1).sval + val_peek(0).sval; }
break;
case 39:
//#line 106 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + val_peek(1).sval + val_peek(0).sval; }
break;
case 40:
//#line 107 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + val_peek(1).sval + val_peek(0).sval; }
break;
case 41:
//#line 108 "inicioCT.y"
{ yyval.sval = "( rand() " + val_peek(2).sval + val_peek(1).sval + ")"; }
break;
case 42:
//#line 109 "inicioCT.y"
{ yyval.sval = "rand() " + val_peek(1).sval + val_peek(0).sval; }
break;
case 43:
//#line 111 "inicioCT.y"
{ yyval.sval = "/"; }
break;
case 44:
//#line 112 "inicioCT.y"
{ yyval.sval = "*"; }
break;
case 45:
//#line 113 "inicioCT.y"
{ yyval.sval = "-"; }
break;
case 46:
//#line 114 "inicioCT.y"
{ yyval.sval = "+"; }
break;
case 47:
//#line 115 "inicioCT.y"
{ yyval.sval = "%"; }
break;
case 48:
//#line 116 "inicioCT.y"
{ yyval.sval = "="; }
break;
case 49:
//#line 117 "inicioCT.y"
{ yyval.sval = "<"; }
break;
case 50:
//#line 118 "inicioCT.y"
{ yyval.sval = ">"; }
break;
case 51:
//#line 119 "inicioCT.y"
{ yyval.sval = "<="; }
break;
case 52:
//#line 120 "inicioCT.y"
{ yyval.sval = ">="; }
break;
case 53:
//#line 121 "inicioCT.y"
{ yyval.sval = "=="; }
break;
case 54:
//#line 123 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "++" + "\n"; }
break;
case 55:
//#line 125 "inicioCT.y"
{  yyval.sval = "int " + val_peek(0).sval + ";\n"; }
break;
case 56:
//#line 126 "inicioCT.y"
{  yyval.sval = "float " + val_peek(0).sval + ";\n"; }
break;
case 57:
//#line 128 "inicioCT.y"
{  yyval.sval = "int " + val_peek(0).sval; }
break;
case 58:
//#line 129 "inicioCT.y"
{  yyval.sval = "float " + val_peek(0).sval; }
break;
case 59:
//#line 130 "inicioCT.y"
{  yyval.sval = "char " + val_peek(0).sval; }
break;
case 60:
//#line 131 "inicioCT.y"
{ yyval.sval = ""; }
break;
case 61:
//#line 133 "inicioCT.y"
{ yyval.sval = "return " + val_peek(0).sval + ";\n"; }
break;
case 62:
//#line 134 "inicioCT.y"
{ yyval.sval = "return " + val_peek(0).sval + ";\n"; }
break;
case 63:
//#line 135 "inicioCT.y"
{ yyval.sval = "return " + val_peek(0).sval + ";\n"; }
break;
//#line 775 "Parser.java"
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
