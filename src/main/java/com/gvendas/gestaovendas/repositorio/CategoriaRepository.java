package com.gvendas.gestaovendas.repositorio;


import org.springframework.data.jpa.repository.JpaRepository;

import com.gvendas.gestaovendas.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	
	Categoria findByNome(String nome);

}
