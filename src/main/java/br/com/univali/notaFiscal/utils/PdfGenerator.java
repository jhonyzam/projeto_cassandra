package br.com.univali.notaFiscal.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import br.com.univali.notaFiscal.dto.NotaFiscalDTO;

@Component
public class PdfGenerator {

	@Autowired
	private TemplateEngine templateEngine;

	public File createPdf(String templateName, NotaFiscalDTO notaFiscalDTO, String id) throws Exception {

		Context context = new Context();

		context.setVariable("notafiscal", notaFiscalDTO);

		String renderedHtmlContent = templateEngine.process(templateName, context);

		ITextRenderer renderer = new ITextRenderer();
		renderer.setDocumentFromString(renderedHtmlContent);
		renderer.layout();

		String outputFile = "C:/pdfs/" + id + ".pdf";

		OutputStream outputStream = new FileOutputStream(outputFile);
		renderer.createPDF(outputStream);
		renderer.finishPDF();
		outputStream.close();

		return new File(outputFile);
	}

	@Bean
	public ClassLoaderTemplateResolver templateResolver() {
		ClassLoaderTemplateResolver emailTemplateResolver = new ClassLoaderTemplateResolver();
		emailTemplateResolver.setPrefix("templates/");
		emailTemplateResolver.setTemplateMode("HTML5");
		emailTemplateResolver.setSuffix(".html");
		emailTemplateResolver.setTemplateMode("XHTML");
		emailTemplateResolver.setCharacterEncoding("UTF-8");
		emailTemplateResolver.setOrder(1);
		return emailTemplateResolver;
	}
}
