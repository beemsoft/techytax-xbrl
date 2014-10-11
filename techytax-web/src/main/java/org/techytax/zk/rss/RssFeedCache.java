/**
 * Copyright 2014 Hans Beemsterboer
 * 
 * This file is part of the TechyTax program.
 *
 * TechyTax is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * TechyTax is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with TechyTax; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package org.techytax.zk.rss;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.zkoss.zrss.RssBinder;
import org.zkoss.zrss.RssFeed;

public class RssFeedCache {
	
	private static Map<String, RssFeed> rssFeedCache;
	
	static {
		rssFeedCache = new HashMap<>();
		Timer feedCheckerTimer = new Timer(true);
		feedCheckerTimer.scheduleAtFixedRate(
		    new TimerTask() {
		      public void run() { updateCache(); }
		    }, 0, 60 * 60 * 1000 * 24);
		
	}
	
	static void updateCache() {
		for (String feedUrl : rssFeedCache.keySet() ) {
			getFeed(feedUrl);
		}
	}
	
	static RssFeed getFeed(String feedUrl) {
		System.out.println("Get feed: " + feedUrl);
		RssFeed rssFeed = rssFeedCache.get(feedUrl);
		if (rssFeed != null) {
			return rssFeed;
		} else {
			try {
				rssFeed = new RssBinder().lookUpFeed(feedUrl);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (rssFeed != null) {
				rssFeedCache.put(feedUrl, rssFeed);
			}
		}
		return rssFeed;
		
	}

}
