let index = { // object이기 때문에 아무일도 일어나지 않음
    init: function () {
        // jQuery 사용
        $("#btn-save").on("click", () => { // function(){}, ()=>{} this를 바인딩하기 위해
            this.save();
        }); // event 바인딩 // 두번째 파라미터에 메서드 작성
        // $("#btn-login").on("click",() => { // function(){}, ()=>{} this를 바인딩하기 위해
        //     this.login();
        // });
        $("#btn-update").on("click", () => { // function(){}, ()=>{} this를 바인딩하기 위해
            this.update();
        });
    },

    save: function () {
        //alert("user의 save 함수 호출");
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        }
        // 회원가입 수행 요청(100초 가정)
        // ajax 통신을 이용해 3개의 데이터를 json으로 변경하여 insert 요청
        // console.log(data); // 자바스크립트 오브젝트
        // console.log(JSON.stringify(data)); //JSON 문자열
        // ajax가 통신 성공하고 서버가 sjon을 리턴해주면 자동으로 자바 오브젝트로 변환해준다
        $.ajax({
            type: "POST",
            url: "/auth/joinProc",
            data: JSON.stringify(data), // http body 데이터
            contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지(MIME)
            //sdataType:"json" // 요청을 서버로 해서 응답이 왔을 때, 기본적으로 모든 것이 문자열(생긴게 json이라면) => 자바스크립트 오브젝트로 변경
        }).done(function (result) {
            console.log(result);
            if (result.status === 500) {
                alert("회원가입에 실패하였습니다.")
            } else {
                alert("회원가입이 완료되었습니다.");
                location.href = "/";
            }
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });

    },

    update: function () {
        let data = {
            id: $("#id").val(),
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        }
        $.ajax({
            type: "PUT",
            url: "/user",
            data: JSON.stringify(data), // http body 데이터
            contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지(MIME)
            //sdataType:"json" // 요청을 서버로 해서 응답이 왔을 때, 기본적으로 모든 것이 문자열(생긴게 json이라면) => 자바스크립트 오브젝트로 변경
        }).done(function (result) {
            console.log(result);
            alert("회원 수정 완료되었습니다.");
            location.href = "/";
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
}

index.init(); // 이걸 호출하면 index 바인딩