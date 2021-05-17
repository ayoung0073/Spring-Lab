package com.may.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더 패턴
// ORM -> Java(다른언어) Object -> 테이블로 매핑 시켜주는 기술
@Entity // ORM 클래스이다 명시하니 가장 가까이 ! -> User 클래스가 MySQL 에 테이블이 생성 됨
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob // 대용량 데이터
    private String content; // 섬머노트 라이브러리 <html> 태그가 섞여서 디자인 됨

    @ColumnDefault("0") // 숫자이기 때문에 "' '"이 아닌 " "
    private int count; // 조회수

    @ManyToOne // Many = Board, One = User
    @JoinColumn(name = "userId")    // 자동으로 FK 생성
    private User user; // DB는 오브젝트 저장할 수 없음, FK, 자바는 오브젝트 저장 가능

    //    alter table Board
    //       add constraint FKnwfsptg8pbhl5hnphivfydtpy
    //       foreign key (userId)
    //       references User (id)

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    // @JoinColumn(name="replyId") // FK 필요없음
    @JsonIgnoreProperties({"board"}) // Reply 클래스의 board 무시 **, user 도 하면 user 속성도 나오지 않음
    // direct일 때는 다 보여줌.
    @OrderBy("id desc")
    private List<Reply> reply;
    // mappedBy : FK 아니다. DB에 컬럼 만드는 것 아니다. join 을 통해 값을 얻기 위해서.
    // board 는 필드 이름(private Board board)(Reply 클래스)
    // OneToMany는 기본패치 전략이 LAZY가 됨. 무조건 들고와야 하니, EAGER로 바꾸자

    @CreationTimestamp
    private LocalDateTime createdAt;

    public void setCount(int count) {
        this.count = count;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
