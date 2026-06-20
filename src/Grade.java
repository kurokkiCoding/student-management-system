// ============================================
// 7. Gradeクラス（Lombok活用）
// ============================================

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * 成績クラス（Lombok全面活用）
 */
@Data
@Builder
@AllArgsConstructor
public class Grade {

    private String subject;     // 科目名
    private int score;          // 点数
    private String semester;    // 学期
    private LocalDate testDate; // 試験日
    private int credits;        // 単位数

    /**
     * スコアをGPAポイントに変換
     */
    public double getGpaPoint() {
        if (score >= 90) return 4.0;
        if (score >= 80) return 3.0;
        if (score >= 70) return 2.0;
        if (score >= 60) return 1.0;
        return 0.0;
    }

    /**
     * 評価文字を取得
     */
    public String getLetterGrade() {
        if (score >= 90) return "A";
        if (score >= 80) return "B";
        if (score >= 70) return "C";
        if (score >= 60) return "D";
        return "F";
    }

    /**
     * 合格判定
     */
    public boolean isPassed() {
        return score >= 60;
    }
}
