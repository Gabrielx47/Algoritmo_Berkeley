import java.net.DatagramPacket;
import java.net.MulticastSocket;

public class Slave2 {
    public static void receiveMessageBroadCast() {
        try {
            // Classe java para trabalhar com multicast ou broadcast
            MulticastSocket mcs = new MulticastSocket(6001);// porta como parametro
            byte rec[] = new byte[256];
            DatagramPacket pkg = new DatagramPacket(rec, rec.length);
            mcs.receive(pkg);// recebendo dados enviados via broadcast
            String data = new String(pkg.getData(), 0, pkg.getLength());
            System.out.println("Hora do servidor: " + data);
            mcs.close();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        System.out.println("receiveMessageBroadCast()");
        receiveMessageBroadCast();
    }
}
