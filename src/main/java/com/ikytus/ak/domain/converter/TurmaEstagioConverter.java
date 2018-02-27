package com.ikytus.ak.domain.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.ikytus.ak.domain.TurmaEstagio;

public class TurmaEstagioConverter implements Converter<String, TurmaEstagio> {
	
	@Override
	public TurmaEstagio convert(String codigo) {
		if (!StringUtils.isEmpty(codigo)) {
			TurmaEstagio turmaEstagio= new TurmaEstagio();
			turmaEstagio.setId(Long.valueOf(codigo));
			return turmaEstagio;
		}
		
		return null;
	}

}
