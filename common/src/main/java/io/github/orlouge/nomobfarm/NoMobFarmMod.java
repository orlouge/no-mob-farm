package io.github.orlouge.nomobfarm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class NoMobFarmMod {
    public static final String MOD_ID = "nomobfarm";
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
	public static final String CONFIG_FNAME = "nomobfarm.properties";

	public static int NATURAL_SLOWDOWN_RATE = 1000;
	public static float NATURAL_RECOVERY_RATE = 0.0003f;
	public static int NATURAL_MAX_WAIT = 100000;
	public static int NATURAL_MIN_DEATHS = 0;
	public static int NATURAL_MAX_DEATHS = -1;
	public static int NATURAL_OFFLINE_PERSISTENCE = 3 * 24 * 60 * 60;

	public static int SPAWNER_SLOWDOWN_NEAR_RATE = 20;
	public static int SPAWNER_SLOWDOWN_FAR_RATE = 100;
	public static float SPAWNER_RECOVERY_RATE = 0.001f;
	public static int SPAWNER_MAX_WAIT = 10000;
	public static int SPAWNER_MIN_DEATHS = 15;
	public static int SPAWNER_MAX_DEATHS = -1;
	public static int SPAWNER_OFFLINE_PERSISTENCE = 3 * 24 * 60 * 60;

	public static int RAID_MIN_SIZE = 512;
	public static int RAID_CENTER_MIN_RANDOMIZATION = 16;
	public static int RAID_CENTER_MAX_RANDOMIZATION = 24;
	public static boolean RAID_NO_BAD_OMEN_LOOP = true;

	public static long GOLEM_DETECTION_MEMORY = 10000;

	public static double REINFORCEMENT_PENALTY = 0.049;
	public static boolean REINFORCEMENT_PENALTY_CONVERSION = true;

	public static boolean LOGGING = false;

	public static int allowedCropDrops = 0;

	public static void init(Path configFolder) {
		String configPath = Paths.get(configFolder.toString(), CONFIG_FNAME).toString();
		Properties defaultProps = new Properties();

		defaultProps.setProperty("natural_slowdown_rate", Integer.toString(NATURAL_SLOWDOWN_RATE));
		defaultProps.setProperty("natural_recovery_rate", Float.toString(NATURAL_RECOVERY_RATE));
		defaultProps.setProperty("natural_max_wait", Integer.toString(NATURAL_MAX_WAIT));
		defaultProps.setProperty("natural_min_deaths", Integer.toString(NATURAL_MIN_DEATHS));
		defaultProps.setProperty("natural_max_deaths", Integer.toString(NATURAL_MAX_DEATHS));
		defaultProps.setProperty("natural_offline_persistence", Integer.toString(NATURAL_OFFLINE_PERSISTENCE));
		defaultProps.setProperty("spawner_slowdown_rate", Integer.toString(SPAWNER_SLOWDOWN_NEAR_RATE));
		defaultProps.setProperty("spawner_slowdown_near_rate", Integer.toString(SPAWNER_SLOWDOWN_NEAR_RATE));
		defaultProps.setProperty("spawner_slowdown_far_rate", Integer.toString(SPAWNER_SLOWDOWN_FAR_RATE));
		defaultProps.setProperty("spawner_recovery_rate", Float.toString(SPAWNER_RECOVERY_RATE));
		defaultProps.setProperty("spawner_max_wait", Integer.toString(SPAWNER_MAX_WAIT));
		defaultProps.setProperty("spawner_min_deaths", Integer.toString(SPAWNER_MIN_DEATHS));
		defaultProps.setProperty("spawner_max_deaths", Integer.toString(SPAWNER_MAX_DEATHS));
		defaultProps.setProperty("spawner_offline_persistence", Integer.toString(SPAWNER_OFFLINE_PERSISTENCE));
		defaultProps.setProperty("raid_min_size", Integer.toString(RAID_MIN_SIZE));
		defaultProps.setProperty("raid_center_min_randomization", Integer.toString(RAID_CENTER_MIN_RANDOMIZATION));
		defaultProps.setProperty("raid_center_max_randomization", Integer.toString(RAID_CENTER_MAX_RANDOMIZATION));
		defaultProps.setProperty("raid_no_bad_omen_loop", Boolean.toString(RAID_NO_BAD_OMEN_LOOP));
		defaultProps.setProperty("golem_detection_memory", Long.toString(GOLEM_DETECTION_MEMORY));
		defaultProps.setProperty("reinforcement_penalty", Double.toString(REINFORCEMENT_PENALTY));
		defaultProps.setProperty("reinforcement_penalty_conversion", Boolean.toString(REINFORCEMENT_PENALTY_CONVERSION));
		defaultProps.setProperty("death_logging", Boolean.toString(LOGGING));

		File f = new File(configPath);
		if (f.isFile() && f.canRead()) {
			try (FileInputStream in = new FileInputStream(f)) {
				Properties props = new Properties(defaultProps);
				props.load(in);
				NATURAL_SLOWDOWN_RATE = Integer.parseInt(props.getProperty("natural_slowdown_rate"));
				NATURAL_RECOVERY_RATE = Float.parseFloat(props.getProperty("natural_recovery_rate"));
				NATURAL_MAX_WAIT = Integer.parseInt(props.getProperty("natural_max_wait"));
				NATURAL_MIN_DEATHS = Integer.parseInt(props.getProperty("natural_min_deaths"));
				NATURAL_MAX_DEATHS = Integer.parseInt(props.getProperty("natural_max_deaths"));
				NATURAL_OFFLINE_PERSISTENCE = Integer.parseInt(props.getProperty("natural_offline_persistence"));
				SPAWNER_SLOWDOWN_NEAR_RATE = Integer.parseInt(props.getProperty("spawner_slowdown_rate"));
				SPAWNER_SLOWDOWN_NEAR_RATE = Integer.parseInt(props.getProperty("spawner_slowdown_near_rate"));
				SPAWNER_SLOWDOWN_FAR_RATE = Integer.parseInt(props.getProperty("spawner_slowdown_far_rate"));
				SPAWNER_RECOVERY_RATE = Float.parseFloat(props.getProperty("spawner_recovery_rate"));
				SPAWNER_MAX_WAIT = Integer.parseInt(props.getProperty("spawner_max_wait"));
				SPAWNER_MIN_DEATHS = Integer.parseInt(props.getProperty("spawner_min_deaths"));
				SPAWNER_MAX_DEATHS = Integer.parseInt(props.getProperty("spawner_max_deaths"));
				SPAWNER_OFFLINE_PERSISTENCE = Integer.parseInt(props.getProperty("spawner_offline_persistence"));
				RAID_MIN_SIZE = Integer.parseInt(props.getProperty("raid_min_size"));
				RAID_CENTER_MIN_RANDOMIZATION = Integer.parseInt(props.getProperty("raid_center_min_randomization"));
				RAID_CENTER_MAX_RANDOMIZATION = Integer.parseInt(props.getProperty("raid_center_max_randomization"));
				RAID_NO_BAD_OMEN_LOOP = Boolean.parseBoolean(props.getProperty("raid_no_bad_omen_loop"));
				GOLEM_DETECTION_MEMORY = Long.parseLong(props.getProperty("golem_detection_memory"));
				REINFORCEMENT_PENALTY = Double.parseDouble(props.getProperty("reinforcement_penalty"));
				REINFORCEMENT_PENALTY_CONVERSION = Boolean.parseBoolean(props.getProperty("reinforcement_penalty_conversion"));
				LOGGING = Boolean.parseBoolean(props.getProperty("death_logging"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try (FileOutputStream out = new FileOutputStream(configPath)) {
				defaultProps.store(out, "");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
