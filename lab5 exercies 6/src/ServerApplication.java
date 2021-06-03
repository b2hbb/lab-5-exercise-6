
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.awt.*; 
import javax.swing.*;


public class ServerApplication {

	public static void main(String[] args) {
		
		
		// Server UDP socket runs at this port
		final int serverPort=50002;
		
		try {

			String WindowMsg = "[+] Starting server [+]";
			JFrame frame = new JFrame("Server application");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			JLabel textLabel = new JLabel("<html>"+WindowMsg+"</html>",SwingConstants.CENTER);  
			textLabel.setPreferredSize(new Dimension(300, 100));
			frame.getContentPane().add(textLabel, BorderLayout.CENTER);      
			frame.setLocationRelativeTo(null);
			frame.pack();
			frame.setVisible(true);

			
		    DatagramSocket serverSocket = new DatagramSocket(serverPort);
		  
		    byte receivingDataBuffer[] = new byte[1024];
		    
	
		    DatagramPacket inputPacket = new DatagramPacket(receivingDataBuffer, receivingDataBuffer.length);

			
			WindowMsg = WindowMsg + "<br/>[+] Waiting for client to connect [+]";
		    textLabel.setText("<html>" +WindowMsg+ "</html>");
		    
		
		    serverSocket.receive(inputPacket);
		    
		  
		    String receivedData = new String(inputPacket.getData());

			
			WindowMsg = WindowMsg + "<br/>[+] Received: " + receivedData +"[+]";
		    textLabel.setText("<html>" +WindowMsg+ "</html>");
		    
			
			String trim = receivedData.trim();
			int receivedDataSize = 0;

			
			if(trim.isEmpty()){
				textLabel.setText("<html>" +WindowMsg + "<br/>[x] Empty data received [x]"+ "</html>");
			}
			else{
			
				receivedDataSize = trim.split("\\s+").length;
			}

		    // Process data - convert to upper case
		    String sendingData = "String size: " + String.valueOf(receivedDataSize);
		    
		    // Creating corresponding buffer to send data
		    byte sendingDataBuffer[] = sendingData.getBytes();
		    
		    // Get client's address
		    InetAddress senderAddress = inputPacket.getAddress();
		    int senderPort = inputPacket.getPort();
		    
		   
		    DatagramPacket outputPacket = new DatagramPacket(sendingDataBuffer, 
		    		sendingDataBuffer.length, senderAddress,senderPort);
		    
		    // Send the created packet to client
		    serverSocket.send(outputPacket);

			
		    WindowMsg = WindowMsg + "<br/>[+] Sent to the client: " + sendingData + "[+]";
		    textLabel.setText("<html>" +WindowMsg+ "</html>");

		 
		    serverSocket.close();

			
		    WindowMsg = WindowMsg + "<br/>[+] Server closed [+]";
		    textLabel.setText("<html>" +WindowMsg+ "</html>");
		      
		} catch (Exception ex) {
			
			System.out.println("Durian Tunggal... we got problem");
			ex.printStackTrace();
		}

	}

}
