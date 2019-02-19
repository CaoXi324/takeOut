import service.BestCharge;
import tools.Tools;

import java.lang.String;

public class Main {

    public static void main(String[] args) {
        Tools tools = new Tools();
        String[] orderArr = tools.getInput();
        BestCharge bestCharge = new BestCharge();
        String[] items = bestCharge.getItems(orderArr);
        int[] count = bestCharge.getCount(orderArr);
        int[] prices = bestCharge.getPrice(items);
        int[] itemPrice = bestCharge.getItemPrice(prices, count);
        int totalPrice = bestCharge.getTotalPrice(itemPrice);
        String[] ids = bestCharge.getId(items);
        int halfPrice = bestCharge.getHalfPrice(prices, count, ids);
        int minusPrice = bestCharge.getMinusPrice(totalPrice);
        String halfItems = bestCharge.getHalfItems(ids);
        String[] price = bestCharge.comparePrice(minusPrice, halfPrice, totalPrice, halfItems);
        tools.printOrder(items, count, itemPrice, price);

    }
}
