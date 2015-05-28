package br.com.e3.churrasquinho;

/**
 * Created by Eduardo on 08/04/2015.
 */
public class Outro {

    private long idOutro;
    private String nomeOutro;
    private double valorOutro;
    private boolean marcado;

    public Outro(){}
    public Outro(long idOutro, String nomeOutro, double valorOutro){
        this.setIdOutro(idOutro);
        this.setNomeOutro(nomeOutro);
        this.setValorOutro(valorOutro);
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

    public boolean isMarcado() {
        return marcado;
    }

    public void setMarcado(boolean marcado) {
        this.marcado = marcado;
    }


}
