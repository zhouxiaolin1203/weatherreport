package com.example.weatherbroadcast;

public class weatherinfo {
	private String city;
	private String weather;
	private String temperature;
	private String wet;
	private String wind;
	public weatherinfo(String city, String weather, String temperature,
			String wet, String wind) {
		super();
		this.city = city;
		this.weather = weather;
		this.temperature = temperature;
		this.wet = wet;
		this.wind = wind;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getWeather() {
		return weather;
	}
	public void setWeather(String weather) {
		this.weather = weather;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getWet() {
		return wet;
	}
	public void setWet(String wet) {
		this.wet = wet;
	}
	public String getWind() {
		return wind;
	}
	public void setWind(String wind) {
		this.wind = wind;
	}
	

}
