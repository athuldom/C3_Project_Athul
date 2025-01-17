import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {
        int value1 = closingTime.compareTo(getCurrentTime());
        int value2 = openingTime.compareTo(getCurrentTime());
        return value1 > 0 && value2 < 0;
    }

    public LocalTime getCurrentTime(){ return  LocalTime.now(); }

    public List<Item> getMenu() {
        return menu;
    }

    private Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    public String getName() {
        return name;
    }

    //    method for calculating the total of selected items
    public int findTotalOfItems(List<String> items){
        List<Item> totalItems=new ArrayList<>();
        int total=0;
        for(String item: items){
            for (Item menus: getMenu()){
                if(item.equals(menus.getName())){
                    totalItems.add(menus);
                }
            }
        }
        if(totalItems.size()>0){
            for (Item selectedItem:totalItems){
                String priceString = selectedItem.toString().split(":")[1];
                String price = String.valueOf(priceString.split("[^a-z0-9]")[0]);
                total += Integer.parseInt(price);
            }
        }
        return total;
    }
}
