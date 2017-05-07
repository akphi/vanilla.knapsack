package main.java.vanilla;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Loader of configuration file.
 * 
 * @author An Phi
 *
 */
public class Config {
	private static Config instance;
	private String propertyFilePath = "config.properties";
	private Properties mainConfig = new Properties();

	/**
	 * Constructor. Load the configuration file.
	 */
	private Config() {
		try {
			File file = new File(propertyFilePath);
			FileInputStream fileInput = new FileInputStream(file);
			mainConfig.load(fileInput);
			fileInput.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Configuration loader singleton.
	 * 
	 * @return the configuration loader
	 */
	public static synchronized Config getInstance() {
		if (instance == null) {
			instance = new Config();
		}
		return instance;
	}

	/**
	 * Get configuration
	 * 
	 * @return the configuration
	 */
	public Properties getConfig() {
		return mainConfig;
	}

}
