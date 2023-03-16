import java.io.IOException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) throws IOException {
        /*Clock clock = new Clock(7, 20);

        clock.start();

        System.out.println("Hora atual: " + clock.currentTime());
        for(int i = 0; i < 10; i++) {
            System.out.println("hour: " + clock.getHour() + "h:"+ clock.getMinute());

            if(i == 5) {
                clock = new Clock(8, 0);
                clock.start();
                System.out.println("hour: " + clock.getHour() + "h:" + clock.getMinute());
                System.out.println("Mudei a hora!!");
                
            }
        }*/
            
            Clock clock = new Clock(1, 1);
            clock.start();
            Scanner scan = new Scanner(System.in);
            Master masterServer = new Master();

            System.out.println("masterServer.sendMessageBroadcast();");
            masterServer.sendMessageBroadcast(clock);

            scan.nextLine();
            
            System.out.println("masterServer.socketServer();");
            masterServer.socketServer();
    }
}