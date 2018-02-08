package com.sire.storage.ModuleEnvironment.Utils;

import java.io.*;

public class SerializeUtil {
      public static byte[] serialize(Object object) {
          if(!(object instanceof Serializable)){
              throw new RuntimeException("对象必须实现Serializable接口并设置SerializeId!");
          }
           ObjectOutputStream oos = null;
            ByteArrayOutputStream baos = null;
            try {
                 // 序列化
                baos = new ByteArrayOutputStream();
                oos = new ObjectOutputStream(baos);
                oos.writeObject(object);
                 byte[] bytes = baos.toByteArray();
                 return bytes;
           } catch (Exception e) {
                e.printStackTrace();
           }
            return null;
     }

      public  static <T> T unserialize( byte[] bytes) {
           ByteArrayInputStream bais = null;
            try {
                 // 反序列化
                bais = new ByteArrayInputStream(bytes);
                ObjectInputStream ois = new ObjectInputStream(bais);
                 return (T) ois.readObject();
           } catch (Exception e) {
                e.printStackTrace();
           }
            return null;
     }
}