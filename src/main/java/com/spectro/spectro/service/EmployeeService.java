package com.spectro.spectro.service;

import com.spectro.spectro.entity.EmployeeEntity;
import com.spectro.spectro.exception.UserAlreadyExistException;
import com.spectro.spectro.exception.UserNotFoundException;
import com.spectro.spectro.model.Employee;
import com.spectro.spectro.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;
    public EmployeeEntity registerEmployee(EmployeeEntity employee) throws UserAlreadyExistException {
        if(employeeRepo.findByName(employee.getName()) != null){
            throw new UserAlreadyExistException("Такой поставщик уже зарегестрирован");
        }
        else if(employeeRepo.findByEmail(employee.getEmail())!=null){
            throw new UserAlreadyExistException("Такой email уже занят");
        }
        else if(employeeRepo.findByNumberphone(employee.getNumberphone())!=null){
            throw new UserAlreadyExistException("Такой номер уже занят");
        }
        return employeeRepo.save(employee);
    }

    public Employee getOne(Long id) throws UserNotFoundException {
        EmployeeEntity employee = employeeRepo.findById(id).get();
        if(employee == null){
            throw new UserNotFoundException("Пользователь не найден");
        }
        return Employee.toModel(employee);
    }

    public Employee login(EmployeeEntity employee) throws UserNotFoundException {
        if(employeeRepo.findByEmail(employee.getEmail()) == null && employeeRepo.findByPassword(employee.getPassword())==null){
            throw new UserNotFoundException("Неверно");
        }

        return Employee.toModel(employee);
    }

    public EmployeeEntity update(EmployeeEntity newEmployee, Long id) throws UserNotFoundException {
        EmployeeEntity employee = employeeRepo.findById(id).get();
        if(employee == null){
            throw new UserNotFoundException("Пользователь не найден");
        }
        employee.setName(newEmployee.getName());
        employee.setNumberphone(newEmployee.getNumberphone());
        employee.setPassword(newEmployee.getPassword());
        return employeeRepo.save(employee);
    }
    public Long delete(Long id){
        employeeRepo.deleteById(id);
        return id;
    }
}
