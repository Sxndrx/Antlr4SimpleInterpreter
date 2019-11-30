grammar Interpreter;

@lexer::members{
    public static int SPACE_CHANNEL = 1;
    public static int OTHER_CHANNEL = 2;
}

@parser::members{
    public static int SPACE_CHANNEL = 1;
    public static int OTHER_CHANNEL = 2;
    boolean isSpace()
     {
        int idx = getCurrentToken().getTokenIndex();
        int ch;
        do
        {
            ch = getTokenStream().get(--idx).getChannel();
        }
        while (ch == OTHER_CHANNEL);

        // Channel 1 is only carrying EOL, no need to check token itself
        return (ch == SPACE_CHANNEL);
     }
}

instruction
    : (MOV {isSpace()}? expression COMA register)             #mov
    | (XOR {isSpace()}? expression COMA register)                #xor
    | (INT {isSpace()}? int_function)                                #int
    | (PUSH {isSpace()}? (expression))                               #push
    ;

int_function
    :PRINT_STACK
    ;

expression
    : LPAR expression RPAR                                 #parExp
    | left=expression MULT right=expression                #multExp
    | left=expression (PLUS | MINUS) right=expression      #addSubExp
    | number                                               #numb
    | register                                             #reg
    ;

number
    : NUMBER
    ;

register
    : EBX
    | ECX
    | EDX
    ;

fragment B
    : ('b' | 'B')
    ;
fragment C
    : ('c' | 'C')
    ;
fragment D
    : ('d' | 'D')
    ;
fragment E
    : ('e' | 'E')
    ;
fragment H
    : ('h' | 'H')
    ;
fragment I
    : ('i' | 'I')
    ;
fragment M
    : ('m' | 'M')
    ;
fragment N
    : ('n' | 'N')
    ;
fragment O
    : ('o' | 'O')
    ;
fragment P
    : ('p' | 'P')
    ;
fragment R
    : ('r' | 'R')
    ;
fragment S
    : ('s' | 'S')
    ;
fragment T
    : ('t' | 'T')
    ;
fragment U
    : ('u' | 'U')
    ;
fragment V
    : ('v' | 'V')
    ;
fragment X
    : ('x' | 'X')
    ;
fragment PROCSIGN
    : '%'
    ;

//opcodes
MOV
    :M O V
    ;
PUSH
    :P U S H
    ;
XOR
    :X O R
    ;
INT
    :I N T
    ;

//registers
EDX
    :PROCSIGN E D X
    ;
ECX
    :PROCSIGN E C X
    ;
EBX
    :PROCSIGN E B X
    ;

//int function
PRINT_STACK
    : '0x80'
    ;

PLUS
    : '+'
    ;
MINUS
    : '-'
    ;
MULT
    : '*'
    ;
LPAR
    : '('
    ;
RPAR
    : ')'
    ;
COMA
    : ','
    ;

NUMBER
    : [0-9] +
    ;
EOL
    : [\r\n] +
    ;
WS
    : [\t] -> channel(2)
    ;
SP
    : ' ' -> channel(1)
    ;
