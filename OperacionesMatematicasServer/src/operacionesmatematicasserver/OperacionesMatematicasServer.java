package operacionesmatematicasserver;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class OperacionesMatematicasServer {

    public static void main(String[] args) throws Exception {
        int port = 1234;
        
        try {
            ServerSocket server = new ServerSocket(port);
            System.out.println("Servidor iniciado en el puerto " + port + ".");

            
            // Aceptar una conexion
            Socket client = server.accept();
            System.out.println("Cliente conectado: " + client.getInetAddress());

            // Leer y responder al cliente
            BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter output = new PrintWriter(client.getOutputStream(), true);


            String message = input.readLine();
            System.out.println("Se ha recibido este mensaje: " + message);



            String answer = evaluarOperacion(message);
            

            output.println(answer);  
        } catch (IOException ex) {
        ex.printStackTrace();
        } 
    }

    
    private static String evaluarOperacion(String message) throws Exception {
        try {
            // Parsear la operación manualmente
            String[] partes;
            if (message.contains("+")) {
                partes = message.split("\\+");
                return String.valueOf(Double.parseDouble(partes[0].trim()) + Double.parseDouble(partes[1].trim()));
            } else if (message.contains("-")) {
                partes = message.split("-");
                return String.valueOf(Double.parseDouble(partes[0].trim()) - Double.parseDouble(partes[1].trim()));
            } else if (message.contains("*")) {
                partes = message.split("\\*");
                return String.valueOf(Double.parseDouble(partes[0].trim()) * Double.parseDouble(partes[1].trim()));
            } else if (message.contains("/")) {
                partes = message.split("/");
                if (Double.parseDouble(partes[1].trim()) == 0) {
                    throw new Exception("División por cero no permitida");
                }
                return String.valueOf(Double.parseDouble(partes[0].trim()) / Double.parseDouble(partes[1].trim()));
            } else {
                throw new Exception("Operación no soportada");
            }
        } catch (NumberFormatException e) {
            throw new Exception("Formato de número no válido");
        }
    }
    
}



