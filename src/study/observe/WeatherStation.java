package observe;

public class WeatherStation {

	
	static WeatherData  weatherData;
	static CurrentConditionDisplay currentDisplay;
	
	public static void weatherStation(){
		weatherData = new WeatherData();
		currentDisplay = new CurrentConditionDisplay(weatherData);
	}
	
	public static void changeWeather(float temp, float humity, float pressure) {  
        weatherData.setMeasurements(temp, humity, pressure);
    }
    

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		weatherStation();
		
		
		System.out.println("-----날씨가 변한다.----");
        changeWeather(40, 50, 10);                    // WeatherData에 새로운 데이터 전송
                
        System.out.println("");
        
        System.out.println("-----날씨가 변한다.----");
        changeWeather(50, 60, 20);                    // WeatherData에 새로운 데이터 전송
        
        System.out.println("");
	}

}
