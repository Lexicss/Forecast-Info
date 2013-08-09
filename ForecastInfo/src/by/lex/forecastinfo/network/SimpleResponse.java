package by.lex.forecastinfo.network;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)

/*
 * @JsonIgnoreProperties(ignoreUnknown = true)
public class Sponsor extends ResponseObject{
	private long sponsorId;
	private String sponsorName;
	private String logoURL;
	
	public long getSponsorId() {
		return sponsorId;
	}
	public void setSponsorId(long sponsorId) {
		this.sponsorId = sponsorId;
	}
	public String getSponsorName() {
		return sponsorName;
	}
	public void setSponsorName(String sponsorName) {
		this.sponsorName = sponsorName;
	}
	public String getLogoURL() {
		return logoURL;
	}
	public void setLogoURL(String logoURL) {
		this.logoURL = logoURL;
	}	
}
 */

public class SimpleResponse extends ResponseObject {
	private String timeZone;
	private int offset;
	public String getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	

}
