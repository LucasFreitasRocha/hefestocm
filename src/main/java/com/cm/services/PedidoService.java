package com.cm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.domain.Pedido;
import com.cm.repositories.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	public Pedido create(Pedido obj) {
		obj.setId(null);
		return repo.save(obj);
	}
}
