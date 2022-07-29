package com.secureApp.SecureApp.service;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.secureApp.SecureApp.DTO.BillCreation;
import com.secureApp.SecureApp.Model.OrderRepository;
import com.secureApp.SecureApp.UtilFunctions.GenerateBillPdf;
import com.secureApp.SecureApp.entity.BillCreationEntity;
import com.secureApp.SecureApp.entity.OrderedItems;
import com.secureApp.SecureApp.entity.PdfDtaEntity;
import com.secureApp.SecureApp.repository.BillingRepository;

/**
 * @author Ankit Mohapatra
 * 
 */
@Service
public class BillingServiceImpl implements BillingService {
	
	private static final Logger log = LoggerFactory.getLogger(BillingServiceImpl.class);
	
	@Autowired
	private BillingRepository billingRepo;
	
	@Autowired
	private GenerateBillPdf generateBillutil;
	
	
	@Autowired
	private OrderRepository orderRepository;
	

	@Override
	public String createBill(BillCreation billCreation) {
		
		BillCreationEntity bill = null;
		BillCreationEntity billEntity = new BillCreationEntity();
		billEntity = dtoToEntity(billCreation);
		try {
			List<OrderedItems> items = billEntity.getItems();
			double totalprice=0;
			double final_price_after_tax=0;
			for(OrderedItems item : items) {
				totalprice  =  totalprice + (item.getOrder_item_price()*item.getOrder_item_qty());
				
			}
			
		    //finding total price
			final_price_after_tax = totalprice + ((billEntity.getCGST()+billEntity.getSGST())*totalprice)/100;
	
			//total amound after tax
			billEntity.setNet_amount(final_price_after_tax);
			bill = new BillCreationEntity();
			String card = unique_bill("BILLNUMBER");
			billEntity.setGstin_unique_bill_number(card);
		    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			Date date = new Date();  
			billEntity.setDate_of_creation(formatter.format(date));
			
			//saving bill
			bill = billingRepo.save(billEntity);
			for(int i =0;i<items.size();i++) {
				OrderedItems item = items.get(i);
				item.setBill(bill);
				//saving order
				orderRepository.saveAndFlush(item);
			}
		}catch (Exception e) {
			log.error("BillingServiceImpl::createBill()",e.toString());
			return "Failed to save bill !";
		}
		
		return "Successfully saved bill !!";
	}
	
	
	//creating unique number for billname
	public String unique_bill(String card) {
		Random rand = new Random();

	    for (int i = 0; i < 15; i++)
	    {
	        int n = rand.nextInt(10) + 0;
	        card += Integer.toString(n);
	    }
	   
		return card;
	}
	
	
	//mapper functions
	//payloadtoentity
	
	public BillCreationEntity dtoToEntity(BillCreation billDto) {
		BillCreationEntity billEntity =  new BillCreationEntity();
		billEntity.setRestaurant_name(billDto.getRestaurant_name());
		billEntity.setRestaurant_address(billDto.getRestaurant_address());
		billEntity.setRestaurant_phone(billDto.getRestaurant_phone());
		billEntity.setRestaurant_secondary_phone(billDto.getRestaurant_secondary_phone());
		billEntity.setTable_number(billDto.getTable_number());
		billEntity.setCustomer_name(billDto.getCustomer_name());
		billEntity.setCustomer_phone(billDto.getCustomer_phone());
		billEntity.setCustomer_email(billDto.getCustomer_email());
		billEntity.setItems(billDto.getItems());
		billEntity.setSGST(billDto.getSGST());
		billEntity.setCGST(billDto.getCGST());
		billEntity.setNet_amount(billDto.getNet_amount());
		billEntity.setCashier_name(billDto.getCashier_name());
		billEntity.setMode_of_payment(billDto.getMode_of_payment());
		
		return billEntity;
	}

	//mapper functions
	//entity to payload

	public BillCreation entityToDto(BillCreationEntity billEntity) {
		BillCreation billDto =  new BillCreation();
		billDto.setRestaurant_name(billEntity.getRestaurant_name());
		billDto.setRestaurant_address(billEntity.getRestaurant_address());
		billDto.setRestaurant_phone(billEntity.getRestaurant_phone());
		billDto.setRestaurant_secondary_phone(billEntity.getRestaurant_secondary_phone());
		billDto.setTable_number(billEntity.getTable_number());
		billDto.setCustomer_name(billEntity.getCustomer_name());
		billDto.setCustomer_phone(billEntity.getCustomer_phone());
		billDto.setCustomer_email(billEntity.getCustomer_email());
		billDto.setItems(billEntity.getItems());
		billDto.setSGST(billEntity.getSGST());
		billDto.setCGST(billEntity.getCGST());
		billDto.setNet_amount(billEntity.getNet_amount());
		billDto.setCashier_name(billEntity.getCashier_name());
		billDto.setMode_of_payment(billEntity.getMode_of_payment());
		return billDto;
	}

	@Override
	public Collection<BillCreation> getAllBills() {
		
		Collection<BillCreationEntity> bills=null;
		Set<BillCreation> billdto = new HashSet<>();
		try {
			bills = billingRepo.findAll();
			for(BillCreationEntity b : bills) {
				billdto.add(entityToDto(b));
			}
		}
		catch(Exception e) {
			log.error("BillingServiceImpl::getAllBills()",e.toString());
			return null;
		}
		return billdto;
	}

	@Override
	public PdfDtaEntity findBillById(long bill_id) {
		BillCreationEntity bill;
		PdfDtaEntity result = null;
		try {
			bill = billingRepo.findById(bill_id).get();
			
			if(bill != null) {
				result = generateBillutil.GenerateBillpdf(bill);
			}
			
		}catch(Exception e) {
			log.error("BillingServiceImpl::findBillById()",e.toString());
			return result;
		}
		return result;
	}
}
