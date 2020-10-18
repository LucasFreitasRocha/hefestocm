package com.cm.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.cm.domain.ItemPedido;
import com.fasterxml.jackson.annotation.JsonProperty;
public class PedidoNewDTO  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "campo obrigatorio")
	private String codigo;
	@NotNull(message = "campo obrigatorio")
	private Integer status;
	@NotNull(message = "campo obrigatorio")
	private BigDecimal taxaFrete;
	@NotNull(message = "campo obrigatorio")
	private List<ItemPedido> itemsNewDTO = new ArrayList<>();
	
	
	public PedidoNewDTO() {}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}



	public BigDecimal getTaxaFrete() {
		return taxaFrete;
	}

	public void setTaxaFrete(BigDecimal taxaFrete) {
		this.taxaFrete = taxaFrete;
	}


	

	public List<ItemPedido> getItemsNewDTO() {
		return itemsNewDTO;
	}
	
	 @JsonProperty("itens")
	public void setItemsNewDTO(List<ItemPedido> itemsNewDTO) {
		this.itemsNewDTO = itemsNewDTO;
	}

	
	
	

}
