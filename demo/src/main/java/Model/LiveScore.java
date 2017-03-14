package Model;

import java.io.Serializable;

/**
 * Created by Danii on 5/14/2015.
 */
public class LiveScore implements Serializable{

    private String bothTeams;
    private String toss;
    private String batTeamName;
    private String batTeamScore;
    private String batsMan1;
    private String batsMan1Runs;
    private String batsMan1Balls;


    private String batsMan2;
    private String batsMan2Runs;
    private String batsMan2Balls;


    private String bowTeamName;

    private String bow1Name;
    private String bow1Over;
    private String bow1Maid;
    private String bow1Runs;
    private String bow1Wickets;
    private String teamAkey;
    private String teamBkey;
    private String innings;
    private String link;
    private  String ext;
    private String info;
    public String key;
    private String run;
    String dayy;
    String runstr;
    String teamAname;
    String teamBname;
    String mom;
    public String getBothTeams() {
        return bothTeams;
    }

    public String getToss() {
        return toss;
    }

    public String getBatTeamName() {
        return batTeamName;
    }

    public String getBatTeamScore() {
        return batTeamScore;
    }

    public String getBatsMan1() {
        return batsMan1;
    }

    public String getBatsMan1Runs() {
        return batsMan1Runs;
    }

    public String getBatsMan1Balls() {
        return batsMan1Balls;
    }



    public String getBatsMan2() {
        return batsMan2;
    }

    public String getBatsMan2Runs() {
        return batsMan2Runs;
    }

    public String getBatsMan2Balls() {
        return batsMan2Balls;
    }





    public String getBowTeamName() {
        return bowTeamName;
    }


    public String getBow1Name() {
        return bow1Name;
    }

    public String getBow1Over() {
        return bow1Over;
    }

    public String getBow1Maid() {
        return bow1Maid;
    }

    public String getBow1Runs() {
        return bow1Runs;
    }

    public String getBow1Wickets() {
        return bow1Wickets;
    }


    public String getTeamBkey() {
        return teamBkey;
    }

    public void setTeamBkey(String teamBkey) {
        this.teamBkey = teamBkey;
    }

    public String getTeamAkey() {
        return teamAkey;
    }

    public void setTeamAkey(String teamAkey) {
        this.teamAkey = teamAkey;
    }

    public String getInnings() {
        return innings;
    }

    public void setInnings(String innings) {
        this.innings = innings;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }


    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getRun() {
        return run;
    }

    public void setRun(String run) {
        this.run = run;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDayy() {
        return dayy;
    }

    public void setDayy(String dayy) {
        this.dayy = dayy;
    }

    public String getRunstr() {
        return runstr;
    }

    public void setRunstr(String runstr) {
        this.runstr = runstr;
    }

    String breakreason;

    public String getBreakreason() {
        return breakreason;
    }

    public void setBreakreason(String breakreason) {
        this.breakreason = breakreason;
    }

    public String getTeamBname() {
        return teamBname;
    }

    public void setTeamBname(String teamBname) {
        this.teamBname = teamBname;
    }

    public String getTeamAname() {
        return teamAname;
    }

    public void setTeamAname(String teamAname) {
        this.teamAname = teamAname;
    }

    public String getMom() {

        return mom;
    }

    public void setMom(String mom) {
        this.mom = mom;
    }

    public LiveScore(String bothTeams, String toss, String batTeamScore, String batsMan1, String batsMan1Runs, String batsMan1Balls
            ,String batsMan2, String batsMan2Runs, String batsMan2Balls
            ,String bow1Name, String bow1Over, String bow1Maid, String bow1Runs, String bow1Wickets
            ,String bowTeamName, String batTeamName,String teamAkey,String teamBkey,String innings,String link,String info,String run,String key,String dayy,String runstr,String breakreason,String teamAname,String teamBname,String mom) {
        this.bothTeams = bothTeams;
        this.key=key;
        this.toss = toss;
        this.batTeamScore = batTeamScore;
        this.batTeamScore = batTeamScore;
        this.batsMan1 = batsMan1;
        this.batsMan1Runs = batsMan1Runs;
        this.batsMan1Balls = batsMan1Balls;
        this.batsMan2 = batsMan2;
        this.runstr=runstr;

        this.breakreason=breakreason;
        this.batsMan2Runs = batsMan2Runs;
        this.batsMan2Balls = batsMan2Balls;
        this.bow1Name = bow1Name;
        this.mom=mom;
        this.dayy=dayy;
        this.teamAname=teamAname;
        this.teamBname=teamBname;
        this.run=run;
        this.info=info;
        this.link=link;
        this.bow1Over = bow1Over;
        this.bow1Maid = bow1Maid;
        this.bow1Runs = bow1Runs;
        this.bow1Wickets = bow1Wickets;
        this.batTeamName = batTeamName;
        this.bowTeamName = bowTeamName;
        this.teamAkey=teamAkey;
        this.teamBkey=teamBkey;
        this.innings=innings;
    }


}
