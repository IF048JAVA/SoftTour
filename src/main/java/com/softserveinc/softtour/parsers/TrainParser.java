package com.softserveinc.softtour.parsers;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.softserveinc.softtour.bean.TrainRoute;
import com.softserveinc.softtour.util.CreatorTrainUrl;
import com.softserveinc.softtour.util.DateValidator;

public class TrainParser {
	private static final int MAX_NUMER_OF_RETRYING = 15;
	private static final int MILLISECONDS = 5000; 
	
	private String url;
	private TrainRoute trainRoute;
	private ArrayList<TrainRoute> routesList;
	private CreatorTrainUrl creatorTrainUrl;
	private DateValidator dateValidator;
	
	private String departureTime;
	
	public TrainParser(String departureCity, String arrivalCity, String departureDate, String departureTime) {
		creatorTrainUrl = new CreatorTrainUrl();
		url = creatorTrainUrl.createUrl(departureCity, arrivalCity, departureDate);
		
		trainRoute = new TrainRoute();
		trainRoute.setDepartureDate(departureDate);
		this.departureTime = departureTime;
		
		routesList = new ArrayList<TrainRoute>();
		dateValidator = new DateValidator();
	}
	
	public TrainParser() {
	}
	
	public ArrayList<TrainRoute>  getRoutes() {
		parse();
		//FIXME Del ...
		System.out.println();
		
		trainRoute.setDepartureDate(dateValidator.setPreviousDate(trainRoute, departureTime));
		parse();
		
		return routesList;
	}
	
	/**
	 * Parses the http://ticket.turistua.com/ site
	 * 
	 * @return the list of the routes
	 */
	public void parse() {
		Document document = null;
		
		int i = 0;
		while (i < MAX_NUMER_OF_RETRYING) {
			++i;
			try {
				document = Jsoup.connect(url).timeout(MILLISECONDS).get();

			} catch (IOException e) {
				// TODO LOG
				System.out.println("Ivalid attempt to connect. Trying one more ...");
				continue;
			}
			break;
		}

		parseRoutes(document);
	}
		
	/**
	 * Parses the routes with the given document
	 * @param document - it's document, which will be parsed
	 */
	private void parseRoutes(Document document){
		Element routesTable = document.getElementsByTag("body").get(0)
				.getElementById("turistua_site")
				.getElementById("content")
				.getElementsByClass("reservationCol").get(0)
				.getElementsByClass("ticketsBox").get(0)
				.getElementById("t2t_tableBox")
				.getElementById("t2t_tables")
				.getElementsByTag("tbody").get(0);
			
		Elements routes = routesTable.select("tr[class]");
		parseRoute(routes);
	}
	
	/**
	 * Parses the route with the given routes
	 * @param routes - it's routes, which will be parsed
	 */
	private void parseRoute(Elements routes) {
		for (Element route : routes) {
			Elements unitsRoute = route.getElementsByTag("td");
			
			parseUnitRoute(unitsRoute);
			
			if (dateValidator.validate(trainRoute, departureTime)) {
				addRoute();
			}
		}
	}

	/**
	 * Parses the unit of the route with the given units 
	 * @param unitsRoute - it's units of the one route, which will be parsed
	 */
	private void parseUnitRoute(Elements unitsRoute) {
			int unitNumber = 0;
			//TODO Refactoring
			trainRoute.setId(unitsRoute.get(unitNumber).text());
			trainRoute.setDepartureCity(unitsRoute.get(++unitNumber).text());
			trainRoute.setArrivalCity(unitsRoute.get(++unitNumber).text());
			trainRoute.setDepartureTime(unitsRoute.get(++unitNumber).text());
			trainRoute.setOnWayTime(unitsRoute.get(++unitNumber).text());
			trainRoute.setArrivalTime(unitsRoute.get(++unitNumber).text());
			
			parsePrices(unitsRoute, unitNumber);
	}

	/**
	 * Parses the prices of the route with the given unitsRoute 
	 * @param unitsRoute - it's units of the one route, which will be parsed
	 * @param unitNumber - it's a number of the unit route
	 */
	private void parsePrices(Elements  unitsRoute, int unitNumber) {
		int unitsSize = unitsRoute.size();
		String[] pricesRoute = new String[unitsSize-(unitNumber+1)];
		
		for (int j = 0; j < pricesRoute.length; j++) {
			Element unitRoutePrise = unitsRoute.get(++unitNumber);
			pricesRoute[j] = parsePrice(unitRoutePrise);
		}
		
		boolean isMaxSet = false;
		for (int i = 0; i < pricesRoute.length; i++) {
			if (pricesRoute[i] == null) {
				continue;
			}
			if (!isMaxSet) {
				trainRoute.setPriceMax(pricesRoute[i]);
				isMaxSet = true;
			}
			trainRoute.setPriceMin(pricesRoute[i]);
		}
	}
	
	/**
	 * Parses the price of the route with the given unitRoutePrise
	 * @param unitRoutePrise - it's unit of the one route, which described the price and which will be parsed
	 * @return the price of the route
	 */
	private String parsePrice(Element unitRoutePrise){
		String price = null;
		Elements unitsPrice = unitRoutePrise.getElementsByTag("span");
		if (!unitsPrice.isEmpty()){
			price = unitsPrice.get(0).text();
		}
		return price;
	}
	
	/**
	 * Adds route to the list of the routes
	 */
	private void addRoute() {
		routesList.add(trainRoute);
		System.out.println(trainRoute);
	}

	/**
	 * Only for testing
	 */
	public static void main(String[] args) {
		TrainParser obj = new TrainParser("Київ", "Львів", "2014-11-03", "21:00");
		obj.getRoutes();
	}
}