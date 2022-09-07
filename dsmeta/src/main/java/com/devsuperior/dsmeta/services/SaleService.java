package com.devsuperior.dsmeta.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {
   
    @Autowired
    private SaleRepository repository;

    public Page<Sale> pageAll(LocalDate minDate,LocalDate maxDate,Integer page,Integer size){
        System.out.println(minDate + " " + maxDate);
        return repository.findSales(minDate,maxDate,PageRequest.of(page, size));
    }
}
