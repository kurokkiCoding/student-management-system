// ============================================
// 4. 学生抽象クラス（Studentの共通機能）
// ============================================

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 学生の抽象基底クラス
 * すべての学生タイプの共通機能を定義
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class Student extends Person 
    implements Evaluable, Reportable {
    
    protected String major;
    protected int enrollmentYear;
    protected List<Grade> grades;
    protected double gpa;
    
    public Student(String id, String name, LocalDate birthDate, 
                  String email, String major, int enrollmentYear) {
        super(id, name, birthDate, email);
        this.major = major;
        this.enrollmentYear = enrollmentYear;
        this.grades = new ArrayList<>();
        this.gpa = 0.0;
    }
    
    /**
     * 成績追加（例外安全）
     */
    public void addGrade(Grade grade) throws InvalidGradeException {
        if (grade == null) {
            throw new InvalidGradeException("Grade cannot be null");
        }
        if (grade.getScore() < 0 || grade.getScore() > 100) {
            throw new InvalidGradeException("Score must be between 0 and 100: " + grade.getScore());
        }
        
        grades.add(grade);
        recalculateGPA();
    }
    
    /**
     * GPA再計算（内部処理）
     */
    private void recalculateGPA() {
        if (grades.isEmpty()) {
            this.gpa = 0.0;
            return;
        }
        
        double total = grades.stream()
                            .mapToDouble(Grade::getGpaPoint)
                            .sum();
        this.gpa = total / grades.size();
    }
    
    /**
     * 学生タイプ取得（抽象メソッド）
     */
    public abstract String getStudentType();
    
    /**
     * 必要単位数取得（抽象メソッド）
     */
    public abstract int getRequiredCredits();
    
    /**
     * 卒業要件チェック（抽象メソッド）
     */
    public abstract boolean canGraduate();
    
    // Evaluableインターフェースの実装（共通部分）
    @Override
    public double calculateGPA() {
        return this.gpa;
    }
    
    @Override
    public String getGradeLevel() {
        if (gpa >= 3.8) return "最優秀";
        if (gpa >= 3.5) return "優秀";
        if (gpa >= 3.0) return "良好";
        if (gpa >= 2.5) return "普通";
        return "要努力";
    }
    
    @Override
    public boolean isEligibleForHonors() {
        return gpa >= 3.8 && grades.size() >= 8;
    }
    
    // Reportableインターフェースの実装（共通部分）
    @Override
    public String generateSummary() {
        return String.format("%s（%s）- GPA: %.2f, 成績レベル: %s", 
                           name, getStudentType(), gpa, getGradeLevel());
    }

    // ============================================
    // ★ ここから追加メソッド（Week6 Lesson4）
    // ============================================

    /**
     * 学生の詳細情報を文字列で返すメソッド
     * Week 6レッスン4で追加：変更管理の実践用
     */
    public String getDetailedInfo() {
        StringBuilder info = new StringBuilder();
        info.append("=== 学生詳細情報 ===\n");
        info.append("学生ID: ").append(getId()).append("\n");
        info.append("氏名: ").append(getName()).append("\n");
        info.append("年齢: ").append(getAge()).append("歳\n");
        info.append("メールアドレス: ").append(getEmail()).append("\n");
        info.append("専攻: ").append(getMajor()).append("\n");
        info.append("====================");
        return info.toString();
    }

    /**
     * 学生の年齢区分を判定するメソッド
     * Week 6レッスン4で追加：Git変更追跡の体験用
     */
    public String getAgeCategory() {
        if (getAge() < 20) {
            return "10代";
        } else if (getAge() < 25) {
            return "20代前半";
        } else if (getAge() < 30) {
            return "20代後半";
        } else {
            return "30代以上";
        }
    }
}
