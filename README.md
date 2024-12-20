##  📦 WMS(Warehouse Management System)

WMS는 **창고 재고 관리 시스템**을 만들기 위해서 기획했습니다. 사용자는 재고를 수주, 발주, 이동을 통하여 쉽게 재고관리를 할 수 있습니다.
<br />
<br />
## 📃 프로젝트 개요
⌛ **개발 기간**
- 2024.08.02 ~ 2024.08.09 (소셜로그인기능 및 부서 관련API)
- 2024.08.23 ~ 2024.09.12 (수주서, 발주서, 입고관련API)
- 2024.11.04 ~ 2024.11.13 (EC2+Docker+Jenkins+Nginx+Github Webhooks CICD 파이프라인 구성)
- 2024.12.18 ~ 2024.12.20 (입고완료, 판매 관련 API)
<br />

**역할**
- **최규호** : Full-stack
<br />

## 🔗 서비스 소개
### 1. 직급에 따른 권한 부여
- **ADMIN** : 모든 기능 부여
- **MANAGER** : 회원 제외 CRUD 모든 기능 부여
- **USER** : 회원, 창고, 구역, 부서, 직급 생성 삭제 제외 모든 기능 부여
- **GUEST** : R기능만 가능
<br />

### 2. 회원가입
- **소셜 회원가입** : 카카오 로그인을 통한 빠른 회원가입 진행(Guset 권한 부여)
- **일반 회원가입** : 관리자에게 문의를 통해 회원가입 진행(관리자가 확인 후 권한 부여)
<br />

### 3. 수주서, 발주서, 상품등록, 랙, 셀, 로트, 입고, 판매, 구역, 부서 페이지
- **등록** : 제목에 있는 것들을 C
- **확인** : 제목에 있는 것들을 R
- **수정** : 제목에 있는 것들을 U
- **삭제** : 제목에 있는 것들을 D
<br />

## 🛠 주요 기술
### - KakaoAPI
- 카카오 로그인(Oauth 2.0을 이용하여 Backend에서 로그인 로직 구현)

### - SecurityConfig
- Spring Security의 requestMatcher를 활용하여 요청 경로별로 접근 권한을 설정

### - JWT
- 토큰 인증 방식을 사용하여 서버의 부담을 줄임
- Cookie의 secure(), httpOnly() 옵션을 통해 보안성 향상
- RTR(RefreshTokenRotation)을 사용하여 토큰의 탈취 피해 최소화

### - S3 Image Storage
- 업로드된 이미지를 S3 버킷에 저장하여 서버의 로컬 저장소 부담을 감소
- 이미지 저장 시 파일 이름 중복 방지를 위해 UUID를 적용

### - CustomHandler & APIResponse
- CustomHandler: 공통된 예외 처리를 위해 예외 핸들러를 커스터마이징
- APIResponse: API 응답 구조 표준화

### - Github Webhook, Jenkins를 활용한 CI/CD 자동 배포
- 자동 배포 설정: GitHub Webhook과 Jenkins CI/CD 파이프라인을 연동하여 자동 배포 구현, main 브랜치를 기준으로 푸시(push) 또는 브랜치 병합(merge) 시 Webhook이 트리거되어 배포 프로세스 실행
<br />

## 🛠 빌드 환경

| FrontEnd                | BackEnd                                      | Database   | Infra                      |
| :---------------------- | :------------------------------------------- | :--------- | :------------------------- |
| React.js 18.2.0         | Java 17                                      | MySQL      | AWS EC2                    |
|                         | Spring Boot 3.2.4                            |            | Docker                     |
|                         | Spring Data JPA                              |            | Nginx                      |
|                         | Spring Security                              |            | AWS S3 Bucket              |
|                         | lombok                                       |            |                            |
|                         | Oauth 2.0                                    |            |                            |
|                         | JWT                                          |            |                            |
|                         |                                              |            |                            |

<br>
<br>

## 📄 산출물

#### 아키텍쳐
![아키텍쳐구조도](https://github.com/user-attachments/assets/62058913-eb1e-416a-971b-8fc3cb506442)



#### ERD
![ERD](https://github.com/user-attachments/assets/8d41ffc8-4c11-4210-be02-88efa8d7bd92)

<br/>
