package com.wms.domain.user.entity;

import com.wms.domain.department.entity.Department;
import com.wms.domain.token.entity.Token;
import com.wms.global.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class User extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 10)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = true)
    private String profileImage;

    @Column(nullable = false)
    private boolean authorization;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Token token;



    @Builder
    public User(Long id, SocialType socialType, String email, String password, String name, Role role, String profileImage, boolean authorization){
        this.id = id;
        this.socialType = socialType;
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
        this.profileImage = profileImage;
        this.authorization = authorization;
    }

    //소셜로그인 생성자
    public User(SocialType socialType, String email, String name, Role role, String profileImage){
        this.socialType = socialType;
        this.email = email;
        this.name = name;
        this.password = String.valueOf(email.hashCode()+name.hashCode());
        this.role = role;
        this.profileImage = profileImage;
    }
}


