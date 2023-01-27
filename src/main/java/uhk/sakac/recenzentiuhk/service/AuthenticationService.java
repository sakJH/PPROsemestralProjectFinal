package uhk.sakac.recenzentiuhk.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uhk.sakac.recenzentiuhk.exception.ResourceAlreadyExists;
import uhk.sakac.recenzentiuhk.exception.ResourceNotFoundException;
import uhk.sakac.recenzentiuhk.model.User;
import uhk.sakac.recenzentiuhk.security.UserDetails;

import java.util.List;

public interface AuthenticationService {

    void signupSubmit(User user) throws ResourceAlreadyExists;

    List<User> allUser() throws ResourceNotFoundException;

    void deleteUser(Long userId) throws ResourceNotFoundException;

    User profile(UserDetails principal);

    User setEditUserPage(Long userId) throws ResourceNotFoundException;

    void editUserSubmit(Long userId, User userDetails);

    void saveUpdatedProfilePicture(Long userId, MultipartFile file) throws ResourceNotFoundException;

}
