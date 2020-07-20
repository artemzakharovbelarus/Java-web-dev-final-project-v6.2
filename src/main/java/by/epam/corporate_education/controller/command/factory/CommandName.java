package by.epam.corporate_education.controller.command.factory;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CommandName {

    public final static String COMMAND_SIGN_IN = "sign-in";
    public final static String COMMAND_SIGN_OUT = "sign-out";
    public final static String COMMAND_SIGN_UP = "sign-up";
    public final static String COMMAND_CHANGE_PASSWORD = "change-password";
    public final static String COMMAND_REDIRECT_FORGOT_PASSWORD = "redirect-forgot-password";
    public final static String COMMAND_REDIRECT_SIGN_UP = "redirect-sign-up";
    public final static String COMMAND_VIEW_ALL_TRAININGS = "view-all-trainings";
    public final static String COMMAND_VIEW_TRAINING = "view-training";
    public final static String COMMAND_FORWARD_TO_MAIN = "forward-to-main";
    public final static String COMMAND_VIEW_ALL_USERS = "view-all-users";
    public final static String COMMAND_VIEW_USER = "view-user";
    public final static String COMMAND_REMOVE_TRAINING = "remove-training";
    public final static String COMMAND_RESTORE_TRAINING = "restore-training";
    public final static String COMMAND_EDIT_TRAINING = "edit-training";
    public final static String COMMAND_FORWARD_EDIT_TRAINING = "forward-edit-training";
    public final static String COMMAND_CHANGE_BANNED_STATUS = "change-banned-status";
    public final static String COMMAND_FORWARD_ENROLL_TRAINING = "forward-enroll-training";
    public final static String COMMAND_CHANGE_LOCALE = "change-locale";
    public final static String COMMAND_ENROLL_TRAINING = "enroll-training";
    public final static String COMMAND_VIEW_ALL_QUERIES = "view-all-queries";
    public final static String COMMAND_UNDO_QUERY = "undo-query";
    public final static String COMMAND_SET_LIKE = "like";
    public final static String COMMAND_SET_DISLIKE = "dislike";
    public final static String COMMAND_VIEW_ALL_QUERIES_TRAINER = "view-all-trainer-trainings";
    public final static String COMMAND_PUT_OFF_LIKE = "put-off-like";
    public final static String COMMAND_PUT_OFF_DISLIKE = "put-off-dislike";
    public final static String COMMAND_VIEW_TRAINING_QUERIES = "view-training-queries";
}
