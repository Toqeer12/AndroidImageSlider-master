package Model;

/**
 * Created by TOQEER on 8/12/2015.
 */
public class CountryItem {

   public String country;
    public String flag;



    public CountryItem(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
