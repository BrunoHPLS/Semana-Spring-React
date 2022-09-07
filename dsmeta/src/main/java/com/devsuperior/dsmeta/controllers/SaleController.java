package com.devsuperior.dsmeta.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.services.SaleService;

@RestController
@RequestMapping("/sales")
public class SaleController {
    
    @Autowired
    private SaleService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Sale>> findAll(
        @RequestParam(defaultValue = "") String minDate,
        @RequestParam(defaultValue = "") String maxDate,
        @RequestParam(defaultValue = "0") Integer page,
        @RequestParam(defaultValue = "5") Integer size
    ){
        return ResponseEntity.ok(
            service.pageAll(
                minDate.isBlank() ? LocalDate.now().minusYears(1):parseDate(minDate),
                maxDate.isBlank() ? LocalDate.now():parseDate(maxDate),
                page,
                size));
    }

    private LocalDate parseDate(String date){
        try{
            return LocalDate.parse(date);
        }catch(DateTimeParseException ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi possível realizar o parsing da data");
        }
    }
}
