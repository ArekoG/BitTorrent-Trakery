package psk.sob.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class DataTransfer {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ElementCollection
    @CollectionTable(name = "usersFromList")
    private List<Integer> usersFrom;
    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "tracker_id")
    private Tracker tracker;
    private String status;
}
