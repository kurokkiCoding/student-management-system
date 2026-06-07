/**
 * 学生が見つからない例外
 */
public class StudentNotFoundException extends StudentManagementException {
    public StudentNotFoundException(String studentId) {
        super("Student not found: " + studentId);
    }
}