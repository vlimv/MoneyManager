package vlimv.moneymanager.Database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class ExpenseViewModel extends AndroidViewModel {
    private AppRepository repository;
    private LiveData<List<ExpenseEntity>> allExpenses;

    public ExpenseViewModel(@NonNull Application application) {
        super(application);
        repository = new AppRepository(application);
        allExpenses = repository.getAllExpenses();
    }

    public LiveData<List<ExpenseEntity>> getAllExpenses() { return allExpenses; }
    public void insert(ExpenseEntity expenseEntity) { repository.insert(expenseEntity); }
}
