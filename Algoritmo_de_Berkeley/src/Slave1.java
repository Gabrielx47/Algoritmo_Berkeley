import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.MulticastSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Slave1 {
    public static void receiveMessageBroadCast(){
        try {
            // Classe java para trabalhar com multicast ou broadcast
            MulticastSocket mcs = new MulticastSocket(6001);// porta como parametro
            byte rec[] = new byte[1024];
            DatagramPacket pkg = new DatagramPacket(rec, rec.length);

            System.out.println("Recenbendo mensagem!!");
            mcs.receive(pkg);// recebendo dados enviados via broadcast

            System.out.println("Já recebeu!!");
            String data = new String(pkg.getData(), 0, pkg.getLength());
            System.out.println("Hora do servidor: " + data);
            mcs.close();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public static void sendMessageTCP(String time) throws UnknownHostException, IOException{
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        
        Socket clientSocket = new Socket("localhost", 6789); 

        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

        //BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        
        String sentence = time;
        outToServer.writeBytes(sentence + '\n');
        //modifiedSentence = inFromServer.readLine();
        //System.out.println("FROM SERVER: " + modifiedSentence);
        clientSocket.close();
    }

    public static void main(String[] args) throws UnknownHostException, IOException {
        Clock clock = new Clock(4, 1);
        clock.start();
        
        System.out.println("receiveMessageBroadCast()");
        receiveMessageBroadCast();

        System.out.println("sendMessageTCP(clock.currentTime());");
        sendMessageTCP(clock.currentTime());
    }
}
