package com.cm.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cm.domain.Pedido;
import com.cm.dto.OnlyPedidoDTO;
import com.cm.dto.PedidoDTO;
import com.cm.dto.PedidoNewDTO;
import com.cm.services.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {
	@Autowired
	private PedidoService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> Listar() {
		
		List<Pedido> list = service.index();
		List<OnlyPedidoDTO> listDto = list.stream().map(obj -> new OnlyPedidoDTO(obj)).collect(Collectors.toList());  
		return ResponseEntity.ok().body(listDto);
	}
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<OnlyPedidoDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="codigo") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Pedido> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<OnlyPedidoDTO> listDto = list.map(obj -> new OnlyPedidoDTO(obj));  
		return ResponseEntity.ok().body(listDto);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {

		Pedido obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> create(@Valid @RequestBody PedidoNewDTO objDTO) {
		Pedido obj = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/teste", method = RequestMethod.POST)
	public ResponseEntity<?> teste(@RequestBody PedidoNewDTO objDTO) {

		return ResponseEntity.ok().body(objDTO);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Pedido> update(@RequestBody PedidoDTO obj, @PathVariable Integer id) {
		obj.setId(id);
		service.update(obj);

		return ResponseEntity.noContent().build();

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
