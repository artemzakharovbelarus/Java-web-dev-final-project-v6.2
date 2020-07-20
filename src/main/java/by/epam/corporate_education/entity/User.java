package by.epam.corporate_education.entity;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@Getter @Setter
@EqualsAndHashCode
@ToString
public class User implements Serializable {
    private static final long serialVersionUID = 5464502705015445125L;

    /**
     * user main information
     */
    @Setter(AccessLevel.PRIVATE)
    private int idUser;
    private String username;
    private String email;
    private boolean leaderStatus;
    private boolean onlineStatus;
    private boolean bannedStatus;
    private UserStatus status;
    @ToString.Exclude
    private String userPhoto;

    /**
     * user additional information
     */
    private String name;
    private String surname;
    private int sex;
    private LocalDate birthDate;
    private String githubLink;
    private String linkedInLink;

    public User(int idUser, String username, String email, boolean leaderStatus, boolean onlineStatus,
                boolean bannedStatus, UserStatus status, String userPhoto){
        setIdUser(idUser);
        setUsername(username);
        setEmail(email);
        setLeaderStatus(leaderStatus);
        setBannedStatus(bannedStatus);
        setOnlineStatus(onlineStatus);
        setStatus(status);
        setUserPhoto(userPhoto);
    }

    public User(String username, String email){
        setUsername(username);
        setEmail(email);
    }
}
