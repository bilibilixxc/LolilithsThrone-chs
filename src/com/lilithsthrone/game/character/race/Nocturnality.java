package com.lilithsthrone.game.character.race;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.lilithsthrone.utils.time.DayPeriod;

/**
 * @since 0.4.7.1
 * @version 0.4.7.1
 * @author Innoxia
 */
public enum Nocturnality {

	DIURNAL("白天活动", "白天活动，夜晚休息。", true, false),
	NOCTURNAL("夜晚活动", "夜晚活动，白天休息。", false, true),

	// All of the following are tagged as being active both at night and during the day:
	
	CREPUSCULAR("黄昏活动", "在早晨和傍晚的黄昏时段活动。", true, true), // 尽管“晨昏性”可以指早晨性、晚间性或两者都指，但在LT中，此术语仅用于指代两者都指的含义。
	MATUTINAL("早晨活动", "在黎明时段活动。", true, true),
	VESPERTINE("晚间活动", "在晚间黄昏时段活动。", true, true),
	
	CATHEMERAL("无规律", "在白天和夜晚不规律地活动。", true, true);
	
	private String name;
	private String description;
	
	private boolean activeAtDay;
	private boolean activeAtNight;

	private Nocturnality(String name, String description, boolean activeAtDay, boolean activeAtNight) {
		this.name = name;
		this.description = description;
		this.activeAtDay = activeAtDay;
		this.activeAtNight = activeAtNight;
	}

	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	
	public boolean isActiveAtDay() {
		return activeAtDay;
	}

	public boolean isActiveAtNight() {
		return activeAtNight;
	}
	
	/**
	 * @return A List of DayPeriods in which a character of this Nocturnality will want to sleep.
	 * <br/>Will be all DayPeriods if the Nocturnality is active at both day and night.
	 * <br/>Otherwise, will contain DayPeriod.DAY and DayPeriod.CIVIL_TWILIGHT if the Nocturnality is inactive during the day, or DayPeriod.NIGHT, DayPeriod.ASTRONOMICAL_TWILIGHT, and DayPeriod.NAUTICAL_TWILIGHT if inactive during the night.
	 */
	public List<DayPeriod> getSleepPeriods() {
		List<DayPeriod> sleepyTimes = new ArrayList<>();
		if(this.isActiveAtDay() && this.isActiveAtNight()) {
			Collections.addAll(sleepyTimes, DayPeriod.values());
		} else if(!this.isActiveAtDay()) {
			sleepyTimes.add(DayPeriod.DAY);
			sleepyTimes.add(DayPeriod.CIVIL_TWILIGHT);
		} else if(!this.isActiveAtNight()) {
			sleepyTimes.add(DayPeriod.NIGHT);
			sleepyTimes.add(DayPeriod.ASTRONOMICAL_TWILIGHT);
			sleepyTimes.add(DayPeriod.NAUTICAL_TWILIGHT);
		}
		return sleepyTimes;
	}
}
