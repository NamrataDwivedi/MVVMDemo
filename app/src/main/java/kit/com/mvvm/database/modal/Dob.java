
package kit.com.mvvm.database.modal;

import androidx.room.ColumnInfo;

public class Dob {

    @ColumnInfo(name ="dob_date")
    private String date;

    @ColumnInfo(name ="dob_age")
    private Integer age;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}
