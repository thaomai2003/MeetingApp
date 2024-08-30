package Server;

import java.awt.EventQueue;
import java.awt.Image;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.github.sarxos.webcam.Webcam;

import Chat.ChatClient;
import Chat.ChatServer;
import switchButton.*;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.Panel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Color;
import javax.swing.Timer;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.GroupLayout.Alignment;

import java.awt.SystemColor;
import javax.swing.border.MatteBorder;

public class Client extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static JLabel img_client;
	public static JLabel img_client_1;
	public static ServerSocket serverSocket;
	static DataOutputStream output;
	static DataInputStream input;	
	static Socket s;
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FlatRobotoFont.install();
			        FlatLaf.registerCustomDefaultsSource("raven.themes");
			        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
			        FlatMacLightLaf.setup();
					Client frame = new Client();
			        frame.setUndecorated(true);
			        frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		s = new Socket(InetAddress.getByName("192.168.1.30"),7805);
		ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
		ObjectInputStream in = new ObjectInputStream(s.getInputStream());
		ImageIcon ic;
		ImageIcon ic1;
		BufferedImage br;
	    Webcam webcam = Webcam.getDefault();
	    webcam.open();	
		while (true) {
			br = webcam.getImage();
			ic = new ImageIcon(br);
			ic1 = (ImageIcon) in.readObject();
			out.writeObject(ic);
			out.flush();   
		    Image img = ic1.getImage();
		    Image scaledImg = img.getScaledInstance(img_client.getWidth(), img_client.getHeight(), Image.SCALE_SMOOTH);
			ic1 = new ImageIcon(scaledImg);
			img_client.setIcon(ic1);
			img_client_1 .setIcon(ic);
		}
	}

	/**
	 * Create the frame.
	 */
	public Client() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(1, 100, 950, 712);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setBackground(SystemColor.inactiveCaptionText);
		contentPane.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 240, 245));
		panel_2.setBounds(245, 0, 774, 675);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		MyButton btnLeave = new MyButton();
		btnLeave.setBounds(251, 621, 47, 44);
		panel_2.add(btnLeave);
		btnLeave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
			        System.exit(0); 
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnLeave.setIcon(new ImageIcon("C:\\Users\\hoang\\Downloads\\exit.png"));
		btnLeave.setBorderPainted(false);
		btnLeave.setRadius(500);
						
		MyButton btnNewButton = new MyButton();
		btnNewButton.setBounds(80, 621, 47, 44);
		panel_2.add(btnNewButton);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setRadius(500);

		btnNewButton.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        // Kiểm tra xem sự kiện có được kích hoạt bởi nút hay không
		        if (e.getSource() == btnNewButton) {
		            // Lấy trạng thái hiện tại của nút
		            boolean isButtonEnabled = btnNewButton.isEnabled();

		            // Đảo trạng thái của nút
		            btnNewButton.setEnabled(!isButtonEnabled);
		        }
		    }
						});
						
								
								
								
		
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\hoang\\Downloads\\microphone-black-shape.png"));
		MyButton btnNewButton_4 = new MyButton();
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int option = JOptionPane.showConfirmDialog(null, "Join screen sharing?"
						+ "", "Confirm Registration", JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) {
					new SwingWorker<Void, Void>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                    	ClientWindow clientWindow = new ClientWindow();
                        clientWindow.setVisible(true);
                        new Thread(new GetScreenCaptureStreamThread(clientWindow.clientScreen)).start();
                        return null;
                    }
                }.execute();
            }
		
			}
		});
		btnNewButton_4.setBounds(137, 621, 47, 44);
		panel_2.add(btnNewButton_4);
		btnNewButton_4.setIcon(new ImageIcon("C:\\Users\\hoang\\Downloads\\upload.png"));
		btnNewButton_4.setBorderPainted(false);
		btnNewButton_4.setRadius(500);
		MyButton btnNewButton_1_1_1_1 = new MyButton();
		btnNewButton_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			ChatClient ChatClient = new Chat.ChatClient();
			ChatClient.setUndecorated(true);
			ChatClient.setVisible(true);
			ChatClient.setLocationRelativeTo(null);
			ChatClient.setBackground(Color.GRAY);
			}
		});
		btnNewButton_1_1_1_1.setBounds(194, 621, 47, 44);
		panel_2.add(btnNewButton_1_1_1_1);
		btnNewButton_1_1_1_1.setIcon(new ImageIcon("C:\\Users\\hoang\\Downloads\\chat.png"));
		btnNewButton_1_1_1_1.setBorderPainted(false);
		btnNewButton_1_1_1_1.setRadius(1000);
		img_client = new JLabel("");
		img_client.setBackground(Color.GRAY);
		img_client.setFont(new Font("Tahoma", Font.PLAIN, 13));
		img_client.setBounds(49, 10, 594, 423);
		img_client.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

		panel_2.add(img_client);
		
		
		
		MyButton btnCam = new MyButton();
		btnCam.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCam.setBounds(23, 621, 47, 44);
		panel_2.add(btnCam);
		btnCam.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		        // Lấy trạng thái hiện tại của img_server
//		        boolean isImgServerVisible = img_client.isVisible();

		        // Đảo trạng thái hiển thị của img_server
