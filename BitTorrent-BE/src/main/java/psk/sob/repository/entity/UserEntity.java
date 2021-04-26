package psk.sob.repository.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user", schema = "torrent")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {

    @Id
    @Column(name = "id")
    private String id;
    private String status;

    @ManyToOne
    @JoinColumn(name = "tracker_id")
    private TrackerEntity tracker;
}
