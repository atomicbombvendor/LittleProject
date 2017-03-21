import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.List;

/**
 * Created by eli9 on 3/6/2017.
 */
public class TestXML {

    public static void main(final String[] args) {
        final TestXML test = new TestXML();
        try {
            test.testGetRoot();
        } catch (final Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 获取文件的xml对象，然后获取对应的根节点root
     */
    public void testGetRoot() throws Exception {
        final SAXReader sax = new SAXReader();// 创建一个SAXReader对象
        //final File xmlFile = new File("C:\\Users\\.yang\\Desktop\\ss.xml");// 根据指定的路径创建file对象
        URLCUSIP URL = new URLCUSIP();
        final Document document = sax.read(URL.getUrl());// 获取document对象,如果文档无节点，则会抛出Exception提前结束
        final Element root = document.getRootElement();// 获取根节点
        getNodes(root);// 从根节点开始遍历所有节点

    }

    /**
     * 从指定节点Element node开始,递归遍历其所有子节点
     */
    public void getNodes(final Element node) {
        System.out.println("-------开始新节点-------------");

        // 当前节点的名称、文本内容和属性
        System.out.println("当前节点名称：" + node.getName());// 当前节点名称
        System.out.println("当前节点的内容：" + node.getTextTrim());// 当前节点内容
        final List<Attribute> listAttr = node.attributes();// 当前节点的所有属性
        for (final Attribute attr : listAttr) {// 遍历当前节点的所有属性
            final String name = attr.getName();// 属性名称
            final String value = attr.getValue();// 属性的值
            System.out.println("属性名称：" + name + "---->属性值：" + value);
        }

        // 递归遍历当前节点所有的子节点
        final List<Element> listElement = node.elements();// 所有一级子节点的list
        for (final Element e : listElement) {// 遍历所有一级子节点
            getNodes(e);// 递归
        }
    }

}