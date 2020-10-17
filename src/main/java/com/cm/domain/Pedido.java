package com.cm.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.DynamicUpdate;

import com.cm.domain.enums.StatusPedido;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.sun.istack.NotNull;

@Entity
@JsonInclude(Include.NON_NULL)
@DynamicUpdate
public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@NotNull
	private String codigo;
	private Integer status;
	private BigDecimal subTotal;
	private BigDecimal taxaFrete;
	private BigDecimal ValorTotal;
	private Date dataCriacao;
	private Date dataConfirmacao;
	private Date dataEntrega;
	private Date dataCancelamento;
	
	
	
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	private List<ItemPedido> itens = new ArrayList<>();
	
	
	

	public Pedido() {}

	public Pedido(Integer id, String codigo, StatusPedido status, BigDecimal subTotal, BigDecimal taxaFrete,
			BigDecimal valorTotal, Date dataCriacao, Date dataConfirmacao, Date dataEntrega, Date dataCancelamento) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.status = status.getCod();
		this.subTotal = subTotal;
		this.taxaFrete = taxaFrete;
		ValorTotal = valorTotal;
		this.dataCriacao = dataCriacao;
		this.dataConfirmacao = dataConfirmacao;
		this.dataEntrega = dataEntrega;
		this.dataCancelamento = dataCancelamento;
	}
	

	public Pedido(String codigo, StatusPedido status, BigDecimal taxaFrete, Date dataCriacao) {
		super();
		this.codigo = codigo;
		this.status = status.getCod();
		this.taxaFrete = taxaFrete;
		this.dataCriacao = dataCriacao;
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

	public StatusPedido getStatus() {
		return StatusPedido.toEnum(status);
	}

	public void setStatus(StatusPedido status) {
		this.status = status.getCod();
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
	public List<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	

}
