import java.util.ArrayList;
import java.util.Scanner;
import java.lang.String;

public class TakeOut {
    static final String[][] ALLITEMS = {{"ITEM0001", "黄焖鸡", "18.00"},
            {"ITEM0013", "肉夹馍", "6.00"},
            {"ITEM0022", "凉皮", "8.00"},
            {"ITEM0030", "冰峰", "2.00"}};
    static final String[][] PROMOTIONS = {{"满30减6元"}, {"指定菜品半价", "ITEM0001", "ITEM0022"}};

    public static void main(String[] args) {
        TakeOut takeOut = new TakeOut();
        System.out.println("请输入菜品和数量，并以逗号分隔");
        Scanner scan = new Scanner(System.in);
        String order = scan.nextLine();
        String[] orderArr = order.split("，");
        takeOut.bestCharge(orderArr);
    }

    public void bestCharge(String[] orderArr) {
        String[] items = getItems(orderArr);
        int[] count = getCount(orderArr);
        int[] prices = getPrice(items);
        int[] itemPrice = getItemPrice(prices, count);
        int totalPrice = getTotalPrice(itemPrice);
        String[] ids = getId(items);
        int halfPrice = getHalfPrice(prices, count, ids);
        int minusPrice = getMinusPrice(totalPrice);
        String halfItems = getHalfItems(ids);
        String[] price = comparePrice(minusPrice, halfPrice, totalPrice, halfItems);
        printOrder(items, count, itemPrice, price);
    }

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

    public static int[] getPrice(String[] items) {
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

    public static int[] getItemPrice(int[] prices, int[] count) {
        int[] itemPrice = new int[prices.length];
        for (int i = 0; i < prices.length; i++) {

            itemPrice[i] = prices[i] * count[i];
        }
        return itemPrice;
    }

    public static int getTotalPrice(int[] itemPrice) {
        int totalPrice = 0;
        for (int i = 0; i < itemPrice.length; i++) {
            totalPrice += itemPrice[i];
        }
        return totalPrice;
    }

    public static String[] getId(String[] items) {
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

    public static int getHalfPrice(int[] prices, int[] count, String[] ids) {
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

    public static int getMinusPrice(int totalPrice) {
        int minusPrice;
        if (totalPrice > 30) {
            minusPrice = totalPrice - 6;
        } else {
            minusPrice = totalPrice;
        }
        return minusPrice;
    }

    public static String getHalfItems(String[] ids) {
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

    public static String[] comparePrice(int minusPrice, int halfPrice, int totalPrice, String halfItems) {
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

    public static void printOrder(String[] items, int[] count, int[] itemPrice, String[] price) {
        System.out.println("------订餐明细------");
        for (int i = 0; i < items.length; i++) {
            System.out.println(items[i] + "×" + count[i] + "=" + itemPrice[i] + "元");
        }
        System.out.println("------------------");
        System.out.println("使用优惠：");
        System.out.println(price[0] + "，省" + price[2] + "元");
        System.out.println("------------------");
        System.out.println("总计：" + price[1] + "元");
    }
}
