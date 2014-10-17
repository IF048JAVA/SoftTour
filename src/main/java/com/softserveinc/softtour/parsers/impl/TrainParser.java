package com.softserveinc.softtour.parsers.impl;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.softserveinc.softtour.dto.TrainTransit;
import com.softserveinc.softtour.util.CreatorTrainUrl;

public class TrainParser {

	String id;
	String departureCity;
	String arrivalCity;
	String departureTime;
	String arrivalTime;
	String priceMax;
	String priceMin;
	
	private String departureDate;
	
	private TrainTransit trainTransit;
	private ArrayList<TrainTransit> routsList = new ArrayList<TrainTransit>();
	
	private String url;
	
	public TrainParser(String departureCity, String arrivalCity, String departureDate) {
		this.departureDate = departureDate;
		this.url = CreatorTrainUrl.createUrl(departureCity, arrivalCity, departureDate); 
	}
	
	private Document document;
	
	public ArrayList<TrainTransit> getRouts() {
		parse();
		setRoutes();
		
		return routsList; 
	}
	
	public void parse() {
		try {
			String htmlDocument = Jsoup.connect(url)
										.timeout(5000)
										.ignoreContentType(true)
										.execute()
										.body();
			
			document = Jsoup.parse(htmlDocument);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	private void setRoutes(){
		Element routesTable = document.getElementsByTag("body").get(0)
				.getElementById("turistua_site")
				.getElementById("content")
				.getElementsByClass("reservationCol").get(0)
				.getElementsByClass("ticketsBox").get(0)
				.getElementById("t2t_tableBox")
				.getElementsByTag("table").get(0);
			
		Elements routes = routesTable.select("tr[class]");
		setRoute(routes);
	}
	
	private void setRoute(Elements routes) {
		for (Element route : routes) {
			Elements info = route.getElementsByTag("td");
			setInfo(info);
			
			addRoute();
				    System.out.println(trainTransit);
				    routsList.add(trainTransit);
		}
		
	}

	private void addRoute() {
		routsList.add(new TrainTransit(id, departureCity, arrivalCity, departureDate, departureTime, arrivalTime, priceMin, priceMax));
		
		
	}

	private void setInfo(Elements info) {
		
		
		//FIXME maybe need to change digit 6 to same variables !
		// from priceLux to priceSitting  
		String[] prices = new String[6];
		
		for (int i = 0; i < info.size(); i++) {
			int n = 0;
			
			id = info.get(n).text();
			departureCity = info.get(++n).text();
			arrivalCity = info.get(++n).text();
			departureTime = info.get(++n).text();
			arrivalTime = info.get(n = n + 2).text();
			
			// Sets prices massif from max to min proce
			for (int j = 0; j < prices.length; j++) {
				prices[j] = setPrice(info, ++n);
			}
		}
		
		
		for (int i = 0; i < prices.length; i++) {
			if (prices[i] != null) {
				priceMax = prices[i];
			}
		}
		
		
		for (int i = prices.length; i < 0 ; i--) {
			if (prices[i] != null) {
				priceMin = prices[i];
			}
		}
	}

	private String setPrice(Elements td, int n) {
		String price = null;
		Elements priceElement = td.get(n).getElementsByTag("span");
		if (!priceElement.isEmpty()) {
			price = priceElement.get(0).text();
		}
		return price;
	}
	
	public static void main(String[] args) {
	//	TrainParser obj = new TrainParser(departureCity, arrivalCity, departureTime)
//		obj.parse();
	}


}
