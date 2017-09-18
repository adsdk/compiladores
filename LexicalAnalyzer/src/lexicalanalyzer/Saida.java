package lexicalanalyzer;

import lexicalanalyzer.enums.SaidaEnum;

/**
 *
 * @author adrisson
 */
public class Saida {
    
    private Integer id;
    private SaidaEnum saida;
    private Integer simboloId;

    public Saida(Integer id, SaidaEnum saida) {
        this.id = id;
        this.saida = saida;
    }
    
    public Saida(Integer id, SaidaEnum saida, Integer simboloId) {
        this.id = id;
        this.saida = saida;
        this.simboloId = simboloId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SaidaEnum getSaida() {
        return saida;
    }

    public void setSaida(SaidaEnum saida) {
        this.saida = saida;
    }

    public Integer getSimboloId() {
        return simboloId;
    }

    public void setSimboloId(Integer simboloId) {
        this.simboloId = simboloId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(this.id).append("]").append(" ").append(this.saida.getString());
        
        if (this.simboloId != null) {
            sb.append(" ").append(this.simboloId);
        }
        
        return sb.toString();
    }
    
}
