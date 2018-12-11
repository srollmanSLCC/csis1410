package problem;

/**
 * Custom exception for student's name.
 */
public class InvalidStudentNameException extends Exception
{
    /**
     * Constructor takes an error message argument.
     * @param errorMessage the error message.
     */
    public InvalidStudentNameException(String errorMessage)
    {
        super(errorMessage);
    }
}
