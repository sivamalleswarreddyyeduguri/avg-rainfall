package com.zettamine.dto;

public class AnnualRainfall {

	private int cityPincode;
	private String cityName;
	private double averageAnnualRainfall;

	public AnnualRainfall() {

	}

	public AnnualRainfall(int cityPincode, String cityName, double averageAnnualRainfall) {
		super();
		this.cityPincode = cityPincode;
		this.cityName = cityName;
		this.averageAnnualRainfall = averageAnnualRainfall;
	}

	public int getCityPincode() {
		return cityPincode;
	}

	public void setCityPincode(int cityPincode) {
		this.cityPincode = cityPincode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public double getAverageAnnualRainfall() {
		return averageAnnualRainfall;
	}

	public void setAverageAnnualRainfall(double averageAnnualRainfall) {
		this.averageAnnualRainfall = averageAnnualRainfall;
	}

	public void calculateAverageAnualRainfall(double monthlyRainfall[]) {

		double avg = 0;

		for (int i = 0; i < monthlyRainfall.length; i++) {
			avg += avg + monthlyRainfall[i];
		}

		avg = avg / 12;

		this.setAverageAnnualRainfall(avg);

	}

	@Override
	public String toString() {
		return String.format("%-15d%-20s%-25.2f", cityPincode, cityName, averageAnnualRainfall);
	}

}
