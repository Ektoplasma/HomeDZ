package insacvl.sti.ssu.homedz;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class MeteoFragment extends Fragment {

    private ListView lv1 = null;
    private ArrayList<ItemDetails> tableau;

    public MeteoFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_list_obat, container, false);

        tableau = new ArrayList<ItemDetails>();

        lv1 = (ListView) rootView.findViewById(R.id.listView);
        lv1.setAdapter(new ItemListBaseAdapterMeteo(getContext(), GetSearchResults()));

        return rootView;
    }

    private ArrayList<ItemDetails> GetSearchResults() {
        ArrayList<ItemDetails> results = new ArrayList<ItemDetails>();

        ItemDetails item_details = new ItemDetails();
        item_details.setDesc("Bourges");
        item_details.setVal(8);
        item_details.setImageNumber(R.drawable.rain);
        results.add(item_details);

        return results;
    }
}