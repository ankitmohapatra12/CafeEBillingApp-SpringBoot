package com.secureApp.SecureApp.UtilFunctions;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.secureApp.SecureApp.Constants.FileStorageLocationConstants;
import com.secureApp.SecureApp.entity.BillCreationEntity;
import com.secureApp.SecureApp.entity.OrderedItems;
import com.secureApp.SecureApp.entity.PdfDtaEntity;


/**
 * @author Ankit Mohapatra
 * 
 */

@Component
public class GenerateBillPdf {
	
	private static final Logger log = LoggerFactory.getLogger(GenerateBillPdf.class);
	//generating bill
	public PdfDtaEntity GenerateBillpdf(BillCreationEntity bill) {
		PdfDtaEntity pdfEntity = null;
		Document document = new Document();
		try {
			File destPath = null;
			String path1 = "D:"+File.separator+"Bill generation"+File.separator;
			System.out.println(path1);
			destPath=new File(path1);
			if(!destPath.exists()){
				destPath.mkdirs();
			}

			String pdfName = path1+bill.getGstin_unique_bill_number()+".pdf";
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdfName));
			document.open();
			Font f1=new Font(FontFamily.HELVETICA,12.0f,Font.BOLDITALIC,BaseColor.BLUE);
			Font f=new Font(FontFamily.HELVETICA,12.0f,Font.NORMAL,BaseColor.DARK_GRAY);
			Font f2=new Font(FontFamily.TIMES_ROMAN,12.0f,Font.BOLDITALIC,BaseColor.GRAY);
			//Font f3=new Font(FontFamily.TIMES_ROMAN,12.0f,Font.BOLD,BaseColor.GRAY);
			Font f4=new Font(FontFamily.TIMES_ROMAN,12.0f,Font.BOLD,BaseColor.BLACK);
			//restaurant name 
			Paragraph para = new Paragraph(bill.getRestaurant_name()+"\n",f1);
			para.setFont(f);
			
			para.setAlignment(Element.ALIGN_CENTER);
			
			document.add(para);
			para.remove(0);
			
			
			//restaurant address
			para.add(bill.getRestaurant_address()+"\n");
			document.add(para);
			para.remove(0);
			
			
			//number
			para.add("PHONE NO  :"+bill.getRestaurant_phone()+","+bill.getRestaurant_secondary_phone()+"\n");
			//para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);
			para.remove(0);
			
			
			
			//gstin
			para.setFont(f2);
			para.add("GSTIN :"+bill.getGstin_unique_bill_number()+"\n\n");
			document.add(para);
			para.remove(0);
			para.setFont(f1);
			
			
			
			Paragraph paraLine = new Paragraph("------------------------------------------------------------"+
					"----------------------------------------------------------------------",f4);
			document.add(paraLine);
			paraLine.remove(0);
			
			
			
			paraLine.add("Bill no  :"+bill.getBill_id()+"                                                                                                             Date :"+bill.getDate_of_creation()+"\n");
			document.add(paraLine);
			paraLine.remove(0);
			
			
			paraLine.add("Table no :"+bill.getTable_number()+"                                      "+"\n");
			document.add(paraLine);
			paraLine.remove(0);
			
			
			
			
			paraLine.add("------------------------------------------------------------"+
					"----------------------------------------------------------------------");
			document.add(paraLine);
			paraLine.remove(0);
			
			
			
			
			PdfPTable table = new PdfPTable(4);
			PdfPCell cell = new PdfPCell();
			cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			table.setWidthPercentage(100); //Width 100%
	        table.setSpacingBefore(10f); //Space before table
	        table.setSpacingAfter(10f);
			float[] columnWidths = {300f,70f,70f,70f};
	        table.setWidths(columnWidths);
			cell.setPhrase(new Phrase("Items"));
			table.addCell(cell);
			cell.setPhrase(new Phrase("Qty"));
			table.addCell(cell);
			cell.setPhrase(new Phrase("Price"));
			table.addCell(cell);
			cell.setPhrase(new Phrase("Value"));
			table.addCell(cell);
			double total =0;
			List<OrderedItems> items = bill.getItems();
			for(int i=0;i<items.size();i++) {
				OrderedItems item = items.get(i);
				cell.setBorder(0);
				cell.setPhrase(new Phrase(item.getOrder_item_name()));
				table.addCell(cell);
				cell.setPhrase(new Phrase(String.valueOf(item.getOrder_item_qty())));
				table.addCell(cell);
				cell.setPhrase(new Phrase(String.valueOf(item.getOrder_item_price())));
				table.addCell(cell);
				cell.setPhrase(new Phrase(String.valueOf(item.getOrder_item_price()*item.getOrder_item_qty())));
				table.addCell(cell);
				total = total+(item.getOrder_item_price()*item.getOrder_item_qty());
			}
			cell.setPhrase(new Phrase(" "));
			table.addCell(cell);
			cell.setPhrase(new Phrase(" "));
			table.addCell(cell); 
			cell.setPhrase(new Phrase(" "));
			table.addCell(cell);
			cell.setPhrase(new Phrase("  "));
			table.addCell(cell);
			document.add(table);
			
			
			
