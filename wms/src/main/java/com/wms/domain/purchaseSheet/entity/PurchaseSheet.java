package com.wms.domain.purchaseSheet.entity;

import com.wms.domain.client.entity.Client;
import com.wms.domain.inputWarehouse.entity.InputWarehouse;
import com.wms.domain.orderDetail.entity.OrderDetail;
import com.wms.domain.orderSheet.entity.OrderSheet;
import com.wms.domain.purchaseDetail.entity.PurchaseDetail;
import com.wms.domain.user.entity.User;
import com.wms.global.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class PurchaseSheet extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(nullable = false)
    private LocalDate dueDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderSheet_id", nullable = false)
    private OrderSheet orderSheet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @OneToMany(mappedBy = "purchaseSheet", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PurchaseDetail> purchaseDetails = new ArrayList<>();

    @OneToMany(mappedBy = "purchaseSheet", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<InputWarehouse> inputWarehouses = new ArrayList<>();


    @Builder
    public PurchaseSheet(Status status, LocalDate dueDate, OrderSheet orderSheet, User user, Client client){
        this.status = status;
        this.dueDate = dueDate;
        this.orderSheet = orderSheet;
        this.user = user;
        this.client = client;
    }
}
