public interface Evaluable {
    double calculateGPA();
    String getGradeLevel();
    boolean isEligibleForHonors();
}

interface Reportable {
    String generateReport();
    String generateSummary();
}

interface ScholarshipEligible {
    boolean isEligibleForScholarship();
    double getScholarshipAmount();
    String getScholarshipType();
}
