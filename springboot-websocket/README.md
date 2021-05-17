# web-socket

웹에서 채팅을 구현할 때 사용 채팅은 누군가 대화를 보내면, 서버에 요청을 보내지 않아도 서버에게 정보를 받아야 함 -> 내가 원하는 정보에 대해 구독을 신청하고, 토픽에 대한 메세지를 발행하면 해당 토픽을 구독하고
있는 모든 사용자에게 보내주는 방식<br>
HTTP/HTTPS는 사용자가 매번 연결을 요청해 정보를 요청해야하고, 그때마다 Header에 요청 정보를 함께 보내야 함. **BUT** 소켓통신은 한번 연결하면 연결이 유지되어서 별다른 설정없이 정보를 주고 받을
수 있음

<https://velog.io/@ayoung0073/springboot-Web-Socket>

![image](https://user-images.githubusercontent.com/69340410/103293277-e4a87e80-4a32-11eb-877f-01bc6f1fa1d5.png)
