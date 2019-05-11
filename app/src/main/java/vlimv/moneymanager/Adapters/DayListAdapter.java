package vlimv.moneymanager.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import vlimv.moneymanager.Database.ExpenseEntity;
import vlimv.moneymanager.Models.Day;
import vlimv.moneymanager.Models.Expense;
import vlimv.moneymanager.R;
import vlimv.moneymanager.Utils;

/**
 * Created by HP on 10-Oct-18.
 */

public class DayListAdapter extends RecyclerView.Adapter<DayListAdapter.ViewHolder> {
    private List<Day> mDays = new ArrayList<>();
    private ArrayList<TextView> dateTextViews = new ArrayList<>();
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context context;

    // data is passed into the constructor
//    DayListAdapter(Context context, List<Day> days) {
//        this.mInflater = LayoutInflater.from(context);
//        this.mDays = days;
//    }

    public DayListAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_day, parent, false);

        int margin = Utils.convertDpToPx(8, context);
        for (int i = 0; i < 5; i++) {
            View expenseLayout = mInflater.inflate(R.layout.item_expense, (ViewGroup) view, false);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(margin, margin, margin, margin);
            expenseLayout.setLayoutParams(layoutParams);
            ((ViewGroup) view).addView(expenseLayout);
        }


        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        String animal = mData.get(position);
//        holder.myTextView.setText(animal);

        holder.dateTextView.setText(mDays.get(position).getDate());
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mDays.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView dateTextView;

        ViewHolder(View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.date);



//            View child = LayoutInflater.from(context).inflate(R.layout.item_expense, null);
//            View.inflate(context, layout, parent)
//            inflate(R.layout.item_expense, null);
//            todayView.addView(child);
//            dateTextViews.add(dateTextView);
//            dateTextView.setText(mDays.get(i).getDate());
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
            Toast.makeText(context, "clicked " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
        }
    }


    public void filterExpenses(List<ExpenseEntity> expenseEntities) {
        mDays.clear();
//        String lastDay = "";
        Date lastDay = null;
        ArrayList<Expense> dayExpenses = new ArrayList<>();
        Format f = new SimpleDateFormat("dd/MM/yy");
        for (int i = 0; i < expenseEntities.size(); i++) {
            Expense expense = expenseEntities.get(i).toExpense();
            if (lastDay == null) {
                lastDay = expense.getDate();
                dayExpenses.add(expense);
            } else if (lastDay.compareTo(expense.getDate()) == 0) {
                String date = f.format(lastDay);

                Day day = new Day(dayExpenses, 50.0f, date);
                dayExpenses.clear();
                lastDay = null;
                mDays.add(day);
            } else {
                dayExpenses.add(expense);
            }
        }
        notifyDataSetChanged();
    }

    private void addViews() {

    }
    // convenience method for getting data at click position
    Day getItem(int id) {
        return mDays.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
