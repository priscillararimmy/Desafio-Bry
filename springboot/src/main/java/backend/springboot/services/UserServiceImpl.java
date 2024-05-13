package backend.springboot.services;

import backend.springboot.dtos.UserRecordDto;
import backend.springboot.models.UserModel;
import backend.springboot.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserModel saveUser(UserRecordDto userRecordDto) {
        var userModel = new UserModel();
        BeanUtils.copyProperties(userRecordDto, userModel);
        return userRepository.save(userModel);
    }

    @Override
    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserModel> getOneUser(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public UserModel updateUser(UUID id, UserRecordDto userRecordDto) {
        Optional<UserModel> userO = userRepository.findById(id);
        UserModel userModel = userO.orElseThrow(() -> new RuntimeException("User not found"));
        BeanUtils.copyProperties(userRecordDto, userModel);
        return userRepository.save(userModel);
    }

    @Override
    public void deleteUser(UUID id) {
        Optional<UserModel> userO = userRepository.findById(id);
        UserModel userModel = userO.orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(userModel);
    }
}

