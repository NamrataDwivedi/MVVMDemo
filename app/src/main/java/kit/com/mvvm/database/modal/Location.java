
package kit.com.mvvm.database.modal;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;

public class Location {

    @Embedded
    private Street street;

    @ColumnInfo(name ="city")
    private String city;

    @ColumnInfo(name ="state")
    private String state;

    @ColumnInfo(name ="country")
    private String country;

    @ColumnInfo(name ="postcode")
    private String postcode;

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

}
