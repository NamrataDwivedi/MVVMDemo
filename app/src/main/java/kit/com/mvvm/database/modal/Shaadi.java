
package kit.com.mvvm.database.modal;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "shaadi_data")
public class Shaadi {

    @ColumnInfo(name = "gender")
    private String gender;

    @Embedded
    private Name name;

    @Embedded
    private Location location;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name ="email")
    private String email;

    @Embedded
    private Dob dob;

    @ColumnInfo(name ="phone")
    private String phone;

    @ColumnInfo(name ="cell")
    private String cell;

    @Embedded
    private Picture picture;

    @ColumnInfo(name ="nat")
    private String nat;

    @ColumnInfo(name = "accepted")
    private boolean accepted = false;

    @ColumnInfo(name = "declined")
    private boolean declined = false;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Dob getDob() {
        return dob;
    }

    public void setDob(Dob dob) {
        this.dob = dob;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public String getNat() {
        return nat;
    }

    public void setNat(String nat) {
        this.nat = nat;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public boolean isDeclined() {
        return declined;
    }

    public void setDeclined(boolean declined) {
        this.declined = declined;
    }
}
