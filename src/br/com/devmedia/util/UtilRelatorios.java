package br.com.devmedia.util;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import com.sun.faces.config.FaceletsConfiguration;

import groovy.servlet.ServletCategory;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class UtilRelatorios {
	
	public static void imprimeRelatorio(String relatorioNome, 
				HashMap parametros, List lista) {
		try {
			JRBeanCollectionDataSource dataSource = 
					new JRBeanCollectionDataSource(lista);
			
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.responseComplete();
			ServletContext scontext = 
					(ServletContext) facesContext.getExternalContext().getContext();			
			String path = scontext.getRealPath("/WEB-INF/relatorios/");
			parametros.put("SUBREPORT_DIR", path + File.separator);
			JasperPrint jasperPrint = 
					JasperFillManager.fillReport(scontext.getRealPath("/WEB-INF/relatorios/") + relatorioNome +".jasper",
							parametros, dataSource);
			byte[] b = JasperExportManager.exportReportToPdf(jasperPrint);
			
			HttpServletResponse res = (HttpServletResponse) 
						facesContext.getCurrentInstance().getExternalContext().getResponse();
			res.setContentType("application/pdf");
			int codigo = (int) (Math.random() * 1000);
			res.setHeader("Context-disposition", "inline);filename=relatorio_"+codigo+".pdf");
			res.getOutputStream().write(b);
			res.getCharacterEncoding();
			facesContext.responseComplete();
			
		} catch(Exception e) {
			UtilMessagens.mensagemErro("Erro ao imprimir: " + UtilErros.getMensagemErro(e));
			e.printStackTrace();
			}
		}
}
