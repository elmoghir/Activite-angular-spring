package springang.springangular.dtos;

import jakarta.persistence.*;
import lombok.*;
import springang.springangular.entities.PaymentStatus;
import springang.springangular.entities.PaymentType;
import springang.springangular.entities.Student;

import java.time.LocalDate;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString @Builder
public class PaymentDTO {
    private long id;
    private LocalDate date;
    private double amount;
    private PaymentType type;
    private PaymentStatus status;
}
