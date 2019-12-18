package com.example.demo.xml;

import cn.hutool.core.util.XmlUtil;
import org.w3c.dom.Document;

public class Hello {

    public static void main(String[] args) {

        Document xml = XmlUtil.createXml();

        xml.createElement("oumingyuan");
        xml.createTextNode("lala");

        System.out.println(XmlUtil.toStr(xml));


    }

}
