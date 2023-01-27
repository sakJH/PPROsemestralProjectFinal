package uhk.sakac.recenzentiuhk.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import uhk.sakac.recenzentiuhk.dao.RoleDao;
import uhk.sakac.recenzentiuhk.model.Role;
import uhk.sakac.recenzentiuhk.service.RoleService;

@Service
public class RoleServiceImplementation implements RoleService {

    //TODO
    @Autowired
    private RoleDao roleDao;


    @Override
    public void setUserNewRole() {

    }

    @Override
    public void setRole() {

    }

    @Override
    public void findUserByRole(Role role) {

    }



}
