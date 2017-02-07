package insacvl.sti.ssu.homedz;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import android.support.v4.app.ListFragment;
import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;

import insacvl.sti.ssu.homedz.pahowrapper.ActivityConstants;
import insacvl.sti.ssu.homedz.pahowrapper.Connection;
import insacvl.sti.ssu.homedz.pahowrapper.Connections;

public class ThermFragment extends ListFragment {

    /** Client handle to a {@link Connection} object **/
    String clientHandle = null;

    private ItemListBaseAdapterTherm itemListBaseAdapterTherm = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        clientHandle = ActivityConstants.currentHandler;
        Connection connection = Connections.getInstance(getActivity()).getConnection(clientHandle);

        ArrayList<ItemDetails> image_details = connection.getTableauTemp();

        itemListBaseAdapterTherm = new ItemListBaseAdapterTherm(getContext(), image_details);

        setListAdapter(itemListBaseAdapterTherm);

    }


    /**
     * Updates the data
     */
    public void refresht() {
        //Initialise the arrayAdapter, view and add data

        // si l'activité n'a pas encore démarré, ajouter un délai
        if(itemListBaseAdapterTherm != null) {
            if (getContext() == null) {
                Runnable task = new Runnable() {
                    public void run() {
                        Log.d("TempFragment", "REFRESH OK");

                        Connection connection = Connections.getInstance(getActivity()).getConnection(clientHandle);

                        ArrayList<ItemDetails> image_details = connection.getTableauTemp();
                        printTab(image_details);
                        itemListBaseAdapterTherm.setResults(image_details);
                        itemListBaseAdapterTherm.notifyDataSetChanged();
                        //setListAdapter(itemListBaseAdapterLight);
                    }
                };

                Handler handler = new Handler();
                handler.postDelayed(task, 1000);

            } else {
                Log.d("TempFragment", "REFRESH OK");

                Connection connection = Connections.getInstance(getActivity()).getConnection(clientHandle);

                ArrayList<ItemDetails> image_details = connection.getTableauTemp();
                printTab(image_details);
                itemListBaseAdapterTherm.setResults(image_details);
                itemListBaseAdapterTherm.notifyDataSetChanged();
                //setListAdapter(itemListBaseAdapterLight);
            }
        }

    }

    public void printTab(ArrayList<ItemDetails> tableau)
    {
        Log.d("ThermFragment", "LOLOOO");
        Iterator<ItemDetails> it = tableau.iterator();
        if(!it.hasNext()) Log.d("ThermFragment","Empty");
        else{
            while(it.hasNext()){
                Log.d("ThermFragment",it.next().getName());
            }
        }
    }
}
