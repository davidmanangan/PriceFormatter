package iam.davidmanangan.priceformatter.model;

import java.math.BigDecimal;

public class PriceFormat {

	/**
	 * Input Parameters
	 */
	private BigDecimal rawPrice;
	private Integer format;
	private Integer scale;
	private Integer fpl;
	private Integer dpl;

	/**
	 * Output Parameters
	 */
	
    private BigDecimal bf;
    private BigDecimal dp;
    private BigDecimal fp;
    
    public PriceFormat() {
	}
    
	public PriceFormat(BigDecimal rawPrice, Integer format, Integer scale, Integer fpl, Integer dpl) {
		super();
		this.rawPrice = rawPrice;
		this.format = format;
		this.scale = scale;
		this.fpl = fpl;
		this.dpl = dpl;
	}

	public BigDecimal getRawPrice() {
		return rawPrice;
	}
	public void setRawPrice(BigDecimal rawPrice) {
		this.rawPrice = rawPrice;
	}
	public Integer getFormat() {
		return format;
	}
	public void setFormat(Integer format) {
		this.format = format;
	}
	public Integer getScale() {
		return scale;
	}
	public void setScale(Integer scale) {
		this.scale = scale;
	}
	public Integer getFpl() {
		return fpl;
	}
	public void setFpl(Integer fpl) {
		this.fpl = fpl;
	}
	public Integer getDpl() {
		return dpl;
	}
	public void setDpl(Integer dpl) {
		this.dpl = dpl;
	}
	public BigDecimal getBf() {
		return bf;
	}
	public void setBf(BigDecimal bf) {
		this.bf = bf;
	}
	public BigDecimal getDp() {
		return dp;
	}
	public void setDp(BigDecimal dp) {
		this.dp = dp;
	}
	public BigDecimal getFp() {
		return fp;
	}
	public void setFp(BigDecimal fp) {
		this.fp = fp;
	}
	
}
