package arrays;

import java.util.Arrays;

/*
    You are given an array of prices where prices[i] is the price of a given stock on an ith day.
    You want to maximize your profit by choosing a single day to buy one stock and
    choosing a different day in the future to sell that stock. Return the maximum
    profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
 */
public class StockBuySell {
    public static void main(String[] args) {
        StockBuySell obj = new StockBuySell();
        int[] stocks = new int[] { 7, 1, 5, 3, 6, 4 };
        obj.approach1(stocks);
        obj.approach2(stocks);
    }
    /*
        Time - O(N * N)
        Space - O(1)
     */
    private void approach1(int[] stocks) {
        int maxProfit = Integer.MIN_VALUE;
        int n = stocks.length;
        for(int buyingDay = 0; buyingDay < n - 1; buyingDay++) {
            for(int sellingDay = buyingDay + 1; sellingDay < n; sellingDay++) {
                maxProfit = Math.max(maxProfit, stocks[sellingDay] - stocks[buyingDay]);
            }
        }
        if(maxProfit < 0) {
            maxProfit = 0;
        }
        System.out.println("Stocks = " + Arrays.toString(stocks));
        System.out.println("Max profit = " + maxProfit);
    }
    /*
        Time - O(N)
        Space - O(1)

        This approach is based around logic that whichever day we sell
        we should have bought the stock at the minimum price possible.
        In starting we assume the first day is minimum price and iterating further
        we update the minimum price.
     */
    private void approach2(int[] stocks) {
        int n = stocks.length;
        int maxProfit = Integer.MIN_VALUE;
        int minPrice = stocks[0];
        for(int i = 1; i < n; i++) {
            int profit = stocks[i] - minPrice;
            maxProfit = Math.max(maxProfit, profit);
            minPrice = Math.min(minPrice, stocks[i]);
        }
        System.out.println("Stocks = " + Arrays.toString(stocks));
        System.out.println("Max profit = " + maxProfit);
    }
}
