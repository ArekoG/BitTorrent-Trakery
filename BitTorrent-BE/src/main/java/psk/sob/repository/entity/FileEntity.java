package psk.sob.repository.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "file")
@Getter
@Setter
@NoArgsConstructor
public class FileEntity {
    @Id
    @Column(name = "id")
    private String id;
    private double size;
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
