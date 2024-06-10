package Beans;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.ApplicationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.inject.Named;


@Named
@ApplicationScoped
public class Agent {
    
    @Inject
    private CounterBean counterBean;

    @PostConstruct
    public void initAgent(){
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName mBean;
        try{
            mBean = new ObjectName("Beans:type=CounterBean");
            if (!mbs.isRegistered(mBean)){
                mbs.registerMBean(counterBean, mBean);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void logSimpleAgentStarted(){
        System.out.println("SimpleAgent.logSimpleAgentStarted");
    }

    public void sartup(@Observes @Initialized(ApplicationScoped.class) Object object){
        Agent a = new Agent();
        a.logSimpleAgentStarted();
    }
}
