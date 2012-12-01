package org.techytax.zk.rss;

import org.zkoss.bind.annotation.Init;

public class RssReaderTechyTax extends RssReader {

	@Init
	public void init() throws Exception {
		feedUrl = "http://www.techytax.org/jforum/rss/forumTopics/1.page";
		super.init();
	}

}
