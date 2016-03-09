import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by 47767573t on 09/03/16.
 */
public class HiloServidor extends Thread {

    //Socket de escucha que recibimos al crear el hilo
    private Socket socket;
    //final String RUTA = "/pruebaDeEnvio.html";
    final String RUTA = "/home/47767573t/Gitprojects/M09ExamenFinal_UF03/src/pruebaDeEnvio.html";
    private Path pathRuta = Paths.get(RUTA);    //creamos objeto path (necesario para readAllBytes)

    //CONSTRUCTOR
    public HiloServidor(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try  {
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            // Array de bytes en el que almacenaremos el codigo HTML
            byte[] ficheroByte = new byte[10000000];
            inputStream.read(ficheroByte);

            // Pasamos los datos que extraemos del archivo a traves del outputStream
            outputStream.write(Files.readAllBytes(pathRuta));
            System.out.println("Archivo enviado ("+ficheroByte.length+")");

            // Cerramos los buffers y el socket
            inputStream.close();
            outputStream.close();
            socket.close();

        } catch (IOException ex){
            ex.printStackTrace();
        }
    }


}
