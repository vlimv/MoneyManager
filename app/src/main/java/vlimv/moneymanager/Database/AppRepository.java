package vlimv.moneymanager.Database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

/**
 * Created by HP on 25-Oct-18.
 */

public class AppRepository {
    private CategoryDao categoryDao;
    private ExpenseDao expenseDao;

    private LiveData<List<CategoryEntity>> allCategories;
    private LiveData<List<ExpenseEntity>> allExpenses;

    AppRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        categoryDao = db.categoryDao();
        expenseDao = db.expenseDao();

        allCategories = categoryDao.getAllCategories();
        allExpenses = expenseDao.getAllExpenses();
    }

    LiveData<List<CategoryEntity>> getAllCategories() {
        return allCategories;
    }

    LiveData<List<ExpenseEntity>> getAllExpenses() {
        return allExpenses;
    }

    public void insert (CategoryEntity categoryEntity) {
        new insertCategoryAsyncTask(categoryDao).execute(categoryEntity);
    }

    public void insert (ExpenseEntity expenseEntity) {
        new insertExpenseAsyncTask(expenseDao).execute(expenseEntity);
    }

    CategoryEntity getCategoryById(int id) {
        return categoryDao.getCategoryById(id);
    }

    private static class insertCategoryAsyncTask extends AsyncTask<CategoryEntity, Void, Void> {

        private CategoryDao mAsyncTaskDao;

        insertCategoryAsyncTask(CategoryDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final CategoryEntity... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class insertExpenseAsyncTask extends AsyncTask<ExpenseEntity, Void, Void> {

        private ExpenseDao mAsyncTaskDao;

        insertExpenseAsyncTask(ExpenseDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final ExpenseEntity... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
