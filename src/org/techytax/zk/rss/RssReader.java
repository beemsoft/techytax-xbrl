package org.techytax.zk.rss;

import java.io.Serializable;

import org.zkoss.zrss.RssBinder;
import org.zkoss.zrss.RssEntry;
import org.zkoss.zrss.RssFeed;

public class RssReader implements Serializable {

	private static final long serialVersionUID = -5208262237527059554L;
	
	private RssFeed selected;
	private RssEntry selectEntry;
	protected String feedUrl;

	protected void init() {
		try {
			selected = new RssBinder().lookUpFeed(feedUrl);
			if (selected.getFeedEntries().size() > 0) {
				selectEntry = selected.getFeedEntries().get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
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
