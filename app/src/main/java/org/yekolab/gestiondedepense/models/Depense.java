package org.yekolab.gestiondedepense.models;

/**
 * Created by setico on 3/13/17.
 */

public class Depense {
    private Long id;
    private String label;
    private int montant;
    private Long date;
    private Categorie categorie;

    public Depense(Long id, String label, int montant, Long date, Categorie categorie) {
        this.id = id;
        this.label = label;
        this.montant = montant;
        this.date = date;
        this.categorie = categorie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }
}
