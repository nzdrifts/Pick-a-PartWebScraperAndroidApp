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
    public static Elements getLink(String url){
        try {
            Document doc = Jsoup.connect(url).get();
            return doc.select("a[href]");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static CarListing lookForCars(String[] carList, Elements links){

        for (Element link : links) {
            for (String car : carList) {
                if (String.valueOf(link).contains(car)) {
                    CarListing x = new CarListing(String.valueOf(link));
                    return x;
                }
            }
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Enter car models, type \"done\" when finished");

        //set the cars to search for (in future will be input from user)
        String[] carList = new String[]{"Murano"};

        //get the html of the website as a string (in future should only be done once)
        //String htmlString = getHTMLString("https://www.pickapart.co.nz/Avondale-Stock");


        Elements links = getLink("https://www.pickapart.co.nz/Avondale-Stock");

        //lookForCars(carList, links);

        CarListing x = lookForCars(carList, links);
        System.out.println(x);
    }
}