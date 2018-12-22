import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

public class TCPEchoClient {
    public static void main(String[] args) throws IOException {
        if(args.length < 2 || args.length > 3) {
            throw new IllegalArgumentException("Parameter(s): <Server> <Word> [<Port>]");
        }

        String server = args[0];
        byte[] data = args[1].getBytes(); // TCP 소켓은 바이트의 시퀀스를 주고 받는다.

        int servPort = (args.length == 3) ? Integer.parseInt(args[2]) : 7;

        Socket socket = new Socket(server, servPort);
        System.out.println("Connected to server...sending echo string");

        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();

        out.write(data); // send encoded string to the server

        // Receive the same string back from the server
        int totalBytesRcvd = 0; // Total bytes received so far
        int bytesRcvd; // Bytes received in last read
        while(totalBytesRcvd < data.length) {
            if((bytesRcvd = in.read(data, totalBytesRcvd, data.length -totalBytesRcvd)) == -1) {
                throw new SocketException("Connection closed prematurely");
            }
            totalBytesRcvd += bytesRcvd;
        } // data array is full
        System.out.println("Received: " + new String(data));
        socket.close();
    }
}
