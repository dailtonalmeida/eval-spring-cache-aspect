/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.nom.dailton.evalspringcacheaspect;

import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Dailton Santana de Almeida
 */
public interface CountryRepository extends CrudRepository<Country, Integer> {
    
}
