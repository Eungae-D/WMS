package com.wms.domain.purchaseDetail.entity;

import com.wms.domain.client.entity.Client;
import com.wms.domain.inputWarehouseDetail.entity.InputWarehouseDetail;
import com.wms.domain.item.entity.Item;
import com.wms.domain.purchaseSheet.entity.PurchaseSheet;
import com.wms.domain.warehouse.entity.Warehouse;
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
public class PurchaseDetail extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchaseSheet_id", nullable = false)
    private PurchaseSheet purchaseSheet;

    @Column(nullable = false)
    private Long amount;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_id", nullable = false)
    private Warehouse warehouse;

    @OneToMany(mappedBy = "purchaseDetail", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<InputWarehouseDetail> inputWarehouseDetails = new ArrayList<>();

    @Builder
    public PurchaseDetail(PurchaseSheet purchaseSheet, Long amount,Status status, Item item, Warehouse warehouse){
        this.purchaseSheet = purchaseSheet;
        this.amount = amount;
        this.status = status;
        this.item = item;
        this.warehouse = warehouse;
    }
}
