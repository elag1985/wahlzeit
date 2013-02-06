package org.wahlzeit.model;

public class ReadWriteException extends RuntimeException {
	
	private static final long serialVersionUID=1L;
	
	public ReadWriteException(Throwable e){
		super(e);
	}

}
