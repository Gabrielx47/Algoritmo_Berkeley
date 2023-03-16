import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.MulticastSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Slave extends Thread {
    private Clock clock;
    private String name; 

    Slave(int hour, int minute, String name){
        clock = new Clock(hour, minute);
        clock.start();
        this.name = name; 
    }


    @Override
    public void run() {
        System.out.println(name + ": recenbendo mensagem do Master\n");
        receiveMessageBroadCast(name);

        try {
            sendMessageTCP(clock.currentTime());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }   
    }

    public String getNameOfSlave() {
        return name;
    }

    public static void receiveMessageBroadCast(String nameOfSlave) {
        try {
            // Classe java para trabalhar com multicast ou broadcast
            MulticastSocket mcs = new MulticastSocket(6001);// porta como parametro
            byte rec[] = new byte[1024];
            DatagramPacket pkg = new DatagramPacket(rec, rec.length);

            System.out.println(nameOfSlave + ": Recenbendo mensagem!!");
            mcs.receive(pkg);// recebendo dados enviados via broadcast

            System.out.println(nameOfSlave + ": Já recebeu!!");
            String timeOfServer = new String(pkg.getData(), 0, pkg.getLength());
            System.out.println(nameOfSlave +": Hora do servidor: " + timeOfServer);
            mcs.close();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public static void sendMessageTCP(String time) throws UnknownHostException, IOException {
        Socket slaveSocket = new Socket("localhost", 6789);

        DataOutputStream outToServer = new DataOutputStream(slaveSocket.getOutputStream());

        outToServer.writeBytes(time + '\n');

        // O slave espera a reposta do Master, sendo que ela possui o valor para
        // a sincronização dos relógios
        BufferedReader inFromMasterServer = new BufferedReader(new
        InputStreamReader(slaveSocket.getInputStream()));
        String timeUpdateValue = inFromMasterServer.readLine();
        System.out.println("FROM SERVER: " + timeUpdateValue);
        slaveSocket.close();
    }
}
