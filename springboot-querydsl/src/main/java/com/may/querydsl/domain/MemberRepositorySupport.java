package com.may.querydsl.domain;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import static com.may.querydsl.domain.QMember.member;
import static com.may.querydsl.domain.QPost.post;

import java.util.List;

@Repository
public class MemberRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public MemberRepositorySupport(JPAQueryFactory queryFactory) {
        super(Member.class);
        this.queryFactory = queryFactory;
    }

    public List<Member> findByName(String name) {
        return queryFactory
                .selectFrom(member)
                .where(member.name.eq(name))
                .fetch();
    }

    public List<Post> findPostListByName(String name) {
        return queryFactory
                .select(Projections.fields(Post.class,
                        post.id,
                        post.name,
                        post.writer
                ))
                .from(member)
                .join(post).on(post.writer.eq(member))
                 // .join(post).on(post.writer.name.eq(member.name)) // 실패
                .where(member.name.eq(name))
                .fetch();
    }
}
