package de.mingasoftwaresolution.nico.chixsetup_final;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Nico on 18.02.2015.
 */

//Eigener Adapter um die ListView in Fragment 3 zu befüllen

public class MyAdapter extends ArrayAdapter<Kontakt> {

   private Context context;
   private ArrayList<Kontakt> allContacts;

    //Konstruktor
    public MyAdapter(Context c, ArrayList<Kontakt> lst_contacts) {
        super(c, R.layout.contact_item, lst_contacts);
        this.context = c;
        this.allContacts = lst_contacts;
    }

    //erstellt die einzelnen Items und befüllt sie.
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.contact_item, parent, false);
        ImageView item_pic = (ImageView) row.findViewById(R.id.item_imgV_bild);
        TextView item_name = (TextView) row.findViewById(R.id.item_txtV_name);
        TextView item_nummer = (TextView) row.findViewById(R.id.item_txtV_nummer);


        String str = "Tel.:" + allContacts.get(position).getNummer(0);


        if (allContacts.get(position).getAnzahlNummern() > 1) {
            str = str + " (...)";
        }



        item_pic.setImageBitmap(allContacts.get(position).getBild());
        item_name.setText(allContacts.get(position).getName());
        item_nummer.setText(str);


        return row;
    }
}
