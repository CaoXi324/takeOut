package service;

import model.Allitems;
import model.Promotion;

import java.util.ArrayList;

public class BestCharge {
    Promotion promotion=new Promotion();
    Allitems allitems=new Allitems();
    String[][] ALLITEMS = allitems.getAllitems();
    String[][] PROMOTIONS = promotion.getPromation();
    public static String[] getItems(String[] orderArr) {
        int num = orderArr.length / 2;
        String[] items = new String[num];
        for (int i = 0; i < num; i++) {
            int j = i * 2;
            items[i] = orderArr[j];
        }
        return items;
    }

    public static int[] getCount(String[] orderArr) {
        int num = orderArr.length / 2;
        int[] count = new int[num];
        for (int i = 0; i < num; i++) {
            int j = i * 2 + 1;
            count[i] = Integer.valueOf(orderArr[j]);
        }
        return count;
    }

    public int[] getPrice(String[] items) {
        int[] prices = new int[items.length];
        for (int i = 0; i < items.length; i++) {
            for (int j = 0; j < 4; j++) {
                if (items[i].equals(ALLITEMS[j][1])) {
                    prices[i] = (int) Double.parseDouble(ALLITEMS[j][2]);
                }
            }
        }
        return prices;
    }

    public int[] getItemPrice(int[] prices, int[] count) {
        int[] itemPrice = new int[prices.length];
        for (int i = 0; i < prices.length; i++) {

            itemPrice[i] = prices[i] * count[i];
        }
        return itemPrice;
    }

    public int getTotalPrice(int[] itemPrice) {
        int totalPrice = 0;
        for (int i = 0; i < itemPrice.length; i++) {
            totalPrice += itemPrice[i];
        }
        return totalPrice;
    }

    public String[] getId(String[] items) {
        String[] ids = new String[items.length];
        for (int i = 0; i < items.length; i++) {
            for (int j = 0; j < 4; j++) {
                if (items[i].equals(ALLITEMS[j][1])) {
                    ids[i] = ALLITEMS[j][0];
                }
            }
        }
        return ids;
    }

    public int getHalfPrice(int[] prices, int[] count, String[] ids) {
        int itemPrice = 0;
        int halfPrice = 0;
        for (int i = 0; i < prices.length; i++) {
            itemPrice = prices[i] * count[i];
            for (int j = 1; j < 3; j++) {
                if (ids[i].equals(PROMOTIONS[1][j])) {
                    itemPrice /= 2;
                    break;
                }
            }
            halfPrice = halfPrice + itemPrice;
        }
        return halfPrice;
    }

    public int getMinusPrice(int totalPrice) {
        int minusPrice;
        if (totalPrice > 30) {
            minusPrice = totalPrice - 6;
        } else {
            minusPrice = totalPrice;
        }
        return minusPrice;
    }

    public String getHalfItems(String[] ids) {
        ArrayList<String> halfItems = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            for (int j = 1; j < 3; j++) {
                if (ids[i].equals(PROMOTIONS[1][j])) {
                    halfItems.add(PROMOTIONS[1][j]);
                }
            }
        }
        String[] halfItemsArr = (String[]) halfItems.toArray(new String[halfItems.size()]);
        String[] halfNames = new String[halfItemsArr.length];
        for (int i = 0; i < halfItemsArr.length; i++) {
            for (int j = 0; j < 4; j++) {
                if (halfItemsArr[i].equals(ALLITEMS[j][0])) {
                    halfNames[i] = ALLITEMS[j][1];
                    break;
                }
            }
        }
        StringBuffer halfItemsStr = new StringBuffer();
        halfItemsStr.append(halfNames[0]);
        for (int i = 1; i < halfNames.length; i++) {
            halfItemsStr.append("，" + halfNames[i]);
        }
        return halfItemsStr.toString();
    }

    public String[] comparePrice(int minusPrice, int halfPrice, int totalPrice, String halfItems) {
        int saveMoney = 6;
        String[] price = new String[3];
        if (minusPrice <= halfPrice) {
            price[0] = PROMOTIONS[0][0];
            price[1] = String.valueOf(minusPrice);
            price[2] = String.valueOf(saveMoney);
        } else {
            saveMoney = totalPrice - halfPrice;
            price[0] = PROMOTIONS[1][0] + "(" + halfItems + ")";
            price[1] = String.valueOf(halfPrice);
            price[2] = String.valueOf(saveMoney);
        }
        return price;
    }

}
