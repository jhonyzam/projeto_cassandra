package br.com.univali.notaFiscal.repositories;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import br.com.univali.notaFiscal.domain.NotaFiscal;

@Repository
public interface NotaFiscalRepository extends CassandraRepository<NotaFiscal, UUID> {

}
