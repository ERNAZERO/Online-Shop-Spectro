package com.spectro.spectro.service;
import com.spectro.spectro.entity.PhoneEntity;
import com.spectro.spectro.enums.PhoneEnum;
import com.spectro.spectro.exception.UserNotFoundException;
import com.spectro.spectro.model.PhonePage;
import com.spectro.spectro.model.PhoneSearchCriteria;
import com.spectro.spectro.repository.PhoneCriteriaRepo;
import com.spectro.spectro.repository.PhoneRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class PhoneService {
    @Autowired
    private PhoneRepo phoneRepo;
    @Autowired
    private PhoneCriteriaRepo phoneCriteriaRepo;
    public void save(PhoneEntity phone){
        if(phoneRepo.findByModel(phone.getModel())==null){
            if(phone.getKolichestvo()>0){
                phone.setStatus(PhoneEnum.available);
            }else phone.setStatus(PhoneEnum.deleted);
        }
        phoneRepo.save(phone);
    }

    public void update(PhoneEntity phone) throws UserNotFoundException {
        PhoneEntity phone1 = phoneRepo.findByModel(phone.getModel());
        if(phoneRepo.findByModel(phone.getModel())!=null){
            if (phone.getKolichestvo()>=0)phone1.setKolichestvo(phone.getKolichestvo());
            if(phone.getChastotaObnovleniya()!=null)phone1.setChastotaObnovleniya(phone.getChastotaObnovleniya());
            if(phone.getDinamic()!=null)phone1.setDinamic(phone.getDinamic());
            if(phone.getDopolnitelnyiModulKamer()!=null)phone1.setDopolnitelnyiModulKamer(phone.getDopolnitelnyiModulKamer());
            if(phone.getFrontalnayaKamera()!=null)phone1.setFrontalnayaKamera(phone.getFrontalnayaKamera());
            if(phone.getKolichestvoSIMKart()!=null)phone1.setKolichestvoSIMKart(phone.getKolichestvoSIMKart());
            if(phone.getNfc()!=null)phone1.setNfc(phone.getNfc());
            if(phone.getOperativnayaPamyat()!=null)phone1.setOperativnayaPamyat(phone.getOperativnayaPamyat());
            if(phone.getOsnovnoiModulKamer()!=null)phone1.setOsnovnoiModulKamer(phone.getOsnovnoiModulKamer());
            if(phone.getModel()!=null)phone1.setModel(phone.getModel());
            if(phone.getProizvoditel()!=null)phone1.setProizvoditel(phone.getProizvoditel());
            if(phone.getRazemy()!=null)phone1.setRazemy(phone.getRazemy());
            if(phone.getPrise()!=null)phone1.setPrise(phone.getPrise());
            if(phone.getRazmerEkrana()!=null)phone1.setRazmerEkrana(phone.getRazmerEkrana());
            if(phone.getRazreshenieEkrana()!=null)phone1.setRazreshenieEkrana(phone.getRazreshenieEkrana());
            if(phone.getShirokougolnyiModulKamer()!=null)phone1.setShirokougolnyiModulKamer(phone.getShirokougolnyiModulKamer());
            if(phone.getStranaProizvoditel()!=null)phone1.setStranaProizvoditel(phone.getStranaProizvoditel());
            if(phone.getTipEkrana()!=null)phone1.setTipEkrana(phone.getTipEkrana());
            if(phone.getTipPamyati()!=null)phone1.setTipPamyati(phone.getTipPamyati());
            if(phone.getTipProtsessora()!=null)phone1.setTipProtsessora(phone.getTipProtsessora());
            if(phone.getTipSIMKart()!=null)phone1.setTipSIMKart(phone.getTipSIMKart());
            if(phone.getVyhodNaushnikov()!=null)phone1.setVyhodNaushnikov(phone.getVyhodNaushnikov());
            if(phone.getVstroennayaPamyat()!=null)phone1.setVstroennayaPamyat(phone.getVstroennayaPamyat());
            if(phone.getZashitaOtVlagi()!=null)phone1.setZashitaOtVlagi(phone.getZashitaOtVlagi());

            if(phone.getStatus()!=null)phone1.setStatus(phone.getStatus());
            phoneRepo.save(phone1);
        }else{
            throw new UserNotFoundException("Can not update phone list. It doesn't exist");
        }
    }

    public void update(String model, Integer amount) throws UserNotFoundException {
        PhoneEntity phone1 = phoneRepo.findByModel(model);
        if(phone1!=null){
            phone1.setKolichestvo(amount);
            phoneRepo.save(phone1);
        }else{
            throw new UserNotFoundException("Can not update phone list. It doesn't exist");
        }
    }

    public void update(String model, Integer amount, PhoneEnum status) throws UserNotFoundException {
        PhoneEntity phone1 = phoneRepo.findByModel(model);
        if(phone1!=null){
            phone1.setKolichestvo(amount);
            phone1.setStatus(status);
            phoneRepo.save(phone1);
        }else{
            throw new UserNotFoundException("Can not update phone list. It doesn't exist");
        }
    }

    public void delete(Long id) throws UserNotFoundException {
        if(phoneRepo.findById(id).get()!=null) {
            PhoneEntity phone = phoneRepo.findById(id).get();
            phone.setStatus(PhoneEnum.deleted);
            phoneRepo.save(phone);
        }else throw new UserNotFoundException("Can not update phone list. It doesn't exist");
    }

    public void delete(String model) throws UserNotFoundException {
        if(phoneRepo.findByModel(model)!=null) {
            PhoneEntity phone = phoneRepo.findByModel(model);
            phone.setStatus(PhoneEnum.deleted);
            phoneRepo.save(phone);
        }else throw new UserNotFoundException("Can not update phone list. It doesn't exist");
    }

    public Page<PhoneEntity> filter(PhonePage page, PhoneSearchCriteria phoneSearchCriteria){
        return phoneCriteriaRepo.findAllWithFilters(page,phoneSearchCriteria);
    }

    public PhoneEntity findByModel(String model){
        return phoneRepo.findByModel(model);
    }

}