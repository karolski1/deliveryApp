package engine;


import java.lang.String; //For string Split

import products.Component;
import statistic.CompanyStatistic;
import statistic.DriverStatistics;
import statistic.OperatorStatistic;
import truck.*;
import users.*;


import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * <h1>The main System</h1>
 * The below class represents the system/"kernel" of our app. Here we store
 * all the important information about our app and many as expected
 * behaviors are implemented here.
 *
 * <b>Note:</b> Zips and the trucks are loaded from the given zips.txt and fleet.txt respectively.
 * <b> The Configurations via the Serialization method, are saved the first time we run the app
 * in a binary file. Then after each time we open the file, we have the same configurations.
 */

public class System {

    private Configuration configuration;
    private List<String> zipCodes = new ArrayList<>();
    private double discount; // Check if its from configurations
    private int maxNumberOfRetries; // Check if its from configurations
    private ArrayList<User> users = new ArrayList<>();
    private List<truck.Truck> trucks = new ArrayList<>(); //We have the fleet.txt and create trucks based on the txt
    private List<Order> orders = new ArrayList<>();

    private List<TruckAssigment> truckAssigment = new ArrayList<>();


    private List<Package> packages = new ArrayList<>();
    private static System instance = null;
    private String currentLogInUser;

    private DeliveryPlan deliveryPlan = null;


    private System() {

        readUserObject();
        loadTrucks("fleet.txt");
        loadZips("zips.txt");
    }

    //my idea is to sort the list first by the urgent than by date and at the and by times of trying to deliver

    // i should sort packages not orders
    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    private void sortOrders() {

        Comparator<Order> comparator = Comparator.comparing(order -> !order.isUrgent());
        comparator = comparator.thenComparing(Comparator.comparing(order -> order.getOrderDate()));
        comparator = comparator.thenComparing(Comparator.comparing(order -> order.getRetry()));
        // Sort the stream:
        Stream<Order> orderStream = orders.stream().sorted(comparator);
        orders = orderStream.collect(Collectors.toList());

        //just to testing
        for (Order order : orders)
            java.lang.System.out.println(order);
    }

    public String getCurrentLogInUser() {
        return currentLogInUser;
    }

    public List<Truck> getTrucks() {
        return trucks;
    }

    public User getCurrentUser() {
        return getUserByUsername(currentLogInUser);
    }

    public List<TruckAssigment> getTruckAssigment() {
        return truckAssigment;
    }

    public Boolean addToPackage(Component component, String addres) {
        for (Package pack : packages) {
            java.lang.System.out.println("pack=" + pack.getTypeOfPackage() + " comp=" + component.getTypeOfProduct());
            java.lang.System.out.println("");
            if ((!pack.getTypeOfPackage().equals(TypeOfProduct.Batch))
                    && (!pack.getTypeOfPackage().equals(TypeOfProduct.Fragile))) {
                if (component.getTypeOfProduct().equals(TypeOfProduct.Food)) {
                    //we cannot mix frozen and non frozen
                }
                if (pack.getTypeOfPackage().equals(component.getTypeOfProduct())) {
                    if (addres.equals(pack.getAddress())) {

                        if (pack.addProduct(component)) {
                            return true;
                        }

                    }
                }
            }
        }
        return false;
    }

    public void createPackage() {

        sortOrders();
        for (Order order : orders) {
            for (Component comp : order.getOurOrder().values()) {
                if (!addToPackage(comp, order.getDeliveryAddress())) {
                    Package pack = new Package(comp.getTypeOfProduct(), order.getDeliveryAddress());
                    pack.addProduct(comp);
                    packages.add(pack);

                }
            }
        }
        orders.clear();
        printPackage();
    }

    public void setTruckAssigment() {

        for (Truck truck : trucks) {
            List<Package> packageList = new ArrayList<>();

            for (Package pack : packages) {
                TypeOfProduct type = pack.typeOfProduct;
                if (pack.typeOfProduct.equals(TypeOfProduct.Batch)) {
                    //checking what type of products are in this batch
                    type = pack.packageContent.get(0).getTypeOfProduct();

                }
                if(pack.getDeliveryState().equals(PackageState.TRUCKNOASSIGNED))
                {
                if (truck instanceof RefrigeratedTruck && (type.equals(TypeOfProduct.Food))) {
                pack.setDelivered(PackageState.TRUCKASSIGNED);
                    packageList.add(pack);
                }else if (truck instanceof StandardTruck && (type.equals(TypeOfProduct.Standard) ||
                        type.equals(TypeOfProduct.Fragile))) {
                    pack.setDelivered(PackageState.TRUCKASSIGNED);
                    packageList.add(pack);
                }else if (truck instanceof SpecialVolumeTruck && type.equals(TypeOfProduct.SpecialVolume)) {
                    pack.setDelivered(PackageState.TRUCKASSIGNED);
                    packageList.add(pack);

                }
                }

            }

            if (!packageList.isEmpty()) {
                java.lang.System.out.println("pacl="+packageList+"truck="+truck.getLicensePlate());
                java.lang.System.out.println();
                truck.setState(StateOfTruck.ASSIGNED);
                TruckAssigment assigment = new TruckAssigment(truck, packageList);
                truckAssigment.add(assigment);


            }

        }
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        deliveryPlan = new DeliveryPlan(formatter.format(new Date()), truckAssigment);
    }

