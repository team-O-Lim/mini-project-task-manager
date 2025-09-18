package org.example.o_lim.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.o_lim.entity.base.BaseTimeEntity;
import org.hibernate.annotations.Comments;

@Entity
@Table(name = "comments")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseTimeEntity {

    // 코멘트 고유키
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 태스크 외래키
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "task_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_comment_task_id"))
    private Task task;

    // 작성자명
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_comment_author_id"))
    private User author;

    // 댓글 내용
    @Column(name = "content", nullable = false)
    private String content;

    public void delete(Comment comment) {
        if (comment == null) return;
    }
}
