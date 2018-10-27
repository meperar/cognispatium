package upv.etsinf.cognispatium.repository;

import java.util.List;

import upv.etsinf.cognispatium.domain.Admin;

public interface AdminDao {

    public List<Admin> getAdminList();

    public void saveAdmin(Admin admin);
    
    //GetAdminby??

}