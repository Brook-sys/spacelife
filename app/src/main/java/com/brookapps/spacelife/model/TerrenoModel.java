package com.brookapps.spacelife.model;

import android.app.Application;

import java.io.Serializable;

public class TerrenoModel implements Serializable {
    private int tamanhoTerreno;
    private int pacoteDesejado;
    private int tipoTerreno;
    private String precoTerreno = "";
    private String numTerreno = "";

    public String getNumTerreno() {
        return numTerreno;
    }

    public void setNumTerreno(String numTerreno) {
        this.numTerreno = numTerreno;
    }

    public String getPrecoTerreno() {
        return precoTerreno;
    }

    public void setPrecoTerreno(String precoTerreno) {
        this.precoTerreno = precoTerreno;
    }

    public TerrenoModel(int mtamanhoTerreno, int mpacoteDesejado, int mtipoTerreno){
        this.tamanhoTerreno = mtamanhoTerreno;
        this.pacoteDesejado = mpacoteDesejado;
        this.tipoTerreno =mtipoTerreno;
    }

    public int getTamanhoTerreno() {
        return tamanhoTerreno;
    }

    public void setTamanhoTerreno(int tamanhoTerreno) {
        this.tamanhoTerreno = tamanhoTerreno;
    }

    public int getPacoteDesejado() {
        return pacoteDesejado;
    }

    public void setPacoteDesejado(int pacoteDesejado) {
        this.pacoteDesejado = pacoteDesejado;
    }

    public int getTipoTerreno() {
        return tipoTerreno;
    }

    public void setTipoTerreno(int tipoTerreno) {
        this.tipoTerreno = tipoTerreno;
    }
}
