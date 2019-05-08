package vlimv.moneymanager;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import vlimv.moneymanager.Database.CategoryEntity;

/**
 * Created by HP on 25-Oct-18.
 */

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.CategoryViewHolder>{
    private Context mContext;
    class CategoryViewHolder extends RecyclerView.ViewHolder {
        private final TextView catNameView;
        private final ImageView catIconView;

        private CategoryViewHolder(View itemView) {
            super(itemView);
            catNameView = itemView.findViewById(R.id.name);
            catIconView = itemView.findViewById(R.id.icon);
        }
    }

    private final LayoutInflater mInflater;
    private List<CategoryEntity> categories;

    CategoryListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.mContext = context;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        if (categories != null) {
            CategoryEntity current = categories.get(position);
            holder.catNameView.setText(current.getName());
            holder.catIconView.setImageResource(current.getIcon());
            if (current.getColor() != 0) {
                holder.catIconView.setColorFilter(ContextCompat.getColor(mContext, current.getColor()), android.graphics.PorterDuff.Mode.MULTIPLY);
            }

        } else {
            // Covers the case of data not being ready yet.
            holder.catNameView.setText("Здесь ничего");
        }
    }

    void setCategories(List<CategoryEntity> mCategories){
        categories = mCategories;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (categories != null)
            return categories.size();
        else return 0;
    }
}
