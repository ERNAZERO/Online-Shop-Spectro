package com.spectro.spectro.controller;
import com.spectro.spectro.entity.UserEntity;
import com.spectro.spectro.exception.UserAlreadyExistException;
import com.spectro.spectro.exception.UserNotFoundException;
import com.spectro.spectro.repository.UserRepo;
import com.spectro.spectro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class UserController {
    @Autowired
    private UserService userService;
    private UserRepo userRepo;
    @PostMapping("/reg")
    public ResponseEntity reg(@RequestBody UserEntity user){
        try {
            userService.register(user);
            return ResponseEntity.ok("Сохранил");
        }catch (UserAlreadyExistException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка3");
        }
    }
    @GetMapping
    public ResponseEntity getOneClient(@RequestParam Long id){
        try{
            return ResponseEntity.ok(userService.getOne(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка 2");
        }catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity signIn(@RequestBody UserEntity user){
        try {
            return ResponseEntity.ok(userService.login(user));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка 2");
        }catch (UserNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PatchMapping("{id}")
    public ResponseEntity updates(@PathVariable("id") Long id, @RequestBody UserEntity user){
        try {
            userService.update(user, id);
            return ResponseEntity.ok("Изменил");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла оышв");
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteClient(@PathVariable Long id){
        try{
            return ResponseEntity.ok(userService.delete(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка 4");
        }
    }
}
