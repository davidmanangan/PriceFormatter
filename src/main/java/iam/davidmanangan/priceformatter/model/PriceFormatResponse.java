package iam.davidmanangan.priceformatter.model;

public class PriceFormatResponse {
	private String rawPrice;
	private Integer format;
	private Integer scale;
	private Integer fpl;
	private Integer dpl;
	
	private String bf;
	private String dp;
	private String fp;
	
	public PriceFormatResponse() {
	}

	public PriceFormatResponse(String rawPrice, Integer format, Integer scale, Integer fpl, Integer dpl, String bf,
			String dp, String fp) {
		super();
		this.rawPrice = rawPrice;
		this.format = format;
		this.scale = scale;
		this.fpl = fpl;
		this.dpl = dpl;
		this.bf = bf;
		this.dp = dp;
		this.fp = fp;
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

	public String getBf() {
		return bf;
	}

	public void setBf(String bf) {
		this.bf = bf;
	}

	public String getDp() {
		return dp;
	}

	public void setDp(String dp) {
		this.dp = dp;
	}

	public String getFp() {
		return fp;
	}

	public void setFp(String fp) {
		this.fp = fp;
	}
	
	
}
