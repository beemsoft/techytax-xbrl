package org.techytax.zk.rss;

import org.zkoss.zrss.RssBinder;
import org.zkoss.zrss.RssEntry;
import org.zkoss.zrss.RssFeed;

public class RssReader {

	private RssFeed selected;
	private RssEntry selectEntry;
	protected String feedUrl;

	protected void init() throws Exception {
		selected = new RssBinder().lookUpFeed(feedUrl);
		if (selected.getFeedEntries().size() > 0) {
			selectEntry = selected.getFeedEntries().get(0);
		}
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
