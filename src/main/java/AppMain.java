import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.Properties;

/**
 * Created by Hexu on 2016/6/19.
 */
public class AppMain {
    public static void main(String[] args) {
        //dao的物理路径
        String daoPath = "D:/mywork/ecommerce-api/src/main/java/com/zhidian/ecommerce/api/dao";
        //model类的名字
        String className = "Friend";
        createIntf(daoPath, className);
        createImpl(daoPath, className);
    }

    public static void createImpl(String daoPath, String className) {
        String filename = daoPath + "/impl/" + className + "DaoImpl" + ".java";
        try {
            File f = new File(filename);
            if (!f.exists()) {
                f.createNewFile();
            }
            Properties props = new Properties();
            props.put("input.encoding", "utf-8");
            props.put("output.encoding", "utf-8");
            OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f), "UTF-8");
            BufferedWriter writer = new BufferedWriter(write);
            VelocityEngine ve = new VelocityEngine();
            ve.init();
            VelocityContext context = new VelocityContext();
            context.put("packageName", "com.zhidian.ecommerce.api.dao.impl");
            context.put("classPakageName", "com.zhidian.ecommerce.api.model");
            context.put("className", className);
            context.put("date", (new java.text.SimpleDateFormat("yyyy/MM/dd")).format(new Date()));
            Template template = ve.getTemplate("/src/main/resources/daoImpl.vm");
            template.setEncoding("UTF-8");
            template.merge(context, writer);
            writer.flush();
            write.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createIntf(String daoPath, String className) {
        String filename = daoPath + "/intf/" + className + "Dao" + ".java";
        try {
            File f = new File(filename);
            if (!f.exists()) {
                f.createNewFile();
            }
            Properties props = new Properties();
            props.put("input.encoding", "utf-8");
            props.put("output.encoding", "utf-8");
            OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f), "UTF-8");
            BufferedWriter writer = new BufferedWriter(write);
            VelocityEngine ve = new VelocityEngine();
            ve.init();
            VelocityContext context = new VelocityContext();
            context.put("packageName", "com.zhidian.ecommerce.api.dao.intf");
            context.put("classPakageName", "com.zhidian.ecommerce.api.model");
            context.put("className", className);
            context.put("date", (new java.text.SimpleDateFormat("yyyy/MM/dd")).format(new Date()));
            Template template = ve.getTemplate("/src/main/resources/daoIntf.vm");
            template.setEncoding("UTF-8");
            template.merge(context, writer);
            writer.flush();
            write.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
