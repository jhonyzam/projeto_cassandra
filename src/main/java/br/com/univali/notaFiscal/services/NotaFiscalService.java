package br.com.univali.notaFiscal.services;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.com.univali.notaFiscal.domain.NotaFiscal;
import br.com.univali.notaFiscal.dto.NotaFiscalDTO;
import br.com.univali.notaFiscal.repositories.NotaFiscalRepository;

@Service
public class NotaFiscalService {

	@Autowired
	private NotaFiscalRepository notaFiscalRepository;

	public String getNotasFiscaisMysql() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/invoice_system_univali");
		ds.setUsername("root");
		ds.setPassword("root");

		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);

		String sql = String.format("SELECT \r\n" 
				+ "	c.name, \r\n" + "	c.address,\r\n" 
				+ "	i.number,\r\n"
				+ "	s.service_description,\r\n" 
				+ "	ii.quantity, ii.unit_value,\r\n" 
				+ "	r.name As recurso,\r\n"
				+ "	rq.qualificatin_name as funcao,\r\n" 
				+ "	ii.tax_percent, \r\n" 
				+ "	ii.discount_percent, \r\n"
				+ "	ii.subtotal,\r\n" 
				+ "	i.value\r\n" 
				+ "FROM\r\n"
				+ "customer c\r\n"
				+ "inner join invoice i on c.id_customer = i.customer_id\r\n"
				+ "inner join invoice_item ii on ii.invoice_id = i.number\r\n"
				+ "inner join service s on s.service_id = ii.service_id\r\n"
				+ "inner join resource r on r.id_resource = ii.resource_id\r\n"
				+ "inner join resource_qualification_assignement rqa on rqa.resource_id = r.id_resource\r\n"
				+ "inner join resource_qualification rq on rq.id_resource_qualification = rqa.qualification_id\r\n"
				+ "order by\r\n" + "	c.name, i.number ");

		List<Map<String, Object>> listMap = jdbcTemplate.queryForList(sql);

		Gson gson = new Gson();
		String json = gson.toJson(listMap);

		Type listType = new TypeToken<ArrayList<NotaFiscalDTO>>() {
		}.getType();
		List<NotaFiscalDTO> listNotaFiscalDTO = gson.fromJson(json, listType);

		// Limpar tabela
		notaFiscalRepository.deleteAll();
		for (NotaFiscalDTO notaFiscalDTO : listNotaFiscalDTO) {
			this.save(notaFiscalDTO);
		}

		return "Tabela notafiscal populada com sucesso";
	}

	public List<NotaFiscal> listAll() {
		List<NotaFiscal> notasFiscais = new ArrayList<>();
		notaFiscalRepository.findAll().forEach(notasFiscais::add); // fun with Java 8
		return notasFiscais;
	}

	public NotaFiscalDTO getById(UUID id) {
		NotaFiscalDTO notaFiscalDTO = this
				.converteNotaFiscalNotafiscalDTO(notaFiscalRepository.findById(id).orElse(null));
		return notaFiscalDTO;
	}

	public NotaFiscal save(NotaFiscalDTO notaFiscalDTO) {
		NotaFiscal notafiscal = this.converteNotaFiscalDTONotafiscal(notaFiscalDTO);
		return notaFiscalRepository.save(notafiscal);
	}

	public void delete(UUID id) {
		notaFiscalRepository.deleteById(id);
	}

	public NotaFiscal converteNotaFiscalDTONotafiscal(NotaFiscalDTO notaFiscalDTO) {
		NotaFiscal notaFiscal = new NotaFiscal();

		notaFiscal.setId(UUID.randomUUID());
		notaFiscal.setName(notaFiscalDTO.getName());
		notaFiscal.setAddress(notaFiscalDTO.getAddress());
		notaFiscal.setNumber(notaFiscalDTO.getNumber());
		notaFiscal.setService_description(notaFiscalDTO.getService_description());
		notaFiscal.setQuantity(notaFiscalDTO.getQuantity());
		notaFiscal.setUnit_value(notaFiscalDTO.getUnit_value());
		notaFiscal.setRecurso(notaFiscalDTO.getRecurso());
		notaFiscal.setFuncao(notaFiscalDTO.getFuncao());
		notaFiscal.setTax_percent(notaFiscalDTO.getTax_percent());
		notaFiscal.setDiscount_percent(notaFiscalDTO.getDiscount_percent());
		notaFiscal.setSubtotal(notaFiscalDTO.getSubtotal());
		notaFiscal.setValue(notaFiscalDTO.getValue());

		return notaFiscal;
	}

	public NotaFiscalDTO converteNotaFiscalNotafiscalDTO(NotaFiscal notaFiscal) {
		NotaFiscalDTO notaFiscalDTO = new NotaFiscalDTO();

		notaFiscalDTO.setName(notaFiscal.getName());
		notaFiscalDTO.setAddress(notaFiscal.getAddress());
		notaFiscalDTO.setNumber(notaFiscal.getNumber());
		notaFiscalDTO.setService_description(notaFiscal.getService_description());
		notaFiscalDTO.setQuantity(notaFiscal.getQuantity());
		notaFiscalDTO.setUnit_value(notaFiscal.getUnit_value());
		notaFiscalDTO.setRecurso(notaFiscal.getRecurso());
		notaFiscalDTO.setFuncao(notaFiscal.getFuncao());
		notaFiscalDTO.setTax_percent(notaFiscal.getTax_percent());
		notaFiscalDTO.setDiscount_percent(notaFiscal.getDiscount_percent());
		notaFiscalDTO.setSubtotal(notaFiscal.getSubtotal());
		notaFiscalDTO.setValue(notaFiscal.getValue());

		return notaFiscalDTO;
	}
}
