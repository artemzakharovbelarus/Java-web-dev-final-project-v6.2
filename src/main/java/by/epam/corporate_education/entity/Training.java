package by.epam.corporate_education.entity;


import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@Getter @Setter
@EqualsAndHashCode
@ToString
public class Training implements Serializable {
    private static final long serialVersionUID = 1898296863587580971L;

    /**
     * training main information
     */
    @Setter(AccessLevel.PRIVATE)
    private int idTraining;
    private String title;
    private String requirements;
    private String information;
    private boolean deletedStatus;
    private String city;
    private int hoursAmount;
    private int minMembers;
    private int maxMembers;
    private LocalDate startDate;
    private LocalDate endDate;
    private int idTrainer;
    private String trainingPhoto;

    public Training(int idTraining, String title, String requirements, String information, boolean deletedStatus,
                    String city, int hoursAmount, int minMembers, int maxMembers,
                    LocalDate startDate, LocalDate endDate, int idTrainer, String trainingPhoto) {
        setIdTraining(idTraining);
        setTitle(title);
        setRequirements(requirements);
        setInformation(information);
        setDeletedStatus(deletedStatus);
        setCity(city);
        setHoursAmount(hoursAmount);

        setMinMembers(minMembers);
        setMaxMembers(maxMembers);
        setStartDate(startDate);
        setEndDate(endDate);
        setIdTrainer(idTrainer);
        setTrainingPhoto(trainingPhoto);
    }

    public Training(int idTraining, String title, String requirements, String information, String city,
                    int hoursAmount, int minMembers, int maxMembers, LocalDate startDate, LocalDate endDate,
                    String trainingPhoto, int idTrainer){
        setIdTraining(idTraining);
        setTitle(title);
        setRequirements(requirements);
        setInformation(information);
        setCity(city);
        setHoursAmount(hoursAmount);
        setMinMembers(minMembers);
        setMaxMembers(maxMembers);
        setStartDate(startDate);
        setEndDate(endDate);
        setTrainingPhoto(trainingPhoto);
        setIdTrainer(idTrainer);
    }

    public Training(int idTraining, String title, String requirements, String information, String city,
                    int hoursAmount, int minMembers, int maxMembers, LocalDate startDate, LocalDate endDate, int idTrainer){
        setIdTraining(idTraining);
        setTitle(title);
        setRequirements(requirements);
        setInformation(information);
        setCity(city);
        setHoursAmount(hoursAmount);
        setMinMembers(minMembers);
        setMaxMembers(maxMembers);
        setStartDate(startDate);
        setEndDate(endDate);
        setIdTrainer(idTrainer);
    }
}
