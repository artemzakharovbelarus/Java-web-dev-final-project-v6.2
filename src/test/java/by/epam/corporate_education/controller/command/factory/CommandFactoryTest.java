package by.epam.corporate_education.controller.command.factory;

import by.epam.corporate_education.controller.command.Command;
import by.epam.corporate_education.controller.command.impl.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommandFactoryTest {

    CommandFactory commandFactory = CommandFactory.getINSTANCE();
    String commandName;

    @Test
    public void createCommand_signIn_correct(){
        //given
        //when
        commandName = CommandName.COMMAND_SIGN_IN;
        Command command = commandFactory.createCommand(commandName);
        //then
        assertTrue(command instanceof SignInCommand);
    }

    @Test
    public void createCommand_signIn_invalid(){
        //given
        //when
        commandName = "";
        Command command = commandFactory.createCommand(commandName);
        //then
        assertFalse(command instanceof SignInCommand);
    }

    @Test
    public void createCommand_signUp_correct(){
        //given
        //when
        commandName = CommandName.COMMAND_SIGN_UP;
        Command command = commandFactory.createCommand(commandName);
        //then
        assertTrue(command instanceof SignUpCommand);
    }

    @Test
    public void createCommand_signUp_invalid(){
        //given
        //when
        commandName = "";
        Command command = commandFactory.createCommand(commandName);
        //then
        assertFalse(command instanceof SignUpCommand);
    }

    @Test
    public void createCommand_signOut_correct(){
        //given
        //when
        commandName = CommandName.COMMAND_SIGN_OUT;
        Command command = commandFactory.createCommand(commandName);
        //then
        assertTrue(command instanceof SignOutCommand);
    }

    @Test
    public void createCommand_signOut_invalid(){
        //given
        //when
        commandName = "";
        Command command = commandFactory.createCommand(commandName);
        //then
        assertFalse(command instanceof SignOutCommand);
    }

    @Test
    public void createCommand_viewAllTrainings_correct(){
        //given
        //when
        commandName = CommandName.COMMAND_VIEW_ALL_TRAININGS;
        Command command = commandFactory.createCommand(commandName);
        //then
        assertTrue(command instanceof ViewAllTrainingsCommand);
    }

    @Test
    public void createCommand_viewAllTrainings_invalid(){
        //given
        //when
        commandName = "";
        Command command = commandFactory.createCommand(commandName);
        //then
        assertFalse(command instanceof ViewAllTrainingsCommand);
    }

    @Test
    public void createCommand_redirectForgotPassword_correct(){
        //given
        //when
        commandName = CommandName.COMMAND_REDIRECT_FORGOT_PASSWORD;
        Command command = commandFactory.createCommand(commandName);
        //then
        assertTrue(command instanceof RedirectToForgotPasswordCommand);
    }

    @Test
    public void createCommand_redirectForgotPassword_invalid(){
        //given
        //when
        commandName = "";
        Command command = commandFactory.createCommand(commandName);
        //then
        assertFalse(command instanceof RedirectToForgotPasswordCommand);
    }

    @Test
    public void createCommand_redirectSignUp_correct(){
        //given
        //when
        commandName = CommandName.COMMAND_REDIRECT_SIGN_UP;
        Command command = commandFactory.createCommand(commandName);
        //then
        assertTrue(command instanceof RedirectToSignUpCommand);
    }

    @Test
    public void createCommand_redirectSignUp_invalid(){
        //given
        //when
        commandName = "";
        Command command = commandFactory.createCommand(commandName);
        //then
        assertFalse(command instanceof RedirectToSignUpCommand);
    }

    @Test
    public void createCommand_forwardToMain_correct(){
        //given
        //when
        commandName = CommandName.COMMAND_FORWARD_TO_MAIN;
        Command command = commandFactory.createCommand(commandName);
        //then
        assertTrue(command instanceof ForwardToMainPageCommand);
    }

    @Test
    public void createCommand_forwardToMain_invalid(){
        //given
        //when
        commandName = "";
        Command command = commandFactory.createCommand(commandName);
        //then
        assertFalse(command instanceof ForwardToMainPageCommand);
    }

    @Test
    public void createCommand_viewAllUsers_correct(){
        //given
        //when
        commandName = CommandName.COMMAND_VIEW_ALL_USERS;
        Command command = commandFactory.createCommand(commandName);
        //then
        assertTrue(command instanceof ViewAllUsersCommand);
    }

    @Test
    public void createCommand_viewAllUsers_invalid(){
        //given
        //when
        commandName = "";
        Command command = commandFactory.createCommand(commandName);
        //then
        assertFalse(command instanceof ViewAllUsersCommand);
    }

    @Test
    public void createCommand_viewUser_correct(){
        //given
        //when
        commandName = CommandName.COMMAND_VIEW_USER;
        Command command = commandFactory.createCommand(commandName);
        //then
        assertTrue(command instanceof ViewUserCommand);
    }

    @Test
    public void createCommand_viewUser_invalid(){
        //given
        //when
        commandName = "";
        Command command = commandFactory.createCommand(commandName);
        //then
        assertFalse(command instanceof ViewUserCommand);
    }

    @Test
    public void createCommand_viewTraining_correct(){
        //given
        //when
        commandName = CommandName.COMMAND_VIEW_TRAINING;
        Command command = commandFactory.createCommand(commandName);
        //then
        assertTrue(command instanceof ViewTrainingCommand);
    }

    @Test
    public void createCommand_viewTraining_invalid(){
        //given
        //when
        commandName = "";
        Command command = commandFactory.createCommand(commandName);
        //then
        assertFalse(command instanceof ViewTrainingCommand);
    }

    @Test
    public void createCommand_removeTraining_correct(){
        //given
        //when
        commandName = CommandName.COMMAND_REMOVE_TRAINING;
        Command command = commandFactory.createCommand(commandName);
        //then
        assertTrue(command instanceof RemoveTrainingCommand);
    }

    @Test
    public void createCommand_removeTraining_invalid(){
        //given
        //when
        commandName = "";
        Command command = commandFactory.createCommand(commandName);
        //then
        assertFalse(command instanceof RemoveTrainingCommand);
    }

    @Test
    public void createCommand_restoreTraining_correct(){
        //given
        //when
        commandName = CommandName.COMMAND_RESTORE_TRAINING;
        Command command = commandFactory.createCommand(commandName);
        //then
        assertTrue(command instanceof RestoreTrainingCommand);
    }

    @Test
    public void createCommand_restoreTraining_invalid(){
        //given
        //when
        commandName = "";
        Command command = commandFactory.createCommand(commandName);
        //then
        assertFalse(command instanceof RestoreTrainingCommand);
    }

    @Test
    public void createCommand_editTraining_correct(){
        //given
        //when
        commandName = CommandName.COMMAND_EDIT_TRAINING;
        Command command = commandFactory.createCommand(commandName);
        //then
        assertTrue(command instanceof EditTrainingCommand);
    }

    @Test
    public void createCommand_editTraining_invalid(){
        //given
        //when
        commandName = "";
        Command command = commandFactory.createCommand(commandName);
        //then
        assertFalse(command instanceof EditTrainingCommand);
    }

    @Test
    public void createCommand_forwardEditTraining_correct(){
        //given
        //when
        commandName = CommandName.COMMAND_FORWARD_EDIT_TRAINING;
        Command command = commandFactory.createCommand(commandName);
        //then
        assertTrue(command instanceof ForwardEditTrainingCommand);
    }

    @Test
    public void createCommand_forwardEditTraining_invalid(){
        //given
        //when
        commandName = "";
        Command command = commandFactory.createCommand(commandName);
        //then
        assertFalse(command instanceof ForwardEditTrainingCommand);
    }

    @Test
    public void createCommand_changeBannedStatus_correct(){
        //given
        //when
        commandName = CommandName.COMMAND_CHANGE_BANNED_STATUS;
        Command command = commandFactory.createCommand(commandName);
        //then
        assertTrue(command instanceof ChangeBannedStatusCommand);
    }

    @Test
    public void createCommand_changeBannedStatus_invalid(){
        //given
        //when
        commandName = "";
        Command command = commandFactory.createCommand(commandName);
        //then
        assertFalse(command instanceof ChangeBannedStatusCommand);
    }

    @Test
    public void createCommand_forwardEnrollTraining_correct(){
        //given
        //when
        commandName = CommandName.COMMAND_FORWARD_ENROLL_TRAINING;
        Command command = commandFactory.createCommand(commandName);
        //then
        assertTrue(command instanceof ForwardEnrollTrainingCommand);
    }

    @Test
    public void createCommand_forwardEnrollTraining_invalid(){
        //given
        //when
        commandName = "";
        Command command = commandFactory.createCommand(commandName);
        //then
        assertFalse(command instanceof ForwardEnrollTrainingCommand);
    }

    @Test
    public void createCommand_changeLocale_correct(){
        //given
        //when
        commandName = CommandName.COMMAND_CHANGE_LOCALE;
        Command command = commandFactory.createCommand(commandName);
        //then
        assertTrue(command instanceof ChangeLocaleCommand);
    }

    @Test
    public void createCommand_changeLocale_invalid(){
        //given
        //when
        commandName = "";
        Command command = commandFactory.createCommand(commandName);
        //then
        assertFalse(command instanceof ChangeLocaleCommand);
    }

    @Test
    public void createCommand_enrollTraining_correct(){
        //given
        //when
        commandName = CommandName.COMMAND_ENROLL_TRAINING;
        Command command = commandFactory.createCommand(commandName);
        //then
        assertTrue(command instanceof EnrollTrainingCommand);
    }

    @Test
    public void createCommand_enrollTraining_invalid(){
        //given
        //when
        commandName = "";
        Command command = commandFactory.createCommand(commandName);
        //then
        assertFalse(command instanceof EnrollTrainingCommand);
    }

    @Test
    public void createCommand_viewAllQueries_correct(){
        //given
        //when
        commandName = CommandName.COMMAND_VIEW_ALL_QUERIES;
        Command command = commandFactory.createCommand(commandName);
        //then
        assertTrue(command instanceof ViewAllQueriesCommand);
    }

    @Test
    public void createCommand_viewAllQueries_invalid(){
        //given
        //when
        commandName = "";
        Command command = commandFactory.createCommand(commandName);
        //then
        assertFalse(command instanceof ViewAllQueriesCommand);
    }

    @Test
    public void createCommand_undoQuery_correct(){
        //given
        //when
        commandName = CommandName.COMMAND_UNDO_QUERY;
        Command command = commandFactory.createCommand(commandName);
        //then
        assertTrue(command instanceof UndoQueryCommand);
    }

    @Test
    public void createCommand_undoQuery_invalid(){
        //given
        //when
        commandName = "";
        Command command = commandFactory.createCommand(commandName);
        //then
        assertFalse(command instanceof UndoQueryCommand);
    }

    @Test
    public void createCommand_setLike_correct(){
        //given
        //when
        commandName = CommandName.COMMAND_SET_LIKE;
        Command command = commandFactory.createCommand(commandName);
        //then
        assertTrue(command instanceof PutTrainingLikeCommand);
    }

    @Test
    public void createCommand_setLike_invalid(){
        //given
        //when
        commandName = "";
        Command command = commandFactory.createCommand(commandName);
        //then
        assertFalse(command instanceof PutTrainingLikeCommand);
    }

    @Test
    public void createCommand_setDislike_correct(){
        //given
        //when
        commandName = CommandName.COMMAND_SET_DISLIKE;
        Command command = commandFactory.createCommand(commandName);
        //then
        assertTrue(command instanceof PutTrainingDislikeCommand);
    }

    @Test
    public void createCommand_setDislike_invalid(){
        //given
        //when
        commandName = "";
        Command command = commandFactory.createCommand(commandName);
        //then
        assertFalse(command instanceof PutTrainingDislikeCommand);
    }

    @Test
    public void createCommand_viewAllTrainerTrainings_correct(){
        //given
        //when
        commandName = CommandName.COMMAND_VIEW_ALL_TRAINER_TRAININGS;
        Command command = commandFactory.createCommand(commandName);
        //then
        assertTrue(command instanceof ViewAllTrainerTrainingsCommand);
    }

    @Test
    public void createCommand_viewAllTrainerTrainings_invalid(){
        //given
        //when
        commandName = "";
        Command command = commandFactory.createCommand(commandName);
        //then
        assertFalse(command instanceof ViewAllTrainerTrainingsCommand);
    }

    @Test
    public void createCommand_putOffLike_correct(){
        //given
        //when
        commandName = CommandName.COMMAND_PUT_OFF_LIKE;
        Command command = commandFactory.createCommand(commandName);
        //then
        assertTrue(command instanceof PutOffTrainingLikeCommand);
    }

    @Test
    public void createCommand_putOffLike_invalid(){
        //given
        //when
        commandName = "";
        Command command = commandFactory.createCommand(commandName);
        //then
        assertFalse(command instanceof PutOffTrainingLikeCommand);
    }

    @Test
    public void createCommand_putOffDislike_correct(){
        //given
        //when
        commandName = CommandName.COMMAND_PUT_OFF_DISLIKE;
        Command command = commandFactory.createCommand(commandName);
        //then
        assertTrue(command instanceof PutOffTrainingDislikeCommand);
    }

    @Test
    public void createCommand_SignUp_invalid(){
        //given
        //when
        commandName = "";
        Command command = commandFactory.createCommand(commandName);
        //then
        assertFalse(command instanceof PutOffTrainingDislikeCommand);
    }

    @Test
    public void createCommand_viewTrainingQueries_correct(){
        //given
        //when
        commandName = CommandName.COMMAND_VIEW_TRAINING_QUERIES;
        Command command = commandFactory.createCommand(commandName);
        //then
        assertTrue(command instanceof ViewTrainingQueriesCommand);
    }

    @Test
    public void createCommand_viewTrainingQueries_invalid(){
        //given
        //when
        commandName = "";
        Command command = commandFactory.createCommand(commandName);
        //then
        assertFalse(command instanceof ViewTrainingQueriesCommand);
    }

    @Test
    public void createCommand_setQueryAnswer_correct(){
        //given
        //when
        commandName = CommandName.COMMAND_SET_QUERY_ANSWER;
        Command command = commandFactory.createCommand(commandName);
        //then
        assertTrue(command instanceof SetQueryAnswerCommand);
    }

    @Test
    public void createCommand_setQueryAnswer_invalid(){
        //given
        //when
        commandName = "";
        Command command = commandFactory.createCommand(commandName);
        //then
        assertFalse(command instanceof SetQueryAnswerCommand);
    }
}