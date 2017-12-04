import java.io.*;

%%

%byaccj

%{

	// Armazena uma referencia para o parser
	private Parser yyparser;

	// Construtor recebendo o parser como parametro adicional
	public Yylex(Reader r, Parser yyparser){
		this(r);
		this.yyparser = yyparser;
	}	

%}

NL = \n | \r | \r\n

%%

"//".* { }
[/][*][^*]*[*]+([^*/][^*]*[*]+)*[/] { }
funcao_principal { return Parser.FUNCAO_PRINCIPAL; }
funcao {return Parser.FUNCAO; }
para { return Parser.LACO_PARA; }
enquanto { return Parser.LACO_ENQUANTO; }
faca { return Parser.LACO_FACA; }
ate { return Parser.ATE; }
se { return Parser.SE; }
senao { return Parser.SENAO; }
incluir	{ return Parser.INCLUIR; }
inteiro { return Parser.INTEIRO; }
real { return Parser.REAL; }
caracter { return Parser.CARACTER; }
retornar { return Parser.RETORNAR; }
"rand()" { return Parser.RANDOM; }
\<.*\>	{ yyparser.yylval = new ParserVal(yytext());
			return Parser.INCLUSAO_ARQUIVO; }
imprima(.*) { yyparser.yylval = new ParserVal(yytext().replace("imprima", ""));
		  return Parser.IMPRIMA; }
"{"	{ return Parser.ABRE_CHAVES; }
"}" { return Parser.FECHA_CHAVES; }
"("	{ return Parser.ABRE_PARENTESES; }
")"	{ return Parser.FECHA_PARENTESES; }
":=" { return Parser.ATRIBUICAO; }
"/" { return Parser.DIVISAO; }
"*" { return Parser.MULTIPLICACAO; }
"-" { return Parser.SUBTRACAO; }
"+" { return Parser.SOMA; }
"%" { return Parser.PERCENT; }
";" { return Parser.PONTO_VIRGULA; }
"<" { return Parser.MENOR; }
">" { return Parser.MAIOR; }
"=" { return Parser.IGUAL; }
"++" { return Parser.SOMA_APOS; }
".*"|'.*' { yyparser.yylval = new ParserVal(yytext());
		return Parser.STRING; }
[0-9]+	{ yyparser.yylval = new ParserVal(yytext());
		return Parser.NUMEROS; }
[a-zA-Z][a-zA-Z0-9]* { yyparser.yylval = new ParserVal(yytext());
		return Parser.IDENTIFICADOR; }
{NL}|" "|\t	{  }
