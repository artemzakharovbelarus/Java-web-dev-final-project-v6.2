package by.epam.corporate_education.entity;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Getter @Setter
@EqualsAndHashCode
@ToString
public class Team implements Serializable {
    private static final long serialVersionUID = 7653793543191737170L;

    /**
     * Team main information
     */
    @Setter(AccessLevel.PRIVATE)
    private int idTeam;
    private int peopleAmount;
    @Setter(AccessLevel.PRIVATE)
    @Getter(AccessLevel.PRIVATE)
    private List<User> students;
    private int idTraining;
    private int idLeader;

    public Team(int idTeam, int peopleAmount, List<User> students, int idTraining, int idLeader){
        setIdTeam(idTeam);
        setPeopleAmount(peopleAmount);
        setStudents(students);
        setIdTraining(idTraining);
        setIdLeader(idLeader);
    }

    public User getStudentById(int idUser){
        User user = students.get(idUser);
        return user;
    }

    public void addNewStudent(User user){
        students.add(user);
        peopleAmount++;
    }
}
