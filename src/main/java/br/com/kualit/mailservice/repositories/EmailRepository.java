package br.com.kualit.mailservice.repositories;

import br.com.kualit.mailservice.models.EmailModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmailRepository extends JpaRepository<EmailModel, UUID> {
}
