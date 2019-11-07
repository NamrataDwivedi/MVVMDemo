
package kit.com.mvvm.database.modal;

import androidx.room.ColumnInfo;

public class Street {

    @ColumnInfo(name ="number")
    private Integer number;

    @ColumnInfo(name ="street_name")
    private String name;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
