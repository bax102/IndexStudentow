package ps.sda.javagdy2.database.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student implements IBaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String imie;
    private String nazwisko;
    private boolean pelnoletni;
    private double wzrost;

    // student => ocena -> nauczyciel -> klasa ->

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "student")
    private List<Grade> gradeList;
}
