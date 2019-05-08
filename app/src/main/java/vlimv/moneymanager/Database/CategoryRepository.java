package vlimv.moneymanager.Database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

/**
 * Created by HP on 25-Oct-18.
 */

public class CategoryRepository {
    private CategoryDao categoryDao;
    private LiveData<List<CategoryEntity>> allCategories;

    CategoryRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        categoryDao = db.categoryDao();
        allCategories = categoryDao.getAllCategories();
    }

    LiveData<List<CategoryEntity>> getAllCategories() {
        return allCategories;
    }

    public void insert (CategoryEntity categoryEntity) {
        new insertAsyncTask(categoryDao).execute(categoryEntity);
    }

    private static class insertAsyncTask extends AsyncTask<CategoryEntity, Void, Void> {

        private CategoryDao mAsyncTaskDao;

        insertAsyncTask(CategoryDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final CategoryEntity... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
