package com.spectro.spectro.repository;

import com.spectro.spectro.entity.PhoneEntity;
import com.spectro.spectro.enums.LaptopEnum;
import com.spectro.spectro.enums.PhoneEnum;
import com.spectro.spectro.model.PhonePage;
import com.spectro.spectro.model.PhoneSearchCriteria;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class PhoneCriteriaRepo {
    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public PhoneCriteriaRepo(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<PhoneEntity> findAllWithFilters(PhonePage phonePage, PhoneSearchCriteria searchCriteria) {
        CriteriaQuery<PhoneEntity> criteriaQuery = criteriaBuilder.createQuery(PhoneEntity.class);
        Root<PhoneEntity> root = criteriaQuery.from(PhoneEntity.class);
        Predicate predicate = getPredicate(searchCriteria, root);

        criteriaQuery.where(predicate);
        setOrder(phonePage, criteriaQuery, root);
        TypedQuery<PhoneEntity> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(phonePage.getPageNumber() * phonePage.getPageSize());
        typedQuery.setMaxResults(phonePage.getPageSize());

        Pageable pageable = getPageable(phonePage);

        long phoneCount = getPhoneCount(predicate);
        return new PageImpl<>(typedQuery.getResultList(), pageable, phoneCount);
    }

    private long getPhoneCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(long.class);
        Root<PhoneEntity> root = countQuery.from(PhoneEntity.class);
        countQuery.select(criteriaBuilder.count(root)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }

    private Pageable getPageable(PhonePage phonePage) {
        Sort sort = Sort.by(phonePage.getSortDirection(), phonePage.getSortBy());
        return PageRequest.of(phonePage.getPageNumber(), phonePage.getPageSize(), sort);
    }

    private void setOrder(PhonePage phonePage, CriteriaQuery<PhoneEntity> criteriaQuery, Root<PhoneEntity> root) {
        if (phonePage.getSortDirection().equals(Sort.Direction.ASC)) {
            criteriaQuery.orderBy(criteriaBuilder.asc(root.get(phonePage.getSortBy())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.desc(root.get(phonePage.getSortBy())));
        }
    }

    private Predicate getPredicate(PhoneSearchCriteria searchCriteria, Root<PhoneEntity> root) {
        List<Predicate> predicates = new ArrayList<>();
        if (Objects.nonNull(searchCriteria.getChastotaObnovleniya())) {
            predicates.add(criteriaBuilder.equal(root.get("chastotaObnovleniya"), "%" + searchCriteria.getChastotaObnovleniya() + "%"));
        }
        if (Objects.nonNull(searchCriteria.getDinamic())) {
            predicates.add(criteriaBuilder.equal(root.get("dinamic"), "%" + searchCriteria.getDinamic() + "%"));
        }
        if (Objects.nonNull(searchCriteria.getDopolnitelnyiModulKamer())) {
            predicates.add(criteriaBuilder.equal(root.get("dopolnitelnyiModulKamer"), "%" + searchCriteria.getDopolnitelnyiModulKamer() + "%"));
        }
        if (Objects.nonNull(searchCriteria.getFrontalnayaKamera())) {
            predicates.add(criteriaBuilder.equal(root.get("frontalnayaKamera"), "%" + searchCriteria.getFrontalnayaKamera() + "%"));
        }
        if (Objects.nonNull(searchCriteria.getKolichestvoSIMKart())) {
            predicates.add(criteriaBuilder.equal(root.get("kolichestvoSIMKart"), "%" + searchCriteria.getKolichestvoSIMKart() + "%"));
        }
        if (Objects.nonNull(searchCriteria.getNfc())) {
            predicates.add(criteriaBuilder.equal(root.get("nfc"), "%" + searchCriteria.getNfc() + "%"));
        }
        if (Objects.nonNull(searchCriteria.getOperativnayaPamyat())) {
            predicates.add(criteriaBuilder.equal(root.get("operativnayaPamyat"), "%" + searchCriteria.getOperativnayaPamyat() + "%"));
        }
        if (Objects.nonNull(searchCriteria.getOsnovnoiModulKamer())) {
            predicates.add(criteriaBuilder.equal(root.get("osnovnoiModulKamer"), "%" + searchCriteria.getOsnovnoiModulKamer() + "%"));
        }
        if (Objects.nonNull(searchCriteria.getProizvoditel())) {
            predicates.add(criteriaBuilder.equal(root.get("proizvoditel"), "%" + searchCriteria.getProizvoditel() + "%"));
        }
        if (Objects.nonNull(searchCriteria.getRazemy())) {
            predicates.add(criteriaBuilder.equal(root.get("razemy"), "%" + searchCriteria.getRazemy() + "%"));
        }
        if (Objects.nonNull(searchCriteria.getRazmerEkrana())) {
            predicates.add(criteriaBuilder.equal(root.get("razmerEkrana"), "%" + searchCriteria.getRazmerEkrana() + "%"));
        }
        if (Objects.nonNull(searchCriteria.getRazreshenieEkrana())) {
            predicates.add(criteriaBuilder.equal(root.get("razreshenieEkrana"), "%" + searchCriteria.getRazreshenieEkrana() + "%"));
        }
        if (Objects.nonNull(searchCriteria.getShirokougolnyiModulKamer())) {
            predicates.add(criteriaBuilder.equal(root.get("shirokougolnyiModulKamer"), "%" + searchCriteria.getShirokougolnyiModulKamer() + "%"));
        }
        if (Objects.nonNull(searchCriteria.getStranaProizvoditel())) {
            predicates.add(criteriaBuilder.equal(root.get("stranaProizvoditel"), "%" + searchCriteria.getStranaProizvoditel() + "%"));
        }
        if (Objects.nonNull(searchCriteria.getTipEkrana())) {
            predicates.add(criteriaBuilder.equal(root.get("tipEkrana"), "%" + searchCriteria.getTipEkrana() + "%"));
        }
        if (Objects.nonNull(searchCriteria.getTipPamyati())) {
            predicates.add(criteriaBuilder.equal(root.get("tipPamyati"), "%" + searchCriteria.getTipPamyati() + "%"));
        }
        if (Objects.nonNull(searchCriteria.getTipProtsessora())) {
            predicates.add(criteriaBuilder.equal(root.get("tipProtsessora"), "%" + searchCriteria.getTipProtsessora() + "%"));
        }
        if (Objects.nonNull(searchCriteria.getTipSIMKart())) {
            predicates.add(criteriaBuilder.equal(root.get("tipSIMKart"), "%" + searchCriteria.getTipSIMKart() + "%"));
        }
        if (Objects.nonNull(searchCriteria.getVstroennayaPamyat())) {
            predicates.add(criteriaBuilder.equal(root.get("vstroennayaPamyat"), "%" + searchCriteria.getVstroennayaPamyat() + "%"));
        }
        if (Objects.nonNull(searchCriteria.getVyhodNaushnikov())) {
            predicates.add(criteriaBuilder.equal(root.get("vyhodNaushnikov"), "%" + searchCriteria.getVyhodNaushnikov() + "%"));
        }
        if (Objects.nonNull(searchCriteria.getZashitaOtVlagi())) {
            predicates.add(criteriaBuilder.equal(root.get("zashitaOtVlagi"), "%" + searchCriteria.getZashitaOtVlagi() + "%"));
        }
        if (Objects.nonNull(searchCriteria.getMinPrice())) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), searchCriteria.getMinPrice()));
        }
        if (Objects.nonNull(searchCriteria.getMaxPrice())) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"),  searchCriteria.getMaxPrice()));
        }
        if (Objects.nonNull(searchCriteria.getModel())) {
            predicates.add(criteriaBuilder.like(root.get("model"),  "%"+searchCriteria.getModel()+"%"));
        }
        predicates.add(criteriaBuilder.equal(root.get("status"), PhoneEnum.available));
        return criteriaBuilder.and(predicates.toArray(predicates.toArray(new Predicate[0])));
    }
}
