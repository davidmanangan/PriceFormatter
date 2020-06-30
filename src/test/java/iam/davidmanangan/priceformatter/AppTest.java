package iam.davidmanangan.priceformatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import iam.davidmanangan.priceformatter.constants.DisplayFormat;
import iam.davidmanangan.priceformatter.model.PriceFormat;
import iam.davidmanangan.priceformatter.service.PriceFormatterService;

class AppTest {

	PriceFormatterService priceFormatterService = new PriceFormatterService();

	
	
	@Test
	public void test_BF_Decimal() {

		PriceFormat mockInput = new PriceFormat(new BigDecimal(47.9200d), DisplayFormat.DECIMAL.getValue(), 4, 3, 2);
		BigDecimal expectedBf = new BigDecimal(4);
		BigDecimal actualBf = priceFormatterService.getBF(mockInput);

		assertEquals(expectedBf, actualBf);
	}

	@Test
	public void test_DP_Decimal() {

		PriceFormat mockInput = new PriceFormat(new BigDecimal(47.9200d), DisplayFormat.DECIMAL.getValue(), 4, 3, 2);
		BigDecimal expectedDp = new BigDecimal(7.9).setScale(1, BigDecimal.ROUND_FLOOR);
		BigDecimal actualDp = priceFormatterService.getDP(mockInput);

		assertEquals(expectedDp, actualDp);
	}

	@Test
	public void test_FP_Decimal() {
		PriceFormat mockInput = new PriceFormat(new BigDecimal(47.9200d), DisplayFormat.DECIMAL.getValue(), 4, 3, 2);
		BigDecimal expectedFp = new BigDecimal(2);
		BigDecimal actualFp = priceFormatterService.getFP(mockInput);

		assertEquals(expectedFp, actualFp);
	}

	
	@Test
	public void test_BF_Decimal_ShiftParameters() {

		PriceFormat mockInput = new PriceFormat(new BigDecimal(47.9200d), DisplayFormat.DECIMAL.getValue(), 4, 3, 3);
		BigDecimal expectedBf = BigDecimal.ZERO;
		BigDecimal actualBf = priceFormatterService.getBF(mockInput);

		assertEquals(expectedBf, actualBf);
	}

	@Test
	public void test_DP_Decimal_ShiftParameters() {
	    DecimalFormat df = new DecimalFormat();

        df.setMaximumFractionDigits(2); //increase precision to avoid loosing original decimal values
        Double dpVal = 47.9;
        Double formattedDPDouble = new Double(df.format(dpVal));
        
		PriceFormat mockInput = new PriceFormat(new BigDecimal(47.9200d), DisplayFormat.DECIMAL.getValue(), 4, 3, 3);
		BigDecimal expectedDp = new BigDecimal(formattedDPDouble).setScale(1, BigDecimal.ROUND_HALF_UP);
		BigDecimal actualDp = priceFormatterService.getDP(mockInput);

		assertEquals(expectedDp, actualDp);
	}

	@Test
	public void test_FP_Decimal_ShiftParameters() {
		PriceFormat mockInput = new PriceFormat(new BigDecimal(47.9200d), DisplayFormat.DECIMAL.getValue(), 4, 3, 3);
		BigDecimal expectedFp = new BigDecimal(2);
		BigDecimal actualFp = priceFormatterService.getFP(mockInput);

		assertEquals(expectedFp, actualFp);
	}
	

	@Test
	public void test_BP_Percentage() {

		PriceFormat mockInput = new PriceFormat(new BigDecimal(0.1523), DisplayFormat.PERCENTAGE.getValue(), 4, 2, 1);
		BigDecimal expectedBf = new BigDecimal(15.20d).setScale(1, BigDecimal.ROUND_HALF_UP);
		BigDecimal actualBf = priceFormatterService.getBF(mockInput);

		assertEquals(expectedBf, actualBf);
	}

	@Test
	public void test_DP_Percentage() {
		PriceFormat mockInput = new PriceFormat(new BigDecimal(0.1523), DisplayFormat.PERCENTAGE.getValue(), 4, 2, 1);
		BigDecimal expectedDp = new BigDecimal(3).setScale(0, BigDecimal.ROUND_FLOOR);
		BigDecimal actualDp = priceFormatterService.getDP(mockInput);

		assertEquals(expectedDp, actualDp);
	}

	@Test
	public void test_FP_Percentage() {
		PriceFormat mockInput = new PriceFormat(new BigDecimal(0.1523), DisplayFormat.PERCENTAGE.getValue(), 4, 2, 1);
		BigDecimal expectedFp = BigDecimal.ZERO.setScale(0, BigDecimal.ROUND_FLOOR);
		BigDecimal actualFp = priceFormatterService.getFP(mockInput);

		assertEquals(expectedFp, actualFp);
	}

	@Test
	public void test_BF_PerMile() {
		PriceFormat mockInput = new PriceFormat(new BigDecimal(0.0018), DisplayFormat.PER_MILE.getValue(), 2, 1, 3);
		BigDecimal expectedBf = BigDecimal.ZERO;
		BigDecimal actualBf = priceFormatterService.getBF(mockInput);

		assertEquals(expectedBf, actualBf);
	}

	@Test
	public void test_DP_PerMile() {
		PriceFormat mockInput = new PriceFormat(new BigDecimal(0.0018), DisplayFormat.PER_MILE.getValue(), 2, 1, 3);
		BigDecimal expectedDp = new BigDecimal(1.8).setScale(1, BigDecimal.ROUND_FLOOR);
		BigDecimal actualDp = priceFormatterService.getDP(mockInput);

		assertEquals(expectedDp, actualDp);
	}

	@Test
	public void test_FP_PerMile() {
		PriceFormat mockInput = new PriceFormat(new BigDecimal(0.0018), DisplayFormat.PER_MILE.getValue(), 2, 1, 3);
		BigDecimal expectedFp = BigDecimal.ZERO;
		BigDecimal actualFp = priceFormatterService.getFP(mockInput);

		assertEquals(expectedFp, actualFp);
	}

	@Test
	public void test_BF_BasisPoint() {
		PriceFormat mockInput = new PriceFormat(new BigDecimal(0.0018), DisplayFormat.BASIS_POINT.getValue(), 2, 1, 3);
		BigDecimal expectedBf = BigDecimal.ZERO.setScale(0, BigDecimal.ROUND_FLOOR);
		BigDecimal actualBf = priceFormatterService.getBF(mockInput);

		assertEquals(expectedBf, actualBf);
	}

	@Test
	public void test_DP_Basis_Point() {
		PriceFormat mockInput = new PriceFormat(new BigDecimal(0.0018), DisplayFormat.BASIS_POINT.getValue(), 2, 1, 3);
		BigDecimal expectedDp = new BigDecimal(18.0d).setScale(1, BigDecimal.ROUND_FLOOR);
		BigDecimal actualDp = priceFormatterService.getDP(mockInput);

		assertEquals(expectedDp, actualDp);
	}

	@Test
	public void test_FP_Basis_Point() {
		PriceFormat mockInput = new PriceFormat(new BigDecimal(0.0018), DisplayFormat.BASIS_POINT.getValue(), 2, 1, 3);
		BigDecimal expectedFp = BigDecimal.ZERO.setScale(0, BigDecimal.ROUND_FLOOR);
		BigDecimal actualFp = priceFormatterService.getFP(mockInput);

		assertEquals(expectedFp, actualFp);
	}

}
