package states;

public abstract class SignedUserState extends State {
    public SignedUserState(States myState) {
        super(myState);
    }

    @Override
    public void draw() {

    }

    @Override
    public States getPreviousState(){return States.MAIN_STATE;};
}

