package com.softserveinc.softtour.parsers.impl;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.softserveinc.softtour.dto.TrainTransit;

public class TrainParser {
	private String url = "http://ticket.turistua.com/ua/train/reservation/?dt=2014-10-18&src=22200001&dst=22218000&transport=train";
	
	private TrainTransit trainTransit;
	private ArrayList<TrainTransit> schedule = new ArrayList<TrainTransit>();
	
	
	public static void main(String[] args) {
		TrainParser obj = new TrainParser();
		obj.parse();
	}

	private void parse() {
		try {
			String doc = Jsoup.connect(url).timeout(5000)
					.ignoreContentType(true).execute().body();
			Document document = Jsoup.parse(doc);

			Element trainsTable = document.getElementsByTag("body").get(0)
					.getElementById("turistua_site")
					.getElementById("content")
					.getElementsByClass("reservationCol").get(0)
					.getElementsByClass("ticketsBox").get(0)
					.getElementById("t2t_tableBox")
					.getElementsByTag("table").get(0);
			
			Elements trains = trainsTable.select("tr[class]");

			for (Element train : trains) {

				Elements td = train.getElementsByTag("td");

				String departureCity = null;
				String arrivalCity = null;
				String departureTime = null;
				String arrivalTime = null;
				
				String priceLux = null;
				String priceCoupeFirm = null;
				String priceCoupe = null;
				String priceBerthFirm = null;
				String priceBerth = null;
				String priceSitting = null;
				
				for (int i = 0; i < td.size(); i++) {
					int n = 1;
					departureCity = td.get(n).text();
					arrivalCity = td.get(++n).text();
					departureTime = td.get(++n).text();
					arrivalTime = td.get(n = n + 2).text();
					
					priceLux = setPrice(td, ++n);
					priceCoupeFirm = setPrice(td, ++n);
					priceCoupe = setPrice(td, ++n);
					priceBerthFirm = setPrice(td, ++n);
					priceBerth = setPrice(td, ++n);
					priceSitting = setPrice(td, ++n);
				
				}
				
				//FIXME min price
					String priceFrom = null;
					if (priceSitting != null) {
						priceFrom = priceSitting;
					} else if (priceBerth != null) {
						priceFrom = priceBerth;
					} else if (priceBerthFirm != null) {
						priceFrom = priceBerthFirm;
					} else if (priceCoupe != null) {
						priceFrom = priceCoupe;
					} else if (priceCoupeFirm != null) {
						priceFrom = priceCoupeFirm;
					} else if (priceLux != null) {
						priceFrom = priceLux;
					}
					
				// FIXME max price
					String priceTo = null;
					if (priceLux != null) {
						priceTo = priceLux;
					} else if (priceCoupeFirm != null) {
						priceTo = priceCoupeFirm;
					} else if (priceCoupe != null) {
						priceTo = priceCoupe;
					} else if (priceBerthFirm != null) {
						priceTo = priceBerthFirm;
					} else if (priceBerth != null) {
						priceTo = priceBerth;
					} else if (priceSitting != null) {
						priceTo = priceSitting;
					} 
					
				    trainTransit = new TrainTransit(departureCity, arrivalCity, departureTime, arrivalTime, priceFrom, priceTo);
				    System.out.println(trainTransit);
				    schedule.add(trainTransit);
			}

		} catch (IOException e) {
			e.printStackTrace();
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

}
