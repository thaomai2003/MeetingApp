package login;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import raven.alerts.MessageAlerts;
import raven.popup.component.PopupCallbackAction;
import raven.popup.component.PopupController;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

import Meeting.Meet;
import raven.popup.GlassPanePopup;
import raven.toast.Notifications;
import swing.EventLogin;

public class Login extends PanelCustom {

    private EventLogin event;

    public Login() {
    	init();
        initComponents();
        setAlpha(1);
    }

    private void init() {
    	FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("raven.themes");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        FlatMacLightLaf.setup();		
	}

	public void setEventLogin(EventLogin event) {
        this.event = event;
    }

    public static final String DB_URL = "jdbc:mysql://localhost:3306/commit";
	public static final String USER_NAME = "root"; 
	public static final String PASSWORD = "";
    @SuppressWarnings("unchecked")
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        txtUser = new swing.TextField();
        txtPass = new swing.Password();
        button1 = 
        		
        		
        		new swing.Button();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setBackground(new java.awt.Color(247, 247, 247));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(76, 76, 76));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("LOGIN");

        txtUser.setForeground(new java.awt.Color(76, 76, 76));
        txtUser.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        txtUser.setHint("USER NAME");

        txtPass.setForeground(new java.awt.Color(76, 76, 76));
        txtPass.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        txtPass.setHint("PASSWORD");

        button1.setBackground(new java.awt.Color(86, 142, 255));
        button1.setForeground(new java.awt.Color(255, 255, 255));
        button1.setText("Sign In");
        button1.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });
        jLabel2.setForeground(new java.awt.Color(76, 76, 76));
        jLabel2.setText("Or Sign in with");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/facebook.png"))); // NOI18N
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/twitter.png"))); // NOI18N
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/google-plus.png"))); // NOI18N
        jButton3.setContentAreaFilled(false);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtPass, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                    .addComponent(button1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30))
            .addGroup(layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        	layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1)
                .addGap(40, 40, 40)
                .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                		.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1))
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(40, 40, 40))
        );
    }
    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {
        try {         
		 	 Class.forName("com.mysql.cj.jdbc.Driver");					
		 	 Connection conn;
			 conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
			 String sql = "SELECT * FROM login WHERE username=? AND password=?";
			 PreparedStatement ps = conn.prepareStatement(sql);
			 ps.setString(1, txtUser.getText());
			 ps.setString(2, txtPass.getText());
		
			 ResultSet rs=ps.executeQuery();
			 if (txtUser.getText().equals("") || txtPass.getText().equals("")) {
			        JOptionPane.showMessageDialog(null, "Please fill in the information" , "Notification", JOptionPane.INFORMATION_MESSAGE);
			 }
			 else if(rs.next()){
				JOptionPane.showMessageDialog(null, "Successful Login");
				JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
		            currentFrame.setVisible(false);
				Meet meet = new Meet();
				meet.setUndecorated(true);
				meet.setVisible(true);
				meet.setLocationRelativeTo(null);
		}else {
			JOptionPane.showMessageDialog(null, "Login Failure");
		}}catch (Exception e2) {
			e2.printStackTrace();				}
        
    }
    private swing.Button button1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private swing.Password txtPass;
    private swing.TextField txtUser;
    // End of variables declaration//GEN-END:variables
}
