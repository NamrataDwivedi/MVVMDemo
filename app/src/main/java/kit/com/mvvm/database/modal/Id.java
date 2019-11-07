
package kit.com.mvvm.database.modal;

import androidx.room.ColumnInfo;

public class Id {

    @ColumnInfo(name ="user_name")
    private String name;

    @ColumnInfo(name ="value")
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
