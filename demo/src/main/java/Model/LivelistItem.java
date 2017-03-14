package Model;

import java.io.Serializable;

/**
 * Created by T.A on 21/05/2015.
 */
public class LivelistItem implements Serializable{
    public String teamone;

    public String getTeamone() {
        return teamone;
    }

    public String getTeamtwo() {
        return teamtwo;
    }

    public void setTeamtwo(String teamtwo) {
        this.teamtwo = teamtwo;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }

    public void setTeamone(String teamone) {
        this.teamone = teamone;

    }

    public String teamtwo;
    public String Link;
    public String type;
    public String check;
    public String date;
    public String ven;
    public String season;
    public String related;
    public String winner;
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String key;
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String status;

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public LivelistItem(String teamone,String teamtwo, String status,String key,String date,String season,String ven,String winner,String related) {
        this.teamone = teamone;
        this.teamtwo = teamtwo;
        this.season = season;
        this.ven=ven;
        this.status=status;
        this.winner=winner;
        this.key=key;
        this.date=date;
        this.related=related;
    }
    public LivelistItem(String teamone,String teamtwo, String status,String key,String date,String season,String ven,String related) {
        this.teamone = teamone;
        this.teamtwo = teamtwo;
        this.season = season;
        this.ven=ven;
        this.status=status;
        this.related=related;
        this.key=key;
        this.date=date;

    }
}
