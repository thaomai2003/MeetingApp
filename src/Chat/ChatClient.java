package Chat;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChatClient extends JFrame implements Runnable{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatClient frame = new ChatClient();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	ServerSocket svSocket;
	Socket socket;
	DataOutputStream output;
	DataInputStream input;
	DefaultListModel model;
	JList lsHistory;


	/**
	 * Create the frame.
	 */
	public ChatClient() {
		
//		countComponents();
		model = new DefaultListModel();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 721, 474);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lsHistory = new JList();
		lsHistory.setBounds(38, 80, 659, 248);
		contentPane.add(lsHistory);
		
		JTextArea txtMessage = new JTextArea();
		txtMessage.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtMessage.setBounds(38, 341, 551, 59);
		contentPane.add(txtMessage);
		
		JButton btnSend = new JButton("SEND");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					output = new DataOutputStream(socket.getOutputStream());
					output.writeUTF(txtMessage.getText());
					output.flush();
					txtMessage.setText("");
				} catch (Exception e2) {
				}
			}
		});
		btnSend.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSend.setBounds(602, 338, 95, 62);
		contentPane.add(btnSend);
		
		JButton btnStart = new JButton("Chat Now");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					socket = new Socket(InetAddress.getByName("10.50.185.151"),8888);
					model.addElement("Client is connected");
					lsHistory.setModel(model);
					Thread t = new Thread(ChatClient.this);
					t.start();
					
				} catch (Exception e2) {
				}
				btnStart.disable();
			}
		});
		btnStart.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnStart.setBounds(293, 10, 129, 26);
		contentPane.add(btnStart);
		
	}
	
	@Override
	public void run() {
		try {
			input = new DataInputStream(socket.getInputStream());
			while (true) {
				if (socket != null) {
					model.addElement("Server say: " + input.readUTF());
					lsHistory.setModel(model);
				}
				Thread.sleep(1000);
			}
		} catch (Exception e) {
		}
		
	}
}
