package br.com.univali.notaFiscal.dto;

public class NotaFiscalItemDTO {

	private String service_description;
	private Long quantity;
	private Long unit_value;
	private String recurso;
	private String funcao;
	private Double tax_percent;
	private Double discount_percent;
	private Double subtotal;

	public String getService_description() {
		return service_description;
	}

	public void setService_description(String service_description) {
		this.service_description = service_description;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Long getUnit_value() {
		return unit_value;
	}

	public void setUnit_value(Long unit_value) {
		this.unit_value = unit_value;
	}

	public String getRecurso() {
		return recurso;
	}

	public void setRecurso(String recurso) {
		this.recurso = recurso;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public Double getTax_percent() {
		return tax_percent;
	}

	public void setTax_percent(Double tax_percent) {
		this.tax_percent = tax_percent;
	}

	public Double getDiscount_percent() {
		return discount_percent;
	}

	public void setDiscount_percent(Double discount_percent) {
		this.discount_percent = discount_percent;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

}
