package com.example.demo.xml;

import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;


public class World {

    public static void main(String[] args) {
        System.out.println("Creat XML");
        Document document = CreatXML();
        System.out.println(document.asXML());

        System.out.println("-----------");
        System.out.println("-----------");

        System.out.println("Parsing XML");
        Document document2 = ParsingXML();
        System.out.println(document2.asXML());

        System.out.println("-----------");
        System.out.println("-----------");

        System.out.println("iterators XML");
        IteratorsXML();

        System.out.println("-----------");
        System.out.println("-----------");

        System.out.println("XPath XML");
        XPathXML();

        System.out.println("-----------");
        System.out.println("-----------");

        System.out.println("快速循环 XML");
        try {
            SAXReader reader = new SAXReader();
            Document document3 = reader.read("XMLtest.xml");
            TreeWalk(document3);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        System.out.println("-----------");
        System.out.println("-----------");

        System.out.println("String_XML");
        String_XML();

    }

    //Creat XML
    //创建XML
    public static Document CreatXML() {
        Document document = DocumentHelper.createDocument();//创建xml文档
        Element root = document.addElement("root");//创建根元素

        //添加根元素下的子元素及其属性,内容
        Element node1 = root.addElement("node").addAttribute("name", "jay").addText("jay zhou");
        Element node2 = root.addElement("node").addAttribute("name", "XU").addText("XU LIN JIE");
        return document;
    }

    //Parsing XML
    //解析XML
    //读取XML
    //SAXReader可以通过多种方式读取xml数据，并返回Document格式的对象。
    //通过查看源码，可以看出read()方法接收File，InputStream和URL等格式的参数来读取相应的xml数据。
    public static Document ParsingXML() {
        Document document = null;
        try {
            //创建解析器
            SAXReader reader = new SAXReader();//创建读取文件内容对象
            document = reader.read("XMLtest.xml");//指定文件并读取

        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return document;
    }

    //iterators XML
    //迭代XML
    public static void IteratorsXML() {
        Document document = null;
        try {
            SAXReader reader = new SAXReader();
            document = reader.read("XMLtest.xml");
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element root = document.getRootElement();
        //遍历孩子元素
        for (Iterator<Element> it = root.elementIterator(); it.hasNext(); ) {
            Element element = it.next();
            System.out.println(element.asXML());
        }

        System.out.println("-----------");
        System.out.println("-----------");

        //遍历标签为node的元素
        for (Iterator<Element> it = root.elementIterator("node"); it.hasNext(); ) {
            Element element = it.next();
            System.out.println(element.asXML());
        }

        System.out.println("-----------");
        System.out.println("-----------");

        //遍历根的属性
        for (Iterator<Attribute> it1 = root.attributeIterator(); it1.hasNext(); ) {
            Attribute attribute = it1.next();
            System.out.println(attribute.asXML());
        }

    }

    /*
     *使用dom4j支持xpath的操作的几种主要形式
   　　       第一种形式
        　　　　    /AAA/DDD/BBB： 表示一层一层的，AAA下面 DDD下面的BBB
    　       第二种形式
       　　　　     //BBB： 表示和这个名称相同，表示只要名称是BBB，都得到
                        第三种形式
        　　　        　/*: 所有元素
                        第四种形式
       　　　　     BBB[1]：　表示第一个BBB元素
        　  　　       BBB[last()]：表示最后一个BBB元素
                        第五种形式
        　　　　    //BBB[@id]： 表示只要BBB元素上面有id属性，都得到
                        第六种形式
        　　　　            //BBB[@id='b1'] 表示元素名称是BBB,在BBB上面有id属性，并且id的属性值是b1
     */
        /*
         * 在dom4j里面提供了两个方法，用来支持xpath
       　　　　    selectNodes("xpath表达式")，获取多个节点
　　　　　　         selectSingleNode("xpath表达式")，获取一个节点
         */

    //XPath XML
    //快速找到某个元素(dom4j在解析xml时只能一层一层，所以可结合XPath来提高效率)
    //需要导入jaxen-1.1.6.jar是一个开源的XPath库。Jaxen是一个Java编写的开源的XPath库
    public static void XPathXML() {
        Document document = null;
        try {
            SAXReader reader = new SAXReader();
            document = reader.read("XMLtest.xml");
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        List<Node> list = document.selectNodes("//returnstatus");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    //Looping XML
    //快速循环XML文档树，比iterators性能高
    public static void TreeWalk(Document document) {
        TreeWalk_E(document.getRootElement());
    }

    public static void TreeWalk_E(Element element) {
        for (int i = 0, size = element.nodeCount(); i < size; i++) {
            Node node = element.node(i);
            if (node instanceof Element) {
                System.out.println(node);
                TreeWalk_E((Element) node);
            }
        }
    }

    //字符串转换为XML
    public static void String_XML() {
        String xmlString = "<root><people>man</people></root>";
        try {
            Document document = DocumentHelper.parseText(xmlString);
            System.out.println(document.asXML());
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }

}

