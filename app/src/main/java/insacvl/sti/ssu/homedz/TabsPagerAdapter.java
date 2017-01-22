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



/*
    @Override
    public CharSequence getPageTitle(int position) {
        Drawable myDrawable = null;
        String title = "";
        switch (position) {
            case 0:
                //   myDrawable = context.getResources().getDrawable(R.drawable.img_section1);
                //   title = context.getResources().getString(R.string.title_section1);
                title = "test";
                break;
            case 1:
                myDrawable = context.getResources().getDrawable(R.drawable.lightbulb_icon64);
                //    title = context.getResources().getString(R.string.title_section2);
                title = "test";
                break;
            case 2:
                //   myDrawable = context.getResources().getDrawable(R.drawable.img_section3);
                //    title = context.getResources().getString(R.string.title_section3);
                title = "test";
                break;
            case 3:
                //   myDrawable = context.getResources().getDrawable(R.drawable.img_section4);
                //    title = context.getResources().getString(R.string.title_section4);
                title = "test";
                break;
            case 4:
                //  myDrawable = context.getResources().getDrawable(R.drawable.img_section5);
                //  title = context.getResources().getString(R.string.title_section5);
                title = "test";
                break;
            default:
                break;
        }
        SpannableStringBuilder sb = new SpannableStringBuilder("   " + title); // space added before text for convenience
        try {
            myDrawable.setBounds(5, 5, myDrawable.getIntrinsicWidth(), myDrawable.getIntrinsicHeight());
            ImageSpan span = new ImageSpan(myDrawable, DynamicDrawableSpan.ALIGN_BASELINE);
            sb.setSpan(span, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        } catch (Exception e) {

        }
        return sb;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        SpannableStringBuilder sb = new SpannableStringBuilder(" ");

        if (position == 0) {
            Drawable drawable = context.getDrawable(R.drawable.lightbulb_icon64);
            drawable.setBounds(0, 0, 48, 48);
            ImageSpan imageSpan = new ImageSpan(drawable);
            //to make our tabs icon only, set the Text as blank string with white space
            sb.setSpan(imageSpan, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        if (position == 1) {
            Drawable drawable = context.getDrawable(R.drawable.lightbulb_icon64);
            drawable.setBounds(0, 0, 48, 48);
            ImageSpan imageSpan = new ImageSpan(drawable);
            //to make our tabs icon only, set the Text as blank string with white space
            sb.setSpan(imageSpan, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        else
            sb = new SpannableStringBuilder("test");

        return sb;
    }
*/
}
