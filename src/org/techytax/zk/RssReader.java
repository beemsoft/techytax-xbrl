package org.techytax.zk;

import org.zkoss.bind.annotation.Init;
import org.zkoss.zrss.RssBinder;
import org.zkoss.zrss.RssEntry;
import org.zkoss.zrss.RssFeed;

public class RssReader {

	private RssFeed selected;
	private RssEntry selectEntry;

	@Init
	public void init() throws Exception {
		selected = new RssBinder().lookUpFeed("http://www.belastingdienst.nl/wps/wcm/connect/bldcontentnl/themaoverstijgend/nieuws/rss/nieuwsfeed_actueel_zakelijk.xml");
		selectEntry = selected.getFeedEntries().get(0);
	}

	public RssFeed getSelectedFeed() {
		return selected;
	}

	public RssEntry getSelectedEntry() {
		return selectEntry;
	}

	public void setSelectedEntry(RssEntry selectedEntry) {
		this.selectEntry = selectedEntry;
	}
}
