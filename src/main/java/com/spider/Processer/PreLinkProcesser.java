package com.spider.Processer;

import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;



public class PreLinkProcesser implements PageProcessor {

	private Site site = Site.me().addStartUrl("http://beijing.55tuan.com");
	private String pagePrefix = "http://beijing.55tuan.com/page";
	private int currentPage = 1;
	private int endPage = 2;

	
	public void process(Page page) {
		
		Selectable select = page.getHtml().xpath("//div[@class='con-boxIndex clearfix']/ul/li/h2/a/@href");
		List<String> ls = select.all();
		page.putField("result", ls);
		
		currentPage++;
		if(currentPage <= endPage){
			String nextPage = pagePrefix + currentPage;
			page.addTargetRequest(nextPage);
		}
		

	}

	public Site getSite() {
		return site;
	}

}
