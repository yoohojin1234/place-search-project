# place-search-project (장소 검색 시스템)

* URL test
  * 장소 검색 api
    * curl http://search-elb-2137515593.ap-northeast-2.elb.amazonaws.com/v1/place\?q\=#{장소}
  * 검색 키워드 목록 api
    * curl http://search-elb-2137515593.ap-northeast-2.elb.amazonaws.com/v1/searchTop10

* 대용량 트래픽 처리 방안
  * aws elastic load balancing을 사용한 트래픽 분산
    * load balencer에 2대의 was mapping
  * java heap memory 증설

* 새로운 검색 API 제공자의 추가 시 변경 영역 최소화
  * yml, util, property를 나누는 구조로 설계해 변경 영역 최소화

* 지속적 유지 보수 및 확장에 용이한 아키텍처
  * mvc 패턴에 기반한 설계
  * api util을 이용한 관리 용이
  * response model mapping을 이용한 가시성 확대
  * aop를 이용한 log 적재

* 사용 라이브러리
  * jetty
    * tomcat uri 한글 인코딩 문제로 인한 jetty 사용
  * lombok
    * annotation 사용으로 인한 getter, setter 등 기본적인 method set
  * mybatis
    * orm을 위한 mybatis 사용

* Framework 환경
  * Java 8
  * SpringBoot 2.6.7

* Configuration
  * src/main/resources/application-sample.yml 복사하여 src/main/resources/application.yml 에 환경설정

* System dependencies
  * maven

* Database
  * mysql
    * aws rds

