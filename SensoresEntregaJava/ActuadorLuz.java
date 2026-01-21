import java.net.*;
import java.io.*;

public class ActuadorLuz {
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(7000);
        System.out.println("Actuador de Luz listo en puerto " + server.getLocalPort());
        
        while (true) {
            try (Socket socket = server.accept();
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                String mensaje = in.readLine();
                System.out.println("Actuador Luz recibio: " + mensaje + " lux -> Encendiendo luz");
            }
        }
    }
}
