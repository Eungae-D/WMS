package com.wms.domain.sell.entity;

import com.wms.domain.orderSheet.entity.OrderSheet;
import com.wms.domain.sellDetail.entity.SellDetail;
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
public class Sell extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderSheet_id", nullable = false)
    private OrderSheet orderSheet;

    @Column(nullable = false)
    private LocalDate dueDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "sell", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SellDetail> sellDetails = new ArrayList<>();

    @Builder
    public Sell(User user, OrderSheet orderSheet, LocalDate dueDate, Status status) {
        this.user = user;
        this.orderSheet = orderSheet;
        this.dueDate = dueDate;
        this.status = status;
    }

}
