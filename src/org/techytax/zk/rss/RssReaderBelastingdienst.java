package org.techytax.zk.rss;

import org.zkoss.bind.annotation.Init;

public class RssReaderBelastingdienst extends RssReader {

	@Init
	public void init() throws Exception {
		feedUrl = "http://www.belastingdienst.nl/wps/wcm/connect/bldcontentnl/themaoverstijgend/nieuws/rss/nieuwsfeed_actueel_zakelijk.xml";
		super.init();
	}

}
