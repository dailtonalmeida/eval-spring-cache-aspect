/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.nom.dailton.evalspringcacheaspect;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.interceptor.SimpleKey;
import org.springframework.stereotype.Service;

/**
 *
 * @author Dailton Santana de Almeida
 */
@Service
public class MyService {
    private final CacheManager cacheManager;
    private MyDependencyService myDependencyService;
    public MyService(CacheManager cacheManager, MyDependencyService myDependencyService) {
        this.cacheManager = cacheManager;
        this.myDependencyService = myDependencyService;
    }

    
    @Cacheable(cacheManager = "cacheManager", cacheNames = "firstCache", key="")
    public String myFirstWork() {
        //do something before get slow data from myDependenceService; in the example, add prefix
        return "calling mySecondService.doTheWork and returning " + myDependencyService.doTheWork();
    }

    
    //called from controller
    public String mySecondWork() {
        //do something beyond get slow data / cached data from doAnotherWork method; in the example, add prefix
        return "my non cached data 2 " + this.doAnotherWork();
    }
    @Cacheable(cacheNames = "secondCache")
    public String doAnotherWork() {
        Utils.sleepSeconds(2L);
        return "second work";
    }


    //called from controller
    public String myThirdWork() {
        Cache cache = Optional.ofNullable(cacheManager.getCache("thirdCache")).orElseThrow();
        String cachedData = cache.get("", () -> {
            Utils.sleepSeconds(2L);
            return "third work";
        });
        //do something beyond get slow data / cached data; in the example, add prefix
        return "my non cached data 3 " + cachedData;
    }
}
