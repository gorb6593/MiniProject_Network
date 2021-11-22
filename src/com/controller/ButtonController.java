package com.controller;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class ButtonController implements MqttCallback {
	private MqttClient mqttclient;
	private MqttConnectOptions mqttOptions;
	
	public ButtonController init(String server, String clientId) {
		mqttOptions = new MqttConnectOptions();
		mqttOptions.setCleanSession(true);
		mqttOptions.setKeepAliveInterval(30);
		try {
			mqttclient = new MqttClient(server, clientId);
			mqttclient.setCallback(this);
			mqttclient.connect(mqttOptions);
		} catch (MqttException e) {
			e.printStackTrace();
		}
		return this;
	}
	public boolean subscribe(String topic) {
		try {
			if(topic!=null) {
				mqttclient.subscribe(topic, 0);
			} 
		}catch (MqttException e) {
				e.printStackTrace();
				return false;
		} 
		return true;
	}	
	
	public static void main(String[] args) {
		ButtonController client = new ButtonController();
		client.init("tec://192.168.0.7:1883", "myid10").subscribe("button");
	}

	@Override
	public void connectionLost(Throwable arg0) {
		
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken arg0) {
		
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		System.out.println("메세지도착");
		String msg = new String(message.getPayload());
		new Thread(new Runnable() {		
			@Override
			public void run() {
				if(msg.equals("button_on")) {
					// 메시지가 button_on이면 컨트롤러에서 어떤걸 호출해야하나?
				}else {
					// 메시지가 그게 아니면 어떤거 해야하지?
				}
			}
		}).start();
	}
}
