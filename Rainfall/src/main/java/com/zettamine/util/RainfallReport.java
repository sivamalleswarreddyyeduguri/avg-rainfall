package com.zettamine.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.zettamine.Dao.DBHandler;
import com.zettamine.dto.AnnualRainfall;

public class RainfallReport {

	private static Statement st;
	private static AnnualRainfall anu;

	public RainfallReport() {
		try {
			Connection con = DBHandler.ConnectionestablishConnection();
			anu = new AnnualRainfall();
			st = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static List<AnnualRainfall> generateRainfallReport(String filePath) {

		List<AnnualRainfall> rainfall = new ArrayList<AnnualRainfall>();
		try {
			FileReader file = new FileReader(filePath);
			BufferedReader bfr = new BufferedReader(file);
			String data = bfr.readLine();
			while (data != null) {
				String[] rainFallData = data.split(",");
				int cityPincode = Integer.parseInt(rainFallData[0]);
				anu.setCityPincode(cityPincode);
				String city = rainFallData[1];
				anu.setCityName(city);
				String validate = String.valueOf(cityPincode);
				if (validate(validate)) {
					double[] month = new double[rainFallData.length - 2];
					for (int i = 2; i < rainFallData.length; i++) {
						month[i - 2] = Double.valueOf(rainFallData[i]);
					}

					anu.calculateAverageAnualRainfall(month);

					rainfall.add(new AnnualRainfall(anu.getCityPincode(), anu.getCityName(),
							anu.getAverageAnnualRainfall()));

					setData();

					data = bfr.readLine();
				} else {
					throw new InvalidCityPincodeException("Invalid City Pincode: " + cityPincode);
				}
			} // End of while

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidCityPincodeException e) {
			e.printStackTrace();
		}
		
		return rainfall;
	}

	public static boolean validate(String cityPincode) {
		if (cityPincode.matches("[0-9]{5}")) {
			return true;
		} else
			return false;
	}

	public static void setData() {

		String sql = "INSERT INTO AnnualRainfall VALUES (" + anu.getCityPincode() + ", '" + anu.getCityName() + "', "
				+ anu.getAverageAnnualRainfall() + ")";

		try {
			st.executeUpdate(sql);
			System.out.println("----Executed Successfully----");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
