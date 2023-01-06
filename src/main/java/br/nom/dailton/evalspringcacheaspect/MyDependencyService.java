/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.nom.dailton.evalspringcacheaspect;

import org.springframework.stereotype.Service;

/**
 *
 * @author Dailton Santana de Almeida
 */
@Service
public class MyDependencyService {

    public String doTheWork() {
        Utils.sleepSeconds(2L);
        return "I did my work after two seconds";
    }

}
