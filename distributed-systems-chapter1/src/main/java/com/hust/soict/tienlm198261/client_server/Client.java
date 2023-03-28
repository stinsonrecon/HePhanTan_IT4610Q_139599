package com.hust.soict.tienlm198261.client_server;

import com.hust.soict.tienlm198261.utils.StringUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) throws Exception {
        socket = new Socket(ip, port);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        System.out.println("The Client is running!");
    }

    public String sendMessage(String message) throws Exception {
        this.out.println(message);
        return "Server response: " + this.in.readLine();
    }

    public String receiveMessage() throws Exception {
        return "Server response: " + this.in.readLine();
    }

    public void stopConnection() throws Exception {
        in.close();
        out.close();
        socket.close();
        System.out.println("The Client has been disconnected!");
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        Client client = new Client();
        client.startConnection("127.0.0.1", 9898);

        System.out.println(client.receiveMessage());

        System.out.println("Input number array: ");
        String message = scanner.nextLine();
        while (!StringUtil.stringIsNullOrEmty(message)) {
            System.out.println(client.sendMessage(message));

            System.out.println("Input number array: ");
            message = scanner.nextLine();
        }

        client.stopConnection();
    }
}
