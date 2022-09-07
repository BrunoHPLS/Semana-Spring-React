package com.devsuperior.dsmeta.services;

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

    public Page<Sale> pageAll(Integer page,Integer size){
        return repository.findAll(PageRequest.of(page, size));
    }
}
