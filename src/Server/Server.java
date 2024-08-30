package Server;

import java.awt.EventQueue;
import java.awt.Image;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
import javax.swing.border.MatteBorder;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.github.sarxos.webcam.Webcam;

import Chat.ChatServer;
import raven.toast.Notifications;
import switchButton.*;
import test.CamPre;

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
import java.awt.AWTException;
import java.awt.Color;
import javax.swing.Timer;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.GroupLayout.Alignment;

import java.awt.SystemColor;

public class Server extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static JLabel img_server;
	public static JLabel img_server_1;
public static ServerSocket serverSocket;
	static DataOutputStream output;
	static DataInputStream input;	
	static Socket s;
    private CamPre camPre;

	/**
	 * Launch the application.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FlatRobotoFont.install();
			        FlatLaf.registerCustomDefaultsSource("raven.themes");
			        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
			        FlatMacLightLaf.setup();
					Server frame = new Server();
			        frame.setUndecorated(true);
			        frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		serverSocket = new ServerSocket(7805);
		Socket socket = serverSocket.accept();
		
		ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
		ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
		
		Webcam webcam = Webcam.getDefault();
		webcam.open();
		
		while (true) {
	        BufferedImage br = webcam.getImage();
			ImageIcon ic1;
			ic1 = new ImageIcon(br);
			out.writeObject(ic1);
			out.flush();
			
			ImageIcon ic;
			ic = (ImageIcon) in.readObject();
		    Image img = ic.getImage();
			Image scaledImg = img.getScaledInstance(img_server.getWidth(), img_server.getHeight(), Image.SCALE_SMOOTH);
			ic = new ImageIcon(scaledImg);

			Image img1 = ic1.getImage();
			Image scaledImg1 = img1.getScaledInstance(img_server_1.getWidth(), img_server_1.getHeight(), Image.SCALE_SMOOTH);
			ic1 = new ImageIcon(scaledImg1);

			img_server.setIcon(ic);
			img_server_1.setIcon(ic1);
			}
		}

	public Server() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 670);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setBackground(SystemColor.inactiveCaptionText);
		contentPane.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 240, 245));
		panel_2.setBounds(239, 0, 774, 675);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		MyButton btnLeave = new MyButton();
		btnLeave.setFont(new Font("SansSerif", Font.PLAIN, 10));
		btnLeave.setBounds(251, 610, 47, 44);
		panel_2.add(btnLeave);
		btnLeave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					serverSocket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		        System.exit(0); 

			}
		});
		btnLeave.setIcon(new ImageIcon("C:\\Users\\hoang\\Downloads\\logout.png"));
		btnLeave.setBorderPainted(false);
		btnLeave.setRadius(500);
		MyButton btnNewButton = new MyButton();
		
		btnNewButton.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        if (e.getSource() == btnNewButton) {
		            boolean isButtonEnabled = btnNewButton.isEnabled();
		            btnNewButton.setEnabled(!isButtonEnabled);
		        }
		    }
			});
						
		btnNewButton.setFont(new Font("SansSerif", Font.PLAIN, 10));
		btnNewButton.setBounds(80, 610, 47, 44);
		panel_2.add(btnNewButton);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setRadius(500);
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\hoang\\Downloads\\voice (1).png"));
		
		MyButton btnNewButton_4 = new MyButton();
		btnNewButton_4.addActionListener(new ActionListener() {
		
			 public void actionPerformed(ActionEvent e) {
					int option = JOptionPane.showConfirmDialog(null, "Sharing your screen?"
							+ "", "Confirm Registration", JOptionPane.OK_CANCEL_OPTION);
					if (option == JOptionPane.OK_OPTION) {
                        Notifications.getInstance().show(Notifications.Type.INFO, "Sharing your screen successfully!");
						new SwingWorker<Void, Void>() {
	                    @Override
	                    protected Void doInBackground() throws Exception {
	                        // Code chạy trên một luồng riêng biệt
	                        ScreenCaptureServer captureServer = new ScreenCaptureServer();
	                        try {
	                            captureServer.startServer();

	                        } catch (IOException | AWTException e1) {
	                            e1.printStackTrace();
	                        }
	                        return null;
	                    }
	                }.execute();
	            }
			}
	        });
		btnNewButton_4.setFont(new Font("SansSerif", Font.PLAIN, 10));
		btnNewButton_4.setBounds(137, 610, 47, 44);
		panel_2.add(btnNewButton_4);
		btnNewButton_4.setIcon(new ImageIcon("C:\\Users\\hoang\\Downloads\\share-video.png"));
		btnNewButton_4.setBorderPainted(false);
		btnNewButton_4.setRadius(500);
		MyButton btnNewButton_1_1_1_1 = new MyButton();
		btnNewButton_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChatServer chatServer = new Chat.ChatServer();
				chatServer.setUndecorated(true);
				chatServer.setVisible(true);
				chatServer.setLocationRelativeTo(null);

			}
		});
		
		btnNewButton_1_1_1_1.setFont(new Font("SansSerif", Font.PLAIN, 10));
		btnNewButton_1_1_1_1.setBounds(194, 610, 47, 44);
		panel_2.add(btnNewButton_1_1_1_1);
		btnNewButton_1_1_1_1.setIcon(new ImageIcon("C:\\Users\\hoang\\Downloads\\speech-bubble.png"));
		btnNewButton_1_1_1_1.setBorderPainted(false);
		btnNewButton_1_1_1_1.setRadius(500);
		img_server = new JLabel("");
		img_server.setBackground(new Color(240, 255, 240));
		img_server.setFont(new Font("Tahoma", Font.PLAIN, 13));
		img_server.setBounds(86, 10, 606, 430);
		img_server.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

		panel_2.add(img_server);
		
		MyButton btnCam = new MyButton();
		btnCam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCam.setFont(new Font("SansSerif", Font.PLAIN, 10));
		btnCam.setBounds(23, 610, 47, 44);
		btnCam.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		        // Lấy trạng thái hiện tại của img_server
		        boolean isImgServerVisible = img_server_1.isVisible();
	            boolean isButtonEnabled = btnCam.isEnabled();
	            img_server_1.setVisible(!isImgServerVisible);
		    }
		});
		panel_2.add(btnCam);
				btnCam.setIcon(new ImageIcon("C:\\Users\\hoang\\Downloads\\camera (1).png"));
				btnCam.setBorderPainted(false);
				btnCam.setRadius(500);
				img_server_1 = 	new JLabel("");
				img_server_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
				img_server_1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
				img_server_1.setBackground(new Color(240, 255, 240));
				img_server_1.setBounds(445, 456, 247, 117);
				panel_2.add(img_server_1);
		
				Menu2 menuPanel = new Menu2();
				GroupLayout groupLayout = (GroupLayout) menuPanel.getLayout();
				groupLayout.setHorizontalGroup(
					groupLayout.createParallelGroup(Alignment.LEADING)
						.addGap(0, 296, Short.MAX_VALUE)
				);
				groupLayout.setVerticalGroup(
					groupLayout.createParallelGroup(Alignment.LEADING)
						.addGap(0, 725, Short.MAX_VALUE)
				);
				menuPanel.setBounds(0, 0, 239, 689);
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
		          lblMain.setFont(new java.awt.Font("sansserif", 0, 13)); // NOI18N
		          lblMain.setForeground(new java.awt.Color(255, 255, 255));
		          lblMain.setBounds(10, 10, 105, 34);
		          menuPanel.add(lblMain);
		          menuPanel.add(lblMain);
				
								
				JLabel lblMain_2 = new JLabel("Web App");
				lblMain_2.setForeground(new java.awt.Color(255, 255, 255));
				lblMain_2.setFont(new java.awt.Font("sansserif", 0, 13)); // NOI18N

				lblMain_2.setBounds(10, 91, 105, 34);
				menuPanel.add(lblMain_2);
								
				 JLabel lblNewLabel_1 = new JLabel("Email");
				   lblNewLabel_1.setForeground(new java.awt.Color(255, 255, 255));
				   lblNewLabel_1.setFont(new java.awt.Font("sansserif", 0, 13)); // NOI18N
				   lblNewLabel_1.setBackground(Color.LIGHT_GRAY);
				   lblNewLabel_1.setBounds(31, 123, 276, 45);
				   menuPanel.add(lblNewLabel_1);
				   JLabel lblNewLabel_1_1 = new JLabel("Chat");
				   lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
				   lblNewLabel_1_1.setForeground(new java.awt.Color(255, 255, 255));
				   lblNewLabel_1_1.setFont(new java.awt.Font("sansserif", 0, 13)); // NOI18N
				   lblNewLabel_1_1.setBackground(Color.LIGHT_GRAY);
				   lblNewLabel_1_1.setBounds(31, 182, 276, 45);
				   menuPanel.add(lblNewLabel_1_1);
				   
				   JLabel lblNewLabel_1_2 = new JLabel("Calendar");
				   lblNewLabel_1_2.setIcon(null);
				   lblNewLabel_1_2.setForeground(new java.awt.Color(255, 255, 255));
				   lblNewLabel_1_2.setFont(new java.awt.Font("sansserif", 0, 13)); // NOI18N
				   lblNewLabel_1_2.setBackground(Color.LIGHT_GRAY);
				   lblNewLabel_1_2.setBounds(31, 240, 276, 45);
				   menuPanel.add(lblNewLabel_1_2);
				   
				   JLabel lblMain_2_2 = new JLabel("Component");
				   lblMain_2_2.setForeground(new java.awt.Color(255, 255, 255));
				   lblMain_2_2.setFont(new java.awt.Font("sansserif", 0, 13)); // NOI18N

				   lblMain_2_2.setBounds(10, 291, 105, 34);
				   menuPanel.add(lblMain_2_2);
	   		       
	   		       JLabel lblNewLabel_1_3 = new JLabel("Advanced UI");
	   		       lblNewLabel_1_3.setForeground(new java.awt.Color(255, 255, 255));
	   		       lblNewLabel_1_3.setFont(new java.awt.Font("sansserif", 0, 13)); // NOI18N
	   		       lblNewLabel_1_3.setBackground(Color.LIGHT_GRAY);
	   		       lblNewLabel_1_3.setBounds(29, 326, 276, 45);
	   		       
	   		       menuPanel.add(lblNewLabel_1_3);
	   		       
	   		       JLabel lblNewLabel_1_1_1 = new JLabel("Contents");
	   		       lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
	   		       lblNewLabel_1_1_1.setForeground(new java.awt.Color(255, 255, 255));
	   		       lblNewLabel_1_1_1.setFont(new java.awt.Font("sansserif", 0, 13)); // NOI18N
	   		       lblNewLabel_1_1_1.setBackground(Color.LIGHT_GRAY);
	   		       lblNewLabel_1_1_1.setBounds(28, 382, 276, 45);
	   		       
	   		       menuPanel.add(lblNewLabel_1_1_1);
	   		       
	   			JLabel lblNewLabel = new JLabel(" Dashboard");
				lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
				lblNewLabel.setBackground(Color.LIGHT_GRAY);
				lblNewLabel.setForeground(new java.awt.Color(255, 255, 255));
				lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 13)); // NOI18N
				lblNewLabel.setBounds(27, 42, 206, 45);
				lblNewLabel.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
				menuPanel.add(lblNewLabel);
									          						
}

	public Server(CamPre camPre) {
        this.camPre = camPre;
	}
	 private void someMethodThatStopsWebcam() {
	        camPre.stopWebcam();
	 }
}
