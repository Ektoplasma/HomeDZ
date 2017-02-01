package insacvl.sti.ssu.homedz;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Iterator;


public class LightFragment extends Fragment {

    private ListView lv1 = null;
    private ArrayList<ItemDetails> tableau;

    public LightFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_list_obat, container, false);

        ArrayList<ItemDetails> image_details = GetSearchResults();

        lv1 = (ListView)rootView.findViewById(R.id.listView);
        lv1.setAdapter(new ItemListBaseAdapterLight(getContext(), image_details));

        return rootView;
    }
    // ne sert plus à rien, c'est dans JsonDZ
    private ArrayList<ItemDetails> GetSearchResults() {
        ArrayList<ItemDetails> results = new ArrayList<ItemDetails>();

        ItemDetails item_details = new ItemDetails();
        item_details.setName("Light1");
        item_details.setId(1);
        item_details.setDesc("Je suis une lumière");
        item_details.setVal(0);
        item_details.setImageNumber(R.drawable.lightbulb_icon_off64);
        results.add(item_details);

        item_details = new ItemDetails();
        item_details.setName("Light2");
        item_details.setId(2);
        item_details.setDesc("Je suis une lumière");
        item_details.setVal(0);
        item_details.setImageNumber(R.drawable.lightbulb_icon_off64);
        results.add(item_details);

        return results;
    }

    /**
     * Updates the data
     */
    public void refreshl() {
        //Initialise the arrayAdapter, view and add data

        if (lv1 != null) {
            Log.d("LightFragmentFragment","REFRESH OK");

            lv1.setAdapter(new ItemListBaseAdapterLight(getContext(), tableau));
            lv1.deferNotifyDataSetChanged();
        }

    }

    public void addItemDetails(ItemDetails bleh) {

        tableau.add(bleh);
        refreshl();
    }

    public boolean isNew(int id){
        boolean hasNew = false;
        Iterator<ItemDetails> it = tableau.iterator();
        while(it.hasNext() && hasNew == false){
            if(it.next().getId() != id)
                hasNew = true;
        }
        return hasNew;
    }
}