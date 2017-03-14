package Model;

/**
 * Created by TOQEER on 9/19/2015.
 */
public class PointTable {
    String country;
    String p;
    String w;
    String l;
    String t;

    String nr;
    String pts;
    String nrr;
    public PointTable(String country, String w, String p, String l, String t, String nr, String pts, String nrr) {
        this.country = country;
        this.w = w;
        this.p = p;
        this.l = l;
        this.t = t;
        this.nr = nr;
        this.pts = pts;
        this.nrr = nrr;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

    public String getW() {
        return w;
    }

    public void setW(String w) {
        this.w = w;
    }

    public String getL() {
        return l;
    }

    public void setL(String l) {
        this.l = l;
    }

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }

    public String getPts() {
        return pts;
    }

    public void setPts(String pts) {
        this.pts = pts;
    }

    public String getNrr() {
        return nrr;
    }

    public void setNrr(String nrr) {
        this.nrr = nrr;
    }
}
