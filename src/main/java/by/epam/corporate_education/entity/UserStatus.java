package by.epam.corporate_education.entity;

public enum UserStatus {
    ADMIN(1),
    STUDENT(2),
    TEACHER(3);

    private int idStatus;

    UserStatus(int status){
        idStatus = status;
    }
    
    public int getIdStatus(){
        return idStatus;
    }
}
