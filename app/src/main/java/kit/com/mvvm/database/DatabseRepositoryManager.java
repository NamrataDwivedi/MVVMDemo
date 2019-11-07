package kit.com.mvvm.database;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import java.util.List;

import kit.com.mvvm.database.modal.Shaadi;

public class DatabseRepositoryManager {

    Context mContext;

    private  AppDatabase appDatabase;

    public DatabseRepositoryManager(Context mContext) {
        this.mContext = mContext;
        appDatabase = Room.databaseBuilder(mContext,AppDatabase.class,
                AppDatabase.DB_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }

    public void insert(Shaadi shaadi){
        appDatabase.shaadiDao().insert(shaadi);
    }

    public LiveData<List<Shaadi>> getList(){
        return appDatabase.shaadiDao().getAllListData();
    }

    public List<Shaadi> getListInDb(){
        return appDatabase.shaadiDao().getAllListDataSize();
    }

    public void update(boolean isAccepted, boolean isDeclined, String email){
        appDatabase.shaadiDao().updateIsAccepted(isAccepted,isDeclined,email);
    }

    public boolean checkIfInDb(String email){
        return appDatabase.shaadiDao().checkIfInDb(email);
    }

}
