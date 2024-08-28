package com.wms.domain.orderSheet.entity;

import com.wms.domain.client.entity.Client;
import com.wms.domain.inventory.entity.Inventory;
import com.wms.domain.orderDetail.entity.OrderDetail;
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
public class OrderSheet extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(nullable = false)
    private LocalDate dueDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @OneToMany(mappedBy = "orderSheet", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails = new ArrayList<>();

    @Builder
    public OrderSheet(Status status, LocalDate dueDate, User user, Client client) {
        this.status = status;
        this.dueDate = dueDate;
        this.user = user;
        this.client = client;
    }
}
