
# Veterinary Management System

Veteriner hekimler ve müşteri hayvanlarının yönetimi için geliştirilmiş RESTful API tabanlı bir sistem. Bu proje veteriner hekimlerin, müşterilerin ve hayvanlarının kayıtlarının, aşı bilgilerinin ve randevularının yönetimini kolaylaştırmak amacıyla geliştirilmiştir. Spring Boot ile REST API mimarisi kullanılarak tasarlanmıştır.



## ## İçindekiler
- [Proje Hakkında](#proje-hakkında)
- [Teknolojiler](#teknolojiler)
- [Kurulum](#kurulum)
- [API Dokümantasyonu](#api-dokümantasyonu)
  - [Doktor Yönetimi](#doktor-yönetimi)
  - [Müşteri ve Hayvan Yönetimi](#müşteri-ve-hayvan-yönetimi)
  - [Aşı Yönetimi](#aşı-yönetimi)
  - [Randevu Yönetimi](#randevu-yönetimi)


## ## Teknolojiler
- Java 21
- Spring Boot 3.5
- Spring Data JPA
- PostgreSQL
- ModelMapper
- Maven
## ## Kurulum
1. PostgreSQL veritabanınızı oluşturun ve `application.properties` dosyanızda gerekli bağlantı bilgilerini yapılandırın.
2. Projeyi klonlayın:
   ```bash
   git clone <repo-url>
3. Proje dizininde Maven bağımlılıklarını indirip projeyi derleyin:
    ```bash
    mvn clean install
4. Uygulamayı çalıştırın:
    ```bash
    mvn spring-boot:run
5. API endpoint'leri http://localhost:8080/api/v1/ altında çalışacaktır.# Veterinary Management System - API Documentation

Bu doküman, Veterinary Management System projesindeki API endpointlerinin açıklamalarını içerir.

---

## 1. Veteriner Doktorlar (Doctors)

| HTTP Method | Endpoint                 | Açıklama                          | Request Body / Query Params                     | Response                        |
|-------------|--------------------------|---------------------------------|------------------------------------------------|--------------------------------|
| POST        | `/api/v1/doctors`         | Yeni doktor ekler                | `DoctorSaveRequest` (JSON)                      | Eklenen doktor bilgisi          |
| PUT         | `/api/v1/doctors/{id}`    | Doktor bilgilerini günceller    | `DoctorUpdateRequest` (JSON)                    | Güncellenen doktor bilgisi     |
| DELETE      | `/api/v1/doctors/{id}`    | Doktoru siler                   | -                                              | -                              |
| GET         | `/api/v1/doctors/{id}`    | ID'ye göre doktor bilgisi getirir | -                                              | Doktor bilgisi                 |
| GET         | `/api/v1/doctors`         | Tüm doktorları listeler          | -                                              | Doktor listesi                 |
| GET         | `/api/v1/doctors/search?name={name}` | İsim ile doktor arama         | Query param: `name`                             | Doktor listesi (filtreli)      |

---

## 2. Doktorların Müsait Günleri (Available Dates)

| HTTP Method | Endpoint                        | Açıklama                         | Request Body / Query Params                     | Response                        |
|-------------|--------------------------------|---------------------------------|------------------------------------------------|--------------------------------|
| POST        | `/api/v1/availableDates`        | Yeni müsait gün ekler            | `AvailableDateSaveRequest` (JSON)               | Eklenen müsait gün bilgisi     |
| PUT         | `/api/v1/availableDates/{id}`   | Müsait günü günceller            | `AvailableDateUpdateRequest` (JSON)             | Güncellenen müsait gün bilgisi |
| DELETE      | `/api/v1/availableDates/{id}`   | Müsait günü siler                | -                                              | -                              |
| GET         | `/api/v1/availableDates/{id}`   | ID'ye göre müsait gün getirir    | -                                              | Müsait gün bilgisi             |
| GET         | `/api/v1/availableDates`        | Tüm müsait günleri listeler      | -                                              | Müsait gün listesi             |
| GET         | `/api/v1/availableDates/doctor/{doctorId}` | Doktora ait müsait günleri getirir | Path param: `doctorId`                        | Müsait gün listesi             |

---

## 3. Müşteriler (Customers)

| HTTP Method | Endpoint                 | Açıklama                          | Request Body / Query Params                     | Response                        |
|-------------|--------------------------|---------------------------------|------------------------------------------------|--------------------------------|
| POST        | `/api/v1/customers`       | Yeni müşteri ekler               | `CustomerSaveRequest` (JSON)                    | Eklenen müşteri bilgisi        |
| PUT         | `/api/v1/customers/{id}`  | Müşteri bilgilerini günceller   | `CustomerUpdateRequest` (JSON)                  | Güncellenen müşteri bilgisi    |
| DELETE      | `/api/v1/customers/{id}`  | Müşteriyi siler                 | -                                              | -                              |
| GET         | `/api/v1/customers/{id}`  | ID'ye göre müşteri getirir       | -                                              | Müşteri bilgisi                |
| GET         | `/api/v1/customers`       | Tüm müşterileri listeler          | -                                              | Müşteri listesi                |
| GET         | `/api/v1/customers/search?name={name}` | İsim ile müşteri arama         | Query param: `name`                             | Müşteri listesi (filtreli)     |
| GET         | `/api/v1/customers/{customerId}/animals` | Müşterinin tüm hayvanlarını getirir | Path param: `customerId`                    | Hayvan listesi                 |

---

## 4. Hayvanlar (Animals)

| HTTP Method | Endpoint                 | Açıklama                          | Request Body / Query Params                     | Response                        |
|-------------|--------------------------|---------------------------------|------------------------------------------------|--------------------------------|
| POST        | `/api/v1/animals`         | Yeni hayvan ekler                | `AnimalSaveRequest` (JSON)                      | Eklenen hayvan bilgisi         |
| PUT         | `/api/v1/animals/{id}`    | Hayvan bilgilerini günceller    | `AnimalUpdateRequest` (JSON)                    | Güncellenen hayvan bilgisi     |
| DELETE      | `/api/v1/animals/{id}`    | Hayvanı siler                   | -                                              | -                              |
| GET         | `/api/v1/animals/{id}`    | ID'ye göre hayvan getirir       | -                                              | Hayvan bilgisi                 |
| GET         | `/api/v1/animals`         | Tüm hayvanları listeler          | -                                              | Hayvan listesi                 |
| GET         | `/api/v1/animals/search?name={name}` | İsim ile hayvan arama          | Query param: `name`                             | Hayvan listesi (filtreli)      |

---

## 5. Aşılar (Vaccines)

| HTTP Method | Endpoint                    | Açıklama                          | Request Body / Query Params                     | Response                        |
|-------------|-----------------------------|---------------------------------|------------------------------------------------|--------------------------------|
| POST        | `/api/v1/vaccines`           | Yeni aşı ekler                   | `VaccineSaveRequest` (JSON)                     | Eklenen aşı bilgisi            |
| PUT         | `/api/v1/vaccines/{id}`      | Aşı bilgilerini günceller       | `VaccineUpdateRequest` (JSON)                   | Güncellenen aşı bilgisi        |
| DELETE      | `/api/v1/vaccines/{id}`      | Aşıyı siler                    | -                                              | -                              |
| GET         | `/api/v1/vaccines/{id}`      | ID'ye göre aşı getirir           | -                                              | Aşı bilgisi                   |
| GET         | `/api/v1/vaccines`           | Tüm aşıları listeler             | -                                              | Aşı listesi                   |
| GET         | `/api/v1/vaccines/filter?startDate={start}&endDate={end}` | Koruyuculuk bitiş tarihine göre filtreler | Query param: `startDate`, `endDate`            | Filtrelenmiş aşı listesi       |
| GET         | `/api/v1/vaccines/animal/{animalId}` | Hayvana ait tüm aşıları getirir | Path param: `animalId`                          | Aşı listesi                   |

---

## 6. Randevular (Appointments)

| HTTP Method | Endpoint                        | Açıklama                         | Request Body / Query Params                     | Response                        |
|-------------|--------------------------------|---------------------------------|------------------------------------------------|--------------------------------|
| POST        | `/api/v1/appointments`          | Yeni randevu oluşturur           | `AppointmentSaveRequest` (JSON)                 | Eklenen randevu bilgisi        |
| PUT         | `/api/v1/appointments/{id}`     | Randevu bilgilerini günceller    | `AppointmentUpdateRequest` (JSON)               | Güncellenen randevu bilgisi    |
| DELETE      | `/api/v1/appointments/{id}`     | Randevuyu siler                 | -                                              | -                              |
| GET         | `/api/v1/appointments/{id}`     | ID'ye göre randevu getirir       | -                                              | Randevu bilgisi               |
| GET         | `/api/v1/appointments`          | Tüm randevuları listeler          | -                                              | Randevu listesi               |
| GET         | `/api/v1/appointments/search/doctor?doctorId={id}&start={start}&end={end}` | Doktor ve tarih aralığına göre filtreler | Query params: `doctorId`, `start`, `end`        | Filtrelenmiş randevu listesi  |
| GET         | `/api/v1/appointments/search/animal?animalId={id}&start={start}&end={end}` | Hayvan ve tarih aralığına göre filtreler | Query params: `animalId`, `start`, `end`        | Filtrelenmiş randevu listesi  |

---

## Hata Yönetimi

- Projede custom exceptionlar ve global exception handler ile anlamlı hata mesajları ve HTTP statü kodları dönülmektedir.

---

## Notlar

- Tarih formatları için `yyyy-MM-dd` (LocalDate) ve `yyyy-MM-dd'T'HH:mm:ss` (LocalDateTime) kullanılır.
- Randevular saat başı alınabilir.
- Doktorların müsait günleri sadece tarih (LocalDate) olarak tutulur, saat bilgisi içermez.
- Aynı tip aşıların koruyuculuk süresi dolmadıkça yeni aşı eklenemez.

---

**Veterinary Management System API Dokümanasyonu**

---


## Hata Yönetimi
API’da aşağıdaki özel exception’lar ve HTTP durum kodları kullanılır:
| Exception                         | HTTP Durum Kodu | Açıklama                                       |
| --------------------------------- | --------------- | ---------------------------------------------- |
| `DoctorNotFoundException`         | 404 Not Found   | Doktor bulunamadığında                         |
| `AnimalNotFoundException`         | 404 Not Found   | Hayvan bulunamadığında                         |
| `CustomerNotFoundException`       | 404 Not Found   | Müşteri bulunamadığında                        |
| `AppointmentConflictException`    | 409 Conflict    | Randevu zaman çakışması                        |
| `AppointmentTimeInvalidException` | 400 Bad Request | Randevu zamanı geçersiz                        |
| `ProtectionDateInvalidException`  | 400 Bad Request | Aşı koruyuculuk bitiş süresiyle ilgili hata    |
| `AlreadyExistsException`          | 409 Conflict    | Var olan kayıt tekrar eklenmeye çalışıldığında |
| `DoctorNotAvailableException`     | 400 Bad Request | Doktorun seçilen tarihte çalışmaması           |

## Katkı

Katkılara her zaman açığız!


  
