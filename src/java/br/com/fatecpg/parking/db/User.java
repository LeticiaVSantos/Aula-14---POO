
package br.com.fatecpg.parking.db;

import br.com.fatecpg.parking.web.DataBaseConnector;
import java.util.ArrayList;


public class User {
    private long id;
    private String login;
    private String name;
    private String role;
    private long passwordHash;

    public User() {
    }

    public User(long id, String login, String name, String role, long passwordHash) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.role = role;
        this.passwordHash = passwordHash;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public long getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(long passwordHash) {
        this.passwordHash = passwordHash;
    }
    
    public static User validateUser(String login, String password) throws Exception{
        String Query = "SELECT * FROM USERS WHERE login = ? AND passwordHash = ?";
        ArrayList<Object[]> list = DataBaseConnector.executeQuery
        (Query, new Object[] {login, password.hashCode()});
        if(list.size()>0){
            Object[] row = list.get(0);
            User u = new User(
                     (long)row[0]
                     ,(String) row[1]
                     ,(String)row[2] 
                     ,(String)row[3]        
                     ,(long)row[4]);
            return u;
        }else{
            return null;
        }
    }
    
}
