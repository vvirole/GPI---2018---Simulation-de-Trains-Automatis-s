/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import controler.Variables.*;

/**
 *
 * @author Benoit
 */
public class Canton {

    private int id;
    private boolean occupyByTrain = false;
    private Station station;
    private int longueur;

    public Canton(int id, Station station, int longueur) {
        this.id = id;
        this.station = station;
        this.longueur = longueur;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOccupyByTrain(boolean occupyByTrain) {
        this.occupyByTrain = occupyByTrain;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public void setLongueur(int longueur) {
        this.longueur = longueur;
    }

    public int getId() {
        return id;
    }

    public boolean isOccupyByTrain() {
        return occupyByTrain;
    }

    public Station getStation() {
        return station;
    }

    public int getLongueur() {
        return longueur;
    }

    

    
    
    
    
}
