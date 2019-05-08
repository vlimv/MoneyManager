package vlimv.moneymanager.Adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import vlimv.moneymanager.R;

public class CategoryGridAdapter extends BaseAdapter{
    private Context mContext;
    private final String[] web;
    private final int[] Imageid;
    private final int[] colorId;

    public CategoryGridAdapter(Context c, String[] web, int[] Imageid, int[] colorId) {
        mContext = c;
        this.Imageid = Imageid;
        this.web = web;
        this.colorId = colorId;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return web.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            grid = new View(mContext);
            grid = inflater.inflate(R.layout.layout_category, null);
            TextView textView = (TextView) grid.findViewById(R.id.grid_text);
            ImageView imageView = (ImageView)grid.findViewById(R.id.grid_image);
            textView.setText(web[position]);
            imageView.setImageResource(Imageid[position]);
            imageView.setColorFilter(ContextCompat.getColor(mContext, colorId[position]), android.graphics.PorterDuff.Mode.MULTIPLY);
        } else {
            grid = (View) convertView;
        }

        return grid;
    }
}
