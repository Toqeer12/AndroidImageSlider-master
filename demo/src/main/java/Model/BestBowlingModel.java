package Model;

/**
 * Created by TOQEER on 9/21/2015.
 */
public class BestBowlingModel {

    String name;
    String opp;
    String o;
    String w;
    String r;
    String m;

    public String getMatch() {
        return match;
    }

    public void setMatch(String match) {
        this.match = match;
    }

    public String getCathces() {
        return cathces;
    }

    public void setCathces(String cathces) {
        this.cathces = cathces;
    }

    String er;
    String match;
    String cathces;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpp() {
        return opp;
    }

    public void setOpp(String opp) {
        this.opp = opp;
    }

    public String getO() {
        return o;
    }

    public void setO(String o) {
        this.o = o;
    }

    public String getW() {
        return w;
    }

    public void setW(String w) {
        this.w = w;
    }

    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
    }

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }

    public String getEr() {
        return er;
    }

    public void setEr(String er) {
        this.er = er;
    }

    public BestBowlingModel(String name, String opp, String o, String w, String r, String m, String er) {
        this.name = name;
        this.opp = opp;
        this.o = o;
        this.w = w;
        this.r = r;
        this.m = m;

        this.er = er;
    }
    public BestBowlingModel(String name,String match,String catches) {
        this.name = name;
        this.match=match;
        this.cathces=catches;

    }
}
