/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Marinelly R
 */
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Hashtable;
import java.util.Vector;
import javax.microedition.io.Connector;
import javax.microedition.io.SocketConnection;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.List;
import javax.microedition.location.Coordinates;
import javax.microedition.location.Criteria;
import javax.microedition.location.LocationException;
import javax.microedition.location.LocationProvider;



public class Services extends Thread{
 protected InputStream instream;
  protected SocketConnection sockConn;
    protected OutputStream outstream;

    //String serv_address = "192.168.0.10";
   String serv_address;
    String serv_port;
    private List sugerencias;
    private Vector indArbol;
    private Vector indBD;
    

    public  Services(String ip, String puerto, List sugerencias, Vector indArbol, Vector indBD){
        this.serv_address = ip;
        this.serv_port = puerto;
        this.sugerencias = sugerencias;
        this.indArbol = indArbol;
        this.indBD = indBD;

        if(sockConn == null){
            try
            {
                  sockConn = (SocketConnection) Connector.open("socket://" + serv_address + ":" + serv_port);
            }catch(IOException ex){
              System.out.println("Could not create connection: " + ex);
            }
            try
            {
              sockConn.setSocketOption(SocketConnection.KEEPALIVE, 1);

            }catch(IOException ex)
            {
              System.out.println("Could not set socket option: " + ex);
            }
            try{
                outstream = sockConn.openOutputStream();

            }catch(IOException ex){
             System.out.println("Could not open socket stream: " + ex);
            }
        }

        this.start();
       
    }

    public void run()
    {
        try {
            instream = sockConn.openInputStream();
            String nm_Exhibicion;
            String indice;
            while (true) {
                try {
                    int b = -1;
                
                    while ((b = instream.read()) != 10) {

                        nm_Exhibicion = "";
                        while (b != '/') {
                            nm_Exhibicion = nm_Exhibicion + (char) b;
                           
                            b = instream.read();
                        }
                         b = instream.read();
                        indice="";
                          while (b != '/') {
                            indice = indice + (char) b;
                            b = instream.read();
                          }
                        indArbol.addElement(indice);
                        if(indice.equals("-2"))
                         sugerencias.append(nm_Exhibicion, null);
                        else
                          sugerencias.append(nm_Exhibicion, Image.createImage("sug.png"));

                        indice="";
                        b = instream.read();
                          while (b != '@') {
                            indice = indice + (char) b;
                            b = instream.read();
                          }
                        indBD.addElement(indice);

                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void sendFix(String location){
        location += "\n";
         sugerencias.deleteAll();
        indArbol.removeAllElements();
        indBD.removeAllElements();

        // get the payload
        byte[] data = location.getBytes();

        System.out.println(location);

        try
        {
            outstream.write(data);
        }catch(IOException ex){
         System.out.println("Could not write to socket stream: " + ex);
        }

    }
public void stopConnection(){

        /*Send the termination message to the server to notify the end    of the connection*/

        String end_str = "end";
        byte[] data = end_str.getBytes();

        System.out.println(data);

        try
        {
            outstream.write(data);
        }catch(IOException ex){
            System.out.println("Could not write to socket stream: " + ex);
        }

         try
        {
            outstream.close();
            sockConn.close();
            }catch(IOException ex){
                System.out.println("Could not write to socket stream: " + ex);
            }


    }
    public Hashtable CargarExhibiciones(){
             Hashtable exhibiciones = new Hashtable();
             exhibiciones.put("León", "León (Panthera leo)Es un mamífero carnívoro de la familia de los félidos y una de las cuatro especies del género Panthera");
             exhibiciones.put("Cebra", "Cebra, mamífero originario de África muy conocido por su pelaje rayado característico. Es más pequeño que su pariente el caballo y muy parecido en aspecto y hábitos al asno salvaje");
             exhibiciones.put("Elefante", "Elefante, mamífero placentario del orden Proboscidea. Existen hoy en día tres especies y diversas subespecies. Los elefantes son los animales terrestres más grandes que existen actualmente.");
             exhibiciones.put("Papión", "El Papión Sagrado (Papio hamadryas) es una de las especies en los Monos del Viejo Mundo. El Papión Sagrado es natural de África.");
             exhibiciones.put("Suricata", "Suricata, o suricato, es el nombre de un pequeño mamífero, miembro de la familia de la mangosta (Herpestidae), que habita la región del desierto de Kalahari y el Namib en África.");
             exhibiciones.put("Avestruz", "El avestruz, es una gran ave no voladora propia de África. Es el ave actual más grande y más pesada; puede alcanzar los 3 metros de altura, y pesar unos 180 kg.");
             exhibiciones.put("7", "");
             exhibiciones.put("8", "");
             exhibiciones.put("9", "");
             exhibiciones.put("10", "");
             exhibiciones.put("11", "");
             exhibiciones.put("12", "");
             exhibiciones.put("13", "");
             exhibiciones.put("14", "");
             exhibiciones.put("15", "");
             exhibiciones.put("16", "");
             exhibiciones.put("17", "");
             exhibiciones.put("18", "");
             exhibiciones.put("19", "");
             exhibiciones.put("20", "");



             return exhibiciones;
    }

    public Vector getIndices() {
        return indArbol;
    }

    public void setIndices(Vector indices) {
        this.indArbol = indices;
    }

    public List getSugerencias() {
        return sugerencias;
    }

    public void setSugerencias(List sugerencias) {
        this.sugerencias = sugerencias;
    }
}
