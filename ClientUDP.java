//Created by Dane Iwema

import java.net.*;
import java.net.InetAddress;
import java.util.Scanner;

class ClientUDP {
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String sent = in.nextLine();
        try{
            // String to store input
            //String sent;
            // Get host name and port number
            if (args.length != 2) {
                System.err.println("usage: java ClientUCP <host name> <port number>");
                System.exit(1);
            }

            // Store hostname and port number
            String hostName = args[0];
            int portNumber = Integer.parseInt(args[1]);

            // Get IP and create socket with it alongside port number
            InetAddress serverIP = InetAddress.getByName(hostName);
            DatagramSocket socket = new DatagramSocket();

            // Get input and put in byte array
            byte[] sentData = sent.getBytes();

            // Put input in packet
            DatagramPacket packet = new DatagramPacket(sentData, sentData.length, serverIP, portNumber);

            // Send packet
            socket.send(packet);
            // Receive:
    
            // Receive data in byte array within packet
            byte[] receiveData = new byte[1024];
            DatagramPacket recPacket = new DatagramPacket(receiveData, receiveData.length);

            while (recPacket.getData() != null) {
                // Receive with same socket
                socket.receive(recPacket);
            
                // Extract message and print
                String message = new String(recPacket.getData(), 0, recPacket.getLength());
                System.out.println("Response: " + message);
            }

            // Close socket and server
            socket.close();
            in.close();

        } catch (Exception e) {
            // Catch exception
            System.out.println("Problem sending packet" + e.getMessage());
            return;
        } 

    }

}