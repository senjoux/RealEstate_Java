package Technique;

import com.alee.extended.panel.GroupPanel;
import com.alee.extended.panel.GroupingType;
import com.alee.laf.WebLookAndFeel;
import com.alee.laf.button.WebButton;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.JToolTip;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.event.MouseInputListener;
import org.jdesktop.swingx.JXMapKit;
import org.jdesktop.swingx.mapviewer.DefaultTileFactory;
import org.jdesktop.swingx.mapviewer.GeoPosition;
import org.jdesktop.swingx.mapviewer.TileFactoryInfo;



/**
 * A simple sample application that uses JXMapKit
 * @author Martin Steiger
 */
public class MapXA
{
    /**
     * @param pos
     */
    String pos ;

     
   public void Demmarer()
   {creatMape();
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
        GroupPanel dff = new GroupPanel(GroupingType.fillFirst, 5, false, creatMape(), Exit);
        frame.getContentPane().add(dff);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((dim.width/2)-400, (dim.height/2)-300);
        frame.setSize(800, 600);

        frame.setVisible(true);}
   
    public MapXA(){
        
    }
//    public static void main(final String[] args) {
//
//        final JFrame frame = new JFrame("");
//        WebLookAndFeel.install();
//        WebLookAndFeel.setDecorateFrames(false);
//        try {
//            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    UIManager.setLookAndFeel(info.getClassName());
//                    break;
//            }
//        }
//        } catch (Exception e) {
//            // If Nimbus is not available, you can set the GUI to another look and feel.
//        }
//        frame.setUndecorated(true);
//        WebButton Exit = new WebButton("Exit");
//        Exit.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//               WebLookAndFeel.install();
//               WebLookAndFeel.setDecorateAllWindows(true);
//               frame.setVisible(false); //you can't see me!
//               frame.dispose(); //Destroy the JFrame object
//            }
//        });
//        
//        GroupPanel dff = new GroupPanel(GroupingType.fillFirst, 5, false, creatMape(), Exit);
//        frame.getContentPane().add(dff);
//        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
//        frame.setLocation((dim.width/2)-400, (dim.height/2)-300);
//        frame.setSize(800, 600);
//        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setVisible(true);
//   
//  
//    }

    public JXMapKit creatMape(){
        final JXMapKit jXMapKit = new JXMapKit();
    TileFactoryInfo info = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        jXMapKit.setTileFactory(tileFactory);
        //String testString ="-7.502778,111.263056";
       
        double l1 = 34.63320791137959;
        double l2 = 9.47021484375;
        final GeoPosition gp = new GeoPosition(l1, l2); 

        final JToolTip tooltip = new JToolTip();
        tooltip.setTipText("");
        tooltip.setComponent(jXMapKit.getMainMap());
        jXMapKit.getMainMap().add(tooltip);

        jXMapKit.setZoom(15);
        jXMapKit.setAddressLocation(gp);

        jXMapKit.getMainMap().addMouseListener(new MouseInputListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
            //Get mouse click position in screen values
            Point point = e.getPoint();
            //Get the lat and long from the x and y mouse position
            Point2D pixelcoord1 = point;
            
           GeoPosition mappos =  jXMapKit.getMainMap().convertPointToGeoPosition(pixelcoord1);
           

                System.out.println(mappos.getLatitude());
                System.out.println(wrap( mappos.getLongitude(), -180, 180, 360));
                GeoPosition gp = new GeoPosition(mappos.getLatitude(), wrap( mappos.getLongitude(), -180, 180, 360));
                jXMapKit.setAddressLocation(gp);
                String LatitudeString =Double.toString(mappos.getLatitude());
                String LongitudeString =Double.toString(wrap( mappos.getLongitude(), -180, 180, 360));
                setPos(LatitudeString+","+LongitudeString);

            }

            @Override
            public void mousePressed(MouseEvent e) {
                           }

            @Override
            public void mouseReleased(MouseEvent e) {
           }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
             }

            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }

            private double wrap(double n, final double minValue, final double maxValue, final double interval) {
                if (minValue > maxValue) {
                throw new IllegalArgumentException("minValue must be smaller than maxValue: "
                + minValue + ">" + maxValue);
                }
                if (interval > maxValue - minValue + 1) {
                throw new IllegalArgumentException(
                "interval must be equal or smaller than maxValue-minValue: " + "min: "
                + minValue + " max:" + maxValue + " int:" + interval);
                }
                while (n < minValue) {
                n += interval;
                }
                while (n > maxValue) {
                n -= interval;
                }
                return n;
                }

          
        });
        
        return jXMapKit;
       
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }
    
}
