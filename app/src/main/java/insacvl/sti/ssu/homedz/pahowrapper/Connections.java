package insacvl.sti.ssu.homedz.pahowrapper;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.util.Log;

import org.eclipse.paho.android.service.MqttAndroidClient;

/**
 * <code>Connections</code> is a singleton class which stores all the connection objects
 * in one central place so they can be passed between activities using a client
 * handle
 *
 */
public class Connections {

    /** Singleton instance of <code>Connections</code>**/
    private static Connections instance = null;

    /** List of {@link Connection} objects**/
    private HashMap<String, Connection> connections = null;


    /**
     * Create a Connections object
     * @param context Applications context
     */
    private Connections(Context context)
    {
        connections = new HashMap<>();

    }

    /**
     * Returns an already initialised instance of <code>Connections</code>, if Connections has yet to be created, it will
     * create and return that instance
     * @param context The applications context used to create the <code>Connections</code> object if it is not already initialised
     * @return Connections instance
     */
    public synchronized static Connections getInstance(Context context)
    {
        if (instance == null) {
            instance = new Connections(context);
        }

        return instance;
    }

    /**
     * Finds and returns a connection object that the given client handle points to
     * @param handle The handle to the <code>Connection</code> to return
     * @return a connection associated with the client handle, <code>null</code> if one is not found
     */
    public Connection getConnection(String handle)
    {
        Log.d("Connections",handle);
        Connection coco = connections.get(handle);
        Log.d("Connections",coco.toString());
        return coco;
    }

    /**
     * Adds a <code>Connection</code> object to the collection of connections associated with this object
     * @param connection connection to add
     */
    void addConnection(Connection connection)
    {
        connections.put(connection.handle(), connection);
    }

    /**
     * Create a fully initialised <code>MqttAndroidClient</code> for the parameters given
     * @param context The Applications context
     * @param serverURI The ServerURI to connect to
     * @param clientId The clientId for this client
     * @return new instance of MqttAndroidClient
     */
    MqttAndroidClient createClient(Context context, String serverURI, String clientId)
    {
        return new MqttAndroidClient(context, serverURI, clientId);
    }

    /**
     * Get all the connections associated with this <code>Connections</code> object.
     * @return <code>Map</code> of connections
     */
    public Map<String, Connection> getConnections()
    {
        return connections;
    }

    /**
     * Removes a connection from the map of connections
     * @param connection connection to be removed
     */
    void removeConnection(Connection connection) {
        connections.remove(connection.handle());

    }

}
