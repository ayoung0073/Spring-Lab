let index = { // object이기 때문에 아무일도 일어나지 않음
    init:function (){
        // jQuery 사용
        $("#btn-board-save").on("click",() => { // function(){}, ()=>{} this를 바인딩하기 위해
            this.save();
        }); // event 바인딩 // 두번째 파라미터에 메서드 작성
    },
    
    save: function (){
        //alert("user 의 save 함수 호출");
        let data ={
            title: $("#title").val(),
            content: $("#content").val(),
        }
        $.ajax({
            type:"POST",
            url:"/api/board",
            data:JSON.stringify(data),
            contentType:"application/json; charset=utf-8",
            dataType: "json"
        }).done(function (result){
            console.log(result);
            alert("글쓰기가 완료되었습니다.");
            location.href = "/";
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });

    },
}

index.init(); // 이걸 호출하면 index 바인딩