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
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChatServer extends JFrame implements Runnable{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatServer frame = new ChatServer();
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
	public ChatServer() {
		
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
					model.addElement("Server is connecting........");
					lsHistory.setModel(model);
					svSocket = new ServerSocket(8888);
					socket = svSocket.accept();
					model.addElement("Server is connected");
					lsHistory.setModel(model);
					Thread t = new Thread(ChatServer.this);
					t.start();

				} catch (Exception e2) {
				}
				btnStart.disable();

			}
		});
		btnStart.setFont(new java.awt.Font("sansserif", 1, 15));
		btnStart.setBounds(305, 10, 118, 26);
		contentPane.add(btnStart);
	}

	@Override
	public void run() {
		try {
			input = new DataInputStream(socket.getInputStream());
			while (true) {
				if (socket != null) {
					model.addElement("Client say: " + input.readUTF());
					lsHistory.setModel(model);
				}
				Thread.sleep(1000);
			}
		} catch (Exception e) {
		}
		
	}
}
