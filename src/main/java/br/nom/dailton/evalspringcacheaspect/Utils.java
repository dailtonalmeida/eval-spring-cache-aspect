/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.nom.dailton.evalspringcacheaspect;

/**
 *
 * @author Dailton Santana de Almeida
 */
public final class Utils {
    public static void sleepSeconds(long seconds) {
        try {
            Thread.sleep(1000L * seconds);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
