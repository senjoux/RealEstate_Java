package Technique;

import com.alee.extended.panel.GroupPanel;
import com.alee.extended.panel.GroupingType;
import com.alee.laf.WebLookAndFeel;
import com.alee.laf.button.WebButton;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.geom.Point2D;
import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.JToolTip;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import org.jdesktop.swingx.JXMapKit;
import org.jdesktop.swingx.JXMapViewer;
import org.jdesktop.swingx.mapviewer.DefaultTileFactory;
import org.jdesktop.swingx.mapviewer.GeoPosition;
import org.jdesktop.swingx.mapviewer.TileFactoryInfo;



/**
 * A simple sample application that uses JXMapKit
 * @author Martin Steiger
 */
public class MapX
{
    /**
     * @param pos
     */
    public MapX(String pos){
         final JFrame frame = new JFrame("");
        
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
            }
        }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }
       
        
        frame.setUndecorated(true);
        WebButton Exit = new WebButton("Exit");
        Exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               frame.setVisible(false); //you can't see me!
               frame.dispose(); //Destroy the JFrame object
            }
        });
        frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        GroupPanel dff = new GroupPanel(GroupingType.fillFirst, 5, false, creatMape(pos), Exit);
        frame.getContentPane().add(dff);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((dim.width/2)-400, (dim.height/2)-300);
        frame.setSize(800, 600);
        
        frame.setVisible(true);
    }
    public static void main(final String[] args) {

        final JFrame frame = new JFrame("");
        WebLookAndFeel.install();
        WebLookAndFeel.setDecorateFrames(false);
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
            }
        }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }
        frame.setUndecorated(true);
        WebButton Exit = new WebButton("Exit");
        Exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               frame.setVisible(false); //you can't see me!
               frame.dispose(); //Destroy the JFrame object
            }
        });
        frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        GroupPanel dff = new GroupPanel(GroupingType.fillFirst, 5, false, creatMape("-7.502778,111.263056"), Exit);
        frame.getContentPane().add(dff);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((dim.width/2)-400, (dim.height/2)-300);
        frame.setSize(800, 600);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
   
  
    }

    public static JXMapKit creatMape(String pos){
        final JXMapKit jXMapKit = new JXMapKit();
        TileFactoryInfo info = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        jXMapKit.setTileFactory(tileFactory);
        //String testString ="-7.502778,111.263056";
        String[] xcv= pos.split("\\,");
        double l1 = Double.parseDouble( xcv[0]);
        double l2 = Double.parseDouble( xcv[1]);
        final GeoPosition gp = new GeoPosition(l1, l2); 

        final JToolTip tooltip = new JToolTip();
        tooltip.setTipText("");
        tooltip.setComponent(jXMapKit.getMainMap());
        jXMapKit.getMainMap().add(tooltip);

        jXMapKit.setZoom(3);
        jXMapKit.setAddressLocation(gp);

        jXMapKit.getMainMap().addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) { 
                // ignore
                
            }

            @Override
            public void mouseMoved(MouseEvent e)
            {
                JXMapViewer map = jXMapKit.getMainMap();

                // convert to world bitmap
                Point2D worldPos = map.getTileFactory().geoToPixel(gp, map.getZoom());

                // convert to screen
                Rectangle rect = map.getViewportBounds();
                int sx = (int) worldPos.getX() - rect.x;
                int sy = (int) worldPos.getY() - rect.y;
                Point screenPos = new Point(sx, sy);

                // check if near the mouse
                if (screenPos.distance(e.getPoint()) < 20)
                {
                    screenPos.x -= tooltip.getWidth() / 2;

                    tooltip.setLocation(screenPos);
                    tooltip.setVisible(true);
                }
                else
                {
                    tooltip.setVisible(false);
                }
            }
        });
        
        return jXMapKit;
       
    }
}
