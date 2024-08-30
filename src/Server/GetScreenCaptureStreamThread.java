package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import javax.imageio.ImageIO;

public class GetScreenCaptureStreamThread implements Runnable {
	public static final int PORT = 7789;	
	boolean IsRun;
	
	ClientScreen clientScreen;
	
	public GetScreenCaptureStreamThread(ClientScreen clientScreen) {
		this.clientScreen = clientScreen;
		this.IsRun = true;
	}

	@Override
	public void run() {
		Socket socket = null;
		try {
			while (this.IsRun){
				socket = new Socket(InetAddress.getByName("192.168.1.30"),PORT);
				this.clientScreen.bufferedImage = ImageIO.read(socket.getInputStream());
				this.clientScreen.repaint();
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
