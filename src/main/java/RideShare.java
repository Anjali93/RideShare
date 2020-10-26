import enums.VehicleType;
import enums.location;
import model.Ride;
import model.RideUser;
import model.Vehicle;
import service.EarliestEndTime;
import service.FastestRide;
import service.RideShareExecutor;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class RideShare {

    public static void main(String[] args) {

        RideShareExecutor rs = new RideShareExecutor();

        rs.addUser(RideUser.builder().id(1).name("Aman").vehicles(new ArrayList<>()).offeredRides(new ArrayList<>()).sharedRides(new ArrayList<>()).build());
        rs.addUserVehicle(1, Vehicle.builder().name("Swift").type(VehicleType.CAR).number("ka-02-1123").build());

        rs.addUser(RideUser.builder().id(2).name("Neha").vehicles(new ArrayList<>()).offeredRides(new ArrayList<>()).sharedRides(new ArrayList<>()).build());
        rs.addUserVehicle(2, Vehicle.builder().name("Scooty").type(VehicleType.SCOOTY).number("ka-02-1128").build());

        rs.addUser(RideUser.builder().id(3).name("Angad").vehicles(new ArrayList<>()).sharedRides(new ArrayList<>()).offeredRides(new ArrayList<>()).build());

        rs.addUser(RideUser.builder().id(4).name("Anjali").vehicles(new ArrayList<>()).offeredRides(new ArrayList<>()).sharedRides(new ArrayList<>()).build());

        rs.addUser(RideUser.builder().id(5).name("Nandu").vehicles(new ArrayList<>()).sharedRides(new ArrayList<>()).offeredRides(new ArrayList<>()).build());
        rs.addUserVehicle(5, Vehicle.builder().name("nexon").type(VehicleType.CAR).number("ka-02-1120").build());

        rs.offerRide(Ride.builder().id(1).availableSeat(3).startTime(LocalDateTime.now()).duration(5).source(location.DELHI).destination(location.CHANDIGARH).build());
        rs.offerRide(Ride.builder().id(2).availableSeat(1).startTime(LocalDateTime.of(2020, 10, 28, 04, 05,00)).duration(3).source(location.DELHI).destination(location.CHANDIGARH).build());
        //rs.offerRide(Ride.builder().id(3).availableSeat(3).startTime(LocalDateTime.now()).duration(5).source(location.DELHI).destination(location.CHANDIGARH).build());

        System.out.println(rs.getAvailableRides().toString());
        System.out.println("Selected Rides are:       ");
        Ride ride = rs.selectRide(1, location.DELHI, location.CHANDIGARH, new EarliestEndTime());
        if(ride!= null)
            System.out.println(ride.toString());

        ride = rs.selectRide(3, location.DELHI, location.CHANDIGARH, new EarliestEndTime());
        if(ride!= null)
            System.out.println(ride.toString());

        ride = rs.selectRide(4, location.DELHI, location.CHANDIGARH, new FastestRide());
        if(ride!= null)
            System.out.println(ride.toString());

        ride = rs.selectRide(5, location.DELHI, location.CHANDIGARH, new FastestRide());
        if(ride!= null)
            System.out.println(ride.toString());

        ride = rs.selectRide(4, location.DELHI, location.CHANDIGARH, new FastestRide());
        if(ride!= null)
            System.out.println(ride.toString());

        System.out.println("Fuel saved by  1   " + rs.getSavedFuel(1));
        System.out.println("Fuel saved by  2  " + rs.getSavedFuel(2));
        System.out.println("Fuel saved by  3   " + rs.getSavedFuel(3));
        System.out.println("Fuel saved by  4   " + rs.getSavedFuel(4));
        System.out.println("Fuel saved by  5   " + rs.getSavedFuel(5));
    }
}
