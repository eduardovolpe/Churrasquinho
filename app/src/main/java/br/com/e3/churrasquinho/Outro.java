package br.com.e3.churrasquinho;

public class Outro {

    private long idOutro;
    private String nomeOutro;
    private double valorOutro;
    private String tipoOutro;
    private boolean marcado;


    public Outro(long idOutro, String nomeOutro, double valorOutro, String tipoOutro){
        this.setIdOutro(idOutro);
        this.setNomeOutro(nomeOutro);
        this.setValorOutro(valorOutro);
        this.setTipoOutro(tipoOutro);
    }

    public long getIdOutro() {
        return idOutro;
    }

    public void setIdOutro(long idOutro) {
        this.idOutro = idOutro;
    }

    public String getNomeOutro() {
        return nomeOutro;
    }

    public void setNomeOutro(String nomeOutro) {
        this.nomeOutro = nomeOutro;
    }

    public double getValorOutro() {
        return valorOutro;
    }

    public void setValorOutro(double valorOutro) {
        this.valorOutro = valorOutro;
    }

    public String getTipoOutro() {
        return tipoOutro;
    }

    public void setTipoOutro(String tipoOutro) {
        this.tipoOutro = tipoOutro;
    }

    public boolean isMarcado() {
        return marcado;
    }

    public void setMarcado(boolean marcado) {
        this.marcado = marcado;
    }


}
