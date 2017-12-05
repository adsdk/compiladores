%{
	import java.io.*;
	import java.util.*;
%}

/* BYACC Declarations */
%token <sval> IDENTIFICADOR
%token <sval> NUMEROS
%token <sval> STRING
%token <sval> INCLUSAO_ARQUIVO
%token <sval> IMPRIMA
%token ABRE_CHAVES
%token FECHA_CHAVES
%token ABRE_COLCHETES
%token FECHA_COLCHETES
%token ABRE_PARENTESES
%token FECHA_PARENTESES
%token ATRIBUICAO
%token FUNCAO_PRINCIPAL
%token FUNCAO
%token INCLUIR
%token INTEIRO
%token REAL
%token CARACTER
%token RETORNAR
%token DIVISAO
%token MULTIPLICACAO
%token SUBTRACAO
%token SOMA
%token PERCENT
%token LACO_PARA
%token LACO_ENQUANTO
%token LACO_FACA
%token CASO
%token BEGIN_CASO
%token OPCAO
%token FIM_OPCAO
%token ATE
%token SE
%token SENAO
%token PONTO_VIRGULA
%token IGUAL
%token MENOR
%token MAIOR
%token SOMA_APOS
%token RANDOM
%type <sval> programa
%type <sval> funcao_principal
%type <sval> funcao
%type <sval> inclusao
%type <sval> comandos
%type <sval> atribuicao
%type <sval> declaracao
%type <sval> declaracao_funcao
%type <sval> retornar
%type <sval> expressao
%type <sval> operador
%type <sval> laco_para
%type <sval> laco_faca
%type <sval> laco_enquanto
%type <sval> se
%type <sval> senao
%type <sval> caso
%type <sval> opcao
%type <sval> incremento

%%
inicio : programa	 { System.out.println($1); }

programa : inclusao programa	{ $$ = $1 + "\n" + $2; }
		 | funcao_principal programa { $$ = $1 + "\n" + $2; }
		 | funcao programa { $$ = $1 + "\n" + $2; }
	     |					{ $$ = ""; }

funcao_principal : FUNCAO_PRINCIPAL ABRE_CHAVES comandos FECHA_CHAVES { $$ = "int main() {\n " + $3 + "}\n"; }

funcao: FUNCAO declaracao_funcao ABRE_PARENTESES declaracao_funcao FECHA_PARENTESES ABRE_CHAVES comandos FECHA_CHAVES {$$ = $2 + " (" +  $4 + ") {\n " + $7 + "}\n"; }

inclusao : INCLUIR INCLUSAO_ARQUIVO	{ $$ = "#include " + $2; }

comandos : declaracao		{ $$ = $1; }
		 | declaracao comandos { $$ = $1 + " " + $2; }
		 | atribuicao { $$ = $1; }
		 | atribuicao comandos { $$ = $1 + " " + $2; }
		 | incremento { $$ = $1; }
		 | incremento comandos { $$ = $1 + " " + $2; }
		 | laco_para{ $$ = $1; }
		 | laco_para comandos { $$ = $1 + " " + $2; }
		 | laco_faca { $$ = $1; }
		 | laco_faca comandos { $$ = $1 + " " + $2; }
		 | laco_enquanto { $$ = $1; }
		 | laco_enquanto comandos { $$ = $1 + " " + $2; }
		 | caso { $$ = $1; }
		 | caso comandos { $$ = $1 + " " + $2; }
		 | se { $$ = $1; }
		 | se comandos { $$ = $1 + " " + $2; }
		 | IMPRIMA { $$ = "printf" + $1 + ";\n"; }
		 | IMPRIMA comandos { $$ = "printf" + $1 + ";\n" + $2; }
		 | retornar
		 
