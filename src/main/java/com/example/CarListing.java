package com.example;


public class CarListing {
    private String model;
    private String year;
    private int quantity;

    public CarListing(String infoString){
        //set quantity
        quantity = Integer.parseInt(infoString.substring(infoString.length()-6, infoString.length()-5));

        //set year
        if(infoString.charAt(infoString.length()-18) == ' '){
            year = infoString.substring(infoString.length()-17, infoString.length()-8);
        }else{
            year = infoString.substring(infoString.length()-19, infoString.length()-8);
        }


        // set model(inc,exc)
        int startIndex = infoString.indexOf(">");
        model = infoString.substring(startIndex+1, infoString.length()-18);
    }

    public String getModel(){
        return model;
    }
    public String getYear(){
        return year;
    }
    public int getQuantity(){
        return quantity;
    }

    //print method
    public String toString() {
        return "Model: " + this.getModel() + "\nYear: " + this.getYear() + "\nStock: " + this.getQuantity() + "\n\n";
    }

}
