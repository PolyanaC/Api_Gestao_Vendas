package com.gvendas.gestaovendas.servico;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gvendas.gestaovendas.entities.Produto;
import com.gvendas.gestaovendas.repositorio.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	public List<Produto> listarTodos(Long codigoCategoria){
		return produtoRepository.findByCategoriaCodigo(codigoCategoria);
	}
	
	public Optional<Produto> buscarPorCodigo(Long codigo, Long codigoCategoria){
		return produtoRepository.buscarPorCodigo(codigo, codigoCategoria);
	}
}
