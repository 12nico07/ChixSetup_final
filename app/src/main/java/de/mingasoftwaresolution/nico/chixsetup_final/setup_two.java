package de.mingasoftwaresolution.nico.chixsetup_final;


import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;



//Hier kann das Passwort festgelegt werden.

public class setup_two extends Fragment {


    private interface_Passwort myInterface;
   private  EditText myText_A;
  private   EditText myText_B;


    //Textfelder bekommen ein OnTextChange Listenener
    //Bei Eingaben wird geprüft ob die Inhalte übereinstimmen.
    //Das Ergebniss der Prüfung sieht der User als Text
    //Sendet den Inhalt der Text Elemente an die Aktivity. Dort werden sie gespeichert.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        final View myview = inflater.inflate(R.layout.setup_two, container, false);

        myText_A = (EditText) myview.findViewById(R.id.txt_pw1);
        myText_B = (EditText) myview.findViewById(R.id.txt_pw2);


        TextWatcher myWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                String pw_1 = myText_A.getText().toString();
                String pw_2 = myText_B.getText().toString();
                myInterface.communicate_Passwort(pw_1, pw_2);

                TextView mytext = (TextView) myview.findViewById(R.id.lbl_pwVergleich);
                if (myText_A.getText().toString().length() > 0 || myText_B.getText().toString().length() > 0) {


                    if (myText_A.getText().toString().equals(myText_B.getText().toString())) {
                        mytext.setTextColor(Color.BLACK);
                        mytext.setText(R.string.pwInfo_gleich);
                        myText_A.setTextColor(Color.BLACK);
                        myText_B.setTextColor(Color.BLACK);
                    } else {
                        mytext.setTextColor(Color.RED);
                        mytext.setText(R.string.pwInfo_ungleich);
                        myText_A.setTextColor(Color.RED);
                        myText_B.setTextColor(Color.RED);

                    }
                } else {
                    mytext.setTextColor(Color.BLACK);
                    mytext.setText(R.string.pwInfo_default);

                }

            }
        };

        myText_A.addTextChangedListener(myWatcher);
        myText_B.addTextChangedListener(myWatcher);

        return myview;
    }

    //Interface zur Kommunikation mit der Aktivity
    interface interface_Passwort {
        public void communicate_Passwort(String pw_1, String pw_2);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            myInterface = (interface_Passwort) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("You need to implement communicate_Passwort method");
        }
    }
}
