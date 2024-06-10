package Beans;

import java.time.LocalDateTime;


public interface CounterBeanMBean {

    public void addMiss(boolean value);
    public void updateIntervals(LocalDateTime currentTime);
    public double getAverageInterval();
}
