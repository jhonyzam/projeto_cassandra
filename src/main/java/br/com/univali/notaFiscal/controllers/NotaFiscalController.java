package br.com.univali.notaFiscal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.univali.notaFiscal.services.NotaFiscalService;

@RestController
public class NotaFiscalController {

	@Autowired
	NotaFiscalService notaFiscalService;

	@RequestMapping(value = "/notafiscal/cassandra/inserir", method = RequestMethod.GET)
	@ResponseBody
	public String popularDadosCassandra() {
		return notaFiscalService.getNotasFiscaisMysql();
	}

}
