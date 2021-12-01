package com.example.appkahootadi;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.ListModel;

import kahootADI.QuestionsRoom;
import kahootADI.configClass;
import kahootADI.waitingRoom;

import java.net.Socket;

import lipermi.exception.LipeRMIException;
import lipermi.handler.CallHandler;
import lipermi.net.IServerListener;
import lipermi.net.Server;
import model.Answer;

public class AppServer implements TestService {
	private String ip;
	private waitingRoom wr;
	private QuestionsRoom qr;
	private configClass cc;
	

	public static void main(String[] args) {
		new AppServer();
	}

	public AppServer() {
		try {
			Enumeration<NetworkInterface> n = NetworkInterface.getNetworkInterfaces();
			for (; n.hasMoreElements();) {
				NetworkInterface e = n.nextElement();
				Enumeration<InetAddress> a = e.getInetAddresses();
				for (; a.hasMoreElements();) {
					InetAddress addr = a.nextElement();
					if (addr.toString().contains("192")) {
						ip = addr.getHostAddress().toString();
					}
				}
			}
			System.out.println(ip);
			CallHandler callHandler = new CallHandler();
			callHandler.registerGlobal(TestService.class, this);
			Server server = new Server();
			server.bind(7777, callHandler);
			wr = new waitingRoom();
			wr.setVisible(true);
			wr.getLblIPLabel().setText("IP: " + ip);
			server.addServerListener(new IServerListener() {
				@Override
				public void clientDisconnected(Socket socket) {
					System.out.println("Client Disconnected: ");
				}

				@Override
				public void clientConnected(Socket socket) {
					// addPlayerToList(wr,sendNickname(ip));
				}
			});
		} catch (LipeRMIException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getNickname(String nickname, String playerIP) {
		addPlayerToList(wr, nickname);
		wr.getTokenPlayer().put(nickname, playerIP);
		return nickname;
	}

	public void addPlayerToList(waitingRoom wr, String player) {
		wr.getListModel().addElement(player);
	}

	@Override
	public boolean kahootStarted() {
		return wr.isPlaying();
	}

	@Override
	public boolean checkNicknames(String actualNickname) {
		boolean exists = false;
		ListModel model = wr.getList().getModel();
		for (int i = 0; i < model.getSize(); i++) {
			System.out.println(actualNickname + " " + wr.getListModel().get(i));
			if (actualNickname.equals(wr.getListModel().get(i))) {
				exists = true;
			}
		}
		return exists;
	}

	@Override
	public int getSizeList() {
		ListModel model = wr.getList().getModel();
		return model.getSize();
	}

	public ArrayList<List<String>> getKahootAnswers() {
		List<List<String>> allAnswers = wr.getKahootAnswer();
		return (ArrayList<List<String>>) allAnswers;
	}

	@Override
	public String answerPlayer(String playerIP, String answer) {
		QuestionsRoom.getAnswerPlayer().put(playerIP, answer);
		return answer;
	}

	@Override
	public boolean siguientePregunta() {
		return qr.issPregunta();
	}

	@Override
	public ArrayList<String> getQuestionAnswers() {
		ArrayList<String> AnswerToString = new ArrayList<String>();
		for (Answer a : QuestionsRoom.getAnswers1()) {
			AnswerToString.add(a.getAnswer());
		}
		return AnswerToString;
	}

	@Override
	public boolean inCountdown() {
		return wr.isStarted();
	}

	@Override
	public long getActualTimer() {
		long tiempo = wr.getClockTime() / 1000;
		System.out.println(tiempo);
		return tiempo;
	}

	@Override
	public int getTimeout() {
		return Integer.parseInt(cc.getTimeout());
	}


	public boolean panelQR() {
		return QuestionsRoom.isPanelQR();
	}

	@Override
	public boolean respondido() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
