package br.com.e3.churrasquinho;

/**
 * Created by Eduardo on 06/04/2015.
 */
public class Carne {
    private long idCarne;
    private String nomeCarne;
    private double valorCarne;

    public Carne(){}
    public Carne(long idCarne, String nomeCarne, double valorCarne){
        this.setIdCarne(idCarne);
        this.setNomeCarne(nomeCarne);
        this.setValorCarne(valorCarne);
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

}

