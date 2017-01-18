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



public class LightFragment extends Fragment {

    public int nombre = 0;
    public boolean off = true;
    ImageView iv1, iv2, iv3;
    TextView tv1, tv2, tv3, tvtest;
    LinearLayout linLay1, linLay2, linLay3;

    public LightFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.z_fragment_light_layout, container, false);

        nombre = 0;
        tv1 = (TextView)rootView.findViewById(R.id.tvLight1);
        tv2 = (TextView)rootView.findViewById(R.id.tvLight2);
        tv3 = (TextView)rootView.findViewById(R.id.tvLight3);
        tvtest = (TextView)rootView.findViewById(R.id.tvtest);

        iv1 = (ImageView)rootView.findViewById(R.id.ivLight1);
        iv2 = (ImageView)rootView.findViewById(R.id.ivLight2);
        iv3 = (ImageView)rootView.findViewById(R.id.ivLight3);

        linLay1 = (LinearLayout)rootView.findViewById(R.id.ln1);
        linLay2 = (LinearLayout)rootView.findViewById(R.id.ln2);
        linLay3 = (LinearLayout)rootView.findViewById(R.id.ln3);

        linLay1.setVisibility(View.INVISIBLE);
        linLay2.setVisibility(View.INVISIBLE);
        linLay3.setVisibility(View.INVISIBLE);

        iv1.setClickable(true);
        iv1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(iv1.getVisibility() == View.VISIBLE){
                    tvtest.setText("vous avez cliqué!");
                }
            }
        });

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

        Button bRefresh = (Button)rootView.findViewById(R.id.bRefresh);
        bRefresh.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(off) {
                    iv1.setImageResource(R.drawable.lightbulb_icon_on64);
                    iv2.setImageResource(R.drawable.lightbulb_icon_on64);
                    iv3.setImageResource(R.drawable.lightbulb_icon_on64);
                    off = false;
                }
                else{
                    iv1.setImageResource(R.drawable.lightbulb_icon_off64);
                    iv2.setImageResource(R.drawable.lightbulb_icon_off64);
                    iv3.setImageResource(R.drawable.lightbulb_icon_off64);
                    off = true;
                }
            }
        });
        return rootView;
    }
}