			paraLine.add("------------------------------------------------------------"+
					"----------------------------------------------------------------------");
			document.add(paraLine);
			paraLine.remove(0);
			
			
			
			
			
			PdfPTable table1 = new PdfPTable(4);
			PdfPCell cell1 = new PdfPCell();
			cell1.setBorder(0);
			table1.setWidthPercentage(100); //Width 100%
	        table1.setSpacingBefore(10f); //Space before table
	        table1.setSpacingAfter(10f);
		
	        table1.setWidths(columnWidths);
			cell1.setPhrase(new Phrase("SUB TOTAL"));
			table1.addCell(cell1);
			cell1.setPhrase(new Phrase(""));
			table1.addCell(cell1);
			cell1.setPhrase(new Phrase(""));
			table1.addCell(cell1);
			cell1.setPhrase(new Phrase(String.valueOf(total)));
			table1.addCell(cell1);
			document.add(table1);
			
	
			
			PdfPTable table3 = new PdfPTable(4);
			PdfPCell cell3 = new PdfPCell();
			cell3.setBorder(0);
			table3.setWidthPercentage(100); //Width 100%
	        table3.setSpacingBefore(10f); //Space before table
	        table3.setSpacingAfter(10f);
			
	        table3.setWidths(columnWidths);
			cell3.setPhrase(new Phrase("Add SGST("+bill.getSGST()+"%) on "+total));
			table3.addCell(cell3);
			cell3.setPhrase(new Phrase(""));
			table3.addCell(cell3);
			cell3.setPhrase(new Phrase(""));
			table3.addCell(cell3);
			cell3.setPhrase(new Phrase(String.valueOf((bill.getSGST()*total)/100)));
			table3.addCell(cell3);
			
			
			
			
			
			
			
			
			
			cell3.setPhrase(new Phrase("Add CGST("+bill.getCGST()+"%) on "+total));
			table3.addCell(cell3);
			cell3.setPhrase(new Phrase(""));
			table3.addCell(cell3);
			cell3.setPhrase(new Phrase(""));
			table3.addCell(cell3);
			cell3.setPhrase(new Phrase(String.valueOf((bill.getCGST()*total)/100)));
			table3.addCell(cell3);
			document.add(table3);
			
			paraLine.add("------------------------------------------------------------"+
					"----------------------------------------------------------------------");
			document.add(paraLine);
			paraLine.remove(0);
			
			
			
			PdfPTable table4 = new PdfPTable(4);
			PdfPCell cell4 = new PdfPCell();
			cell4.setBorder(0);
			cell4.setBackgroundColor(BaseColor.LIGHT_GRAY);
			table4.setWidthPercentage(100); //Width 100%
	        table4.setSpacingBefore(10f); //Space before table
	        table4.setSpacingAfter(10f);
			
	        table4.setWidths(columnWidths);
	
			double net_worth = total+(((bill.getCGST()+bill.getSGST())*total)/100);
			cell4.setPhrase(new Phrase("AMOUNT INC. OF ALL TAXES"));
			table4.addCell(cell4);
			cell4.setPhrase(new Phrase(""));
			table4.addCell(cell4);
			cell4.setPhrase(new Phrase(""));
			table4.addCell(cell4);
			
			cell4.setPhrase(new Phrase(String.valueOf(net_worth)+" INR"));
			table4.addCell(cell4);
			
			
			for(int i =0;i<4;i++) {
				cell4.setPhrase(new Phrase(""));
				table4.addCell(cell4);
			}
			
	
			
			
			PdfPTable table5 = new PdfPTable(4);
			PdfPCell cell5 = new PdfPCell();
			cell5.setBorder(0);
			table5.setWidthPercentage(100); //Width 100%
	        table5.setSpacingBefore(10f); //Space before table
	        table5.setSpacingAfter(10f);
			
	        table5.setWidths(columnWidths);
			
	        
	        
	        
			
			cell5.setPhrase(new Phrase("CASHIER :"+bill.getCashier_name()));
			table5.addCell(cell5);
			cell5.setPhrase(new Phrase(""));
			table5.addCell(cell5);
			cell5.setPhrase(new Phrase(""));
			table5.addCell(cell5);
			cell5.setPhrase(new Phrase(""));
			table5.addCell(cell5);
			
			
			
			document.add(table4);
			
			
			paraLine.add("\n\n");
			document.add(paraLine);
			paraLine.remove(0);
			
			
			
			
	
			para.add("Thankyou for visit \n");
			document.add(para);
			para.remove(0);
			
			
			para.add("Have a nice day !\n");
			document.add(para);
			para.remove(0);
			
			document.close();
			writer.close();
			
			pdfEntity = new PdfDtaEntity();
			pdfEntity.setFk_bill_id(bill.getBill_id());
			pdfEntity.setPdf_name(bill.getGstin_unique_bill_number()+".pdf");
			String path = FileStorageLocationConstants.PathUrl+bill.getGstin_unique_bill_number()+".pdf";
			pdfEntity.setPdf_url(path);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			Date date = new Date();  
			pdfEntity.setDate_of_creation(formatter.format(date));
			
			return pdfEntity;
			
		}catch (Exception e) {
			log.error("GenerateBillpdf::GenerateBillpdf()",e.toString());
			return null;
		}
		
	}
}
