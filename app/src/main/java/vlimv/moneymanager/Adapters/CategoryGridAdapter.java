package vlimv.moneymanager.Adapters;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import vlimv.moneymanager.Database.CategoryEntity;
import vlimv.moneymanager.Database.CategoryViewModel;
import vlimv.moneymanager.R;

public class CategoryGridAdapter extends BaseAdapter{
    private Context mContext;
//    private final String[] web;
//    private final int[] Imageid;
//    private final int[] colorId;
    private List<CategoryEntity> categories;
    private int chosenCatId = -1;
    View chosenCatView = null;

    public CategoryGridAdapter(Context c) {
        mContext = c;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        if (categories != null) {
            return categories.size();
        }
        return 0;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null && categories != null) {
//            grid = new View(mContext);
            grid = inflater.inflate(R.layout.layout_category, null);

            final CategoryEntity mCat = categories.get(position);
            final TextView textView = grid.findViewById(R.id.grid_text);
            final ImageView imageView = grid.findViewById(R.id.grid_image);

            textView.setText(mCat.getName());
            imageView.setImageResource(mCat.getIcon());
            imageView.setColorFilter(ContextCompat.getColor(mContext, mCat.getColor()), android.graphics.PorterDuff.Mode.MULTIPLY);

            grid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (chosenCatId != -1) {
                        chosenCatView.setBackgroundColor(Color.WHITE);
                        ImageView img = chosenCatView.findViewById(R.id.grid_image);
                        CategoryEntity categ = categories.get(chosenCatId);
                        img.setColorFilter(ContextCompat.getColor(mContext, categ.getColor()), android.graphics.PorterDuff.Mode.MULTIPLY);
                    }
                    grid.setBackgroundResource(mCat.getColor());
                    imageView.setColorFilter(ContextCompat.getColor(mContext, R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);
                    chosenCatId = categories.get(position).getId();
                    chosenCatView = grid;

                }
            });
        } else {
            grid = (View) convertView;
        }

        return grid;
    }

    public void setCategories(List<CategoryEntity> mCategories){
        categories = mCategories;
        notifyDataSetChanged();

        for (int i = 0; i < categories.size(); i++) {
            System.out.println("Category " + categories.get(i).getName() + ", id " + categories.get(i).getId());
        }
    }

    public int getChosenCatId() {
        return chosenCatId;
    }

//    public CategoryGridAdapter(Context c, String[] web, int[] Imageid, int[] colorId) {
//        mContext = c;
//        this.Imageid = Imageid;
//        this.web = web;
//        this.colorId = colorId;
//    }
//
//    @Override
//    public int getCount() {
//        // TODO Auto-generated method stub
//        return web.length;
//    }
//
//    @Override
//    public Object getItem(int position) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public long getItemId(int position) {
//        // TODO Auto-generated method stub
//        return 0;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        // TODO Auto-generated method stub
//        View grid;
//        LayoutInflater inflater = (LayoutInflater) mContext
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//        if (convertView == null) {
//            grid = new View(mContext);
//            grid = inflater.inflate(R.layout.layout_category, null);
//            TextView textView = (TextView) grid.findViewById(R.id.grid_text);
//            ImageView imageView = (ImageView)grid.findViewById(R.id.grid_image);
//            textView.setText(web[position]);
//            imageView.setImageResource(Imageid[position]);
//            imageView.setColorFilter(ContextCompat.getColor(mContext, colorId[position]), android.graphics.PorterDuff.Mode.MULTIPLY);
//        } else {
//            grid = (View) convertView;
//        }
//
//        return grid;
//    }
//
//    public void setCategories(List<CategoryEntity> mCategories){
//        categories = mCategories;
//        notifyDataSetChanged();
//    }

}
