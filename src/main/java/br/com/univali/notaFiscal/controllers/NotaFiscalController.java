package br.com.univali.notaFiscal.controllers;

import java.io.File;
import java.io.FileInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.net.HttpHeaders;

import br.com.univali.notaFiscal.dto.NotaFiscalDTO;
import br.com.univali.notaFiscal.services.NotaFiscalService;
import br.com.univali.notaFiscal.utils.PdfGenerator;

@RestController
public class NotaFiscalController {

	@Autowired
	NotaFiscalService notaFiscalService;

	@Autowired
	PdfGenerator pdfGenerator;

	@RequestMapping(value = "/notafiscal/cassandra/inserir", method = RequestMethod.GET)
	@ResponseBody
	public String popularDadosCassandra() {
		return notaFiscalService.getNotasFiscaisMysql();
	}

	@RequestMapping(value = "/notafiscal/cassandra/exportPDF/{number}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<InputStreamResource> exportNotafiscal(@PathVariable("number") Integer number) throws Exception {
		NotaFiscalDTO notaFiscalDTO = notaFiscalService.getByNumber(number);
		
		MediaType mediaType = MediaType.parseMediaType("application/pdf");
		File file = pdfGenerator.createPdf("notafiscal/export", notaFiscalDTO, number.toString());
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
 
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                .contentType(mediaType)
                .contentLength(file.length())
                .body(resource);
	}

}
