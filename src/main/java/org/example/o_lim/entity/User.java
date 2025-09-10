package org.example.o_lim.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.o_lim.common.enums.Gender;
import org.example.o_lim.common.enums.RoleType;
import org.example.o_lim.entity.base.BaseTimeEntity;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
        name="users",
        uniqueConstraints = {
                @UniqueConstraint(name="uk_user_login_id", columnNames = "email"),
                @UniqueConstraint(name="uk_users_login_id", columnNames = "login_id"),
                @UniqueConstraint(name="uk_user_nickname", columnNames = "nickname")
        }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

//    로그인 아이디
    @Column(name="login_id", nullable = false, length = 50)
    private String loginId;
//    비밀번호
    @Column(name="password", nullable = false, length = 255)
    private String password;
//    이메일
    @Column(name="email", nullable = false, length = 255)
    private String email;
//    닉네임
    @Column(name="nickname", nullable = false, length = 50)
    private String nickname;
//    성별
    @Column(name="gender", length = 10)
    @Enumerated(EnumType.STRING)
    private Gender gender;
//    권한
    @ElementCollection(fetch = FetchType.LAZY)
    @Column(name="role", length = 30, nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<RoleType> roles = new HashSet<>();

}
