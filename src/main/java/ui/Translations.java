package ui;

import java.util.HashMap;
import java.util.Map;

public class Translations {

    private static final Map<String, Map<String, String>> translations = new HashMap<>();

    static {
        Map<String, String> english = new HashMap<>();
        english.put("title", "Employee Management");
        english.put("first_name", "First Name:");
        english.put("last_name", "Last Name:");
        english.put("email", "Email:");
        english.put("save", "Save");

        Map<String, String> farsi = new HashMap<>();
        farsi.put("title", "عنوان برنامه");
        farsi.put("first_name", "نام:");
        farsi.put("last_name", "نام خانوادگی:");
        farsi.put("email", "ایمیل:");
        farsi.put("save", "ذخیره");

        Map<String, String> japanese = new HashMap<>();
        japanese.put("title", "アプリのタイトル");
        japanese.put("first_name", "ラベル.名:");
        japanese.put("last_name", "ラベル.姓:");
        japanese.put("email", "ラベル.メール:");
        japanese.put("save", "保存");

        translations.put("English", english);
        translations.put("Farsi", farsi);
        translations.put("Japanese", japanese);
    }

    public static String[] getAvailableLanguages() {
        return translations.keySet().toArray(new String[0]);
    }

    public static String getTranslation(String language, String key) {
        return translations.getOrDefault(language, translations.get("English")).get(key);
    }
}
