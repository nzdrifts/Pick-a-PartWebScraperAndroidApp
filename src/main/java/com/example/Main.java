package com.example;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.rmi.ssl.SslRMIClientSocketFactory;


public class Main {

    public static String[] getCarsList(){
        String car = "";
        ArrayList<String> array = new ArrayList<String>();
        Scanner scanner = new Scanner(System.in);

        while (!car.equals("done")) {
            car = scanner.nextLine();
            array.add(car);
        }
        String[] arr = new String[array.size()];
        arr = array.toArray(arr);
        return arr;
    }
    private static void lookForCars(String[] carList, String html){
        Document doc = Jsoup.parse(html);
        Elements links = doc.select("a[href]");

        for (Element link : links) {
            for (String car : carList) {
                if (String.valueOf(link).contains(car)) {
                    //System.out.println(link);
                    CarListing x = new CarListing(String.valueOf(link));

                    System.out.println(x);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String url = "https://www.pickapart.co.nz/Avondale-Stock";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection(); 
        
        // optional request header 
        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        // response code of 200 means connection successful
        int responseCode = con.getResponseCode(); 
        System.out.println("Enter car models, type \"done\" when finished");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream())); 
        String inputLine; 
        StringBuilder response = new StringBuilder(); 
        while ((inputLine = in.readLine()) != null) { 
            response.append(inputLine); 
        } 
        in.close(); 
        String html = response.toString();


        //get input from user and populate array with car models to search for
        String[] carArray = getCarsList();

        //prints available information on specified cars
        lookForCars(carArray, html);
    }
}