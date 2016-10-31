package sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class SocketServer extends JFrame {
    
    JTextField pantalla;
    
     public SocketServer() {
		super();
		setSize(500, 500);
		setTitle("SocketClient");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);

		
		JPanel panel = (JPanel) this.getContentPane();
		panel.setLayout(new BorderLayout());

		pantalla = new JTextField("0", 20);
		pantalla.setBorder(new EmptyBorder(4, 4, 4, 4));
		pantalla.setFont(new Font("Arial", Font.BOLD, 25));
		pantalla.setHorizontalAlignment(JTextField.RIGHT);
		pantalla.setEditable(true);
		pantalla.setBackground(Color.WHITE);
		panel.add("North", pantalla);

		validate();
	}
 
   public static void main(String args[]) {
 
       Vector<String> datosRecibidos = new Vector<String>();
 
       try {
             ServerSocket s = new ServerSocket(1234);
             System.out.println("Esperando conexiones...");
                   
             while (true) {
                    Socket cliente = s.accept();
                    BufferedReader entrada = new BufferedReader(
                             new InputStreamReader(cliente.getInputStream()));
                    PrintWriter salida = new PrintWriter(
             new OutputStreamWriter(cliente.getOutputStream()),true);
                    String datos = entrada.readLine();
                    if (datos.equals("DATOS")){
                           for (int n=0; n<datosRecibidos.size(); n++ ){
                                  salida.println(datosRecibidos.get(n));
                           }
                    } else {
                           datosRecibidos.add(0, datos);
                           salida.println("OK");
                    }
                    cliente.close();
             }
       } catch (IOException e) {
             System.out.println(e);
       }
}
}