//		        img_client.setVisible(!isImgServerVisible);
		     
		    }
		});
		
				btnCam.setIcon(new ImageIcon("C:\\Users\\hoang\\Downloads\\camera.png"));
				btnCam.setBorderPainted(false);
				btnCam.setRadius(500);
				
				img_client_1 = new JLabel("");
				img_client_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
				img_client_1.setBackground(Color.GRAY);
				img_client_1.setBounds(269, 443, 373, 151);
				img_client_1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

				panel_2.add(img_client_1);
		

				Menu2 menuPanel = new Menu2();
				GroupLayout groupLayout = (GroupLayout) menuPanel.getLayout();
				groupLayout.setHorizontalGroup(
					groupLayout.createParallelGroup(Alignment.LEADING)
						.addGap(0, 246, Short.MAX_VALUE)
				);
				groupLayout.setVerticalGroup(
					groupLayout.createParallelGroup(Alignment.LEADING)
						.addGap(0, 675, Short.MAX_VALUE)
				);
				menuPanel.setBounds(0, 0, 246, 675);
				contentPane.add(menuPanel);
				
				JLabel lblMain_1 = new JLabel("Main");
				lblMain_1.setForeground(new java.awt.Color(255, 255, 255));
				lblMain_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
				lblMain_1.setBounds(10, 102, 105, 34);

			        // Tạo viền xám xung quanh JLabel
			    Border border = BorderFactory.createLineBorder(Color.GRAY, 2);
					      
					      JLabel lblMain_2_1 = new JLabel("Web App");
					      lblMain_2_1.setForeground(new java.awt.Color(255, 255, 255));
lblMain_2_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
					      lblMain_2_1.setBounds(10, 362, 105, 34);
		          
		          JLabel lblMain = new JLabel("Main");
		          lblMain.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
		          lblMain.setForeground(new java.awt.Color(255, 255, 255));
		          lblMain.setBounds(10, 10, 105, 34);
		          menuPanel.add(lblMain);
		          menuPanel.add(lblMain);
				
								
				JLabel lblMain_2 = new JLabel("Web App");
				lblMain_2.setForeground(new java.awt.Color(255, 255, 255));
				lblMain_2.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
				lblMain_2.setBounds(10, 91, 105, 34);
				menuPanel.add(lblMain_2);
								
				 JLabel lblNewLabel_1 = new JLabel("Email");
				   lblNewLabel_1.setForeground(new java.awt.Color(255, 255, 255));
				   lblNewLabel_1.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
				   lblNewLabel_1.setBackground(Color.LIGHT_GRAY);
				   lblNewLabel_1.setBounds(31, 123, 276, 45);
				   menuPanel.add(lblNewLabel_1);
				   JLabel lblNewLabel_1_1 = new JLabel("Chat");
				   lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
				   lblNewLabel_1_1.setForeground(new java.awt.Color(255, 255, 255));
				   lblNewLabel_1_1.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
				   lblNewLabel_1_1.setBackground(Color.LIGHT_GRAY);
				   lblNewLabel_1_1.setBounds(31, 182, 276, 45);
				   menuPanel.add(lblNewLabel_1_1);
				   
				   JLabel lblNewLabel_1_2 = new JLabel("Calendar");
				   lblNewLabel_1_2.setIcon(null);
				   lblNewLabel_1_2.setForeground(new java.awt.Color(255, 255, 255));
				   lblNewLabel_1_2.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
				   lblNewLabel_1_2.setBackground(Color.LIGHT_GRAY);
				   lblNewLabel_1_2.setBounds(31, 240, 276, 45);
				   menuPanel.add(lblNewLabel_1_2);
				   
				   JLabel lblMain_2_2 = new JLabel("Component");
				   lblMain_2_2.setForeground(new java.awt.Color(255, 255, 255));
				   lblMain_2_2.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
				   lblMain_2_2.setBounds(10, 291, 105, 34);
				   menuPanel.add(lblMain_2_2);
	   		       
	   		       JLabel lblNewLabel_1_3 = new JLabel("Advanced UI");
	   		       lblNewLabel_1_3.setForeground(new java.awt.Color(255, 255, 255));
	   		       lblNewLabel_1_3.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
	   		       lblNewLabel_1_3.setBackground(Color.LIGHT_GRAY);
	   		       lblNewLabel_1_3.setBounds(29, 326, 276, 45);
	   		       
	   		       menuPanel.add(lblNewLabel_1_3);
	   		       
	   		       JLabel lblNewLabel_1_1_1 = new JLabel("Content");
	   		       lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
	   		       lblNewLabel_1_1_1.setForeground(new java.awt.Color(255, 255, 255));
	   		       lblNewLabel_1_1_1.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
	   		       lblNewLabel_1_1_1.setBackground(Color.LIGHT_GRAY);
lblNewLabel_1_1_1.setBounds(28, 382, 276, 45);
	   		       
	   		       menuPanel.add(lblNewLabel_1_1_1);
				
				JLabel lblNewLabel = new JLabel(" Dashboard");
				lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
				lblNewLabel.setBackground(Color.LIGHT_GRAY);
				lblNewLabel.setForeground(new java.awt.Color(255, 255, 255));
				lblNewLabel.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
				lblNewLabel.setBounds(27, 42, 206, 45);
				lblNewLabel.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
				menuPanel.add(lblNewLabel);						          						
}
}
