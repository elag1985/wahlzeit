package org.wahlzeit.model;

public class PhotoNotFoundException extends RuntimeException{

	public PhotoNotFoundException (Throwable e){
		super(e);
	}
	
}
