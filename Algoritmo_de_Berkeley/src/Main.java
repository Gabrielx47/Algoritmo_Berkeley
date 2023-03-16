import java.io.IOException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {        
            Clock clock = new Clock(1, 1);
            clock.start();
            Scanner scan = new Scanner(System.in);
            Master masterServer = new Master();
            Slave slave1 = new Slave(7, 0, "slave1");
            Slave slave2 = new Slave(12, 0, "slave2");

            slave1.start();
            slave2.start();
            System.out.println("masterServer.sendMessageBroadcast();");
            masterServer.sendMessageBroadcast(clock);
            
            

            System.out.println("masterServer.socketServer();");
            masterServer.socketServer();
    }
}