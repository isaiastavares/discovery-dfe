package br.com.discovery.dfe.entidade.support;

import org.springframework.data.domain.Persistable;

public interface Entityable extends Persistable<Long> {

	Long getId();

	void setId(Long id);

}
