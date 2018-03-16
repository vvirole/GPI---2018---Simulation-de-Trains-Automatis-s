package core.utility;

public class Clock {
	
	private int day;
	private int hours;
	private int minute;
	private int seconde;
	private int counter;
	
	
	private static Clock instance;
	
	public Clock(int hours) {
		this.day = 0;
		this.hours = hours;
		this.minute = 0;
		this.seconde = 0;
	}
	
	public static Clock newInstance(int hours) {
		if(instance == null) {
			instance = new Clock(hours);
		}
		return instance;
	}
	
	public static Clock getInstance(){
		return instance;
	}
	
	public void incrementClock() {
		
		seconde+=30;
		counter+=30;
		
		if(seconde >= 60) {
			seconde = 0;
			minute++;
		}
		
		if(minute == 60) {
			minute = 0;
			hours++;
		}
		
		if(hours == 24) {
			hours = 0;
			day++;
		}
	}
	
	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getSeconde() {
		return seconde;
	}

	public void setSeconde(int seconde) {
		this.seconde = seconde;
	}
	
	public int getCounter() {
		return seconde;
	}
	
	public String toString() {
		return hours + ":" + minute + ":" + seconde + " Jours : " + day;
	}
	
}
