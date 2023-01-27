package uhk.sakac.recenzentiuhk.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uhk.sakac.recenzentiuhk.dao.RoleDao;
import uhk.sakac.recenzentiuhk.dao.UserDao;
import uhk.sakac.recenzentiuhk.exception.ResourceAlreadyExists;
import uhk.sakac.recenzentiuhk.exception.ResourceNotFoundException;
import uhk.sakac.recenzentiuhk.model.Role;
import uhk.sakac.recenzentiuhk.model.User;
import uhk.sakac.recenzentiuhk.security.UserDetails;
import uhk.sakac.recenzentiuhk.service.AuthenticationService;
import uhk.sakac.recenzentiuhk.utility.FileUpload;
import uhk.sakac.recenzentiuhk.utility.ImageType;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class AuthServiceImplementation implements AuthenticationService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public void signupSubmit(User user) throws ResourceAlreadyExists {

        if (userDao.findByUsername(user.getUsername()) != null) throw new ResourceAlreadyExists("Username Already exists");

        // defaultní role = user
        Role role = roleDao.findByRoleName("USER");
        Set<Role> roles = new HashSet<Role>();
        roles.add(role);
        user.setRoles(roles);
        user.setProfilePicPath("/images/profile/default.png");

        user.setEnabled(true);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword())); // password encrypted
        userDao.save(user);
    }

    @Override
    public List<User> allUser() throws ResourceNotFoundException {
        List<User> users = userDao.findAllUser();
        if (users.isEmpty())
            throw new ResourceNotFoundException("No User found");
        return users;
    }

    @Override
    public void deleteUser(Long userId) throws ResourceNotFoundException {
        userDao.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userDao.deleteById(userId);
    }

    @Override
    public User profile(UserDetails principal) {
        User user = userDao.findById(principal.getId()).orElse(new User());
        return user;
    }

    @Override
    public User setEditUserPage(Long userId) throws ResourceNotFoundException {
        User user = userDao.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return user;
    }

    @Override
    public void editUserSubmit(Long userId, User userDetails) {

        User user = userDao.findById(userId).orElse(new User());
        user.setUsername(userDetails.getUsername());
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setEmail(userDetails.getEmail());
        user.setRoles(userDetails.getRoles());
        userDao.save(user);
    }

    @Override
    public void saveUpdatedProfilePicture(Long userId, MultipartFile file) throws ResourceNotFoundException {
        User user = userDao.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        String path = FileUpload.saveImage(ImageType.USER_PROFILE, user.getUsername(), file);
        user.setProfilePicPath(path);
        userDao.save(user);
    }
}
