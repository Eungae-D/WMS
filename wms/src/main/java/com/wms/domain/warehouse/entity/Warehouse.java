package com.wms.domain.warehouse.entity;

import com.wms.domain.area.entity.Area;
import com.wms.domain.inputWarehouseDetail.entity.InputWarehouseDetail;
import com.wms.domain.inventory.entity.Inventory;
import com.wms.domain.purchaseDetail.entity.PurchaseDetail;
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
public class Warehouse extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "warehouse", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Area> areas = new ArrayList<>();

    @OneToMany(mappedBy = "warehouse", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Inventory> inventory = new ArrayList<>();

    @OneToMany(mappedBy = "warehouse", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PurchaseDetail> purchaseDetails = new ArrayList<>();

    @OneToMany(mappedBy = "warehouse", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<InputWarehouseDetail> inputWarehouseDetails = new ArrayList<>();

    @Builder
    public Warehouse(String code, String name){
        this.code = code;
        this.name = name;
    }
}
