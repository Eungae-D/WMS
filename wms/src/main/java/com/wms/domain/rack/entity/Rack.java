package com.wms.domain.rack.entity;

import com.wms.domain.area.entity.Area;
import com.wms.domain.cell.entity.Cell;
import com.wms.domain.inputWarehouseDetail.entity.InputWarehouseDetail;
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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Rack extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_id", nullable = false)
    private Area area;

    @OneToMany(mappedBy = "rack", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Cell> cells = new ArrayList<>();

    @OneToMany(mappedBy = "rack", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Inventory> inventory = new ArrayList<>();

    @OneToMany(mappedBy = "rack", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<InputWarehouseDetail> inputWarehouseDetails = new ArrayList<>();

    @Builder
    public Rack(String code, String name, Area area){
        this.code = code;
        this.name = name;
        this.area = area;
    }

}
