package upv.etsinf.cognispatium.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import upv.etsinf.cognispatium.domain.Admin;
import upv.etsinf.cognispatium.repository.AdminDao;

@Component
public class SimpleAdminManager implements AdminManager {

    private static final long serialVersionUID = 1L;
    
    

    @Autowired
    private AdminDao adminDao;

    public void setAdminDao(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    public List<Admin> getAdmins() {
       return adminDao.getAdminList();        
    }

    
}