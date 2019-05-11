package vlimv.moneymanager.Database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import vlimv.moneymanager.Models.Expense;

/**
 * Created by HP on 25-Oct-18.
 */

@Database(entities = {CategoryEntity.class, ExpenseEntity.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CategoryDao categoryDao();
    public abstract ExpenseDao expenseDao();

    private static volatile AppDatabase INSTANCE;

    static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
//                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
//                            CategoryRoomDatabase.class, "category_database")
//                            .addCallback(sRoomDatabaseCallback)
//                            .build();
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "category_database")
                            .addMigrations(MIGRATION_1_2)
                            .addMigrations(MIGRATION_2_3)
//                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE 'category_table' ADD COLUMN 'color' INTEGER NOT NULL DEFAULT 0");

        }
    };

    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS 'expense_table'" +
                    "('id' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "'category_id' INTEGER NOT NULL," +
                    "'date' TEXT NOT NULL," +
                    "'sum' REAL NOT NULL," +
                    "FOREIGN KEY('category_id') REFERENCES 'category_table'('id'))");

        }
    };
//
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){
                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };


    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final ExpenseDao mDao;

        PopulateDbAsync(AppDatabase db) {
            mDao = db.expenseDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAll();
//            for (int i = 0; i < 3; i++) {
//                ExpenseEntity e = new ExpenseEntity(1, "06.09", 20.f);
//                mDao.insert(e);
//            }
//
//            for (int i = 0; i < 3; i++) {
//                ExpenseEntity e = new ExpenseEntity(1, "07.09", 40.f);
//                mDao.insert(e);
//            }
//
//            for (int i = 0; i < 3; i++) {
//                ExpenseEntity e = new ExpenseEntity(1, "09.09", 10.f);
//                mDao.insert(e);
//            }
            return null;
        }
    }

}


