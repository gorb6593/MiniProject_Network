package test;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

public class practice01 {
	private MqttClient client;
	public practice01() {
		try {
			client = new MqttClient("tcp://192.168.0.7:1883", "id1");
			client.connect();
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
	public boolean send(String topic, String msg) {
		try {
			MqttMessage message = new MqttMessage();
			message.setPayload(msg.getBytes());
			client.publish(topic, message);
		} catch (MqttPersistenceException e) {
			e.printStackTrace();
		} catch (MqttException e) {
			e.printStackTrace();
		}
		return true;
	}
	public void close() {
		if(client!=null) {
			try {
				client.disconnect();
				client.close();
			} catch (MqttException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		practice01 sender = new practice01();	
		new Thread(new Runnable() {			
			@Override
			public void run() {
				int i = 1;
				String msg ="";
				while(true) {
					if(i==5) {
						break;
					}else {
						if(i%2==1) {
							msg = "led_on";
						}else {
							msg = "led_off";
						}
					}
					sender.send("led", msg);
					i++;
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				sender.close();
			}			
		}).start();
	}
}
