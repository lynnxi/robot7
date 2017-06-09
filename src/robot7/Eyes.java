package robot7;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.Console;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


/**
 *     @author wangtianze QQ:864620012
 *     @date 2017年4月16日 下午4:43:11
 *     <p>description:Robot实现监控屏幕,实现效果是每隔50微秒获取当前屏幕图像，显示到JFrame内</p>
 */
public class Eyes {
    public static void main(String[] args){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dm = toolkit.getScreenSize();
    	JFrame frame = new JFrame(); 
    	JPanel panel1 = new JPanel() {
    	    public void paint(Graphics g) {
    	    	g.drawRect(50, 50, 800, 1300);
    	    }
    	};

//    	JTextArea area = new JTextArea(40,80);
//    	JScrollPane panel2 = new JScrollPane(area);
    	JLabel label = new JLabel();
    	label.setPreferredSize(new Dimension(1200, 1700));
    	label.setForeground(Color.BLUE);
//      panel2 = new JPanel();
    	JPanel panel3 = new JPanel();
    	JButton button = new JButton("catch");
    	button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new Thread(){
					public void run(){
			            while(true){
			            	BufferedImage image = Eyes.look(new Rectangle(50, 50, 900, 1400));
			            	//BufferedImage newImg = Eyes.resize(image, label.getWidth(), label.getHeight());
			            	label.setIcon(new ImageIcon(image));
			            	
			                try {
			                    Thread.sleep(50);
			                } catch (InterruptedException e) {
			                    // TODO Auto-generated catch block
			                    e.printStackTrace();
			                }
			            }
					}
				}.start();

	
			}
		});;
    	panel3.add(button);

		frame.add(panel1, BorderLayout.CENTER);
		frame.add(label, BorderLayout.EAST);
		frame.add(panel3, BorderLayout.SOUTH);
		
		//frame.setSize((int)dm.getWidth(), (int)dm.getHeight());
		frame.setSize(2300, 1500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
    }
    
    public static BufferedImage look(Rectangle rec) {
        try {
            Robot robot = new Robot();
            return robot.createScreenCapture(rec);
        } catch (AWTException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
	}
    
    public static BufferedImage resize(BufferedImage img, int newW, int newH){
        int w = img.getWidth();
        int h = img.getHeight();
        //创建一个缩放后的图片流
        BufferedImage newImg = new BufferedImage(newW,newH,img.getType());
        Graphics2D g = newImg.createGraphics();
        //设置模式
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        //按比例缩放
        g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);
        g.dispose();
        return newImg;
    }
}
