package com.example.demo.hello;

//import com.sun.xml.internal.ws.util.xml.XmlUtil;

import cn.hutool.core.util.XmlUtil;
import org.w3c.dom.Document;

import javax.xml.xpath.XPathConstants;
import java.io.File;

public class lala {

    public static void main(String[] args) {

        System.out.println("hello world");

        //结果为“ok”
        Document docResult = XmlUtil.readXML("C:\\Users\\MLoong\\IdeaProjects\\demo\\src\\main\\java\\com\\example\\demo\\hello\\lala.xml");

        Object value = XmlUtil.getByXPath("//returnsms/message", docResult, XPathConstants.STRING);


        System.out.println(value);

        String s = XmlUtil.toStr(docResult);

        System.out.println(s);

//        XmlUtil.toFile(docResult,"C:\\Users\\MLoong\\IdeaProjects\\demo\\src\\main\\java\\com\\example\\demo\\hello\\lalala.xml");

//        readObjectFromXml

        Object o = XmlUtil.readObjectFromXml(new File("C:\\Users\\MLoong\\IdeaProjects\\demo\\src\\main\\java\\com\\example\\demo\\hello\\lalala.xml"));

        System.out.println(o);


    }
}
