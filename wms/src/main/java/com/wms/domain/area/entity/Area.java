package com.wms.domain.area.entity;

import com.wms.domain.cell.entity.Cell;
import com.wms.domain.client.entity.Client;
import com.wms.domain.inputWarehouseDetail.entity.InputWarehouseDetail;
import com.wms.domain.inventory.entity.Inventory;
import com.wms.domain.rack.entity.Rack;
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
public class Area extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_id", nullable = false)
    private Warehouse warehouse;

    @OneToMany(mappedBy = "area", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Rack> racks = new ArrayList<>();

    @OneToMany(mappedBy = "area", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Inventory> inventory = new ArrayList<>();

    @OneToMany(mappedBy = "area", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<InputWarehouseDetail> inputWarehouseDetails = new ArrayList<>();

    @Builder
    public Area(String code, String name, Warehouse warehouse){
        this.code = code;
        this.name = name;
        this.warehouse = warehouse;
    }
}