    private Truck findTruckByLincesePlate(String licensePlate)
    {
        for(Truck truck:trucks)
            if (truck.getLicensePlate().equals(licensePlate))
                return truck;
    return null;
    }
    public void assignTruckToDriver(String LicensePlate, Driver driver) {
        for (TruckAssigment truckAss : truckAssigment) {
            if (truckAss.getLicensePlate().equals(LicensePlate)) {

                truckAss.setDriver(driver);
                driver.setAssign(true);
        Truck tr = findTruckByLincesePlate(truckAss.getLicensePlate());
        if(tr != null)
            tr.setState(StateOfTruck.READY);
            driver.setTruckAssigment(truckAss);
        }

        }
        if (deliveryPlan != null) {
            deliveryPlan.updateTruckAssigments(truckAssigment);
        }


    }


    public Driver findDriverByName(String name) {
        for (User user : users)
            if (user.getUsername().equals(name) && user instanceof Driver)
                return (Driver) user;
        return null;

    }


    private void printPackage() {
        java.lang.System.out.println("size=" + packages.size());
        for (Package p : packages) {
            java.lang.System.out.println("package=" + p);
            java.lang.System.out.println();
            java.lang.System.out.println();

        }
    }

    public static engine.System getInstance() {
        if (instance == null)
            instance = new System();

        return instance;
    }

