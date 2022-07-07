package states;

import engine.System;

import java.util.Scanner;

/**
* <h1>Abstract class State</h1>
* With this class we make possible to have states in our app. This way it is easier
* to handle the give the correct rights on which user has the authentication to do what.
*
*
*/
public abstract  class State {
    private final States myState;
    private static States nextState = null;
    public final Scanner sc=new Scanner(java.lang.System.in);
    public final System system = System.getInstance();
    public State(States myState) {
        this.myState = myState;
        //initialize value to next state
        nextState = getMyState();
        draw();
    }

    public void setNextState(States nextState) {
        this.nextState = nextState;
    }

    public final States getMyState() {
        return myState;
    }

    public final States getNextState(){return nextState;};
    public abstract States getPreviousState();
    /*this function is baisically taking user input and printing into terminal

    * */
    public abstract void draw();

}
