package Beans;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import lombok.Data;


@Data
@jakarta.inject.Named
@jakarta.enterprise.context.ApplicationScoped
public class CounterBean implements Serializable, CounterBeanMBean {

    private int missCounter=0;
    private ArrayList<Long> timeList = new ArrayList<>();
    private LocalDateTime lastResultTime;
    

    @Override
    public void addMiss(boolean value){
        if (value){
            this.missCounter++;
            if (missCounter%3==0) {
                System.out.println("TripleMiss");
                this.missCounter=0;
            }
        }
        else{
            this.missCounter=0;
        }
    }

    @Override
    public void updateIntervals(LocalDateTime currentTime){
        if (lastResultTime != null) {
            long interval = ChronoUnit.SECONDS.between(lastResultTime, currentTime);
            timeList.add(interval);
        }
        lastResultTime = currentTime;
    }

    @Override
    public double getAverageInterval() {
        if (timeList.isEmpty()) {
            return 0.0;
        }
        long total = 0;
        for (Long interval : timeList) {
            total += interval;
        }
        return (double) total / timeList.size();
    }
    
}
