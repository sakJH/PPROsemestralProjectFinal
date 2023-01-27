package uhk.sakac.recenzentiuhk.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uhk.sakac.recenzentiuhk.dao.UserDao;
import uhk.sakac.recenzentiuhk.model.User;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if( user == null ) throw new UsernameNotFoundException("Username Not Found");
        return new UserDetails(user);
    }

}
