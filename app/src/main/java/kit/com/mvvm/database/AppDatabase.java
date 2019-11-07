package kit.com.mvvm.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import kit.com.mvvm.database.modal.Shaadi;


@Database(entities = {
        Shaadi.class
},version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public static final  String DB_NAME="shaadi_db";

    public abstract ShaadiListDataDao shaadiDao();
}
