package insacvl.sti.ssu.homedz.pahowrapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import java.util.Map.Entry;
import java.util.logging.LogManager;

import insacvl.sti.ssu.homedz.R;
import org.eclipse.paho.client.mqttv3.MqttException;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;

import insacvl.sti.ssu.homedz.pahowrapper.ActionListener.Action;
import insacvl.sti.ssu.homedz.pahowrapper.Connection.ConnectionStatus;
import org.eclipse.paho.android.service.MqttAndroidClient;
import insacvl.sti.ssu.homedz.TabLayoutActivity;

/**
 * Deals with actions performed in the {@link ClientConnections} activity
 * and the {@link TabLayoutActivity} activity and associated fragments
 *
 */
public class Listener implements OnMenuItemClickListener {

    /** The handle to a {@link Connection} object which contains the {@link MqttAndroidClient} associated with this object **/
    private String clientHandle = null;

    /** {@link ClientConnections} reference used to perform some actions**/
    private ClientConnections clientConnections = null;
    /** {@link Context} used to load and format strings **/
    private Context context = null;

    /** Whether Paho is logging is enabled**/
    static boolean logging = false;

    /**
     * Constructs a listener object for use with {@link TabLayoutActivity} activity and
     * associated fragments.
     * @param tabLayoutActivity The instance of {@link TabLayoutActivity}
     * @param clientHandle The handle to the client that the actions are to be performed on
     */

    public Listener(TabLayoutActivity tabLayoutActivity, String clientHandle)
    {
        /* {@link TabLayoutActivity} reference used to perform some actions*/
        this.clientHandle = clientHandle;
        context = tabLayoutActivity;

    }

    /**
     * Constructs a listener object for use with {@link ClientConnections} activity.
     * @param clientConnections The instance of {@link ClientConnections}
     */
    Listener(ClientConnections clientConnections) {
        this.clientConnections = clientConnections;
        context = clientConnections;
    }

    /**
     * Perform the needed action required based on the button that
     * the user has clicked.
     *
     * @param item The menu item that was clicked
     * @return If there is anymore processing to be done
     *
     */
    @Override
    public boolean onMenuItemClick(MenuItem item) {

        int id = item.getItemId();

        switch (id)
        {
            case R.id.newConnection :
                createAndConnect();
                break;
            case R.id.disconnect :
                disconnect();
                break;
            case R.id.connectMenuOption :
                reconnect();
                break;
            case R.id.startLogging :
                enablePahoLogging();
                break;
            case R.id.endLogging :
                disablePahoLogging();
                break;
        }

        return false;
    }

    /**
     * Reconnect the selected client
     */
    private void reconnect() {

        Connections.getInstance(context).getConnection(clientHandle).changeConnectionStatus(ConnectionStatus.CONNECTING);

        Connection c = Connections.getInstance(context).getConnection(clientHandle);
        try {
            c.getClient().connect(c.getConnectionOptions(), null, new ActionListener(context, Action.CONNECT, clientHandle, null));
        } catch (MqttException e) {
            Log.e(this.getClass().getCanonicalName(), "Failed to reconnect the client with the handle " + clientHandle, e);
            c.addAction("Client failed to connect");
        }

    }

    /**
     * Disconnect the client
     */
    private void disconnect() {

        Connection c = Connections.getInstance(context).getConnection(clientHandle);

        //if the client is not connected, process the disconnect
        if (!c.isConnected()) {
            return;
        }

        try {
            c.getClient().disconnect(null, new ActionListener(context, Action.DISCONNECT, clientHandle, null));
            c.changeConnectionStatus(ConnectionStatus.DISCONNECTING);
        }
        catch (MqttException e) {
            Log.e(this.getClass().getCanonicalName(), "Failed to disconnect the client with the handle " + clientHandle, e);
            c.addAction("Client failed to disconnect");
        }

    }

    /**
     * Create a new client and connect
     */
    private void createAndConnect()
    {
        Intent createConnection;

        //start a new activity to gather information for a new connection
        createConnection = new Intent();
        createConnection.setClassName(
                clientConnections.getApplicationContext(),
                "insacvl.sti.ssu.homedz.pahowrapper.NewConnection");

        clientConnections.startActivityForResult(createConnection,
                ActivityConstants.connect);
    }

    /**
     * Enables logging in the Paho MQTT client
     */
    private void enablePahoLogging() {

        try {
            InputStream logPropStream = context.getResources().openRawResource(R.raw.jsr47android);
            LogManager.getLogManager().readConfiguration(logPropStream);
            logging = true;

            HashMap<String, Connection> connections = (HashMap<String,Connection>)Connections.getInstance(context).getConnections();
            if(!connections.isEmpty()){
                Entry<String, Connection> entry = connections.entrySet().iterator().next();
                Connection connection = entry.getValue();
                connection.getClient().setTraceEnabled(true);
                //change menu state.
                clientConnections.invalidateOptionsMenu();
                //Connections.getInstance(context).getConnection(clientHandle).getClient().setTraceEnabled(true);
            }else{
                Log.i("SampleListener","No connection to enable log in service");
            }
        }
        catch (IOException e) {
            Log.e("MqttAndroidClient",
                    "Error reading logging parameters", e);
        }

    }

    /**
     * Disables logging in the Paho MQTT client
     */
    private void disablePahoLogging() {
        LogManager.getLogManager().reset();
        logging = false;

        HashMap<String, Connection> connections = (HashMap<String,Connection>)Connections.getInstance(context).getConnections();
        if(!connections.isEmpty()){
            Entry<String, Connection> entry = connections.entrySet().iterator().next();
            Connection connection = entry.getValue();
            connection.getClient().setTraceEnabled(false);
            //change menu state.
            clientConnections.invalidateOptionsMenu();
        }else{
            Log.i("SampleListener","No connection to disable log in service");
        }
        clientConnections.invalidateOptionsMenu();
    }

}
