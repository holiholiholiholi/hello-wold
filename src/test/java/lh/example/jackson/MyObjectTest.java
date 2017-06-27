package lh.example.jackson;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lihong on 27.06.17.
 */
public class MyObjectTest {

    @Test
    public void xmlTest() throws IOException {
        MyTree mo = new MyTree();

//        mo.map.put("key", Arrays.asList("a","b"));
//        mo.map.put("key2", Arrays.asList("x"));
        //mo.map.put("key", asList("value"));
        mo.addNode(new MyTree.Node(1));
        mo.addNode(new MyTree.Node(2));
        mo.addNode(new MyTree.Node(3));
        mo.addNode(new MyTree.Node(4));

        MyTree mo2 = new MyTree();
        mo2.addNode(new MyTree.Node(10));
        mo2.addNode(new MyTree.Node(20));
        mo2.addNode(new MyTree.Node(30));
        mo2.addNode(new MyTree.Node(40));

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        StringBuilder sb = new StringBuilder();

        String json = mapper.writeValueAsString(mo);
//        System.out.println(json);
//        System.out.println(mapper.readValue(json, MyTree.class).getNodes());

        sb.append(json);
        sb.append("\n");
        sb.append(mapper.writeValueAsString(mo2));

        MappingIterator<MyTree> it = mapper.readValues(new JsonFactory().createParser(sb.toString()),MyTree.class);
        for(;it.hasNext();){
            System.out.println("Iterator:"+it.next().getNodes());
        }

        JacksonXmlModule module = new JacksonXmlModule();
       module.setDefaultUseWrapper(false);
        XmlMapper xmlMapper = new XmlMapper(module);
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
//        JaxbAnnotationModule jaxbAnnotationModule = new JaxbAnnotationModule();
//        xmlMapper.registerModule(jaxbAnnotationModule);

        String xml = xmlMapper.writeValueAsString(Arrays.asList(mo, mo2));
        System.out.println(xml);


//        ObjectMapper jmapper = new ObjectMapper();
//        jmapper.enable(SerializationFeature.INDENT_OUTPUT);
//        System.out.println(jmapper.writeValueAsString(mo));

//        String xml1 = "<MyTree><map><key2>x</key2><key><value>a</value><value>b</value></key></map></MyTree>";
//
//        System.out.println(mapper.readValue(xml1, MyTree.class).map.get("key").size());
    }

    @Test
    public void xmlMapTest() throws IOException{
        MyObject mo = new MyObject();
        mo.map.put("key", Arrays.asList("a","b"));
        mo.map.put("key2", Arrays.asList("x"));

        XmlMapper mapper = new XmlMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);


        String xml = mapper.writeValueAsString(mo);
        System.out.println(xml);
    }
}