import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.awt.*; 
import javax.swing.*;



public class ClientApplication {

	public static void main(String[] args) {
		
		final int SERVERPORT = 50002;

		int bufferSize = 1024;

		try {
			String WindowMsg = "[+] Starting Client [+]";
			JFrame frame = new JFrame("Client application");//Window
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Set how to close
			JLabel textLabel = new JLabel("<html>"+ WindowMsg +"</html>",SwingConstants.CENTER);//Window information as label
			textLabel.setPreferredSize(new Dimension(300, 100)); //Set the label size
			frame.getContentPane().add(textLabel, BorderLayout.CENTER);//Display the window.       
			frame.setLocationRelativeTo(null); //how to place the elements within the window
			frame.pack();//Prepare all graphics elements together
			frame.setVisible(true);//Show the window
			
		
			DatagramSocket clientSocket = new DatagramSocket();

			InetAddress serverAddress = InetAddress.getByName("localhost");

		
			byte sendingDataBuffer[] = new byte[bufferSize];

	
			String sentence = "An attempt from UDP client";
			sendingDataBuffer = sentence.getBytes();

			// Creating a UDP packet 
			DatagramPacket sendingPacket = new DatagramPacket(sendingDataBuffer,
					sendingDataBuffer.length, serverAddress, SERVERPORT);

		
			WindowMsg = WindowMsg + "<br/>[+] Sending data to server [+]";
			textLabel.setText("<html>" +WindowMsg+ "</html>");

		
			clientSocket.send(sendingPacket);
			
		
			WindowMsg = WindowMsg + "<br/> [!] Sented: " + sentence + "[!]";
		    textLabel.setText("<html>" +WindowMsg+ "</html>");

		
			byte receivingDataBuffer [] = new byte[bufferSize];
			
		
		    DatagramPacket receivingPacket = new DatagramPacket(receivingDataBuffer,
		    		receivingDataBuffer.length);
		    clientSocket.receive(receivingPacket);
	
		    String dataSize = new String(receivingPacket.getData());

		
			WindowMsg = WindowMsg + "<br/>[+] From server: " + dataSize + "[+]";
		    textLabel.setText("<html>" +WindowMsg+ "</html>");
			// Closing the socket connection with the server
			clientSocket.close();
			
			WindowMsg = WindowMsg + "<br/>[+] Client closed [+]";
		    textLabel.setText("<html>" +WindowMsg+ "</html>");
		} catch (Exception ex) {

			System.out.println("Durian Tunggal... we got problem");
			ex.printStackTrace();
		}

	}

}
