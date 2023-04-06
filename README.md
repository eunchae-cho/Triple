# 인터파크 Triple 기술 과제  


### 📃 내용
```
트리플여행자 클럽 마일리지 서비스 API 구현하기
```
  
### 🛠 기술 스택
name | version
-----|---------
Kotlin | 1.6.21
Spring Boot | 2.7.11
Gradle | 8.0.2
MySQL | 5.7.40
Swagger | 3.0.0
  

### 💻 개발 환경
- Window
- Intellij, DataGrip, Postman  

### 프로젝트 구조

### 📑 REST API 명세서
> 자세한 내용은 서버 기동 후 **swagger-ui**를 통해 알 수 있습니다!  
  
Method | Resource URL | Description
-------|--------------|------------
Post | /events | 포인트 적립
Get | /events/{userId} | 사용자 포인트 조회

### ✏ 테이블 정의서
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

### 💷 ERD 
![my_db](https://user-images.githubusercontent.com/68311262/230319628-b8e27fc5-1af1-4162-bc60-5956424d8327.png)  
  

### 💡 서버 실행 전 필요한 셋팅

### 서버 실행 방법

### Swagger UI
