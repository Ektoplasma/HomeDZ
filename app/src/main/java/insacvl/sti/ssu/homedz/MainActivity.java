package insacvl.sti.ssu.homedz;

import android.content.Intent;
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

import insacvl.sti.ssu.homedz.pahowrapper.ClientConnections;
import insacvl.sti.ssu.homedz.pahowrapper.Connections;
import insacvl.sti.ssu.homedz.pahowrapper.NewConnection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent signIntent = new Intent(this, ClientConnections.class);
        this.startActivity(signIntent);
    }

}
