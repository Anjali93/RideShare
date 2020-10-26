package service;


import model.Pair;
import enums.location;
import interfaces.RideProperties;
import interfaces.SelectionStrategy;
import lombok.*;
import model.Ride;
import model.RideUser;
import model.Vehicle;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class RideShareExecutor implements RideProperties {

    private HashMap<Integer, RideUser> users = new HashMap<>();
    private HashMap<Pair, LinkedList<Ride>> availableRides = new HashMap<>();


    @Override
    public void addUser(RideUser user) {

        if(users.containsKey(user.getId()))
            System.out.println("User already exists");
        else
            users.put(user.getId(), user);
    }

    @Override
    public void addUserVehicle(Integer userId, Vehicle vehicle) {

        if(users.containsKey(userId)){
            users.get(userId).addVehicle(vehicle);
        }
        else
            System.out.println("User doesnt exist");
    }

    @Override
    public void offerRide(Ride ride) {

        LocalDateTime endTime = ride.getStartTime().plusHours(ride.getDuration());
        ride.setEndTime(endTime);
        if(users.containsKey(ride.getId())){
            if(users.get(ride.getId()).addOfferedRide(ride)) {
                if (availableRides.containsKey(new Pair(ride.getSource(), ride.getDestination()))) {
                    availableRides.get(new Pair(ride.getSource(), ride.getDestination())).add(ride);
                } else
                    availableRides.put(new Pair(ride.getSource(), ride.getDestination()),
                            new LinkedList<Ride>(Arrays.asList(ride)));
            }
        }
        else
            System.out.println("Driver doesnt exist");
    }

    @Override
    public Ride selectRide(Integer userId, location source, location destination, SelectionStrategy strategy) {

        Pair p = new Pair(source, destination);
        if(availableRides.containsKey(p)){
            Optional<Ride> res = strategy.select(availableRides.get(p), userId);

            if(!res.isPresent()){
                System.out.println("No rides available, please try later");
                return null;
            }

            Ride ride = res.get();

            int availableSeats = ride.getAvailableSeat();
            if(availableSeats ==1){
                availableRides.get(p).remove(ride);
            }
            else {
                int index = availableRides.get(p).indexOf(ride);
                ride.setAvailableSeat(availableSeats-1);
                availableRides.get(p).set(index, ride);
            }
            users.get(userId).addShareRide(ride);
            return ride;
        }
        else {
            System.out.println("No such Route available");
            return null;
        }

    }
}
