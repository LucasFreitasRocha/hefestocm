package com.cm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.domain.ItemPedido;
import com.cm.domain.Pedido;
import com.cm.dto.ItemPedidoDTO;
import com.cm.dto.PedidoDTO;
import com.cm.dto.pedidoItemNewDTO;
import com.cm.repositories.ItemPedidoRepository;
import com.cm.services.exceptions.ObjectNotFoundException;
@Service
public class ItemPedidoService {
	@Autowired
	private ItemPedidoRepository repo;
	@Autowired
	private PedidoService pedidoService;
	
	

	public ItemPedido find(Integer id) {
		Optional<ItemPedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"item do pedido não encontrado com esse id: " + id + ", Tipo: " + ItemPedido.class.getName()));
	}
	
	public void create(pedidoItemNewDTO obj) {
		Pedido p = pedidoService.find(obj.getId_pedido());
		ItemPedido newObj = new ItemPedido(obj.getQuantidade(), obj.getPrecoUnitario(), obj.getObservacao(), p);
		newObj.setPrecoTotal(newObj.calcularPrecoTotal(obj.getQuantidade(), obj.getPrecoUnitario()));
		List<ItemPedido> itens = p.getItens();
		itens.add(newObj);
		PedidoDTO pedidoDto = new PedidoDTO();
		pedidoDto.setId(p.getId());
		pedidoDto.setItens(itens);
		pedidoService.update(pedidoDto);
	}

	public void update(ItemPedidoDTO itemPedidoDTO) {
		ItemPedido ip = find(itemPedidoDTO.getId());
		 ip = repo.save(updateData(ip, itemPedidoDTO));
		 
		updatePedido(ip.getPedido());
	}
	public ItemPedido updateData( ItemPedido ip, ItemPedidoDTO itemPedidoDTO) {
		
		if(itemPedidoDTO.getObservacao() != null) {
			ip.setObservacao(itemPedidoDTO.getObservacao());
		}
		if(itemPedidoDTO.getPrecoUnitario() != null) {
			ip.setPrecoUnitario(itemPedidoDTO.getPrecoUnitario());
			ip.setPrecoTotal(ip.calcularPrecoTotal(ip.getQuantidade(), itemPedidoDTO.getPrecoUnitario()));
		}
		if(itemPedidoDTO.getQuantidade() != null) {
			ip.setQuantidade(itemPedidoDTO.getQuantidade());
			ip.setPrecoTotal(ip.calcularPrecoTotal(itemPedidoDTO.getQuantidade(), ip.getPrecoUnitario()));
		}
		return ip;
	}

	public void delete(Integer id) {
		ItemPedido ip = find(id);
		repo.deleteById(id);
		updatePedido(ip.getPedido());
		
	}
	
	public void updatePedido(Pedido p) {
		PedidoDTO pedidoDto = new PedidoDTO();
		pedidoDto.setId(p.getId());
		pedidoDto.setItens(p.getItens());
		pedidoService.update(pedidoDto);
	}

}
