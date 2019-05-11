package vlimv.moneymanager;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import vlimv.moneymanager.Models.Expense;


public class Utils {
    List<Expense> sortByDate(List<Expense> expenses) {
        List<Expense> sortedExpenses = new ArrayList<>();

        Collections.sort(expenses, new Expense.ExpenseComparator());

        return expenses;
    }

    public static int convertDpToPx(int dp, Context c) {
        Resources r = c.getResources();
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
        return px;
    }

}
