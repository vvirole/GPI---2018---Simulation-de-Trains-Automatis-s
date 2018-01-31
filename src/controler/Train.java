/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import vue.*;

/**
 *
 * @author Benoit
 */
public class Train implements Runnable{

    private int passengers;
    private int currentPostion;
    private Canton currentCanton;
    private Line line;
    private int numberCurrentCanton;

    public Train(int passengers, Line line, int numberCurrentCanton) {
        this.passengers = passengers;
        this.line = line;
        this.numberCurrentCanton = numberCurrentCanton;
    }

    public void run() {
        
        //do {
            currentPostion = 0;
            while (line.getCantonByPosition(numberCurrentCanton).isOccupyByTrain()) {}
            line.getCantonByPosition(numberCurrentCanton).setOccupyByTrain(true);
            currentCanton = line.getCantonByPosition(numberCurrentCanton);
            while(currentPostion < currentCanton.getLongueur()){
                currentPostion += 1;
                try {
                    sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Train.class.getName()).log(Level.SEVERE, null, ex);
                }
                TestVue.afficherLine(currentPostion,currentCanton.getLongueur());
            }
               
            currentPostion = currentCanton.getLongueur();
            TestVue.afficherLine(currentPostion,currentCanton.getLongueur());

        //} while (numberCurrentCanton < line.getNumStation());

    }

    /**
     * *****SETTER******
     */
    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    /**
     * *****GETTER******
     */
    public int getPassengers() {
        return passengers;
    }
}
