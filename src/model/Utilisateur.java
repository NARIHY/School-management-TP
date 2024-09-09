package model;

import java.util.Date;

/**
 * Classe représentant un utilisateur.
 * Permet de stocker et de manipuler les informations relatives à un utilisateur.
 * 
 * @autor RANDRIANARISOA
 */
public class Utilisateur {
    private int id;
    private String email;
    private String username;
    private Date dateCreation;
    private String password;  // Correction de "motsDePasse" à "motDePasse"
    public boolean verif = false;

    //constructeur
    public Utilisateur()
    {
        this.verif = true;
    }
    public Utilisateur(String email, String username,Date dateCreation, String password)
    {
        this.email = email;
        this.username = username;
        this.dateCreation = dateCreation;
        this.password = password;
    }
    // Getters et setters

    /**
     * Retourne l'identifiant de l'utilisateur.
     * 
     * @return L'identifiant de l'utilisateur.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Définit l'identifiant de l'utilisateur.
     * 
     * @param id L'identifiant à définir.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Email of user
     * 
     * @return Le nom de l'utilisateur.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Define new email of user.
     * 
     * @param email email of user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    
    /**
     * Return username
     * 
     * @return 
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Define new username
     * 
     * @param nomUtilisateur Le nom d'utilisateur à définir.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Date of creation
     * 
     * @return La date de création.
     */
    public Date getDateCreation() {
        return this.dateCreation;
    }


    /**
     * Retourne le mot de passe de l'utilisateur.
     * 
     * @return Le mot de passe.
     */
    public String getPassword() {
        return this.password;
    }

    
}
