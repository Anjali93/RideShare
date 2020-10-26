package interfaces;

import model.Ride;

import java.util.List;
import java.util.Optional;

public interface SelectionStrategy {

    public Optional<Ride> select(List<Ride> allRides, Integer userId);
}
