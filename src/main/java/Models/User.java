package Models;

import java.util.Objects;


public class User {

    int id, passeport;
    String nom, prenom, adresse, email, type, Password;
    float Balance;
    public User(){
    }

    public User(int id,int passeport,String nom,String prenom, String Password,  float Balance,String adresse,String email,String type) {
        this.id=id;
        this.passeport=passeport;
        this.nom=nom;
        this.prenom=prenom;
        this.Password=Password;
        this.Balance=Balance;
        this.adresse=adresse;
        this.email=email;
        this.type=type;
    }

    public User(int passeport,String nom,String prenom, String Password,float Balance,String adresse,String email,String type) {
        this.passeport=passeport;
        this.nom=nom;
        this.prenom=prenom;
        this.Password=Password;
        this.Balance=Balance;
        this.adresse=adresse;
        this.email=email;
        this.type=type;
    }
    public User(int id, String nom, String email,float Balance,String type) {
        this.id=id;
        this.nom=nom;
        this.Balance=Balance;
        this.email=email;
        this.type=type;
    }


    public int getId() {
        return id;
    }

    public int getPasseport() {
        return passeport;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }
    public String getPassword(){return Password;}
    public float getBalance(){
        return Balance;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getEmail() {
        return email;
    }

    public String getType() {
        return type;
    }

    public void setId(int id) {
        this.id=id;
    }

    public void setPasseport(int passeport) {
        this.passeport=passeport;
    }

    public void setNom(String nom) {
        this.nom=nom;
    }

    public void setPrenom(String prenom) {
        this.prenom=prenom;
    }
    public void setPassword(String Password){this.Password=Password;}
    public void setBalance(float Balance){
        this.Balance=Balance;
    }

    public void setAdresse(String adresse) {
        this.adresse=adresse;
    }

    public void setEmail(String email) {
        this.email=email;
    }

    public void setType(String type) {
        this.type=type;
    }

    @Override
    public String toString() {
        return "User{"+
                "id="+id+
                ", nom='"+nom+'\''+
                ", email='"+email+'\''+
                ", type='"+type+'\''+
                ", Balance="+Balance+
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user=(User) o;
        return Objects.equals(type,user.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }
}
