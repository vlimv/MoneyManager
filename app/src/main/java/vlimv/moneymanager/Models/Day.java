package vlimv.moneymanager.Models;

import android.view.View;

import java.util.List;

public class Day {
    private List<Expense> expenses;
    float balance;
    String date;

    View view;

    public Day(List<Expense> expenses, float balance, String date) {
        this.expenses = expenses;
        this.balance = balance;
        this.date = date;
    }

    public List<Expense> getExpenses() {
        return this.expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public List<Expense> addExpense(Expense expense) {
        this.expenses.add(expense);
        return this.expenses;
    }

    public float getBalance() {
        return this.balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public View getView(){return this.view;}
    public void setView(View view) {this.view = view;}

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
