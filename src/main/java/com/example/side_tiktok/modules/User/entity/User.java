package com.example.side_tiktok.modules.User.entity;

import com.example.side_tiktok.modules.User.enums.RoleType;
import com.example.side_tiktok.modules.User.enums.UserStatus;
import com.example.side_tiktok.modules.common.jpa.BaseTime;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "USERS")
public class User extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("고유 번호")
    private Long id;

    @Comment("유저 이름")
    private String username;

    @Comment("유저 비밀번호")
    private String password;

    @Comment("유저 이메일")
    private String email;

    @Comment("유저 생년월일")
    private LocalDateTime birth;

    @Comment("유저 권한")
    @Enumerated(EnumType.STRING)
    private RoleType role;

//    @Comment("유저 이메일 인증 여부")
//    private boolean emailVerified;

    @Comment("유저 상태")
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Builder
    public User(Long id, String username, String password, String email, RoleType role, UserStatus status) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.status = status;
    }
}
