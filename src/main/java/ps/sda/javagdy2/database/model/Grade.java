package ps.sda.javagdy2.database.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Grade implements IBaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // @Enumerated -- aby w bazie wartości były prezentowane jako varchar
    @Enumerated(EnumType.STRING)
    private Przedmiot przedmiot;

    //    private Double ocena; // może przyjąć null
    private double ocena; // nie może przyjąć null

    @CreationTimestamp
    private LocalDateTime dataDodania;

    @ManyToOne()
    private Student student;

    public Grade(Przedmiot przedmiot, double ocena) {
        this.przedmiot = przedmiot;
        this.ocena = ocena;
    }
}