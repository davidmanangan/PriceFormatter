package iam.davidmanangan.priceformatter.model;

public class PriceFormatRequest {
	private String rawPrice;
	private Integer format;
	private Integer scale;
	private Integer fpl;
	private Integer dpl;
	
	public PriceFormatRequest() {
	}
	
	public String getRawPrice() {
		return rawPrice;
	}
	public void setRawPrice(String rawPrice) {
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
	
	
}
