package Controllers;

import Models.User;
import Services.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class EditUser {
    public VBox ghjgh;
    @FXML
    private TableView<User> TableFX=new TableView<>();
    @FXML
    private TableColumn<User,Integer> idFX;
    @FXML
    private TableColumn<User,String> NameFX;
    @FXML
    private TableColumn<User,String> SecondNameFX;
    @FXML
    private TableColumn<User,String> PasswordFX;
    @FXML
    private TableColumn<User,Float> BalanceFX;
    @FXML
    private TableColumn<User,String> PasseportFX;
    @FXML
    private TableColumn<User,String> EmailFX;

    @FXML
    private TableColumn<User,String> TypeFX;

    private UserService userService=new UserService();
    int idTab = TableFX.getSelectionModel().getSelectedItem().getId();



    public void initialize(URL location,ResourceBundle resources) {
        TableColumn<User,Integer> idColumn=new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<User,String> nomColumn=new TableColumn<>("Nom");
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));

        TableColumn<User,String> prenomColumn=new TableColumn<>("Prenon");
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));

        TableColumn<User,String> passwordColumn=new TableColumn<>("Password");
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("Password"));

        TableColumn<User,Double> balanceColumn=new TableColumn<>("Balance");
        balanceColumn.setCellValueFactory(new PropertyValueFactory<>("balance"));

        TableColumn<User,String> adresseColumn=new TableColumn<>("Adresse");
        adresseColumn.setCellValueFactory(new PropertyValueFactory<>("adresse"));

        TableColumn<User,String> passeportColumn=new TableColumn<>("Passeport");
        passeportColumn.setCellValueFactory(new PropertyValueFactory<>("passeport"));

        TableColumn<User,String> emailColumn=new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<User,String> typeColumn=new TableColumn<>("Type");
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        TableFX.getColumns().addAll(idColumn, nomColumn, prenomColumn,passwordColumn, balanceColumn, adresseColumn, passeportColumn, emailColumn, typeColumn );

        String cssFile = getClass().getResource("/style.css").toExternalForm();
        TableFX.getStylesheets().add(cssFile);

        User user;
        try {

            user = userService.getUserById(idTab);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        TableFX.getItems().add(user);

        ghjgh.getChildren().add(TableFX);
    }

    }

