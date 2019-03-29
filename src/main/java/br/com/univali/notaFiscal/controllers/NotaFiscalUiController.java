package br.com.univali.notaFiscal.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.univali.notaFiscal.domain.NotaFiscal;
import br.com.univali.notaFiscal.services.NotaFiscalService;

@Controller
public class NotaFiscalUiController {

	@Autowired
	NotaFiscalService notaFiscalService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getPost(Model model) {
		try {
			List<NotaFiscal> notaFiscal = notaFiscalService.listAll();

			model.addAttribute("listNotaFiscal", notaFiscal);

			return "/notafiscal/list";
		} catch (Exception e) {
			throw new RuntimeException("Falha ao consultar uma nota");
		}
	}
}
