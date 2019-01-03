package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

public class UDPEchoClientTimeout {

    private static final int TIMEOUT = 3_000; // resend timeout (ms)
    private static final int MAX_TRIES = 5; // maximum retransmissions

    public static void main(String[] args) throws IOException {

        if (args.length != 3) {
           throw new IllegalArgumentException("Parameters(s): <Server> <Port> <Word>");
        }

        InetAddress serverAddress = InetAddress.getByName(args[0]);
        int servPort = Integer.parseInt(args[1]);

        byte[] bytesToSend = args[2].getBytes();

        DatagramSocket socket = new DatagramSocket();

        socket.setSoTimeout(TIMEOUT);

        DatagramPacket sendPacket = new DatagramPacket(bytesToSend, bytesToSend.length, serverAddress, servPort);

        DatagramPacket receivePacket = new DatagramPacket(new byte[bytesToSend.length], bytesToSend.length);

        int tries = 0;

        boolean receivedResponse = false;

        do {
            socket.send(sendPacket);
            try {
                socket.receive(receivePacket);

                if (!receivePacket.getAddress().equals(serverAddress)) { // check source
                    throw new IOException("Received packet from unknown source");
                }
                receivedResponse = true;
            } catch (SocketTimeoutException e) { // did not get anything
                tries++;
                System.out.println("Timed out, " + (MAX_TRIES - tries) + " more tries...");
            }
        } while (!receivedResponse && tries < MAX_TRIES);

        if(receivedResponse) {
            System.out.println("Received: " + new String(receivePacket.getData()));
        } else {
            System.out.println("No response -- giving up");
        }
        socket.close();
    }
}
