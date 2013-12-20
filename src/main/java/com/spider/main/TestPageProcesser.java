package com.spider.main;

import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ResultItemsCollectorPipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import com.spider.Processer.PreLinkProcesser;

public class TestPageProcesser implements PageProcessor {

	private Site site = Site.me().addStartUrl("http://beijing.55tuan.com");
	private int startPage = 1;
	private int endPage = 2;
	
	public Site getSite() {
		return site;
	}

	public void process(Page page) {
		
		Html html = page.getHtml();
		//Selectable select = html.xpath("//div[@class='con-boxIndex clearfix']/ul[@class=['goods-allInd clearfix']/li[@class='good-list']/h2/a/text()");
		Selectable select = html.xpath("//div[@class='con-boxIndex clearfix']/ul/li/h2/a/@href");
		List<String> ls = select.all().subList(0, 2);
		page.putField("goodsNames", ls);
		ResultItems r = page.getResultItems();
		
	}
	
	public static void main(String[] args){
		
		ResultItemsCollectorPipeline pipline = new ResultItemsCollectorPipeline();
		Spider.create(new PreLinkProcesser()).addPipeline(pipline).run();
		
		System.out.println("OK");
	}

}
