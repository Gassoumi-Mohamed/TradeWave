package Controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Models.User;
import Services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class AjouterUser {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button add;

    @FXML
    private TextField PasswordFX;

    @FXML
    private TextField adresseFX;

    @FXML
    private TextField balanceFX;

    @FXML
    private TextField emailFX;

    @FXML
    private TextField nomFX;

    @FXML
    private TextField passeportFX;

    @FXML
    private TextField prenomFX;

    @FXML
    private TextField typeFX;






    @FXML
    void addUser(ActionEvent event) {
        String nomF = nomFX.getText();
        String prenomF = prenomFX.getText();
        String passwordF = PasswordFX.getText();
        float balanceF = Float.parseFloat(balanceFX.getText());
        String adresseF = adresseFX.getText();
        String emailF = emailFX.getText();
        String typeF = typeFX.getText();
        int passeportF = Integer.parseInt(passeportFX.getText());






        if (nomF.isEmpty() || prenomF.isEmpty() || passwordF.isEmpty()  || adresseF.isEmpty() || emailF.isEmpty() || typeF.isEmpty()  ) {
            if (nomF.isEmpty()) {
                afficherMessageErreur("Le champ nom est vide");
            }
            if (prenomF.isEmpty()) {
                afficherMessageErreur("Le champ prenom est vide");
            }
            if (passwordF.isEmpty()) {
                afficherMessageErreur("Le champ mot de passe est vide");
            }

            if (adresseF.isEmpty()) {
                afficherMessageErreur("Le champ adresse est vide");
            }
            if (emailF.isEmpty()) {
                afficherMessageErreur("Le champ email est vide");
            }
            if (typeF.isEmpty()) {
                afficherMessageErreur("Le champ type est vide");
            }


            return; // Arrêter le processus de connexion
        }

        User user = new User(passeportF,nomF, prenomF, passwordF, balanceF, adresseF, emailF, typeF);
        UserService userService = new UserService();
        if (!verifierFormatEmail(emailF)) {
            afficherMessageErreur("Le format de l'email n'est pas valide");
            return;
        }
        if (passwordF.length() < 8 || passwordF.length() > 20) {
            afficherMessageErreur("Le mot de passe doit avoir entre 8 et 20 caractères");
            return;
        }


        try {
            userService.ajouter(user);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("L'utilisateur est ajouté avec succées");
            alert.show();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }

    }

    @FXML
    void initialize() {
        assert PasswordFX != null : "fx:id=\"PasswordFX\" was not injected: check your FXML file 'AjouterUser.fxml'.";
        assert add != null : "fx:id=\"add\" was not injected: check your FXML file 'AjouterUser.fxml'.";
        assert adresseFX != null : "fx:id=\"adresseFX\" was not injected: check your FXML file 'AjouterUser.fxml'.";
        assert balanceFX != null : "fx:id=\"balanceFX\" was not injected: check your FXML file 'AjouterUser.fxml'.";
        assert emailFX != null : "fx:id=\"emailFX\" was not injected: check your FXML file 'AjouterUser.fxml'.";
        assert nomFX != null : "fx:id=\"nomFX\" was not injected: check your FXML file 'AjouterUser.fxml'.";
        assert passeportFX != null : "fx:id=\"passeportFX\" was not injected: check your FXML file 'AjouterUser.fxml'.";
        assert prenomFX != null : "fx:id=\"prenomFX\" was not injected: check your FXML file 'AjouterUser.fxml'.";
        assert typeFX != null : "fx:id=\"typeFX\" was not injected: check your FXML file 'AjouterUser.fxml'.";

    }
    private boolean verifierFormatEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }
    private void afficherMessageErreur(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
