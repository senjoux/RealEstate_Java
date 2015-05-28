package Entity;

import java.util.ArrayList;

/**
 *
 * @author Elyes
 */
public class BoiteMessages {

    public ArrayList<Message> box;

    public BoiteMessages() {
        this.box = new ArrayList<Message>();
    }

    public void ajouterMessage(Message m) {
        this.box.add(m);
    }

}
