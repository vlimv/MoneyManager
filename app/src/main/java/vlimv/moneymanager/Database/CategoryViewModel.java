package vlimv.moneymanager.Database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by HP on 25-Oct-18.
 */

public class CategoryViewModel extends AndroidViewModel {
    private AppRepository repository;
    private LiveData<List<CategoryEntity>> allCategories;

    public CategoryViewModel(@NonNull Application application) {
        super(application);
        repository = new AppRepository(application);
        allCategories = repository.getAllCategories();
    }

    public LiveData<List<CategoryEntity>> getAllCategories() { return allCategories; }
    public void insert(CategoryEntity categoryEntity) { repository.insert(categoryEntity); }
    public CategoryEntity getCategoryById(int id) { return repository.getCategoryById(id);}

}
