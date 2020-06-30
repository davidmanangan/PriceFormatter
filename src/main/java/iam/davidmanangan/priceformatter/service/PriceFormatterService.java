package iam.davidmanangan.priceformatter.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import iam.davidmanangan.priceformatter.constants.DisplayFormat;
import iam.davidmanangan.priceformatter.model.PriceFormat;

@Service
public class PriceFormatterService {
	
	static final Map<Integer,Integer> decimalPlaceIncrementMap = initDecimalPlaceIncrementMap();
	
	
	public BigDecimal getBF(PriceFormat pfi){
        
    
        BigDecimal bf = extractBF(pfi);

        BigDecimal bfNonTrailingZeroes = removeTrailingZeroes(bf);
        
        if(bfNonTrailingZeroes != null){
        	
            return bfNonTrailingZeroes;        
            
        }
        
        return BigDecimal.ZERO;

	}
	
	public BigDecimal getDP(PriceFormat pfi){

        BigDecimal formattedRawPrice = getFormattedRawPrice(pfi);

        BigDecimal bf = extractBF(pfi);

        BigDecimal dpfp = formattedRawPrice.subtract(bf);
        
        BigDecimal dpScaled = dpfp.setScale(pfi.getScale()-pfi.getFpl(),BigDecimal.ROUND_FLOOR);
    
        BigDecimal dpTruncated = truncateValue(dpScaled);
        
        BigDecimal dpNonTrailingZeroes = removeTrailingZeroes(dpTruncated);
        
        if(dpNonTrailingZeroes != null){
        	
        	if(pfi.getScale() < pfi.getDpl() + pfi.getFpl()) {
        		if(pfi.getFpl() < pfi.getScale()) {
        		    
        		    DecimalFormat df = new DecimalFormat();

                    df.setMaximumFractionDigits(pfi.getScale()- pfi.getFpl()+1); //increase precision to avoid loosing original decimal values
                    
                    Double formattedDPDouble = new Double(df.format(dpScaled.doubleValue()));
                    
                    dpNonTrailingZeroes = removeTrailingZeroes(new BigDecimal(formattedDPDouble));
                    
        		    return dpNonTrailingZeroes.setScale(pfi.getScale() - pfi.getFpl(),BigDecimal.ROUND_HALF_UP);
        		}
        	}
        	
            return dpNonTrailingZeroes;
        }
        
        return BigDecimal.ZERO;
        
        
        
	}

	public BigDecimal getFP(PriceFormat pfi){

        BigDecimal formattedRawPrice = getFormattedRawPrice(pfi);
        
        BigDecimal rawPriceAsWholeNumber = formattedRawPrice.multiply(new BigDecimal(Math.pow(10,pfi.getScale()))).setScale(0,BigDecimal.ROUND_FLOOR);
        
        Double bfdpAsWholeNumber = rawPriceAsWholeNumber.divide(new BigDecimal(Math.pow(10,pfi.getFpl()))).setScale(0,BigDecimal.ROUND_FLOOR).doubleValue();
        
        Double bfdpScaled = bfdpAsWholeNumber * Math.pow(10,pfi.getFpl());
        
        Double fp = rawPriceAsWholeNumber.doubleValue() - bfdpScaled;
        
        BigDecimal fpTruncated = truncateValue(new BigDecimal(fp));
        
        BigDecimal fpNonTrailingZeroes = removeTrailingZeroes(fpTruncated);
        
        if(fpNonTrailingZeroes != null){
            
            return fpNonTrailingZeroes;
            
        }

        return BigDecimal.ZERO;

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

	
	 double getPlaceValue(double value){
	        double tenthPower = Math.floor(Math.log10(value));
	        double place = Math.pow(10, tenthPower);
	        return place;
	    }


	 BigDecimal removeTrailingZeroes(BigDecimal bf){
        
        Double place = getPlaceValue(bf.doubleValue());
    
        
        if(place == 1.0d){ //DECIMAL

            return bf;

        }else if(place == 10.0d){ //TENS

            Double onesPlace = bf.doubleValue() % 10; 

            if(onesPlace < 10 && onesPlace > 0){
                return bf;
            }else{
                return bf.divide(new BigDecimal(10)).setScale(0,BigDecimal.ROUND_FLOOR);
            
            }
        }else if(place == 100.0d){ //PERCENTAGE
            Double tensPlace = (bf.doubleValue()/10) % 10;
            
            if(tensPlace < 100 && tensPlace > 0){
                return bf;            
            }else{
                return bf.divide(new BigDecimal(100)).setScale(0,BigDecimal.ROUND_FLOOR);
            }
            
        }else if(place == 1000.0d){ //PER MILE
            Double hundredsPlace = (bf.doubleValue()/100) % 10;
            
            if(hundredsPlace < 1000 && hundredsPlace > 0){
                return bf;            
            }else{
                return bf.divide(new BigDecimal(1000)).setScale(0,BigDecimal.ROUND_FLOOR);
            }
            
        }else if(place == 10000.0d){ //BASIS POINT
            Double thousandsPlace = (bf.doubleValue()/10000) % 10;
            
            if(thousandsPlace < 10000 && thousandsPlace > 0){
                return bf;            
            }else{
                return bf.divide(new BigDecimal(10000)).setScale(0,BigDecimal.ROUND_FLOOR);
            }
            
        }
        
        return null;
    
    }
    
    BigDecimal truncateValue(BigDecimal dp){
    
        Double dpDouble = dp.doubleValue();
    
        String[] dpArr = dpDouble.toString().split("\\.");
        Integer dpWholeNumber = new Integer(dpArr[0]);
        Integer dpFractionalNumber = 0;
        if(dpArr.length > 1){
            dpFractionalNumber = new Integer(dpArr[1]);
        }
        String dpTruncatedNumber = "";
        StringBuilder sb = new StringBuilder();

        if(dpWholeNumber > 0 && dpFractionalNumber > 0){
            
            sb.append(dpWholeNumber.toString());
            sb.append(".");
            sb.append(dpFractionalNumber.toString());

            dpTruncatedNumber = sb.toString();
            if(dpTruncatedNumber.equals("")){
                return BigDecimal.ZERO;
            } 
            
            return new BigDecimal(new Double(dpTruncatedNumber)).setScale(dpArr[1].length(), BigDecimal.ROUND_FLOOR);
        }else {

            if(dpWholeNumber > 0 ){
                
                sb.append(dpWholeNumber.toString());   
                
            }
            
            if( dpFractionalNumber > 0){
                sb.append(dpFractionalNumber.toString());
                
            }        
        
        }
        
        dpTruncatedNumber = sb.toString();
        if(dpTruncatedNumber.equals("")){
            return BigDecimal.ZERO;
        }        
        return new BigDecimal(new Double(dpTruncatedNumber));
        
    }
	
    private static final Map<Integer,Integer> initDecimalPlaceIncrementMap(){
    	Map<Integer,Integer> decimalPlaceIncrementMap = new HashMap<Integer, Integer>();
    	
    	decimalPlaceIncrementMap.put(1,1);
    	decimalPlaceIncrementMap.put(100,2);
    	decimalPlaceIncrementMap.put(1000,3);
    	decimalPlaceIncrementMap.put(10000,4);
    	decimalPlaceIncrementMap.put(100000,5);
    	
    	return decimalPlaceIncrementMap;
    }
}
