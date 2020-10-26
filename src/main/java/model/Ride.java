package model;

import enums.location;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Ride {

    private Integer id;
    private location source;
    private location destination;
    private int availableSeat;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int duration;
}
