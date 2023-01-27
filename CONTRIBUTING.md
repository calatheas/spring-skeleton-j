# Contributing to SkeletonJ API Server

SkeletonJ API 에 기여하기 위해 아래의 내용을 지켜주세요.   
누구나 기여할 수 있는 코드이면서 누구나 기여하고 싶은 코드를 만들어갑니다.

## 필수적인 코드리뷰
반드시 코드리뷰를 받아야 합니다.
## Naming Rules
1. URL : lower-kebab-case
   1. 기본적으로 복수 또는 셀수없는 명사 사용 `users/{userId}`
   2. 리소스의 제어나 실행 등을 나타내는 경우, 동사 허용
2. API 파라미터 : lowerCamelCase
3. Package : only lowercase
4. Class : PascalCase
   1. 명사 중심 + 형용사 약간
5. Method : lowerCamelCase
   1. 행위 중심
   2. 좋은 코드를 위한 자바 메서드 네이밍 - https://tecoble.techcourse.co.kr/post/2020-04-26-Method-Naming/
6. Property : lowerCamelCase
   1. 명사 중심 + 형용사 약간
7. Constant : UPPER_SNAKE_CASE
## 도메인 중심 설계
테스트가 용이한 아키텍처를 지향하고 항상 외부 컴포넌트(adapter) 가 내부 컴포넌트(domain, service) 에 의존 한다.
1. common 패키지 : 도메인 공통, 스프링 설정, 유틸 등
2. 도메인 패키지 : 도메인 중심의 클린 아키텍처 지향
   1. adapter
       - 컨트롤러(web.in), 리파지토리(persistence), 웹 클라이언트(web.out)
       - adapter 에서 사용하는 모델은 항상 분리하고 매핑을 통해 내부 컴포넌트들을 호출
       - adapter 의 모델은 도메인 모델에 의존하며 변환 책임을 갖는다.
       - 컨트롤러 모델 중 커맨드 서비스는 request, response 접미어 사용
       - 컨트롤러 모델 중 쿼리 서비스는 filter, query 접미어 사용 
       - 예외적으로 쿼리 서비스는 adapter 와 service 가 같은 모델을 사용할 수 있다.
       - 리파지토리 모델은 도메인을 직접 사용하므로 adapter 와 service 의 중간 정도로 정의
       - 외부API 호출하는 웹 클라이언트는 의존성 역전을 위한 port 사용
   2. domain
       - 도메인 모델
       - Persistence 와 분리하지 않고 JPA Entity 를 직접 사용 (편의성)
       - 도메인 모델에 최대한 많은 책임을 부여
       - 유효성 검증을 위해 Persistence 의 기능이 필요한 경우, 도메인을 위한 별도 서비스 구현
   3. service
       - 유스케이스(=비즈니스 로직) 구현
       - 쿼리 서비스와 커맨드 서비스 분리
       - 쿼리 서비스는 조회 모델을 제공하는 서비스, 커맨드 서비스는 도메인 변경하는 서비스(도메인 변경을 위한 조회 기능을 제공할 수 있다.)
       - 서비스 입출력 전용 모델을 사용
       - 보통 Header 입력, Path variable 입력, Payload 입력이 합쳐져서 서비스 입력 모델을 구성하게 된다.
       - 커맨드 서비스는 command 모델을 사용
       - 쿼리 서비스는 query 모델을 사용
## 상세한 예외
서버 내부의 예외를 최대한 전달
1. Validation exception
    - API 요청에 대한 입력 검증은 Validation exception 을 이용
    - 여러 필드에 대한 검증 결과를 모두 전달
2. Exception type
    - 재사용하기 보다는 상세하게 예외를 분할하고 예외코드를 부여
    - status code 매핑하여 메시지 외에도 확인할 수 있도록 함
    - 커스텀 예외코드(4자리)는 시스템 오픈 전에 정리
3. Message code
    - message.properties 에 코드를 등록해서 사용
    - 예외 발생시 문자열 메시지를 등록할 수도 있고, 메시지 코드를 등록할 수도 있다.
## Enum code 활용
1. 코드 데이터는 Enum으로 구현하여 표현력을 높임
2. 코드 API 를 통해 다른 시스템과 코드 데이터 공유
3. Enum name 과 code 를 매핑하기 위해 converter 사용
## Restdoc 을 이용한 문서화
1. 컨트롤러 테스트(API 정상 케이스) 작성
2. 입출력으로 사용하는 Mock 모델은 공통화를 위해 MockModel 클래스에 작성
3. Restdoc 공통 유틸(RestdocUtils) 활용
4. 개별 adoc, index.adoc 파일 수정하여 새로운 문서 반영
## 코드 커버리지 100%
1. 컨트롤러 테스트
    - 문서화를 하기 위해 필요
    - 인터페이스 변경에 따른 Regression test 수행
2. 서비스 테스트
    - 비즈니스 로직 변경에 따른 Regression test 수행
    - adapter 영역은 mocking
3. DB 연동테스트
    - JPA를 사용하여 서버 초기화시 엔티티에 대한 DB 체크 가능
    - 서비스 테스트에서 mocking 하여 테스트 진행 했으므로 큰 실익이 없음
5. 테스트 DB, Mock server 를 이용한 통합테스트
    - 금상첨화

