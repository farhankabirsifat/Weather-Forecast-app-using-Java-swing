package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import javax.swing.*;

public class App {
    static String apiKey = "f15274354ddadf213483a9e5b7a536c3"; // generated key
    static String pin_1 = "v0";
    static String pin_2 = "v1";
    static String token = "9XbNpWq_SXidLJrWBWYfj_3pG_yBzxu8";

    public static JSONObject getWeatherData(String city) {
        try {
            // Fetch weather data from OpenWeatherMap
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            if (connection.getResponseCode() != 200) {
                JOptionPane.showMessageDialog(null, connection.getResponseMessage());
                return null;
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            JSONObject jsonObject = (JSONObject) JSONValue.parse(response.toString());
            JSONObject mainObj = (JSONObject) jsonObject.get("main");
            JSONObject windObj = (JSONObject) jsonObject.get("wind");
            JSONArray weatherArray = (JSONArray) jsonObject.get("weather");
            JSONObject weather = (JSONObject) weatherArray.get(0);
            JSONObject weatherData = new JSONObject();

            double temperatureKelvin = ((Number) mainObj.get("temp")).doubleValue();
            double feelsLikeKelvin = ((Number) mainObj.get("feels_like")).doubleValue();
            double maxTemperatureKelvin = ((Number) mainObj.get("temp_max")).doubleValue();
            double minTemperatureKelvin = ((Number) mainObj.get("temp_min")).doubleValue();
            long humidity = ((Number) mainObj.get("humidity")).longValue();
            double windSpeed = ((Number) windObj.get("speed")).doubleValue();
            String description = (String) weather.get("description");
            String icon = (String) weather.get("icon");

            double temperatureCelsius = temperatureKelvin - 273.15;
            String temperatureFormat = String.format("%.2f", temperatureCelsius);
            double feelsLikeCelsius = feelsLikeKelvin - 273.15;
            String feelsLikeFormat = String.format("%.2f", feelsLikeCelsius);
            double maxTemperatureCelsius = maxTemperatureKelvin - 273.15;
            String maxTemperatureFormat = String.format("%.2f", maxTemperatureCelsius);
            double minTemperatureCelsius = minTemperatureKelvin - 273.15;
            String minTemperatureFormat = String.format("%.2f", minTemperatureCelsius);

            // Fetch room data from Blynk
            JSONObject roomData = getRoomData();
            if (roomData == null) {
                return null;
            }

            double roomTemperature = (Double) roomData.get("room_temperature");
            double roomHumidity = (Double) roomData.get("room_humidity");

            weatherData.put("temperature", temperatureFormat);
            weatherData.put("feels_like", feelsLikeFormat);
            weatherData.put("max_temperature", maxTemperatureFormat);
            weatherData.put("min_temperature", minTemperatureFormat);
            weatherData.put("description", description);
            weatherData.put("icon", icon);
            weatherData.put("wind", windSpeed);
            weatherData.put("humidity", humidity);
            weatherData.put("room_temperature", roomTemperature);
            weatherData.put("room_humidity", roomHumidity);

            return weatherData;

        } catch (Exception e) {
            e.printStackTrace();
            return null; // Return null in case of an error
        }
    }

    public static JSONObject getRoomData() {
        try {
            URL urlRoom = new URL("https://blynk.cloud/external/api/get?token=" + token + "&" + pin_1 + "&" + pin_2);
            HttpURLConnection conn = (HttpURLConnection) urlRoom.openConnection();
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() != 200) {
                JOptionPane.showMessageDialog(null, conn.getResponseMessage());
                return null;
            }
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder responseBlynk = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                responseBlynk.append(inputLine);
            }
            in.close();
            org.json.JSONObject jsonResponse = new org.json.JSONObject(responseBlynk.toString());
            JSONObject roomData = new JSONObject();
            double roomTemperature = jsonResponse.getDouble("v0");
            double roomHumidity = jsonResponse.getDouble("v1");
            roomData.put("room_temperature", roomTemperature);
            roomData.put("room_humidity", roomHumidity);

            return roomData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
