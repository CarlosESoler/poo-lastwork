package model.utils;

import java.io.*;

public class Serializacao {
    
    File file;
    ObjectInputStream ois;

    public Serializacao() {
        try {
        this.file = new File("Objetos.bin");
        } catch (Exception e) {
            System.out.println("DEU RUIM VEI" + e.getCause());
        }
    }
    
    public void serializa(Object obj) {
        try(FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(obj);
        } catch (Exception e) {
            for (StackTraceElement ste : e.getStackTrace()) {
                System.out.println(ste);
            }
            System.out.println("DEU RUIM VEI");
        }
    }

    public Object deserializaObjeto() {
        try(FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis)) {
        return ois.readObject();
        } catch (Exception e) {
            System.out.println("DEU RUIM VEI");
        }
        return null;
    }
}
