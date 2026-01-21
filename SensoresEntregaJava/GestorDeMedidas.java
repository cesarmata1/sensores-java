
import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.Socket;

public class GestorDeMedidas {
    public static void main(String[] args) throws Exception {
        DatagramSocket udpSocket = new DatagramSocket(5000);
        byte[] buffer = new byte[1024];
        
        while (true) {
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            udpSocket.receive(packet);
            String mensaje = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Gestor recibio: " + mensaje);
            
            String[] partes = mensaje.split(":");
            String tipo = partes[0];
            int medida = Integer.parseInt(partes[1]);
            
            if (tipo.equals("temperatura") && (medida < 19 || medida > 24)) {
                
                enviarATCP(new InetSocketAddress("210.1.1.78", 6000), String.valueOf(medida)); 
               
            } else if (tipo.equals("luz") && medida < 200) {
              
                enviarATCP(new InetSocketAddress("210.1.1.78", 7000), String.valueOf(medida)); 
                
            }
        }
    }

    private static void enviarATCP(InetSocketAddress address, String mensaje) throws IOException {
        try (Socket socket = new Socket(address.getAddress(), address.getPort());
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
            out.println(mensaje);
            System.out.println("Gestor envio a " + address.getPort() + ": " + mensaje);
        }
    }
}
