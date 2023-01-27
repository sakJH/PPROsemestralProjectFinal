package uhk.sakac.recenzentiuhk.service;

import org.springframework.stereotype.Service;
import uhk.sakac.recenzentiuhk.model.Role;

public interface RoleService {

    public void setUserNewRole();

    public void setRole();

    public void findUserByRole(Role role);
}
