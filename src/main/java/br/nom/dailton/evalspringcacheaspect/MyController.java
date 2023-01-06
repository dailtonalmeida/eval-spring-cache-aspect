/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.nom.dailton.evalspringcacheaspect;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Dailton Santana de Almeida
 */
@RestController
public class MyController {
    private final MyService myService;
    private final CountryService countryService;
    public MyController(MyService myService, CountryService countryService) {
        this.myService = myService;
        this.countryService = countryService;
    }

    
    //
    //CACHE
    //
    
    @GetMapping({"/", "/first"})
    public String firstWork() {
        return myService.myFirstWork();
    }

    @GetMapping("/second")
    public String secondWork() {
        return myService.mySecondWork();
    }

    @GetMapping("/third")
    public String thirdWork() {
        return myService.myThirdWork();
    }

    
    //
    //DB
    //
    
    @GetMapping("/countries")
    public Iterable<Country> getAllCountries() {
        return countryService.getAllCountries();
    }
    
    @PostMapping("addtwocountries1")
    public void addTwoCountries1(@RequestParam("firstName") String firstName
            , @RequestParam("secondName") String secondName) {
        countryService.insertTwoCountries1(firstName, secondName);
    }
    @PostMapping("addtwocountries2")
    public void addTwoCountries2(@RequestParam("firstName") String firstName
            , @RequestParam("secondName") String secondName) {
        countryService.insertTwoCountries2(firstName, secondName);
    }
    @PostMapping("addtwocountries3")
    public void addTwoCountries3(@RequestParam("firstName") String firstName
            , @RequestParam("secondName") String secondName) {
        countryService.insertTwoCountries3(firstName, secondName);
    }

}
