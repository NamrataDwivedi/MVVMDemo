package kit.com.mvvm.network;

import kit.com.mvvm.database.modal.Shaadi;
import kit.com.mvvm.database.modal.ShaadiListData;
import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("/api/?results=10")
    Call<ShaadiListData<Shaadi>> getShaadiData();

}
