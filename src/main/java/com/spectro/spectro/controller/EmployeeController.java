package com.spectro.spectro.controller;
import com.spectro.spectro.entity.EmployeeEntity;
import com.spectro.spectro.exception.UserAlreadyExistException;
import com.spectro.spectro.exception.UserNotFoundException;
import com.spectro.spectro.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @PostMapping
    public ResponseEntity reg(@RequestBody EmployeeEntity employee){
        try {
            employeeService.registerEmployee(employee);
            return ResponseEntity.ok("Сохранил");
        }catch (UserAlreadyExistException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
    @PostMapping("/login")
    public ResponseEntity signIn(@RequestBody EmployeeEntity employee){
        try {
            return ResponseEntity.ok(employeeService.login(employee));
        }catch (UserNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошв");
        }
    }
    @GetMapping
    public ResponseEntity getOneClient(@RequestParam Long id){
        try{
            return ResponseEntity.ok(employeeService.getOne(id));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("{id}")
    public ResponseEntity updates(@PathVariable("id") Long id, @RequestBody EmployeeEntity employee){
        try {
            employeeService.update(employee, id);
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
            return ResponseEntity.ok(employeeService.delete(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
