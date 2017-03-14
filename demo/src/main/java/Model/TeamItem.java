package Model;

/**
 * Created by T.A on 14/05/2015.
 */
public class TeamItem  {

    public String team;
    public String matches;
    public String points;
    public String rating;
    public String id;

    public TeamItem(String id,String team,String matches, String points,String rating) {
        this.team = team;
        this.matches = matches;
        this.points = points;
        this.rating = rating;
        this.id=id;

    }
}
