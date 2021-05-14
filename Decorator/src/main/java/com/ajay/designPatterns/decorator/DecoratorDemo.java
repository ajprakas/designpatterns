package com.ajay.designPatterns.decorator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

interface Pizza {
   public int getBill();
}

class BasePizza implements Pizza {

    @Override
    public int getBill() {
        return 10;
    }
}

class VeggiePizza implements Pizza{

    @Override
    public int getBill() {
        return 15;
    }
}

interface Topping {
    public int getPrice();
}

class TomatoTopping implements Topping {

    @Override
    public int getPrice() {
        return 1;
    }
}

class CheeseTopping implements Topping {

    @Override
    public int getPrice() {
        return 2;
    }
}

class CapcicumTopping implements Topping {

    @Override
    public int getPrice() {
        return 3;
    }
}
interface Decorator{
    public int getTotalBill();
    public void addTopping(Topping topping);
}

class BasePizzaDecorator implements Decorator{
    BasePizza pizza;
    List<Topping> toppingList;

    public BasePizzaDecorator() {
        this.toppingList = new ArrayList<>();
        pizza = new BasePizza();
    }

    @Override
    public int getTotalBill() {
        int result = pizza.getBill();
        for (Topping topping : this.toppingList){
            result+= topping.getPrice();
        }

        return result;
    }

    @Override
    public void addTopping(Topping topping) {
        toppingList.add(topping);
    }
}

class VeggiePizzaDecorator implements Decorator{
    VeggiePizza veggiePizza;
    List<Topping> toppingList;

    public VeggiePizzaDecorator() {
        this.veggiePizza = new VeggiePizza();
        this.toppingList = new ArrayList<>();
    }

    @Override
    public int getTotalBill() {
        int result = veggiePizza.getBill();
        for (Topping topping : toppingList){
            result += topping.getPrice();
        }
        return result;
    }

    @Override
    public void addTopping(Topping topping) {
        toppingList.add(topping);
    }
}

enum PizzaType{
    Base, Veggie;
}

enum ToppingType{
    Tomato, Cheese, Capcicum;
}

class OrderReceiver{
    DecoratorFactory decoratorFactory;
    ToppingFactory toppingFactory;
    Decorator decorator;

    public OrderReceiver() {
        decoratorFactory = new DecoratorFactory();
        toppingFactory = new ToppingFactory();
    }

    public void takeOrder(PizzaType type){
        System.out.println("start taking order for "+type);
        decorator = decoratorFactory.getDecorator(type);
    }

    public void addExtra(ToppingType toppingType){
        System.out.println("Adding toppings "+toppingType.toString());
        Topping topping = toppingFactory.getToppings(toppingType);
        decorator.addTopping(topping);
    }

    public int getTotalBill(){
        System.out.println("Generating bill.....");
        return decorator.getTotalBill();
    }
}

class DecoratorFactory{
    Map<PizzaType, Decorator> decorators = new HashMap<>();
    public DecoratorFactory() {
        decorators.put(PizzaType.Base, new BasePizzaDecorator());
        decorators.put(PizzaType.Veggie, new VeggiePizzaDecorator());
    }

    public Decorator getDecorator(PizzaType type){
        return decorators.get(type);
    }
}

class ToppingFactory{
    Map<ToppingType, Topping> toppingsMap = new HashMap<>();

    public ToppingFactory() {
        toppingsMap.put(ToppingType.Tomato, new TomatoTopping());
        toppingsMap.put(ToppingType.Cheese, new CheeseTopping());
        toppingsMap.put(ToppingType.Capcicum, new CapcicumTopping());
    }

    public Topping getToppings(ToppingType toppingType){
        return toppingsMap.get(toppingType);
    }
}

public class DecoratorDemo {
    public static void main(String[] args) {
        OrderReceiver orderReceiver = new OrderReceiver();
        orderReceiver.takeOrder(PizzaType.Base);
        orderReceiver.addExtra(ToppingType.Tomato);
        orderReceiver.addExtra(ToppingType.Cheese);
        orderReceiver.addExtra(ToppingType.Cheese);
        System.out.println("total bill ="+orderReceiver.getTotalBill());
        orderReceiver.takeOrder(PizzaType.Veggie);
        orderReceiver.addExtra(ToppingType.Tomato);
        orderReceiver.addExtra(ToppingType.Cheese);
        System.out.println("total bill ="+orderReceiver.getTotalBill());
    }
}
