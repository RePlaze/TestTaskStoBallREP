package part4;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class TestReport {
    
    public static class TestResult {
        private String testCaseId;
        private String description;
        private String status;
        private String comments;

        public TestResult(String testCaseId, String description, String status, String comments) {
            this.testCaseId = testCaseId;
            this.description = description;
            this.status = status;
            this.comments = comments;
        }

        @Override
        public String toString() {
            return String.format("""
                    ID тест-кейса: %s
                    Описание: %s
                    Статус: %s
                    Комментарии: %s
                    """, testCaseId, description, status, comments);
        }
    }

    public static class Report {
        private String projectName;
        private String version;
        private LocalDate testDate;
        private List<TestResult> results;
        private int totalTests;
        private int passedTests;
        private int failedTests;
        private int blockedTests;

        public Report(String projectName, String version, LocalDate testDate, List<TestResult> results) {
            this.projectName = projectName;
            this.version = version;
            this.testDate = testDate;
            this.results = results;
            this.totalTests = results.size();
            this.passedTests = (int) results.stream().filter(r -> r.status.equals("PASSED")).count();
            this.failedTests = (int) results.stream().filter(r -> r.status.equals("FAILED")).count();
            this.blockedTests = (int) results.stream().filter(r -> r.status.equals("BLOCKED")).count();
        }

        public String generateReport() {
            return String.format("""
                    ОТЧЕТ ПО ТЕСТИРОВАНИЮ
                    
                    Проект: %s
                    Версия: %s
                    Дата тестирования: %s
                    
                    Статистика:
                    Всего тестов: %d
                    Успешно: %d
                    Неуспешно: %d
                    Блокировано: %d
                    Процент успешных: %.2f%%
                    
                    Детальные результаты:
                    %s
                    
                    Заключение:
                    %s
                    
                    Рекомендации:
                    %s
                    """,
                    projectName,
                    version,
                    testDate,
                    totalTests,
                    passedTests,
                    failedTests,
                    blockedTests,
                    (double) passedTests / totalTests * 100,
                    String.join("\n", results.stream().map(TestResult::toString).toList()),
                    generateConclusion(),
                    generateRecommendations()
            );
        }

        private String generateConclusion() {
            if (passedTests == totalTests) {
                return "Все тесты успешно пройдены. Система готова к релизу.";
            } else if ((double) passedTests / totalTests >= 0.9) {
                return "Большинство тестов пройдены успешно. Критические функции работают корректно.";
            } else {
                return "Обнаружены существенные проблемы. Требуется доработка перед релизом.";
            }
        }

        private String generateRecommendations() {
            StringBuilder recommendations = new StringBuilder();
            if (failedTests > 0) {
                recommendations.append("- Исправить выявленные дефекты\n");
            }
            if (blockedTests > 0) {
                recommendations.append("- Устранить блокирующие факторы для заблокированных тестов\n");
            }
            recommendations.append("- Провести регрессионное тестирование после исправлений\n");
            recommendations.append("- Улучшить покрытие тестами критических функций\n");
            return recommendations.toString();
        }
    }

    public static void main(String[] args) {
        List<TestResult> results = Arrays.asList(
            new TestResult("TC-001", "Успешная регистрация через VK", "PASSED", "Все шаги выполнены успешно"),
            new TestResult("TC-002", "Проверка корректности полученных данных", "PASSED", "Данные корректно отображаются"),
            new TestResult("TC-003", "Изменение данных профиля", "FAILED", "Ошибка при сохранении изменений"),
            new TestResult("TC-004", "Подтверждение нового номера телефона", "PASSED", "SMS-код получен и подтвержден"),
            new TestResult("TC-005", "Попытка регистрации с некорректными данными", "PASSED", "Система корректно обрабатывает ошибки"),
            new TestResult("TC-006", "Авторизация через VK", "BLOCKED", "Проблемы с API VK")
        );

        Report report = new Report(
            "Система регистрации через VK",
            "1.0.0",
            LocalDate.now(),
            results
        );

        System.out.println(report.generateReport());
    }
} 