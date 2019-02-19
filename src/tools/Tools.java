package tools;

import java.util.Scanner;

public class Tools {
    public String[] getInput() {
        System.out.println("请输入菜品和数量，并以逗号分隔");
        Scanner scan = new Scanner(System.in);
        String order = scan.nextLine();
        String[] orderArr = order.split("，");
        return orderArr;

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
