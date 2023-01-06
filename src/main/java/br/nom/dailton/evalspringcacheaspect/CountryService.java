/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.nom.dailton.evalspringcacheaspect;

import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

/**
 *
 * @author Dailton Santana de Almeida
 */
@Service
public class CountryService {
    private final TransactionTemplate transactionTemplate;
    private final CountryRepository countryRepository;
    public CountryService(PlatformTransactionManager transactionManager, CountryRepository countryRepository) {
        this.transactionTemplate = new TransactionTemplate(transactionManager);
        this.countryRepository = countryRepository;
    }
    
    public Iterable<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    
    //called from controller
    @Transactional
    public void insertTwoCountries1(String firstName, String secondName) {
        saveCountriesWithoutAnnotation(Country.build(firstName)
                , Country.build(secondName));
    }
    protected Boolean saveCountriesWithoutAnnotation(Country c0, Country c1) {
        countryRepository.save(c0);
        countryRepository.save(c1);
        throw new IllegalStateException("FORCED EXCEPTION");
        //return Boolean.TRUE
    }

    
    //called from controller
    public void insertTwoCountries2(String firstName, String secondName) {
        saveCountriesWithAnnotation(Country.build(firstName)
                , Country.build(secondName));
    }
    @Transactional
    protected Boolean saveCountriesWithAnnotation(Country c0, Country c1) {
        countryRepository.save(c0);
        countryRepository.save(c1);
        throw new IllegalStateException("FORCED EXCEPTION");
        //return Boolean.TRUE
    }

    
    //called from controller
    public void insertTwoCountries3(String firstName, String secondName) {
        saveCountriesWithTransactionTamplate(Country.build(firstName)
                , Country.build(secondName));
    }
    protected Boolean saveCountriesWithTransactionTamplate(Country c0, Country c1) {
        transactionTemplate.execute((TransactionStatus status) -> {
            countryRepository.save(c0);
            countryRepository.save(c1);
            throw new IllegalStateException("FORCED EXCEPTION");
        });
        return Boolean.TRUE;
    }

}
