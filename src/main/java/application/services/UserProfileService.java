package application.services;

import application.entities.user.User;
import application.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    @Value("${upload.path}")
    private String uploadPath;
    private final UserRepository iUserRepository;
    private final FileService fileService;

    public void adaptAndSaveEditedUserProfile(MultipartFile avatar, String firstName,
                                              String lastName, String username, User user) {
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        addUserAvatar(user, avatar);

        iUserRepository.save(user);
    }

    private void addUserAvatar(User user, MultipartFile avatar) {
        user.setAvatar(fileService.uploadFile(avatar));
    }

}
