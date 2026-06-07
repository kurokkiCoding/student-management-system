/**
 * 成績評価可能なオブジェクトの契約
 */
public interface Evaluable {
    double calculateGPA();
    String getGradeLevel();
    boolean isEligibleForHonors();
}