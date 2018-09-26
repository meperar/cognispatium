package upv.etsinf.cognispatium.service;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class IncreaseBenefits {

    /** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

    @Min(0)
    @Max(50)
    private int benefits;

    public void setBenefits(int i) {
    	benefits = i;
        logger.info("Benefits set to " + i);
    }

    public int getBenefits() {
        return benefits;
    }
}