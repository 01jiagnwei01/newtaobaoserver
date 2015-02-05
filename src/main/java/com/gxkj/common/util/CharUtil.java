package com.gxkj.common.util;

public class CharUtil {
	
	public static void main(String[] args) {
		System.out.println(ToDBC("一汽一大众汽车有限公司"));
		System.out.println(ToDBC("一汽-大众汽车有限公司"));
	}
	/**
	 * 转全角(SBC case)
	 * @param input  
	 * @return
	 */
	public static String ToSBC(String input)
    {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++)
        {
            if (c[i] == 32)
            {
                c[i] = (char)12288;
                continue;
            }
            if (c[i] < 127)
                c[i] = (char)(c[i] + 65248);
        }
        return new String(c);
    }
    /**
     * 转半角(DBC case)
     * @param input
     * @return
     */
    public static String ToDBC( String input)
    {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++)
        {
            if (c[i] == 12288)
            {
                c[i] = (char)32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375)
                c[i] = (char)(c[i] - 65248);
        }
        return new String(c);
    }
     
    
}
