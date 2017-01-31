package insacvl.sti.ssu.homedz;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.ArrayList;


public class LightFragment extends Fragment {

    public LightFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_list_obat, container, false);

        ArrayList<ItemDetails> image_details = GetSearchResults();

        final ListView lv1 = (ListView)rootView.findViewById(R.id.listView);
        lv1.setAdapter(new ItemListBaseAdapterLight(getContext(), image_details));

        return rootView;
    }

    private ArrayList<ItemDetails> GetSearchResults() {
        ArrayList<ItemDetails> results = new ArrayList<ItemDetails>();

        ItemDetails item_details = new ItemDetails();
        item_details.setName("Light1");
        item_details.setId("1");
        item_details.setDesc("Je suis une lumière");
        item_details.setVal(0);
        item_details.setImageNumber(R.drawable.lightbulb_icon_off64);
        results.add(item_details);

        item_details = new ItemDetails();
        item_details.setName("Light2");
        item_details.setId("2");
        item_details.setDesc("Je suis une lumière");
        item_details.setVal(0);
        item_details.setImageNumber(R.drawable.lightbulb_icon_off64);
        results.add(item_details);

        return results;
    }
}