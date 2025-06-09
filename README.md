# PNUGuide

Coroutine
구현 위치: Retrofit + Kotlin Coroutines를 사용하여
GPT-4 챗봇(OpenAI API) 호출
YouTube Data API 호출
Google Maps/Direction API 비동기 요청
설명: 모든 외부 API 통신을 suspend 함수 + ViewModelScope.launch { … } 형태로 처리하여 메인(UI) 스레드를 블로킹하지 않고 결과를 받아옵니다.
Jetpack Library 
RecyclerView
CourseActivity에서 코스 리스트(썸네일, 이름, 소요 시간) 표시
SpotListFragment(코스 내 스팟 목록)에서 스팟 아이템 나열
Fragment
CourseActivity 내부에 탭별 프래그먼트(인기, 역사·문화, 학습·휴식)로 코스 목록 분리
MainActivity 하단 탭(Home, Map, Profile)을 Fragment로 화면 전환
DrawerLayout
MainActivity 상단 햄버거 아이콘 클릭 시 사이드 메뉴(설정, 익명 닉네임 초기화 등) 표시
외부 App 연동 
YouTube 앱 Intent
SpotDetailActivity에서 각 단과대학·건물 소개 영상을 YouTube 앱으로 바로 연동
Camera 앱 Intent (ML QR 대신 사진 촬영)
사용자가 곧바로 카메라 앱을 호출해 장소 사진 촬영 → ML 모델 유사도 비교 후 스탬프 자동 부여
DB
Firebase Firestore(외부 DB)
비회원(익명)에 대한 사용자 데이터(스탬프 수집 기록, 코스 즐겨찾기, 퀴즈 결과)를 Firestore 익명 인증(Anonymous Auth)으로
저장·동기화
Firestore의 오프라인 퍼시스턴스 기능을 활성화해 네트워크가 없어도 최근 데이터가 캐시되어 안정적으로 동작
API 
Google Maps SDK & Directions API
캠퍼스 주요 시설 위치 표시, 경로 탐색, 실시간 내비게이션 연동
OpenAI GPT-4 API
ChatActivity에서 GPT 챗봇을 구현해 학교 관련 자연어 질의응답
머신러닝 (30점)
TensorFlow Lite 클러스터링 모델
사전에 학습된 캠퍼스 내 스팟 이미지 클러스터링 모델(tflite) 사용
사용자가 찍은 사진의 특징 벡터를 비교하여 “어떤 스팟과 유사한지” 판별 → 유사도 임계치 이상일 경우 디지털 스탬프 자동 부여
심미성 (UI/UX 평가)
Material 3 디자인 지침 준수
컬러 팔레트(Primary #6200EE 등)와 타이포그래피(Roboto) 적용
Figma 기반 와이어프레임→프로토타입
Auto Layout을 활용해 반응형 레이아웃 구현
애니메이션·다크모드 지원
버튼 클릭, 스탬프 획득 시 Lottie 애니메이션 적용
시스템 다크모드 감지 후 다크 테마로 자동 전환
안정성 (앱 실행 중 설계대로 동작)
오프라인 퍼시스턴스
Firestore 오프라인 캐시 활성화 → 네트워크 불안정 시에도 데이터 조회 가능
오류 처리
모든 네트워크 통신에 try-catch와 Result 래핑 처리, 사용자에게 적절한 에러 토스트/대화상자 안내
백그라운드 작업
Firestore 동기화 및 퀴즈 히스토리 저장 시 WorkManager를 사용하여 안정적으로 처리
테스트
Unit Test: ViewModel 로직(코스 추천 알고리즘, ML 유사도 비교) 최소 80% 이상 커버리지
Instrumentation Test: 주요 Activity/Fragment UI 흐름 자동화 테스트 작성
독창성 (참신함 평가)
ML 클러스터링 기반 사진 인식 → 스탬프 부여
기존의 QR/비콘 방식이 아닌, 실제 사진을 찍어서 해당 장소인지 판단하도록 함으로써 참신한 인터랙션 제공
비회원 모드 + 익명 Firestore 동기화
로그인 없이도 외부 DB에 동기화 가능하여 사용자 진입 장벽 최소화
GPT 챗봇 연동
단순한 정보 나열이 아니라 대화형 자연어 인터랙션을 통해 사용자 경험 차별화