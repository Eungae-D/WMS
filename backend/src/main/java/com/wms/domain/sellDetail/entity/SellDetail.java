package com.wms.domain.sellDetail.entity;

import com.wms.domain.item.entity.Item;
import com.wms.domain.orderSheet.entity.OrderSheet;
import com.wms.domain.sell.entity.Sell;
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
public class SellDetail extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long amount;

    @Column(nullable = false)
    private Long sellPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sell_id", nullable = false)
    private Sell sell;


    @Builder
    public SellDetail(Long amount,Long sellPrice, Item item, Sell sell) {
        this.amount = amount;
        this.sellPrice = sellPrice;
        this.item = item;
        this.sell = sell;
    }

}
