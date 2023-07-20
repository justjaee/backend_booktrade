package services;

import com.example.booktrade_backend.user_form.model.User;
import com.example.booktrade_backend.user_form.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Iterable<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found with id: " + id);
        }

        return optionalUser.get();
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User updates) {
        User userToUpdate = getUserById(id);

        if (!updates.getFirstName().isEmpty()) {
            userToUpdate.setFirstName(updates.getFirstName());
        }
        if (!updates.getLastName().isEmpty()) {
            userToUpdate.setLastName(updates.getLastName());
        }
        if (!updates.getEmail().isEmpty()) {
            userToUpdate.setEmail(updates.getEmail());
        }
        if (!updates.getStudentID().isEmpty()) {
            userToUpdate.setStudentID(updates.getStudentID());
        }
        if (!updates.getSchool().isEmpty()) {
            userToUpdate.setSchool(updates.getSchool());
        }
        if (!updates.getMajor().isEmpty()) {
            userToUpdate.setMajor(updates.getMajor());
        }

        return userRepository.save(userToUpdate);
    }

    public HashMap<String, Object> deleteUser(Long id) {
        HashMap<String, Object> responseMap = new HashMap<>();

        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            responseMap.put("wasDeleted", false);
            responseMap.put("userInfo", null);
            responseMap.put("message", "User not found with id: " + id);
            return responseMap;
        }

        userRepository.deleteById(id);
        responseMap.put("wasDeleted", true);
        responseMap.put("userInfo", optionalUser.get());

        return responseMap;
    }
}

