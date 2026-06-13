// ============================================
// 3. 抽象基底クラス（Person）
// ============================================

import java.time.LocalDate;
import java.time.Period;

import lombok.Data;

/**
 * 人物の抽象基底クラス
 */
@Data
public abstract class Person {
    protected String id;
    protected String name;
    protected LocalDate birthDate;
    protected String email;
    
    public Person(String id, String name, LocalDate birthDate, String email) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.email = email;
    }
    
    /**
     * 年齢計算（共通ロジック）
     */
    public int getAge() {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }
    
    /**
     * 自己紹介（抽象メソッド - 子クラスで具体化を強制）
     */
    public abstract String introduce();
    
    /**
     * 詳細情報表示（テンプレートメソッドパターン）
     */
    public final void showDetails() {
        System.out.println("=== 詳細情報 ===");
        System.out.println("ID: " + id);
        System.out.println("名前: " + name);
        System.out.println("年齢: " + getAge() + "歳");
        System.out.println("Email: " + email);
        System.out.println(introduce());
    }

    // ============================================
    // ★ Week 6 レッスン4 追加メソッド
    // ============================================

    /**
     * 人物の基本情報を短縮形式で表示
     * Week 6レッスン4で追加：複合変更管理の実践用
     */
    public String getShortInfo() {
        return String.format("%s(%d歳)", getName(), getAge());
    }

    /**
     * メールドメインを取得
     * Week 6レッスン4で追加：複合変更管理の実践用
     */
    public String getEmailDomain() {
        if (getEmail() != null && getEmail().contains("@")) {
            return getEmail().substring(getEmail().indexOf("@") + 1);
        }
        return "不明";
    }
}
