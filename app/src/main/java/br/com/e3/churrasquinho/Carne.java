package br.com.e3.churrasquinho;

/**
 * Created by Eduardo on 06/04/2015.
 */
public class Carne {

    private long id;
    private String nomeCarne;
    private double valorCarne;

    public Carne(){}
    public Carne(long id, String nomeCarne, double valorCarne){
        this.setId(id);
        this.setNomeCarne(nomeCarne);
        this.setValorCarne(valorCarne);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

