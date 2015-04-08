package br.com.e3.churrasquinho;

/**
 * Created by Eduardo on 08/04/2015.
 */
public class Acompanhamento {
    private long idAcompanhamento;
    private String nomeAcompanhamento;
    private double valorAcompanhamento;

    public Acompanhamento(){}
    public Acompanhamento(long idAcompanhamento, String nomeAcompanhamento, double valorAcompanhamento){
        this.setIdAcompanhamento(idAcompanhamento);
        this.setNomeAcompanhamento(nomeAcompanhamento);
        this.setValorAcompanhamento(valorAcompanhamento);
    }

    public long getIdAcompanhamento() {
        return idAcompanhamento;
    }

    public void setIdAcompanhamento(long idAcompanhamento) {
        this.idAcompanhamento = idAcompanhamento;
    }

    public String getNomeAcompanhamento() {
        return nomeAcompanhamento;
    }

    public void setNomeAcompanhamento(String nomeAcompanhamento) {
        this.nomeAcompanhamento = nomeAcompanhamento;
    }

    public double getValorAcompanhamento() {
        return valorAcompanhamento;
    }

    public void setValorAcompanhamento(double valorAcompanhamento) {
        this.valorAcompanhamento = valorAcompanhamento;
    }

}
