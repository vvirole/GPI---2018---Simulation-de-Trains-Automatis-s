/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import controler.*;

/**
 *
 * @author Benoit
 */
public class TestVue {

    public static void afficherLine(int currentPostion, int max) {
        
        for (int i = 0; i < max; i++) {
            if(i == currentPostion){
                System.out.print("T");
            }else{
                System.out.print("-");
            }
            
        }
        System.out.println("");
    }

}
