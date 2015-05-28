package Technique;

import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author Elyes
 */
public class LoadIcon {

    public static ImageIcon loadIcon(URL path) {

        if (path != null) {
            return new ImageIcon(path);
        } else {
            return new ImageIcon("resources/notFound.png");
        }
    }

}
