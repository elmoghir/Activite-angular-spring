package springang.springangular;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springang.springangular.entities.Payment;
import springang.springangular.entities.PaymentStatus;
import springang.springangular.entities.PaymentType;
import springang.springangular.entities.Student;
import springang.springangular.repository.PaymentRepository;
import springang.springangular.repository.StudentRepository;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class SpringAngularApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAngularApplication.class, args);
	}
    @Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository,
										PaymentRepository paymentRepository)
	{
		return args-> {
			studentRepository.save(Student.builder().id(UUID.randomUUID().toString()).firstName("Abdessamad").code("112233").programCode("SDIA").build());
			studentRepository.save(Student.builder().id(UUID.randomUUID().toString()).firstName("Youssef").code("113233").programCode("SDIA").build());
			studentRepository.save(Student.builder().id(UUID.randomUUID().toString()).firstName("Hamza").code("114233").programCode("SDIA").build());
			studentRepository.save(Student.builder().id(UUID.randomUUID().toString()).firstName("Driss").code("115233").programCode("SDIA").build());
			studentRepository.save(Student.builder().id(UUID.randomUUID().toString()).firstName("Amine").code("116233").programCode("SDIA").build());
			PaymentType[] paymentTypes = PaymentType.values();
			Random random = new Random();
			studentRepository.findAll().forEach(st->{
				for (int i = 0; i < 20; i++) {
					int index = random.nextInt(paymentTypes.length);
					Payment payment = Payment.builder()
							.amount(1000+(int)(Math.random()*2000))
							.type(paymentTypes[index])
							.status(PaymentStatus.CREATED)
							.date(LocalDate.now())
							.student(st)
							.build();
					paymentRepository.save(payment);
				}
			});


		};
	}
}
