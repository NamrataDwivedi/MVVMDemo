
package kit.com.mvvm.database.modal;

import androidx.room.ColumnInfo;

public class Name {

    @ColumnInfo(name ="title")
    private String title;

    @ColumnInfo(name ="first")
    private String first;

    @ColumnInfo(name ="last")
    private String last;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

}
