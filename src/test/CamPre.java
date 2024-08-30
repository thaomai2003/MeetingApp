package test;

import switchButton.Menu;
import switchButton.Menu2;
import switchButton.SwitchButton;
import switchButton.SwitchListener;
import javax.swing.GroupLayout.Alignment;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamViewer;

import Meeting.Meet;
import Meeting.button;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class CamPre extends javax.swing.JFrame {
    public CamPre(){
        initComponents();   
        switchOnOff9.addEventSwitchSelected(new SwitchListener() {
			@Override
			public void selectChange(boolean on) {
				if(on) {
					webcamPanel_2.setVisible(true);  
				} 
				if(!on) {
					webcamPanel_2.setVisible(false);;  
			        }
			}
			
			
		});
    }
    private Webcam webcam;
    private javax.swing.JPanel menuPanel;
    private switchButton.SwitchButton switchOnOff9;
    public static JPanel webcamPanel;
    private static JPanel webcamPanel_2;
    public void stopWebcam() {
            ((WebcamPanel) webcamPanel_2).stop();
    }
    private void initComponents() {
    	Menu menuPanel = new Menu();
		GroupLayout groupLayout = (GroupLayout) menuPanel.getLayout();
		menuPanel.setBounds(0, 0, 296, 725);
		
        switchOnOff9 = new switchButton.SwitchButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new FlowLayout());

        menuPanel.setBackground(new java.awt.Color(246, 246, 246));
        
        switchOnOff9.setRound(999);
        switchOnOff9.setSwitchColor(new java.awt.Color(0, 128, 255));
        
        webcamPanel = new JPanel();
        Webcam webcam = Webcam.getDefault();
        webcamPanel_2 = new WebcamPanel(webcam);
        webcamPanel_2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));

        button btnCancel = new button("Cancel");
        btnCancel.setText("Cancel");
        btnCancel.setActionCommand("");
        btnCancel.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {
            stopWebcam();
            Meet meet = new Meet();
            meet.setUndecorated(true);
			meet.setVisible(true);
			meet.setLocationRelativeTo(null);
            setVisible(false);
            
            }
        });

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\hoang\\Downloads\\camera (1).png"));
        
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\hoang\\Downloads\\voice (1).png"));
        
        SwitchButton switchOnOff9_1 = new SwitchButton();
        switchOnOff9_1.setSwitchColor(new Color(0, 128, 255));
        switchOnOff9_1.setRound(999);
        
        JLabel lblNewLabel_3 = new JLabel("");
        lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\hoang\\Downloads\\link.png"));
        
        button btnGo_1 = new button("Go");
        btnGo_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                 stopWebcam();
                 Server.Server server = new Server.Server();
                Thread serverThread = new Thread(() -> {
                    try {
                        server.main(new String[]{});
                        Thread.sleep(5000);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    } catch (ClassNotFoundException e1) {
                        e1.printStackTrace();
                    }
                    server.setLocationRelativeTo(null);
                });
                setVisible(false);
                serverThread.start();
                server.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	}
        });
        btnGo_1.setActionCommand("");
        javax.swing.GroupLayout menuPanelLayout = new javax.swing.GroupLayout(menuPanel);
        menuPanelLayout.setHorizontalGroup(
        	menuPanelLayout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(menuPanelLayout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(webcamPanel_2, GroupLayout.PREFERRED_SIZE, 414, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
        			.addGroup(menuPanelLayout.createParallelGroup(Alignment.TRAILING, false)
        				.addGroup(menuPanelLayout.createSequentialGroup()
        					.addGap(40)
        					.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        					.addComponent(btnGo_1, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
        					.addContainerGap())
        				.addGroup(menuPanelLayout.createSequentialGroup()
        					.addGap(65)
        					.addGroup(menuPanelLayout.createParallelGroup(Alignment.LEADING)
        						.addGroup(menuPanelLayout.createSequentialGroup()
        							.addGroup(menuPanelLayout.createParallelGroup(Alignment.LEADING, false)
        								.addComponent(lblNewLabel_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        								.addComponent(lblNewLabel, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addGroup(menuPanelLayout.createParallelGroup(Alignment.LEADING, false)
        								.addComponent(switchOnOff9_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        								.addComponent(switchOnOff9, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)))
        						.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
        					.addGap(71))))
        );
        menuPanelLayout.setVerticalGroup(
        	menuPanelLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(Alignment.TRAILING, menuPanelLayout.createSequentialGroup()
        			.addContainerGap(33, Short.MAX_VALUE)
        			.addGroup(menuPanelLayout.createParallelGroup(Alignment.LEADING, false)
        				.addGroup(menuPanelLayout.createSequentialGroup()
        					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
        					.addGap(18)
        					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
        				.addGroup(menuPanelLayout.createSequentialGroup()
        					.addComponent(switchOnOff9, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        					.addComponent(switchOnOff9_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
        			.addGap(167)
        			.addGroup(menuPanelLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(btnGo_1, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
        				.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
        			.addGap(28))
        		.addGroup(menuPanelLayout.createSequentialGroup()
        			.addGap(19)
        			.addComponent(webcamPanel_2, GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
        			.addGap(19))
        );
        menuPanel.setLayout(menuPanelLayout);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
      
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
    	 
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CamPre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CamPre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CamPre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CamPre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
      
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            	CamPre camPre = new CamPre();
                camPre.setVisible(true);

            }
        });
    }
}

