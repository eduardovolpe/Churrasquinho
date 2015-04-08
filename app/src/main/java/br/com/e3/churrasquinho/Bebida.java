package br.com.e3.churrasquinho;

/**
 * Created by Eduardo on 08/04/2015.
 */
public class Bebida {
    private long idBebida;
    private String nomeBebida;
    private double valorBebida;

    public Bebida(){}
    public Bebida(long idBebida, String nomeBebida, double valorBebida){
        this.setIdBebida(idBebida);
        this.setNomeBebida(nomeBebida);
        this.setValorBebida(valorBebida);
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

}


