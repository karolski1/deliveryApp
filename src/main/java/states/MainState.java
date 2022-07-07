package states;

public class MainState extends State {
    public MainState(States myState) {
        super(myState);
    }

    @Override
    public States getPreviousState() {
        return States.MAIN_STATE;
    }

    @Override
    public void draw() {
//        //is too much repeated that variable we have to change that late
//        boolean correctString = false;
//        String decision;
//        while (!correctString) {
//            System.out.print("signIn(I)/signUp(U)");
//            decision = sc.next();
//            if (decision.equals("I"))
//            {
//                setNextState(States.SIGN_IN_STATE);
//                correctString = true;
//
//            }
//            else if (decision.equals("U"))
//            {
//                setNextState(States.SIGN_IN_STATE);
//                correctString = true;
//            }
//            else
//            {
//                System.out.print("Wrong options \n");
//            }
//
//        }

        }
    }
