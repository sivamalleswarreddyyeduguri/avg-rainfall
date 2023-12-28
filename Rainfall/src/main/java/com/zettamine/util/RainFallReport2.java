package com.zettamine.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.zettamine.Dao.DBHandler;
import com.zettamine.dto.AnnualRainfall;

public class RainFallReport2 {

	private static Statement st;

	public RainFallReport2() {
		try {
			Connection con = DBHandler.ConnectionestablishConnection();
			st = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static List<AnnualRainfall> findMaximumRainfallCities() {

		List<AnnualRainfall> maxiRainfallCities = new ArrayList<>();

		try {
			String query = "select * from AnnualRainfall where average_annual_rainfall = "
					+ "(select max(average_annual_rainfall) from AnnualRainfall)";
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				int cityPincode = rs.getInt(1);
				String cityName = rs.getString(2);
				double avgRainfall = rs.getDouble(3);

				AnnualRainfall city = new AnnualRainfall(cityPincode, cityName, avgRainfall);
				maxiRainfallCities.add(city);
			}

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return maxiRainfallCities;

	}

}
