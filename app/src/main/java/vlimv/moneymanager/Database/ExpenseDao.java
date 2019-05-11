package vlimv.moneymanager.Database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ExpenseDao {
    @Insert
    void insert(ExpenseEntity expenseEntity);

    @Query("DELETE FROM 'expense_table'")
    void deleteAll();

    @Query("SELECT * from 'expense_table'")
    LiveData<List<ExpenseEntity>> getAllExpenses();

}
