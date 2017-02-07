package insacvl.sti.ssu.homedz;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Adapter to the list of therm sensors
 */
class ItemListBaseAdapterTherm extends BaseAdapter {
    private static ArrayList<ItemDetails> itemDetailsrrayList;

    private LayoutInflater l_Inflater;

    ItemListBaseAdapterTherm(Context context, ArrayList<ItemDetails> results) {
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
            convertView = l_Inflater.inflate(R.layout.item_details_view_therm, null);
            holder = new ViewHolder();
            holder.txt_itemName = (TextView) convertView.findViewById(R.id.name);
            holder.txt_id = (TextView) convertView.findViewById(R.id.id);
            holder.img_item = (ImageView) convertView.findViewById(R.id.photo);
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

        return convertView;
    }

    void setResults(ArrayList<ItemDetails> image_details) {
        itemDetailsrrayList = image_details;

    }

    private static class ViewHolder {
        TextView txt_itemName;
        TextView txt_id;
        ImageView img_item;
        TextView txt_desc;
        TextView txt_val;
    }
}