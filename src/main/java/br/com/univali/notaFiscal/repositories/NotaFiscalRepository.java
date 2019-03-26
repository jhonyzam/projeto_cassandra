package br.com.univali.notaFiscal.repositories;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.univali.notaFiscal.domain.NotaFiscal;

@Repository
public interface NotaFiscalRepository extends CrudRepository<NotaFiscal, UUID> {

}
