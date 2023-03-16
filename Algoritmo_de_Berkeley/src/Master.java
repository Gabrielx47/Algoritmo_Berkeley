import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Master {

    Master(){
    }

    public void sendMessageBroadcast(Clock clock){
        try {
            if(clock.isAlive()){
                System.out.println(clock.getName() + ": vivo");
            } else {
                System.out.println("Thread do clock: morto");
            } 
            // byte[] b = "Testando Broadcast".getBytes();
            byte[] b = clock.currentTime().getBytes();
            
            // Definindo o endere�o de envio do pacote neste caso o endere�o de broadcast
            InetAddress addr = InetAddress.getByName("255.255.255.255");
            DatagramPacket pkg = new DatagramPacket(b, b.length, addr, 6001);
            DatagramSocket ds = new DatagramSocket();

            System.out.println("ds.send(pkg)");
            ds.send(pkg);// enviando pacote broadcast

            System.out.println("Close connection !!");
            //ds.close();
        } catch (Exception e) {
            System.out.println("Nao foi possivel enviar a mensagem");
        }
    }

    public void socketServer () throws IOException{
        String clientSentence;
        //String capitalizedSentence;
        ServerSocket welcomeSocket = new ServerSocket(6789);

        while (true) {
            Socket connectionSocket = welcomeSocket.accept();
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            clientSentence = inFromClient.readLine();

            System.out.println("Hora do slave: " + clientSentence);
            
            connectionSocket.close();
            /*DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            capitalizedSentence = clientSentence.toUpperCase() + '\n';
            outToClient.writeBytes(capitalizedSentence);*/
        }
        //welcomeSocket.close();
    }
}
