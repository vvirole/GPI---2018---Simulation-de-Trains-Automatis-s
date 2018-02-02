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
public class TIC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Line line = new Line(8);
        Train tr = new Train(7,line,0);
        Thread trainThread = new Thread(tr);
        //trainThread.setDaemon(true);
        trainThread.start();
    }
    
}
