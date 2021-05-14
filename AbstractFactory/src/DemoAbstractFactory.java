import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

interface Chair{
    public void sitOn();
    public void hasLegs();
}

interface CoffeeTable{
    public void useToDrinkCoffee();
    public void hasStyle();
}

interface Sofa{
    public void relaxOn();
    public void hasDesign();
}

class ModernChair implements Chair{

    public ModernChair() {
    }

    @Override
    public void sitOn() {
        System.out.println("Sit on modern chair");
    }

    @Override
    public void hasLegs() {
        System.out.println("chair has modern style legs");
    }
}

class ArtDecoChair implements Chair{

    public ArtDecoChair() {

    }

    @Override
    public void sitOn() {
        System.out.println("Sit on Art Deco chair");
    }

    @Override
    public void hasLegs() {
        System.out.println("chair has art deco style legs");
    }
}

class ModernSofa implements Sofa{

    @Override
    public void relaxOn() {
        System.out.println("Relax on modern sofa");
    }

    @Override
    public void hasDesign() {
        System.out.println("Sofa has modern style");
    }
}

class ArtDecoSofa implements Sofa{

    @Override
    public void relaxOn() {
        System.out.println("Relax on Art Deco Style Sofa");
    }

    @Override
    public void hasDesign() {
        System.out.println("Sofa has Art Deco style");
    }
}

class ModernCoffeeTable implements CoffeeTable{

    @Override
    public void useToDrinkCoffee() {
        System.out.println("Drinking coffee at modern style Coffee Table");
    }

    @Override
    public void hasStyle() {
        System.out.println("Coffee Table has modern style");
    }
}

class ArtDecoCoffeeTable implements CoffeeTable{

    @Override
    public void useToDrinkCoffee() {
        System.out.println("Drinking coffee at Art Deco style Coffee Table");
    }

    @Override
    public void hasStyle() {
        System.out.println("Coffee Table has art deco style");
    }
}
interface FurnitureFactory{
    public Chair createChair();
    public Sofa createSofa();
    public CoffeeTable createCoffeeTable();
}

class ModernFurnitureFactory implements FurnitureFactory{

    @Override
    public Chair createChair() {
        return new ModernChair();
    }

    @Override
    public Sofa createSofa() {
        return new ModernSofa();
    }

    @Override
    public CoffeeTable createCoffeeTable() {
        return new ModernCoffeeTable();
    }
}

class ArtDecoFurnitureFactory implements FurnitureFactory{

    @Override
    public Chair createChair() {
        return new ArtDecoChair();
    }

    @Override
    public Sofa createSofa() {
        return new ArtDecoSofa();
    }

    @Override
    public CoffeeTable createCoffeeTable() {
        return new ArtDecoCoffeeTable();
    }
}
enum Style{
    Modern, ArtDeco;
}
class FurnitureOrderReceiver{
    Map<Style, FurnitureFactory> factories = new HashMap<>();
    Map<String, Style> clients = new HashMap<>();

    public FurnitureOrderReceiver() {
        this.factories.put(Style.Modern, new ModernFurnitureFactory());
        this.factories.put(Style.ArtDeco, new ArtDecoFurnitureFactory());
    }

    public void startOrder(String user, Style style){
        clients.put(user, style);
    }

    private FurnitureFactory getFactory(String user){
        return factories.get(clients.getOrDefault(user, Style.Modern));
    }

    public Chair orderChair(String user){
        FurnitureFactory furnitureFactory = getFactory(user);
        return furnitureFactory.createChair();
    }

    public Sofa orderSofa(String user){
        FurnitureFactory furnitureFactory = getFactory(user);
        return furnitureFactory.createSofa();
    }

    public CoffeeTable orderCoffeeTable(String user){
        FurnitureFactory furnitureFactory = getFactory(user);
        return furnitureFactory.createCoffeeTable();
    }
}
public class DemoAbstractFactory {

    public static void main(String[] args) {
        FurnitureOrderReceiver orderReceiver = new FurnitureOrderReceiver();
        List<Chair> chairs = new ArrayList<>();
        List<Sofa> sofas = new ArrayList<>();
        List<CoffeeTable> coffeeTables = new ArrayList<>();
        orderReceiver.startOrder("Ajay", Style.Modern);
        chairs.add(orderReceiver.orderChair("Ajay"));
        sofas.add(orderReceiver.orderSofa("Ajay"));
        coffeeTables.add(orderReceiver.orderCoffeeTable("Ajay"));
        for (Chair chair : chairs) {
            chair.sitOn();
        }

        for (Sofa sofa : sofas) {
            sofa.relaxOn();
        }
        for (CoffeeTable coffeeTable : coffeeTables) {
            coffeeTable.useToDrinkCoffee();
        }
    }
}
