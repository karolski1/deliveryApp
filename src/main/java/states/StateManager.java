package states;

import gui.GuiManager;

import java.util.HashMap;
import java.util.Map;

public class StateManager {
    private Map<States,State> stateMap = new HashMap<>(); //map which key is a name of state and value is object of state
    private static State state = null;
    private static StateManager instance = null;
    private static int counter = 0;
    private static States currentState;
    private static States nextState;

    public static StateManager getInstance() {
        if (instance == null)
            instance = new StateManager() ;
        return instance;
    }
    public State getState() {
        return state;
    }

    public void registerState(State state)
    {
        stateMap.put(state.getMyState(),state); //key is a string name of the state and value is a object of type state
    };
    public void changeState (States newState)
    {
        state = findStateByName(newState);
    };
    public void run (){
        if (state==null) return; //check if state was already created

        currentState = state.getMyState();
        nextState = state.getNextState();

        //when states are different change state to next state
        if (currentState != nextState)
        {
            currentState=nextState;
            GuiManager.getInstance().changeState(currentState);
            state.draw();
            changeState(currentState);
        }
        //update GUI
        GuiManager.getInstance().update();


    };

    public State findStateByName(States name) {
        if(stateMap.containsKey(name))
        {
            return stateMap.get(name);
        }
            return null;
    }
}
