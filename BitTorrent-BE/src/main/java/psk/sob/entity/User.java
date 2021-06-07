package psk.sob.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String login;
    private String status;


    @OneToMany(mappedBy = "user")
    private Set<TrackerUsersList> trackerUsersList;
    @OneToMany(mappedBy = "user")
    private Set<File> fileList;
}
