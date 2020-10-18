package com.cm.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.cm.domain.Pedido;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class OnlyPedidoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	

	private Integer id;
	private String codigo;
	private Integer status;
	private BigDecimal subTotal;
	private BigDecimal taxaFrete;
	private BigDecimal ValorTotal;
	private Date dataCriacao;
	private Date dataConfirmacao;
	private Date dataEntrega;
	private Date dataCancelamento;
	
	public OnlyPedidoDTO () {}

	public OnlyPedidoDTO(Pedido p) {
		super();
		this.id = p.getId();
		this.codigo = p.getCodigo();
		this.status = p.getStatus().getCod();
		this.subTotal = p.getSubTotal();
		this.taxaFrete = p.getTaxaFrete();
		this.ValorTotal = p.getValorTotal();
		this.dataCriacao = p.getDataCriacao();
		this.dataConfirmacao = p.getDataConfirmacao();
		this.dataEntrega = p.getDataEntrega();
		this.dataCancelamento = p.getDataCancelamento();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public BigDecimal getTaxaFrete() {
		return taxaFrete;
	}

	public void setTaxaFrete(BigDecimal taxaFrete) {
		this.taxaFrete = taxaFrete;
	}

	public BigDecimal getValorTotal() {
		return ValorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		ValorTotal = valorTotal;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataConfirmacao() {
		return dataConfirmacao;
	}

	public void setDataConfirmacao(Date dataConfirmacao) {
		this.dataConfirmacao = dataConfirmacao;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public Date getDataCancelamento() {
		return dataCancelamento;
	}

	public void setDataCancelamento(Date dataCancelamento) {
		this.dataCancelamento = dataCancelamento;
	}
	
	
	
	
}
	
	
