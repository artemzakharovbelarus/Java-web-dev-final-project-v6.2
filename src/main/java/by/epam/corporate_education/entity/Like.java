package by.epam.corporate_education.entity;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Like {
    @Setter(AccessLevel.PRIVATE)
    private int idLike;
    private boolean enabledStatus;
    private int idUser;
    private int idTraining;

    public Like(int idUser, int idTraining){
        setIdUser(idUser);
        setIdTraining(idTraining);
    }
}
