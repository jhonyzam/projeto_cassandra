package br.com.univali.notaFiscal.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.univali.notaFiscal.dto.NotaFiscalDTO;
import br.com.univali.notaFiscal.services.NotaFiscalService;

@Controller
public class DanfeControler {

	@Autowired
	NotaFiscalService notaFiscalService;

	@RequestMapping(value = "/notafiscal/cassandra/danfe/{id}", method = RequestMethod.GET)
	public String getPost(@PathVariable String id, Model model) {
		try {
			UUID uid = UUID.fromString(id);
			NotaFiscalDTO notaFiscalDTO = notaFiscalService.getById(uid);

			model.addAttribute("notafiscal", notaFiscalDTO);

			return "notafiscal/export";
		} catch (Exception e) {
			throw new RuntimeException("Falha ao consultar uma nota");
		}
	}
}
