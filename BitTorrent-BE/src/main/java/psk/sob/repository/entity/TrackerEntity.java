package psk.sob.repository.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tracker")
@Getter
@Setter
@NoArgsConstructor
public class TrackerEntity {

    @Id
    @Column(name = "id")
    private String id;
    private String status;

}
