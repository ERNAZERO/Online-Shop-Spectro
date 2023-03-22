package com.spectro.spectro.controller;
import com.spectro.spectro.entity.PhoneEntity;
import com.spectro.spectro.enums.PhoneEnum;
import com.spectro.spectro.exception.UserNotFoundException;
import com.spectro.spectro.model.PhonePage;
import com.spectro.spectro.model.PhoneSearchCriteria;
import com.spectro.spectro.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/phone")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class PhoneController {
    @Autowired
    private PhoneService phoneService;

    @GetMapping(value = "/")
    public String mainPage() {
        return "phoneMainPage";
    }

    @GetMapping
    public String phoneMainPage(){
        return "phoneMainPage";
    }

    @GetMapping(value = "newphone")
    public String newPhone(){
        return "newPhone";
    }

    @PostMapping(value = "/saveNewPhone")
    public ResponseEntity saveNewPhone(@RequestBody PhoneEntity phone){
        try {
            phone.setStatus(PhoneEnum.available);
            phoneService.save(phone);
            return ResponseEntity.ok("Сохранил");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Ошибка сохранения нового телефона");
        }
    }

    @PatchMapping(value = "/updatePhone")
    public String updatePhone(@RequestParam("model") String model, @RequestParam("amount") Integer amount) throws Exception {
        try {
            phoneService.update(model, amount);
            return "succsess";
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    @PatchMapping(value = "/updatePhoneByEntity")
    public String updatePhone(@RequestBody PhoneEntity phone) throws Exception {
        try {
            phoneService.update(phone);
            return "succsess";
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    @DeleteMapping(value = "/deletePhone-by-id")
    public String deletePhone(@RequestParam("id") Long id) {
        try {
            phoneService.delete(id);
            return "Success";
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "/filter")
    public ResponseEntity<Page<PhoneEntity>> getPhones(PhonePage phonePage, PhoneSearchCriteria phoneSearchCriteria){
        return new ResponseEntity<>(phoneService.filter(phonePage,phoneSearchCriteria), HttpStatus.OK);
    }

    @GetMapping(value = "/searchModel")
    public ResponseEntity<PhoneEntity> getOnePhone(@RequestParam(name = "model") String model){
        return new ResponseEntity<>(phoneService.findByModel(model), HttpStatus.OK);
    }

    @PatchMapping(value = "/sell")
    public String sell(@RequestParam("model") String model, @RequestParam("amount") int amount) throws Exception {
        try {
            PhoneEntity l = phoneService.findByModel(model);
            int newAmount = l.getKolichestvo() - amount;
            if (newAmount==0){
                phoneService.update(model,newAmount, PhoneEnum.sold_out);
            } else if (newAmount>0) {
                phoneService.update(model,newAmount);
            }
            return "succsess";
        } catch (Exception e){
            throw new Exception(e);
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
