package com.nayab.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.transaction.Transactional;

import com.nayab.datamodel.Shipping;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.nayab.datamodel.Customer;
import com.nayab.datamodel.SalesOrder;
import com.nayab.dto.SalesOrderDTO;
import com.nayab.repository.CustomerSORepository;
import com.nayab.repository.SalesOrderRepository;

@Transactional
@Service
public class SalesOrderService {
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private SalesOrderRepository salesOrderRepository;

	@Autowired
	private CustomerSORepository customerSORepository;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public List<SalesOrderDTO> all() {
		return salesOrderRepository.findAll().stream().map(c -> modelMapper.map(c, SalesOrderDTO.class))
				.collect(Collectors.toList());
	}

	public SalesOrderDTO save(SalesOrderDTO salesOrderDTO) {
		SalesOrder salesOrder = salesOrderRepository.save(modelMapper.map(salesOrderDTO, SalesOrder.class));
		Customer customer = customerSORepository.getOne(salesOrder.getCustId());
		new Shipping("123","456","hgcchg","bjhnmjb","ghgcg","aerse","india");
		try {
			this.mailSender.send(mimeMessage -> {
				mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(customer.getCustEmail()));
				mimeMessage.setFrom(new InternetAddress("info@mymicroservice.com"));
				StringBuilder messageBodyBldr = new StringBuilder();
				messageBodyBldr.append("Dear ").append(customer.getCustLastName()).append(", ")
						.append(customer.getCustFirstName()).append(", thanks for your order. ")
						.append("Your order number is ").append(salesOrder.getId()).append(".");
				mimeMessage.setText(messageBodyBldr.toString());

			});
		} catch (MailException ex) {
			// simply log it and go on...
			System.err.println(ex.getMessage());
		}
		return modelMapper.map(salesOrder, SalesOrderDTO.class);
	}

	public SalesOrderDTO get(long orderId) {
		Optional<SalesOrder> orderResult = salesOrderRepository.findById(orderId);
		if (!orderResult.isPresent()) {
			return null;
		}
		return modelMapper.map(orderResult.get(), SalesOrderDTO.class);
	}

	public void delete(long orderId) {
		salesOrderRepository.deleteById(orderId);
	}
}
