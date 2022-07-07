package states;

public class SignUpState extends State {


    @Override
    public void draw() {
//        System.out.println("What User do uo want create? \nDriver(D), Company(C), Operator(O)");
//        boolean correctDecision = false; // boolian just to check if the user input was valid or not
//        String decision = sc.next();
//        while (!correctDecision) {
//            if (decision.equals("D")) {
//                setNextState(States.DRIVER_SIGN_UP);
//                correctDecision = true;
//            }
//            if (decision.equals("C")) {
//                setNextState(States.CUSTOMER_SIGN_UP);
//                correctDecision = true;
//            }
//            if (decision.equals("O")) {
//                setNextState(States.OPERATOR_SIGN_UP);
//                correctDecision = true;
//            }
//        }

    }

    public SignUpState(States myState) {
        super(myState);
    }


    @Override
    public States getPreviousState() {
    return States.MAIN_STATE;
    };
}
