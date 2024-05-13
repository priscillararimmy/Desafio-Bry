package backend.springboot.controllers;

import backend.springboot.services.UserService;
import backend.springboot.dtos.UserRecordDto;
import backend.springboot.models.UserModel;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<UserModel> saveUser(@RequestBody @Valid UserRecordDto userRecordDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(userRecordDto));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserModel>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Object> getOneUser(@PathVariable(value="id") UUID id) {
        Optional<UserModel> userO = userService.getOneUser(id);
        if(userO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(userO.get());
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable(value="id") UUID id,
                                             @RequestBody @Valid UserRecordDto userRecordDto) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(id, userRecordDto));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value="id") UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully.");
    }
}

