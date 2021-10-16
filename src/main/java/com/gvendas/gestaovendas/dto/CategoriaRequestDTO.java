package com.gvendas.gestaovendas.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.gvendas.gestaovendas.entities.Categoria;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Categira requisição DTO")
public class CategoriaRequestDTO {
	//Classe de objetos de entrada
	
	@ApiModelProperty(value = "nome")
	@NotBlank(message = "Nome")
	@Length(min = 3, max = 50, message = "Nome")
	private String nome;
	
	public Categoria converterPraEntidade() {
		return new Categoria(nome);
	}
	
	public Categoria converterPraEntidade(Long codigo) {
		return new Categoria(codigo, nome);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	

}
