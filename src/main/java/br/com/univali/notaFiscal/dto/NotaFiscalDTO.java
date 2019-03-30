package br.com.univali.notaFiscal.dto;

import java.util.List;

public class NotaFiscalDTO {

	private String name;
	private String address;
	private Integer number;
	private Double value;

	List<NotaFiscalItemDTO> items;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public List<NotaFiscalItemDTO> getItems() {
		return items;
	}

	public void setItems(List<NotaFiscalItemDTO> items) {
		this.items = items;
	}

}