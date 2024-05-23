package org.example;

import org.json.simple.JSONObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WeatherUI extends JFrame {
	private JSONObject weatherData;
	JTextField searchTextField;
	JLabel weatherConditionImage;
	JLabel temperatureText;
	JLabel weatherConditionDesc;
	JLabel feelsLikeText;
	JLabel tempMaxText;
	JLabel tempMinText;
	JLabel humidityText;
	JLabel windspeedText;
	JLabel roomTempValue;
	JLabel roomHumidityValue;
	Timer roomDataTimer;

	public WeatherUI(JSONObject weatherData) {
		this.weatherData = weatherData;
		setTitle("Weather App");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(450, 700);
		setLocationRelativeTo(null);
		setLayout(null);
		setResizable(false);
		addGuiComponents();
		setVisible(true);
		startRoomDataUpdate();
	}

	private ImageIcon loadImage(String resourcePath) {
		try {
			BufferedImage image = ImageIO.read(new File(resourcePath));
			return new ImageIcon(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void addGuiComponents() {
		ImageIcon LogoIcon = new ImageIcon("src/main/resources/assets/weather.png");
		setIconImage(LogoIcon.getImage());
		getContentPane().setBackground(new Color(52, 152, 219));

		searchTextField = new JTextField();
		searchTextField.setBounds(15, 15, 351, 45);
		searchTextField.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		searchTextField.setText("Search for a location");
		searchTextField.setCaretColor(Color.WHITE);
		searchTextField.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.GRAY),
				BorderFactory.createEmptyBorder(5, 10, 5, 10)
		));

		searchTextField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (searchTextField.getText().equals("Search for a location")) {
					searchTextField.setText("");
					searchTextField.setForeground(Color.BLACK);
					searchTextField.setCaretColor(Color.BLACK);
				}
			}
		});
		add(searchTextField);

		weatherConditionImage = new JLabel();
		weatherConditionImage.setIcon(loadImage("src/main/resources/assets/clear sky d.png"));
		weatherConditionImage.setBounds(0, 85, 450, 217);
		weatherConditionImage.setHorizontalAlignment(weatherConditionImage.CENTER);
		add(weatherConditionImage);

		temperatureText = new JLabel("0 °C");
		temperatureText.setBounds(0, 310, 450, 54);
		temperatureText.setFont(new Font("Dialog", Font.BOLD, 48));
		temperatureText.setHorizontalAlignment(temperatureText.CENTER);
		temperatureText.setForeground(Color.BLACK);
		add(temperatureText);

		weatherConditionDesc = new JLabel("clear");
		weatherConditionDesc.setBounds(0, 365, 450, 36);
		weatherConditionDesc.setFont(new Font("Dialog", Font.PLAIN, 32));
		weatherConditionDesc.setHorizontalAlignment(weatherConditionDesc.CENTER);
		weatherConditionDesc.setForeground(Color.BLACK);
		add(weatherConditionDesc);

		feelsLikeText = new JLabel("Feels like: 0 °C");
		feelsLikeText.setBounds(0, 410, 450, 30);
		feelsLikeText.setFont(new Font("Dialog", Font.BOLD, 20));
		feelsLikeText.setHorizontalAlignment(feelsLikeText.CENTER);
		feelsLikeText.setForeground(Color.BLACK);
		add(feelsLikeText);

		JLabel tempMaxImage = new JLabel();
		tempMaxImage.setIcon(loadImage("src/main/resources/assets/hot.png"));
		tempMaxImage.setBounds(20, 455, 74, 66);
		add(tempMaxImage);

		tempMaxText = new JLabel("<html>Maximum Temperature<br>0 °C</html>");
		tempMaxText.setBounds(110, 455, 150, 55);
		tempMaxText.setFont(new Font("Dialog", Font.BOLD, 16));
		tempMaxText.setForeground(Color.BLACK);
		add(tempMaxText);

		JLabel tempMinImage = new JLabel();
		tempMinImage.setIcon(loadImage("src/main/resources/assets/cold.png"));
		tempMinImage.setBounds(220, 455, 74, 66);
		add(tempMinImage);

		tempMinText = new JLabel("<html>Minimum Temperature<br>0 °C</html>");
		tempMinText.setBounds(310, 455, 150, 55);
		tempMinText.setFont(new Font("Dialog", Font.BOLD, 16));
		tempMinText.setForeground(Color.BLACK);
		add(tempMinText);

		JLabel humidityImage = new JLabel();
		humidityImage.setIcon(loadImage("src/main/resources/assets/humidity.png"));
		humidityImage.setBounds(20, 530, 74, 66);
		add(humidityImage);

		humidityText = new JLabel("<html>Humidity<br>0%</html>");
		humidityText.setBounds(110, 530, 150, 55);
		humidityText.setFont(new Font("Dialog", Font.BOLD, 16));
		humidityText.setForeground(Color.BLACK);
		add(humidityText);

		JLabel windspeedImage = new JLabel();
		windspeedImage.setIcon(loadImage("src/main/resources/assets/windspeed.png"));
		windspeedImage.setBounds(220, 530, 74, 66);
		add(windspeedImage);

		windspeedText = new JLabel("<html>Windspeed<br>0km/h</html>");
		windspeedText.setBounds(310, 530, 150, 55);
		windspeedText.setFont(new Font("Dialog", Font.BOLD, 16));
		windspeedText.setForeground(Color.BLACK);
		add(windspeedText);

		JLabel roomTempLabel = new JLabel("Room Temperature:");
		roomTempLabel.setBounds(50, 600, 200, 30);
		roomTempLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		add(roomTempLabel);

		roomTempValue = new JLabel("0°C");
		roomTempValue.setBounds(250, 600, 100, 30);
		roomTempValue.setFont(new Font("Dialog", Font.BOLD, 18));
		add(roomTempValue);

		JLabel roomHumidityLabel = new JLabel("Room Humidity:");
		roomHumidityLabel.setBounds(50, 630, 200, 30);
		roomHumidityLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		add(roomHumidityLabel);

		roomHumidityValue = new JLabel("0%");
		roomHumidityValue.setBounds(250, 630, 100, 30);
		roomHumidityValue.setFont(new Font("Dialog", Font.BOLD, 18));
		add(roomHumidityValue);

		JButton searchButton = new JButton();
		searchButton.setIcon(loadImage("src/main/resources/assets/search.png"));
		searchButton.setBounds(375, 15, 50, 45);
		searchButton.setFocusable(false);
		searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				performSearch();
			}
		});
		getRootPane().setDefaultButton(searchButton);
		add(searchButton);

		updateWeatherData();
	}

	private void performSearch() {
		String userInput = searchTextField.getText().trim();

		if (userInput.isEmpty() || userInput.equalsIgnoreCase("Search for a location")) {
			JOptionPane.showMessageDialog(this, "Please enter a valid location.");
			return;
		}

		weatherData = App.getWeatherData(userInput);
		if (weatherData != null) {
			updateWeatherData();
		} else {
			JOptionPane.showMessageDialog(this, "Failed to fetch weather data. Please check your city and API key.");
		}
	}

	private void updateWeatherData() {
		if (weatherData == null) {
			return;
		}

		String temperature = (String) weatherData.get("temperature");
		temperatureText.setText(temperature + " °C");

		String roomTemp = String.valueOf(weatherData.get("room_temperature"));
		roomTempValue.setText(roomTemp + " °C");

		String roomHumidity = String.valueOf(weatherData.get("room_humidity"));
		roomHumidityValue.setText(roomHumidity + " %");

		String weatherCondition = (String) weatherData.get("description");
		weatherConditionDesc.setText(weatherCondition);

		String feelsLike = (String) weatherData.get("feels_like");
		feelsLikeText.setText("Feels like: " + feelsLike + " °C");

		String tempMax = (String) weatherData.get("max_temperature");
		tempMaxText.setText("<html>Maximum Temperature<br>" + tempMax + " °C</html>");

		String tempMin = (String) weatherData.get("min_temperature");
		tempMinText.setText("<html>Minimum Temperature<br>" + tempMin + " °C</html>");

		Long humidity = (Long) weatherData.get("humidity");
		humidityText.setText("<html>Humidity<br>" + humidity + "%</html>");

		Double windspeed = (Double) weatherData.get("wind");
		windspeedText.setText("<html>Windspeed<br>" + windspeed + "km/h</html>");

		String iconcode = (String) weatherData.get("icon");

		weatherConditionImage.setIcon(loadImage(getIconPath(iconcode)));
	}

	private String getIconPath(String iconcode) {
		switch (iconcode) {
			case "01d":
				return "src/main/resources/assets/clear sky d.png";
			case "01n":
				return "src/main/resources/assets/clear sky n.png";
			case "02d":
				return "src/main/resources/assets/few clouds d.png";
			case "02n":
				return "src/main/resources/assets/few clouds n.png";
			case "03d":
			case "03n":
				return "src/main/resources/assets/scattered clouds.png";
			case "04d":
			case "04n":
				return "src/main/resources/assets/broken clouds.png";
			case "09d":
			case "09n":
				return "src/main/resources/assets/shower rain.png";
			case "10d":
				return "src/main/resources/assets/rain d.png";
			case "10n":
				return "src/main/resources/assets/rain n.png";
			case "11d":
			case "11n":
				return "src/main/resources/assets/thunderstorm.png";
			case "13d":
			case "13n":
				return "src/main/resources/assets/snow.png";
			case "50d":
			case "50n":
				return "src/main/resources/assets/mist.png";
			default:
				return "src/main/resources/assets/unknown.png"; // default icon for unknown cases
		}
	}

	private void startRoomDataUpdate() {
		roomDataTimer = new Timer(5000, new ActionListener() { // Update every 5 seconds
			@Override
			public void actionPerformed(ActionEvent e) {
				updateRoomData();
			}
		});
		roomDataTimer.start();
	}

	private void updateRoomData() {
		JSONObject roomData = App.getRoomData();
		if (roomData != null) {
			String roomTemp = String.valueOf(roomData.get("room_temperature"));
			roomTempValue.setText(roomTemp + " °C");

			String roomHumidity = String.valueOf(roomData.get("room_humidity"));
			roomHumidityValue.setText(roomHumidity + " %");
		}
	}
}
