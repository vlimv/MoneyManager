package vlimv.moneymanager.Database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by HP on 25-Oct-18.
 */

@Dao
public interface CategoryDao {
    @Insert
    void insert(CategoryEntity categoryEntity);

    @Query("DELETE FROM 'category_table'")
    void deleteAll();

    @Query("SELECT * from 'category_table'")
    LiveData<List<CategoryEntity>> getAllCategories();

    @Query("SELECT * FROM 'category_table' WHERE id = :id")
    CategoryEntity getCategoryById(int id);

}
