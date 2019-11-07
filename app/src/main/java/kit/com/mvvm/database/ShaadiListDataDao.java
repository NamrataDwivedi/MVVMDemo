package kit.com.mvvm.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import kit.com.mvvm.database.modal.Shaadi;

@Dao
public interface ShaadiListDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Shaadi shaadi);

    @Query("SELECT * FROM shaadi_data")
    LiveData<List<Shaadi>> getAllListData();

    @Query("SELECT * FROM shaadi_data")
    List<Shaadi> getAllListDataSize();

    @Query("UPDATE shaadi_data SET accepted = :isAccepted, declined=:isDeclined WHERE email = :email")
    void updateIsAccepted(boolean isAccepted, boolean isDeclined, String email);

    @Query("SELECT * FROM shaadi_data WHERE email=:email")
    boolean checkIfInDb(String email);

}
