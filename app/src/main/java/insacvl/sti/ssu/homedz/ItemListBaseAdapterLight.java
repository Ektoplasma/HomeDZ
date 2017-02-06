package insacvl.sti.ssu.homedz;

import android.content.ClipData;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;

import insacvl.sti.ssu.homedz.pahowrapper.ActionListener;
import insacvl.sti.ssu.homedz.pahowrapper.ActivityConstants;
import insacvl.sti.ssu.homedz.pahowrapper.Connections;

import java.util.ArrayList;

public class ItemListBaseAdapterLight extends BaseAdapter {

    private Context context = null;
    private static ArrayList<ItemDetails> itemDetailsrrayList;
    private LayoutInflater l_Inflater;

    public ItemListBaseAdapterLight(Context context, ArrayList<ItemDetails> results) {
        this.context = context;
        itemDetailsrrayList = results;
        l_Inflater = LayoutInflater.from(context);
    }

    public void setResults(ArrayList<ItemDetails> results)
    {
        itemDetailsrrayList = results;
       // l_Inflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return itemDetailsrrayList.size();
    }

    public Object getItem(int position) {
        return itemDetailsrrayList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = l_Inflater.inflate(R.layout.item_details_view_light, null);
            holder = new ViewHolder();
            holder.txt_itemName = (TextView) convertView.findViewById(R.id.name);
            holder.txt_id = (TextView) convertView.findViewById(R.id.id);
            holder.img_item = (ImageView) convertView.findViewById(R.id.photo);
            holder.img_btn = (ImageButton) convertView.findViewById(R.id.imgbtn);
            holder.txt_desc = (TextView) convertView.findViewById(R.id.desc);
            holder.txt_val = (TextView) convertView.findViewById(R.id.val);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txt_itemName.setText(itemDetailsrrayList.get(position).getName());
        holder.txt_id.setText(Integer.toString(itemDetailsrrayList.get(position).getId()));
        holder.img_item.setImageResource(itemDetailsrrayList.get(position).getImageNumber());
        holder.txt_desc.setText(itemDetailsrrayList.get(position).getDesc());
        holder.txt_val.setText(Integer.toString(itemDetailsrrayList.get(position).getVal()));

        holder.img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (itemDetailsrrayList.get(position).getImageNumber() == R.drawable.lightbulb_icon_on64) {
                    holder.img_item.setImageResource(R.drawable.lightbulb_icon_off64);
                    itemDetailsrrayList.get(position).setImageNumber(R.drawable.lightbulb_icon_off64);

                    try {
                        String topic = "domoticz/in";
                        String message = "{\"command\": \"switchlight\", \"idx\": "+itemDetailsrrayList.get(position).getId()+", \"switchcmd\": \"Off\" }";
                        String args[] = new String[2];

                        args[0] = message;
                        args[1] = topic;

                        Connections.getInstance(context).getConnection(ActivityConstants.currentHandler).getClient()
                                .publish(topic, message.getBytes(), 0, false, null, new ActionListener(context, ActionListener.Action.PUBLISH, ActivityConstants.currentHandler, args));
                    }
                    catch (MqttSecurityException e) {
                        Log.e(this.getClass().getCanonicalName(), "Failed to publish a messged from the client with the handle " + ActivityConstants.currentHandler, e);
                    }
                    catch (MqttException e) {
                        Log.e(this.getClass().getCanonicalName(), "Failed to publish a messged from the client with the handle " + ActivityConstants.currentHandler, e);
                    }
                } else if (itemDetailsrrayList.get(position).getImageNumber() == R.drawable.lightbulb_icon_off64) {
                    holder.img_item.setImageResource(R.drawable.lightbulb_icon_on64);
                    itemDetailsrrayList.get(position).setImageNumber(R.drawable.lightbulb_icon_on64);
                    try {
                        String topic = "domoticz/in";
                        String message = "{\"command\": \"switchlight\", \"idx\": "+itemDetailsrrayList.get(position).getId()+", \"switchcmd\": \"On\", \"level\": 100 }";
                        String args[] = new String[2];

                        args[0] = message;
                        args[1] = topic;

                        Connections.getInstance(context).getConnection(ActivityConstants.currentHandler).getClient()
                                .publish(topic, message.getBytes(), 0, false, null, new ActionListener(context, ActionListener.Action.PUBLISH, ActivityConstants.currentHandler, args));
                    }
                    catch (MqttSecurityException e) {
                        Log.e(this.getClass().getCanonicalName(), "Failed to publish a messged from the client with the handle " + ActivityConstants.currentHandler, e);
                    }
                    catch (MqttException e) {
                        Log.e(this.getClass().getCanonicalName(), "Failed to publish a messged from the client with the handle " + ActivityConstants.currentHandler, e);
                    }
                }
                else{
                    holder.txt_val.setText(Integer.toString(itemDetailsrrayList.get(position).getVal() + 1));
                    itemDetailsrrayList.get(position).setVal(itemDetailsrrayList.get(position).getVal() + 1);

                }
            }
        });
        return convertView;
    }

    static class ViewHolder {
        TextView txt_itemName;
        TextView txt_id;
        ImageView img_item;
        ImageButton img_btn;
        TextView txt_desc;
        TextView txt_val;
    }
}