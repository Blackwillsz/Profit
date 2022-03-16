package br.com.profit.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.profit.models.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, UUID> {

}
