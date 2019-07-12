package br.com.kleber.calcard.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.kleber.calcard.dto.ClienteDTO;
import br.com.kleber.calcard.dto.PropostaResultDTO;
import br.com.kleber.calcard.entity.Cliente;
import br.com.kleber.calcard.repository.ClienteRepository;
import br.com.kleber.calcard.service.AnalisarPropostaService;

@RestController
public class ClienteController {

	private final ClienteRepository repository;
	
	private final AnalisarPropostaService analisarPropostaService;
	
	public ClienteController(ClienteRepository repository, AnalisarPropostaService analisarPropostaService) {
		this.repository = repository;
		this.analisarPropostaService = analisarPropostaService;
	}

	@PostMapping("/cliente/analisar")
	public ResponseEntity<PropostaResultDTO> clienteDTO(@RequestBody ClienteDTO clienteRequest) {
		try {
			PropostaResultDTO resultado = analisarPropostaService.analisar(clienteRequest);
			ResponseEntity<PropostaResultDTO> response = new ResponseEntity<PropostaResultDTO>(resultado, HttpStatus.OK);
			return response;
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/clientes")
	public ResponseEntity<List<Cliente>> todosClientes() {
		return new ResponseEntity<List<Cliente>>(repository.findAll(), HttpStatus.OK);
	}

	@GetMapping("/cliente/{id}")
	public ResponseEntity<Cliente> clienteById(@PathVariable Long id) {
		Optional<Cliente> findById = repository.findById(id);
		if (findById.isPresent()) {
			return new ResponseEntity<>(findById.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/cliente/consultar/{cpf}")
	public ResponseEntity<PropostaResultDTO> clienteById(@PathVariable String cpf) {
		Cliente cliente = repository.findByCpf(cpf);
		if (cliente != null) {
			return new ResponseEntity<>(new PropostaResultDTO(cliente.getResultado(), cliente.getLimite()), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@DeleteMapping("/cliente/delete/{id}")
	public void deleteCliente(@PathVariable Long id) {
		repository.deleteById(id);
	}


}
