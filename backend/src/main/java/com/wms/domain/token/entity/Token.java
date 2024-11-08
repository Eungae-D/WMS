package com.wms.domain.token.entity;

import com.wms.domain.user.entity.User;
import com.wms.global.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Token extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = true, length = 200)
    private String refreshToken;

    private LocalDateTime expiredDate;

    @Builder
    public Token(User user, String refreshToken, LocalDateTime expiredDate) {
        this.user = user;
        this.refreshToken = refreshToken;
        this.expiredDate = expiredDate;
    }

    public void updateToken(String refreshToken, LocalDateTime expiredDate) {
        this.refreshToken = refreshToken;
        this.expiredDate = expiredDate;
    }

    public static Token toEntity(User user,String refreshToken, LocalDateTime expiredDate) {
        return Token.builder()
                .user(user)
                .refreshToken(refreshToken)
                .expiredDate(expiredDate)
                .build();
    }


}
