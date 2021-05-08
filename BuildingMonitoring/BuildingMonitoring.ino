#include "DHTesp.h"
#include "ESP8266WiFi.h"
#include <PubSubClient.h>



#define DHTPIN 2     // what pin we're connected to

// Uncomment whatever type you're using!
//#define DHTTYPE DHT11   // DHT 11
//#define DHTTYPE DHT22   // DHT 22  (AM2302)
#define DHTTYPE DHT21   // DHT 21 (AM2301)

// Connect pin 1 (on the left) of the sensor to +5V
// Connect pin 2 of the sensor to whatever your DHTPIN is
// Connect pin 4 (on the right) of the sensor to GROUND
// Connect a 10K resistor from pin 2 (data) to pin 1 (power) of the sensor

DHTesp dht;

char* ssid = "Muroslava";            // your network SSID (name)
char* pass = "28032015";        // your network password


const char *mqtt_server = "192.168.0.105";
const int mqtt_port = 1883;
const char *mqtt_user = "";
const char *mqtt_pass = "";


int status = WL_IDLE_STATUS;     // the Wifi radio's status

void callback(char* topic, byte* payload, unsigned int length)
{
  Serial.print(topic);
  Serial.print(" => ");

  for (int i = 0; i < length; i++) {
    Serial.print((char)payload[i]);
  }
  Serial.println();
  delay(20000);


  if (String(topic) == "server") {
    //todo  parse json and read data
  }
}

WiFiClient wclient;
PubSubClient client(wclient);


void setup() {
  Serial.begin(9600);
  Serial.println("DHT21 test!");

  dht.setup(4, DHTesp::DHT22);

  WiFi.begin(ssid, pass);
  while (WiFi.status() != WL_CONNECTED)
  {
    delay(500);
    Serial.print("*");
  }

  Serial.println("");
  Serial.println("WiFi connection Successful");
  Serial.print("The IP Address of ESP8266 Module is: ");
  Serial.print(WiFi.localIP());// Print the IP address
  client.setServer(mqtt_server, 1883);
  client.setCallback(callback);
}

void loop() {
  client.connect("409");
  Serial.println(client.connected());

  sendData();
  wait(500);


}

void wait(int time) {
  int now = millis();

  while ((millis() - now) < time) {
    client.subscribe("server");
    client.loop();
    yield();
  }
}

void sendData() {
  // Reading temperature or humidity takes about 250 milliseconds!
  // Sensor readings may also be up to 2 seconds 'old' (its a very slow sensor)
  float h = dht.getHumidity();
  wait(500);
  float t = dht.getTemperature();

  if (isnan(t) || isnan(h)) {
    Serial.println("Failed to read from DHT");
  } else {
    Serial.print("Humidity: ");
    Serial.print(h);
    Serial.print(" %\t");
    Serial.print("Temperature: ");
    Serial.print(t);
    Serial.println(" *C");
    //todo send data

    client.publish("client", "hi");
  }

}
