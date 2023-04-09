package codigo;
import static codigo.Tokens.*;
%%
%class Lexer
%type Tokens
E = [(]*([-+]?\(*[0-9]+\)*([-+*/]\(*[0-9]+\)*)*)[)]*
espacio=[ ,\t,\r,\n]+
D = [0-9]+
%{
    public String lexeme;
%}
%%
{espacio} {/*Ignore*/}
"//".* {/*Ignore*/}
{E} {lexeme=yytext(); return ExpresionMatematica;}
 . {return ERROR;}
