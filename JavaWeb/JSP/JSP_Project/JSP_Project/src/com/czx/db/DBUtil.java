package com.czx.db;

import com.czx.bean.Emp;

import java.util.HashMap;
import java.util.Map;

public class DBUtil {
    public static Map<String, Emp> map = new HashMap<>();

    static {
        map.put("101", new Emp("101", "AA", "123", "1234@qq.com"));
        map.put("102", new Emp("102", "AB", "123", "1235@qq.com"));
        map.put("103", new Emp("103", "AC", "123", "1236@qq.com"));
        map.put("104", new Emp("104", "AD", "123", "1237@qq.com"));
        map.put("105", new Emp("105", "AE", "123", "1238@qq.com"));
        map.put("106", new Emp("106", "AF", "123", "1239@qq.com"));
        map.put("107", new Emp("107", "AG", "123", "1230@qq.com"));
    }

    public static boolean selectEmpByAccountAndPassword(Emp emp) {
        boolean flag = false;
        for (String key : map.keySet()) {
            Emp e = map.get(key);
            flag = emp.getAccount().equals(e.getAccount()) && emp.getPassword().equals(e.getPassword());
            if (flag) {
                break;
            }
        }
        return flag;
    }
}
