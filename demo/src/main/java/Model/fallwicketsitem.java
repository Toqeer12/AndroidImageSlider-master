package Model;

/**
 * Created by TOQEER on 9/9/2015.
 */
public class fallwicketsitem {
    public String name;
    public int i;
    public String over;
    public String ball1;
    public String ball2;
    public String ball3;
    public String ball4;
    public String ball5;
    public String ball6;
    public String overno;
    public String getBall1() {
        return ball1;
    }

    public void setBall1(String ball1) {
        this.ball1 = ball1;
    }

    public String getBall2() {
        return ball2;
    }

    public String getBall3() {
        return ball3;
    }

    public void setBall3(String ball3) {
        this.ball3 = ball3;
    }

    public String getBall4() {
        return ball4;
    }

    public void setBall4(String ball4) {
        this.ball4 = ball4;
    }

    public String getBall5() {
        return ball5;
    }

    public void setBall5(String ball5) {
        this.ball5 = ball5;
    }

    public String getBall6() {
        return ball6;
    }

    public void setBall6(String ball6) {
        this.ball6 = ball6;
    }

    public fallwicketsitem(String ball1, String ball2,String ball3,String ball4,String ball5,String ball6,String overno) {
        this.ball1 = ball1;
        this.ball2 = ball2;
        this.ball3 = ball3;
        this.ball4 = ball4;
        this.ball5 = ball5;
        this.ball6 = ball6;
        this.overno=overno;

    }

    public void setBall2(String ball2) {
        this.ball2 = ball2;
    }

    public fallwicketsitem(int i,String name, String run, String over) {
        this.name = name;
        this.run = run;
        this.over = over;
        this.i=i;
    }
    public fallwicketsitem(String name, String run, String over) {
        this.name = name;
        this.run = run;
        this.over = over;

    }
    public String run;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOver() {
        return over;
    }

    public void setOver(String over) {
        this.over = over;
    }

    public String getRun() {
        return run;
    }

    public void setRun(String run) {
        this.run = run;
    }
}
