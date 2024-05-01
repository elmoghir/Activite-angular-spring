package springang.springangular.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springang.springangular.entities.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,String> {
    Student findByCode(String code);
    List<Student> findByProgramCode(String programCode);
}
