package com.wms.domain.inputWarehouse.entity;

import com.wms.domain.inputWarehouseDetail.entity.InputWarehouseDetail;
import com.wms.domain.purchaseSheet.entity.PurchaseSheet;
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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class InputWarehouse extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchaseSheet_id", nullable = false)
    private PurchaseSheet purchaseSheet;

    @OneToMany(mappedBy = "inputWarehouse", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<InputWarehouseDetail> inputWarehouseDetails = new ArrayList<>();

    @Builder
    public InputWarehouse(Status status, User user, PurchaseSheet purchaseSheet){
        this.status = status;
        this.user = user;
        this.purchaseSheet = purchaseSheet;
    }
}
