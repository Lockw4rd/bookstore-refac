package br.livraria.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Convert {

	private Convert() {}
	
	public static Date parseDate(String data) {
		
		if(data.equals(""))
			return new Date();
		
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		
		Date dataFormatada = null;
		
		try {
			dataFormatada = formato.parse(data);
			
		} catch (ParseException e1) {
			
			e1.printStackTrace();
			
		}
		
		return dataFormatada;
	}
	
}
