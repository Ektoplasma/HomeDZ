package insacvl.sti.ssu.homedz;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Iterator;

import insacvl.sti.ssu.homedz.pahowrapper.ActivityConstants;
import insacvl.sti.ssu.homedz.pahowrapper.Connection;
import insacvl.sti.ssu.homedz.pahowrapper.Connections;


public class LightFragment extends ListFragment {

    private ItemListBaseAdapterLight itemListBaseAdapterLight = null;

    /** Client handle to a {@link Connection} object **/
    String clientHandle = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        clientHandle = ActivityConstants.currentHandler;
        Connection connection = Connections.getInstance(getActivity()).getConnection(clientHandle);

        ArrayList<ItemDetails> image_details = connection.getTableauLight();

        itemListBaseAdapterLight = new ItemListBaseAdapterLight(getContext(), image_details);

        setListAdapter(itemListBaseAdapterLight);

    }

    /**
     * Updates the data
     */
    public void refreshl() {
        //Initialise the arrayAdapter, view and add data

            // si l'activité n'a pas encore démarré, ajouter un délai
        if(itemListBaseAdapterLight != null) {
            if (getContext() == null) {
                Runnable task = new Runnable() {
                    public void run() {
                        Log.d("LightFragment", "REFRESH OK");
                        Connection connection = Connections.getInstance(getActivity()).getConnection(clientHandle);

                        ArrayList<ItemDetails> image_details = connection.getTableauLight();
                        printTab(image_details);

                        itemListBaseAdapterLight = new ItemListBaseAdapterLight(getContext(), image_details);
                        itemListBaseAdapterLight.notifyDataSetChanged();
                    }
                };

                Handler handler = new Handler();
                handler.postDelayed(task, 1000);

            } else {
                Log.d("LightFragment", "REFRESH OK");

                Connection connection = Connections.getInstance(getActivity()).getConnection(clientHandle);

                ArrayList<ItemDetails> image_details = connection.getTableauLight();
                printTab(image_details);
                itemListBaseAdapterLight.setResults(image_details);
                itemListBaseAdapterLight.notifyDataSetChanged();
                //setListAdapter(itemListBaseAdapterLight);
            }
        }

    }
/*
    public void addItemDetails(ItemDetails bleh) {
        tableau.add(bleh);
        refreshl();
    }

    public boolean isNew(int id){

        boolean hasNew = false;
        Iterator<ItemDetails> it = tableau.iterator();
        if(!it.hasNext()) hasNew = true;
        else{
            while(it.hasNext() && hasNew == false){
                if(it.next().getId() != id)
                    hasNew = true;
            }
        }

        return hasNew;
    }*/

    public void printTab(ArrayList<ItemDetails> tableau)
    {
        Log.d("LightFragment", "LOLOOO");
        Iterator<ItemDetails> it = tableau.iterator();
        if(!it.hasNext()) Log.d("LightFragment","Empty");
        else{
            while(it.hasNext()){
                Log.d("LightFragment",it.next().getName());
            }
        }
    }
}