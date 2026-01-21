import java.net.*;
import java.io.*;

public class ActuadorTemperatura {
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(6000);
        System.out.println("Actuador de Temperatura listo en puerto " + server.getLocalPort());
        
        while (true) {
            try (Socket socket = server.accept();
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                String mensaje = in.readLine();
                System.out.println("Activador de Temperatura recibio: " + mensaje + "°C -> Activando climatizador");
            }
        }
    }
}
