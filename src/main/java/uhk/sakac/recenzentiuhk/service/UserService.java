package uhk.sakac.recenzentiuhk.service;

import org.springframework.stereotype.Service;
import uhk.sakac.recenzentiuhk.model.User;

public interface UserService {

    public void saveNewUser(User user);

    public void deleteUser(Long id);

}
