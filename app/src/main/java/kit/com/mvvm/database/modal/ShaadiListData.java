
package kit.com.mvvm.database.modal;

import androidx.room.ColumnInfo;

import com.google.gson.annotations.Expose;

import java.util.List;

public class ShaadiListData<T>{

    @ColumnInfo(name = "shaadi_list")
    @Expose
    private List<T> results = null;
    @ColumnInfo(name = "info")
    @Expose
    private Info info;

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

}
