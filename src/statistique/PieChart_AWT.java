package statistique;

/**
 *
 * @author admin
 */
import DAO.AdresseDAO;
import DAO.OffreDAO;
import Entity.Adresse;
import Entity.OffreX;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class PieChart_AWT extends ApplicationFrame {

    static TreeMap<Integer, Integer> MapOfAdresse = new TreeMap<Integer, Integer>();

    public PieChart_AWT(String title) {
        super(title);
        setContentPane(createDemoPanel());
    }

    private static void setNbrAdresseByOffre() {
        OffreDAO offreDAO = new OffreDAO();
        List<OffreX> listOffre = offreDAO.getAllListOfOffreX();
        
        
        //key === id_adresse  value ==> nombre des apparition des adresse dans la liste des offre
        //value map.get pour retourner la valeur de l'@ et on add 1
        for (OffreX of : listOffre) {
            if (MapOfAdresse.get(of.getId_adresse()) == null) {
                MapOfAdresse.put(of.getId_adresse(), 0);
            }
            MapOfAdresse.put(of.getId_adresse(), MapOfAdresse.get(of.getId_adresse()) + 1);
        }
    }

    private static PieDataset createDataset() {

        DefaultPieDataset dataset = new DefaultPieDataset();
        setNbrAdresseByOffre();
        AdresseDAO adDAO = new AdresseDAO();
  
        for (Map.Entry<Integer, Integer> entry : MapOfAdresse.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            Adresse adresse = adDAO.findAdresseById(key);
            dataset.setValue(adresse.getVille(), new Double(value));
        }
       
        return dataset;
    }

    private static JFreeChart createChart(PieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart(
                "nombre des offres par ville", // chart title 
                dataset, // data    
                true, // include legend   
                true,
                false);

        return chart;
    }

    public JPanel createDemoPanel() {
        JFreeChart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {

        PieChart_AWT demo = new PieChart_AWT("nombre des offres par ville");

        demo.setSize(560, 367);
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
