package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPEchoServer {
    private static final int ECHOMAX = 255; // maximum size of echo datagram

    public static void main(String[] args) throws IOException {
        if (args.length != 1) throw new IllegalArgumentException("Parameter(s): <Port>");

        int serverPort = Integer.parseInt(args[0]); // 서버 소켓의 로컬 포트

        DatagramSocket socket = new DatagramSocket(serverPort);
        DatagramPacket packet = new DatagramPacket(new byte[ECHOMAX], ECHOMAX);

        while (true) { // run forever, receiving and echoing datagrams
            socket.receive(packet);
            System.out.println("Handling client at " + packet.getAddress().getHostAddress()
            + " on port " + packet.getPort());
            socket.send(packet); // 똑같은 패킷을 클라이언트에게 보냄
            packet.setLength(ECHOMAX); // 버퍼 크기가 줄어드는 걸 방
        }
    }
}
