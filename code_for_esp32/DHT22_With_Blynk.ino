//Tech Trends Shameer

#define BLYNK_TEMPLATE_ID "TMPL6OL6J2n5q"
#define BLYNK_TEMPLATE_NAME "test"
#define BLYNK_AUTH_TOKEN "9XbNpWq_SXidLJrWBWYfj_3pG_yBzxu8"

#define BLYNK_PRINT Serial
#include <WiFi.h>
//#include <ESP8266WiFi.h> 
#include <BlynkSimpleEsp32.h>

#include <DHT.h>


char auth[] = BLYNK_AUTH_TOKEN;

char ssid[] = "Error 404";  // type your wifi name
char pass[] = "404theke1";  // type your wifi password

BlynkTimer timer;


#define DHTPIN 26 //Connect Out pin to D2 in NODE MCU
#define DHTTYPE DHT22  
DHT dht(DHTPIN, DHTTYPE);
void setup()
{   
  
  Serial.begin(115200);
  

  Blynk.begin(auth, ssid, pass);
  dht.begin();
  timer.setInterval(100L, sendSensor);
 
  }

void sendSensor()
{
  float h = dht.readHumidity();
  float t = dht.readTemperature(); // or dht.readTemperature(true) for Fahrenheit

  if (isnan(h) || isnan(t)) {
    Serial.println("Failed to read from DHT sensor!");
    return;
  }
  // You can send any value at any time.
  // Please don't send more that 10 values per second.
    Blynk.virtualWrite(V0, t);
    Blynk.virtualWrite(V1, h);
    Serial.print("Temperature : ");
    Serial.print(t);
    Serial.print("    Humidity : ");
    Serial.println(h);
}


void loop()
{
  Blynk.run();
  timer.run();
 }