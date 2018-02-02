/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

/**
 *
 * @author Benoit
 */
public class Line {
    Canton[] line;
    int numStation;
    
    public Line(int numStation){
        this.numStation = numStation;
        line = new Canton[numStation];
        for (int i = 0; i < line.length; i++) {
            line[i] = new Canton(i, new Station(), 100);
        }
    }

    public Canton[] getLine() {
        return line;
    }
    
    public Canton getCantonByPosition(int position) {
        return line[position];
    }

    public int getNumStation() {
        return numStation;
    }


    public void setLine(Canton[] line) {
        this.line = line;
    }
    
    
    
}
