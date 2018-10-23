package upv.etsinf.cognispatium.service;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import javax.swing.text.StyleConstants;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class PDFgenerator {


	private OutputStream fos;
	private PdfWriter writer;
	private PdfDocument pdfdoc;
	private Document doc;
	private String filename;
	   
	public PDFgenerator(String filename) throws IOException{
	      this.filename=filename;
	      fos = new FileOutputStream(new File(filename));
	      writer = new PdfWriter(fos);
	      pdfdoc = new PdfDocument(writer);
	      doc = new Document(pdfdoc);
	      createSimpleTextReport();
	      doc.close();
	      writer.close();
	   }
	private void createSimpleTextReport(){
		
	      doc.add(new Paragraph("- FACTURA - ").setFontSize(20f));
	      doc.add(new Paragraph("Servicio :"));
	      doc.add(new Paragraph("Descripción: "));
	      doc.add(new Paragraph("Precio: "));

	     
	   }
	
	public static void main(String[] args) {
		
		try {
			new PDFgenerator("PDf.pdf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
