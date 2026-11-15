package com.example.laborator4_dam;

import java.io.Serializable;

public class DetailingVehicle implements Serializable {
    private String modelMasina;
    private double pret;
    private String tipPachet;
    private String gradMurdarie;
    private boolean tratamentPiele;
    private boolean ozonificare;
    private float calitateDorita;

    public DetailingVehicle(String modelMasina, double pret, String tipPachet, String gradMurdarie, boolean tratamentPiele, boolean ozonificare, float calitateDorita) {
        this.modelMasina = modelMasina;
        this.pret = pret;
        this.tipPachet = tipPachet;
        this.gradMurdarie = gradMurdarie;
        this.tratamentPiele = tratamentPiele;
        this.ozonificare = ozonificare;
        this.calitateDorita = calitateDorita;
    }

    public String getModelMasina() {
        return modelMasina;
    }

    public double getPret() {
        return pret;
    }

    public String getTipPachet() {
        return tipPachet;
    }

    public String getGradMurdarie() {
        return gradMurdarie;
    }

    public boolean isTratamentPiele() {
        return tratamentPiele;
    }

    public boolean isOzonificare() {
        return ozonificare;
    }

    public float getCalitateDorita() {
        return calitateDorita;
    }

    @Override
    public String toString() {
        return "Masina: " +modelMasina+
                "\nPreț: " + pret + " RON" +
                "\nPachet: " + tipPachet +
                "\nMurdărie: " + gradMurdarie +
                "\nTratament piele: " + (tratamentPiele ? "Da" : "Nu") +
                "\nOzonificare: " + (ozonificare ? "Da" : "Nu") +
                "\nRating: " + calitateDorita + " stele.";
    }
}