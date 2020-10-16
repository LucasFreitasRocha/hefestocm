package com.cm.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.domain.Pedido;
import com.cm.domain.enums.StatusPedido;
import com.cm.dto.PedidoNewDTO;
import com.cm.repositories.PedidoRepository;
import com.cm.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;

	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Pedido não encontrado com esse id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

	public Pedido create(PedidoNewDTO objDTO) {
		Pedido obj = mountPedidoFromPedidoDTO(objDTO);
		obj.setId(null);
		return repo.save(obj);
	}

	public Pedido update(Pedido obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	
	

	public void delete(Integer id) {
		find(id);
		repo.deleteById(id);
	}
	private Pedido mountPedidoFromPedidoDTO(PedidoNewDTO objDTO) {
		Pedido obj = new Pedido(objDTO.getCodigo(), StatusPedido.toEnum(objDTO.getStatus()), objDTO.getTaxaFrete(), objDTO.getDataCriacao() );
		
		
		return obj;
	}
}
