package tudresden.ocl20.pivot.tools.template.sql;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import tudresden.ocl20.pivot.tools.template.ITemplateGroup;
import tudresden.ocl20.pivot.tools.template.TemplatePlugin;
import tudresden.ocl20.pivot.tools.template.exception.TemplateException;


public class SQLTemplate {
	
	
	public static void loadSQLTemplates() {
		
		URL stream =
				SQLTemplatePlugin.class
						.getResource("/resources/templates/Standard.stg");
		URL stream2 =
				SQLTemplatePlugin.class
						.getResource("/resources/templates/Standard-inv.stg");
		List<URL> streams = new LinkedList<URL>();
		streams.add(stream);
		streams.add(stream2);
		ITemplateGroup standardGroup = null;
		try {
			standardGroup =
					TemplatePlugin.getTemplateGroupRegistry().addDefaultTemplateGroup(
							"Standard(SQL)", null);
			standardGroup.addFiles(streams);
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		stream =
				SQLTemplatePlugin.class.getResource("/resources/templates/MySQL.stg");
		stream2 =
				SQLTemplatePlugin.class
						.getResource("/resources/templates/MySQL-inv.stg");
		streams = new LinkedList<URL>();
		streams.add(stream);
		streams.add(stream2);
		ITemplateGroup mysqlGroup;
		try {
			mysqlGroup =
					TemplatePlugin.getTemplateGroupRegistry().addDefaultTemplateGroup(
							"MySQL(SQL)", standardGroup);
			mysqlGroup.addFiles(streams);
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		stream =
				SQLTemplatePlugin.class
						.getResource("/resources/templates/Oracle8i.stg");
		stream2 =
				SQLTemplatePlugin.class
						.getResource("/resources/templates/Oracle8i-inv.stg");
		streams = new LinkedList<URL>();
		streams.add(stream);
		streams.add(stream2);
		ITemplateGroup oracleGroup;
		try {
			oracleGroup =
					TemplatePlugin.getTemplateGroupRegistry().addDefaultTemplateGroup(
							"Oracle 8i(SQL)", standardGroup);
			oracleGroup.addFiles(streams);
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		stream =
				SQLTemplatePlugin.class
						.getResource("/resources/templates/PostgreSQL81.stg");
		stream2 =
				SQLTemplatePlugin.class
						.getResource("/resources/templates/PostgreSQL81-inv.stg");
		streams = new LinkedList<URL>();
		streams.add(stream);
		streams.add(stream2);
		ITemplateGroup postgreGroup;
		try {
			postgreGroup =
					TemplatePlugin.getTemplateGroupRegistry().addDefaultTemplateGroup(
							"PostgreSQL 8.1(SQL)",  standardGroup);
			postgreGroup.addFiles(streams);
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}

}
