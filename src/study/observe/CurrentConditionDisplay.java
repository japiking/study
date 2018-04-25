package observe;

import java.util.Observable;
import java.util.Observer;

public class CurrentConditionDisplay implements Observer, DisplayElement{
	
	Observable observable;                                // 등록될 Observable

    private float temperature;                        // 온도
    private float humidity;                            // 습도
    
    public CurrentConditionDisplay(Observable observable) {
		// TODO Auto-generated constructor stub
    	this.observable = observable;
    	observable.addObserver(this);
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		 System.out.println("현재 온도 : " + temperature + "도,  현재 습도 : " + humidity + "%");
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if(o instanceof WeatherData){
			WeatherData weatherData  = (WeatherData)o;
			this.temperature = weatherData.getTemperature();
			this.humidity = weatherData.getHumidity();
			display();
		}
		
	}

}
