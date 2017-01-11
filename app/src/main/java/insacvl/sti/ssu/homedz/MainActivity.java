package insacvl.sti.ssu.homedz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.UnsupportedEncodingException;

import insacvl.sti.ssu.homedz.pahowrapper.Connections;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Connections conn = Connections.getInstance(getApplicationContext());

        String clientId = MqttClient.generateClientId();
        MqttAndroidClient client = conn.createClient(getApplicationContext(), "tcp://10.0.2.2:1883", clientId);

    }
}
