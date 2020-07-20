package by.epam.corporate_education.entity;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Dislike {
    @Setter(AccessLevel.PRIVATE)
    private int idDislike;
    private boolean enabledStatus;
    private int idUser;
    private int idTraining;

    public Dislike(int idUser, int idTraining){
        setIdUser(idUser);
        setIdTraining(idTraining);
    }
}