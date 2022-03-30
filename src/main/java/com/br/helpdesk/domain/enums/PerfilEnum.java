package com.br.helpdesk.domain.enums;

public enum PerfilEnum {
	
	ABERTO(0,"ABERTO"),ANDAMENTO(1,"ANDAMENTO"),ENCERRADO(2,"ENCERRADO");
	
	
	private Integer codigo;
	private String descricao;	
	
	
	private PerfilEnum(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	
	public static PerfilEnum toEnum(Integer cod) {
		
		if(cod ==null) {
			return null;
		}		
		
		for(PerfilEnum x: PerfilEnum.values()) {
			if(cod.equals(x.getCodigo())) {				
				return x;
			}
		}
		
		throw new IllegalArgumentException("Status Inválido");
	}


	public Integer getCodigo() {
		return codigo;
	}


	public String getDescricao() {
		return descricao;
	}

}
