package br.com.e3.churrasquinho;

public class Bebida {
    private long idBebida;
    private String nomeBebida;
    private double valorBebida;
    private String tipoBebida;
    private String categoria;
    private boolean marcado;

    public Bebida(long idBebida, String nomeBebida, double valorBebida, String tipoBebida, String categoria){
        this.setIdBebida(idBebida);
        this.setNomeBebida(nomeBebida);
        this.setValorBebida(valorBebida);
        this.setTipoBebida(tipoBebida);
        this.setCategoria(categoria);
    }

    public long getIdBebida() {
        return idBebida;
    }

    public void setIdBebida(long idBebida) {
        this.idBebida = idBebida;
    }

    public String getNomeBebida() {
        return nomeBebida;
    }

    public void setNomeBebida(String nomeBebida) {
        this.nomeBebida = nomeBebida;
    }

    public double getValorBebida() {
        return valorBebida;
    }

    public void setValorBebida(double valorBebida) {
        this.valorBebida = valorBebida;
    }

    public String getTipoBebida() {
        return tipoBebida;
    }

    public void setTipoBebida(String tipoBebida) {
        this.tipoBebida = tipoBebida;
    }

    public String getCategoria(){
        return categoria;
    }

    public void setCategoria(String categoria){
        this.categoria = categoria;
    }

    public boolean isMarcado() {
        return marcado;
    }

    public void setMarcado(boolean marcado) {
        this.marcado = marcado;
    }

}


