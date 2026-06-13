import java.time.LocalDate;

/**
 * 聴講生クラス
 * Week 6レッスン4で新規追加：新規ファイル管理の実践用
 * 
 * 正規の学生とは異なり、単位取得はできないが講義を聴講できる学生
 */
public class AuditStudent extends Person {
    
    private String auditCourse;   // 聴講している講座名
    private int attendanceCount;  // 出席回数
    private boolean isActive;     // 聴講継続中かどうか
    
    /**
     * デフォルトコンストラクタ
     */
    public AuditStudent() {
        super(null, null, null, null);  // birthDate は後で setBirthDate で設定
        this.isActive = true;
        this.attendanceCount = 0;
    }
    
    /**
     * 全パラメータコンストラクタ（birthDate を使用）
     */
    public AuditStudent(String id, String name, LocalDate birthDate, String email, 
                       String auditCourse) {
        super(id, name, birthDate, email);
        this.auditCourse = auditCourse;
        this.attendanceCount = 0;
        this.isActive = true;
    }
    
    public String getAuditCourse() {
        return auditCourse;
    }
    
    public void setAuditCourse(String auditCourse) {
        this.auditCourse = auditCourse;
    }
    
    public int getAttendanceCount() {
        return attendanceCount;
    }
    
    public void setAttendanceCount(int attendanceCount) {
        this.attendanceCount = Math.max(0, attendanceCount);
    }
    
    public boolean isActive() {
        return isActive;
    }
    
    public void setActive(boolean active) {
        isActive = active;
    }
    
    /**
     * 出席回数をカウントアップ
     */
    public void markAttendance() {
        if (isActive) {
            attendanceCount++;
            System.out.println(getName() + "さんの出席回数: " + attendanceCount + "回");
        } else {
            System.out.println(getName() + "さんは聴講を終了しています。");
        }
    }
    
    /**
     * 聴講を終了する
     */
    public void endAudit() {
        this.isActive = false;
        System.out.println(getName() + "さんの聴講が終了しました。");
        System.out.println("総出席回数: " + attendanceCount + "回");
    }
    
    @Override
    public String toString() {
        return String.format(
            "聴講生 - ID:%s, 氏名:%s, 年齢:%d歳, 聴講講座:%s, 出席回数:%d回, 継続状況:%s",
            getId(), getName(), getAge(), auditCourse, attendanceCount,
            isActive ? "継続中" : "終了"
        );
    }
    
    /**
     * 聴講生レポートの生成
     */
    public String generateAuditReport() {
        StringBuilder report = new StringBuilder();
        report.append("=== 聴講生レポート ===\n");
        report.append("聴講生ID: ").append(getId()).append("\n");
        report.append("氏名: ").append(getName()).append("\n");
        report.append("聴講講座: ").append(auditCourse).append("\n");
        report.append("出席回数: ").append(attendanceCount).append("回\n");
        report.append("継続状況: ").append(isActive ? "継続中" : "終了").append("\n");
        
        // 出席率の計算（仮に全15回とする）
        double attendanceRate = (attendanceCount / 15.0) * 100;
        report.append("出席率: ").append(String.format("%.1f", attendanceRate)).append("%\n");
        report.append("==================\n");
        
        return report.toString();
    }

    @Override
    public String introduce() {
        return "私は聴講生です。講座「" + auditCourse + "」を受講しています。";
    }
}
