# 🌍 Weather Forecast App using Java Swing

This Java Swing application provides weather forecasts for any location worldwide and displays real-time room temperature and humidity. It utilizes the **OpenWeatherMap API** for global weather data and the **Blynk IoT platform**, in conjunction with an **ESP32** microcontroller and a **DHT22** sensor, for local room environment monitoring.

## 🚀 Features

- **Global Weather Forecast**  
  Displays current weather conditions (temperature, description, feels like, min/max temperature, humidity, wind speed) for any searched city.
  
- **Visual Weather Icons**  
  Shows appropriate icons based on the current weather condition.
  
- **Room Environment Monitoring**  
  Shows live room temperature and humidity readings obtained from an ESP32 and DHT22 sensor via Blynk.
  
- **Location Search**  
  Allows users to search for weather information for different locations.
  
- **Dynamic Updates**  
  Room temperature and humidity data are updated periodically.
  
- **Custom Look and Feel**  
  Uses the JTattoo Aluminium Look and Feel for the UI.

## 🛠 Technologies Used

- **Application Framework**: Java Swing  
- **APIs & Services**:
  - [OpenWeatherMap API](https://openweathermap.org/api) (for global weather data)
  - [Blynk IoT Platform](https://blynk.io/) (for sensor data transmission and retrieval)
- **Hardware**:
  - ESP32 Microcontroller
  - DHT22 Temperature and Humidity Sensor
- **Build Tool**: Apache Maven
- **Libraries**:
  - `org.json` (for JSON processing)
  - `com.google.code.gson` (for JSON processing)
  - `com.googlecode.json-simple` (for JSON processing - see Setup)
  - `com.jtattoo.JTattoo` (for UI Look and Feel - see Setup)

## ⚙️ Setup and Installation

### Prerequisites

- Java Development Kit (JDK) 22 or higher.
- Apache Maven.
- Arduino IDE (for ESP32 setup).
- Git (for cloning the repository).

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/farhankabirsifat/Weather-Forecast-app-using-Java-swing.git
cd Weather-Forecast-app-using-Java-swing

