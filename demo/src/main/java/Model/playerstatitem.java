package Model;

/**
 * Created by TOQEER on 8/5/2015.
 */
public class playerstatitem {
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    String name;
    String country;
    String Pid;
    public String flag;
    public String pprofile;
    public String cover_image;
    public playerstatitem(String name) {
        this.name = name;
    }

    public String getPid() {
        return Pid;
    }

    public void setPid(String pid) {
        Pid = pid;
    }

    public String getPprofile() {
        return pprofile;
    }

    public void setPprofile(String pprofile) {
        this.pprofile = pprofile;
    }

    public String getCover_image() {
        return cover_image;
    }

    public void setCover_image(String cover_image) {
        this.cover_image = cover_image;
    }

    public playerstatitem(String name,String country,String Pid,String pprofile,String cover_image) {
        this.name = name;
        this.country=country;


        this.Pid=Pid;
        this.pprofile=pprofile;
        this.cover_image=cover_image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
