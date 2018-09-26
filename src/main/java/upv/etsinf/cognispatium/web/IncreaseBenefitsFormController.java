package upv.etsinf.cognispatium.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import upv.etsinf.cognispatium.service.IncreaseBenefits;
import upv.etsinf.cognispatium.service.ProfessionalManager;;

@Controller
@RequestMapping(value="/viewincreasebenefits.htm")
public class IncreaseBenefitsFormController {

    /** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private ProfessionalManager professionalManager;

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(@Valid IncreaseBenefits increaseBenefits, BindingResult result)
    {
        if (result.hasErrors()) {
            return "viewincreasebenefits";
        }
		
        int increase = increaseBenefits.getBenefits();
        logger.info("Increasing prices by " + increase + "%.");

        professionalManager.increaseBenefits(increase);;

        return "redirect:/hello.htm";
    }

    @RequestMapping(method = RequestMethod.GET)
    protected IncreaseBenefits formBackingObject(HttpServletRequest request) throws ServletException {
    	IncreaseBenefits increaseBenefits = new IncreaseBenefits();
    	increaseBenefits.setBenefits(15);
        return increaseBenefits;
    }

    public void setProductManager(ProfessionalManager professionalManager) {
        this.professionalManager = professionalManager;
    }
    
    @ModelAttribute("IncreaseBenefits")
    public ProfessionalManager getProfessionalManager() {
        return professionalManager;
    }

}