package users;

import java.io.Serializable;

/**
* <h1>Abstract class Users</h1>
* In this abstract class we have the common variables that all the subclasses
* about the user will have. 
* <p>
* <b>Note:</b>The class is abstract because  we have implemented 3 seperate subclasses that 
* implement the 3 possible users of the app, a Driver, a Operator and a Company.
*/


public abstract class User implements Serializable {
    protected String username;
    protected String password;

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {
    }
}
