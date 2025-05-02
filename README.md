# 📊 DTS Platforması - Backend

Bu layihə **DTS Platformasının Backend hissəsidir**. Platforma, şirkətlərin rəqəmsal transformasiya vəziyyətlərini analiz etmək və lazım olan məlumatları toplamaq məqsədilə hazırlanmışdır.

## ✅ Tələblər

- ✅ Java 17 və ya daha yüksək
- ✅ Maven 3.8 və ya daha yüksək
- ✅ Docker
- ✅ Docker Compose

## 🚀 Başlamaq üçün

### 1. 🐘 Verilənlər Bazasını Başlatmaq

PostgreSQL verilənlər bazasını Docker ilə başlatmaq üçün terminalda aşağıdakı əmri icra edin:

```bash
docker-compose up -d
```

### 2. ☕ Backend Servisini Başlatmaq

#### Maven Wrapper ilə:
```bash
./mvnw spring-boot:run
```

#### Yaxud əgər sistemində Maven quraşdırılıbsa:
```bash
mvn spring-boot:run
```

#### Alternativ olaraq `.jar` faylı yaradıb işlətmək üçün:

```bash
mvn clean install
java -jar target/DTSSystem-0.0.1-SNAPSHOT.jar
```

## 📚 Əlavə Məlumat

- API sənədləri Swagger UI vasitəsilə aşağıdakı ünvanda mövcuddur:  
  👉 [`http://localhost:8080/swagger-ui/index.html`](http://localhost:8080/swagger-ui/index.html)

- Əgər `application.yml` və ya `application.properties` faylında dəyişiklik etmisinizsə, tətbiqi yenidən yığmağınız tövsiyə olunur:
  ```bash
  mvn clean install
  ```

---

> **Hazırlayan:** DTS Team Lead Sarkhan Babayev
> 💡 Suallarınız olarsa, issues bölməsindən və ya e-poçt vasitəsilə bizimlə əlaqə saxlayın.