# 🔹 Тест-кейсы: Регистрация и работа с профилем через VK

**Module:** User Onboarding & Profile Management  
**Requirement Reference:** REQ-REG-001 to REQ-REG-006  
**Environment:** Web (Desktop & Mobile Web)  
**Test Level:** Functional / UI / Integration  
**Tester:** QA Manual

---

### ✅ TC-001 — Регистрация нового пользователя через VK
**Requirement ID:** REQ-REG-001  
**Preconditions:**
- Пользователь не зарегистрирован в системе
- Активный VK-аккаунт
- Веб-система доступна

**Test Steps:**
1. Перейти на страницу входа
2. Нажать «Войти через VK»
3. Авторизоваться в VK с валидными данными
4. Подтвердить разрешения
5. Дождаться редиректа

**Expected Result:**  
Пользователь успешно зарегистрирован и перенаправлен в профиль

**Priority:** HIGH  
**Test Type:** Positive / Smoke

---

### ✅ TC-002 — Отображение данных VK в профиле
**Requirement ID:** REQ-REG-002  
**Preconditions:**
- Пользователь зарегистрирован через VK
- Успешный редирект в профиль

**Test Steps:**
1. Перейти на страницу профиля
2. Визуально проверить: ФИО, номер телефона, email

**Expected Result:**  
Данные VK корректно отображаются в полях профиля

**Priority:** HIGH  
**Test Type:** Positive / Integration / UI

---

### ✅ TC-003 — Авторизация пользователя через VK
**Requirement ID:** REQ-REG-006  
**Preconditions:**
- Пользователь ранее зарегистрирован через VK
- Вышел из системы

**Test Steps:**
1. Нажать «Войти через VK»
2. Авторизоваться с тем же аккаунтом
3. Подтвердить вход

**Expected Result:**  
Пользователь успешно авторизован и перенаправлен в профиль

**Priority:** HIGH  
**Test Type:** Positive / Regression

---

### ✅ TC-004 — Изменение ФИО на странице профиля
**Requirement ID:** REQ-PROF-001  
**Preconditions:**
- Пользователь авторизован

**Test Steps:**
1. Перейти в профиль
2. Нажать «Редактировать»
3. Ввести новое ФИО
4. Сохранить

**Expected Result:**  
Новое ФИО отображается после сохранения

**Priority:** MEDIUM  
**Test Type:** Positive / UI

---

### ✅ TC-005 — Обновление номера телефона с подтверждением
**Requirement ID:** REQ-PROF-002  
**Preconditions:**
- Пользователь авторизован
- Имеет доступ к новому номеру

**Test Steps:**
1. Перейти в профиль
2. Изменить номер
3. Получить SMS
4. Ввести код
5. Подтвердить

**Expected Result:**  
Номер обновлён, отображается актуальный, подтверждение успешно

**Priority:** HIGH  
**Test Type:** Positive / Integration / Security

---

### ✅ TC-006 — Обновление электронной почты с подтверждением
**Requirement ID:** REQ-PROF-003  
**Preconditions:**
- Пользователь авторизован
- Имеет доступ к новому email

**Test Steps:**
1. Перейти в профиль
2. Изменить email
3. Получить письмо с кодом
4. Ввести код
5. Подтвердить

**Expected Result:**  
Email обновлён и подтверждён, отображается новый адрес

**Priority:** HIGH  
**Test Type:** Positive / Integration / Security

---

### ❌ TC-007 — Ошибка регистрации с невалидными данными VK
**Requirement ID:** REQ-REG-001  
**Preconditions:**
- Пользователь не зарегистрирован
- Используются фейковые данные VK

**Test Steps:**
1. Нажать «Войти через VK»
2. Ввести некорректный логин/пароль

**Expected Result:**  
Отображается ошибка VK, регистрация не происходит

**Priority:** MEDIUM  
**Test Type:** Negative / Security

---

### ❌ TC-008 — Отказ от разрешений при регистрации через VK
**Requirement ID:** REQ-REG-001  
**Preconditions:**
- Пользователь не зарегистрирован

**Test Steps:**
1. Начать регистрацию через VK
2. На экране разрешений — выбрать «Отклонить»

**Expected Result:**  
Регистрация отменена, пользователь остаётся на экране входа

**Priority:** MEDIUM  
**Test Type:** Negative / UX

---

### ❌ TC-009 — Ошибка при вводе неверного SMS-кода
**Requirement ID:** REQ-PROF-002  
**Preconditions:**
- Пользователь начал изменение номера

**Test Steps:**
1. Ввести неправильный код
2. Подтвердить

**Expected Result:**  
Ошибка подтверждения, номер не изменён

**Priority:** HIGH  
**Test Type:** Negative / Security

---

### ❌ TC-010 — Ошибка подтверждения email при неверном коде
**Requirement ID:** REQ-PROF-003  
**Preconditions:**
- Пользователь начал изменение email

**Test Steps:**
1. Ввести неверный код из письма
2. Подтвердить

**Expected Result:**  
Ошибка подтверждения, email не изменён

**Priority:** HIGH  
**Test Type:** Negative / Security  
