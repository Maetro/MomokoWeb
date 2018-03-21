package com.momoko.es.util;

import com.momoko.es.api.datosestructurados.BookMainEntity;
import com.momoko.es.api.datosestructurados.BookReview;
import com.momoko.es.api.dto.EntradaDTO;
import com.momoko.es.api.dto.LibroDTO;

public class JsonLDUtils {

	public static String crearJsonLDAnalisis(LibroDTO libro, EntradaDTO entrada){
		StringBuilder builder = new StringBuilder();
		if (libro != null && entrada != null) {
		
		BookReview bookReview = new BookReview();
		bookReview.setContext("http://schema.org");
		bookReview.setType("WebPage");
		bookReview.setBreadcrumb("Libros > "+ libro.getGeneros().iterator().next().getCategoria().getNombreCategoria() +" > " + libro.getGenerosString());
		mainEntity = new BookMainEntity();
		
		}
		return builder.toString();
	}
	
}
