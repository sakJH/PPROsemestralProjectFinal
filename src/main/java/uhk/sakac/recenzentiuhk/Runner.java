package uhk.sakac.recenzentiuhk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import uhk.sakac.recenzentiuhk.dao.RoleDao;
import uhk.sakac.recenzentiuhk.dao.UserDao;
import uhk.sakac.recenzentiuhk.model.Role;
import uhk.sakac.recenzentiuhk.model.User;

import java.util.HashSet;
import java.util.Set;

@Component
public class Runner implements CommandLineRunner {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public void run(String... args) throws Exception {

        if( roleDao.findAll().isEmpty() ) {

            //Definice rolí
            // User
            Role role_user = new Role();
            role_user.setRoleName("USER");
            roleDao.save(role_user);

            // Admin
            Role role_admin = new Role();
            role_admin.setRoleName("ADMIN");
            roleDao.save(role_admin);

            // Vytváření uživatele - admin
            User admin = new User();
            admin.setUsername("admin");
            admin.setEnabled(true);
            admin.setPassword(new BCryptPasswordEncoder().encode("admin"));
            admin.setFirstName("Jan");
            admin.setLastName("Sakac");
            admin.setEmail("admin@recenzentiuhk.com");
            admin.setProfilePicPath("/images/profile/default.png");

            Role role = roleDao.findByRoleName("ADMIN");
            Set<Role> roles = new HashSet<Role>();
            roles.add(role);
            admin.setRoles(roles);
            userDao.save(admin);


            // Vytváření uživatele - user
            User user = new User();
            admin.setUsername("user");
            admin.setEnabled(true);
            admin.setPassword(new BCryptPasswordEncoder().encode("user"));
            admin.setFirstName("Martin");
            admin.setLastName("Novak");
            admin.setEmail("user@recenzentiuhk.com");
            admin.setProfilePicPath("/images/profile/default.png");

            Role role2 = roleDao.findByRoleName("USER");
            Set<Role> roles2 = new HashSet<Role>();
            roles.add(role2);
            admin.setRoles(roles2);
            userDao.save(user);
        }



    }
}

