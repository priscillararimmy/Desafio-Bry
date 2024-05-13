package backend.springboot.services;
import backend.springboot.dtos.UserRecordDto;
import backend.springboot.models.UserModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    UserModel saveUser(UserRecordDto userRecordDto);
    List<UserModel> getAllUsers();
    Optional<UserModel> getOneUser(UUID id);
    UserModel updateUser(UUID id, UserRecordDto userRecordDto);
    void deleteUser(UUID id);
}
