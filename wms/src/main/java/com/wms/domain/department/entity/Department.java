package com.wms.domain.department.entity;

import com.wms.domain.user.entity.User;
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
@NoArgsConstructor(access = AccessLevel.PROTECTED) //생성자를 통해 값 변경 목적으로 접근하는 메시지들 차단 -> protected 이므로 다른 패키지 내에서 접근 가능
@EntityListeners(AuditingEntityListener.class) 
public class Department extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String department_code;

    @Column(nullable = false)
    private String department_name;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<User> users = new ArrayList<>();

    @Builder
    public Department(String department_code, String department_name) {
        this.department_code = department_code;
        this.department_name = department_name;
    }
}

