package com.gvendas.gestaovendas.servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gvendas.gestaovendas.entities.Produto;
import com.gvendas.gestaovendas.excecao.RegraNegocioException;
import com.gvendas.gestaovendas.repositorio.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaService categoriaService;

	public List<Produto> listarTodos(Long codigoCategoria) {
		return produtoRepository.findByCategoriaCodigo(codigoCategoria);
	}

	public Optional<Produto> buscarPorCodigo(Long codigo, Long codigoCategoria) {
		return produtoRepository.buscarPorCodigo(codigo, codigoCategoria);
	}

	public Produto salvar(Produto produto) {
		validarCategoriadoProdutoExiste(produto.getCategoria().getCodigo());
		validarProdutoDuplicado(produto);
		return produtoRepository.save(produto);
	}

	private void validarProdutoDuplicado(Produto produto) {
		if (produtoRepository
				.findByCategoriaCodigoAndDescricao(produto.getCategoria().getCodigo(), produto.getDescricao())
				.isPresent()) {
			throw new RegraNegocioException(
					String.format("O produto %s já esta cadastrado", produto.getDescricao()));
		}
	}

	private void validarCategoriadoProdutoExiste(Long codigoCategoria) {
		if (codigoCategoria == null) {
			throw new RegraNegocioException("A categoria não pode ser nula");
		}

		if (!categoriaService.buscaPorCodigo(codigoCategoria).isPresent()) {
			throw new RegraNegocioException(
					String.format("A categoria %s informada não existe no cadastro", codigoCategoria));
		}
	}
}
