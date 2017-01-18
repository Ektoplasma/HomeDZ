package insacvl.sti.ssu.homedz;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class MeteoFragment extends Fragment {

    public MeteoFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.z_fragment_meteo_layout, container, false);
        TextView txt = (TextView) rootView.findViewById(R.id.tvMeteo);
        return rootView;
    }
}