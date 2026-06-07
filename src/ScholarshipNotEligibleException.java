/**
 * 奨学金申請条件不適合例外
 */
public class ScholarshipNotEligibleException extends StudentManagementException {
    public ScholarshipNotEligibleException(String reason) {
        super("Scholarship not eligible: " + reason);
    }
}