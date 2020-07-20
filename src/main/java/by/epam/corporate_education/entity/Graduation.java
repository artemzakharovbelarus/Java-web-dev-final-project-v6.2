package by.epam.corporate_education.entity;

import lombok.*;

import java.io.Serializable;

@Data
public class Graduation implements Serializable {
    private static final long serialVersionUID = -7825005575199750008L;

    /**
     * Graduation main information
     */
    @Setter(AccessLevel.PRIVATE)
    private int idGraduation;
    private int mark;
    private float attendance;
    private byte[] feedback;
    private int idTeam;
}
