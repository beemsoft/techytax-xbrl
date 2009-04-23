package org.techytax.props;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropsFactory {
	
	private static Properties props;

	public static Properties loadProperties() throws Exception {
		if (props != null) return props;
		File file = new File("test");
		System.out.println("Test: "+file.getAbsolutePath());
		props = new Properties();
		try {
			props.load(new FileInputStream("techytax.properties"));
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		return props;
	}

}
