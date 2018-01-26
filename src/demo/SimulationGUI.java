package demo;

import java.util.Random;

import javax.swing.JFrame;

/**
 * @author tliu@u-cergy.fr
 */
public class SimulationGUI extends JFrame implements Runnable {
	private static final int TRAIN_SPEED_VARIATION = 3;
	private static final int TRAIN_BASIC_SPEED = Rand(5,7);
	private SimulationDashboard dashboard = new SimulationDashboard();
	private int currentTime = 0;
	private static final int SIMULATION_DURATION = 1000;
	public static final int TIME_UNIT = 50;

	public SimulationGUI() {
		super("Train simulation");
		getContentPane().add(dashboard);
		setSize(1300, 300);
		setVisible(true);
	}

	public static void main(String[] args) {
		SimulationGUI simulationGUI = new SimulationGUI();
		Thread simulationThread = new Thread(simulationGUI);
		simulationThread.start();
	}

	@Override
	public void run() {
		int trainBasicSpeed = TRAIN_BASIC_SPEED;
		while (currentTime <= SIMULATION_DURATION) {
			//System.out.println("Current time : " + currentTime);
			if (currentTime % 12 == 0) {
				Line line = dashboard.getLine();
				Canton firstCanton = line.getCantons().get(0);
				if (firstCanton.isFree()) {
					Train newTrain = new Train(line, firstCanton, trainBasicSpeed);
					dashboard.addTrain(newTrain);
					newTrain.start();
					System.out.println("New Train created " + newTrain.toString());
					//trainBasicSpeed += TRAIN_SPEED_VARIATION;
					trainBasicSpeed = Rand(5,7);
					System.out.println(trainBasicSpeed);
				}

			}
			dashboard.repaint();
			try {
				Thread.sleep(TIME_UNIT);
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
			currentTime++;
		}
	}
	
	public static int Rand(int i, int j) {
        Random rnd = new Random();
        int nombre = i + rnd.nextInt(j - i + 1);
        return nombre;
    }
}
