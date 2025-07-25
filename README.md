# FInNS - 백엔드 레포지토리

**재테크를 위한 금융 상품 비교 추천 플랫폼 - FInNS**  
본 프로젝트는 Vue.js와 Spring Framework를 기반으로 한 금융 SNS 플랫폼으로, 사용자에게 금융 상품 추천, 비교 및 커뮤니티 기능을 제공합니다. 이 저장소는 백엔드 API 서버를 담당하며, Spring Boot를 기반으로 다양한 비즈니스 로직과 데이터 처리 기능을 수행합니다.

## 🏆 수상 이력
- **국민은행 it's your life 5기 부트캠프** 최우수상 수상작
- 주제: “2030세대 재테크 습관 형성을 위한 금융 플랫폼 개발”

---

## 📌 주요 기능

### 1. 금융 상품 추천 API
- 국내 주요 은행/카드사/보험사 상품 크롤링 및 정제
- 사용자 관심사에 기반한 맞춤형 금융 상품 추천
- 카테고리별(예적금/카드/보험) 정렬 및 필터링 기능 제공

### 2. 금융 상품 크롤링 및 전처리
- 공공 API + 웹 크롤링 혼합 방식
- 금융 데이터 전처리 및 DB 저장 자동화

### 3. 사용자 금융 활동 관리
- 가입/저장한 금융 상품 리스트 조회 API
- 사용자별 커뮤니티 활동 내역, 포인트 관리 API

### 4. 커뮤니티 기능 백엔드 지원
- 게시글 등록, 수정, 삭제, 댓글 기능
- 포인트 제도 및 활동 기반 랭킹 기능

---

## 🛠 기술 스택

- **Frontend**: Vue.js, Vuex, Vue Router, Axios(→ [FrontEnd Repo](https://github.com/dydrltk1379/KB_Project_FrontEnd))
- **Backend**: Spring, JPA, MySQL 
- **배포**: AWS EC2 (백엔드)

---

## 🧑‍💻 담당 역할 (작성자 중심)

### ✅ 백엔드 개발 (Spring Boot)
- 전체 API 설계 및 ERD 기반 DB 구조 설계
- 금융 상품 비교 및 추천 알고리즘 구현
- 카테고리, 정렬 기준에 따른 필터링 API 설계

### ✅ 대량 금융 데이터 처리
- 금융 상품 크롤링 로직 설계 (Jsoup, RestTemplate 활용)
- 정제 로직 구현 및 RDBMS 설계 (MySQL)
- 대량 데이터 처리를 위한 **커서 기반 페이지네이션** 구현

### ✅ 시스템 통합 및 테스트
- 프론트엔드(Vue)와 RESTful API 통신 설계 및 협업
- Postman을 활용한 API 테스트 및 예외 처리 설계

---

## 🗂️ 프로젝트 구조
src
- ├── main
- │ ├── java/com/fintech/finns
- │ │ ├── controller
- │ │ ├── domain
- │ │ ├── repository
- │ │ ├── service
- │ │ └── dto
- │ └── resources
- │ ├── application.yml
- │ └── static/
- └── test

---

## 📈 주요 성과

- 대용량 금융 데이터(1만건의 소비내역 + 금융 상품 정보 + 카드 정보)를 처리하기 위한 **커서 기반 페이지네이션 적용**
- 사용자 피드백 기반으로 UI/UX 개선
- 금융 크롤링 데이터를 기반으로 사용자 맞춤 콘텐츠 제공
- 실제 배포 환경에서의 테스트 및 배포 경험(Netlify 활용)

- ## 💬 협업 방식

- GitHub Issues + Projects로 기능 단위 태스크 관리
- Notion 기반 스프린트 회고 및 일정 공유
- Figma로 UI 프로토타입 제작 후 구현
- GitHub Flow 방식으로 브랜치 운영 및 PR 리뷰

---

## 📎 관련 링크

- 🔗 [프론엔드 저장소](https://github.com/KB-FInNS/KB_Project_FrontEnd)
- 📑 발표 자료 [FInNS_portfolio.pdf](https://github.com/user-attachments/files/21430052/FInNS_portfolio.pdf)
- 🎓 부트캠프: KB국민은행 it's your life 5기

