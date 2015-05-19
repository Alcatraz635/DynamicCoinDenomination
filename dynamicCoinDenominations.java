/*
 * Tyler Schumacher
 */
class  dynamicCoinDenominations
{
    static final int A = 40;
    static int denom[] = {1, 10, 25};
    static int[][] f = new int[2][A+1];
    static int[][] i = new int[4][A+1];
    public static void main (String [] args) 
    {
        for(int x = 0; x <= A; x++)
        {
            dynamicMinimalCoins(x, denom);
        }
        System.out.println("Minimal Amount of Coins Table");
        printArray(f);
        System.out.println("Amount of Each Denomination Table");
        i[1][0] = 1;
        i[2][0] = 10;
        i[3][0] = 25;
        printArray(i);
        System.out.println("k = 34");
        backtrack(34,i);
        System.out.println("k = 39");
        backtrack(39,i);
        System.out.println("k = 40");
        backtrack(40,i);
    }
    public static int dynamicMinimalCoins(int max, int[] denom)
    {
        int[][] table = new int[2][max+1];
        table[0][0] = 0;
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        for (int i = 1; i < max+1; i++) 
        {
            table[0][i] = -1;
        }
        if (table[0][max] < 0) 
        {
            for (int m = 0; m <= max; m++) 
            {
                if (table[0][m] < 0) 
                {
                    table[0][m] = 1000000000;
                    for (int k : denom) 
                    {
                        if (m - k >= 0 && table[0][m - k] + 1 < table[0][m]) 
                        {
                            table[0][m] = table[0][m - k] + 1;
                            table[1][m] = k;
                        }
                    }
                }
                f[0][m] = m;
                i[0][m] = m;
                f[1][m] = table[0][m];
            }
        }
        for (int y = max; y > 0; y -= table[1][y]) 
        {
                if(table[1][y] == 1)
                {
                    count1 += 1;
                }
                if(table[1][y] == 10)
                {
                    count2 += 1;
                }
                if(table[1][y] == 25)
                {
                    count3 += 1;
                }
        }
        i[1][max] = count1;
        i[2][max] = count2;
        i[3][max] = count3;
        return table[0][max];
    }
    public static void printArray(int[][] table)
    {
        for (int[] rows : table) 
        {
            for (int col : rows) 
            {
                System.out.format("%5d", col);
            }
            System.out.println();
        }
    
    }
    public static void backtrack(int key, int[][] table)
    {
        System.out.println("d1(1) = " + table[1][key]);
        System.out.println("d2(10) = " + table[2][key]);
        System.out.println("d3(25) = " + table[3][key]);
    }
}