se : SE ABRE_PARENTESES FECHA_PARENTESES ABRE_CHAVES FECHA_CHAVES { $$ = "if ( ) {\n }\n"; }
   | SE ABRE_PARENTESES FECHA_PARENTESES { $$ = "if ( )\n"; }
   | SE ABRE_PARENTESES expressao operador NUMEROS FECHA_PARENTESES ABRE_CHAVES FECHA_CHAVES { $$ = "if (" + $3 + $4 + $5 + ") {\n }\n"; }
   | SE ABRE_PARENTESES expressao operador NUMEROS FECHA_PARENTESES { $$ = "if (" + $3 + $4 + $5 + ")\n"; }
   | SE ABRE_PARENTESES expressao operador NUMEROS FECHA_PARENTESES ABRE_CHAVES comandos FECHA_CHAVES { $$ = "if (" + $3 + $4 + $5 + ") {\n " + $8 + "}\n"; }
   | SE ABRE_PARENTESES IDENTIFICADOR operador NUMEROS FECHA_PARENTESES { $$ = "if (" + $3 + $4 + $5 + ")\n"; }
   | SE ABRE_PARENTESES NUMEROS operador NUMEROS FECHA_PARENTESES ABRE_CHAVES comandos FECHA_CHAVES { $$ = "if (" + $3 + $4 + $5 + ") {\n " + $8 + "}\n"; }
   | SE ABRE_PARENTESES IDENTIFICADOR operador NUMEROS FECHA_PARENTESES ABRE_CHAVES comandos FECHA_CHAVES { $$ = "if (" + $3 + $4 + $5 + ") {\n " + $8 + "}\n"; }
   | SE ABRE_PARENTESES NUMEROS operador NUMEROS FECHA_PARENTESES ABRE_CHAVES comandos FECHA_CHAVES senao { $$ = "if (" + $3 + $4 + $5 + ") {\n " + $8 + "} " + $10; }
   | SE ABRE_PARENTESES IDENTIFICADOR operador NUMEROS FECHA_PARENTESES ABRE_CHAVES comandos FECHA_CHAVES senao { $$ = "if (" + $3 + $4 + $5 + ") {\n " + $8 + "} " + $10; }
   
senao : SENAO ABRE_CHAVES comandos FECHA_CHAVES { $$ = "else {\n" + $3 + "}\n"; }
		 
laco_para : LACO_PARA ABRE_PARENTESES IDENTIFICADOR ATRIBUICAO NUMEROS PONTO_VIRGULA IDENTIFICADOR operador IDENTIFICADOR PONTO_VIRGULA IDENTIFICADOR SOMA_APOS FECHA_PARENTESES ABRE_CHAVES comandos FECHA_CHAVES { $$ = "for (" + $3 + " = " + $5 + "; " + $7 + $8 + $9 + "; " + $11 + "++) {\n " + $15 + " }\n"; }
          | LACO_PARA ABRE_PARENTESES IDENTIFICADOR ATRIBUICAO NUMEROS PONTO_VIRGULA IDENTIFICADOR operador IDENTIFICADOR PONTO_VIRGULA IDENTIFICADOR SOMA_APOS FECHA_PARENTESES ABRE_CHAVES comandos FECHA_CHAVES { $$ = "for (" + $3 + " = " + $5 + "; " + $7 + $8+ $9 + "; " + $11 + "++) {\n " + $15 + " }\n"; }
          | LACO_PARA ABRE_PARENTESES IDENTIFICADOR ATRIBUICAO NUMEROS PONTO_VIRGULA IDENTIFICADOR operador IDENTIFICADOR PONTO_VIRGULA IDENTIFICADOR SOMA_APOS FECHA_PARENTESES ABRE_CHAVES FECHA_CHAVES { $$ = "for (" + $3 + " = " + $5 + "; " + $7 + $8 + $9 + "; " + $11 + "++) {\n }\n"; }
		  | LACO_PARA ABRE_PARENTESES IDENTIFICADOR ATRIBUICAO NUMEROS PONTO_VIRGULA IDENTIFICADOR operador IDENTIFICADOR PONTO_VIRGULA IDENTIFICADOR SOMA_APOS FECHA_PARENTESES ABRE_CHAVES FECHA_CHAVES { $$ = "for (" + $3 + " = " + $5 + "; " + $7 + $8 + $9 + "; " + $11 + "++) {\n }\n"; }
		  
laco_faca : LACO_FACA ABRE_CHAVES FECHA_CHAVES ATE ABRE_PARENTESES expressao FECHA_PARENTESES { $$ = "do {\n } while (" + $6+ ");\n"; }
		  | LACO_FACA ABRE_CHAVES comandos FECHA_CHAVES ATE ABRE_PARENTESES expressao FECHA_PARENTESES { $$ = "do {\n" + $3 + " } while (" + $7+ ");\n"; }

laco_enquanto : LACO_ENQUANTO ABRE_PARENTESES expressao FECHA_PARENTESES ABRE_CHAVES FECHA_CHAVES { $$ = "while (" + $3+ ") {\n}\n"; }
              | LACO_ENQUANTO ABRE_PARENTESES expressao FECHA_PARENTESES ABRE_CHAVES comandos FECHA_CHAVES { $$ = "while (" + $3+ ") {\n" + $6 + "}\n"; }
			  
