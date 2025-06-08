package org.example.opentable;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* Entity
* Coffee
* TypeofCoffee
* AdOns
* getPrice()
*
* */
public class Coffee {
    TypeOfCoffee coffeeType;
    List<AdOns> adonsList;
    UserState userState;

    Map<TypeOfCoffee, Integer> coffeePrice;
    Map<AdOns, Integer> adOnsPrice;

    int price = 0;


    public Coffee(){
        populatePrice();
        userState = UserState.NONE;
    }

    public int getPrice(TypeOfCoffee coffeeType, List<AdOns> adonsList){

        int price = 0;
        price += coffeePrice.get(coffeeType);

        for(AdOns adon : adonsList){
            price += adOnsPrice.get(adon);
        }
        setPrice(price);
        userState = UserState.SELECTION;
        return price;
    }


    public int getCoffee(int userCash){
        int cashReturn = 0;
        if(UserState.SELECTION == userState){
            int totalPrice = getPrice();

            if(userCash > totalPrice){
                cashReturn = userCash - totalPrice;
            }
        }
        userState = UserState.COFFEE_DES;
        return cashReturn;
    }

    public void populatePrice(){
        coffeePrice = new HashMap<>();
        adOnsPrice = new HashMap<>();
        coffeePrice.put(TypeOfCoffee.BLACK_COFFEE, 20);
        coffeePrice.put(TypeOfCoffee.LATTE, 50);
        adOnsPrice.put(AdOns.CREAM, 20);
        adOnsPrice.put(AdOns.SUGAR, 10);
    }

    public void setPrice(int price){
        this.price = price;
    }

    public int getPrice(){
        return this.price;
    }
}
