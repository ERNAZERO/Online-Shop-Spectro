package com.spectro.spectro.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PhoneSearchCriteria {
    private String model;
    private String proizvoditel;
    private String stranaProizvoditel;
    private String tipPamyati;
    private String vstroennayaPamyat;
    private String operativnayaPamyat;
    private String tipEkrana;
    private String chastotaObnovleniya;
    private String razmerEkrana;
    private String razreshenieEkrana;
    private String dopolnitelnyiModulKamer;
    private String osnovnoiModulKamer;
    private String shirokougolnyiModulKamer;
    private String frontalnayaKamera;
    private String dinamic;
    private String vyhodNaushnikov;
    private String razemy;
    private String kolichestvoSIMKart;
    private String tipSIMKart;
    private Boolean nfc;
    private String zashitaOtVlagi;
    private String tipProtsessora;
    private BigDecimal maxPrice;
    private BigDecimal minPrice;
}