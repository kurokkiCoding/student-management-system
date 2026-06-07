/**
 * レポート生成可能なオブジェクトの契約
 */
public interface Reportable {
    String generateReport();
    String generateSummary();
}