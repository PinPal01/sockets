package sockets;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class SocketClient extends JFrame{
    
	JTextField pantalla;
        
    public SocketClient() {
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

    public static void main(String[] args) {
//        para conectarns desde otros equipos hay que agregar cambiar 
//        localhost por la ip del servidor
        String ip = "127.0.0.1";
        int puerto = 1234;
        System.out.println(" socket " + ip + " " + puerto);
        
        
        try {
            Socket sk = new Socket(ip, puerto);
            BufferedReader entrada = new BufferedReader(
                    new InputStreamReader(sk.getInputStream()));
            PrintWriter salida = new PrintWriter(
                    new OutputStreamWriter(sk.getOutputStream()), true);
            System.out.println("enviando...");
            salida.println("Nombre usuario: " + " " + "agrege su nombre aqui");
            System.out.println("recibiendo ... " + entrada.readLine());
            sk.close();
        } catch (Exception e) {
            System.out.println("error: " + e.toString());
        }
    }

}
