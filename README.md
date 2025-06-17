# Weather Forecast App using Java Swing
[![Ask DeepWiki](https://devin.ai/assets/askdeepwiki.png)](https://deepwiki.com/farhankabirsifat/Weather-Forecast-app-using-Java-swing)

This Java Swing application provides weather forecasts for any location worldwide and displays real-time room temperature and humidity. It utilizes the OpenWeatherMap API for global weather data and the Blynk IoT platform, in conjunction with an ESP32 microcontroller and a DHT22 sensor, for local room environment monitoring.

## Features

*   **Global Weather Forecast:** Displays current weather conditions (temperature, description, feels like, min/max temperature, humidity, wind speed) for any searched city.
*   **Visual Weather Icons:** Shows appropriate icons based on the current weather condition.
*   **Room Environment Monitoring:** Shows live room temperature and humidity readings obtained from an ESP32 and DHT22 sensor via Blynk.
*   **Location Search:** Allows users to search for weather information for different locations.
*   **Dynamic Updates:** Room temperature and humidity data are updated periodically.
*   **Custom Look and Feel:** Uses the JTattoo Aluminium Look and Feel for the UI.

## Technologies Used

*   **Application Framework:** Java Swing
*   **APIs & Services:**
    *   OpenWeatherMap API (for global weather data)
    *   Blynk IoT Platform (for sensor data transmission and retrieval)
*   **Hardware:**
    *   ESP32 Microcontroller
    *   DHT22 Temperature and Humidity Sensor
*   **Build Tool:** Apache Maven
*   **Libraries:**
    *   `org.json` (for JSON processing)
    *   `com.google.code.gson` (for JSON processing)
    *   `com.googlecode.json-simple` (for JSON processing - *see Setup*)
    *   `com.jtattoo.JTattoo` (for UI Look and Feel - *see Setup*)

## Setup and Installation

### Prerequisites

*   Java Development Kit (JDK) 22 or higher.
*   Apache Maven.
*   Arduino IDE (for ESP32 setup).
*   Git (for cloning the repository).

### 1. Clone the Repository

```bash
git clone https://github.com/farhankabirsifat/Weather-Forecast-app-using-Java-swing.git
cd Weather-Forecast-app-using-Java-swing
```

### 2. Update `pom.xml` for Missing Dependencies

The provided `pom.xml` may be missing dependencies for `json-simple` and `JTattoo` which are used in the code. Add the following dependencies to your `pom.xml` file within the `<dependencies>` section:

```xml
        <dependency>
            <groupId>com.googlecode.json-simple</groupId>
            <artifactId>json-simple</artifactId>
            <version>1.1.1</version> <!-- Or the latest version -->
        </dependency>
        <dependency>
            <groupId>com.jtattoo</groupId>
            <artifactId>JTattoo</artifactId>
            <version>1.6.13</version> <!-- Or the latest version -->
        </dependency>
```

### 3. Configure API Keys and Credentials

You need to obtain API keys and configure credentials for OpenWeatherMap and Blynk.

**a. OpenWeatherMap API Key:**

1.  Sign up for a free API key at [OpenWeatherMap](https://openweathermap.org/appid).
2.  Open the file `src/main/java/org/example/App.java`.
3.  Replace `"f15274354ddadf213483a9e5b7a536c3"` with your OpenWeatherMap API key:
    ```java
    static String apiKey = "YOUR_OPENWEATHERMAP_API_KEY"; // generated key
    ```

**b. Blynk IoT Platform Setup:**

1.  Create an account on [Blynk](https://blynk.cloud/).
2.  Create a new Device Template (e.g., "Weather Station").
    *   Define two datastreams for temperature (e.g., Virtual Pin V0, Double type, Units: Celsius) and humidity (e.g., Virtual Pin V1, Double type, Units: Percentage).
3.  Create a new Device from your template. Note down the **Blynk Auth Token**, **Template ID**, and **Template Name**.
4.  Open the ESP32 sketch `code_for_esp32/DHT22_With_Blynk.ino`.
5.  Update the following lines with your Blynk project details:
    ```c++
    #define BLYNK_TEMPLATE_ID "YOUR_TEMPLATE_ID"
    #define BLYNK_TEMPLATE_NAME "YOUR_TEMPLATE_NAME"
    #define BLYNK_AUTH_TOKEN "YOUR_BLYNK_AUTH_TOKEN"
    ```
6.  In the same file (`DHT22_With_Blynk.ino`), update your WiFi credentials:
    ```c++
    char ssid[] = "YOUR_WIFI_SSID";  // type your wifi name
    char pass[] = "YOUR_WIFI_PASSWORD";  // type your wifi password
    ```
7.  Open the Java file `src/main/java/org/example/App.java`.
8.  Update the Blynk token and virtual pin identifiers to match your Blynk setup. Ensure `pin_1` matches your temperature datastream (e.g., V0) and `pin_2` matches your humidity datastream (e.g., V1).
    ```java
    static String pin_1 = "v0"; // Virtual pin for temperature on Blynk
    static String pin_2 = "v1"; // Virtual pin for humidity on Blynk
    static String token = "YOUR_BLYNK_AUTH_TOKEN"; // Same Blynk Auth Token as in the ESP32 sketch
    ```

### 4. Hardware Setup (ESP32 & DHT22)

1.  **Connections:**
    *   Connect the DHT22 sensor's VCC pin to 3.3V on the ESP32.
    *   Connect the DHT22 sensor's GND pin to GND on the ESP32.
    *   Connect the DHT22 sensor's Data pin to GPIO 26 on the ESP32 (as defined by `DHTPIN 26` in `DHT22_With_Blynk.ino`). A pull-up resistor (e.g., 4.7kΩ to 10kΩ) between the Data pin and VCC might be needed if not already on your DHT22 module.
2.  **Upload Firmware to ESP32:**
    *   Open `code_for_esp32/DHT22_With_Blynk.ino` in the Arduino IDE.
    *   Ensure you have the ESP32 board support installed in Arduino IDE.
    *   Install the required libraries:
        *   `Blynk` by Volodymyr Shymanskyy (from Library Manager)
        *   `DHT sensor library` by Adafruit (from Library Manager)
    *   Select your ESP32 board and COM port from the Tools menu.
    *   Click "Upload" to flash the firmware to your ESP32.
    *   Open the Serial Monitor to check if the ESP32 connects to WiFi and Blynk, and starts sending sensor readings.

### 5. Build the Java Application

1.  Navigate to the root directory of the cloned project (where `pom.xml` is located).
2.  Build the project using Maven:
    ```bash
    mvn clean package
    ```
    This will compile the code and create a JAR file in the `target/` directory (e.g., `Weather-Forecast-app-using-Java-swing-1.0-SNAPSHOT.jar`).

### 6. Run the Java Application

1.  You can run the application from your IDE by running the `org.example.Main` class.
2.  Alternatively, run the compiled JAR file:
    ```bash
    java -jar target/Weather-Forecast-app-using-Java-swing-1.0-SNAPSHOT.jar
    ```
    (Replace the JAR filename if it differs).

## Usage

1.  Launch the application.
2.  The application will initially display the weather for a default city (set to "Dhaka" in the code) and the current room temperature and humidity if the ESP32 and Blynk setup is working correctly.
3.  To search for weather in another location, type the city name into the search field at the top and click the search icon or press Enter.
4.  The weather information for the searched city will be displayed.
5.  Room temperature and humidity values will update automatically every 5 seconds, fetched from your Blynk project.

## Project Structure

```
farhankabirsifat-weather-forecast-app-using-java-swing/
├── README.md                 # This file
├── pom.xml                   # Maven project configuration
├── code_for_esp32/
│   └── DHT22_With_Blynk.ino  # Arduino sketch for ESP32 and DHT22 sensor
├── lib/                      # (empty in provided structure, may contain libraries if not using Maven exclusively)
└── src/
    └── main/
        ├── java/
        │   └── org/
        │       └── example/
        │           ├── App.java          # Handles API calls to OpenWeatherMap and Blynk
        │           ├── Main.java         # Main entry point for the Java Swing application
        │           └── WeatherUI.java    # Defines the graphical user interface
        └── resources/
            └── assets/               # Contains image assets for the UI (icons, etc.)
```

## Important Notes

*   **API Keys & Credentials:** This application requires you to configure your own API keys and credentials for OpenWeatherMap and Blynk as described in the setup section. The placeholder keys in the source code will not work.
*   **ESP32 & Blynk:** For the room temperature and humidity feature to work:
    *   The ESP32 must be correctly wired to the DHT22 sensor.
    *   The ESP32 firmware (`DHT22_With_Blynk.ino`) must be configured with your WiFi and Blynk credentials and successfully uploaded to the device.
    *   The ESP32 must be powered on, connected to WiFi, and actively sending data to your Blynk project.
*   **Image Assets:** The application loads UI image assets from `src/main/resources/assets/`. Ensure these paths are correct and accessible if you modify the project structure or run it in different environments.
*   **Error Handling:** Basic error messages are shown for API failures or incorrect city names. Check console output for more detailed errors if issues arise.


## 🙋‍♂️ Author

* **Made with ❤️ by Farhan Kabir Sifat
