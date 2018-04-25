package observe;

import java.util.Observable;

public class WeatherData extends Observable{
	
    private float temperature;                           // 온도
	private float humidity;                               // 습도
    private float pressure;                             // 기압
	
	public WeatherData(){
		
	}
	
	public void setMeasurements(float temperature, float humidity, float pressure){
        this.temperature = temperature;   
        this.humidity = humidity; 
        this.pressure = pressure;
        measurementsChanged();
	}
	public void measurementsChanged(){
		setChanged();
		notifyObservers();
	}
	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	public float getHumidity() {
		return humidity;
	}

	public void setHumidity(float humidity) {
		this.humidity = humidity;
	}

	public float getPressure() {
		return pressure;
	}

	public void setPressure(float pressure) {
		this.pressure = pressure;
	}
}