caso : CASO ABRE_PARENTESES IDENTIFICADOR ABRE_COLCHETES IDENTIFICADOR FECHA_COLCHETES FECHA_PARENTESES ABRE_CHAVES FECHA_CHAVES { $$ = "switch (" + $3 + "[" + $5 + "]) {\n }\n"; }
     | CASO ABRE_PARENTESES IDENTIFICADOR ABRE_COLCHETES IDENTIFICADOR FECHA_COLCHETES FECHA_PARENTESES ABRE_CHAVES opcao FECHA_CHAVES { $$ = "switch (" + $3 + "[" + $5 + "]) {\n" + $9 + "}\n"; }

opcao: OPCAO STRING BEGIN_CASO comandos FIM_OPCAO { $$ = "case " + $2 + ":\n" + $4 + "break;\n"; }
     | OPCAO STRING BEGIN_CASO comandos FIM_OPCAO opcao { $$ = "case " + $2 + ":\n" + $4 + "break;\n" + $6; }

atribuicao : IDENTIFICADOR ATRIBUICAO NUMEROS { $$ = $1 + " = " + $3 + ";\n"; }
           | IDENTIFICADOR ATRIBUICAO expressao { $$ = $1 + " = " + $3 + ";\n"; }
		   | IDENTIFICADOR ABRE_COLCHETES IDENTIFICADOR FECHA_COLCHETES ATRIBUICAO IDENTIFICADOR ABRE_PARENTESES FECHA_PARENTESES { $$ = $1 + "[" + $3 + "] = " + $6 + "();\n"; }
		   | IDENTIFICADOR ABRE_COLCHETES IDENTIFICADOR FECHA_COLCHETES ATRIBUICAO IDENTIFICADOR ABRE_PARENTESES IDENTIFICADOR ABRE_COLCHETES IDENTIFICADOR FECHA_COLCHETES 
		   FECHA_PARENTESES { $$ = $1 + "[" + $3 + "] = " + $6 + "(" + $8+ "[" + $10 + "]);\n"; }
		   
expressao : IDENTIFICADOR operador IDENTIFICADOR { $$ = $1 + $2 + $3; }
          | IDENTIFICADOR operador NUMEROS { $$ = $1 + $2 + $3; }
		  | NUMEROS operador NUMEROS { $$ = $1 + $2 + $3; }
          | ABRE_PARENTESES RANDOM operador expressao FECHA_PARENTESES { $$ = "( rand() " + $3 + $4 + ")"; }
		  | RANDOM operador NUMEROS { $$ = "rand() " + $2 + $3; }

operador: DIVISAO { $$ = "/"; }
        | MULTIPLICACAO { $$ = "*"; }
		| SUBTRACAO { $$ = "-"; }
		| SOMA { $$ = "+"; }
		| PERCENT { $$ = "%"; }
		| IGUAL { $$ = "="; }
		| MENOR { $$ = "<"; }
		| MAIOR { $$ = ">"; }
		| MENOR IGUAL { $$ = "<="; }
		| MAIOR IGUAL { $$ = ">="; }
		| IGUAL IGUAL { $$ = "=="; }
		
incremento: IDENTIFICADOR SOMA_APOS { $$ = $1 + "++;\n"; }
		 
declaracao : INTEIRO IDENTIFICADOR	{  $$ = "int " + $2 + ";\n"; }
		   | REAL IDENTIFICADOR {  $$ = "float " + $2 + ";\n"; }
		   | CARACTER IDENTIFICADOR ABRE_COLCHETES IDENTIFICADOR FECHA_COLCHETES {  $$ = "char " + $2 + "[" + $4 + "];\n"; }
		   | INTEIRO IDENTIFICADOR ABRE_COLCHETES IDENTIFICADOR FECHA_COLCHETES {  $$ = "int " + $2 + "[" + $4 + "];\n"; }
		   | REAL IDENTIFICADOR ABRE_COLCHETES IDENTIFICADOR FECHA_COLCHETES {  $$ = "float " + $2 + "[" + $4 + "];\n"; }
		   
declaracao_funcao : INTEIRO IDENTIFICADOR	{  $$ = "int " + $2; }
		   | REAL IDENTIFICADOR {  $$ = "float " + $2; }
		   | CARACTER IDENTIFICADOR {  $$ = "char " + $2; }
		   | { $$ = ""; }
		   
retornar : RETORNAR IDENTIFICADOR { $$ = "return " + $2 + ";\n"; }
         | RETORNAR NUMEROS { $$ = "return " + $2 + ";\n"; }
		 | RETORNAR STRING  { $$ = "return " + $2 + ";\n"; }

%%

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
