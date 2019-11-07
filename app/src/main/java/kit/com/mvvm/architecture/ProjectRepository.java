package kit.com.mvvm.architecture;

import android.util.Log;

import kit.com.mvvm.MyApplication;
import kit.com.mvvm.database.modal.Shaadi;
import kit.com.mvvm.database.modal.ShaadiListData;
import kit.com.mvvm.network.APIClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectRepository {

    private static ProjectRepository mInstance;

    public ProjectRepository() {

    }

    public void getShaadiListData() {

        new APIClient().getApiInterface().getShaadiData()
                .enqueue(new Callback<ShaadiListData<Shaadi>>() {
                    @Override
                    public void onResponse(Call<ShaadiListData<Shaadi>> call, Response<ShaadiListData<Shaadi>> response) {

                        ShaadiListData<Shaadi> shaadiShaadiListData = response.body();
                        if (shaadiShaadiListData != null)
                            if (shaadiShaadiListData.getResults().size() > 0) {
                                for (Shaadi value : shaadiShaadiListData.getResults()) {
                                    if (!MyApplication.getDatabseManager().checkIfInDb(value.getEmail())) {
                                        MyApplication.getDatabseManager().insert(value);
                                    }
                                }
                            }
                    }

                    @Override
                    public void onFailure(Call<ShaadiListData<Shaadi>> call, Throwable t) {
                        Log.e("", "onFailure: " + t.getMessage());
                    }
                });
    }
}
