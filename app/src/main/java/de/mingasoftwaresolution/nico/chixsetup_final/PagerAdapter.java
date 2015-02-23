package de.mingasoftwaresolution.nico.chixsetup_final;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Nico on 05.02.2015.
 */

//Eigener FragmentPageAdapter umd die 4 Fragments per swype zu wechseln.

public class PagerAdapter extends FragmentPagerAdapter {

    //Konstruktor
    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    //Gibt jeweils das nächste Fragment zurück.
    @Override
    public Fragment getItem(int arg0) {

        switch (arg0) {
            case 0:
                return new setup_one();
            case 1:
                return new setup_two();
            case 2:
                return new setup_three();
            case 3:
                return new setup_four();
            default:
                break;
        }


        return null;
    }


    //Gibt die Anzahl der verfügbaren Fragmente zurück
    @Override
    public int getCount() {
        return 4;
    }


}
