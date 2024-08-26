package com.wms.domain.item.entity;

import com.wms.domain.client.entity.Client;
import com.wms.domain.inventory.entity.Inventory;
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
public class Item extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long unitPrice;

    @Column(nullable = false)
    private Long shippingPrice;

    @Column(nullable = false)
    private String itemImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Inventory> inventory = new ArrayList<>();

    @Builder
    public Item(String code, String name, Long unitPrice, Long shippingPrice, String itemImage, Client client) {
        this.code = code;
        this.name = name;
        this.unitPrice = unitPrice;
        this.shippingPrice = shippingPrice;
        this.itemImage = itemImage;
        this.client = client;
    }

}
