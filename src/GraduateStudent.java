// ============================================
// 6. 大学院生クラス
// ============================================

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 大学院生クラス
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GraduateStudent extends Student implements ScholarshipEligible {
    
    private String degree;      // 学位（修士・博士）
    private String advisor;     // 指導教員
    private String researchArea; // 研究分野
    private boolean isTA;       // TA（Teaching Assistant）担当
    
    @Builder
    public GraduateStudent(String id, String name, LocalDate birthDate,
                          String email, String major, int enrollmentYear,
                          String degree, String advisor, String researchArea, boolean isTA) {
        super(id, name, birthDate, email, major, enrollmentYear);
        this.degree = degree;
        this.advisor = advisor;
        this.researchArea = researchArea;
        this.isTA = isTA;
        // log.info(...) を削除
    }
    
    @Override
    public String getStudentType() {
        return "大学院生(" + degree + "課程)";
    }
    
    @Override
    public int getRequiredCredits() {
        return "修士".equals(degree) ? 30 : 20; // 修士30単位、博士20単位
    }
    
    @Override
    public boolean canGraduate() {
        return grades.size() >= getRequiredCredits() / 2 && // 簡易単位数チェック
               gpa >= 3.0; // 大学院は高い基準
    }
    
    @Override
    public String introduce() {
        return String.format("%s課程の%sです。%sを専攻し、%s先生のもとで%sの研究をしています。%s",
                           degree, name, major, advisor, researchArea,
                           isTA ? "TAも担当しています。" : "");
    }
    
    @Override
    public String generateReport() {
        StringBuilder report = new StringBuilder();
        report.append("=== 大学院生 成績レポート ===\n");
        report.append("学生ID: ").append(id).append("\n");
        report.append("氏名: ").append(name).append("\n");
        report.append("課程: ").append(degree).append("課程\n");
        report.append("専攻: ").append(major).append("\n");
        report.append("指導教員: ").append(advisor).append("\n");
        report.append("研究分野: ").append(researchArea).append("\n");
        report.append("GPA: ").append(String.format("%.2f", gpa)).append("\n");
        report.append("成績レベル: ").append(getGradeLevel()).append("\n");
        report.append("卒業可能: ").append(canGraduate() ? "可能" : "不可").append("\n");
        report.append("TA担当: ").append(isTA ? "あり" : "なし").append("\n");
        
        if (isEligibleForScholarship()) {
            report.append("奨学金: ").append(getScholarshipType())
                  .append(" (").append(getScholarshipAmount()).append("万円)\n");
        }
        
        return report.toString();
    }
    
    // ScholarshipEligibleインターフェースの実装
    @Override
    public boolean isEligibleForScholarship() {
        return gpa >= 3.3 || isTA; // 大学院生はTAでも対象
    }
    
    @Override
    public double getScholarshipAmount() {
        if (!isEligibleForScholarship()) return 0.0;
        
        double amount = 0.0;
        if (gpa >= 3.8) amount += 80.0; // GPA優秀
        else if (gpa >= 3.5) amount += 60.0;
        else if (gpa >= 3.3) amount += 40.0;
        
        if (isTA) amount += 20.0; // TA手当
        if ("博士".equals(degree)) amount += 30.0; // 博士課程加算
        
        return amount;
    }
    
    @Override
    public String getScholarshipType() {
        if (!isEligibleForScholarship()) return "対象外";
        
        List<String> types = new ArrayList<>();
        if (gpa >= 3.3) types.add("研究奨励金");
        if (isTA) types.add("TA奨学金");
        if ("博士".equals(degree)) types.add("博士課程支援金");
        
        return String.join(" + ", types);
    }
}
