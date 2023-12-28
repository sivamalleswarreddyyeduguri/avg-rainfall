package com.zettamine.Driver;

import java.util.List;

import com.zettamine.dto.AnnualRainfall;
import com.zettamine.util.RainFallReport2;
import com.zettamine.util.RainfallReport;

public class MainRainFallReport {

	public static void main(String[] args) {

		RainfallReport report1 = new RainfallReport();
		RainFallReport2 report2 = new RainFallReport2();
		List<AnnualRainfall> maximumRainfallCities = RainFallReport2.findMaximumRainfallCities();

//		 RainfallReport.generateRainfallReport("C:\\Users\\Siva Malleshwar\\Desktop\\rainfallreport.txt");

		System.out.println(
				String.format("%-15s%-20s%-25s", "City Pincode |", "City Name |", "Average Annual Rainfall\n"));

		for (AnnualRainfall max : maximumRainfallCities) {
			System.out.println(max + "\n");
		}
	}

}
