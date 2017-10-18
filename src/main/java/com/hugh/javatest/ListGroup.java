package com.hugh.javatest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Hugh on 2015-12-14.
 */
public class ListGroup {

    public static void main(String[] args) {
        List<Obj> list = new ArrayList<Obj>();
        list.add(new Obj("��ԴA", 100));
        list.add(new Obj("��ԴB", 200));
        list.add(new Obj("��ԴC", 300));
        list.add(new Obj("��ԴB", 6600));
        list.add(new Obj("��ԴA", 99800));

        List<Obj> groupList = getListByGroup(list);
        for (Obj obj : groupList) {
            System.out.print(obj.getKey()+ ","+obj.getValue());
        }
    }

    private static List<Obj> getListByGroup(List<Obj> list) {
        List<Obj> result = new ArrayList<Obj>();
        Map<String, Integer> map = new HashMap<String, Integer>();

        for (Obj obj : list) {
            if (map.containsKey(obj.getKey())) {
                map.put(obj.getKey(), map.get(obj.getKey()) + obj.getValue());
            } else {
                map.put(obj.getKey(), obj.getValue());
            }
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            result.add(new Obj(entry.getKey(), entry.getValue()));
        }
        return result;
    }
}

class Obj {
    private String key;
    private int value;

    public Obj(String key, int value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }
}
