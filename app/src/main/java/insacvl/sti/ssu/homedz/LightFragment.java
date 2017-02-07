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

        if(itemListBaseAdapterLight != null) {
            // if activity hasn't started yet, add delay
            if (getContext() == null) {
                Runnable task = new Runnable() {
                    public void run() {
                        Connection connection = Connections.getInstance(getActivity()).getConnection(clientHandle);

                        ArrayList<ItemDetails> image_details = connection.getTableauLight();

                        itemListBaseAdapterLight = new ItemListBaseAdapterLight(getContext(), image_details);
                        itemListBaseAdapterLight.notifyDataSetChanged();
                    }
                };

                Handler handler = new Handler();
                handler.postDelayed(task, 1000);

            }// else refresh the UI
            else {
                Connection connection = Connections.getInstance(getActivity()).getConnection(clientHandle);

                ArrayList<ItemDetails> image_details = connection.getTableauLight();

                itemListBaseAdapterLight.setResults(image_details);
                itemListBaseAdapterLight.notifyDataSetChanged();
            }
        }

    }


}