package com.wms.domain.inventory.entity;

import com.wms.domain.area.entity.Area;
import com.wms.domain.cell.entity.Cell;
import com.wms.domain.item.entity.Item;
import com.wms.domain.lot.entity.Lot;
import com.wms.domain.rack.entity.Rack;
import com.wms.domain.user.entity.User;
import com.wms.domain.warehouse.entity.Warehouse;
import com.wms.global.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Inventory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private int orderQuantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

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
    @JoinColumn(name = "lot_id", nullable = false)
    private Lot lot;

    @Builder
    public Inventory(int quantity, int orderQuantity, User user, Item item, Warehouse warehouse, Area area, Rack rack, Cell cell, Lot lot) {
        this.quantity = quantity;
        this.orderQuantity = orderQuantity;
        this.user = user;
        this.item = item;
        this.warehouse = warehouse;
        this.area = area;
        this.rack = rack;
        this.cell = cell;
        this.lot = lot;
    }

    public void addQuantity(Long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("추가할 수량은 0보다 커야 합니다.");
        }
        this.quantity += amount;
    }

}
