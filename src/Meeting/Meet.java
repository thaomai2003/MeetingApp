package Meeting;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

import raven.toast.Notifications;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import switchButton.SwitchListener;
import test.CamPre;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Meet extends JFrame {
    private JPanel contentPane;
    private int nextPanelY = 208;
    private JScrollPane scrollPane;
    public static final String DB_URL = "jdbc:mysql://localhost:3306/commit";
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "";
    public Meet() {
     	FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("raven.themes.light");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 15));
        FlatMacLightLaf.setup();
	    setBackground(Color.white); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1079, 690);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(Color.white); 
       JLabel lblNewLabel = new JLabel("Meetings");
       lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(-2, 10, 152, 47);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Upcoming");
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setBackground(Color.BLUE);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new java.awt.Font("sansserif", 1, 15));
		lblNewLabel_1.setBounds(10, 52, 100, 32);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Previous");
		lblNewLabel_1_1.setForeground(Color.BLACK);
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(84, 52, 110, 32);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Meeting Templates");
		lblNewLabel_1_2.setForeground(Color.BLACK);
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblNewLabel_1_2.setBounds(180, 52, 142, 32);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("____________");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3.setForeground(Color.BLUE);
		lblNewLabel_1_3.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblNewLabel_1_3.setBackground(Color.BLACK);
		lblNewLabel_1_3.setBounds(10, 66, 110, 32);
		contentPane.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("________________");
		lblNewLabel_1_1_1.setForeground(Color.BLACK);
		lblNewLabel_1_1_1.setFont(new Font("SansSerif", Font.PLAIN, 10));
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setBounds(94, 68, 110, 32);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("__________________________");
		lblNewLabel_1_2_1.setForeground(Color.BLACK);
		lblNewLabel_1_2_1.setFont(new Font("SansSerif", Font.PLAIN, 10));
		lblNewLabel_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2_1.setBounds(149, 68, 194, 32);
		contentPane.add(lblNewLabel_1_2_1);
		
		button btnNewButton = new button("Start time to End Time");
		btnNewButton.setHorizontalAlignment(SwingConstants.CENTER);
		btnNewButton.setFont(new java.awt.Font("sansserif", 1, 15));
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\hoang\\eclipse-workspace\\final_test\\src\\image\\calendar.png"));
		btnNewButton.setBounds(10, 101, 276, 41);
		contentPane.add(btnNewButton);
		
		
		button btnSchedule = new button("+ Schedule Meeting");
		btnSchedule.setFont(new java.awt.Font("sansserif", 1, 15));
		btnSchedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Calendar calendar = new Calendar();
				calendar.setUndecorated(true);
				calendar.setVisible(true);
				calendar.setLocationRelativeTo(null);
			}
		});
		btnSchedule.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnSchedule.setForeground(SystemColor.window);
		btnSchedule.setBackground(SystemColor.textHighlight);
		btnSchedule.setBounds(779, 52, 276, 47);
		contentPane.add(btnSchedule);
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.GRAY);
		panel.setBackground(new Color(70, 130, 180));
		panel.setBounds(10, 166, 1045, 32);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Today");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblNewLabel_2.setBounds(0, 0, 100, 32);
		lblNewLabel_2.setBackground(Color.white);

		panel.add(lblNewLabel_2);
		
		scrollPane = new JScrollPane();
		scrollPane.setForeground(Color.white); 
		scrollPane.setBackground(Color.white); 
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBorder(null);
		scrollPane.setBounds(10, 208, 1045, 400);
		contentPane.add(scrollPane);

		button btnNewButton_2 = new button("Refresh");
		btnNewButton_2.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                updateMeetingPanels();
			}
		});
		btnNewButton_2.setFont(new java.awt.Font("sansserif", 1, 15));
		btnNewButton_2.setBounds(779, 115, 276, 41);
		contentPane.add(btnNewButton_2);
		clearMeetingPanels();
	    updateMeetingPanels();
    }
    private void createAndDisplayPanel(String topic, String describe, String time, String when) {
    	JPanel newPanel = new JPanel();
        newPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
        newPanel.setBackground(Color.white);
        newPanel.setForeground(Color.white);
        newPanel.setBounds(50, 50, 200, 200);
        newPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        newPanel.setPreferredSize(new Dimension(900, 250));
        newPanel.setLayout(new GridLayout(4, 1));
        JLabel txtTopic = new JLabel("<html><font color='red'>TOPIC:</font> " + topic);
        txtTopic.setFont(new java.awt.Font("sansserif",1, 16));
        txtTopic.setBackground(Color.black);
        txtTopic.setForeground(Color.black);
        txtTopic.setBounds(16, 40, 400, 20);
        txtTopic.setHorizontalAlignment(SwingConstants.LEFT);
        newPanel.add(txtTopic);
        
        JLabel txtDes = new JLabel("<html><font color='red'>DESCRIPTION:</font> " + describe);
	    txtDes.setFont(new java.awt.Font("sansserif", 1, 16));
        txtDes.setBounds(16, 100, 800, 20);
        txtDes.setBackground(Color.black);
        txtDes.setForeground(Color.black);
        newPanel.add(txtDes);
        
        JLabel txtTime = new JLabel("<html><font color='red'>TIME:</font> " + time);
        txtTime.setFont(new java.awt.Font("sansserif", 1, 16));      
        txtTime.setBounds(16, 70, 400, 20);
        txtTime.setBackground(Color.black);
        txtTime.setForeground(Color.black);
        newPanel.add(txtTime);
        
        JLabel txtWhen = new JLabel("<html><font color='red'>WHEN :</font> " + when);
        txtWhen.setFont(new java.awt.Font("sansserif" ,1,16));      
        txtWhen.setBounds(16, 10, 400, 20);
        txtWhen.setBackground(Color.black);
        txtWhen.setForeground(Color.black);
        newPanel.add(txtWhen);
       
		button btnStart = new button("Start");
        btnStart.setForeground(SystemColor.window);
        btnStart.setBackground(new Color(0, 0, 139));
        btnStart.setFont(new java.awt.Font("sansserif", 1, 15));
		btnStart.setBounds(333, 53, 118, 21);
		newPanel.add(btnStart);
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int option = JOptionPane.showConfirmDialog(null, "Join meeting " +topic+ " ?"
						+ "", "Confirm Registration", JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) {
		        	CamPre camPre = new CamPre();
		        	camPre.setVisible(true);
		        	camPre.setLocationRelativeTo(null);
					setVisible(false);
					Notifications.getInstance().show(Notifications.Type.INFO, "Join meeting "+topic+" Successfully!");
				}
		        }
		});
			button btnEdit = new button("Edit");
		    btnEdit.setFont(new java.awt.Font("sansserif", 1, 15));
		    btnEdit.setBounds(461, 53, 90, 21);
		    btnEdit.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		            String newDescribe = JOptionPane.showInputDialog("Enter new description:", describe);
		            String newTime = JOptionPane.showInputDialog("Enter new time:", time);
		            String newWhen = JOptionPane.showInputDialog("Enter new 'When':", when);
		            if (newDescribe == null || newTime == null || newWhen == null) {
		                return;
		            }
		            editMeeting(topic, newDescribe, newTime, newWhen);
					Notifications.getInstance().show(Notifications.Type.INFO, "Update Successfully!");
		       }
		    });
		newPanel.add(btnEdit);
		button btnDelete = new button("Delete");
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setBackground(new Color(178, 34, 34));
		btnDelete.setFont(new java.awt.Font("sansserif", 1, 15));
		btnDelete.setBounds(589, 53, 118, 21);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int option = JOptionPane.showConfirmDialog(null, "Are you sure?"
						+ "", "Confirm Registration", JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) {
				  try {
				        Class.forName("com.mysql.cj.jdbc.Driver");
				        Connection conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
				        String sql = "DELETE FROM meeting WHERE Topic = ?";
				        PreparedStatement preparedStatement = conn.prepareStatement(sql);
				        preparedStatement.setString(1, topic);
				        // Thực thi truy vấn DELETE
				        int rowsAffected = preparedStatement.executeUpdate();
				        if (rowsAffected > 0) {
							  Notifications.getInstance().show(Notifications.Type.INFO, "Delete Successfully!");
				        	updateMeetingPanels();
				        } else {
				        }
				        preparedStatement.close();
				        conn.close();
				    } catch (Exception e2) {
				        e2.printStackTrace();
				    }
			}
			}
		});
		
		newPanel.add(btnDelete);
		  JPanel contentPanePanel = (JPanel) scrollPane.getViewport().getView(); 
		  if (contentPanePanel == null) {
            contentPanePanel = new JPanel();
            contentPanePanel.setLayout(new BoxLayout(contentPanePanel, BoxLayout.Y_AXIS));
            scrollPane.setViewportView(contentPanePanel);
        }
		  JViewport viewport = scrollPane.getViewport();
          viewport.setBackground(Color.WHITE);
          contentPanePanel.setBackground(Color.white);
          contentPanePanel.setForeground(Color.white);
          scrollPane.setBackground(Color.white);
          scrollPane.setForeground(Color.white);
        contentPanePanel.add(newPanel);
        contentPanePanel.revalidate();
        contentPanePanel.repaint();
        
    }
    
    private void clearMeetingPanels() {
        if (scrollPane.getViewport().getView() == null) {
            return;
        }
        JPanel contentPanePanel = (JPanel) scrollPane.getViewport().getView();
        contentPanePanel.removeAll();
        contentPanePanel.revalidate();
        contentPanePanel.repaint();
    } 
    private void editMeeting(String topic, String newDescribe, String newTime, String newWhen) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            String sql = "UPDATE meeting SET `Describe`=?, `Time`=?, `When`=? WHERE Topic=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, newDescribe);
            preparedStatement.setString(2, newTime);
            preparedStatement.setString(3, newWhen);
            preparedStatement.setString(4, topic);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
            	updateMeetingPanels();
            } else {
                System.out.println("No rows updated.");
            }
            preparedStatement.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void updateMeetingPanels() {
    	clearMeetingPanels();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            Statement statement = conn.createStatement();
            String sql = "SELECT * FROM meeting";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String topic = resultSet.getString("Topic");
                String describe = resultSet.getString("Describe");
                String time = resultSet.getString("Time");
                String when = resultSet.getString("When");
                createAndDisplayPanel(topic,describe,time,when);
            }
            contentPane.revalidate();
            contentPane.repaint();
            resultSet.close();
            statement.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Meet frame = new Meet();
                frame.setUndecorated(true);
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}