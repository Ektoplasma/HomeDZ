package insacvl.sti.ssu.homedz;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class LogFragment extends Fragment {

    public LogFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.z_fragment_log_layout, container, false);
        TextView txt = (TextView) rootView.findViewById(R.id.tvLog);
        return rootView;
    }
}
