package Fabreze.bots;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class JSON {
    public static <T extends Object> String mapToJson(Map<String, T> map){
        String res = "{";
        for(int i = 0; i < map.size(); i++){
            Map.Entry<String, T> e = (Map.Entry<String, T>)map.entrySet().toArray()[i];
            if(i > 0)
                res += ",";
            res += "\"" + e.getKey() + "\":" + objectToValue(e.getValue());
        }
        res += "}";
        return res;
    }

    public static String objectToValue(Object o){
        StringBuilder res = new StringBuilder();
        if(o instanceof Boolean || o instanceof Integer){
            res.append(String.valueOf(o));
        } else if (o instanceof Object[]){
            Object[] array = (Object[])o;
            res.append("[");
            for(int i = 0; i < array.length; i++){
                if(i > 0)
                    res.append(",");
                res.append(objectToValue(array[i]));
            }
            res.append("]");
        } else if (o instanceof Map){
            Map map = (Map)o;
            res.append("{");
            for(int i = 0; i < map.size(); i++){
                if(i > 0)
                    res.append(",");
                res.append(objectToValue(map.keySet().toArray()[i])).append(":").append(objectToValue(map.values().toArray()[i]));
            }
            res.append("}");
        } else {
            res.append("\"").append(String.valueOf(o)).append("\"");
        }
        return res.toString();
    }

    public static Map<String, Object> jsonToMap(String jsonString){
        HashMap<String, Object> info = new HashMap<>();
        if(jsonString != null) {
            jsonString = jsonString.substring(1, jsonString.length()-1);
            int block = 0;
            boolean qToggle = false;
            boolean bValue = false;
            String key = "";
            String value = "";
            for (char c : jsonString.toCharArray()) {
                switch (c) {
                    case '"':
                        if(qToggle)
                            block--;
                        else
                            block++;
                        qToggle^=true;
                        continue;
                    case '{':
                        block++;
                        break;
                    case '}':
                        block--;
                        break;
                    case '[':
                        block++;
                        break;
                    case ']':
                        block--;
                        break;
                    case ',':
                        if(block == 0){
                            info.put(key, toObject(value));
                            bValue = false;
                            key = "";
                            value = "";
                            continue;
                        }
                    case ':':
                        if(block == 0){
                            bValue = true;
                            continue;
                        }
                }
                if (bValue)
                    value += c;
                else
                    key += c;
            }
            info.put(key, toObject(value));
        }
        return info;
    }

    public static Object toObject(String string){
        if(string.matches("-?\\d+")){
            return Integer.parseInt(string);
        } else if(string.matches("-?\\d+\\.\\d+")){
            return Double.parseDouble(string);
        } else if (string.matches("(true|false)")){
            return Boolean.valueOf(string);
        } else if (string.matches("\\{.*\\}")){
            LinkedHashMap<Object, Object> map = new LinkedHashMap<>();
            string = string.substring(1, string.length() - 1);
            int block = 0;
            boolean bValue = false;
            StringBuilder key = new StringBuilder();
            StringBuilder value = new StringBuilder();
            for (char c : string.toCharArray()) {
                switch (c) {
                    case '"':
                        continue;
                    case '{':
                        block++;
                        continue;
                    case '}':
                        block--;
                        break;
                    case '[':
                        block++;
                        break;
                    case ']':
                        block--;
                        break;
                    case ',':
                        if(block == 0){
                            map.put(key.toString(), toObject(value.toString()));
                            bValue = false;
                            key = new StringBuilder();
                            value = new StringBuilder();
                            continue;
                        }
                    case ':':
                        if(block == 0){
                            bValue = true;
                            continue;
                        }
                    default:
                }
                if (bValue)
                    value.append(c);
                else
                    key.append(c);
            }
            map.put(key.toString(), toObject(value.toString()));
            return map;
        } else if (string.matches("\\[.*\\]")) {
            ArrayList<Object> list = new ArrayList<>();
            string = string.substring(1, string.length()-1);
            int block = 0;
            StringBuilder value = new StringBuilder();
            for (char c : string.toCharArray()) {
                switch (c) {
                    case '"':
                        //block ^= true;
                        continue;
                    case '{':
                        block++;
                        continue;
                    case '}':
                        block--;
                        break;
                    case '[':
                        block++;
                        break;
                    case ']':
                        block--;
                        break;
                    case ',':
                        if (block == 0) {
                            list.add(toObject(value.toString()));
                            value = new StringBuilder();
                            continue;
                        }
                    case ':':
                        if (block == 0)
                            continue;
                    default:
                        value.append(c);
                }
            }
            list.add(toObject(value.toString()));
            return list.toArray();
        }
        return string;
    }
}