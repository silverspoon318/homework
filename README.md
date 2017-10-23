## 환경
- java 8
- spring boot 1.4.7
- gradle wrapper

## 접속 URL
http://{localhost}:10880/

에러코드
-1 : url is empty
-2 : url is invalid
-3 : Short URL Maximum count exceeded

테스트 코드
1. 체크 : 생성 전과 후가 original url이 같은지
2. short url 이 중복없이 정상적으로 호출되는지
3. 예외에 대한 리턴 값이 client에 정상적으로 전달되는지
4. 8^8 를 넘었을 때 예외처리가 되는지