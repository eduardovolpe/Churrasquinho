package br.com.e3.churrasquinho;

public class Acompanhamento {
    private long idAcompanhamento;
    private String nomeAcompanhamento;
    private double valorAcompanhamento;
    private String tipoAcompanhamento;
    private boolean marcado;

    public Acompanhamento(long idAcompanhamento, String nomeAcompanhamento, double valorAcompanhamento, String tipoAcompanhamento){
        this.setIdAcompanhamento(idAcompanhamento);
        this.setNomeAcompanhamento(nomeAcompanhamento);
        this.setValorAcompanhamento(valorAcompanhamento);
        this.setTipoAcompanhamento(tipoAcompanhamento);
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

    public String getTipoAcompanhamento() {
        return tipoAcompanhamento;
    }

    public void setTipoAcompanhamento(String tipoAcompanhamento) {
        this.tipoAcompanhamento = tipoAcompanhamento;
    }


    public boolean isMarcado() {
        return marcado;
    }

    public void setMarcado(boolean marcado) {
        this.marcado = marcado;
    }

}
