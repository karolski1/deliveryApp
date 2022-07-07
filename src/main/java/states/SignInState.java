package states;

import gui.UserSigned;
import users.TypeOfUser;
import users.User;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SignInState extends State {

    public SignInState(States myState) {
        super(myState);
    }



    @Override
    public States getPreviousState() {
        return States.MAIN_STATE;
    }

    @Override
    public void draw() {
//        String password = "";
//        String username = "";
//        TypeOfUser typeOfUser = TypeOfUser.NONE;
//        while (typeOfUser == TypeOfUser.NONE) {
//            System.out.println("Enter username");
//            username = sc.next();
//            System.out.println("Enter password");
//            password = sc.next();
//            typeOfUser = system.singIn(username, password);
//        //if is correct that below shouldnt be print
//            if(typeOfUser == TypeOfUser.NONE)
//            System.out.println("Wrong Username or Password");
//        }
//
//        if(typeOfUser == TypeOfUser.COMPANY)
//        {
//            setNextState(States.CUSTOMER_SIGNED);//maybe use enum for every state becuse by string is very easy for a typo
//        }
//        else if (typeOfUser== TypeOfUser.DRIVER)
//        setNextState(States.DRIVER_SIGNED);
//        else if(typeOfUser== TypeOfUser.OPERATOR)
//        setNextState(States.OPERATOR_SIGNED);
//
//
//        /*if credential correct log in
//        * but i dont think so if this function should be responsible for log into system
//        *
//        * i think as well that the local variable should be change to some setter
//        * and maybe this setter will return bool just to do not back unnecessary to the previous window
//        *


    }}



