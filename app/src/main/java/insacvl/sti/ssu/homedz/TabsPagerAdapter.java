package insacvl.sti.ssu.homedz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.Log;

import java.util.ArrayList;

import insacvl.sti.ssu.homedz.pahowrapper.ActivityConstants;

public class TabsPagerAdapter extends FragmentPagerAdapter {

    Context context;

    // Stores the instances of the pages
    private ArrayList<Fragment> fragments = null;

    public TabsPagerAdapter(FragmentManager fm, Context c) {
        super(fm);
        context = c;
        fragments = new ArrayList<Fragment>();
        fragments.add(new LogFragment());
        fragments.add(new LightFragment());
        fragments.add(new ThermFragment());
        fragments.add(new MeteoFragment());
        fragments.add(new SceneFragment());

    }

    @Override
    public Fragment getItem(int position) {

        return fragments.get(position);

    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        CharSequence seq;
        switch (position){
            case 0:
                seq = "LOGS";
                break;
            case 1:
                seq = "Lights";
                break;
            case 2:
                seq = "Temperatures";
                break;
            case 3:
                seq = "Meteo";
                break;
            case 4:
                seq = "Scenes";
                break;
            default:
                seq = "NULL";
        }

        return seq;
    }
}
