package com.example.weather.moder;

public class Weather {
	private String dayOfWeek;//���ڼ�
	private String date;//����
	private String temperature;//�¶�
	private String weather;//����
	public Weather(){
	}
	public Weather(String dayOfWeek, String date, String temperature,
			String weather) {
		super();
		this.dayOfWeek = dayOfWeek;
		this.date = date;
		this.temperature = temperature;
		this.weather = weather;
	}
	public String getDayOfWeek() {
		return dayOfWeek;
	}
	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getWeather() {
		return weather;
	}
	public void setWeather(String weather) {
		this.weather = weather;
	}
	@Override
	public String toString() {
		return "Weather [dayOfWeek=" + dayOfWeek + ", date=" + date
				+ ", temperature=" + temperature + ", weather=" + weather + "]";
	}

}
