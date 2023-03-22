package com.spectro.spectro.model;

import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class LaptopPage {
    private int pageNumber;
    private int pageSize;
    private Sort.Direction sortDirection = Sort.Direction.ASC;
    private String sortBy = "model";
}
