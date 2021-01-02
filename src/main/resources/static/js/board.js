let index = { // object이기 때문에 아무일도 일어나지 않음
    init:function (){
        // jQuery 사용
        $("#btn-board-save").on("click",() => { // function(){}, ()=>{} this를 바인딩하기 위해
            this.save();
        }); // event 바인딩 // 두번째 파라미터에 메서드 작성
        $("#btn-delete").on("click",() => {
            this.delete();
        });
        $("#btn-update").on("click",() => {
            this.update();
        });
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

    delete: function (){

        let id = $("#id").text(); // value 하다가 안됨
        console.log(($("#id").val())); // 안 나 옴
        console.log(($("#id").text()));


        $.ajax({
            type:"DELETE",
            url:"/api/board/" + id,
            dataType: "json"
        }).done(function (result){
            console.log(result);
            alert("삭제가 완료되었습니다.");
            location.href = "/";
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    update: function (){
        //alert("user 의 save 함수 호출");
        let id = $("#id").val();
        let data = {
            title: $("#title").val(),
            content: $("#content").val(),
        }
        $.ajax({
            type:"PUT",
            url:"/api/board/" + id,
            data:JSON.stringify(data),
            contentType:"application/json; charset=utf-8",
            dataType: "json"
        }).done(function (result){
            console.log(result);
            alert("글수정이 완료되었습니다.");
            location.href = "/";
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });

    },
}

index.init(); // 이걸 호출하면 index 바인딩