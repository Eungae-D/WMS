package com.wms.domain.user.entity;

import com.wms.domain.department.entity.Department;
import com.wms.domain.inventory.entity.Inventory;
import com.wms.domain.orderSheet.entity.OrderSheet;
import com.wms.domain.position.entity.Position;
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

    @Column(nullable = false)
    private String profileImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id", nullable = false)
    private Position position;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Token> token = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Inventory> inventory = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderSheet> orderSheet = new ArrayList<>();

    @Builder
    public User(Long id, SocialType socialType, String email, String password, String name, Role role, String profileImage,Department department, Position position){
        this.id = id;
        this.socialType = socialType;
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
        this.profileImage = profileImage;
        this.department = department;
        this.position = position;
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


