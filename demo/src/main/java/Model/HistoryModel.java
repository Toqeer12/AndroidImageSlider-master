package Model;

/**
 * Created by TOQEER on 9/11/2015.
 */
public class HistoryModel {

   String title;
    String Id;
     String year;
     String winner;
    String winnerrun;
    String runnerrun;
    String runner;
    String wino;
    String runo;
    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public HistoryModel(String id, String title) {
        this.title = title;

        this.Id=id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getRunner() {
        return runner;
    }

    public void setRunner(String runner) {
        this.runner = runner;
    }

    public String getWinnerrun() {
        return winnerrun;
    }

    public void setWinnerrun(String winnerrun) {
        this.winnerrun = winnerrun;
    }

    public String getRunnerrun() {
        return runnerrun;
    }

    public void setRunnerrun(String runnerrun) {
        this.runnerrun = runnerrun;
    }

    public String getWino() {
        return wino;
    }

    public void setWino(String wino) {
        this.wino = wino;
    }

    public String getRuno() {
        return runo;
    }

    public void setRuno(String runo) {
        this.runo = runo;
    }

    public HistoryModel(String year, String winner, String runner,String winnerrun,String wino,String runnerrun,String runo) {
        this.winner = winner;
        this.runner = runner;
            this.wino=wino;
        this.runo=runo;

            this.winnerrun=winnerrun;
        this.runnerrun=runnerrun;
        this.year = year;
    }
    public HistoryModel(String year, String winner, String runner,String winnerrun,String runnerrun) {
        this.winner = winner;
        this.runner = runner;


        this.winnerrun=winnerrun;
        this.runnerrun=runnerrun;
        this.year = year;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
