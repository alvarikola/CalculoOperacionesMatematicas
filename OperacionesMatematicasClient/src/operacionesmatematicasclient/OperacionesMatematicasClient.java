package operacionesmatematicasclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class OperacionesMatematicasClient {

    public static void main(String[] args) {
        String host = "localhost";
        int port = 1234;
        
        
        try {
            //Conectarse al servidor
            Socket socket = new Socket(host, port);
            System.out.println("Conectado al servidor " + host + " en el puerto " + port + ".");
            
            // Enviarle un mensaje
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            String respuesta = "3+5";
            System.out.println(respuesta);
            output.println(respuesta);

            String answer = input.readLine();
            System.out.println("La respuesta del servidor es: " + answer); 
                
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
