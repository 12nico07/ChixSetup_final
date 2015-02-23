package de.mingasoftwaresolution.nico.chixsetup_final;


import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Created by Nico on 16.02.2015.
 */

//Ein Objekt in dem einige Daten des Kontakts gespeichert werden können.

public class Kontakt {
    private String name = null;
    private ArrayList<String> nummern = new ArrayList<String>();
    private Bitmap bild;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setBild(Bitmap bild) {
        this.bild = bild;
    }

    public Bitmap getBild() {
        return this.bild;
    }

    public void addNummer(String nummer) {
        this.nummern.add(nummer);
    }

    public String getNummer(int index) {
        return this.nummern.get(index);
    }

    public int getAnzahlNummern() {
        return this.nummern.size();
    }

}
