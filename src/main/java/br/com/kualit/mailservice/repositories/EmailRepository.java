package br.com.kualit.mailservice.repositories;

import br.com.kualit.mailservice.models.EmailModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<EmailModel, Long> {
}
