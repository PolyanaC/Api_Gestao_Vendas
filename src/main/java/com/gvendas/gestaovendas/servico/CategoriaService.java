package com.gvendas.gestaovendas.servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gvendas.gestaovendas.entities.Categoria;
import com.gvendas.gestaovendas.repositorio.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
private CategoriaRepository categoriaRepository;
	
	public List<Categoria> listarTodas(){
		return categoriaRepository.findAll();
	}
	
	public Optional<Categoria> buscaPorCodigo(Long codigo){
		return categoriaRepository.findById(codigo); 
	}
	
	public Categoria salvar(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	
	public Categoria atualizar(Long codigo, Categoria categoria) {
		Categoria categoriaSalvar = validarCategoriaExiste(codigo);
		
		//Substituindo o que está no banco de dados pelo que foi recebido como parametro
		BeanUtils.copyProperties(categoria, categoriaSalvar, "codigo");
		return categoriaRepository.save(categoriaSalvar);
	}

	private Categoria validarCategoriaExiste(Long codigo) {
		Optional<Categoria> categoria = buscaPorCodigo(codigo);
		if(categoria.isPresent()) {
			return categoria.get();
		}
		
		throw new EmptyResultDataAccessException(1);
		
	}
}
