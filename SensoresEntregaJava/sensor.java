import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Random;
import java.util.Scanner;

public class sensor {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress address = InetAddress.getByName("210.1.1.78"); 
        int puertoGestor = 5000;
        Random rand = new Random();
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("¿Quieres generar una nueva medida? (si/no): ");
            String respuesta = scanner.nextLine();
            
            if (respuesta.equals("no")) {
                System.out.println("Cerrando el programa...");
                break;  
            }
            
            if (respuesta.equals("si")) {
                String tipo;
                if (rand.nextBoolean()) {
                    tipo = "temperatura";
                } else {
                    tipo = "luz";
                }
                
                int medida;
                if (tipo.equals("temperatura")) {
                    medida = 15 + rand.nextInt(15);
                } else {
                    medida = rand.nextInt(500);
                }
                
                String mensaje = tipo + ":" + medida;
                byte[] buffer = mensaje.getBytes();
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, puertoGestor);
                socket.send(packet);
                System.out.println("Sensor envio: " + mensaje);
            } else {
                System.out.println("Respuesta no valida. Por favor, ingresa 'si' o 'no'.");
            }
        }
        
        socket.close();
        scanner.close();
    }
}
