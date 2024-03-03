package Services;
import Models.User;
import Utils.MyDataBase;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService implements IService<User> {
    private final Connection connection;

    public UserService() {
        connection=MyDataBase.getInstance().gtConnection();
    }


    @Override
    public void ajouter(User user) throws SQLException {
        String sql="INSERT INTO user (nom, prenom, Password, Balance, adresse, passeport, email, type) "+
                "VALUES ('"+user.getNom()+"','"+user.getPrenom()+"','"+user.getPassword()+"','"+user.getBalance()+"','"+
                user.getAdresse()+"','"+user.getPasseport()+"','"+user.getEmail()+"','"+user.getType()+"')";


        Statement statement=connection.createStatement();
        statement.executeUpdate(sql);

    }

    @Override
    public void modifier(User user) throws SQLException {
        String sql="update user set nom = ?, prenom = ?, Password = ?, Balance = ?, adresse = ?, passeport = ?, email = ?, type = ? where id = ?";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1,user.getNom());
        preparedStatement.setString(2,user.getPrenom());
        preparedStatement.setString(3,user.getPassword());
        preparedStatement.setFloat(4,user.getBalance());
        preparedStatement.setString(5,user.getAdresse());
        preparedStatement.setInt(6,user.getPasseport());
        preparedStatement.setString(7,user.getEmail());
        preparedStatement.setString(8,user.getType());
        preparedStatement.setInt(9,user.getId());
        preparedStatement.executeUpdate();

    }

    public void supprimer(int id) throws SQLException {
        String req="DELETE FROM `user` WHERE id=?";
        PreparedStatement preparedStatement=connection.prepareStatement(req);
        preparedStatement.setInt(1,id);
        preparedStatement.executeUpdate();

    }

    @Override
    public List<User> recuperer() throws SQLException {

            String sql = "select * from user";

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            List<User> list = new ArrayList<>();
            while (rs.next()){
                User us = new User();
                us.setId(rs.getInt("id"));
                us.setNom(rs.getString("Nom"));
                us.setPrenom(rs.getString("Prenom"));
                us.setBalance(rs.getInt("Balance"));
                us.setAdresse(rs.getString("Adresse"));
                us.setPasseport(rs.getInt("Passeport"));
                us.setEmail(rs.getString("Email"));
                us.setType(rs.getString("Type"));
                list.add(us);

            }
            return list;

    }
    public List<User> recupererTab() throws SQLException {

        String sql = "select * from user";

        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        List<User> list = new ArrayList<>();
        while (rs.next()){
            User us = new User();
            us.setId(rs.getInt("id"));
            us.setNom(rs.getString("Nom"));
            us.setEmail(rs.getString("Email"));
            us.setBalance(rs.getInt("Balance"));
            us.setType(rs.getString("Type"));
            list.add(us);

        }
        return list;

    }
    public User getUserById(int id) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = null;

        try {
            // Préparer la requête SQL pour récupérer l'utilisateur par son ID
            String query = "SELECT * FROM users WHERE id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            // Exécuter la requête et récupérer le résultat
            resultSet = statement.executeQuery();

            // Si un utilisateur est trouvé, créer un objet User à partir des données récupérées
            if (resultSet.next()) {
                String nom = resultSet.getString("nom");
                String email = resultSet.getString("email");
                float balance = resultSet.getFloat("balance");
                String type = resultSet.getString("type");
                user = new User(id, nom, email, balance, type);
            }
        } finally {
            // Fermer les ressources JDBC
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }

        return user;
    }
  }

