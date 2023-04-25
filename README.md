  
## 1. 설명
```
여행자 클럽 마일리지 서비스 API 구현
```
  
## 2. 프로젝트 명세 
  
### 2-1. 기술 스택 💻
name | version
-----|---------
Kotlin | 1.6.21
JPA | 1.6.21
Spring Boot | 2.7.11
Gradle | 8.0.2
MySQL | 5.7.40
Swagger | 3.0.0
  

### 2-2. 개발 환경 🛠
- Window
- Intellij, DataGrip, Postman  

### 2-3. 프로젝트 구조 🏷
```
com.triple.demo
----------------
   ㄴ common
            ㄴ entitiy
                    ㄴ AuditingEntity
            ㄴ enums
                    ㄴ EnumModel
                    ㄴ ActionType
                    ㄴ EventType
                    ㄴ PointType
   ㄴ config
            ㄴ SwaggerConfig
   ㄴ controller
            ㄴ EventController
   ㄴ entity
            ㄴ ReviewEntity
   ㄴ model
            ㄴ Point
            ㄴ Event
            ㄴ Place
            ㄴ User
   ㄴ repository
            ㄴ PointRepository
            ㄴ PlaceRepository
            ㄴ EventRepository
            ㄴ UserRepository
   ㄴ service
            ㄴ PointService
            ㄴ PlaceService
            ㄴ EventService
            ㄴ UserService
   ㄴ DemoApplication
```

### 2-4. REST API 명세서
> 자세한 내용은 서버 기동 후 **swagger-ui**를 통해 알 수 있습니다!  
  
Method | Resource URL | Description
-------|--------------|------------
Post | /events | 포인트 적립
Get | /events/{userId} | 사용자 포인트 조회

### 2-5. 테이블 정의서
> 간단하게 테이블 구성을 정리했습니다:)  

Table | Table Description | Column | Column Description
------|-------------------|--------|--------------------
**user** | **사용자**
||| id | 식별 아이디
||| name | 이름
||| login_name | 로그인명
||| password | 패스워드
**place** | **장소**
||| id | 식별 아이디
||| title | 제목
||| content | 내용
**event** | **이벤트** 
||| id | 식별 아이디
||| user_id | 사용자 아이디
||| place_id | 장소 아이디
||| type | 이벤트 타입 (리뷰 - REVIEW)
||| review_id | 리뷰 아이디 (현재 따로 테이블(혹은 모델 객체)는 없는 임의 값)
||| content | 리뷰 내용
||| action | 리뷰 등록/수정/삭제 (ADD/MOD/DELETE)
||| created_at | 이벤트 생성 날짜시간
||| modified_at | 이벤트 수정 날짜시간
**Point** | **포인트**
||| id | 식별 아이디
||| score | 포인트 점수
||| score_type | 포인트 타입 (추가 - ADD, 회수 - DELETE)
||| comment | 포인트 적립 내용
**event_attached_photo_ids** | **이벤트 리뷰 사진**
|||  event_id | 이벤트 아이디
||| attached_photo_ids | 해당 이벤트의 리뷰 사진 아이디
**event_points** | **이벤트 포인트 내역**
||| event_id | 이벤트 아이디
||| points_id | 포인트 아이디
**event_aud** | **이벤트 히스토리**
||| rev | 발생 순서
||| revType | 발생 타입 (0 - 생성, 1 - 수정, 2 - 삭제)
**event_attatched_photo_ids_aud** | **이벤트 리뷰 사진 히스토리**
||| rev | 발생 순서
||| revType | 발생 타입 (0 - 생성, 1 - 수정, 2 - 삭제)
**event_points_aud** | **이벤트 포인트 히스토리**
||| rev | 발생 순서
||| revType | 발생 타입 (0 - 생성, 1 - 수정, 2 - 삭제
**hibernate_sequence** | **시퀀스 정보 테이블**
||| next_val | 다음 시퀀스 값 정보

### 2-6. ERD 
![my_db](https://user-images.githubusercontent.com/68311262/230333947-0daa7c94-79ff-469e-abea-dbb25c0943e3.png)

  
## 3. 애플리케이션 실행

### 3-1. 서버 실행 방법 🎮

##### (1) DB 스키마를 직접 추가한다. (스키마명 : my_db | 명령어 : create schema my_db;)
##### (2) Intellij 혹은 서버를 실행할 수 있는 에디터로 프로젝트를 열어준다.
##### (3) 프로젝트 설정에 맞게 에디터 환경을 셋팅한다. (SDK : java 11, Gradle VM : java 11)
##### (4) 'Triple > demo' 아래에서 'gradle clean build -x test' 명령어를 실행한다. (에디터의 gradle 도구를 이용해도 좋음 : build > clean, build > build)
##### (5) DemoApplication에서 main()을 실행한다. (에디터의 gradle 도구를 이용해도 좋음 : application > bootRun)

### 3-2. 기타 전달 사항 💡
- 서버 기동 시 JPA로 테이블을 생성하는 것이 아닌, schema.sql과 data.sql를 사용하여 기본 테이블과 기본 정보를 생성합니다.  
- 기본 정보는 사용자 1명(id: 샘플에 있는 UUID)과 장소 1개(id: 샘플에 있는 UUID)가 있습니다.
- 서버 기동할 때마다 테이블과 데이터는 초기화됩니다.
- Spring, JPA 설정은 application.yml을 통해 설정했습니다. (서버 port : 8080)
- 이벤트 히스토리는 hibernate eveners를 사용하여 테이블명 '*_aud'의 형태로 저장되어 이벤트 발생 시마다 관리됩니다.
- DemoApplicationTests에 간단한 통합테스트 하나를 만들어 놓았습니다.

### 3-3. Swagger UI
> 서버 기동 후 접속 url 입니다!
```
http://localhost:8080/swagger-ui/index.html
```
