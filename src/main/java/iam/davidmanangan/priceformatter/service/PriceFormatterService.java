package iam.davidmanangan.priceformatter.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.springframework.stereotype.Service;

import iam.davidmanangan.priceformatter.constants.DisplayFormat;
import iam.davidmanangan.priceformatter.model.PriceFormat;

@Service
public class PriceFormatterService {
	
	
	public BigDecimal getBF(PriceFormat pfi){
        
    
        if(DisplayFormat.DECIMAL.getValue() == pfi.getFormat()){

            BigDecimal formattedRawPrice = getFormattedRawPrice(pfi);

            BigDecimal priceAsWholeNumber = formattedRawPrice.multiply(new BigDecimal(Math.pow(10,pfi.getScale()))).setScale(0,BigDecimal.ROUND_FLOOR);
            
            BigDecimal bfAsWholeNumber = priceAsWholeNumber.divide(new BigDecimal(Math.pow(10,pfi.getDpl()+pfi.getFpl())));
            
            return bfAsWholeNumber.setScale(0,BigDecimal.ROUND_FLOOR);
            
        }else{
            
            BigDecimal bf = extractBF(pfi);
                
    		return bf.doubleValue() == 0? BigDecimal.ZERO: bf;                
        }

	}
	
	public BigDecimal getDP(PriceFormat pfi){

        BigDecimal formattedRawPrice = getFormattedRawPrice(pfi);

        BigDecimal bf = extractBF(pfi);

        BigDecimal dpfp = formattedRawPrice.subtract(bf);
        
        BigDecimal dpScaled = dpfp.setScale(pfi.getScale()-pfi.getFpl(),BigDecimal.ROUND_FLOOR);
        
        if(dpScaled.doubleValue() < 1 && dpScaled.doubleValue() > -1) {
        
            return dpScaled.multiply(new BigDecimal(Math.pow(10,pfi.getScale()-pfi.getFpl()))).setScale(0, BigDecimal.ROUND_FLOOR);
            
        }else{
        
            return dpScaled;        
            
        }
        
	}

	public BigDecimal getFP(PriceFormat pfi){

        BigDecimal formattedRawPrice = getFormattedRawPrice(pfi);
        
        BigDecimal rawPriceAsWholeNumber = formattedRawPrice.multiply(new BigDecimal(Math.pow(10,pfi.getScale()))).setScale(0,BigDecimal.ROUND_FLOOR);
        
        Double bfdpAsWholeNumber = rawPriceAsWholeNumber.divide(new BigDecimal(Math.pow(10,pfi.getFpl()))).setScale(0,BigDecimal.ROUND_FLOOR).doubleValue();
        
        Double bfdpScaled = bfdpAsWholeNumber * Math.pow(10,pfi.getFpl());
        
        Double fp = rawPriceAsWholeNumber.doubleValue() - bfdpScaled;
        
        return new BigDecimal(fp);

	}	
	
	
	BigDecimal getFormattedRawPrice(PriceFormat pfi1){

    	DecimalFormat df = new DecimalFormat();

        df.setMaximumFractionDigits(pfi1.getScale()+1); //increase precision to avoid loosing original decimal values

        Double scaledPriceDouble = pfi1.getRawPrice().doubleValue() * pfi1.getFormat().doubleValue();

        Double formattedPriceDouble = new Double(df.format(scaledPriceDouble));
        
        BigDecimal formattedRawPrice = new BigDecimal(formattedPriceDouble).setScale(pfi1.getScale(),BigDecimal.ROUND_FLOOR);

        return formattedRawPrice;
	        
	}

	BigDecimal extractBF(PriceFormat pfi){

        BigDecimal formattedRawPrice = getFormattedRawPrice(pfi);

        BigDecimal bf = formattedRawPrice.setScale(pfi.getScale()-pfi.getDpl()-pfi.getFpl(),BigDecimal.ROUND_FLOOR); 

        return bf;
	}
	
}
