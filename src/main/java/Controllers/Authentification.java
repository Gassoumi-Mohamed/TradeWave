package Controllers;
import javafx.beans.value.ChangeListener;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import Utils.MyDataBase;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Node;




public class Authentification {

    public MediaView fiviou;
    @FXML
    private TextField emailAuth;

    @FXML
    private PasswordField passwordAuth;
    // Déclarer une propriété pour l'écouteur de propriété focus
    private ChangeListener<Boolean> focusListener;

    @FXML
    void initialize() {
        String videoPath = "src/main/resources/images/1071516229-preview.mp4";

        // Créer un objet Media avec le chemin de la vidéo
        Media video = new Media(new File(videoPath).toURI().toString());

// Créer un lecteur multimédia
        MediaPlayer mediaPlayer = new MediaPlayer(video);

// Ajouter un écouteur d'événements pour détecter lorsque la vidéo est terminée
        mediaPlayer.setOnEndOfMedia(() -> {
            // Redémarrer la lecture lorsque la vidéo est terminée
            mediaPlayer.seek(Duration.ZERO);
            mediaPlayer.play();
        });
// Commencer la lecture de la vidéo
        mediaPlayer.play();
// Définir le lecteur multimédia sur la vue multimédia
        fiviou.setMediaPlayer(mediaPlayer);

////            emailAuth.focusedProperty().addListener((observable, oldValue, newValue) -> {
////        if (!newValue) { // Vérifier le format de l'email lorsque le champ d'email perd le focus
////            String email=emailAuth.getText();
////            if (!verifierFormatEmail(email)) {
////                afficherMessageErreur("Le format de l'email n'est pas valide");
////                emailAuth.requestFocus(); // Revenir au champ d'email
////            }
////        }
//
//    });
    }





    @FXML
    void Connexion(ActionEvent event) {
        String email = emailAuth.getText();
        String password = passwordAuth.getText();
        if (email.isEmpty() || password.isEmpty()) {
            if (email.isEmpty()) {
                afficherMessageErreur("Le champ email est vide");
            }
            if (password.isEmpty()) {
                afficherMessageErreur("Le champ mot de passe est vide");
            }
            return; // Arrêter le processus de connexion
        }
        if (!verifierFormatEmail(email)) {
            afficherMessageErreur("Le format de l'email n'est pas valide");
            return; // Arrêter le processus de connexion
        }


        Connection connexion = MyDataBase.getInstance().gtConnection(); // Obtenir la connexion ici

        try {
            if (validerUtilisateur(connexion, email, password)) {
                afficherMessage("Authentification réussie !");
                verifierTypeUtilisateur(email, password) ;
                // Vous pouvez ajouter ici le code pour rediriger l'utilisateur vers une autre vue ou effectuer d'autres actions nécessaires après l'authentification
            } else {
                afficherMessageErreur("Échec de l'authentification. Veuillez vérifier votre e-mail et votre mot de passe.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            afficherMessageErreur("Erreur lors de l'authentification. Veuillez réessayer plus tard.");
        }
    }
    private void verifierTypeUtilisateur(String email, String password) {
        Connection connexion = MyDataBase.getInstance().gtConnection(); // Obtenir la connexion ici

        try {
            String requete = "SELECT type FROM user WHERE email=? AND password=?";
            PreparedStatement statement = connexion.prepareStatement(requete);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultat = statement.executeQuery();

            if (resultat.next()) {
                String typeUtilisateur = resultat.getString("type");
                if ("admin".equals(typeUtilisateur)) {
                    System.out.println("IN ADMIN ");
                    // Lancer l'interface administrateur
                    lancerInterfaceAdmin();
                } else {
                    System.out.println("IN USER ");

                    // Lancer l'interface utilisateur
                    lancerInterfaceUtilisateur();
                }
            } else {
                // Aucun utilisateur trouvé avec ces identifiants
                afficherMessageErreur("Échec de l'authentification. Veuillez vérifier votre e-mail et votre mot de passe.");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            afficherMessageErreur("Erreur lors de l'authentification. Veuillez réessayer plus tard.");
        }
    }

    // Méthode pour lancer l'interface administrateur
    private void lancerInterfaceAdmin() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AdminInterface.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }//src/main/resources/AdminInterface.fxml

    // Méthode pour lancer l'interface utilisateur
    private void lancerInterfaceUtilisateur() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UserAccount.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }


    private boolean validerUtilisateur(Connection connexion, String email, String password) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultat = null;

        try {
            String requete = "SELECT * FROM user WHERE email=? AND password=?";
            statement = connexion.prepareStatement(requete);
            statement.setString(1, email);
            statement.setString(2, password);

            resultat = statement.executeQuery();

            return resultat.next(); // Si une ligne correspondante est trouvée, l'utilisateur est authentifié
        } finally {
            // Fermeture des ressources
            if (resultat != null) resultat.close();
            if (statement != null) statement.close();
        }
    }
   /* private boolean validerFormatEmail(String email) {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }*/
   private boolean verifierFormatEmail(String email) {
       return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
   }


        private void afficherMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void afficherMessageErreur(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    private void CreateAccountButton(ActionEvent event) {
        try {
            // Charger l'interface "Ajouter utilisateur" à partir du fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterUser.fxml"));
            Parent root = loader.load();

            // Créer une nouvelle scène avec l'interface "Ajouter utilisateur"
            Scene scene = new Scene(root);

            // Obtenir la scène actuelle à partir de l'événement
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Définir la nouvelle scène sur la fenêtre principale
            stage.setScene(scene);

            // Afficher la nouvelle scène
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
