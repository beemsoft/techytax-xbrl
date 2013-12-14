package org.techytax.zk.rss;

import org.zkoss.bind.annotation.Init;

public class RssReaderTechyTax extends RssReader {

	private static final long serialVersionUID = 7418782346322636602L;
	
	private String imageLink;

	@Init
	public void init() {
		feedUrl = "http://sourceforge.net/p/techytax/code/feed";
		imageLink = "/images/sf-footer-logo.png";
		super.init();
	}

	public String getImageLink() {
		return imageLink;
	}

}
