package springang.springangular.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springang.springangular.entities.PaymentStatus;
import springang.springangular.entities.Payment;
import springang.springangular.entities.PaymentType;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
    List<Payment> findByStudentCode(String code);
    List<Payment> findByStatus(PaymentStatus status);
    List<Payment> findByType(PaymentType type);
}
