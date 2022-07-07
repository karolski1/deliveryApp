package states;

public class CustomerSigned extends SignedUserState{
    public CustomerSigned(States myState) {
        super(myState);
    }



    @Override
    public States getPreviousState() {
        return States.MAIN_STATE;
    }
}
