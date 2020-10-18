package com.cm.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

public class pedidoItemNewDTO {
	
	@NotNull(message = "campo obrigatorio")
	private Integer quantidade;
	@NotNull(message = "campo obrigatorio")
	private BigDecimal precoUnitario;
	private String observacao;
	private PedidoDTO pedido;
	
	@NotNull(message = "campo obrigatorio")
	private Integer id_pedido;
	
	

	public pedidoItemNewDTO() {
		
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(BigDecimal precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public PedidoDTO getPedido() {
		return pedido;
	}

	public void setPedido(PedidoDTO pedido) {
		this.pedido = pedido;
	}

	public Integer getId_pedido() {
		return id_pedido;
	}

	public void setId_pedido(Integer id_pedido) {
		this.id_pedido = id_pedido;
	}
	

	
	
}
