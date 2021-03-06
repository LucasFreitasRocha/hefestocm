package com.cm.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class ItemPedido implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Integer quantidade;
	private BigDecimal precoUnitario;
	private BigDecimal precoTotal;
	private String observacao;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="id_pedido")
	private Pedido pedido;
	
	@Column(name="id_pedido", updatable=false, insertable=false) 
    private Long id_pedido;

	
	public ItemPedido() {}

	public ItemPedido(Integer id, Integer quantidade, BigDecimal precoUnitario, BigDecimal precoTotal,
			String observacao, Pedido pedido) {
		super();
		this.id = id;
		this.quantidade = quantidade;
		this.precoUnitario = precoUnitario;
		this.precoTotal = precoTotal;
		this.observacao = observacao;
		this.pedido = pedido;
	}
	

	public ItemPedido(Integer quantidade, BigDecimal precoUnitario, String observacao, Pedido pedido) {
		super();
		this.quantidade = quantidade;
		this.precoUnitario = precoUnitario;
		this.observacao = observacao;
		this.pedido = pedido;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public BigDecimal getPrecoTotal() {
		return precoTotal;
	}

	public void setPrecoTotal(BigDecimal precoTotal) {
		this.precoTotal = precoTotal;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	

	public Long getid_pedido() {
		return id_pedido;
	}

	public void setid_pedido(Long idPedido) {
		this.id_pedido = idPedido;
	}
	
	public BigDecimal calcularPrecoTotal(Integer quantidade , BigDecimal precoUnitario) {
		BigDecimal precoTotal = new BigDecimal(quantidade);
		return precoTotal.multiply(precoUnitario);
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
		ItemPedido other = (ItemPedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
