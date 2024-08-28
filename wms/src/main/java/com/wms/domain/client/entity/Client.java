package com.wms.domain.client.entity;

import com.wms.domain.item.entity.Item;
import com.wms.domain.orderSheet.entity.OrderSheet;
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
public class Client extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String owner;

    @Column(nullable = false)
    private String tel;

    @Column
    private String fax;

    @Column(nullable = false)
    private String bank;

    @Column(nullable = false)
    private String account;

    @Column(nullable = false)
    private String zipCode;

    @Column(nullable = false)
    private String address1; //주소

    @Column(nullable = false)
    private String address2; //상세주소

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String businessNumber; //사업자 등록번호

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Item> items = new ArrayList<>();

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderSheet> orderSheet = new ArrayList<>();

    @Builder
    public Client(String code, Category category, String name, String owner, String tel, String fax, String bank, String account, String zipCode, String address1, String address2, String email, String businessNumber){
        this.code = code;
        this.category = category;
        this.name = name;
        this.owner = owner;
        this.tel = tel;
        this.fax = fax;
        this.bank = bank;
        this.account = account;
        this.zipCode = zipCode;
        this.address1 = address1;
        this.address2 = address2;
        this.email = email;
        this.businessNumber = businessNumber;
    }
}
