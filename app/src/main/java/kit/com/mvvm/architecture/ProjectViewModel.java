package kit.com.mvvm.architecture;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import kit.com.mvvm.MyApplication;
import kit.com.mvvm.database.modal.Shaadi;

public class ProjectViewModel extends ViewModel {

    private LiveData<List<Shaadi>> mShaadiList;

    public ProjectViewModel() {
        this.mShaadiList = MyApplication.getDatabseManager().getList();
    }

    public LiveData<List<Shaadi>> getShaadiList() {
        return mShaadiList;
    }
}
