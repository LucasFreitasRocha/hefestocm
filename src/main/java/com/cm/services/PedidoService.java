package com.cm.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cm.domain.ItemPedido;
import com.cm.domain.Pedido;
import com.cm.domain.enums.StatusPedido;
import com.cm.dto.PedidoDTO;
import com.cm.dto.PedidoNewDTO;
import com.cm.repositories.PedidoRepository;
import com.cm.services.exceptions.ObjectNotFoundException;
import com.cm.utils.Calcular;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	 

	
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Pedido não encontrado com esse id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
	@Transactional
	public Pedido create(PedidoNewDTO objDTO) {
		Pedido obj = mountPedidoFromPedidoDTO(objDTO);
		obj.setId(null);
		
		return repo.save(obj);
	}
	public Pedido update(PedidoDTO newObj)  {
		Pedido oldObjs = find(newObj.getId());
		
		return repo.save(updateData(oldObjs,newObj)) ;
	}
	
	

	private Pedido updateData( Pedido obj, PedidoDTO newObj)  {
		if(newObj.getCodigo() !=null) {
			obj.setCodigo(newObj.getCodigo());
		}
		if(newObj.getTaxaFrete() != null) {
			obj.setTaxaFrete(newObj.getTaxaFrete() );
			obj.setValorTotal(Calcular.calcularTotalPedido(newObj.getTaxaFrete(), obj.getSubTotal()));
		}
		return obj;
	}
	public void delete(Integer id) {
		find(id);
		repo.deleteById(id);
	}
	public Pedido mountPedidoFromPedidoDTO(PedidoNewDTO objDTO) {
		Date date = new Date(); 
		Pedido obj = new Pedido(objDTO.getCodigo(), StatusPedido.toEnum(objDTO.getStatus()), objDTO.getTaxaFrete(), date );
		BigDecimal precoTotal = new BigDecimal(0);
		obj.setItens(objDTO.getItemsNewDTO());
		List<ItemPedido> itens = new ArrayList<>();
		for(ItemPedido objItem : objDTO.getItemsNewDTO()) {
			BigDecimal precoTotalItem = new BigDecimal(objItem.getQuantidade());
			precoTotalItem = precoTotalItem.multiply(objItem.getPrecoUnitario());
			objItem.setPrecoTotal(precoTotalItem);
			objItem.setPedido(obj);
			itens.add(objItem);
			precoTotal = precoTotal.add(precoTotalItem);
			
		}
		
		obj.setSubTotal(precoTotal);
		obj.setValorTotal(precoTotal.add(obj.getTaxaFrete()));
		obj.setItens(itens);
		return obj;
	}
}
