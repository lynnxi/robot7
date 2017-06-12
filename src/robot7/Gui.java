package robot7;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Gui {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui window = new Gui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Gui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 2500, 1600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel() {
    	    public void paint(Graphics g) {
    	    	g.drawRect(0, 0, 800, 1400);
    	    }
		};
		panel.setBounds(50, 50, 900, 1500);
		frame.getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setHorizontalAlignment(SwingConstants.HORIZONTAL);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(Color.YELLOW);
		lblNewLabel.setBounds(1650, 50, 800, 1500);
		frame.getContentPane().add(lblNewLabel);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(1000, 50, 600, 1200);
		frame.getContentPane().add(textArea);
		
		JButton btnNewButton = new JButton("ok");
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 96));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Thread(){
					public void run(){
						
			            while(true){
			            	
			            	BufferedImage image = Eyes.look(new Rectangle(50, 100, 800, 1400));
			                ITesseract instance = new Tesseract();  // JNA Interface Mapping  
			                //instance.setLanguage("chi_sim");//添加中文字库
			                try {
			                	long s = System.currentTimeMillis();
			                    String result = instance.doOCR(new File("C:\\Users\\zl-pc\\Pictures\\QQ图片20170612011329.png"));  
//			                    System.out.println(result);  
			                    textArea.setText(result + "\n" + "cost : " + Long.toString((s - System.currentTimeMillis()) / 1000) + "\n");

				                } catch (TesseractException e) {  
				                    System.err.println(e.getMessage());  
				               }  

			            	//BufferedImage newImg = Eyes.resize(image, label.getWidth(), label.getHeight());
			                lblNewLabel.setIcon(new ImageIcon(image));
			                
			            	
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
		});
		btnNewButton.setBounds(1116, 1318, 414, 157);
		frame.getContentPane().add(btnNewButton);
	}
}
