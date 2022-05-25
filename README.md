# place-search-project (장소 검색 시스템)

## Framework 환경
  * Java 8
  * SpringBoot 2.6.7
  * Mybatis - orm을 위한 mybatis 사용
  * maven
  * jetty - tomcat uri 한글 인코딩 문제로 인한 jetty 사용
  * lombok - annotation 사용으로 인한 getter, setter 등 기본적인 method set

## Database
* mysql
  * aws rds

## Configuration
  * src/main/resources/application-sample.yml파일을 참고하여 src/main/resources/application.yml생성
  * 배포환경에 맞춰 설정값 정의(DB, server port, logging level, api key)

### 대용량 트래픽 처리 방안
  * aws elastic load balancing을 사용한 트래픽 분산
    * load balencer에 2대의 was mapping
  * java heap memory 증설

### 새로운 검색 API 제공자의 추가 시 변경 영역 최소화
  * yml, util, property를 나누는 구조로 설계해 변경 영역 최소화

### 지속적 유지 보수 및 확장에 용이한 아키텍처
  * mvc 패턴에 기반한 설계
  * api util을 이용한 관리 용이
  * response model mapping을 이용한 가시성 확대
  * aop를 이용한 log 적재


## Usage
### 장소 검색 api
api 명세
|parameter             |description   |necessary   |
|-----------------------|-----------|----------------|
|`q`            |파라미터설명        |필수             |
```sh
curl http://search-elb-2137515593.ap-northeast-2.elb.amazonaws.com/v1/place\?q\=#{장소}
```

### 검색 키워드 목록 api
```sh
curl http://search-elb-2137515593.ap-northeast-2.elb.amazonaws.com/v1/searchTop10
```