package states;

public class OperatorSigned extends SignedUserState{
    public OperatorSigned(States myState) {

        super(myState);
    }



    @Override
    public States getPreviousState() {
        return States.MAIN_STATE;
    }


}
