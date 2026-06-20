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
        // log.info 削除
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
        // log.info 削除
        System.out.println("=== 詳細情報 ===");
        System.out.println("ID: " + id);
        System.out.println("名前: " + name);
        System.out.println("年齢: " + getAge() + "歳");
        System.out.println("Email: " + email);
        System.out.println(introduce());
    }
}
