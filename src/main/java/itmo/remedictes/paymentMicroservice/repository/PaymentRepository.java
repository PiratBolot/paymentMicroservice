package itmo.remedictes.paymentMicroservice.repository;

import itmo.remedictes.paymentMicroservice.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Payment findByOrderId(Long orderId);
}
