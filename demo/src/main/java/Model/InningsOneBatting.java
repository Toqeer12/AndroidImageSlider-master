package Model;

/**
 * Created by Danii on 5/20/2015.
 */
public class InningsOneBatting {

    private String BATSMEN_1_NAME;
    private String status;
    private String BATSMEN_1_RUNS;
    private String BATSMEN_1_BALLS;
    private String BATSMEN_1_4S;
    private String BATSMEN_1_6S;
    private String BATSMEN_1_SR;
    private String batmanstatus;
    public String name;
    public String run;

    public String getRun() {
        return run;
    }

    public void setRun(String run) {
        this.run = run;
    }

    public void setInningsOneExtras(String inningsOneExtras) {
        this.inningsOneExtras = inningsOneExtras;
    }

    public InningsOneBatting(){

    }
    private String inningsOneExtras;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBatmanstatus() {
        return batmanstatus;
    }

    public void setBatmanstatus(String batmanstatus) {
        this.batmanstatus = batmanstatus;
    }

    public InningsOneBatting(String BATSMEN_1_NAME,String BATSMEN_1_RUNS, String BATSMEN_1_BALLS, String BATSMEN_1_4S, String BATSMEN_1_6S, String BATSMEN_1_SR,String batmanstatus,String name) {
        this.batmanstatus=batmanstatus;
        this.BATSMEN_1_NAME = BATSMEN_1_NAME;
        this.BATSMEN_1_RUNS = BATSMEN_1_RUNS;
        this.BATSMEN_1_BALLS = BATSMEN_1_BALLS;
        this.name=name;
        this.BATSMEN_1_4S = BATSMEN_1_4S;
        this.BATSMEN_1_6S = BATSMEN_1_6S;
        this.BATSMEN_1_SR = BATSMEN_1_SR;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBATSMEN_1_NAME() {
        return BATSMEN_1_NAME;
    }

    public void setBATSMEN_1_NAME(String BATSMEN_1_NAME) {
        this.BATSMEN_1_NAME = BATSMEN_1_NAME;
    }

    public String getBATSMEN_1_STATUS() {
        return status;
    }

    public void setBATSMEN_1_STATUS(String BATSMEN_1_STATUS) {
        this.status = BATSMEN_1_STATUS;
    }

    public String getBATSMEN_1_RUNS() {
        return BATSMEN_1_RUNS;
    }

    public void setBATSMEN_1_RUNS(String BATSMEN_1_RUNS) {
        this.BATSMEN_1_RUNS = BATSMEN_1_RUNS;
    }

    public String getBATSMEN_1_BALLS() {
        return BATSMEN_1_BALLS;
    }

    public void setBATSMEN_1_BALLS(String BATSMEN_1_BALLS) {
        this.BATSMEN_1_BALLS = BATSMEN_1_BALLS;
    }

    public String getBATSMEN_1_4S() {
        return BATSMEN_1_4S;
    }

    public void setBATSMEN_1_4S(String BATSMEN_1_4S) {
        this.BATSMEN_1_4S = BATSMEN_1_4S;
    }

    public String getBATSMEN_1_6S() {
        return BATSMEN_1_6S;
    }

    public void setBATSMEN_1_6S(String BATSMEN_1_6S) {
        this.BATSMEN_1_6S = BATSMEN_1_6S;
    }

    public String getBATSMEN_1_SR() {
        return BATSMEN_1_SR;
    }

    public void setBATSMEN_1_SR(String BATSMEN_1_SR) {
        this.BATSMEN_1_SR = BATSMEN_1_SR;
    }

    public String getInningsOneExtras() {
        return inningsOneExtras;
    }
}
