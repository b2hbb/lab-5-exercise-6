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
			JFrame frame = new JFrame("Client application");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			JLabel textLabel = new JLabel("<html>"+ WindowMsg +"</html>",SwingConstants.CENTER);
			textLabel.setPreferredSize(new Dimension(300, 100)); 
			frame.getContentPane().add(textLabel, BorderLayout.CENTER);    
			frame.setLocationRelativeTo(null); 
			frame.pack();
			frame.setVisible(true);
			
			// Instantiate client socket
			DatagramSocket clientSocket = new DatagramSocket();

			// Get the IP address of the server
			InetAddress serverAddress = InetAddress.getByName("localhost");

			// Create buffer to send data
			byte sendingDataBuffer[] = new byte[bufferSize];

			// Convert data to bytes and store data in the buffer
			String sentence = "An attempt from UDP client";
			sendingDataBuffer = sentence.getBytes();

			// Creating a UDP packet 
			DatagramPacket sendingPacket = new DatagramPacket(sendingDataBuffer,
					sendingDataBuffer.length, serverAddress, SERVERPORT);

			
			WindowMsg = WindowMsg + "<br/>[+] Sending data to server [+]";
			textLabel.setText("<html>" +WindowMsg+ "</html>");

			// Sending UDP packet to the server
			clientSocket.send(sendingPacket);
			
			
			WindowMsg = WindowMsg + "<br/> [!] Sented: " + sentence + "[!]";
		    textLabel.setText("<html>" +WindowMsg+ "</html>");

			// Create buffer to receive data
			byte receivingDataBuffer [] = new byte[bufferSize];
			
			// Receive data packet from server
		    DatagramPacket receivingPacket = new DatagramPacket(receivingDataBuffer,
		    		receivingDataBuffer.length);
		    clientSocket.receive(receivingPacket);
		    
		
		    String dataSize = new String(receivingPacket.getData());

			JOptionPane.showMessageDialog(null, "Connection Success", "ATTENTION: " + "Success", JOptionPane.INFORMATION_MESSAGE);

			
			WindowMsg = WindowMsg + "<br/>[+] From server: " + dataSize + "[+]";
		    textLabel.setText("<html>" +WindowMsg+ "</html>");
			
			clientSocket.close();
			
			WindowMsg = WindowMsg + "<br/>[+] Client closed [+]";
		    textLabel.setText("<html>" +WindowMsg+ "</html>");
		} catch (Exception ex) {

			System.out.println("Durian Tunggal... we got problem");
			ex.printStackTrace();
		}

	}

}
