package com.gvendas.gestaovendas.servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

	public Produto salvar(Long codigoCategoria, Produto produto) {
		validarCategoriadoProdutoExiste(codigoCategoria);
		validarProdutoDuplicado(produto);
		return produtoRepository.save(produto);
	}

	public Produto atualizar(Long codigoCategoria, Long codigoProduto, Produto produto) {
		Produto produtoSalvar = validarProdutoExiste(codigoProduto, codigoCategoria);
		validarCategoriadoProdutoExiste(codigoCategoria);
		validarProdutoDuplicado(produto);
		BeanUtils.copyProperties(produto, produtoSalvar, "codigo");
		return produtoRepository.save(produtoSalvar);
	}
	
	public void deletar(Long codigoCategoria, Long codigoProduto) {
		Produto produto = validarProdutoExiste(codigoProduto, codigoCategoria);
		produtoRepository.delete(produto);
	}

	private Produto validarProdutoExiste(Long codigoProduto, Long codigoCategoria) {
		Optional<Produto> produto = buscarPorCodigo(codigoProduto, codigoCategoria);

		if (!produto.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}

		return produto.get();
	}

	private void validarProdutoDuplicado(Produto produto) {
		Optional<Produto> produtoPorDescricao = produtoRepository.findByCategoriaCodigoAndDescricao(produto.getCategoria().getCodigo(), produto.getDescricao());
		if (produtoPorDescricao.isPresent() && produtoPorDescricao.get().getCodigo() != produto.getCodigo()) {
			throw new RegraNegocioException(
					String.format("O produto %s j?? esta cadastrado", produto.getDescricao()));
		}
	}

	private void validarCategoriadoProdutoExiste(Long codigoCategoria) {
		if (codigoCategoria == null) {
			throw new RegraNegocioException("A categoria n??o pode ser nula");
		}

		if (!categoriaService.buscaPorCodigo(codigoCategoria).isPresent()) {
			throw new RegraNegocioException(
					String.format("A categoria %s informada n??o existe no cadastro", codigoCategoria));
		}
	}
}
