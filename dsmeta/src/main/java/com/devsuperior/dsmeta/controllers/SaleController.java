package com.devsuperior.dsmeta.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.services.SaleService;
import com.devsuperior.dsmeta.services.SmsService;

@RestController
@RequestMapping("/sales")
public class SaleController {
    
    @Autowired
    private SaleService service;

    @Autowired
    private SmsService smsService;

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

    @GetMapping("/{id}/notification")
    public ResponseEntity<Void> notifySms(
        @PathVariable Long id
    ){
        smsService.sendSms(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private LocalDate parseDate(String date){
        try{
            return LocalDate.parse(date);
        }catch(DateTimeParseException ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi possível realizar o parsing da data");
        }
    }
}
