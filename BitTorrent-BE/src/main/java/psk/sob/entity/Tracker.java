package psk.sob.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Tracker {
    @Id
    @Column(name = "tracker_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String status;

    @OneToMany(mappedBy = "tracker")
    private Set<TrackerUsersList> trackerUsersList;
}
