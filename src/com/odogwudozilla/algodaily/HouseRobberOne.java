package com.odogwudozilla.algodaily;

public class HouseRobberOne {
    public static int rob(int[] nums) {
        int robWithOutIncludingLastHouse = 0, robWithIncludingLastHouse = 0;

        for (int n : nums) {
            System.out.println("n = " + n);
            int temp = Math.max(robWithOutIncludingLastHouse + n, robWithIncludingLastHouse);
            robWithOutIncludingLastHouse = robWithIncludingLastHouse;
            System.out.println("temp = " + temp);
            System.out.println("robWithOutIncludingLastHouse = " + robWithOutIncludingLastHouse);
            robWithIncludingLastHouse = temp;
            System.out.println("robWithIncludingLastHouse = " + robWithIncludingLastHouse);
            System.out.println("-----------------------------------------------------");
        }
        return robWithIncludingLastHouse;
    }

    public static void main(String[] args) {
        System.out.println(rob(new int[]{1, 2, 3, 1}));
        //System.out.println(rob(new int[]{2, 7, 9, 3, 1}));
    }
}
