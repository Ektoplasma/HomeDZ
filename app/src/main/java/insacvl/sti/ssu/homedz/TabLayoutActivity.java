package insacvl.sti.ssu.homedz;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import insacvl.sti.ssu.homedz.pahowrapper.ActionListener;
import insacvl.sti.ssu.homedz.pahowrapper.ActivityConstants;
import insacvl.sti.ssu.homedz.pahowrapper.Connection;
import insacvl.sti.ssu.homedz.pahowrapper.Connections;
import insacvl.sti.ssu.homedz.pahowrapper.Listener;


public class TabLayoutActivity extends AppCompatActivity {

    /** This instance of <code>TabLayoutActivity</code> **/
    private final TabLayoutActivity tabLayoutActivity = this;

    /**
     * {@link TabsPagerAdapter} that is used to get pages to display
     */
    public static TabsPagerAdapter tabsPagerAdapter;
    /**
     * The handle to the {@link Connection} which holds the data for the client
     * selected
     **/
    private String clientHandle = null;

    /**
     * The instance of {@link Connection} that the <code>clientHandle</code>
     * represents
     **/
    private Connection connection = null;

    /** The currently selected tab **/
    private int selected = 0;

    /**
     * {@link ViewPager} object allows pages to be flipped left and right
     */
    ViewPager pager;

    /**
     * The {@link TabLayoutActivity.ChangeListener} this object is using for the connection
     * updates
     **/
    private TabLayoutActivity.ChangeListener changeListener = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        clientHandle = getIntent().getStringExtra("handle");

        ActivityConstants.currentHandler = clientHandle;

        setContentView(R.layout.z_activity_tab_layout);

        final TabLayout tabs = (TabLayout)findViewById(R.id.tabs);
        pager = (ViewPager) findViewById(R.id.pager);
        tabsPagerAdapter = new TabsPagerAdapter(getSupportFragmentManager(), getApplicationContext());

        pager.setAdapter(tabsPagerAdapter);
        tabs.setupWithViewPager(pager);

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // When the given tab is selected, switch to the corresponding page in
                // the ViewPager.
                pager.setCurrentItem(tab.getPosition());
                selected = tab.getPosition();
                // invalidate the options menu so it can be updated
                invalidateOptionsMenu();
                // history fragment is at position zero so get this then refresh its
                // view
                ((LogFragment) tabsPagerAdapter.getItem(0)).refresh();
                ((LightFragment) tabsPagerAdapter.getItem(1)).refreshl();
                //((ThermFragment) tabsPagerAdapter.getItem(2)).refresht();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));
        pager.post(new Runnable() {
            @Override
            public void run() {
                new TabLayout.TabLayoutOnPageChangeListener(tabs).onPageSelected(pager.getCurrentItem());
            }
        });

        connection = Connections.getInstance(this).getConnection(clientHandle);
        changeListener = new TabLayoutActivity.ChangeListener();
        connection.registerChangeListener(changeListener);

        get_idx_info(2);

    }

    /**
     * Publish the message the user has specified
     */
    private void get_idx_info(int idx){
        String topic = "domoticz/in";
        int qos = ActivityConstants.defaultQos;

        String message = new String("{\"command\": \"getdeviceinfo\", \"idx\": "+idx+" }");

        String[] args = new String[2];
        args[0] = message;
        args[1] = topic+";qos:"+qos+";retained:"+"false";

        try {
            Connections.getInstance(getApplicationContext()).getConnection(clientHandle).getClient().publish(topic, message.getBytes(), qos, false, null, new ActionListener(getApplicationContext(), ActionListener.Action.PUBLISH, clientHandle, args));
        }
        catch (MqttSecurityException e) {
            Log.e(this.getClass().getCanonicalName(), "Failed to publish a messged from the client with the handle " + clientHandle, e);
        }
        catch (MqttException e) {
            Log.e(this.getClass().getCanonicalName(), "Failed to publish a messged from the client with the handle " + clientHandle, e);
        }
    }

    /**
     * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        int menuID;
        Integer button = null;
        boolean connected = Connections.getInstance(this)
                .getConnection(clientHandle).isConnected();

        // Select the correct action bar menu to display based on the
        // connectionStatus and which tab is selected
        if (connected) {
            menuID = R.menu.activity_connection_details;
        }
        else {
            menuID = R.menu.activity_connection_details_disconnected;
        }
        // inflate the menu selected
        getMenuInflater().inflate(menuID, menu);
        Listener listener = new Listener(this, clientHandle);
        // add listeners
        if (button != null) {
            // add listeners
            menu.findItem(button).setOnMenuItemClickListener(listener);
            if (!Connections.getInstance(this).getConnection(clientHandle)
                    .isConnected()) {
                menu.findItem(button).setEnabled(false);
            }
        }
        // add the listener to the disconnect or connect menu option
        if (connected) {
            menu.findItem(R.id.disconnect).setOnMenuItemClickListener(listener);
        }
        else {
            menu.findItem(R.id.connectMenuOption).setOnMenuItemClickListener(
                    listener);
        }

        return true;
    }

    /**
     * <code>ChangeListener</code> updates the UI when the {@link Connection}
     * object it is associated with updates
     *
     */
    private class ChangeListener implements PropertyChangeListener {

        /**
         * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
         */
        @Override
        public void propertyChange(PropertyChangeEvent event) {
            // connection object has change refresh the UI


            tabLayoutActivity.runOnUiThread(new Runnable() {

                @Override
                public void run() {

                    tabLayoutActivity.invalidateOptionsMenu();
                    ((LogFragment) tabsPagerAdapter.getItem(0)).refresh();
                    ((LightFragment) tabsPagerAdapter.getItem(1)).refreshl();
                    ((ThermFragment) tabsPagerAdapter.getItem(2)).refresht();

                }
            });

        }

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
        }
    }

    @Override
    protected void onDestroy() {
        connection.removeChangeListener(null);
        super.onDestroy();
    }
}