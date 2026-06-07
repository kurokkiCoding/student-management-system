// ============================================
// 2. カスタム例外設計（業務固有エラー）
// ============================================

/**
 * 学生管理システム基底例外
 */
public class StudentManagementException extends Exception {
    public StudentManagementException(String message) {
        super(message);
    }
    
    public StudentManagementException(String message, Throwable cause) {
        super(message, cause);
    }
}
