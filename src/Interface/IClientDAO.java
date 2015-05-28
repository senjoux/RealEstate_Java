
package Interface;

import Entity.Client;
import Entity.Gerant;
import java.util.List;

/**
 *
 * @author user
 */
public interface IClientDAO {
    
     public void insertClient(Client c);
    public void deleteClient(int id);
    public List<Client> displayAll();
    public Client findClientById(int id);
}
