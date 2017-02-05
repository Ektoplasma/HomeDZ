package insacvl.sti.ssu.homedz;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Iterator;

public class ThermFragment extends Fragment {

    private ListView lv1 = null;
    private ArrayList<ItemDetails> tableau;

    public ThermFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_list_obat, container, false);

        tableau = new ArrayList<ItemDetails>();

        lv1 = (ListView)rootView.findViewById(R.id.listView);
        lv1.setAdapter(new ItemListBaseAdapterTherm(getContext(), tableau));

        return rootView;
    }
    // ne sert plus à rien, c'est dans JsonDZ
 /*   private ArrayList<ItemDetails> GetSearchResults() {
        ArrayList<ItemDetails> results = new ArrayList<ItemDetails>();

        ItemDetails item_details = new ItemDetails();
        item_details.setName("Thermo1");
        item_details.setId(1);
        item_details.setDesc("Je suis un thermometre");
        item_details.setVal(0);
        item_details.setImageNumber(R.drawable.therm_64);
        results.add(item_details);

        item_details = new ItemDetails();
        item_details.setName("Thermo2");
        item_details.setId(2);
        item_details.setDesc("Je suis un thermometre");
        item_details.setVal(0);
        item_details.setImageNumber(R.drawable.therm_64);
        results.add(item_details);

        return results;
    }
*/
    public void refresht() {
        //Initialise the arrayAdapter, view and add data

        if (lv1 != null && tableau != null) {
            Log.d("ThermFragment","REFRESH OK");

            lv1.setAdapter(new ItemListBaseAdapterLight(getContext(), tableau));
            lv1.deferNotifyDataSetChanged();
        }

    }

    public void addItemDetails(ItemDetails bleh) {

        tableau.add(bleh);
        refresht();
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
