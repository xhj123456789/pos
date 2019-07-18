import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.SAXOutputter;
import org.jdom.output.XMLOutputter;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


public class MyTest {
    public @Test void T1(){
        System.out.println(1111111111111111111111111111111111111111111111);
        //设置属性标签
        Element persons = new Element("Persons");
        Element person = new Element("Person");
        Element username = new Element("Username");
        username.addContent("皮皮虾");     //设置标签值
        Element age = new Element("Age");
        age.addContent("18");
        //设置子父级关系
        persons.addContent(person);
        person.addContent(username);
        person.addContent(age);
        XMLOutputter outputter = new XMLOutputter(); //输出 xml独有也是jdom
        //设置format(格式)来解决乱码问题
        Format format = outputter.getFormat();  //获取format对象
        format.setEncoding("UTF-8");     //设置编码格式
        format.setIndent("\t");     //设置是否换行
        outputter.setFormat(format);
        try {
            FileOutputStream outputStream = new FileOutputStream("E://out.xml");
            outputter.output(persons,outputStream); //输入根,以及输入路径
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public @Test void T2(){
            //SAXBuilder() 是jdom的升级版 边加载边读取 mybatis读取xml原理就是他
        try {
            //获得文本对象    //类加载器.获取文件流
            Document build = new SAXBuilder().build(MyTest.class.getClassLoader().getResourceAsStream("MyXml.xml"));
            Element element = build.getRootElement(); //获取根
            System.out.println(element.getName());      //Persons
            List<Element> child1 = element.getChildren();
            for (Element c:child1) {
                System.out.print(c.getName()+" 有: ");
                List<Element> children1 = c.getChildren();
                for (Element c1:children1
                     ) {
                    System.out.print(c1.getName()+"   ");
                }
                System.out.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
