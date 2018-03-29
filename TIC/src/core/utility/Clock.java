package core.utility;

import core.LineController;
import gui.GUIConstants;

public class Clock extends Thread {
	
	private static Clock instance;
	private static LineController controller;
	
	private int day;
	private int seconde;
	private int minute;
	private int hour;
	private int counter;
	
	// Run or stop the counter
	private boolean running = false;
	private boolean stop = false;
	
	public Clock(){
		hour = GUIConstants.START_HOUR;
	}
	
	public static Clock newInstance(LineController controller){
		Clock.instance = new Clock();
		Clock.controller = controller;
		return instance;
	}
	
	public static Clock getInstance(){
		return instance;
	}
	
	@Override
	public void run(){
		while (!stop){
			try {
				Thread.sleep(GUIConstants.TIME_SPEED);
				if (running){
					increment();
					if (counter % 300 == 0) controller.update();
					if (counter % 600 == 0) controller.updateSatisfaction();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Stop definetely the clock
	 */
	public void close(){
		stop = true;
		running = false;
	}

	/**
	 * Increment the clock by one second
	 */
	public void increment(){	
		seconde++;
		counter++;
		
		if (seconde == 60){
			seconde = 0;
			minute++;
		}
		
		if (minute == 60){
			minute = 0;
			hour++;
		}
		
		if (hour == 24){
			hour = 0;
			day++;
		}
	}
	
	/**
	 * @return the number of hours elapsed
	 */
	public int getElapsedHour(){
		return counter/3600;
	}
	
	/**
	 * @return the number of minutes elapsed
	 */
	public int getElapsedMinute(){
		return counter/60;
	}
	
	/**
	 * @return the number of secondes elapsed
	 */
	public int getElapsedSeconde(){
		return counter;
	}
	
	public float getFormattedTime() {
		return Float.parseFloat(hour + "." + minute);
	}

	public int getDay() {
		return day;
	}

	public int getSeconde() {
		return seconde;
	}

	public int getMinute() {
		return minute;
	}

	public int getHour() {
		return hour;
	}

	public int getCounter() {
		return counter;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public void setSeconde(int seconde) {
		this.seconde = seconde;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}
	
	public boolean isRunning(){
		return running;
	}
	
	public void setRunning(boolean running){
		this.running = running;
	}
	
	@Override
	public String toString(){
		String hourS = (hour < 10) ? "0" + hour : String.valueOf(hour);
		String minuteS = (minute < 10) ? "0" + minute : String.valueOf(minute);
		String secondeS = (seconde < 10) ? "0" + seconde : String.valueOf(seconde);
		return "Jour " + day + ", " + hourS + ":" + minuteS + ":" + secondeS;
	}
}
