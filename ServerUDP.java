// simple UDP server to recieve a message and return it in all uppercase as well as the server name
// Created by Dane Iwema

import java.io.*;
import java.net.*;
import java.lang.String;

public class ServerUDP {
    public static void main(String[] args) throws IOException {
          // all necessary global variables
          int port = Integer.parseInt(args[0]);
          DatagramSocket server = new DatagramSocket(port);
          byte[] data = new byte[1024];
          DatagramPacket receivePacket = new DatagramPacket(data, 1024);

          //server loop, we chose a true loop because server never closes
          while (receivePacket.getData() != null) {
              // Server waiting for clients message
              server.receive(receivePacket);
              

              //message string created
              String messageStr = new String(receivePacket.getData(), 0, receivePacket.getLength());
              System.out.println("Recieved message: " + messageStr);
              messageStr = messageStr.toUpperCase() + " " + InetAddress.getLocalHost().getHostName();
              
              // Datagram packet is built with message and sender address and port
              InetAddress clientAddr = receivePacket.getAddress();
              byte[] message = messageStr.getBytes();
              DatagramPacket messageToClient = new DatagramPacket(message, message.length, clientAddr, receivePacket.getPort());
              System.out.println("Sending Message: " + messageStr);
              //sends packet to client
              server.send(messageToClient);
              }
    }
}