# 서버리스

서버리스란 서버가 없다는 뜻이다. 
서버에서 처리하는 작업을 클라우드 기반의 서비스로 처리하는 것이다. 서버를 직접 구축하지 않고 서비스를 사용함으로써 구축 및 관리 비용을 낮추고, <br>
서버 운영에 따라 발생하는 유지보수의 어려움을 줄이기 위해 필요한 순간에만 컴퓨팅 서비스를 제공한다.
## Cloud Native Patterns

- Legacy(오래된 방식을 의미) : 인프라부터 애플리케이션까지 구축
- IaaS(Infrastructure as a Service) : 하드웨어와 가상화, OS 등 인프라 요소를 서비스로 제공 (ex: AWS EC2)
- CaaS(Container as a Service) : 서비스 형태로 제공되는 컨테이너를 활용하여 배포 (ex: AWS EC2)
- PaaS(Platform as a Service) : 애플리케이션 개발에 집중할 수 있도록 인프라와 런타임 환경을 제 (ex: AWS Elastic Beanstalk)
- FaaS(Function as a Service) : 실행할 함수 코드만 구현 (ex: AWS Lambda)
- SaaS(Software as a Service) : 제공되는 소프트웨어 사용 (ex: Gmail, Dropbox)

![image](https://lerablog.org/wp-content/uploads/2019/06/IaaS-PaaS-and-SaaS.png)
 


## 서버리스 형태
일반적으로 두 가지 형태로 나눈다.
- BaaS (Backend as a Service)
  그동안 개발자가 직접 구현하던 백엔드의 부분부분을 서비스로 제공받는 것이다. 예를 들어 Auth0, AWS Cognito 같은 인증서비스나 구글의 모바일 앱 백엔드 서비스인 Firebase가 있다.
- FaaS (Function as a Service)
  함수를 서비스로 이용하는 것이다. 개발자가 환경을 구성하고 서버 코드를 작성하는 것이 아닌, FaaS를 이용함으로써 함수만 구현하면 된다.
  AWS Lambda가 대표적인 FaaS 이다. <br>
  예를 들어, Amazon S3에 AWS Lambda를 트리거하면 Amazon S3에 업로드를 진행할 때 서버코드를 AWS Lambda로 실행할 수 있다. 요청 수에 맞추어 확장도 가능하다.
  

### 서버리스를 도입하면 NoOps인가.
관리에는 모니터링, 보안, 네트워크 등이 있고, 필요에 따라 직접 스케일링 하는 경우가 발생한다. FaaS가 오토 스케일링 되지만 설정에는 한계가 있다. <br>
운영 중인 시스템에서 이러한 문제가 발생하면 해결해야 할 전략이 필요하고, 시스템 관리자는 이를 대응하는 방법을 제시할 것이다.


### FaaS를 이용하면 서버리스인가?
서버리스는 필요할 때 일시적인 컴퓨팅 서비스를 받는 것이다. FaaS만 보면 서버리스가 맞지만 서버리스 아키텍처와 별개로 보는 것이 맞다.<br>
서버리스 아키텍쳐는 FaaS와 다른 클라우드 서비스를 이용하여 서버관리를 직접 하지 않도록 구성하는 것이다. <br>
만약 FaaS를 이용했지만 RDBMS와 연결하다면 이는 '절반 정도는 서버리스 아키텍처로 구성되어 있다'라고 표현할 수 있다.<br>
AWS Lambda는 용량에도 제한이 있어 이보다 크게 되면 Lambda를 쪼개거나 혹은 AWS Fargate를 이용하여 컨테이너 기반 서버리스 아키텍처를 생각해볼 수 있다.<br><br>
AWS에서는 아래와 같이 정의하고 있다.
> **서버리스 아키텍처**
> 인프라를 관리할 필요 없이 애플리케이션과 서비스를 구축하고 실행하는 방식이다. 애플리케이션은 여전히 서버에서 실해오디만, 모든 서버 관리는 AWS에서 수행한다.<br>
> 더이상 애플리케이션, 데이터베이스 및 스토리지 시스템을 실행하기 위해 서버를 프로비저닝, 확장 및 유지 관리할 필요가 없다.


## FaaS의 단점
- 상태유지가 되지 않는다.
  컨테이너(혹은 microVM)이 잠시 실행되는 환경이다. 이는 상태 비저장(Stateless)을 의미한다. <br>
  서버 코드로 상태 값을 유지하고, 그 값을 이용하여 로직을 구현하던 방식은 사용할 수 없다. 보완하기 위해 **DB**를 이용하는 방법이 있다.
- 함수가 실행되기 위해 항상 준비된 상태가 아니다.
    실행 시 약간의 지연시간이 발생하는데 이를 **콜드 스타트**라고 한다. 만약 함수를 실행했다면 함수를 실행한 컨테이너는 잠시 대기 상태가 되는데 이때 다시 실행하는 것을 **웜 스타트**라고 한다.<br>
  이는 준비되어 잇어 처음 호출할 때 발생하는 지연 시간이 발생하지 않는다.
- 서비스 제공사(클라우드 회사)에 의존적이다.


# 네트워크

## Amazon VPC
AWS Lambda는 네트워크 설정을 Amazon VPC(Virtual Private Cloud)로 구성한다.
> **VPN**(가상사설망)<br>
> 회사의 네트워크를 구성할 때 보안상의 이유로 각각의 권한별 혹은 부서별로 접근망을 가상으로 구성한다. <br>
> ![image](https://cdn.mhns.co.kr/news/photo/202012/424456_557690_2018.png) <br>

Amazon VPC는 클라우드 환경에서 구성하는 VPN이라고 생각하면 된다. Amazon VPC가 없으면 서로 복잡하게 거미줄처럼 연결될 것이다.<br>
이렇게 되면 각자 서버별로 네트워크 관리가 필요하다. 1개의 서버가 추가될 때마다 복잡한 과정을 거쳐야 한다.<br>
이런 복잡도를 해결하기 위해 Amazon VPC가 필요하다. 이는 네트워크를 그룹화하여 보다 쉽게 관리할 수 있도록 돕는다.


# AWS Lambda
Amazon Web Services에서 2014년부터 제공하는 FaaS 서비스이다. <br>
![image](https://docs.aws.amazon.com/ko_kr/lambda/latest/dg/images/logs-api-concept-diagram.png) <br>
[참고 링크](https://docs.aws.amazon.com/ko_kr/lambda/latest/dg/runtimes-context.html) <br><br>
AWS Lambda는 이벤트를 감지하여 아마존 리눅스 환경이 MicroVM을 띄우고 함수를 실행한다. 그리고 결과를 처리한다. <br>
함수를 실행될 때 필요한 환경이 있는데, 이것을 런타임이라고 한다. 런타임은 어떤 언어로 작성하는지에 따라 다르며, 그 환경에 따라 성능 차이가 있다.<br>
> Lambda의 내부
> ![image](https://images.velog.io/images/ayoung0073/post/f7b0077d-8720-43cf-948f-8362728938ac/image.png)
> Compute substrate : 함수가 실행될 MicroVM<br>
> Execution Environment : 환경 변수 등의 실행 환경 <br><br>
> 람다가 실행되면 아마존 리눅스 OS 기반의 Micro VM이 실행된다. 환경 변수 등 실행환경을 맞추고, 지정한 언어별 런타임 환경을 준비한다.<br>
> 그리고 마지막에 작성한 함수를 실행한다.

### 파일 시스템
AWS Lambda에서 AWS EFS를 사용할 수 있게 됐다.<br>
AWS EFS를 사용하면 기존에 용량 제한이 있었으나 파일 시스템을 사용함으로써 용량에 제한이 없어졌다. 이를 통해 무거운 라이브러리 패키지를 사용하는 것이 가능하게 되었다.


# API Gateway
Amazon API Gateway는 사용자가 쉽게 API를 생성, 게시, 관리, 모니터링, 보안까지 관리가 가능한 완전관리형 서비스이다. <br>
Amazon API Gateway는 트래픽 관리, CORS 지원, 권한 부여 및 액세스 제어, 모니터링 및 API 버전 관리 등 최대 수십만 개의 동시 API 호출을 수신 및 처리하는 데 관계된 모든 작업을 처리한다.<br>
Amazon API Gateway는 최소 요금이 존재하지 않는다. 수신한 API 호출과 전송한 데이터양에 대한 요금 청구를 받는다. <br>
Amazon API Gateway의 선택 옵션으로는 REST API, WebSocket API가 있고, 2019년에 신규 추가된 HTTP API까지 존재한다.

> HTTP API와 REST API
> HTTP API는 100만 요청 기준 1달러이고 REST API는 3.5달러이다. 
> HTTP API는 기존 REST API 대비 대기 시간을 최대 60% 이상 줄일 수 있다. HTTP API는 프록시 기능만을 제공하며, 그에 맞게 최적화되어 있기 때문이다.<br>
> REST API의 기능이 정말 본인이 구현하는 데 필요한 기능이 존재하는지, HTTP API로 충분하지 않은지 고려해봐야 한다.


# 모니터링
## Amazon CloudWatch
AWS 리소스와 AWS에서 실시간으로 실행 중인 애플리케이션을 모니터링한다. Amazon CloudWatch를 사용하여 지표를 수집 및 추적하고 로그 파일을 수집, 모니터링 하고 경보를 설정할 수 있다.<br>
이를 통해 시스템의 전반적인 리소스 활용률, 성능, 상태를 파악할 수 있다. 이 정보를 기반으로 문제에 대응하며, 개선점을 찾아 우너활한 애플리케이션 상태를 유지할 수 있다.


# 데이터베이스
## Amazon DynamoDB
NoSQL 기반의 완전관리형 데이터베이스 서비스이다.<br>
...


# 기본 예제

## AWS Lambda에서 AWS Lambda 실행하기 