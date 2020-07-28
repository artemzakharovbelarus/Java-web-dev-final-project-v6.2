package by.epam.corporate_education.dao.util;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ColumnName {

    /**
     * column titles for table 'users'
     */
    public static final String USER_ID_USER = "idUser";
    public static final String USER_USERNAME = "username";
    public static final String USER_PASSWORD = "password";
    public static final String USER_EMAIL = "email";
    public static final String USER_LEADER_STATUS = "leader_status";
    public static final String USER_BANNED_STATUS = "banned_status";
    public static final String USER_ONLINE_STATUS = "online_status";
    public static final String USER_USER_PHOTO= "user_photo";
    public static final String USER_ID_ROLE = "idRole";

    /**
     * column titles for table 'users_additional_info'
     */
    public static final String USER_INFO_NAME = "name";
    public static final String USER_INFO_SURNAME = "surname";
    public static final String USER_INFO_SEX = "sex";
    public static final String USER_INFO_BIRTH_DATE = "birth_date";
    public static final String USER_INFO_GITHUB = "github";
    public static final String USER_INFO_LINKEDIN = "linkedIn";

    /**
     * column titles for table 'trainings'
     */
    public static final String TRAINING_ID_TRAINING = "idTraining";
    public static final String TRAINING_TITLE = "title";
    public static final String TRAINING_REQUIREMENTS = "requirements";
    public static final String TRAINING_INFORMATION = "information";
    public static final String TRAINING_DELETED_STATUS = "deleted_status";
    public static final String TRAINING_CITY = "city";
    public static final String TRAINING_HOURS_AMOUNT = "hours_amount";
    public static final String TRAINING_MIN_MEMBERS = "min_members";
    public static final String TRAINING_MAX_MEMBERS = "max_members";
    public static final String TRAINING_START_DATE = "start_date";
    public static final String TRAINING_END_DATE = "end_date";
    public static final String TRAINING_IMAGE = "training_image";
    public static final String TRAINING_ID_USER = "idUser";

    /**
     * column titles for table 'queries'
     */
    public static final String QUERY_ID_QUERY = "idQuery";
    public static final String QUERY_CANCELED_STATUS = "canceled_status";
    public static final String QUERY_ACCEPTED_STATUS = "accepted_status";

    /**
     * column title for count requests
     */
    public static final String TOTAL = "total";

    /**
     * column title for 'likes' and 'dislikes' tables
     */
    public static final String ENABLED_STATUS = "enabled_status";

    /**
     * column titles for table 'news'
     */
    public static final String NEWS_ID_NEWS = "idNews";
    public static final String NEWS_INTRO_NEWS = "news_intro";
    public static final String NEWS_TEXT_NEWS = "news_text";
    public static final String NEWS_IMAGE_NEWS = "news_image";
    public static final String NEWS_ID_USER = "idUser";

}
