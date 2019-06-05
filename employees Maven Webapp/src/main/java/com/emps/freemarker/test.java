package com.emps.freemarker;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class test {
   public static void main(String[] args) throws IOException, TemplateException {
	   @SuppressWarnings("deprecation")
	Configuration cfg = new Configuration();
	  cfg.setDirectoryForTemplateLoading(new File("templates")); 
	  
	  
	  Map root = new HashMap();
	  List list = new ArrayList();
	  list.add(new Address("中国","北京"));
	  list.add(new Address("美国","New York"));
	  list.add(new Address("法国","巴黎"));
	  
	  root.put("user", "老张");
	  root.put("random", new Random().nextInt(100));
	  root.put("date1", new Date());
	  root.put("list", list);
	  

	  
	 Template t1 =cfg.getTemplate("a.ftl");
	 
	 Writer out = new OutputStreamWriter(System.out);
	 t1.process(root, out);
	 out.flush();
	 out.close();
   }
}
