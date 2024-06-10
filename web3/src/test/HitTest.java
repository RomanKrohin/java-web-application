import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.Test;

import utils.ResultsBean;

/**
 * HitTest
 */
public class HitTest {

    @Test
    public void hitTrue(){
        assertTrue(validate(0.0, 0.0, 1.0));;
    }

    @Test
    public void hitFalse(){
        assertFalse(validate(5.0, 5.0, 1.0));
    }
    
    public Boolean validate(Double x, Double y, Double r){
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
}