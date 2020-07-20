package by.epam.corporate_education.entity;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Query {
    @Setter(AccessLevel.PRIVATE)
    private int idQuery;
    private int idTraining;
    private int idUser;
    private String username;
    private int acceptedStatus;
    private boolean canceledStatus;
    private String trainingTitle;
    private LocalDate startDate;

    public Query(int idQuery, int idTraining, int idUser, boolean canceledStatus, String trainingTitle, LocalDate startDate){
        setIdQuery(idQuery);
        setIdTraining(idTraining);
        setIdUser(idUser);
        setCanceledStatus(canceledStatus);
        setTrainingTitle(trainingTitle);
        setStartDate(startDate);
    }

    public Query(int idTraining, int idUser){
        setIdTraining(idTraining);
        setIdUser(idUser);
    }

    public Query(int idQuery, int idTraining, int idUser, boolean canceledStatus){
        setIdQuery(idQuery);
        setIdTraining(idTraining);
        setIdUser(idUser);
        setCanceledStatus(canceledStatus);
    }
    public Query(int idQuery, String username, String trainingTitle, int acceptedStatus, LocalDate startDate){
        setIdQuery(idQuery);
        setUsername(username);
        setTrainingTitle(trainingTitle);
        setAcceptedStatus(acceptedStatus);
        setStartDate(startDate);
    }
}
