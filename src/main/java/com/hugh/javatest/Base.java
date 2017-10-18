package com.hugh.javatest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hugh on 2015/11/24.
 */
public class Base {

    private static String str1="test1";
    private static List<String> list1=new ArrayList<String>();

    public static void main(String[] arg){
        String str2=str1;
        str1="test3";
        System.out.println("str1=" + str1 + ",str2=" + str2);
        list1.add("test1");
        List<String> list2=list1;
        list2.add("test2");
        System.out.println("list1=" + list1 + ",list2=" + list2);
    }
}
