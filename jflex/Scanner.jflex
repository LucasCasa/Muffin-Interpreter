package Example;

import java_cup.runtime.SymbolFactory;
%%
%cup
%class Scanner
%{
	public Scanner(java.io.InputStream r, SymbolFactory sf){
		this(r);
		this.sf=sf;
	}
	private SymbolFactory sf;
%}
%eofval{
    return sf.newSymbol("EOF",sym.EOF);
%eofval}

%%
\/\*.*\*\/ { /* ignore white space. */ }
\"[^\"]*\" {return sf.newSymbol("Literal",sym.LIT,new String(yytext())); }
";" 		{ return sf.newSymbol("Semicolon",sym.SEMI); }
("AND"|"and"|"&&") 	{ return sf.newSymbol("And",sym.AND); }
("OR"|"or"|"||") 		{ return sf.newSymbol("Or",sym.OR); }
("NOT"|"not"|"!")	{ return sf.newSymbol("Not",sym.NOT); }
"+" 		{ return sf.newSymbol("Plus",sym.PLUS); }
"-" 		{ return sf.newSymbol("Minus",sym.MINUS); }
"*" 		{ return sf.newSymbol("Times",sym.TIMES); }
"**" 		{ return sf.newSymbol("Power",sym.POW); }
"$"			{ return sf.newSymbol("Dollar",sym.DOLLAR);}
"\/" 		{ return sf.newSymbol("Division",sym.DIV); }
"="			{ return sf.newSymbol("Equals",sym.EQUALS); }
"%" 		{ return sf.newSymbol("Module",sym.MOD); }
">"			{ return sf.newSymbol("gt",sym.GT);}
"<"			{ return sf.newSymbol("lt",sym.LT);}
">="		{ return sf.newSymbol("ge",sym.GE);}
"<="		{ return sf.newSymbol("le",sym.LE);}
"=="		{ return sf.newSymbol("eq",sym.EQ);}
"!="		{ return sf.newSymbol("diff",sym.DIFF);}
"<-"		{	return sf.newSymbol("assign",sym.ASSIGN);}
"["			{ return sf.newSymbol("Open Straight Bracket",sym.SLBRACKET);}
"]"			{ return sf.newSymbol("Close Straight Bracket",sym.SRBRACKET);}
"(" 		{ return sf.newSymbol("Left Parentesis",sym.LPAREN); }
")" 		{ return sf.newSymbol("Right Parentesis",sym.RPAREN); }
"," 		{ return sf.newSymbol("Comma",sym.COMMA); }
"return" { return sf.newSymbol("return",sym.RETURN); }
"void" 		{ return sf.newSymbol("void",sym.VOID); }

[0-9]+\.[0-9]+ { return sf.newSymbol("Double Number",sym.DOUBLE, new Double(yytext())); }

[0-9]+ 	{ return sf.newSymbol("Integral Number",sym.NUMBER, new Integer(yytext())); }

"true"  { return sf.newSymbol("True",sym.TRUE, new Boolean(yytext())); }
"if" 		{ return sf.newSymbol("IF",sym.IF);}
"else"	{ return sf.newSymbol("Else",sym.ELSE);}
"while"	{ return sf.newSymbol("While",sym.WHILE);}
"{"			{ return sf.newSymbol("Open Bracket",sym.LBRACKET);}
"}" 		{ return sf.newSymbol("Close Bracket",sym.RBRACKET);}
"false" { return sf.newSymbol("False",sym.FALSE, new Boolean(yytext())); }
">>"		{ return sf.newSymbol("Start Printer",sym.PRINTER); }
"<<"		{ return sf.newSymbol("Read",sym.READ); }
"int"  	{ return sf.newSymbol("Integer",sym.INT); }
"ints"	{ return sf.newSymbol("AInteger",sym.AINT);}
"double" { return sf.newSymbol("Double", sym.DOUB);}
"doubles" {return sf.newSymbol("ADouble", sym.ADOUB);}
"bool" { return sf.newSymbol("Bool", sym.BOOL);}
"bools"	{ return sf.newSymbol("ABool", sym.ABOOL);}
(s|S)(tr(ing)?) { return sf.newSymbol("String",sym.STR);}
(s|S)(tr(ing)?s) { return sf.newSymbol("AString",sym.ASTR);}
"rand"	{ return sf.newSymbol("Rand",sym.RAND);}
[a-zA-z][0-9a-zA-z]* {return sf.newSymbol("Variable",sym.VAR,yytext()); }

[ \t\r\n\f] { /* ignore white space. */ }
. { System.err.println("Illegal character: "+yytext()); }
