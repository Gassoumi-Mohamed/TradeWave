package Controllers;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import javafx.fxml.FXMLLoader;


public class AdminInterface {
    public AnchorPane areamg;
    private AnchorPane aremg;
    public void lancerManageUsersButton() throws IOException {
        // Charger l'interface CRUD
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UsersList.fxml"));
        Node root = loader.load();
        areamg.getChildren().clear();
        areamg.getChildren().add(root) ;
        // Créer une nouvelle scène
       // Scene scene = new Scene(root);

        // Créer une nouvelle fenêtre
        //Stage stage = new Stage();
       // stage.setScene(scene);
        //stage.show();
    }


}
