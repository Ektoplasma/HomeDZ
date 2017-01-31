package insacvl.sti.ssu.homedz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class ItemListBaseAdapterLight extends BaseAdapter {
    private static ArrayList<ItemDetails> itemDetailsrrayList;
    private LayoutInflater l_Inflater;

    public ItemListBaseAdapterLight(Context context, ArrayList<ItemDetails> results) {
        itemDetailsrrayList = results;
        l_Inflater = LayoutInflater.from(context);
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
        holder.txt_id.setText(itemDetailsrrayList.get(position).getId());
        holder.img_item.setImageResource(itemDetailsrrayList.get(position).getImageNumber());
        holder.txt_desc.setText(itemDetailsrrayList.get(position).getDesc());
        holder.txt_val.setText(Integer.toString(itemDetailsrrayList.get(position).getVal()));

        holder.img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (itemDetailsrrayList.get(position).getImageNumber() == R.drawable.lightbulb_icon_on64) {
                    holder.img_item.setImageResource(R.drawable.lightbulb_icon_off64);
                    itemDetailsrrayList.get(position).setImageNumber(R.drawable.lightbulb_icon_off64);
                } else if (itemDetailsrrayList.get(position).getImageNumber() == R.drawable.lightbulb_icon_off64) {
                    holder.img_item.setImageResource(R.drawable.lightbulb_icon_on64);
                    itemDetailsrrayList.get(position).setImageNumber(R.drawable.lightbulb_icon_on64);
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