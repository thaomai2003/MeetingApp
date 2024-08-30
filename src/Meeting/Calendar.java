package Meeting;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

import raven.alerts.MessageAlerts;
import raven.popup.GlassPanePopup;
import raven.popup.component.PopupCallbackAction;
import raven.popup.component.PopupController;
import raven.toast.Notifications;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.Window;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class Calendar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtTopic;
	private JTextField txtDescribe;
	private JTextField txtAtten;
	private JTextField txtDay;
	public static final String DB_URL = "jdbc:mysql://localhost:3306/commit";
	public static final String USER_NAME = "root"; // Replace with your database username
	public static final String PASSWORD = ""; // Replace with your database password
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	 
		EventQueue.invokeLater(new Runnable() {
			public void run() {
			   	FlatRobotoFont.install();
		        FlatLaf.registerCustomDefaultsSource("raven.themes");
		        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
		        FlatMacLightLaf.setup();
         

				try {
					Calendar frame = new Calendar();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Calendar() {
	    GlassPanePopup.install(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 855, 676);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));       
		setContentPane(contentPane);
		contentPane.setLayout(null);
		 addWindowListener(new WindowAdapter() {
	            @Override
	            public void windowClosing(WindowEvent e) {
	                dispose();
	            }
	        });
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 846, 639);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Schedule a meeting");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBackground(Color.RED);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(318, 10, 182, 36);
		panel.add(lblNewLabel);
		
		JLabel lblCh = new JLabel("Topic :");
		lblCh.setFont(new java.awt.Font("sansserif", 0, 13));
		lblCh.setBounds(71, 76, 80, 25);
		panel.add(lblCh);
		
		txtTopic = new JTextField();
		txtTopic.setFont(new java.awt.Font("sansserif", 0, 13));
		txtTopic.setBounds(177, 76, 566, 36);
		panel.add(txtTopic);
		txtTopic.setColumns(10);
		
		JLabel lblMT = new JLabel("Description :");
		lblMT.setFont(new java.awt.Font("sansserif", 0, 13));
		lblMT.setBounds(71, 142, 96, 25);
		panel.add(lblMT);
		
		txtDescribe = new JTextField();
		txtDescribe.setFont(new Font("SansSerif", Font.PLAIN, 13));
		txtDescribe.setHorizontalAlignment(SwingConstants.LEFT);
		txtDescribe.setColumns(10);
		txtDescribe.setBounds(177, 142, 566, 51);
		panel.add(txtDescribe);
		
		JLabel lblMT_1 = new JLabel("Time : ");
		lblMT_1.setFont(new java.awt.Font("sansserif", 0, 13));
		lblMT_1.setBounds(71, 230, 80, 22);
		panel.add(lblMT_1);
		
		JComboBox jcbHour = new JComboBox();
		jcbHour.setFont(new java.awt.Font("sansserif", 0, 13));
		jcbHour.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3"}));
		jcbHour.setBounds(178, 230, 118, 25);
		panel.add(jcbHour);
		
		JComboBox jcbMin = new JComboBox();
		jcbMin.setFont(new java.awt.Font("sansserif", 0, 13));
		jcbMin.setModel(new DefaultComboBoxModel(new String[] {"0", "15", "30", "45"}));
		jcbMin.setBounds(360, 229, 118, 25);
		panel.add(jcbMin);
		
		JLabel lblMT_1_1 = new JLabel("When : ");
		lblMT_1_1.setFont(new java.awt.Font("sansserif", 0, 13));
		lblMT_1_1.setBounds(70, 300, 65, 25);
		panel.add(lblMT_1_1);
		
		JLabel lblNewLabel_1 = new JLabel("hour");
		lblNewLabel_1.setFont(new java.awt.Font("sansserif", 0, 13));
		lblNewLabel_1.setBounds(306, 230, 55, 22);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("min");
		lblNewLabel_1_1.setFont(new java.awt.Font("sansserif", 0, 13));
		lblNewLabel_1_1.setBounds(488, 230, 55, 22);
		panel.add(lblNewLabel_1_1);
		
		JComboBox jcbHours = new JComboBox();
		jcbHours.setFont(new java.awt.Font("sansserif", 0, 13));
		jcbHours.setModel(new DefaultComboBoxModel(new String[] {"12:00", "12:30", "01:00", "01:30", "02:00", "02:30", "03:00", "03:30", "04:00", "04:30", "05:00", "05:30", "06:00", "06:30", "07:00", "07:30", "08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30"}));
		jcbHours.setBounds(177, 300, 118, 25);
		panel.add(jcbHours);
		
		JComboBox jcbMor = new JComboBox();
		jcbMor.setFont(new java.awt.Font("sansserif", 0, 13));
		jcbMor.setModel(new DefaultComboBoxModel(new String[] {"AM", "PM"}));
		jcbMor.setBounds(305, 300, 118, 25);
		panel.add(jcbMor);
		
		JLabel lblMT_1_1_1 = new JLabel("Attendees :");
		lblMT_1_1_1.setFont(new java.awt.Font("sansserif", 0, 13));
		lblMT_1_1_1.setBounds(71, 369, 96, 25);
		panel.add(lblMT_1_1_1);
		
		txtAtten = new JTextField();
		txtAtten.setHorizontalAlignment(SwingConstants.LEFT);
		txtAtten.setFont(new java.awt.Font("sansserif", 0, 13));
		txtAtten.setColumns(10);
		txtAtten.setBounds(177, 369, 566, 44);
		panel.add(txtAtten);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("<html>Enable persistent meeting chat.<br>Added attendees will have access to Meeting Huddle before and after the meeting.</html>");
		rdbtnNewRadioButton.setFont(new java.awt.Font("sansserif", 0, 13));
		rdbtnNewRadioButton.setBounds(177, 460, 562, 74);
		panel.add(rdbtnNewRadioButton);
		
		button btnCreate = new button("Create meeting");
		btnCreate.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int option = JOptionPane.showConfirmDialog(null, "Are you sure?", "Notification", JOptionPane.OK_CANCEL_OPTION);
		        if (option == JOptionPane.OK_OPTION) {
		            String topic = txtTopic.getText();
		            String desc = txtDescribe.getText();
		            String hour = (String) jcbHour.getSelectedItem();
		            String min = (String) jcbMin.getSelectedItem();
		            String day = txtDay.getText();
		            String hours = (String) jcbHours.getSelectedItem();
		            String mor = (String) jcbMor.getSelectedItem();
		            String atten = txtAtten.getText();
		            try {
		                Class.forName("com.mysql.cj.jdbc.Driver");
		                Connection conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
		                String sql = "insert into  meeting values (?,?,?,?,?)";
		                PreparedStatement ps = conn.prepareStatement(sql);
		                ps.setString(1, topic);
		                ps.setString(2, desc);
		                ps.setString(3, hour + " giờ " + min + " phút " + " ngày " + day);
		                ps.setString(4, hours + " giờ " + mor);
		                ps.setString(5, atten);
		                int n = ps.executeUpdate();
		                if (n != 0) {
		                	  Notifications.getInstance().show(Notifications.Type.INFO, "Created successfully!");
		      				        MessageAlerts.getInstance().showMessage("Data Saving Success!","Great! We have saved your data. "
		      				        		+ "Hope you have a great day with your work.", MessageAlerts.MessageType.SUCCESS,
		      				        		MessageAlerts.OK_OPTION, new PopupCallbackAction() {
		      				            @Override
		      				            public void action(PopupController pc, int i) {
		      				              if (i == MessageAlerts.OK_OPTION) {
		      				                Window parentWindow = SwingUtilities.getWindowAncestor((Component) e.getSource());
		      				                if (parentWindow != null) {
		      				                    parentWindow.dispose();
		      				                }
		      				              }
		      				            }
		      				        });
		                } else {
		                
		                }
		            } catch (Exception ex) {
		            	Notifications.getInstance().show(Notifications.Type.ERROR, "Test Toast Notifications");
		                MessageAlerts.getInstance().showMessage("Data Saving Error", "Oops! We encountered an issue while attempting to save your data. Please try again later or contact support for assistance. Apologies for any inconvenience caused.", MessageAlerts.MessageType.ERROR, MessageAlerts.OK_OPTION, new PopupCallbackAction() {
		                    @Override
		                    public void action(PopupController pc, int i) {
		                        if (i == MessageAlerts.OK_OPTION) {
		                            Window parentWindow = SwingUtilities.getWindowAncestor((Component) e.getSource());
		                            if (parentWindow != null) {
		                                parentWindow.dispose();
		                            }
		                        }
		                    }
		                });
		            }
		        }
		    }
		});

		btnCreate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCreate.setBounds(334, 564, 196, 51);
		panel.add(btnCreate);
		
		txtDay = new JTextField();
		txtDay.setFont(new java.awt.Font("sansserif", 0, 13));
		txtDay.setBounds(433, 300, 118, 25);
		panel.add(txtDay);
		txtDay.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel(" Day/Month/Year  ");
		lblNewLabel_2.setFont(new java.awt.Font("sansserif", 0, 13));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(552, 300, 110, 24);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Enter a username or Email address");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new java.awt.Font("sansserif", 0, 13));
		lblNewLabel_3.setBounds(161, 417, 582, 27);
		panel.add(lblNewLabel_3);
	}
}
