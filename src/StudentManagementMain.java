import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentManagementMain {

    // メニューで扱う学生リスト（簡易版）
    private static List<Student> students = new ArrayList<>();

    public static void main(String[] args) {

        // 初期データ投入（必要なら）
        initializeSampleData();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            showMenu();
            String choice = scanner.next();

            switch (choice) {
                case "1":
                    showStudentList();
                    break;
                case "2":
                    addStudentInteractive(scanner);
                    break;
                case "3":
                    deleteStudentInteractive(scanner);
                    break;
                case "4":
                    searchByGpaInteractive(scanner);
                    break;
                case "5":
                    exportToCsv(students);
                    break;
                case "6":
                    System.out.println("システムを終了します。");
                    return;
                default:
                    System.out.println("無効な入力です。1〜6を選択してください。");
            }
        }
    }

    // ============================================
    // 初期データ
    // ============================================
    private static void initializeSampleData() {
        students.add(
            UndergraduateStudent.builder()
                .id("U001")
                .name("田中太郎")
                .birthDate(LocalDate.of(2003, 4, 15))
                .email("tanaka@university.ac.jp")
                .major("コンピュータサイエンス")
                .enrollmentYear(2022)
                .currentYear(2)
                .club("プログラミング研究会")
                .build()
        );

        students.add(
            GraduateStudent.builder()
                .id("G001")
                .name("山田次郎")
                .birthDate(LocalDate.of(1999, 12, 3))
                .email("yamada@graduate.university.ac.jp")
                .major("人工知能")
                .enrollmentYear(2023)
                .degree("修士")
                .advisor("AI教授")
                .researchArea("機械学習")
                .isTA(true)
                .build()
        );
    }

    // ============================================
    // 1. 学生一覧表示
    // ============================================
    private static void showStudentList() {
        System.out.println("=== 学生一覧 ===");

        if (students.isEmpty()) {
            System.out.println("学生が登録されていません。");
            return;
        }

        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            System.out.printf("%d. %s (%s専攻, %d歳)\n",
                    i + 1, s.getName(), s.getMajor(), s.getAge());
        }
    }

    // ============================================
    // 2. 学生追加（簡易版）
    // ============================================
    private static void addStudentInteractive(Scanner scanner) {
        System.out.println("=== 学生追加 ===");
        System.out.print("名前: ");
        String name = scanner.next();

        System.out.print("専攻: ");
        String major = scanner.next();

        // 最小限の情報で学部生として追加
        students.add(
            UndergraduateStudent.builder()
                .id("U" + (students.size() + 1))
                .name(name)
                .birthDate(LocalDate.of(2000, 1, 1))
                .email(name + "@example.com")
                .major(major)
                .enrollmentYear(2024)
                .currentYear(1)
                .club("未所属")
                .build()
        );

        System.out.println("学生を追加しました！");
    }

    // ============================================
    // 3. 学生削除
    // ============================================
    private static void deleteStudentInteractive(Scanner scanner) {
        System.out.println("=== 学生削除 ===");
        System.out.print("削除する学生名: ");
        String name = scanner.next();

        Student target = students.stream()
                .filter(s -> s.getName().equals(name))
                .findFirst()
                .orElse(null);

        if (target == null) {
            System.out.println("該当する学生が見つかりません。");
            return;
        }

        if (confirmDeletion(name)) {
            students.remove(target);
            System.out.println(name + " を削除しました。");
        } else {
            System.out.println("削除をキャンセルしました。");
        }
    }

    // ============================================
    // 削除確認
    // ============================================
    private static boolean confirmDeletion(String studentName) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("本当に \"" + studentName + "\" を削除しますか？ (y/n): ");
        String input = scanner.next().toLowerCase();

        return input.equals("y") || input.equals("yes")
                || input.equals("はい") || input.equals("削除");
    }

    // ============================================
    // 4. GPA検索（簡易版）
    // ============================================
    private static void searchByGpaInteractive(Scanner scanner) {
        System.out.print("GPAの下限値を入力してください: ");
        double min = scanner.nextDouble();

        System.out.println("=== GPA検索結果 ===");
        students.stream()
                .filter(s -> s.getGpa() >= min)
                .forEach(s -> System.out.println(s.getName() + " (GPA: " + s.getGpa() + ")"));
    }

    // ============================================
    // 5. CSV出力
    // ============================================
    private static void exportToCsv(List<Student> students) {
        System.out.println("=== CSV出力機能 ===");

        if (students.isEmpty()) {
            System.out.println("出力する学生データがありません。");
            return;
        }

        try {
            java.io.FileWriter writer = new java.io.FileWriter("students.csv");

            writer.write("名前,年齢,GPA,専攻\n");

            for (Student student : students) {
                writer.write(String.format("%s,%d,%.2f,%s\n",
                        student.getName(),
                        student.getAge(),
                        student.getGpa(),
                        student.getMajor()));
            }

            writer.close();
            System.out.println("students.csv に出力完了しました！");

        } catch (java.io.IOException e) {
            System.out.println("CSV出力でエラーが発生しました: " + e.getMessage());
        }
    }

    // ============================================
    // メインメニュー
    // ============================================
    private static void showMenu() {
        System.out.println("\n=== 学生管理システム ===");
        System.out.println("1. 学生一覧表示");
        System.out.println("2. 学生追加");
        System.out.println("3. 学生削除");
        System.out.println("4. GPA検索");
        System.out.println("5. CSV出力");
        System.out.println("6. 終了");
        System.out.print("選択してください (1-6): ");
    }
}
