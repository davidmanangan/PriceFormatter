package iam.davidmanangan.priceformatter.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import iam.davidmanangan.priceformatter.model.PriceFormat;
import iam.davidmanangan.priceformatter.model.PriceFormatRequest;
import iam.davidmanangan.priceformatter.service.PriceFormatterService;


@CrossOrigin(origins = "*")
@RestController
public class PriceFormatterController {

	@Autowired
	PriceFormatterService priceFormatterService;
	
	@PostMapping("/format-price")
	public PriceFormat formatPrice(@RequestBody PriceFormatRequest pfr) {
		
		PriceFormat pf = new PriceFormat();
		
		pf.setRawPrice(new BigDecimal(pfr.getRawPrice()));
		pf.setFormat(pfr.getFormat());
		pf.setScale(pfr.getScale());
		pf.setDpl(pfr.getDpl());
		pf.setFpl(pfr.getFpl());
		
		pf.setBf(priceFormatterService.getBF(pf));
		pf.setDp(priceFormatterService.getDP(pf));
		pf.setFp(priceFormatterService.getFP(pf));
		
		return pf;
	}
}
