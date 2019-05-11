package vlimv.moneymanager.Models;

import android.view.View;


import java.util.Comparator;
import java.util.Date;

public class Expense {
    private Category category;
    private int categoryId;
//    private String date;
    private Date date;

    private float sum;
    private String from = "debit card";

    private View view;

//    public Expense(Category category, String date, float sum) {
//        this.category = category;
//        this.date = date;
//        this.sum = sum;
//    }

    public Expense(int categoryId, Date date, float sum) {
        this.categoryId = categoryId;
        this.date = date;

        this.sum = sum;
    }

    public Category getCategory(){return this.category;}
    public void setCategory(Category category) {this.category = category;}

//    public String getDate(){return this.date;}
//    public void setDate(String date) {this.date = date;}
//
    public Date getDate(){return this.date;}
    public void setDate(Date date) {this.date = date;}

    public View getView(){return this.view;}
    public void setView(View view) {this.view = view;}

    public float getSum(){return this.sum;}
    public void setSum (float sum) {this.sum = sum;}

    public static class ExpenseComparator implements Comparator<Expense>
    {
        public int compare(Expense e1, Expense e2)
        {
            return e1.getDate().compareTo(e2.getDate());
        }
    }
}
