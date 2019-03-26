package br.com.univali.notaFiscal.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.com.univali.notaFiscal.dto.NotaFiscalDTO;
import java.lang.reflect.Type;

@Service
public class NotaFiscalService {

	public void getNotasFiscais() throws JsonParseException, JsonMappingException, IOException {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/invoice_system_univali");
		ds.setUsername("root");
		ds.setPassword("root");

		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);

		String sql = String.format("select * from customer");

		List<Map<String, Object>> listMap = jdbcTemplate.queryForList(sql);

		Gson gson = new Gson();
		String json = gson.toJson(listMap);

		Type listType = new TypeToken<ArrayList<NotaFiscalDTO>>() {
		}.getType();
		List<NotaFiscalDTO> listNotaFiscalDTO = gson.fromJson(json, listType);

		System.out.println(listNotaFiscalDTO);
	}

}
