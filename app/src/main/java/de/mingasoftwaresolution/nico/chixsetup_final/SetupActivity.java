package de.mingasoftwaresolution.nico.chixsetup_final;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import static de.mingasoftwaresolution.nico.chixsetup_final.R.layout.activity_setup;


public class SetupActivity extends FragmentActivity implements setup_two.interface_Passwort, setup_three.interface_selectedContact {

    ViewPager vpager;

    // Enthalten den Inhalt der EditText Elemente von Fragment 2
   private String myPasswort_a = "";
    private String myPasswort_b = "";

    // Enthaelt die zu übergebenen Daten
   private ArrayList<Kontakt> choosenContacts = new ArrayList<Kontakt>();
     String passwortHash = "";









    // Wenn der Button von Fragment 4 gedrückt wird und das Setup abgeschlossen wird
    public void btn_fertig_onClick(View v) {

        //schreibt in "passwortHash" den Has des Passwort und gibt einen InfoString zurück.
        String toast_pw = convertPW();

        Toast.makeText(this, toast_pw + " Es werden " + choosenContacts.size() + " Kontakte importiert.", Toast.LENGTH_LONG).show();

    }


    //schreibt in "passwortHash" den Has des Passwort und gibt einen InfoString zurück.
    private String convertPW(){

        if (myPasswort_a.equals(myPasswort_b) && myPasswort_a.length() > 0) {
            try {
                passwortHash = AeSimpleSHA1.SHA1(myPasswort_a);
                return "Der Passwortschutz wird eingerichtet.";
            } catch (Exception e) {
                passwortHash = "";
                return "Der Passwortschutz kann leider nicht eingerichtet werden.";
            }

        } else {
            return "Der Passwortschutz wird nicht eingerichtet.";
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_setup);
        vpager = (ViewPager) findViewById(R.id.vpager);
        PagerAdapter padapter = new PagerAdapter(getSupportFragmentManager());
        vpager.setAdapter(padapter);
    }



    //Speichert die Werte der EditTextfelder aus Fragment 2
    @Override
    public void communicate_Passwort(String pw_1, String pw_2) {
        myPasswort_a = pw_1;
        myPasswort_b = pw_2;
    }


    //Vergleicht einen ausgewählten Kontakt mit der Liste
    //Existiert der Kontakt schon, wird er gelöscht und es wird false zurück gegeben
    //Ist der Kontakt noch nicht vorhanden, wird er hinzugefügt und es wird true zurück gegeben
    @Override
    public boolean addContact(Kontakt mySelectedContact) {

        if (choosenContacts.contains(mySelectedContact)) {
            choosenContacts.remove(mySelectedContact);
            return false;
        } else {
            choosenContacts.add(mySelectedContact);
            return true;
        }
    }

}
