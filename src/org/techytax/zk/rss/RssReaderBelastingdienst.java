package org.techytax.zk.rss;

import org.zkoss.bind.annotation.Init;

public class RssReaderBelastingdienst extends RssReader {

	private static final long serialVersionUID = 4683057507461954305L;

	@Init
	public void init() throws Exception {
		feedUrl = "http://www.belastingdienst.nl/wps/wcm/connect/bldcontentnl/themaoverstijgend/nieuws/rss/nieuwsfeed_actueel_zakelijk.xml";
		super.init();
	}

}
