package Server;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.imageio.ImageIO;

public class ScreenCaptureServer{
	
	public static void main(String[] args) throws AWTException, IOException{
        startServer();
	}

	  public static void  startServer() throws IOException, AWTException {
		ServerSocket serverSocket = new ServerSocket(7789);
		Robot robot = new Robot();
		while (true) {
			Socket socket = serverSocket.accept();
			BufferedImage image = robot.createScreenCapture(new Rectangle(0, 0, 1500, 1500));
			ImageIO.write(image, "jpg", socket.getOutputStream());
			socket.close();		
		}
		}
}
