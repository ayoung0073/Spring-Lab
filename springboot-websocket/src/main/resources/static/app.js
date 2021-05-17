var stompClient = null;

function getFormatDate(date) {
    //Tue Dec 29 2020 22:11:10 GMT+0900 (대한민국 표준
    console.log("=======" + date);
    var year = date.getFullYear();              //yyyy
    var month = (1 + date.getMonth());          //M
    month = month >= 10 ? month : '0' + month;  //month 두자리로 저장
    var day = date.getDate();                   //d
    day = day >= 10 ? day : '0' + day;          //day 두자리로 저장
    var time = date.toString().substring(16, 24);
    return year + '-' + month + '-' + day + ' ' + time;       //'-' 추가하여 yyyy-mm-dd 형태 생성 가능
}


function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#chat").show();
    } else {
        $("#chat").hide();
    }
    $("#chat").html("");
}

//소켓 연결
function connect() {
    var socket = new SockJS('/ayolo-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        // 입장에 대한 구독
        stompClient.subscribe('/topic/on', function (msg) {
            showHello(JSON.parse(msg.body));
        });
        // 입장에 대한 메시지 전달
        stompClient.subscribe('/topic/message', function (msg) {
            showDetail(JSON.parse(msg.body));
        });
        // 퇴장에 대한 구독
        stompClient.subscribe('/topic/off', function (msg) {
            showBye(JSON.parse(msg.body));
        });
        sendHello();
    });
}

//소켓 연결 끊음
function disconnect() {
    if (stompClient != null) {
        sendBye();
        stompClient.disconnect();
    }
    setConnected(false);

    console.log("Disconnected");
}

function sendHello() {
    stompClient.send("/app/on", {}, JSON.stringify({
        name: $('#name').val()
    }));
}

function sendDetail() {
    stompClient.send("/app/chat", {}, JSON.stringify({
        name: $('#name').val(),
        content: $('#btn-input').val()
    }));
}

function sendBye() {
    stompClient.send("/app/off", {}, JSON.stringify({
        name: $('#name').val()
    }));
}

function showDetail(message) {
    var html = "";
    var date = new Date(message.sendDate);
    if (message.name == $('#name').val()) {
        html += '<li class="left clearfix">';
        html += '	<span class="chat-img pull-right">'
        html += '		<img src="image/profile.png" width="65" height="43" alt="" class="img-circle">';
        html += '	</span>';
        html += '	<div class="chat-body clearfix">';
        html += '		<div class="header">';
        html += '		<strong class="pull-right primary-font">' + message.name + '</strong>';
        html += '		<small class="text-muted">';
        html += '			<i class="fa fa-clock-o fa-fw"></i>' + getFormatDate(date);
        html += '		</small>';
        html += '	</div>';
        html += '	<p>';
        html += message.content;
        html += '	</p>';
        html += '	</div>';
        html += '</li>';
    } else {
        html += '<li class="left clearfix">';
        html += '	<span class="chat-img pull-left">'
        html += '		<img src="image/profile.png" width="65" height="43" alt="" class="img-circle">';
        html += '	</span>';
        html += '	<div class="chat-body clearfix">';
        html += '		<div class="header">';
        html += '		<strong class="primary-font">' + message.name + '</strong>';
        html += '		<small class="pull-right text-muted">';
        html += '			<i class="fa fa-clock-o fa-fw"></i>' + getFormatDate(new Date(message.sendDate));
        html += '		</small>';
        html += '	</div>';
        html += '	<p>';
        html += message.content;
        html += '	</p>';
        html += '	</div>';
        html += '</li>';
    }

    $(".chat").append(html);
    $('.panel-body').scrollTop($(".chat")[0].scrollHeight);
}

function showHello(message) {
    var html = "";

    html += '<li class="left clearfix">';
    html += '	<div class="chat-body clearfix">';
    html += '	<div class="header">';
    html += '		<strong class="primary-font">' + message.name + '</strong>';
    html += '		<small class="pull-right text-muted">';
    html += '			<i class="fa fa-clock-o fa-fw"></i>' + getFormatDate(new Date(message.sendDate));
    html += '		</small>';
    html += '	</div>';
    html += '	<p>';
    html += message.name + '님이 입장하였습니다';
    html += '	</p>';
    html += '	</div>';
    html += '</li>';

    $(".chat").append(html);
    $('.panel-body').scrollTop($(".chat")[0].scrollHeight);
}

function showBye(message) {
    var html = "";
    var date = message.sendDate

    html += '<li class="left clearfix">';
    html += '	<div class="chat-body clearfix">';
    html += '	<div class="header">';
    html += '		<strong class="primary-font">' + message.name + '</strong>';
    html += '		<small class="pull-right text-muted">';
    html += '			<i class="fa fa-clock-o fa-fw"></i>' + getFormatDate(new Date(message.sendDate));
    html += '		</small>';
    html += '	</div>';
    html += '	<p>';
    html += message.name + '님이 퇴장하였습니다';
    html += '	</p>';
    html += '	</div>';
    html += '</li>';

    $(".chat").append(html);
    $('.panel-body').scrollTop($(".chat")[0].scrollHeight);
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#connect").click(function () {
        // 소켓 연결
        connect();
    });
    $("#disconnect").click(function () {
        // 소켓 연결 끊음
        disconnect();
    });
    $("#btn-chat").click(function () {
        // 메시지 전달
        sendDetail();
        $('#btn-input').val('');
    });
});