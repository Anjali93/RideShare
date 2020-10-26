package interfaces;

import enums.location;
import model.Ride;
import model.RideUser;
import model.Vehicle;

public interface RideProperties {

    public void addUser(RideUser user);
    public void addUserVehicle(Integer userId, Vehicle vehicle);
    public void offerRide(Ride ride);
    public Ride selectRide(Integer userId, location source, location destination, SelectionStrategy strategy);
}
