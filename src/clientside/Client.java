
package clientside;

import java.io.*;
import java.net.*;


/**
 *
 * @author naqee
 */
public class Client {
    
    protected static ObjectInputStream in;
    protected static ObjectOutputStream out;
    private Socket socket;
    private String response;
    
    public Client() {
        try{
            socket = new Socket("127.0.0.1", 6666);
        } catch (IOException e){
            
        }
        
    }
    
    //objects constructed for the data transfer
    private void getStreams() {
        try{
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e){
            
        }
    }
    
    //setting up the streams bec the connections was established
    public void communicate() {
        try {
            getStreams();
            do {
                response = (String) in.readObject();
            } while (!response.equalsIgnoreCase("UPDATE"));
        } catch (IOException e) {
            
        } catch (ClassNotFoundException e) {
           
        } 
    }
    
    //closing the socket and the streams
    private void closeConnection(){
        try{
            in.close();
            out.close();
            socket.close();
        } catch (IOException e){
            
        }
    }
}


