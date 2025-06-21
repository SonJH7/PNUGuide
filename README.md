# PNU Guide

PNU Guide는 부산대학교 캠퍼스를 더욱 쉽고 재미있게 탐험할 수 있도록 도와주는 종합 모바일 가이드 앱입니다. 인터랙티브 코스, 스탬프 수집, 영상 라이브러리, 챗봇, 지도 네비게이션 등 다양한 기능을 한 곳에서 경험하세요.

---

## 주요 기능

### 🎓 Interactive Courses

* 캠퍼스 내 인기 코스를 단계별로 안내
* 각 스팟마다 진행 상태(완료/미완료) 시각화
* 검색·정렬 기능으로 원하는 코스를 빠르게 찾기

### 📸 Stamp Collection

* 사진 촬영 → 머신러닝 클러스터링 매칭으로 해당 스팟 인식
* 퀴즈 풀기 (O/X, 객관식) 완료 시 스탬프 지급
* 나만의 획득 스탬프 뱃지 북 구현

### 🎥 Campus Video Library

* 홈 화면에 대표 건물·단과대학 소개 영상 2개 하이라이트
* “더보기” 클릭 시 유튜브 영상 목록 화면으로 이동
* 검색창·가나다/인기 순 정렬 지원
* 외부 YouTube 앱 호출로 바로 재생

### 💬 Chatbot

* ChatGPT API 연동
* 캠퍼스 정보, 길찾기 팁, 행사 일정 등 대화형 질의응답
* Coroutine 기반 비동기 처리로 부드러운 UX

### 🗺️ Map & Navigation

* Google Maps SDK 탑재
* 현위치 기반 캠퍼스 전체 뷰 및 마커 표시
* 경로 탐색(네비게이션) 기능
* 클릭 한 번으로 스팟 상세 옵션 (사진 촬영 / 퀴즈) 접근

### ⚙️ Settings & Profile

* 익명 UUID 기반 간편 로그인
* 앱 정보, 이용약관, 로그아웃/탈퇴
* 최소한의 개인정보만 요구하여 부담 감소

---

## 기술 스택

| 구분         | 기술 및 라이브러리                       |
| ---------- | -------------------------------- |
| 언어         | Kotlin                           |
| UI         | XML 레이아웃, RecyclerView, Fragment |
| 비동기 처리     | Kotlin Coroutines                |
| 머신러닝       | TensorFlow Lite + 커스텀 클러스터링 모델   |
| 지도         | Google Maps Android SDK          |
| HTTP & API | Retrofit, OkHttp, ChatGPT API    |
| 데이터베이스     | Firebase Firestore (외부 DB)       |
| 외부 앱 연동    | Camera Intent, YouTube Intent    |

---

## 아키텍처

* **MVVM** 패턴
* **Repository** 레이어로 데이터 소스(ML 모델, Firestore, Map)를 추상화
* **ViewModel** 에서 Coroutine으로 비동기 작업 수행
* **LiveData / StateFlow** 를 사용해 UI 업데이트

---

## 설치 및 실행

1. 저장소 클론

   ```bash
   git clone https://github.com/your-org/pnu-guide.git
   ```
2. Android Studio로 프로젝트 열기
3. Google Maps API 키, Firebase 설정 파일(`google-services.json`) 추가
4. `gradlew assembleDebug` 또는 IDE에서 실행

---

## 사용 가이드

1. 앱 실행 후 **익명 로그인**
2. **홈**: 이어서 진행하던 코스, 인기 스팟, 영상 바로가기
3. **Courses**: 코스 리스트 → 상세 → 스팟 클릭
4. **Stamps**: 사진 촬영 → ML 매칭 → 퀴즈 → 스탬프 수집
5. **Chat**: 챗봇으로 캠퍼스 궁금증 해결
6. 헤더 아이콘으로 **지도** 및 **설정** 화면 접근

---

## 기여

1. Fork
2. 브랜치 생성 (`git checkout -b feature/foo`)
3. 커밋 (`git commit -m "Add foo feature"`)
4. Push (`git push origin feature/foo`)
5. Pull Request

---

*PNU Guide로 캠퍼스 탐험을 더욱 스마트하고 즐겁게!*
