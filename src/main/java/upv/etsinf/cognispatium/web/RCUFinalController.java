package upv.etsinf.cognispatium.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class RCUFinalController {

    /** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

    @RequestMapping("/responderconsultaurgentefinal.htm")
    public String RespuestaCUForm(Model model) {
       // model.addAttribute("product", new Product());
        return "responderCUFinal";
    }
    
    
}