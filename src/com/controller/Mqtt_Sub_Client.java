package com.controller;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;


public class Mqtt_Sub_Client implements MqttCallback{
	private MqttClient mqttclient;
	private MqttConnectOptions mqttOptions;
	public Mqtt_Sub_Client init(String server, String clientId) {		
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
	//구독신청하기
	public boolean subscribe(String topic) {
		try {
			if(topic!=null) {
				mqttclient.subscribe(topic, 0);//topic, Qos는 메시지를 전달하고 관리하는 방법(품질)
			}
		}catch(MqttException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public static void main(String[] args) {
		Mqtt_Sub_Client client = new Mqtt_Sub_Client();	
		client.init("tcp://192.168.0.7:1883", "myid").subscribe("button");
	}
	//커넥션이 종료되면 호출 - 통신오류로 연결이 끊어진 경우 호출
	@Override
	public void connectionLost(Throwable arg0) {
		
	}
	//메시지 배달이 완료되면 호출
	@Override
	public void deliveryComplete(IMqttDeliveryToken arg0) {
		
	}
	//메시지가 도착하면 호출되는 메소드 - topic(broker구독신청한 topic명), MqttMessage는 메시지
	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		System.out.println("===메시지도착===");
		System.out.println(message+","+"topic:"+topic+",id:"+message.getId()+",Payload:"+new String(message.getPayload()));
	}
	
}