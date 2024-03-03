package Controllers;

import Models.User;
import Services.UserService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;



import static javafx.scene.layout.Region.USE_PREF_SIZE;


public class UsersList implements Initializable {


    public VBox vbbbb;

    @FXML
    private TableColumn<User, Integer> idFX;
    @FXML
    private TableColumn<User, String> NameFX;
    @FXML
    private AnchorPane areamg ;
    @FXML
    private TableColumn<User, String> EmailFX;
    @FXML
    private TableColumn<User, Float> BalanceFX;
    @FXML
    private TableColumn<User, String> TypeFX;

    private UserService userService = new UserService();
    private URL UR ;
    private   TableView<User> TableFX ;
    private  ResourceBundle RS ;




    @Override
    public void initialize(URL location, ResourceBundle resources) {



        UR=location ;
        RS=resources ;



        TableView<User> userTable = new TableView<>();
        userTable.setPrefWidth(USE_PREF_SIZE);
        TableColumn<User, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<User, String> nomColumn = new TableColumn<>("Nom");
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));

        TableColumn<User, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<User, Double> balanceColumn = new TableColumn<>("Balance");
        balanceColumn.setCellValueFactory(new PropertyValueFactory<>("balance"));

        TableColumn<User, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));



        TableColumn<User, Void> deleteColumn = new TableColumn<>("Supprimer");
        deleteColumn.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("Supprimer");

            {
                deleteButton.setOnAction(event -> {
                    User user = getTableView().getItems().get(getIndex()); // Récupérer l'utilisateur de la ligne actuelle
                    int userId = user.getId(); // Récupérer l'ID de l'utilisateur
                    try {
                        delete_(userId);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });


            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                }
            }
        });

     TableFX = new TableView<>();
        TableFX.getColumns().clear();
        List<User> users;
        try {
            users = userService.recupererTab();
            System.out.println( users.toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        TableFX.getColumns().addAll(idColumn, nomColumn, emailColumn, balanceColumn, typeColumn,  deleteColumn);
        TableFX.getColumns().clear();
        TableFX.getColumns().addAll(idColumn, nomColumn, emailColumn, balanceColumn, typeColumn,  deleteColumn);
        TableFX.getItems().addAll(users);
        vbbbb.getChildren().add(TableFX);



        String cssFile = getClass().getResource("/style.css").toExternalForm();
        TableFX.getStylesheets().add(cssFile);
    }

    private void delete_(int id) throws SQLException {

        userService.supprimer(id);
        TableFX.getItems().clear();
        TableFX.getItems().addAll(userService.recupererTab());
    }

}
