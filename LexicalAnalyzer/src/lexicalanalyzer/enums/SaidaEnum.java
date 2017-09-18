package lexicalanalyzer.enums;

/**
 *
 * @author adrisson
 */
public enum SaidaEnum {
    INT("INT"),
    DOUBLE("DOUBLE"),
    FLOAT("FLOAT"),
    REAL("REAL"),
    BREAK("BREAK"),
    CASE("CASE"),
    CHAR("CHAR"),
    CONST("CONST"),
    CONTINUE("CONTINUE"),
    IDENTIFICADOR("IDENTIFICADOR"),
    COMENTARIO("COMENTÁRIO"),
    NUMERO_INTEIRO("NÚMERO INTEIRO"),
    NUMERO_REAL("NÚMERO REAL"),
    STRING("STRING");

    public String descricao;

    SaidaEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getString() {
        return descricao;
    }

}
