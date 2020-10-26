package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RideUser {

    private String name;
    private int id;
    private int fuelSaved;
    private List<Ride> sharedRides = new ArrayList<>();
    private List<Vehicle> vehicles = new ArrayList<>();
    private List<Ride> offeredRides = new ArrayList<>();

    public boolean addOfferedRide(Ride ride) {

        boolean cantOffer = offeredRides.stream().anyMatch(ride1 ->
                (ride.getStartTime().isBefore(ride1.getEndTime()) && ride.getStartTime().isAfter(ride1.getStartTime()))
                || (ride.getEndTime().isAfter(ride1.getStartTime()) && ride.getStartTime().isBefore(ride1.getStartTime())));

        if(!cantOffer){
            offeredRides.add(ride);
            return true;
        }

        System.out.println("Overlapping Ride, Can't offer");
        return false;
    }


    public void addShareRide(Ride ride) {

        sharedRides.add(ride);
        fuelSaved += ride.getDuration();
    }


    public int calculateFuelSaved() {
        return fuelSaved;
    }

    public void addVehicle(Vehicle vehicle){

        vehicles.add(vehicle);
    }
}
