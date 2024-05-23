package org.example;

import org.json.simple.JSONObject;
import javax.swing.*;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
        String city = "Dhaka"; // Replace "YourCity" with the desired city
        JSONObject weatherData = App.getWeatherData(city);
        if (weatherData != null) {
            SwingUtilities.invokeLater(() -> new WeatherUI(weatherData));
        } else {
            JOptionPane.showMessageDialog(null, "Failed to fetch weather data. Please check your city and API key.");
        }
    }
}