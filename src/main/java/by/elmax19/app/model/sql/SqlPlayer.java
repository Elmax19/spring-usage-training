package by.elmax19.app.model.sql;

import by.elmax19.app.model.Position;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "player")
@Getter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SqlPlayer {
    @Id
    @Column(name = "id", nullable = false)
    @Setter
    private String id;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "height", nullable = false)
    private Double height;

    @Column(name = "spike", nullable = false)
    private Integer spike;

    @Column(name = "block", nullable = false)
    private Integer block;

    @Column(name = "position", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Position position;

    @Column(name = "current_club", nullable = false)
    private String currentClub;

    @Column(name = "number", nullable = false)
    private Integer number;
}
