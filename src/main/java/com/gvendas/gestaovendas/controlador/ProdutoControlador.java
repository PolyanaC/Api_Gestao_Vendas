package com.gvendas.gestaovendas.controlador;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gvendas.gestaovendas.entities.Produto;
import com.gvendas.gestaovendas.servico.ProdutoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Produto")
@RestController
@RequestMapping("/categoria{codigoCategoria}/produto")
public class ProdutoControlador {

	@Autowired
	private ProdutoService produtoService;

	@ApiOperation(value = "Listar", nickname = "listarTodos")
	@GetMapping
	public List<Produto> listarTodos(@PathVariable long codigoCategoria) {
		return produtoService.listarTodos(codigoCategoria);
	}

	@ApiOperation(value = "ListarPorCodigo", nickname = "buscarPorCodigo")
	@GetMapping("/{codigo}")
	public ResponseEntity<Optional<Produto>> buscarPorCodigo(@PathVariable Long codigoCategoria,
			@PathVariable Long codigo) {
		Optional<Produto> produto = produtoService.buscarPorCodigo(codigo, codigoCategoria);
		return produto.isPresent() ? ResponseEntity.ok(produto) : ResponseEntity.notFound().build();

	}

	@ApiOperation(value = "Salvar", nickname = "salvaProduto")
	@PostMapping
	public ResponseEntity<Produto> salvar(@PathVariable Long codigoCategoria, @Valid @RequestBody Produto produto) {
		Produto produtoSalvo = produtoService.salvar(codigoCategoria, produto);
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
	}

	@ApiOperation(value = "Atualizar", nickname = "atualizarProduto")
	@PutMapping("/{codigoProduto}")
	public ResponseEntity<Produto> atualizar(@PathVariable Long codigoCategoria, @PathVariable Long codigoProduto,
			@Valid @RequestBody Produto produto) {
		Produto produtoAtualizado = produtoService.atualizar(codigoCategoria, codigoProduto, produto);
		return ResponseEntity.ok(produtoAtualizado);
	}
	
	@ApiOperation(value = "Deletar", nickname = "deletarProduto")
	@DeleteMapping("/{codigoProduto}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long codigoCategoria, @PathVariable Long codigoProduto) {
		produtoService.deletar(codigoCategoria, codigoProduto);
	}

}
