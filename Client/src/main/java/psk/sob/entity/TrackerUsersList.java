package psk.sob.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TrackerUsersList {
    @Id
    @Column(name = "tracker_user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "tracker_id")
    private Tracker tracker;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String status;
}
