/**
 * 不正な成績データ例外
 */
public class InvalidGradeException extends StudentManagementException {
    public InvalidGradeException(String message) {
        super("Invalid Grade: " + message);
    }
}