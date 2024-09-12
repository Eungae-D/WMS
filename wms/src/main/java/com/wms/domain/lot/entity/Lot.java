package com.wms.domain.lot.entity;

import com.wms.domain.inputWarehouseDetail.entity.InputWarehouseDetail;
import com.wms.domain.inventory.entity.Inventory;
import com.wms.domain.item.entity.Item;
import com.wms.global.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) //생성자를 통해 값 변경 목적으로 접근하는 메시지들 차단 -> protected 이므로 다른 패키지 내에서 접근 가능
@EntityListeners(AuditingEntityListener.class)
public class Lot extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String lotNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @OneToMany(mappedBy = "lot", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Inventory> inventory = new ArrayList<>();

    @OneToMany(mappedBy = "lot", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<InputWarehouseDetail> inputWarehouseDetails = new ArrayList<>();

    @Builder
    public Lot(String lotNumber, Item item) {
        this.lotNumber = lotNumber;
        this.item = item;
    }

    public static String generateLotNumber(LocalDate date, int sequence) {
        return date.format(DateTimeFormatter.ofPattern("yyyyMMdd")) + String.format("%06d", sequence);
    }
}
