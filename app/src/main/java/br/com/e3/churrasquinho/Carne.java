package br.com.e3.churrasquinho;

public class Carne {
    private long idCarne;
    private String nomeCarne;
    private double valorCarne;
    private String tipoCarne;
    private boolean marcado;

    public Carne(long idCarne, String nomeCarne, double valorCarne, String tipoCarne){
        this.setIdCarne(idCarne);
        this.setNomeCarne(nomeCarne);
        this.setValorCarne(valorCarne);
        this.setTipoCarne(tipoCarne);
    }

    public long getIdCarne() {
        return idCarne;
    }

    public void setIdCarne(long idCarne) {
        this.idCarne = idCarne;
    }

    public String getNomeCarne() {
        return nomeCarne;
    }

    public void setNomeCarne(String nomeCarne) {
        this.nomeCarne = nomeCarne;
    }

    public double getValorCarne() {
        return valorCarne;
    }

    public void setValorCarne(double valorCarne) {
      this.valorCarne = valorCarne;
    }

    public String getTipoCarne() {return tipoCarne; }

    public void setTipoCarne(String tipoCarne){this.tipoCarne = tipoCarne; }

    public boolean isMarcado() {
        return marcado;
    }

    public void setMarcado(boolean marcado) {
        this.marcado = marcado;
    }

}

