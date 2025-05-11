# 💼 Crypto Portfolio Backend

Java Spring Boot приложение за управление на криптовалутен портфейл. Системата позволява на потребителите да:
- Създават акаунти
- Регистрират транзакции
- Следят текущите си активи
- Получават цени на криптовалути чрез Kraken API

## ⚙️ Технологии

- Java 17+
- Spring Boot
- Maven
- REST API
- CORS настройка за frontend (`http://localhost:3000`)

## 📁 Структура на проекта

- `controller` – REST API интерфейси
- `service` – Бизнес логика
- `model` – Същности и DTO обекти
- `repository` – (ако е приложимо) достъп до базата

## 🚀 Стартиране

//backend 
cd backend
mvn spring-boot:run

//frontend
cd frontend
mpm start
