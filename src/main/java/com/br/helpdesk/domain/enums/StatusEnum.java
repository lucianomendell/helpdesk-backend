package com.br.helpdesk.domain.enums;

public enum StatusEnum {
	
	ADMIN(0,"ROLE_ADMIN"),CLIENTE(1,"ROLE_CLIENTE"),TECNICO(2,"ROLE_TECNICO");	
	
	private Integer codigo;
	private String descricao;	
	
	
	private StatusEnum(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public static StatusEnum toEnum(Integer cod) {
		
		if(cod ==null) {
			return null;
		}		
		
		for(StatusEnum x: StatusEnum.values()) {
			if(cod.equals(x.getCodigo())) {				
				return x;
			}
		}
		
		throw new IllegalArgumentException("Perfil Inválido");
	}


	public Integer getCodigo() {
		return codigo;
	}


	public String getDescricao() {
		return descricao;
	}

}
