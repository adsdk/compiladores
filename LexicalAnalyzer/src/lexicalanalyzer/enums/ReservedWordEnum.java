package lexicalanalyzer.enums;

/**
 *
 * @author adrisson
 */
public enum ReservedWordEnum {
    INT("int"), DOUBLE("double"), FLOAT("float"), REAL("real"), BREAK("break"), CASE("case"), CHAR("char"), CONST("const"), CONTINUE("continue");

    public String word;

    ReservedWordEnum(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

}
