# 🚀 FInNS
> 자산 관리, 금융상품 추천·비교, 커뮤니티 기능을 제공하는 금융 SNS 플랫폼의 백엔드 서버

<img width="1209" height="563" alt="FInNS 대표 이미지" src="https://github.com/user-attachments/assets/36c4ee31-945a-4af9-8648-6190780337c0" />

## 🔗 Related Links
> **[프론트엔드 Repository](https://github.com/dydrltk1379/KB_Project_FrontEnd)**
  
---
<br>

## 📝 1. 프로젝트 개요
FInNS는 자산 관리, 금융상품 추천·비교, 커뮤니티 기능을 제공하는 금융 SNS 플랫폼입니다.  
본 저장소는 FInNS의 백엔드 서버로, 사용자 인증, 금융상품 조회·추천, 사용자 활동 관리, 커뮤니티 API를 제공합니다.  

또한 공공 API 및 크롤링 기반의 금융 데이터 수집·전처리 로직을 구현했으며,  
Redis 기반 Hybrid Pagination을 적용해 대량 조회 성능을 개선했습니다.


## 🏆 2. 수상 이력
> **KB국민은행 It's Your Life 5기 부트캠프 최우수상 수상작**

---
<br>

## 🛠 3. 기술 스택

| 분류 | 기술 | 도입 목적 |
| :--- | :--- | :--- |
| 언어 | ![Java](https://img.shields.io/badge/Java_17-007396?style=flat-square&logo=openjdk&logoColor=white) | 비즈니스 로직 구현 및 안정적인 서버 애플리케이션 개발 |
| 프레임워크 | ![Spring Framework](https://img.shields.io/badge/Spring_Framework-6DB33F?style=flat-square&logo=spring&logoColor=white) | REST API 구성 및 계층형 아키텍처 기반 서버 개발 |
| 데이터 접근 | ![MyBatis](https://img.shields.io/badge/MyBatis-BE1E2D?style=flat-square&logoColor=white) | SQL 중심 데이터 접근 및 쿼리 제어 |
| 데이터베이스 | ![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=mysql&logoColor=white) | 사용자·금융상품·커뮤니티 데이터 저장 |
| 보안 | ![Spring Security](https://img.shields.io/badge/Spring_Security-6DB33F?style=flat-square&logo=springsecurity&logoColor=white) ![JWT](https://img.shields.io/badge/JWT-000000?style=flat-square&logo=jsonwebtokens&logoColor=white) | 인증·인가 처리 및 토큰 기반 사용자 인증 구현 |
| API 문서 / 테스트 | ![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=flat-square&logo=swagger&logoColor=black) ![Postman](https://img.shields.io/badge/Postman-FF6C37?style=flat-square&logo=postman&logoColor=white) | API 문서화 및 엔드포인트 기능 검증 |
| 배포 환경 | ![AWS EC2](https://img.shields.io/badge/AWS_EC2-FF9900?style=flat-square&logo=amazonec2&logoColor=white) | 서버 배포 및 실행 환경 구성 |
| 빌드 도구 | ![Gradle](https://img.shields.io/badge/Gradle-02303A?style=flat-square&logo=gradle&logoColor=white) | 프로젝트 빌드 및 의존성 관리 |

---
<br>

## 👥 4. 팀 구성 및 담당 역할
**6인 팀 프로젝트 | Backend**
- 금융상품 조회·비교 API 설계 및 구현
- 추천 로직 구현 및 데이터 모델링
- MySQL 스키마 및 조회 구조 설계
- Redis 기반 Hybrid Pagination으로 대량 조회 성능 개선
 
---
<br>

## 🔧 5. Engineering Highlights
### [1] Deep Pagination 병목을 Hybrid Pagination으로 해결
- **문제**  
  금융상품 목록 조회 API에서 페이지가 깊어질수록 Offset 스캔 비용이 증가해 응답 속도가 크게 저하되었습니다.

- **해결**  
  Offset 기반 조회를 Redis 기반 Hybrid Pagination으로 전환해, 
  page 기반 UX를 유지하면서도 실제 조회는 Cursor 방식으로 처리했습니다.

- **결과**  
  - Offset 기반: Page Depth 3000에서 **3,229ms**
  - Cursor 기반(Hybrid): 평균 **47ms**
  - 최대 응답 시간 기준 **약 98% 개선**

<img width="100%" height="auto" alt="JMeter 성능 비교 결과" src="https://github.com/user-attachments/assets/3a8afb89-fcb4-4b99-900a-3bbd4c087681" />

> JMeter를 활용해 3만 건 데이터 기준 페이지 깊이에 따른 응답 시간을 비교했습니다.

### [2] 이기종 금융 데이터 정합성 개선
- **문제**  
  공공 API와 크롤링 데이터 간 포맷 차이로 저장 예외와 정합성 문제가 발생했습니다.

- **해결**  
  Data Pre-processing Layer를 분리하고,  
  MyBatis TypeHandler와 ON DUPLICATE KEY UPDATE를 적용해 포맷 변환과 중복 처리를 일관화했습니다

- **결과**  
  저장 예외를 줄이고, 추천·비교 로직에 활용되는 금융 데이터의 안정성을 높였습니다.

---
<br>

## ✨ 6. 핵심 기능
### 1. 금융상품 조회 및 추천
- 금융상품 데이터 조회 API 제공
- 사용자 관심사 기반 추천 및 상품 비교 기능 구현
- 카테고리별 정렬 및 필터링 지원

### 2. 금융 데이터 수집 및 전처리
- 공공 API 및 웹 크롤링 기반 금융 데이터 수집
- 수집 데이터 전처리 및 DB 저장 로직 구현

### 3. 사용자 기능
- Spring Security & JWT 기반 무상태(Stateless) 인증 및 인가 처리
- 저장한 금융상품 조회, 사용자 활동 내역 및 포인트 관리

### 4. 커뮤니티 기능
- 게시글 / 댓글 CRUD API 구현
- 활동 기반 포인트 및 랭킹 기능 지원

---
<br>

## 🏗 7. 시스템 아키텍처

<div align="center">
  <img width="799" height="480" alt="image" src="https://github.com/user-attachments/assets/50c3cc44-2680-4217-9684-90f527e7803b" />
</div>

> Client 요청을 **Spring 기반 백엔드 서버**에서 처리하고, 인증/인가, 금융 데이터 처리, 커뮤니티 도메인, DB 연동을 독립적으로 분리하여 구성했습니다.

---
<br>

## 🗂️ 8. 프로젝트 구조

```bash
src
└── main
    ├── java
    │   └── com
    │       ├── finns
    │       │   ├── config       # 보안, Swagger, DB 설정
    │       │   ├── controller   # API 엔드포인트 정의
    │       │   ├── service      # 핵심 비즈니스 로직
    │       │   ├── repository   # 데이터 접근 계층 (MyBatis 연동)
    │       │   ├── domain       # 주요 도메인 모델
    │       │   ├── dto          # 요청/응답 및 계층 간 데이터 전달 객체
    │       │   ├── exception    # 공통 예외 처리
    │       │   └── security     # JWT, 인증/인가 처리
    │       └── kb               # 공통/보조 패키지
    ├── resources
    │   ├── application.yml      # DB, JWT, 외부 API 등 환경 설정
    │   └── mapper               # MyBatis SQL 매퍼 파일
    └── webapp                   # 웹 리소스 및 웹 설정
```

---
<br>

## 🗄️ 9. ERD
<img width="1710" height="748" alt="image" src="https://github.com/user-attachments/assets/70ac7896-1964-45a3-9d70-0f1640c75f39" />

---
<br>

## 📌 10. API 
- 인증: 회원가입, 로그인, JWT 기반 인증
- 금융상품: 목록 조회, 추천, 비교
- 커뮤니티: 게시글/댓글 CRUD
- 상세 명세: **[Notion API 문서](https://www.notion.so/KB-bec379c70ede8393888b81af62dac0ed?source=copy_link)**
  
---
<br>

## ⚙️ 11. 실행 방법
### 📋 Prerequisites
- Java 17
- MySQL 8.0 이상
- Gradle 7.x 이상

### 🚀 Installation
```bash
git clone https://github.com/dydrltk1379/KB_project_Back.git
cd KB_project_Back
./gradlew build
```

---
<br>

## 📎 주요 관련 링크
> 📑 **[발표 자료](https://github.com/user-attachments/files/21430052/FInNS_portfolio.pdf)**
