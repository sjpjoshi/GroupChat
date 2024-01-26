import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Image;
import java.io.*;
import java.net.*;

public class User4 extends JFrame implements ActionListener, Runnable {
	
	 JPanel panel;
	 JTextField text;
	 JButton button;
	 static JTextArea textArea;
	    
	 BufferedWriter writer;
	 BufferedReader reader;
	
	 // makes the chat
	 User4() {
		
		 // this sets the panel at the top
		 panel = new JPanel();
		 panel.setLayout(null);
		 panel.setBackground(new Color(36, 36, 36)); 
		 panel.setBounds(0, 0, 450, 70);
	     add(panel);
	     
	     // This creates our icon to which it will allow us to exit the chat
	    JLabel label = new JLabel("");
	    Image icon = new ImageIcon(this.getClass().getResource("/menu.png")).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
	    label.setIcon(new ImageIcon(icon));
	    label.setBounds(5, 15, 25, 30);	     	    
	    panel.add(label);
	    
	    // this will allow us to exit out of the chat 
	    label.addMouseListener(new MouseAdapter(){
	        public void mouseClicked(MouseEvent ae)  {    	
	            System.exit(0);	            
	        } // mouseClicked
	    }); // addMouseListener
	    
	 
	    // this makes our label for the chat at the top
	    JLabel l4 = new JLabel("CSA GC");
	    l4.setFont(new Font("SAN_SERIF", Font.PLAIN, 30));
	    l4.setForeground(Color.WHITE);
	    l4.setBounds(150, 40, 120, 30);
	    panel.add(l4);   
	    
	    // phone icon
	    JLabel label2 = new JLabel("");
	    Image  call = new ImageIcon(this.getClass().getResource("/phone.png")).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
	    label2.setIcon(new ImageIcon(call));
	    label2.setBounds(300, 30, 130, 40);
	    panel.add(label2);
	    
	    // camera icon
	    JLabel label3 = new JLabel("");
	    Image  cam = new ImageIcon(this.getClass().getResource("/camera.png")).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
	    label3.setIcon(new ImageIcon(cam));
	    label3.setBounds(340, 30, 130, 40);
	    panel.add(label3);
	    
	    // memebers icon
	    JLabel label4 = new JLabel("");
	    Image  mem = new ImageIcon(this.getClass().getResource("/f.png")).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
	    label4.setIcon(new ImageIcon(mem));
	    label4.setBounds(390, 30, 130, 40);
	    panel.add(label4);
	    
	    // this makes us write in the text area
	    textArea = new JTextArea();
	    textArea.setBounds(5, 75, 440, 570);
	    textArea.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
	    textArea.setEditable(false);
	    textArea.setLineWrap(true);
	    textArea.setWrapStyleWord(true);
	    add(textArea);
	    
	    // this displays the text box
	    text = new JTextField();
	    text.setBounds(5, 655, 310, 40);
	    text.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
	    add(text);
	    
	    // this diplays the button with the action listener 
	    button = new JButton("Enter");
	    button.setBounds(320, 655, 123, 40);
	    button.setBackground(new Color(255,255,255));
	    button.setForeground(Color.BLUE);
	    button.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
	    button.addActionListener(this);
	    add(button);
	     
	    // just sets the chat to be visible
	    getContentPane().setBackground(Color.GRAY);
	    setLayout(null);
	    setSize(450, 700);
	    setLocation(1900, 200); 
	    setUndecorated(true);
	    setVisible(true);
		
	    // connects client to server
	    try {
	    	
	    	Socket client = new Socket("localhost", 2007);
	    	writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
	    	reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
	    	
	    } catch(Exception e) {} // catch
	    
	 } // User

	 // runs the program 
	 public static void main(String[] args) {
		 
		 User4 one = new User4();
		 Thread thread = new Thread(one);
		 thread.start();
		 
	 } // main
	 
	// this will display who said the message and then flush the message so there is no data kept
	public void actionPerformed(ActionEvent e) {

		String see = "user4\n" + text.getText();
		
		try {
			
			writer.write(see);
			writer.write("\r\n");
			writer.flush();
			
		} catch(Exception e1) {
			
			text.setText("");
			
		} // catch
		
	} // actionPerformed 
	
	// this will check the message and if there is something in the box it will send the message and add a new line
	public void run() {
		
		try {
			
			String message = "";
			while( (message = reader.readLine()) != null ) {
				
				textArea.append(message + "\n");
				
			} // while
			
		} catch (Exception e) {}
		
	} // run

		
} // class
