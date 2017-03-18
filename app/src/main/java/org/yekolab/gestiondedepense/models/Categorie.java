package org.yekolab.gestiondedepense.models;

import org.yekolab.gestiondedepense.contracts.DepenseContract;

/**
 * Created by setico on 3/13/17.
 */

public class Categorie {
    private Long id;
    private String label;

    public Categorie(Long id, String label) {
        this.id = id;
        this.label = label;
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

    @Override
    public String toString() {
        return label;
    }
}