    //saving array of users to the file test.json
    private void saveUsersObject() {
        try (
                FileOutputStream fs = new FileOutputStream("test.json")) {
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(users);
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Reading user object from file
     * */
    private void readUserObject() {
        users.clear();
        try (
                FileInputStream fi = new FileInputStream("test.json")) {
            ObjectInputStream os = new ObjectInputStream(fi);
            // @SuppressWarnings("unchecked")
            users = (ArrayList<User>) os.readObject();
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public DeliveryPlan getDeliveryPlan() {
        return this.deliveryPlan;
    }

    public boolean validateZip(String zipCode) {
        if (zipCodes.contains(zipCode)){
            return true;
        }
        return false;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    // fileName == zips.txt
    public void loadZips(String fileName) {
        try {
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                zipCodes.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // fileName == fleet.txt
    public void loadTrucks(String fileName) {

        try {
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] parts = data.split("[:]"); // We split the given string from .txt with ":" as the "splitter"
                if (parts.length == 3) //omitting empty line in file and lines with wrong pattern
                {
                    // We check what type of truck we will create
                    if (parts[2].equals("STANDARD")) {
                        Truck truck = new StandardTruck(parts[0], Integer.parseInt(parts[1]));
                        trucks.add(truck);
                    } else if (parts[2].equals("SPECIAL")) {
                        Truck truck = new SpecialVolumeTruck(parts[0], Integer.parseInt(parts[1]));
                        trucks.add(truck);
                    } else if (parts[2].equals("REFRIGERATED")) {
                        Truck truck = new RefrigeratedTruck(parts[0], Integer.parseInt(parts[1]));
                        trucks.add(truck);
                    }
                }
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public String printDeliveryInformation(Driver driver){
        String str;
        String addr="";
        int count = 1;

        if(deliveryPlan == null){
            return "No delivery plan yet!";
        } 

        TruckAssigment truckAssigm = deliveryPlan.getTruckAssigment(driver);
        for(Package pac : truckAssigm.getPackages()){
            addr += "Address no." + count + " is " + pac.getAddress() + "\n";
            count++;
        }
        str = "License Plate is " + truckAssigm.getLicensePlate() + "\n" + addr;

        return str;
    }

    // From a file so the argument will be a file,Probably here we will fill the
    // truck list
    public void printCompanyStatistics(String companyName) {
        int numberOfOrders = 0;
        BigDecimal moneySpent = new BigDecimal(0);
        for (Order element : orders) {
            if (element.getCompanyName().equals(companyName)) {
                numberOfOrders++;
                moneySpent.add(element.getOrderPrice());
            }
        }
        CompanyStatistic companyStatistic = new CompanyStatistic(numberOfOrders, moneySpent);
        companyStatistic.print();
    }

    public String printCompanyStatisticsStr(String companyName) {
        int numberOfOrders = 0;
        BigDecimal moneySpent = new BigDecimal(0);
        for (Order element : orders) {
            if (element.getCompanyName() == null) continue;
            if (element.getCompanyName().equals(companyName)) {
                numberOfOrders++;
                moneySpent.add(element.getOrderPrice());
            }
        }
        CompanyStatistic companyStatistic = new CompanyStatistic(numberOfOrders, moneySpent);
        return companyStatistic.printStr();
    }

    public String printDriverStatisticsStr(String driverUsername) {
        int numberOfOrders = 0, numberOfPackagesDelivered = 0, numberOfPackagesNonDelivered = 0;
        for (Order ord : orders) {
            if(ord.getDriver() == null) continue;
            if (ord.getDriver().getUsername().equals(driverUsername)) {
                numberOfOrders++;
                if (ord.getDelivered().equals(PackageState.DELIVERED)) numberOfPackagesDelivered++;
                else if (ord.getDelivered().equals(PackageState.WILLNOTBEDELIVERED)) numberOfPackagesNonDelivered++;
            }
        }
        DriverStatistics driverStatistic = new DriverStatistics(numberOfOrders, numberOfPackagesDelivered, numberOfPackagesNonDelivered,findDriverByName(driverUsername).getFuelConsumption());
        return driverStatistic.printStr();
    }

    public void printOperatorStatistics(String operatorUsername) {
        int numberOfOrders = 0;
        BigDecimal incomePerMonth = new BigDecimal(0);
        for (Order element : orders) {
            numberOfOrders++;
            incomePerMonth.add(element.getOrderPrice());
        }

        OperatorStatistic operatorStatistic = new OperatorStatistic(numberOfOrders, incomePerMonth);
        operatorStatistic.print();
    }


    public void addOrder(Order order) {
        orders.add(order);
    }

    public List<Order> getOrdersByCompany(String Company) {
        List<Order> compOrders = new ArrayList<>();
        for (Order ord : orders) {
            if (ord.getCompanyName() == null) continue;
            if (ord.getCompanyName().equals(Company))
                compOrders.add(ord);
        }
        return compOrders;
    }


    public TypeOfUser singIn(String username, String password) {
        User user = getUserByUsername(username);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                currentLogInUser = username;
                if (user instanceof Company)
                    return TypeOfUser.COMPANY;
                else if (user instanceof Driver)
                    return TypeOfUser.DRIVER;
                else if (user instanceof Operator)
                    return TypeOfUser.OPERATOR;
            }
        }
        return TypeOfUser.NONE;
    }

    //users array contain all type of users
    public void singUp(User user) {
        users.add(user);
        saveUsersObject();
    }


    /*
     * this function is just need to do not repeat the code and have to just return
     * an Object in type user which the system
     * already created
     */
    private User getUserByUsername(String username) {
        if (!users.isEmpty()) {
            for (User user : users) {
                if (user.getUsername().equals(username)) {
                    return user;
                }
            }
        }
        return null; // if we dont find a user we should return null
    }

    public boolean checkIfCustomerExist(String username) {
        if (!checkUsername(username))
            if (getUserByUsername(username) instanceof Company)//checking if the user is not allready created
                return true;
        return false;
    }

    public boolean checkUsername(String username) {
        if (getUserByUsername(username) == null)//checking if the user is not allready created
            return true;
        return false;
    }

    public void disableDriver(String driver){
        for(User driv : users){
            if(driv.getUsername().equals(driver)){
                ((Driver) driv).setOnRest(true);
                return;
            }
        }
    }

    public void enableDriver(String driver){
        for(User driv : users){
            if(driv.getUsername().equals(driver)){
                ((Driver) driv).setOnRest(false);
                return;
            }
        }
    }

    public void disableTruck(String licensePlate){
        for(Truck trck : trucks){
            if(trck.getLicensePlate().equals(licensePlate)){
                trck.setState(StateOfTruck.DISABLED);
                return;
            }
        }
    }

    public void enableTruck(String licensePlate){
        for(Truck trck : trucks){
            if(trck.getLicensePlate().equals(licensePlate)){
                trck.setState(StateOfTruck.READY);
                return;
            }
        }
    }
}