package springang.springangular.web;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springang.springangular.entities.Payment;
import springang.springangular.entities.PaymentStatus;
import springang.springangular.entities.PaymentType;
import springang.springangular.entities.Student;
import springang.springangular.repository.PaymentRepository;
import springang.springangular.repository.StudentRepository;
import springang.springangular.services.PaymentService;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("*")
public class PaymentRestController {
    private StudentRepository studentRepository;
    private PaymentRepository paymentRepository;

    private PaymentService paymentService;
    public PaymentRestController(StudentRepository studentRepository, PaymentRepository paymentRepository, PaymentService paymentService)
    {
        this.studentRepository = studentRepository;
        this.paymentRepository = paymentRepository;
        this.paymentService = paymentService;
    }

    @GetMapping(path = "/payments")
    public List<Payment> allPayments()
    {
        return paymentRepository.findAll();
    }

    @GetMapping(path = "/student/{code}/payments")
    public List<Payment> getPaymentByStudent(@PathVariable String code)
    {
        return paymentRepository.findByStudentCode(code);
    }

    @GetMapping(path = "/payments/byStatus")
    public List<Payment> getPaymentByStatus(@RequestParam PaymentStatus status)
    {
        return paymentRepository.findByStatus(status);
    }

    @GetMapping(path = "/payments/byType")
    public List<Payment> getPaymentByType(@RequestParam PaymentType type)
    {
        return paymentRepository.findByType(type);
    }

    @GetMapping(path = "/payments/{id}")
    public Payment getPaymentById(@PathVariable Long id)
    {
        return paymentRepository.findById(id).get();
    }

    @GetMapping(path = "/students")
    public List<Student> allStudents()
    {
        return studentRepository.findAll();
    }

    @GetMapping(path = "/students/{code}")
    public Student getStudentByCode(@PathVariable  String code)
    {
        return studentRepository.findByCode(code);
    }

    @GetMapping(path = "/studentByProgramId")
    public List<Student> getStudentByProgramId(@RequestParam String programId)
    {
        return studentRepository.findByProgramCode(programId);
    }

    @PutMapping(path = "/payments/{id}")
    public Payment updatePaymentStatus(@RequestParam PaymentStatus status,@PathVariable Long id)
    {
        return paymentService.updatePaymentStatus(status, id);
    }
    @PostMapping(path = "/payments", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Payment save(@RequestParam MultipartFile file, LocalDate date, double amount, PaymentType type, String studentCode) throws IOException {
        return paymentService.save(file, date, amount, type, studentCode);
    }

    @GetMapping(path = "/paymentFile/{paymentId}", produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] getPaymentsFile(@PathVariable Long paymentId) throws IOException {
        return paymentService.getPaymentsFile(paymentId);
    }

}
