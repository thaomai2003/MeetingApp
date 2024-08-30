package login;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

import swing.Password;

import java.awt.event.ActionEvent;


public class Register extends PanelCustom {

   
    public Register() {
        initComponents();
    }
	public static final String DB_URL = "jdbc:mysql://localhost:3306/commit";
    public static final String USER_NAME = "root"; 
    public static final String PASSWORD = "";
    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtUser = new swing.TextField();
        txtGmail = new swing.TextField();
        txtPass = new swing.Password();
        txtConfirm = new swing.Password();
        button1 = new swing.Button();
        button1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int option = JOptionPane.showConfirmDialog(null, "Are you sure?"
						+ "", "Confirm Registration", JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) {
				    String username = txtUser.getText();
				    String email = txtGmail.getText();
				    String password = txtPass.getText();
				    String confirmPassword = txtConfirm.getText();

				    if (username.equals("") || email.equals("") || password.equals("") || confirmPassword.equals("")) {
				        JOptionPane.showMessageDialog(null, "Please fill in the information", "Notification", JOptionPane.INFORMATION_MESSAGE);
				    } else if (!password.equals(confirmPassword)) {
				        JOptionPane.showMessageDialog(null, "Confirmation password does not match"
				        		+ "", "Notification", JOptionPane.INFORMATION_MESSAGE);
				    } else {
				        try {
				       	 Class.forName("com.mysql.cj.jdbc.Driver");
						 Connection conn;
						 conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
						 String sql = "insert into login values (?,?,?,?)";
						 PreparedStatement ps = conn.prepareStatement(sql);
						 ps.setString(1, txtUser.getText());
						 ps.setString(2, txtGmail.getText());
						 ps.setString(3, txtPass.getText());
						 ps.setString(4, txtConfirm.getText());
						 int n = ps.executeUpdate();
				            if (n != 0) {
				                JOptionPane.showMessageDialog(null, "Successfull registration!", "Notification", JOptionPane.INFORMATION_MESSAGE);
				                txtUser.setText("");
				                txtGmail.setText("");
				                txtPass.setText("");
				                txtConfirm.setText("");
				            } else {
				                JOptionPane.showMessageDialog(null, "Failure registration!", "Notification", JOptionPane.INFORMATION_MESSAGE);
				            }
				        } catch (Exception e1) {
				            e1.printStackTrace();
				        }
				    }
				}
        	}
        });

        setBackground(new java.awt.Color(58, 58, 58));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(242, 242, 242));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SIGN UP");

        txtUser.setForeground(new java.awt.Color(242, 242, 242));
        txtUser.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        txtUser.setHint("NAME");

        txtGmail.setForeground(new java.awt.Color(242, 242, 242));
        txtGmail.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        txtGmail.setHint("GMAIL");

        txtPass.setForeground(new java.awt.Color(242, 242, 242));
        txtPass.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        txtPass.setHint("PASSWORD");

        txtConfirm.setForeground(new java.awt.Color(242, 242, 242));
        txtConfirm.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        txtConfirm.setHint("CONFIRM PASSWORD");

        button1.setBackground(new java.awt.Color(86, 142, 255));
        button1.setForeground(new java.awt.Color(255, 255, 255));
        button1.setText("Sign Up");
        button1.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(button1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtConfirm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                    .addComponent(txtGmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPass, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1)
                .addGap(40, 40, 40)
                .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtGmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.Button button1;
    private javax.swing.JLabel jLabel1;
    private swing.Password txtConfirm;
    private swing.TextField txtUser;
    private swing.TextField txtGmail;
    private swing.Password txtPass;
    // End of variables declaration//GEN-END:variables
}
