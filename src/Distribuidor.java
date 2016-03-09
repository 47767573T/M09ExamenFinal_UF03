import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by 47767573t on 09/03/16.
 */
public class Distribuidor {

    public static void main(String[] args)  {

        try  {
            // Creamos nuestro ServerSocket y le damos una dirección
            ServerSocket serverSocket = new ServerSocket();
            InetSocketAddress addrs = new InetSocketAddress("0.0.0.0", 9090);
            serverSocket.bind(addrs);
            System.out.println("Escuchando....");

            // Creamos el hilo del servidor y le enviamos el socket
            Socket socket = serverSocket.accept();
            System.out.println("...conexion aceptada");
            HiloServidor thread = new HiloServidor(socket);
            thread.start();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
