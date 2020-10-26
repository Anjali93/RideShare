package service;

import interfaces.SelectionStrategy;
import model.Ride;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class FastestRide implements SelectionStrategy {


    @Override
    public Optional<Ride> select(List<Ride> allRides, Integer userId) {

       return Optional.of(allRides.stream().filter(ride -> !ride.getId().equals(userId) && ride.getAvailableSeat() >=1).
               min(Comparator.comparingInt(Ride::getDuration)).get());
    }
}
