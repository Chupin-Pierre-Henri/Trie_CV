package fr.univ_lyon1.info.m1.cv_search.view;

public class RequestAddExeption extends Exception {
	private String message;
	public RequestAddExeption(String message) {
		this.message = message;
	}
	/**
	 * error code for RequestAddExeption
	 */
	private static final long serialVersionUID = 1L;
	
}
