package com.wms.domain.inputWarehouseDetail.entity;

import com.wms.domain.area.entity.Area;
import com.wms.domain.cell.entity.Cell;
import com.wms.domain.inputWarehouse.entity.InputWarehouse;
import com.wms.domain.item.entity.Item;
import com.wms.domain.lot.entity.Lot;
import com.wms.domain.purchaseDetail.entity.PurchaseDetail;
import com.wms.domain.rack.entity.Rack;
import com.wms.domain.warehouse.entity.Warehouse;
import com.wms.global.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class InputWarehouseDetail extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(nullable = false)
    private LocalDateTime arrivalDateTime;

    @Column(nullable = false)
    private Long amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lot_id", nullable = false)
    private Lot lot;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_id", nullable = false)
    private Warehouse warehouse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_id", nullable = false)
    private Area area;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rack_id", nullable = false)
    private Rack rack;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cell_id", nullable = false)
    private Cell cell;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchaseDetail_id", nullable = false)
    private PurchaseDetail purchaseDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inputWarehouse_id", nullable = false)
    private InputWarehouse inputWarehouse;

    @Builder
    public InputWarehouseDetail(Status status, LocalDateTime arrivalDateTime, Long amount, Lot lot, Warehouse warehouse, Area area, Rack rack, Cell cell, Item item, PurchaseDetail purchaseDetail, InputWarehouse inputWarehouse){
        this.status = status;
        this.arrivalDateTime = arrivalDateTime;
        this.amount = amount;
        this.lot = lot;
        this.warehouse = warehouse;
        this.area = area;
        this.rack = rack;
        this.cell = cell;
        this.item = item;
        this.purchaseDetail = purchaseDetail;
        this.inputWarehouse = inputWarehouse;

    }
}
