package de.mingasoftwaresolution.nico.chixsetup_final;


import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;


/**
 * Created by Nico on 05.02.2015.
 */


//Liest das Telefonbuch aus und speichert Kontakte mit mind. 1 Nummer in einer ArrayList.
// Diese Kontakte werden dann in der Listview angezeigt und auswählbar sein.
public class setup_three extends Fragment {

    private interface_selectedContact myInterface;
    private ArrayList<Kontakt> kontakt_list = new ArrayList();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.setup_three, container, false);


        if (kontakt_list.size() < 1) {
            kontakt_list = readContacts();
        }

        if (kontakt_list.size() > 1) {

            //Listview mit den KontaktDaten füllen und OnLick Methode angeben.
            ListView myLstView;
            myLstView = (ListView) v.findViewById(R.id.lstView_Kontakte);
            MyAdapter adapter = new MyAdapter(getActivity(), kontakt_list);
            myLstView.setAdapter(adapter);


            myLstView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    if (myInterface.addContact(kontakt_list.get(position))) {
                        //added
                        Toast.makeText(getActivity(), "'" + kontakt_list.get(position).getName() + "' wurde hinzugefügt.", Toast.LENGTH_SHORT).show();
                    } else {
                        //delated
                        Toast.makeText(getActivity(), "'" + kontakt_list.get(position).getName() + "' wurde gelöscht.", Toast.LENGTH_SHORT).show();
                    }


                }
            });

        }

        return v;
    }

    //Interface zur Kommunikation mit der Activity.
    interface interface_selectedContact {
        public boolean addContact(Kontakt mySelectedContact);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            myInterface = (interface_selectedContact) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("You need to implement sendData method");
        }
    }

    //Liest Name, Nummern und Bild von KOntakten aus. Sofern diese mind. 1 Nummer haben.
    //Speichert diese in ArrayList und gibt die Liste zurück
    private  ArrayList<Kontakt> readContacts() {
        ArrayList<Kontakt> kontakt_list = new ArrayList();

        ContentResolver myContResol = getActivity().getContentResolver();
        Cursor myCurser = myContResol.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, ContactsContract.Contacts.SORT_KEY_PRIMARY + " ASC");

        if (myCurser.getCount() > 0) {

            while (myCurser.moveToNext()) {
                String id = myCurser.getString(myCurser.getColumnIndex(ContactsContract.Contacts._ID));
                String name = myCurser.getString(myCurser.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));


                Kontakt aktuellerKontakt = new Kontakt();
                aktuellerKontakt.setName(name);

                if (Integer.parseInt(myCurser.getString(myCurser.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                    Cursor phoneCursor = myContResol.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{id}, null);

                    while (phoneCursor.moveToNext()) {
                        String nummer = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        aktuellerKontakt.addNummer(nummer);
                    }
                    phoneCursor.close();


                    Uri myUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, Long.parseLong(id));
                    InputStream is = ContactsContract.Contacts.openContactPhotoInputStream(myContResol, myUri);
                    Bitmap bmp;
                    if (is == null) {

                        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);

                    } else {
                        bmp = BitmapFactory.decodeStream(is);
                    }

                    aktuellerKontakt.setBild(bmp);


                    kontakt_list.add(aktuellerKontakt);
                }
            }
            myCurser.close();

            return kontakt_list;
        } else {
            return null;
        }

    }


}

