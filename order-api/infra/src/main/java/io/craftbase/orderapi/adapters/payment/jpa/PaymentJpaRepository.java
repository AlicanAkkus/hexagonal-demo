package io.craftbase.orderapi.adapters.payment.jpa;

import io.craftbase.orderapi.adapters.payment.jpa.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentJpaRepository extends JpaRepository<PaymentEntity, Long> {
}