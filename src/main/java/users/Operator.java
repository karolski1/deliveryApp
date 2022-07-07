package users;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import engine.Configuration;

public class Operator extends User {
    private Configuration configurations;


    public Operator(String username, String password) {
        super(username, password);
    }
    public Operator(String username, String password,Configuration configurations) {
        super(username, password);
        this.configurations = configurations;
    }


    public void changeConfigurations(int maxNumberOfRetries, int numberOfDifferentAddresses, double discount,double maxWeight,double maxTime){
        
        //We change the configurations and we save them in the binary file "config.ser"
        Configuration config = new Configuration(maxNumberOfRetries, numberOfDifferentAddresses, discount, maxWeight,maxTime);
        try
        {   
            //Saving of object in a file
            FileOutputStream file = new FileOutputStream("config.ser");
            ObjectOutputStream out = new ObjectOutputStream(file);
                    
            // Method for serialization of object
            out.writeObject(config);
                    
            out.close();
            file.close();
                    
            java.lang.System.out.println("Object has been serialized");

        } 
        catch(IOException ex)
        {
            java.lang.System.out.println("IOException is caught");
        }

    }
}
