package problem;

public class InvalidStudentNameException extends Exception
{
    public InvalidStudentNameException(String errorMessage)
    {
        super(errorMessage);
    }
}
