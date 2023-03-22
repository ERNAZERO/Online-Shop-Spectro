package com.spectro.spectro.repository;

import com.spectro.spectro.entity.EmployeeEntity;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepo extends CrudRepository<EmployeeEntity, Long> {
    EmployeeEntity findByName(String name);
    EmployeeEntity findByEmail(String email);
    EmployeeEntity findByNumberphone(String number);
    EmployeeEntity findByPassword(String password);
}
