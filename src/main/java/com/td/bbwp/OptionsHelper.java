package com.td.bbwp;

import java.util.Optional;

import com.td.bbwp.wf.CaseDefinition;

public class OptionsHelper {
	
	public static <T> T getOrThrow(Optional<T> o, RuntimeException t){
		return o.map(y -> y)
			.orElseThrow(() -> t);
	}

}
