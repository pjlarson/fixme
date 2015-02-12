import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by pelarson on 02/12/2015.
 */


public class WidgetMaker {


    String location;


    public static void main(String[] args) {

        NissanLeaf myTesla = new NissanLeaf();
        NissanAltima myAltima = new NissanAltima();



        if(myTesla.getManufacturer() == myAltima.getManufacturer()){

        }else{
            System.out.println("All my cars were made by the same company!");
        }

        switch(myAltima.get_power_source()){
            case BIODIESEL:
                System.out.print("You are very conscientious.");
            case DIESEL:
                System.out.print("How interesting");
            case ELECTRIC:
                System.out.println("Very trendy");
            case GASOLINE:
                System.err.println("Keeping it old school");
                break;
            default:
                System.out.print("Unknown fuel type");
        }

        Collection carcoll = makeMyCollection(
                new Object[]{myTesla, myAltima}
        );


        //testing turning cars on and off
        for(Object mycar: carcoll){
            Vehicle v = (Vehicle)mycar;
            System.out.println("Testing turning car on");
            v.turnOn();
            System.out.println("Is vehicle on?: " + v.isOn());
            v.turnOff();
            System.out.println("Is vehicle on?: " + v.isOn());
        }

        System.out.println("Manufacturer: " + myTesla.getManufacturer() );
        System.out.println("Power source: " + myTesla.get_power_source());
        System.out.println("Opening Door: " );
        myTesla.open_door(DoorType.HATCH);
        System.out.println("Power source: " + myTesla.fuelRemaining() );
        myTesla.honk();

        System.out.println("Manufacturer: " + myAltima.getManufacturer() );
        System.out.println("Power source: " + myAltima.get_power_source() );
        System.out.println("Opening Door: " );
        myAltima.open_door(DoorType.HATCH);
        System.out.println("Power source: " + myAltima.fuelRemaining() );
        myAltima.honk();



    }

    static Collection makeMyCollection(Object[] cars){
        Collection carcollection =  new ArrayList();
        for(int ii=0; ii<cars.length; ii++){
            carcollection.add(cars[ii]);
        }
        return carcollection;
    }

    static interface Driveable{
        public int getWheelCount();
        public boolean hasWheels();
        public void open_door(DoorType doorType);
    }

    static interface Vehicle {
        public String getManufacturer();
        public boolean hasWheels();
        public FuelType get_power_source();
        public void honk();
        public double getMilesRemaining();
        double fuelRemaining();
        public void turnOn();
        public void turnOff();
        public boolean isOn();
    }

    // basic implementation
    static abstract class Car implements Vehicle, Driveable{

        @Override
        public int getWheelCount() {
            return 4;
        }

        @Override
        public void open_door(DoorType doorType)
        {
            System.err.print(doorType + "door was opened");
        }

        @Override
        public boolean hasWheels() {
            return true;
        }

        @Override
        public FuelType get_power_source() {
            return null;
        }

        @Override
        public void honk() {
            System.out.println("Beep beep");
        }

        @Override
        public double getMilesRemaining() {
            return 0;
        }

        @Override
        public double fuelRemaining() {
            return 0;
        }
    }

    /* specific implementation */
    static class NissanLeaf extends Car {

        private double powerRemaining = 0.8;
        private double totalMileage = 150;
        public boolean on = false;

        @Override
        public double getMilesRemaining() {
            return this.powerRemaining * totalMileage;
        }

        public String getManufacturer(){
            return "Nissan";
        }

        @Override
        public double fuelRemaining() {
            return this.powerRemaining;
        }

        @Override
        public FuelType get_power_source() {
            return FuelType.ELECTRIC;
        }

        public boolean isElectric(){
            return true;
        }

        public void turnOn(){
            this.on = true;
        }

        public void turnOff(){
            if(this.on){
                this.on = false;
            }
            System.out.println("The car is turned off");
        }

        public boolean isOn(){
            return this.on;
        }

    }

    static class NissanAltima extends Car{

        private double fuelLevel;


        @Override
        public String getManufacturer(){
            return "Nissan";
        }

        @Override
        public boolean hasWheels() {
            return true;
        }

        @Override
        public FuelType get_power_source() {
            return FuelType.ELECTRIC;
        }

        @Override
        public void honk() {
            System.out.println("Beep");
        }

        @Override
        public double getMilesRemaining() {
            return fuelLevel * 300;
        }

        @Override
        public double fuelRemaining() {
            return fuelLevel;
        }

        @Override
        public void turnOn() {
            return;
        }

        @Override
        public void turnOff() {
            return;
        }

        @Override
        public boolean isOn() {
            return false;
        }
    }




    enum DoorType{
        FRONT,
        REAR,
        HATCH;
    }

    enum FuelType{
        GASOLINE,
        DIESEL,
        BIODIESEL,
        ELECTRIC,
        MANUAL;
    }

}
