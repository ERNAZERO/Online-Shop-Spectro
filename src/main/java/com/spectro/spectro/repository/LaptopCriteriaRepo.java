package com.spectro.spectro.repository;

import com.spectro.spectro.entity.LaptopEntity;
import com.spectro.spectro.enums.LaptopEnum;
import com.spectro.spectro.enums.PhoneEnum;
import com.spectro.spectro.model.LaptopPage;
import com.spectro.spectro.model.LaptopSearch;
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
public class LaptopCriteriaRepo {
    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public LaptopCriteriaRepo(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<LaptopEntity> findAllWithFilters(LaptopPage laptopPage, LaptopSearch searchCriteria) {
        CriteriaQuery<LaptopEntity> criteriaQuery = criteriaBuilder.createQuery(LaptopEntity.class);
        Root<LaptopEntity> root = criteriaQuery.from(LaptopEntity.class);
        Predicate predicate = getPredicate(searchCriteria, root);
        criteriaQuery.where(predicate);
        setOrder(laptopPage, criteriaQuery, root);
        TypedQuery<LaptopEntity> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(laptopPage.getPageNumber() * laptopPage.getPageSize());
        typedQuery.setMaxResults(laptopPage.getPageSize());

        Pageable pageable = getPageable(laptopPage);

        long laptopCount = getLaptopCount(predicate);
        return new PageImpl<>(typedQuery.getResultList(), pageable, laptopCount);
    }

    private long getLaptopCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(long.class);
        Root<LaptopEntity> root = countQuery.from(LaptopEntity.class);
        countQuery.select(criteriaBuilder.count(root)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }

    private Pageable getPageable(LaptopPage laptopPage) {
        Sort sort = Sort.by(laptopPage.getSortDirection(), laptopPage.getSortBy());
        return PageRequest.of(laptopPage.getPageNumber(), laptopPage.getPageSize(), sort);
    }

    private void setOrder(LaptopPage laptopPage, CriteriaQuery<LaptopEntity> criteriaQuery, Root<LaptopEntity> root) {
        if (laptopPage.getSortDirection().equals(Sort.Direction.ASC)) {
            criteriaQuery.orderBy(criteriaBuilder.asc(root.get(laptopPage.getSortBy())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.desc(root.get(laptopPage.getSortBy())));
        }
    }

    private Predicate getPredicate(LaptopSearch searchCriteria, Root<LaptopEntity> root) {
        List<Predicate> predicates = new ArrayList<>();
        if (Objects.nonNull(searchCriteria.getCamera())) {
            predicates.add(criteriaBuilder.equal(root.get("camera"), "%" + searchCriteria.getCamera() + "%"));
        }
        if (Objects.nonNull(searchCriteria.getConnectors())) {
            predicates.add(criteriaBuilder.equal(root.get("connectors"), "%" + searchCriteria.getConnectors() + "%"));
        }
        if (Objects.nonNull(searchCriteria.getDimensions())) {
            predicates.add(criteriaBuilder.equal(root.get("dimensions"), "%" + searchCriteria.getDimensions() + "%"));
        }
        if (Objects.nonNull(searchCriteria.getHeadphoneJack())) {
            predicates.add(criteriaBuilder.equal(root.get("headphoneJack"), "%" + searchCriteria.getHeadphoneJack() + "%"));
        }
        if (Objects.nonNull(searchCriteria.getHousingMaterial())) {
            predicates.add(criteriaBuilder.equal(root.get("housingMaterial"), "%" + searchCriteria.getHousingMaterial() + "%"));
        }
        if (Objects.nonNull(searchCriteria.getManufacturer())) {
            predicates.add(criteriaBuilder.equal(root.get("manufacturer"), "%" + searchCriteria.getManufacturer() + "%"));
        }
        if (Objects.nonNull(searchCriteria.getMemory())) {
            predicates.add(criteriaBuilder.equal(root.get("memory"), "%" + searchCriteria.getMemory() + "%"));
        }
        if (Objects.nonNull(searchCriteria.getNumberOfCores())) {
            predicates.add(criteriaBuilder.equal(root.get("numberOfCores"), "%" + searchCriteria.getNumberOfCores() + "%"));
        }
        if (Objects.nonNull(searchCriteria.getOS())) {
            predicates.add(criteriaBuilder.equal(root.get("OS"), "%" + searchCriteria.getOS() + "%"));
        }
        if (Objects.nonNull(searchCriteria.getProcessorType())) {
            predicates.add(criteriaBuilder.equal(root.get("processorType"), "%" + searchCriteria.getProcessorType() + "%"));
        }
        if (Objects.nonNull(searchCriteria.getProducingCountry())) {
            predicates.add(criteriaBuilder.equal(root.get("producingCountry"), "%" + searchCriteria.getProducingCountry() + "%"));
        }
        if (Objects.nonNull(searchCriteria.getRAM())) {
            predicates.add(criteriaBuilder.equal(root.get("RAM"), "%" + searchCriteria.getRAM() + "%"));
        }
        if (Objects.nonNull(searchCriteria.getScreenSize())) {
            predicates.add(criteriaBuilder.equal(root.get("screenSize"), "%" + searchCriteria.getScreenSize() + "%"));
        }
        if (Objects.nonNull(searchCriteria.getScreenResolution())) {
            predicates.add(criteriaBuilder.equal(root.get("screenResolution"), "%" + searchCriteria.getScreenResolution() + "%"));
        }
        if (Objects.nonNull(searchCriteria.getSpeaker())) {
            predicates.add(criteriaBuilder.equal(root.get("speaker"), "%" + searchCriteria.getSpeaker() + "%"));
        }
        if (Objects.nonNull(searchCriteria.getVideoCard())) {
            predicates.add(criteriaBuilder.equal(root.get("videoCard"), "%" + searchCriteria.getVideoCard() + "%"));
        }
        if (Objects.nonNull(searchCriteria.getMinBigDecimal())) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), searchCriteria.getMinBigDecimal()));
        }
        if (Objects.nonNull(searchCriteria.getMaxBigDecimal())) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"),  searchCriteria.getMaxBigDecimal()));
        }
        if (Objects.nonNull(searchCriteria.getModel())) {
            predicates.add(criteriaBuilder.like(root.get("model"),  "%"+searchCriteria.getModel()+"%"));
        }
        predicates.add(criteriaBuilder.equal(root.get("status"), LaptopEnum.available));
        return criteriaBuilder.and(predicates.toArray(predicates.toArray(new Predicate[0])));
    }
}
