package insacvl.sti.ssu.homedz;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import insacvl.sti.ssu.homedz.pahowrapper.ActivityConstants;
import insacvl.sti.ssu.homedz.pahowrapper.Connection;
import insacvl.sti.ssu.homedz.pahowrapper.Connections;


public class LogFragment extends ListFragment {

    /** Client handle to a {@link Connection} object **/
    String clientHandle = null;
    /** {@link ArrayAdapter} to display the formatted text **/
    ArrayAdapter<Spanned> arrayAdapter = null;

    /**
     * @see ListFragment#onCreate(Bundle)
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Pull history information out of bundle

        clientHandle = ActivityConstants.currentHandler;
        Connection connection = Connections.getInstance(getActivity()).getConnection(clientHandle);

        Spanned[] history = connection.history();

        //Initialise the arrayAdapter, view and add data
        arrayAdapter = new ArrayAdapter<Spanned>(getActivity(), R.layout.list_view_text_view);

        arrayAdapter.addAll(history);
        setListAdapter(arrayAdapter);

    }

    /**
     * Updates the data displayed to match the current history
     */
    public void refresh() {
        //Initialise the arrayAdapter, view and add data

        if (arrayAdapter != null) {
            Log.d("LogFragment","REFRESH OK");
            arrayAdapter.clear();
            arrayAdapter.addAll(Connections.getInstance(getActivity()).getConnection(clientHandle).history());
            arrayAdapter.notifyDataSetChanged();
        }

    }
}
