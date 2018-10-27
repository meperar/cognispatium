package upv.etsinf.cognispatium.service;

import java.io.Serializable;
import java.util.List;

import upv.etsinf.cognispatium.domain.Admin;

public interface AdminManager extends Serializable {
    
    public List<Admin> getAdmins(); 

}