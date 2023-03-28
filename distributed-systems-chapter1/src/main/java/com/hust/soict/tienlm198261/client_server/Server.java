package com.hust.soict.tienlm198261.client_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import com.hust.soict.tienlm198261.helper.*;
import java.util.Arrays;

public class Server {
    public static void main(String[] args) {
        System.out.println("The Sorter Server is running!");
        int clientNumber = 0;
        try (ServerSocket listener = new ServerSocket(9898)) {
            while (true) {
                new Sorter(listener.accept(), clientNumber++).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static class Sorter extends Thread {
        private final Socket socket;
        private final int clientNumber;

        public Sorter(Socket socket, int clientNumber) {
            this.socket = socket;
            this.clientNumber = clientNumber;
            System.out.println("New client #" + clientNumber + " connected at " + socket);
        }

        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                out.println("Hello, you are client #" + clientNumber);
                while (true) {
                    String input = in.readLine();
                    if (input == null || input.isEmpty()) {
                        break;
                    }
                    //Put it in a string array
                    String[] nums = input.split(" ");

                    //Convert this string array to an int array
                    int[] intarr = new int[nums.length];

                    int i = 0;

                    for (String textValue : nums) {
                        intarr[i] = Integer.parseInt(textValue);
                        i++;
                    }

                    //Sort the numbers in this int array
                    new SelectionSort().sort(intarr);
//                    new BubbleSort().sort(intarr);
//                    new InsertionSort().sort(intarr);
//                    new ShellSort().sort(intarr);
                    
                    //Convert the int array to String
                    String[] strArray = Arrays.stream(intarr)
                            .mapToObj(String::valueOf)
                            .toArray(String[]::new);
                    //Send the result to Client
                    out.println(Arrays.toString(strArray));
                }
            } catch (IOException e) {
                System.out.println("Error handling client #" + clientNumber);
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                }
                System.out.println("Connection with client # " + clientNumber + " closed");
            }
        }
    }
}
