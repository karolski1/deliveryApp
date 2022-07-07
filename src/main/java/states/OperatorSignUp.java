package states;

import users.Company;
import users.User;

public class OperatorSignUp extends SignUpState {
    public OperatorSignUp(States myState) {
        super(myState);
    }


    @Override
    public void draw() {
//        /*copy and paste from custumerSignUP check that and corect*/
//
//        //public Company(String username, String password, String address, String email, String creditCardNumber, String name)
//        //maybe instaed of String username and so on we should set value to some class???
//        java.lang.System.out.print("Enter a username\n");
//        String username = sc.next();
//        //check if credentials are correct
//        java.lang.System.out.print("Enter\n" + username);
//
//        while (!system.checkUsername(username)) {
//            java.lang.System.out.print("Enter a username\n");
//            username = sc.next();
//        }
//        java.lang.System.out.print("Enter a password\n");
//        String password = sc.next();
//        java.lang.System.out.print("Enter a address\n");
//        String address = sc.next();
//        java.lang.System.out.print("Enter a email\n");
//        String email = sc.next();
//        java.lang.System.out.print("repeate a email\n");
//        String email2 = sc.next();
//        java.lang.System.out.print("Enter a creadit card number\n");
//        String credidCardNumber = sc.next();
//        java.lang.System.out.print("Enter a Name of Company\n");
//        String nameOfCompany = sc.next();
//
//        //creating user
//        User user = new Company(username, password, address, email, credidCardNumber, nameOfCompany);
//        system.singUp(user);
//        setNextState(States.SIGN_IN_STATE);
   }


}

