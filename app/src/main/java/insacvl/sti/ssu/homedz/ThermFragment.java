package insacvl.sti.ssu.homedz;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ThermFragment extends Fragment {

    public int nombre = 0;
    public boolean off = true;
    ImageView iv1, iv2, iv3;
    TextView tv1, tv2, tv3, tvtest;
    LinearLayout linLay1, linLay2, linLay3;

    public ThermFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.z_fragment_therm_layout, container, false);

        linLay1 = (LinearLayout)rootView.findViewById(R.id.ln1);
        linLay2 = (LinearLayout)rootView.findViewById(R.id.ln2);
        linLay3 = (LinearLayout)rootView.findViewById(R.id.ln3);

        linLay1.setVisibility(View.INVISIBLE);
        linLay2.setVisibility(View.INVISIBLE);
        linLay3.setVisibility(View.INVISIBLE);

        Button bAdd = (Button)rootView.findViewById(R.id.bAdd);
        bAdd.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                switch(nombre){
                    case 0:
                        linLay1.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        linLay2.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        linLay3.setVisibility(View.VISIBLE);
                        break;
                }
                nombre ++;
            }
        });

        return rootView;
    }
}
