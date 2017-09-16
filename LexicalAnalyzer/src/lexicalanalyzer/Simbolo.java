package lexicalanalyzer;

/**
 *
 * @author adrisson
 */
public class Simbolo {

    private Integer id;
    private String descricao;

    public Simbolo(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.id).append(" - ").append(this.descricao);
        return sb.toString();
    }

}
