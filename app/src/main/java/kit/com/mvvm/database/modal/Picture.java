
package kit.com.mvvm.database.modal;

import androidx.room.ColumnInfo;

public class Picture {

    @ColumnInfo(name = "large")
    private String large;

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

}
