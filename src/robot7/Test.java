package robot7;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.GroupLayout;
/**
 * Displays a JFrame and draws a ractangle on it using the Java 2D Graphics API
 *
 * @author cn.outofmemory
 */
public class Test extends javax.swing.JFrame {

    /**
     * Creates a new instance of Java2DFrame
     */
    public Test() {
        initComponents();
    }

    /**
     * This is the method where the rectangle is drawn.
     *
     * @param g The graphics object
     */
    public void paint(Graphics g) {
        Graphics2D graphics2 = (Graphics2D) g;
        Rectangle2D rectangle = new Rectangle2D.Float(100, 100, 480, 960);
        graphics2.draw(rectangle); 
    }

    // <editor-fold defaultstate="collapsed" desc=" Generated Code "> 
    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 300, Short.MAX_VALUE)
        );
        pack();
    }// </editor-fold> 

    /**
     * Starts the program
     *
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Test().setVisible(true);
            }
        });
    }
}