package insacvl.sti.ssu.homedz;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import java.util.ArrayList;


class TabsPagerAdapter extends FragmentPagerAdapter {

    private Context context;

    // Stores the instances of the pages
    private ArrayList<Fragment> fragments = null;

    TabsPagerAdapter(FragmentManager fm, Context c) {
        super(fm);
        context = c;
        fragments = new ArrayList<>();
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
