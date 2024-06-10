package Beans;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import workmodules.DatabaseHandler;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Data;

@jakarta.inject.Named
@ApplicationScoped
@Data
public class ResultsBean {

    private Result newResult= new Result();
    private List<Result> results= new ArrayList<Result>();
    private DatabaseHandler databaseHandler= new DatabaseHandler();
    private int firstResultIndex = 0;
    @Inject
    private CounterBean counterBean;

    public ResultsBean(){
        results= databaseHandler.load();
    }

    public void addNewResult(){
        double start = System.nanoTime();
        newResult.setValue(String.valueOf(validate(Double.valueOf(newResult.getX()), Double.valueOf(newResult.getY()), Double.valueOf(newResult.getR()))));
        double execTime = Math.round(((System.nanoTime() - start) * 0.00001) * 100.0) / 100.0;
        newResult.setExecTime(String.valueOf(execTime));
        newResult.setTime(String.valueOf(LocalDate.now()+" "+LocalTime.now().withNano(0)));
        counterBean.updateIntervals(LocalDateTime.now());
        counterBean.addMiss(Boolean.getBoolean(newResult.getValue()));
        results.add(newResult);
        databaseHandler.persist(newResult);
        newResult= new Result();
    }

    public void clearCollection(){
        System.out.println(1);
        results.clear();
        databaseHandler.clear();
    }

    private Boolean validate(Double x, Double y, Double r){
        if (x>=0 && y>=0 && x<=r/2 && y<=r){
            return true;
        }
        if (x<=0 && y<=0 && x>=-r && y>=x-r){
            return true;
        }
        if (x<=0 && y>=0 && Math.sqrt(x*x+y*y)<=r){
            return true;
        }
        return false;
    }

    public void nextPage() {
        firstResultIndex += 10;
    }

    public void previosPage() {
        if (firstResultIndex>=10){
            firstResultIndex -= 10;
        }
    }

    public Result getNewResult(){
        return this.newResult;
    }
}
