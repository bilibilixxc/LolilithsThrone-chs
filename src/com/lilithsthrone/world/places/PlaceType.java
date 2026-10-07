package com.lilithsthrone.world.places;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.lilithsthrone.LolificationProject.Lexi.dialogue.encounters.LexiEncounter;
import com.lilithsthrone.game.character.npc.dominion.Daddy;
import com.lilithsthrone.game.character.npc.dominion.Elle;
import com.lilithsthrone.game.character.npc.dominion.Helena;
import com.lilithsthrone.game.character.npc.dominion.Natalya;
import com.lilithsthrone.game.character.npc.dominion.Wes;
import com.lilithsthrone.game.character.npc.submission.DarkSiren;
import com.lilithsthrone.game.character.npc.submission.Elizabeth;
import com.lilithsthrone.game.character.npc.submission.FortressAlphaLeader;
import com.lilithsthrone.game.character.npc.submission.FortressFemalesLeader;
import com.lilithsthrone.game.character.npc.submission.FortressMalesLeader;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.character.race.SubspeciesSpawnRarity;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueManager;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.encounters.AbstractEncounter;
import com.lilithsthrone.game.dialogue.encounters.Encounter;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.DaddyDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.DemonHome;
import com.lilithsthrone.game.dialogue.places.dominion.DominionPark;
import com.lilithsthrone.game.dialogue.places.dominion.DominionPlaces;
import com.lilithsthrone.game.dialogue.places.dominion.EnforcerWarehouse;
import com.lilithsthrone.game.dialogue.places.dominion.HomeImprovements;
import com.lilithsthrone.game.dialogue.places.dominion.LilithsTower;
import com.lilithsthrone.game.dialogue.places.dominion.RedLightDistrict;
import com.lilithsthrone.game.dialogue.places.dominion.cityHall.CityHall;
import com.lilithsthrone.game.dialogue.places.dominion.cityHall.CityHallDemographics;
import com.lilithsthrone.game.dialogue.places.dominion.cityHall.CityHallProperty;
import com.lilithsthrone.game.dialogue.places.dominion.enforcerHQ.BraxOffice;
import com.lilithsthrone.game.dialogue.places.dominion.enforcerHQ.EnforcerHQDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.feliciaApartment.FeliciaApartment;
import com.lilithsthrone.game.dialogue.places.dominion.harpyNests.HarpyNestHelena;
import com.lilithsthrone.game.dialogue.places.dominion.harpyNests.HarpyNestsDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.helenaHotel.HelenaApartment;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.Lab;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.Library;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayaHomeGeneric;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayaSpa;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayasRoom;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.RoomArthur;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.RoomPlayer;
import com.lilithsthrone.game.dialogue.places.dominion.nightlife.NightlifeDistrict;
import com.lilithsthrone.game.dialogue.places.dominion.nyansApartment.NyanApartment;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.ArcaneArts;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.ClothingEmporium;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.DreamLover;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.PixsPlayground;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.RalphsSnacks;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.ShoppingArcadeDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.SuccubisSecrets;
import com.lilithsthrone.game.dialogue.places.dominion.slaverAlley.BountyHunterLodge;
import com.lilithsthrone.game.dialogue.places.dominion.slaverAlley.ScarlettsShop;
import com.lilithsthrone.game.dialogue.places.dominion.slaverAlley.SlaverAlleyDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.slaverAlley.SlaveryAdministration;
import com.lilithsthrone.game.dialogue.places.dominion.warehouseDistrict.DominionExpress;
import com.lilithsthrone.game.dialogue.places.dominion.warehouseDistrict.KaysWarehouse;
import com.lilithsthrone.game.dialogue.places.dominion.warehouseDistrict.Warehouses;
import com.lilithsthrone.game.dialogue.places.dominion.zaranixHome.ZaranixHomeFirstFloor;
import com.lilithsthrone.game.dialogue.places.dominion.zaranixHome.ZaranixHomeFirstFloorRepeat;
import com.lilithsthrone.game.dialogue.places.dominion.zaranixHome.ZaranixHomeGroundFloor;
import com.lilithsthrone.game.dialogue.places.dominion.zaranixHome.ZaranixHomeGroundFloorRepeat;
import com.lilithsthrone.game.dialogue.places.fields.FieldsDialogue;
import com.lilithsthrone.game.dialogue.places.submission.BatCaverns;
import com.lilithsthrone.game.dialogue.places.submission.LyssiethPalaceDialogue;
import com.lilithsthrone.game.dialogue.places.submission.SlimeQueensLair;
import com.lilithsthrone.game.dialogue.places.submission.SubmissionGenericPlaces;
import com.lilithsthrone.game.dialogue.places.submission.gamblingDen.GamblingDenDialogue;
import com.lilithsthrone.game.dialogue.places.submission.gamblingDen.PregnancyRoulette;
import com.lilithsthrone.game.dialogue.places.submission.gamblingDen.RoxysShop;
import com.lilithsthrone.game.dialogue.places.submission.impFortress.ImpCitadelDialogue;
import com.lilithsthrone.game.dialogue.places.submission.impFortress.ImpFortressDialogue;
import com.lilithsthrone.game.dialogue.places.submission.ratWarrens.RatWarrensCaptiveDialogue;
import com.lilithsthrone.game.dialogue.places.submission.ratWarrens.RatWarrensDialogue;
import com.lilithsthrone.game.dialogue.places.submission.ratWarrens.VengarCaptiveDialogue;
import com.lilithsthrone.game.dialogue.places.submission.rebelBase.RebelBase;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.AbstractWorldType;
import com.lilithsthrone.world.Bearing;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.TeleportPermissions;
import com.lilithsthrone.world.Weather;
import com.lilithsthrone.world.WorldRegion;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.population.Population;
import com.lilithsthrone.world.population.PopulationDensity;
import com.lilithsthrone.world.population.PopulationType;

import static com.lilithsthrone.LolificationProject.Lexi.dialogue.encounters.LexiEncounter.CECILIA_AMBUSH;

/**
 * @since 0.1.0
 * @version 0.4
 * @author Innoxia
 */
public class PlaceType {
	
	// Generic holding map:
	
	public static final AbstractPlaceType GENERIC_IMPASSABLE = new AbstractPlaceType(
			WorldRegion.MISC, "不可通过地块", "", null, PresetColour.BASE_GREY, null, Darkness.ALWAYS_LIGHT, null, "");
	
	public static final AbstractPlaceType GENERIC_EMPTY_TILE = new AbstractPlaceType(
			WorldRegion.MISC, "空地块", "", "dominion/slaverAlleyIcon", PresetColour.BASE_CRIMSON, null, Darkness.ALWAYS_LIGHT, null, "");

	public static final AbstractPlaceType GENERIC_HOLDING_CELL = new AbstractPlaceType(
			WorldRegion.MISC, "暂留室", "", "dominion/slaverAlleyIcon", PresetColour.BASE_GREY, null, Darkness.ALWAYS_LIGHT, null, "");

	public static final AbstractPlaceType GENERIC_CLUB_HOLDING_CELL = new AbstractPlaceType(
			WorldRegion.MISC, "暂留室 (夜店)", "", "dominion/slaverAlleyIcon", PresetColour.BASE_GREY, null, Darkness.ALWAYS_LIGHT, null, "");
	
	public static final AbstractPlaceType GENERIC_MUSEUM = new AbstractPlaceType(
			WorldRegion.OLD_WORLD, "博物馆", "", "dominion/slaverAlleyIcon", PresetColour.BASE_TAN, null, Darkness.ALWAYS_LIGHT, null, "in Lily's Museum"
			).initItemsPersistInTile()
			.initWeatherImmune();
	
	
	
	// Museum:
	
	public static final AbstractPlaceType MUSEUM_ENTRANCE = new AbstractPlaceType(
			WorldRegion.OLD_WORLD,
			"入口",
			"莉莉姨妈供职博物馆的正门。",
			"prologue/exit",
			PresetColour.BASE_RED,
			null,
			Darkness.ALWAYS_LIGHT,
			null, "在莉莉的博物馆") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.PERSON, PopulationDensity.FEW, Util.newHashMapOfValues(new Value<>(Subspecies.HUMAN, SubspeciesSpawnRarity.TEN))));
		}
	}.initItemsPersistInTile()
	.initWeatherImmune();
	
	public static final AbstractPlaceType MUSEUM_CROWDS = new AbstractPlaceType(
			WorldRegion.OLD_WORLD,
			"人群",
			"博物馆大厅的这一部分目前挤满了一大群游客。他们和你一样，是来参加展览的开幕活动的。",
			"prologue/crowd",
			PresetColour.BASE_YELLOW,
			null,
			Darkness.ALWAYS_LIGHT,
			null, "在莉莉的博物馆") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(false, PopulationType.CROWD, PopulationDensity.DENSE, Util.newHashMapOfValues(new Value<>(Subspecies.HUMAN, SubspeciesSpawnRarity.TEN))));
		}
	}.initItemsPersistInTile()
	.initWeatherImmune();
	
	public static final AbstractPlaceType MUSEUM_OFFICE = new AbstractPlaceType(
			WorldRegion.OLD_WORLD,
			"办公室",
			"一间宽敞的行政办公室。它看起来有点眼熟，你搜肠刮肚，觉得像是莉莉姨妈的……",
			"prologue/office",
			PresetColour.BASE_BLUE_LIGHT,
			null,
			Darkness.ALWAYS_LIGHT,
			null, "在莉莉的博物馆"
			).initItemsPersistInTile()
			.initWeatherImmune();
	
	public static final AbstractPlaceType MUSEUM_STAGE = new AbstractPlaceType(
			WorldRegion.OLD_WORLD,
			"舞台",
			"大厅中央竖起了一个大舞台，你的姨妈莉莉就要发表演讲。",
			"prologue/stage",
			PresetColour.BASE_ORANGE,
			null,
			Darkness.ALWAYS_LIGHT,
			null, "在莉莉的博物馆") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(false, PopulationType.CROWD, PopulationDensity.DENSE, Util.newHashMapOfValues(new Value<>(Subspecies.HUMAN, SubspeciesSpawnRarity.TEN))));
		}
	}.initItemsPersistInTile()
	.initWeatherImmune();
	
	public static final AbstractPlaceType MUSEUM_ROOM = new AbstractPlaceType(
			WorldRegion.OLD_WORLD,
			"展厅",
			"博物馆中专门展示古代文物和珍贵文物的众多房间之一。",
			"prologue/room",
			PresetColour.BASE_TAN,
			null,
			Darkness.ALWAYS_LIGHT,
			null, "在莉莉的博物馆"
			).initItemsPersistInTile()
			.initWeatherImmune();
	
	public static final AbstractPlaceType MUSEUM_STAIRS = new AbstractPlaceType(
			WorldRegion.OLD_WORLD,
			"楼梯",
			"博物馆的楼梯连接着一楼和二楼。",
			"prologue/stairsUp",
			PresetColour.BASE_GREEN,
			null,
			Darkness.ALWAYS_LIGHT,
			null, "在莉莉的博物馆"
			).initItemsPersistInTile()
			.initWeatherImmune();
	
	public static final AbstractPlaceType MUSEUM_LOBBY = new AbstractPlaceType(
			WorldRegion.OLD_WORLD,
			"大厅",
			 "博物馆主要的双层楼高大厅，楼上的栏杆上挂着庆祝新展览的横幅。",
			null,
			PresetColour.BASE_TAN,
			null,
			Darkness.ALWAYS_LIGHT,
			null, "在莉莉的博物馆") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.PERSON, PopulationDensity.FEW, Util.newHashMapOfValues(new Value<>(Subspecies.HUMAN, SubspeciesSpawnRarity.TEN))));
		}
	}.initItemsPersistInTile()
	.initWeatherImmune();
	
	public static final AbstractPlaceType MUSEUM_CORRIDOR = new AbstractPlaceType(
			WorldRegion.OLD_WORLD,
			"走廊",
			 "博物馆上层的走廊布局像迷宫一样令人震惊。",
			null,
			PresetColour.BASE_TAN,
			null,
			Darkness.ALWAYS_LIGHT,
			null, "在莉莉的博物馆"
			).initItemsPersistInTile()
			.initWeatherImmune();
	
	public static final AbstractPlaceType MUSEUM_MIRROR = new AbstractPlaceType(
			WorldRegion.OLD_WORLD,
			"镜室",
			"这个房间里有一面直通天花板的巨大镜子，是其最大的亮点。",
			"prologue/mirror",
			PresetColour.BASE_PINK,
			null,
			Darkness.ALWAYS_LIGHT,
			null, "在莉莉的博物馆"
			).initItemsPersistInTile()
			.initWeatherImmune();
	
	
	
	// Dominion:
	
	public static final AbstractPlaceType DOMINION_PLAZA = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"莉莉丝广场",
			"在御城区的正中心有一个宽阔的广场，莉莉丝领地的消息是由全职的公告传报员宣读的。",
			"dominion/statue",
			PresetColour.BASE_PINK_DEEP,
			DominionPlaces.DOMINION_PLAZA,
			Darkness.ALWAYS_LIGHT,
			null, "于御城区中央广场") {
		@Override
		public List<Population> getPopulation() {
			List<Population> pop = new ArrayList<>();
			
			if(Main.game.isDayTime()) {
				if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
					pop.add(new Population(true, PopulationType.PERSON, PopulationDensity.COUPLE, Subspecies.getDominionStormImmuneSpecies(true)));
					pop.add(new Population(false, PopulationType.ENFORCER, PopulationDensity.OCCASIONAL, Subspecies.getDominionStormImmuneSpecies(true, Subspecies.HUMAN)));
				} else {
					pop.add(new Population(true, PopulationType.CROWD, PopulationDensity.DENSE, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
					pop.add(new Population(false, PopulationType.ENFORCER, PopulationDensity.OCCASIONAL, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true, Subspecies.HUMAN)));
					pop.add(new Population(true, PopulationType.CENTAUR_CARTS, PopulationDensity.NUMEROUS, Util.newHashMapOfValues(new Value<>(Subspecies.CENTAUR, SubspeciesSpawnRarity.TEN))));
				}
			} else {
				if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
					pop.add(new Population(false, PopulationType.PERSON, PopulationDensity.OCCASIONAL, Subspecies.getDominionStormImmuneSpecies(true)));
					pop.add(new Population(false, PopulationType.ENFORCER, PopulationDensity.OCCASIONAL, Subspecies.getDominionStormImmuneSpecies(true, Subspecies.HUMAN)));
				} else {
					pop.add(new Population(false, PopulationType.PERSON, PopulationDensity.OCCASIONAL, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
					pop.add(new Population(false, PopulationType.ENFORCER, PopulationDensity.OCCASIONAL, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true, Subspecies.HUMAN)));
				}
			}
			
			return pop;
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_PINK);
	
	public static final AbstractPlaceType DOMINION_STREET = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"御城区街道",
			"御城区宽阔的街道两旁排列着保存完好的城镇住宅，而且都有人行道。",
			null,
			PresetColour.BASE_GREY,
			DominionPlaces.STREET,
			Darkness.ALWAYS_LIGHT,
			Encounter.DOMINION_STREET,
			"于御城区的街上") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public List<Population> getPopulation() {
			List<Population> pop = new ArrayList<>();
			
			if(Main.game.isDayTime()) {
				if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
					if(Main.game.getNonCompanionCharactersPresent().isEmpty() || !Main.game.getCurrentDialogueNode().isTravelDisabled()) {
						pop.add(new Population(false, PopulationType.PERSON, PopulationDensity.OCCASIONAL, Subspecies.getDominionStormImmuneSpecies(true)));
					}
					
				} else {
					pop.add(new Population(true, PopulationType.CROWD, PopulationDensity.DENSE, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
					pop.add(new Population(false, PopulationType.ENFORCER, PopulationDensity.OCCASIONAL, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true, Subspecies.HUMAN)));
					pop.add(new Population(true, PopulationType.CENTAUR_CARTS, PopulationDensity.NUMEROUS, Util.newHashMapOfValues(new Value<>(Subspecies.CENTAUR, SubspeciesSpawnRarity.TEN))));
				}
			} else {
				if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
					if(Main.game.getNonCompanionCharactersPresent().isEmpty() || !Main.game.getCurrentDialogueNode().isTravelDisabled()) {
						pop.add(new Population(false, PopulationType.PERSON, PopulationDensity.OCCASIONAL, Subspecies.getDominionStormImmuneSpecies(true)));
					}
				} else {
					pop.add(new Population(true, PopulationType.CROWD, PopulationDensity.SPARSE, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
					pop.add(new Population(false, PopulationType.ENFORCER, PopulationDensity.OCCASIONAL, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true, Subspecies.HUMAN)));
				}
			}
			
			return pop;
		}
	};
	
	public static final AbstractPlaceType DOMINION_BOULEVARD = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"御城区主干道",
			"通向御城区中心的主干道，十分宽敞，畅行无阻，经常有执法者巡逻。",
			null,
			PresetColour.BASE_PINK_LIGHT,
			DominionPlaces.BOULEVARD,
			Darkness.ALWAYS_LIGHT, Encounter.DOMINION_BOULEVARD, "御城区的街上") {
		@Override
		public List<Population> getPopulation() {
			return DOMINION_PLAZA.getPopulation();
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_PINK);
	
	public static final AbstractPlaceType DOMINION_LILITHS_TOWER = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"莉莉丝之塔",
			"暗色石块筑成的高塔雄伟矗立于此，数里外就清晰可见，莉莉丝就居住其中，时刻提醒着市民其统治者的威严。",
			"dominion/lilithsTowerIcon",
			PresetColour.BASE_PURPLE,
			LilithsTower.OUTSIDE,
			Darkness.ALWAYS_LIGHT,
			null, "于御城区的街上") {

		@Override
		public List<Population> getPopulation() {
			return DOMINION_PLAZA.getPopulation();
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_PINK);
	
	public static final AbstractPlaceType DOMINION_ENFORCER_HQ = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"执法者总部",
			"执法者总部是御城区外观最现代化的建筑之一，也是所有御城区执法人员的命令发起处。",
			"dominion/enforcerHQIcon",
			PresetColour.BASE_BLUE,
			EnforcerHQDialogue.EXTERIOR,
			Darkness.ALWAYS_LIGHT,
			null, "于御城区的街上") {
		@Override
		public List<Population> getPopulation() {
			return DOMINION_STREET.getPopulation();
		}
	};
	
	public static final AbstractPlaceType DOMINION_DEMON_HOME_GATE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"恶魔之家大门",
			"进入“恶魔之家”这个区域的入口只有寥寥几处，且都有大量执法者在执勤。",
			"dominion/gate",
			PresetColour.BASE_PINK_LIGHT,
			DemonHome.DEMON_HOME_GATE,
			Darkness.ALWAYS_LIGHT,
			null, "于恶魔之家所在街道") {
		@Override
		public List<Population> getPopulation() {
			return DOMINION_PLAZA.getPopulation();
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_PINK);
	
	public static final AbstractPlaceType DOMINION_DEMON_HOME = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"恶魔之家",
			"这片环绕着莉莉丝之塔的区域被称作“恶魔之家”，虽然名称如此，但居民的种族却仍旧多种多样。",
			null,
			PresetColour.BASE_PINK,
			DemonHome.DEMON_HOME_STREET,
			Darkness.ALWAYS_LIGHT,
			null, "于恶魔之家所在街道") {
		@Override
		public List<Population> getPopulation() {
			return DOMINION_PLAZA.getPopulation();
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_PINK);
	
	public static final AbstractPlaceType DOMINION_DEMON_HOME_ARTHUR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"圣地塔",
			"一座巨大的石质建筑，装饰成豪华的维多利亚风格，看上去不像是公寓，反而像是个五星级酒店。",
			"dominion/demonHomeSawltyTowersIcon",
			PresetColour.RACE_HUMAN,
			DemonHome.DEMON_HOME_STREET_ARTHUR,
			Darkness.ALWAYS_LIGHT,
			null, "于恶魔之家的街道上，圣地塔外") {
		@Override
		public String getName() {
			if(Main.game.isStarted()) {
				return UtilText.parse("恶魔之家([arthur.Name])");
			}
			return name;
		}
		@Override
		public String getTooltipDescription() {
			return tooltipDescription+"亚瑟的公寓就位于该区域。";
		}
		@Override
		public List<Population> getPopulation() {
			return DOMINION_PLAZA.getPopulation();
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_PINK);
	
	public static final AbstractPlaceType DOMINION_DEMON_HOME_ZARANIX = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"恶魔之家(扎拉尼克斯)",
			"这片环绕着莉莉丝之塔的区域被称作“恶魔之家”，虽然名称如此，但居民的种族却仍旧多种多样。",
			"dominion/demonHomeZaranixIcon",
			PresetColour.BASE_PINK,
			DemonHome.DEMON_HOME_STREET_ZARANIX,
			Darkness.ALWAYS_LIGHT,
			null, "于恶魔之家所在街道") {
		@Override
		public String getName() {
			if(Main.game.isStarted()) {
				return UtilText.parse("恶魔之家([zaranix.Name])");
			}
			return name;
		}
		@Override
		public String getTooltipDescription() {
			return tooltipDescription+UtilText.parse("[zaranix.NamePos]的公寓就位于该区域。");
		}
		@Override
		public List<Population> getPopulation() {
			return DOMINION_PLAZA.getPopulation();
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_PINK);
	
	public static final AbstractPlaceType DOMINION_DEMON_HOME_DADDY = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"恶魔之家(爹地)",
			"这片环绕着莉莉丝之塔的区域被称作“恶魔之家”，虽然名称如此，但居民的种族却仍旧多种多样。",
			"dominion/demonHomeDaddyIcon",
			PresetColour.BASE_INDIGO,
			DemonHome.DEMON_HOME_STREET_DADDY,
			Darkness.ALWAYS_LIGHT,
			null, "于恶魔之家所在街道") {
		@Override
		public String getName() {
			if(Main.game.isStarted()) {
				return UtilText.parse("恶魔之家([daddy.Name])");
			}
			return name;
		}
		@Override
		public String getTooltipDescription() {
			return tooltipDescription+UtilText.parse("[daddy.NamePos]的公寓就位于该区域。");
		}
		@Override
		public List<Population> getPopulation() {
			if(Main.game.getCharactersPresent().contains(Main.game.getNpc(Daddy.class))) {
				return Util.newArrayListOfValues(new Population(true, PopulationType.DINER, PopulationDensity.NUMEROUS, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
			}
			return DOMINION_PLAZA.getPopulation();
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_PINK);
	
	public static final AbstractPlaceType DOMINION_DEMON_HOME_SEX_SHOP = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"洛维耶纳奢侈品店",
			"为了迎合恶魔之家的富裕客户群体，名为“洛维耶纳奢侈品店”的成人用品商店可以购买性玩具和自动性爱玩偶。",
			"dominion/sexShopIcon",
			PresetColour.BASE_PINK_LIGHT,
			DemonHome.DEMON_HOME_SEX_SHOP,
			Darkness.ALWAYS_LIGHT,
			null, "于恶魔之家所在街道") {
		@Override
		public List<Population> getPopulation() {
			if(Main.game.getDialogueFlags().hasFlag("innoxia_doll_factory_exterior_population_hidden")) {
				return super.getPopulation();
			}
			
			if(Main.game.getPlayer().getQuest(QuestLine.SIDE_DOLL_FACTORY)==Quest.DOLL_FACTORY_7A) {
				if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
					return Util.newArrayListOfValues(
							new Population(false, PopulationType.CROWD, PopulationDensity.DENSE, Subspecies.getDominionStormImmuneSpecies(true)),
							new Population(true, PopulationType.ENFORCER, PopulationDensity.DOZENS, Subspecies.getDominionStormImmuneSpecies(true, Subspecies.HUMAN)));
				} else {
					return Util.newArrayListOfValues(
							new Population(false, PopulationType.CROWD, PopulationDensity.DENSE, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)),
							new Population(true, PopulationType.ENFORCER, PopulationDensity.DOZENS, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true, Subspecies.HUMAN)));
				}
			}
			return DOMINION_PLAZA.getPopulation();
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_PINK);
	
	public static final AbstractPlaceType DOMINION_SHOPPING_ARCADE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"购物中心",
			"尽管御城区四处散落着无数的店铺，但购物中心是众所周知的好去处。",
			"dominion/shoppingArcadeIcon",
			PresetColour.BASE_GOLD,
			ShoppingArcadeDialogue.OUTSIDE,
			Darkness.ALWAYS_LIGHT, Encounter.DOMINION_STREET, "御城区的街上") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public List<Population> getPopulation() {
			return DOMINION_STREET.getPopulation();
		}
	};

	public static final AbstractPlaceType DOMINION_NYAN_APARTMENT = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"妮安的公寓",
			"妮安已经告诉你她住在哪了，你得知妮安的公寓就在御城区的这个区域。",
			"dominion/homeNyanIcon",
			PresetColour.BASE_PINK_LIGHT,
			DominionPlaces.STREET,
			Darkness.ALWAYS_LIGHT,
			Encounter.DOMINION_STREET,
			"于御城区的街上") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public List<Population> getPopulation() {
			return DOMINION_STREET.getPopulation();
		}
	};

	public static final AbstractPlaceType DOMINION_CALLIE_BAKERY = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"奶油烘焙店",
			"这家烘焙店是一个叫卡丽的马女经营。",
			"dominion/callieBakeryIcon",
			PresetColour.BASE_BROWN,
			DominionPlaces.STREET,
			Darkness.ALWAYS_LIGHT,
			Encounter.DOMINION_STREET,
			"于御城区的街上") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public List<Population> getPopulation() {
			return DOMINION_STREET.getPopulation();
		}
	};
	
	public static final AbstractPlaceType DOMINION_STREET_HARPY_NESTS = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"御城区街道",
			"哈比之巢错综复杂的通道和互相交错的桥面连接着各个巢穴的顶部，在下方的街道上投下了一大片阴影。",
			null,
			PresetColour.BASE_GREY,
			DominionPlaces.STREET_SHADED,
			Darkness.ALWAYS_LIGHT, Encounter.DOMINION_STREET, "御城区的街上") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public List<Population> getPopulation() {
			return DOMINION_STREET.getPopulation();
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_DARK);
	
	public static final AbstractPlaceType DOMINION_HARPY_NESTS_ENTRANCE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"哈比之巢入口",
			"一座巨大的建筑，其中无数的电梯和蜿蜒的楼梯连接着哈比之巢和下方的街道。",
			"dominion/harpyNestIcon",
			PresetColour.BASE_MAGENTA,
			HarpyNestsDialogue.OUTSIDE,
			Darkness.ALWAYS_LIGHT, Encounter.DOMINION_STREET, "御城区的街上") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public List<Population> getPopulation() {
			return DOMINION_STREET.getPopulation();
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_DARK);

	public static final AbstractPlaceType DOMINION_HELENA_HOTEL = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"金羽毛酒店",
			"一座御城区最负盛名且广为人知的酒店，“金羽毛酒店”为哈比族长海伦娜所有。",
			"dominion/demonHomeHelenaIcon",
			PresetColour.BASE_GOLD,
			DominionPlaces.HELENAS_HOTEL,
			Darkness.ALWAYS_LIGHT,
			null, "于恶魔之家所在街道") {
		@Override
		public List<Population> getPopulation() {
			return DOMINION_STREET.getPopulation();
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_DARK);
	
	public static final AbstractPlaceType DOMINION_NIGHTLIFE_DISTRICT = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"夜生活区",
			"尽管御城区四处散落着无数的夜店、酒吧和其他娱乐场所，但夜生活的最佳去处就是这里。",
			"dominion/nightlifeIcon",
			PresetColour.BASE_PINK_LIGHT,
			NightlifeDistrict.OUTSIDE,
			Darkness.ALWAYS_LIGHT, Encounter.DOMINION_STREET, "御城区的街上") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public List<Population> getPopulation() {
			return DOMINION_STREET.getPopulation();
		}
	};
	
	public static final AbstractPlaceType DOMINION_CITY_HALL = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"市政厅",
			"市政厅是地区政府的中枢，能够办理的行政事务不仅限于御城区，而是整个莉莉丝治下的土地。",
			"dominion/townHallIcon",
			PresetColour.BASE_INDIGO,
			CityHall.OUTSIDE,
			Darkness.ALWAYS_LIGHT, Encounter.DOMINION_STREET, "御城区的街上") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public List<Population> getPopulation() {
			return DOMINION_STREET.getPopulation();
		}
	};

	public static final AbstractPlaceType DOMINION_BANK = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"御城区银行",
			"王国内唯一的银行“御城区银行”，总行就位于御城区此处。",
			"dominion/bankIcon",
			PresetColour.BASE_GOLD,
			DialogueManager.getDialogueFromId("innoxia_places_dominion_bank_generic_exterior"),
			Darkness.ALWAYS_LIGHT,
			null,
			"于御城区的街上") {
		@Override
		public List<Population> getPopulation() {
			return DOMINION_PLAZA.getPopulation();
		}
	};
	
	public static final AbstractPlaceType DOMINION_AUNTS_HOME = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"莉莱雅的家",
			"莉莱雅的家规模宏大，在这一地区显得格外引人注目。它不是一般的那种富人公馆，真的是很少见的那种超大型豪华公馆。",
			"dominion/homeIcon",
			PresetColour.BASE_BLUE_LIGHT,
			LilayaHomeGeneric.OUTSIDE,
			Darkness.ALWAYS_LIGHT, Encounter.DOMINION_STREET, "御城区的街上") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public List<Population> getPopulation() {
			return DOMINION_STREET.getPopulation();
		}
	};
	
	public static final AbstractPlaceType DOMINION_SLAVER_ALLEY = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"奴隶巷",
			"虽然奴隶制在御城区完全合法，但所有奴隶交易的枢纽就位于城市的阴暗一角，被危机四伏的暗巷所环绕。",
			"dominion/slaverAlleyIcon",
			PresetColour.BASE_CRIMSON,
			SlaverAlleyDialogue.OUTSIDE,
			Darkness.ALWAYS_LIGHT,
			null, "于奴隶巷边的小巷") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public List<Population> getPopulation() {
			if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
				return new ArrayList<>();
			}
			return SLAVER_ALLEY_ENTRANCE.getPopulation();
		}
	};

	public static final AbstractPlaceType DOMINION_RED_LIGHT_DISTRICT = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"红灯区",
			"虽然性爱在御城区唾手可得，但谁也没法保证对手床上技术如何。在这里便可以雇佣到专业的一夜情对象，做完之后就可以拍拍屁股走人。",
			"dominion/brothel",
			PresetColour.BASE_MAGENTA,
			RedLightDistrict.OUTSIDE,
			Darkness.ALWAYS_LIGHT, Encounter.DOMINION_STREET, "御城区的街上") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public List<Population> getPopulation() {
			return DOMINION_STREET.getPopulation();
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_DARK);

	public static final AbstractPlaceType DOMINION_PARK = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"公园",
			"御城区散布着几处大型公园，完全对公众开放。",
			"dominion/park",
			PresetColour.BASE_GREEN,
			DominionPark.PARK,
			Darkness.DAYLIGHT,
			Encounter.DOMINION_PARK,
			"于御城区的一座公园") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public List<Population> getPopulation() {
			List<Population> pop = new ArrayList<>();
			
			if(Main.game.getCurrentWeather()!=Weather.MAGIC_STORM) {
				pop.add(new Population(true, PopulationType.PERSON, PopulationDensity.SEVERAL, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
				pop.add(new Population(false, PopulationType.ENFORCER, PopulationDensity.OCCASIONAL, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true, Subspecies.HUMAN)));
				if(Main.game.getCharactersPresent().contains(Main.game.getNpc(Natalya.class))) {
					pop.add(new Population(true, PopulationType.CENTAUR_CARTS, PopulationDensity.SEVERAL, Util.newHashMapOfValues(new Value<>(Subspecies.CENTAUR, SubspeciesSpawnRarity.TEN))));
				}
			}
			
			return pop;
		}
	};

	public static final AbstractPlaceType DOMINION_HOME_IMPROVEMENT = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"阿格斯的DIY仓库",
			"“阿格斯的DIY仓库”同时给DIY爱好者和专业建筑公司提供素材，拥有两间巨大的库房，位于一处广阔的木料厂内。",
			"dominion/construction",
			PresetColour.BASE_ORANGE,
			HomeImprovements.OUTSIDE,
			Darkness.ALWAYS_LIGHT, Encounter.DOMINION_STREET, "御城区的街上") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public List<Population> getPopulation() {
			return DOMINION_STREET.getPopulation();
		}
	};

	public static final AbstractPlaceType DOMINION_WAREHOUSES = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"仓库区",
			"尽管御城区四处散落着无数的工业建筑，但大多数都集中于专用的仓库区。",
			"dominion/warehouse",
			PresetColour.BASE_BROWN,
			Warehouses.WAREHOUSE_DISTRICT,
			Darkness.ALWAYS_LIGHT, Encounter.DOMINION_STREET, "御城区的街上") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public List<Population> getPopulation() {
			return DOMINION_STREET.getPopulation();
		}
	};
	
	
	
	// Alleyways:
	
	public static final AbstractPlaceType DOMINION_BACK_ALLEYS_SAFE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"小巷(有巡逻)",
			"御城区的大多数小巷危险异常，而这片区域处于执法者的严兵把守之下，保证流掠者不会威胁到公众安全。",
			"dominion/alleysIcon",
			PresetColour.BASE_GREY,
			DominionPlaces.BACK_ALLEYS_SAFE,
			Darkness.DAYLIGHT, Encounter.DOMINION_STREET, "于御城区的一条后巷"
			) {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather()==Weather.MAGIC_STORM;
		}
		@Override
		public List<Population> getPopulation() {
			if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
				return new ArrayList<>();
			}
			return Util.newArrayListOfValues(
					new Population(true, PopulationType.PERSON, PopulationDensity.FEW, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)),
					new Population(true, PopulationType.ENFORCER, PopulationDensity.SEVERAL, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
		}
	};
	
	public static final AbstractPlaceType DOMINION_BACK_ALLEYS = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"小巷",
			"这座城市中迷宫般的小巷虽然提供了道路之间的近道，但却几乎都无人光顾，大家心知肚明这里是社会危险分子的温床。",
			"dominion/alleysIcon",
			PresetColour.BASE_BLACK,
			DominionPlaces.BACK_ALLEYS,
			Darkness.DAYLIGHT, Encounter.DOMINION_ALLEY, "于御城区的一条后巷"
			).initDangerous()
			.initSexNotBlockedFromCharacterPresent();

	public static final AbstractPlaceType DOMINION_DARK_ALLEYS = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"阴暗小巷",
			"御城区迷宫般曲折蜿蜒的小巷的最深处，即使是以此为家的暴徒也不愿涉足……",
			"dominion/alleysDarkIcon",
			PresetColour.BASE_PURPLE,
			DominionPlaces.DARK_ALLEYS,
			Darkness.DAYLIGHT, Encounter.DOMINION_DARK_ALLEY, "于御城区的一条阴暗小巷"
			).initDangerous()
			.initSexNotBlockedFromCharacterPresent();
	
	public static final AbstractPlaceType DOMINION_ALLEYS_CANAL_CROSSING = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"运河交汇处",
			"城市运河交汇处的小巷危机四伏，据说亡命之徒常在此出没。",
			"dominion/bridge",
			PresetColour.BASE_BLUE_LIGHT,
			DominionPlaces.BACK_ALLEYS_CANAL,
			Darkness.DAYLIGHT, Encounter.DOMINION_ALLEY, "于御城区的一条后巷"
			).initDangerous()
			.initAquatic(Aquatic.MIXED);
	       
	// Canals:
	
	public static final AbstractPlaceType DOMINION_CANAL = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"运河",
			"坐着驳船或小舟行进在御城区的运河上确实十分安全，但若是走在人迹罕至，执法者鞭长莫及的纤道上那就完全不是一回事了……",
			"dominion/canalIcon",
			PresetColour.BASE_BLUE_LIGHT,
			DominionPlaces.CANAL,
			Darkness.DAYLIGHT, Encounter.DOMINION_CANAL, "于御城区的运河旁"
			).initDangerous()
			.initAquatic(Aquatic.MIXED);
	
	public static final AbstractPlaceType DOMINION_CANAL_END = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"运河",
			"御城区运河旁的纤道在城市外围戛然而止。",
			"dominion/canalEndIcon",
			PresetColour.BASE_BLUE,
			DominionPlaces.CANAL_END,
			Darkness.DAYLIGHT, Encounter.DOMINION_CANAL, "于御城区的运河旁"
			).initDangerous()
			.initAquatic(Aquatic.MIXED);
	
	// Exits & entrances:
	
	public static final AbstractPlaceType DOMINION_EXIT_TO_SUBMISSION = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"屈城区入口",
			"在每处御城区运河与道路的交汇处，都有着一个通向屈城区地下城市的入口。",
			"dominion/submissionExit",
			PresetColour.BASE_TEAL,
			DominionPlaces.CITY_EXIT_SEWERS,
			Darkness.ALWAYS_LIGHT,
			null, "于御城区的街上") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public List<Population> getPopulation() {
			return DOMINION_PLAZA.getPopulation();
		}
		@Override
		public Bearing getBearing() {
			return Bearing.RANDOM;
		}
	};

	public static final AbstractPlaceType DOMINION_EXIT_TO_BAT_CAVERNS = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"通向蝙蝠洞窟的竖井",
			"一口幽深的竖井，周围都被高高的铁丝网围住，附近的标识上写着此处通向屈城区下的蝙蝠洞窟。",
			"dominion/batCaverns",
			PresetColour.BASE_BLUE,
			DominionPlaces.CITY_EXIT_BAT_CAVERNS,
			Darkness.ALWAYS_LIGHT,
			null,
			"于御城区的街上") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public List<Population> getPopulation() {
			return DOMINION_PLAZA.getPopulation();
		}
		@Override
		public Bearing getBearing() {
			return Bearing.RANDOM;
		}
	};
	
	public static final AbstractPlaceType DOMINION_EXIT_EAST = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"御城区出口",
			"御城区宽阔的林荫大道变作了平常的道路，延伸至城市小得出奇的郊区。",
			"dominion/exitEast",
			PresetColour.BASE_RED,
			DominionPlaces.CITY_EXIT,
			Darkness.ALWAYS_LIGHT,
			null, "于御城区的街上") {
		@Override
		public List<Population> getPopulation() {
			return DOMINION_PLAZA.getPopulation();
		}
		@Override
		public Bearing getBearing() {
			return Bearing.NORTH;
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_PINK);
	
	public static final AbstractPlaceType DOMINION_EXIT_NORTH = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"御城区出口",
			"御城区宽阔的林荫大道变作了平常的道路，延伸至城市小得出奇的郊区。",
			"dominion/exitNorth",
			PresetColour.BASE_RED,
			DominionPlaces.CITY_EXIT,
			Darkness.ALWAYS_LIGHT,
			null, "于御城区的街上") {
		@Override
		public List<Population> getPopulation() {
			return DOMINION_PLAZA.getPopulation();
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_PINK);
	
	public static final AbstractPlaceType DOMINION_EXIT_WEST = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"御城区出口",
			"御城区宽阔的林荫大道变作了平常的道路，延伸至城市小得出奇的郊区。",
			"dominion/exitWest",
			PresetColour.BASE_RED,
			DominionPlaces.CITY_EXIT,
			Darkness.ALWAYS_LIGHT,
			null, "于御城区的街上") {
		@Override
		public List<Population> getPopulation() {
			return DOMINION_PLAZA.getPopulation();
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_PINK);
	
	public static final AbstractPlaceType DOMINION_EXIT_SOUTH = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"御城区出口",
			"御城区宽阔的林荫大道变作了平常的道路，延伸至城市小得出奇的郊区。",
			"dominion/exitSouth",
			PresetColour.BASE_RED,
			DominionPlaces.CITY_EXIT,
			Darkness.ALWAYS_LIGHT,
			null, "于御城区的街上") {
		@Override
		public List<Population> getPopulation() {
			return DOMINION_PLAZA.getPopulation();
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_PINK);
	
	
	
	// Enforcer HQ:
	
	public static final AbstractPlaceType ENFORCER_HQ_CORRIDOR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"走廊",
			"相当平常的走廊两旁都排列着许多扇门，每扇门上都标记着不同执法部门的名称和领域。",
			null,
			PresetColour.BASE_BLACK,
			EnforcerHQDialogue.CORRIDOR,
			Darkness.ALWAYS_LIGHT,
			null, "于执法者总部") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.ENFORCER, PopulationDensity.SEVERAL, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true, Subspecies.HUMAN)));
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_HQ_CELLS_CORRIDOR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"走廊",
			"这段走廊没什么特点。",
			null,
			PresetColour.BASE_BLACK,
			EnforcerHQDialogue.CORRIDOR_PLAIN,
			Darkness.ALWAYS_LIGHT,
			null, "于执法者总部")
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_HQ_STAIRS = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"配备安保的楼梯间",
			"一段通向楼上的楼梯，由一位警惕的执法者守卫。",
			"dominion/enforcerHQ/stairs",
			PresetColour.BASE_GREEN,
			EnforcerHQDialogue.STAIRCASE,
			Darkness.ALWAYS_LIGHT,
			null, "于执法者总部")
			.initWeatherImmune();
	
	public static final AbstractPlaceType ENFORCER_HQ_WAITING_AREA = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"等待区",
			"几张低矮的沙发，几盆盆栽，以及单调无聊的氛围，构成了等候区的一切。",
			"dominion/enforcerHQ/waitingRoom",
			PresetColour.BASE_BROWN,
			EnforcerHQDialogue.WAITING_AREA,
			Darkness.ALWAYS_LIGHT,
			null, "于执法者总部")
			.initWeatherImmune();
	
	public static final AbstractPlaceType ENFORCER_HQ_RECEPTION_DESK = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"前台",
			"执法者总部的前台，职员是那个胸大无脑的猫女，坎迪。",
			"dominion/enforcerHQ/receptionDesk",
			PresetColour.BASE_BLUE_LIGHT,
			EnforcerHQDialogue.RECEPTION_DESK,
			Darkness.ALWAYS_LIGHT,
			null, "于坎迪的办公室")
			.initWeatherImmune();
	
	public static final AbstractPlaceType ENFORCER_HQ_GUARDED_DOOR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"配备安保的门",
			"一位特别强壮的马男守卫着连接公共等候室与执法者总部其他区域的门口。",
			"dominion/enforcerHQ/guardedDoor",
			PresetColour.BASE_CRIMSON,
			EnforcerHQDialogue.GUARDED_DOOR,
			Darkness.ALWAYS_LIGHT,
			null, "于执法者总部") {
		@Override
		public Colour getColour() {
			if((Main.game.getDialogueFlags().values.contains(DialogueFlagValue.accessToEnforcerHQ) && !Main.game.isBraxMainQuestComplete())
					|| Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_WES)) {
				return PresetColour.BASE_GREEN_LIGHT;
			}
			return PresetColour.BASE_CRIMSON;
		} 
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(false, PopulationType.ENFORCER, PopulationDensity.ONE, Util.newHashMapOfValues(new Value<>(Subspecies.HORSE_MORPH, SubspeciesSpawnRarity.TEN))));
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType ENFORCER_HQ_REQUISITIONS_DOOR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"上锁的门",
			"这扇内部门被牢固锁定，没有持有所需钥匙的人无法通过。",
			"dominion/enforcerHQ/guardedDoor",
			PresetColour.BASE_CRIMSON,
			EnforcerHQDialogue.REQUISITIONS_DOOR,
			Darkness.ALWAYS_LIGHT,
			null, "于执法者总部") {
		@Override
		public Colour getColour() {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_WES)) {
				return PresetColour.BASE_GREEN_LIGHT;
			}
			return PresetColour.BASE_CRIMSON;
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType ENFORCER_HQ_LOCKED_DOOR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"上锁的门",
			"这扇内部门被牢固锁定，没有持有所需钥匙的人无法通过。",
			"dominion/enforcerHQ/guardedDoor",
			PresetColour.BASE_CRIMSON,
			EnforcerHQDialogue.LOCKED_DOOR,
			Darkness.ALWAYS_LIGHT,
			null, "于执法者总部").initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_HQ_LOCKED_DOOR_EDGE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"上锁的门",
			"这扇内部门被牢固锁定，没有持有所需钥匙的人无法通过。",
			"dominion/enforcerHQ/guardedDoor",
			PresetColour.BASE_RED_DARK,
			EnforcerHQDialogue.LOCKED_DOOR,
			Darkness.ALWAYS_LIGHT,
			null, "于执法者总部")
			.initWeatherImmune();
	
	public static final AbstractPlaceType ENFORCER_HQ_BRAXS_OFFICE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"布拉克斯的办公室",
			"“督察”级别的执法者被允许拥有自己的办公室，可以根据他们的意愿进行装饰。",
			"dominion/enforcerHQ/office",
			PresetColour.BASE_BLUE_DARK,
			BraxOffice.INTERIOR_BRAX,
			Darkness.ALWAYS_LIGHT,
			null, "于他的办公室") {
		@Override
		public void applyInventoryInit(CharacterInventory inventory) {
			AbstractClothing jacket = Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfdjacket", PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_BLUE, null, false);
			jacket.setSticker("collar", "tab_ip");
			jacket.setSticker("name", "name_brax");
			jacket.setSticker("ribbon", "ribbon_brax");
			inventory.addClothing(jacket);
			
			inventory.addClothing(Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfdbelt", PresetColour.CLOTHING_DESATURATED_BROWN, false));
			
			AbstractClothing hat = Main.game.getItemGen().generateClothing("dsg_eep_ptrlequipset_pcap", PresetColour.CLOTHING_BLACK, false);
			hat.setSticker("badge", "badge_dominion");
			inventory.addClothing(hat);
		}
		@Override
		public boolean isItemsDisappear() {
			return false;
		}
		@Override
		public DialogueNode getDialogue(Cell c, boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.braxEncountered)) {
				return BraxOffice.INTERIOR_BRAX_REPEAT;
			} else {
				return BraxOffice.INTERIOR_BRAX;
			}
		}
		@Override
		public boolean isDangerous() {
			return !Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_C_WOLFS_DEN);
		}
	}.initDangerous()
	.initWeatherImmune();
	
	public static final AbstractPlaceType ENFORCER_HQ_OFFICE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"上锁的办公室",
			"这间办公室的门被锁住，让你不禁好奇办公室里有什么。",
			"dominion/enforcerHQ/office",
			PresetColour.BASE_GREY,
			EnforcerHQDialogue.OFFICE,
			Darkness.ALWAYS_LIGHT,
			null, "于执法者总部")
			.initWeatherImmune();
	
	public static final AbstractPlaceType ENFORCER_HQ_CELLS_OFFICE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"监狱办公室",
			"这间小办公室是所有囚犯进出牢房的登记处",
			"dominion/enforcerHQ/office",
			PresetColour.BASE_PURPLE,
			EnforcerHQDialogue.CELLS_OFFICE,
			Darkness.ALWAYS_LIGHT,
			null, "于执法者总部")
			.initWeatherImmune();
	
	public static final AbstractPlaceType ENFORCER_HQ_CELL = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"监狱",
			"执法者总部的牢房是暂时关押囚犯的地方，直到他们被妥善处理",
			"dominion/enforcerHQ/cell",
			PresetColour.BASE_BROWN_DARK,
			EnforcerHQDialogue.CELL,
			Darkness.ALWAYS_LIGHT,
			null, "于执法者总部的监狱")
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_HQ_ENTRANCE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"门廊",
			"执法者总部的入口是一扇隔音玻璃门",
			"dominion/enforcerHQ/exit",
			PresetColour.BASE_RED,
			EnforcerHQDialogue.ENTRANCE,
			Darkness.ALWAYS_LIGHT,
			null, "")
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_HQ_ENFORCER_ENTRANCE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"执法者入口",
			"这是执法者总部众多非公开入口之一，只有在附近的奥术扫描仪上刷一下特殊通行证，隔音玻璃门才会打开。",
			"dominion/enforcerHQ/exit",
			PresetColour.BASE_BLUE,
			EnforcerHQDialogue.ENTRANCE_ENFORCER,
			Darkness.ALWAYS_LIGHT,
			null, "")
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_HQ_REQUISITIONS = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"前台",
			"在此区域签发专用或替换用的执法者装备。",
			"dominion/enforcerHQ/requisitions",
			PresetColour.BASE_TAN,
			EnforcerHQDialogue.REQUISITIONS,
			Darkness.ALWAYS_LIGHT,
			null, "") {
		@Override
		public List<Population> getPopulation() {
			if(!Main.game.getCharactersPresent(Main.game.getWorlds().get(WorldType.ENFORCER_HQ).getCell(ENFORCER_HQ_REQUISITIONS)).contains(Main.game.getNpc(Wes.class))
					&& !Main.game.getCharactersPresent(Main.game.getWorlds().get(WorldType.ENFORCER_HQ).getCell(ENFORCER_HQ_REQUISITIONS)).contains(Main.game.getNpc(Elle.class))) {
				return Util.newArrayListOfValues(new Population(false, PopulationType.ENFORCER, PopulationDensity.ONE, Util.newHashMapOfValues(new Value<>(Subspecies.DOG_MORPH_GERMAN_SHEPHERD, SubspeciesSpawnRarity.TEN))));
			}
			return super.getPopulation();
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_HQ_OFFICE_QUARTERMASTER = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"军需官办公室",
			"总部军需官负责管理执行者的装备，他们的办公室就设在请购台的对面，非常方便。",
			"dominion/enforcerHQ/office",
			PresetColour.BASE_ORANGE,
			EnforcerHQDialogue.OFFICE_QUARTERMASTER,
			Darkness.ALWAYS_LIGHT,
			null, "")
			.initWeatherImmune();
	
        //Felicia's Apartment       
        
        public static final AbstractPlaceType FELICIA_APARTMENT_ENTRYWAY = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"门厅",
			"在[felicia.NamePos]公寓的入口处有一个旁边的衣帽间。",
			"dominion/feliciaApartment/entranceHall",
			PresetColour.BASE_RED,
			FeliciaApartment.ENTRYWAY,
			Darkness.ALWAYS_LIGHT,
			null,
			"于[felicia.NamePos]公寓的门厅"
        ).initWeatherImmune();
        
        public static final AbstractPlaceType FELICIA_APARTMENT_BEDROOM = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"卧室",
			"PLACEHOLDER_FELICIA_APARTMENT_BEDROOM",
			"dominion/feliciaApartment/feliciaBedroom",
			PresetColour.BASE_YELLOW_PALE,
			FeliciaApartment.FELICIA_BEDROOM,
			Darkness.ALWAYS_LIGHT,
			null,
			"于[felicia.NamePos]的卧室"
        ).initWeatherImmune();
        
        public static final AbstractPlaceType FELICIA_APARTMENT_BATHROOM = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"浴室",
			"PLACEHOLDER_FELICIA_APARTMENT_BATHROOM",
			"dominion/feliciaApartment/toilet",
			PresetColour.BASE_BLUE_LIGHT,
			FeliciaApartment.BATHROOM,
			Darkness.ALWAYS_LIGHT,
			null,
			"于[felicia.NamePos]公寓的浴室"
        ).initWeatherImmune();
        
        public static final AbstractPlaceType FELICIA_APARTMENT_KITCHEN = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"厨房",
			"宽敞的厨房与走廊和餐厅相连。",
			"dominion/feliciaApartment/kitchen",
			PresetColour.BASE_ORANGE,
			FeliciaApartment.KITCHEN,
			Darkness.ALWAYS_LIGHT,
			null,
			"于[felicia.NamePos]公寓的厨房"
        ).initWeatherImmune();
        
        public static final AbstractPlaceType FELICIA_APARTMENT_DINING_AREA = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"用餐区",
			"[felicia.NamePos]公寓的用餐区即使只坐一个人也显得十分局促。",
			"dominion/feliciaApartment/diningArea",
			PresetColour.BASE_BLUE_STEEL,
			FeliciaApartment.DINING_AREA,
			Darkness.ALWAYS_LIGHT,
			null,
			"于[felicia.NamePos]公寓的用餐区"
        ).initWeatherImmune();
        
        public static final AbstractPlaceType FELICIA_APARTMENT_LIVING_AREA = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"居住区",
			"[felicia.Name]的客厅装饰简约，但可以俯瞰到御城区的街道。",
			"dominion/feliciaApartment/livingArea",
			PresetColour.BASE_INDIGO,
			FeliciaApartment.LIVING_AREA,
			Darkness.ALWAYS_LIGHT,
			null,
			"于[felicia.NamePos]公寓的居住区"
        ).initWeatherImmune();
        
        public static final AbstractPlaceType FELICIA_APARTMENT_HALLWAY = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"走廊",
			"这个走廊没有任何装饰或家具。",
			null,
			PresetColour.BASE_BLACK,
			FeliciaApartment.HALLWAY,
			Darkness.ALWAYS_LIGHT,
			null,
			"于[felicia.NamePos]公寓的走廊"
        ).initWeatherImmune();
        
	// Enforcer warehouse:
	
	public static final AbstractPlaceType ENFORCER_WAREHOUSE_ENTRANCE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"入口",
			"唯一通往仓库的入口由一个小型执法者值勤的亭子看守着。",
			"dominion/enforcerWarehouse/exit",
			PresetColour.BASE_RED,
			EnforcerWarehouse.ENTRANCE,
			Darkness.ALWAYS_LIGHT,
			null, "于执法者仓库")
			.initDangerous()
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_WAREHOUSE_CORRIDOR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"走廊",
			"仓库内有许多走廊，两边蜿蜒曲折地堆满了木箱。",
			null,
			PresetColour.BASE_BLACK,
			EnforcerWarehouse.CORRIDOR,
			Darkness.ALWAYS_LIGHT,
			null, "于执法者仓库")
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_WAREHOUSE_CLAIRE_WARNING = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"走廊",
			"仓库内有许多走廊，两边蜿蜒曲折地堆满了木箱。",
			null,
			PresetColour.BASE_BLACK,
			EnforcerWarehouse.CLAIRE_WARNING,
			Darkness.ALWAYS_LIGHT,
			null, "于执法者仓库")
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_WAREHOUSE_ENCLOSURE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"围起的区域",
			"在仓库的一个被遗忘的角落，四面都被高耸的木箱堆栈围绕着。",
			null,
			PresetColour.BASE_BLACK,
			EnforcerWarehouse.ENCLOSURE,
			Darkness.ALWAYS_LIGHT,
			null, "于执法者仓库")
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_WAREHOUSE_ENCLOSURE_TELEPORT_PADS = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"传送板",
			"你和克莱尔到达的传送板就在这个区域。",
			"dominion/enforcerWarehouse/teleportPads",
			PresetColour.BASE_MAGENTA,
			EnforcerWarehouse.ENCLOSURE_TELEPORT_PADS,
			Darkness.ALWAYS_LIGHT,
			null, "于执法者仓库")
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_WAREHOUSE_ENCLOSURE_TELEPORT_SHELVING = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"货架",
			"这个角落里堆放着几个独立的货架。",
			"dominion/enforcerWarehouse/shelving",
			PresetColour.BASE_PURPLE_LIGHT,
			EnforcerWarehouse.ENCLOSURE_SHELVING,
			Darkness.ALWAYS_LIGHT,
			null, "于执法者仓库")
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_WAREHOUSE_ENFORCER_GUARD_POST = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"执法者岗哨",
			"仓库里散布着几个执法者岗哨，这些岗哨只有一把椅子和一张桌子。",
			"dominion/enforcerWarehouse/enforcerGuardPost",
			PresetColour.BASE_BLUE_STEEL,
			EnforcerWarehouse.ENFORCER_GUARD_POST,
			Darkness.ALWAYS_LIGHT,
			null, "于执法者仓库")
			.initDangerous()
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_WAREHOUSE_CRATES = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"板条箱",
			"在仓库的这个区域，还有一两个箱子还没有封上。",
			"dominion/enforcerWarehouse/crates",
			PresetColour.BASE_ORANGE,
			EnforcerWarehouse.CRATES,
			Darkness.ALWAYS_LIGHT,
			null, "于执法者仓库")
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_WAREHOUSE_CRATES_SEARCHED = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"板条箱(已搜索)",
			"在仓库的这一区域，还有一两个箱子还没有被封上，因此你可以在其中进行搜索。",
			"dominion/enforcerWarehouse/cratesSearched",
			PresetColour.BASE_GREY,
			EnforcerWarehouse.CRATES,
			Darkness.ALWAYS_LIGHT,
			null, "于执法者仓库")
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_WAREHOUSE_CRATES_ARK = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"板条箱",
			"在仓库的这个区域，还有一两个箱子还没有封上。",
			"dominion/enforcerWarehouse/crates",
			PresetColour.BASE_ORANGE,
			EnforcerWarehouse.CRATES_ARK,
			Darkness.ALWAYS_LIGHT,
			null, "于执法者仓库")
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_WAREHOUSE_CRATES_ARK_SEARCHED = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"板条箱(已搜索)",
			"在仓库的这一区域，还有一两个箱子还没有被封上，因此你可以在其中进行搜索。",
			"dominion/enforcerWarehouse/cratesSearched",
			PresetColour.BASE_GREY,
			EnforcerWarehouse.CRATES_ARK,
			Darkness.ALWAYS_LIGHT,
			null, "于执法者仓库")
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_WAREHOUSE_CRATES_LUST_WEAPON = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"“绝密”板条箱",
			"该区域的一个箱子被标记为“绝密”。",
			"dominion/enforcerWarehouse/cratesLustWeapon",
			PresetColour.BASE_PINK_DEEP,
			EnforcerWarehouse.CRATES_LUST_WEAPON,
			Darkness.ALWAYS_LIGHT,
			null, "于执法者仓库")
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_WAREHOUSE_CRATES_SPELL_BOOK = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"货架",
			"这个特殊区域的书架上摆满了禁书和其他违禁品，而不是仓库里常见的板条箱。",
			"dominion/enforcerWarehouse/shelvingSpellBook",
			PresetColour.BASE_MAGENTA,
			EnforcerWarehouse.SHELVES_SPELL_BOOK,
			Darkness.ALWAYS_LIGHT,
			null, "于执法者仓库")
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_WAREHOUSE_CRATES_SPELL_BOOK_SEARCHED = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"货架(已搜索)",
			"与仓库中到处都是的普通货箱不同，这个特定区域放置了满满的被禁书籍和其他违禁文件。你已经搜索过，并找到了一本法术书。",
			"dominion/enforcerWarehouse/shelvingSearched",
			PresetColour.BASE_GREY,
			EnforcerWarehouse.SHELVES_SPELL_BOOK,
			Darkness.ALWAYS_LIGHT,
			null, "于执法者仓库")
			.initWeatherImmune();
	
	
	// City hall:
	
	public static final AbstractPlaceType CITY_HALL_CORRIDOR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"走廊",
			"御城区市政厅的大理石走廊让常驻的官僚们可以轻松地从一个办公室大步走到另一个办公室。",
			null,
			PresetColour.BASE_BLACK,
			CityHall.CITY_HALL_CORRIDOR,
			Darkness.ALWAYS_LIGHT,
			null, "于御城区市政厅") {

		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.OFFICE_WORKER, PopulationDensity.SEVERAL, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType CITY_HALL_ENTRANCE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"入口",
			"市政厅的入口是一对旋转玻璃门，其中一扇标有“出口”，另一扇标有“入口：禁止入内”。",
			"dominion/cityHall/exit",
			PresetColour.BASE_RED,
			CityHall.CITY_HALL_FOYER,
			Darkness.ALWAYS_LIGHT,
			null, "于御城区的市政厅") {

		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.PERSON, PopulationDensity.SEVERAL, Subspecies.getDominionStormImmuneSpecies(true)));
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType CITY_HALL_INFORMATION_DESK = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"咨询台",
			"偌大的门厅中央摆放着一张圆形办公桌，坐着几位接待员。",
			"dominion/cityHall/front_desk",
			PresetColour.BASE_BLUE_LIGHT,
			CityHall.CITY_HALL_INFORMATION_DESK,
			Darkness.ALWAYS_LIGHT,
			null, "于御城区的市政厅") {

		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.PERSON, PopulationDensity.SEVERAL, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType CITY_HALL_WAITING_AREA = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"等待室",
			"在开放式等待室的另一侧，悬挂着一个大型模拟时钟，秒针缓慢地滴答作响，时刻提醒着在场的所有人，官僚机构的效率低得令人头疼。",
			"dominion/cityHall/waiting_area",
			PresetColour.BASE_PURPLE_LIGHT,
			CityHall.CITY_HALL_WAITING_AREA,
			Darkness.ALWAYS_LIGHT,
			null, "于御城区的市政厅") {

		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.PERSON, PopulationDensity.NUMEROUS, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType CITY_HALL_OFFICE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"办公室",
			"这间办公室的门上标有“私人”字样，让所有人都知道里面绝对没有发生任何重要的事情。",
			"dominion/cityHall/office",
			PresetColour.BASE_ORANGE,
			CityHall.CITY_HALL_OFFICE,
			Darkness.ALWAYS_LIGHT,
			null, "于御城区的市政厅") {
	}.initWeatherImmune();

	public static final AbstractPlaceType CITY_HALL_STAIRS = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"楼梯",
			"通往市政厅高层的楼梯被标注为私人通道，并用红绳隔离。",
			"dominion/cityHall/stairs",
			PresetColour.BASE_GREY,
			CityHall.CITY_HALL_STAIRS,
			Darkness.ALWAYS_LIGHT,
			null, "于御城区的市政厅") {
	}.initWeatherImmune();
	
	public static final AbstractPlaceType CITY_HALL_BUREAU_OF_DEMOGRAPHICS = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"人口统计局",
			"“人口统计局”由一间小办公室组成，毗邻一个巨大的、类似图书馆的储藏室。",
			"dominion/cityHall/officeDemographics",
			PresetColour.BASE_TEAL,
			CityHallDemographics.CITY_HALL_DEMOGRAPHICS_ENTRANCE,
			Darkness.ALWAYS_LIGHT,
			null, "于御城区的市政厅") {
	}.initWeatherImmune();

	public static final AbstractPlaceType CITY_HALL_ARCHIVES = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"人口统计局(档案室)",
			"“人口统计局”由一间小办公室组成，毗邻一个巨大的、类似图书馆的储藏室。",
			"dominion/cityHall/officeDemographicsArchives",
			PresetColour.BASE_BLUE, // Player cannot enter this tile.
			CityHallDemographics.CITY_HALL_DEMOGRAPHICS_ENTRANCE,
			Darkness.ALWAYS_LIGHT,
			null, "于御城区的市政厅") {
	}.initWeatherImmune();

	public static final AbstractPlaceType CITY_HALL_BUREAU_OF_PROPERTY_RIGHTS_AND_COMMERCE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"走廊",
			"“商业财政部”是市政厅中规模最大、资金最充足的部门之一，由许多相互关联的办公室和会议室组成。",
			"dominion/cityHall/officeProperty",
			PresetColour.BASE_GOLD,
			CityHallProperty.CITY_HALL_PROPERTY_ENTRANCE,
			Darkness.ALWAYS_LIGHT,
			null, "于御城区的市政厅") {
	}.initWeatherImmune();

	
	// Home Improvements:
	
	public static final AbstractPlaceType HOME_IMPROVEMENTS_CORRIDOR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"过道",
			"宽阔的水泥地面过道贯穿整个仓库，为顾客提供了充足的空间，让他们可以推着手推车走过别人身边。",
			null,
			PresetColour.BASE_BLACK,
			HomeImprovements.CORRIDOR,
			Darkness.ALWAYS_LIGHT,
			null, "在'阿格斯的DIY仓库'里") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.SHOPPER, PopulationDensity.SEVERAL, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType HOME_IMPROVEMENTS_ENTRANCE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"入口",
			"市政厅的入口是一对旋转玻璃门，其中一扇标有“出口”，另一扇标有“入口：禁止入内”。",
			"dominion/homeImprovements/exit",
			PresetColour.BASE_RED,
			HomeImprovements.ENTRANCE,
			Darkness.ALWAYS_LIGHT,
			null, "在'阿格斯的DIY仓库'里") {
		@Override
		public List<Population> getPopulation() {
			return HOME_IMPROVEMENTS_CORRIDOR.getPopulation();
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType HOME_IMPROVEMENTS_SHELVING_PREMIUM = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"货架(高级)",
			"正对大门的货架上摆满了“高级”油漆、工具和其他 DIY 杂项用品。",
			"dominion/homeImprovements/shelving",
			PresetColour.BASE_GOLD,
			HomeImprovements.SHELVING_PREMIUM,
			Darkness.ALWAYS_LIGHT,
			null, "在'阿格斯的DIY仓库'里") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.SHOPPER, PopulationDensity.FEW, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType HOME_IMPROVEMENTS_SHELVING_STANDARD = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"货架(常规)",
			"主通道旁的货架上摆满了“标准”油漆、工具和其他 DIY 杂项用品。",
			"dominion/homeImprovements/shelving",
			PresetColour.BASE_BROWN,
			HomeImprovements.SHELVING_STANDARD,
			Darkness.ALWAYS_LIGHT,
			null, "在'阿格斯的DIY仓库'里") {
		@Override
		public List<Population> getPopulation() {
			return HOME_IMPROVEMENTS_CORRIDOR.getPopulation();
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType HOME_IMPROVEMENTS_BUILDING_SUPPLIES = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"建筑素材",
			"在靠近仓库后面的地方，有许多货架，上面堆满了木材、瓷砖、管道和其他各种建筑材料。",
			"dominion/homeImprovements/crates",
			PresetColour.BASE_ORANGE,
			HomeImprovements.BUILDING_SUPPLIES,
			Darkness.ALWAYS_LIGHT,
			null, "在'阿格斯的DIY仓库'里") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.SHOPPER, PopulationDensity.COUPLE, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType HOME_IMPROVEMENTS_OFFICE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"经理办公室",
			"阿格斯是这家公司的所有人兼经理，他的办公室就在仓库入口附近。",
			"dominion/homeImprovements/office",
			PresetColour.BASE_MAGENTA,
			HomeImprovements.OFFICE,
			Darkness.ALWAYS_LIGHT,
			null, "于阿格斯的办公室")
	.initWeatherImmune();

	public static final AbstractPlaceType HOME_IMPROVEMENTS_TOILETS = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"厕所",
			"在仓库后面的角落里，有一些供顾客使用的厕所。",
			"dominion/homeImprovements/toilets",
			PresetColour.BASE_BLUE_LIGHT,
			HomeImprovements.TOILETS,
			Darkness.ALWAYS_LIGHT,
			null, "在'阿格斯的DIY仓库'的厕所里")
	.initWeatherImmune();

	
	// Dominion Express:
	
	public static final AbstractPlaceType DOMINION_EXPRESS_CORRIDOR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"走廊",
			"这条走廊贯穿整个仓库，有效地将开放式仓储区与企业的众多办公室分割开来。",
			null,
			PresetColour.BASE_BLACK,
			DominionExpress.CORRIDOR,
			Darkness.ALWAYS_LIGHT, Encounter.DOMINION_EXPRESS, "与“御城速递”的仓库") {
		@Override
		public List<Population> getPopulation() {
			if(Main.game.isExtendedWorkTime()) {
				return Util.newArrayListOfValues(new Population(true, PopulationType.SLAVE, PopulationDensity.SEVERAL, Util.newHashMapOfValues(new Value<>(Subspecies.CENTAUR, SubspeciesSpawnRarity.TEN))));
			}
			return Util.newArrayListOfValues(new Population(true, PopulationType.SLAVE, PopulationDensity.COUPLE, Util.newHashMapOfValues(new Value<>(Subspecies.CENTAUR, SubspeciesSpawnRarity.TEN))));
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType DOMINION_EXPRESS_EXIT = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"入口",
			"仓库入口的一侧有一个单独的接待台，秘书们坐在接待台后面，确保来访者都有来这里的理由。",
			"dominion/dominionExpress/exit",
			PresetColour.BASE_RED,
			DominionExpress.ENTRANCE,
			Darkness.ALWAYS_LIGHT,
			null, "于“御城速递”的仓库") {
		@Override
		public List<Population> getPopulation() {
			if(Main.game.isExtendedWorkTime()) {
				return Util.newArrayListOfValues(new Population(true, PopulationType.RECEPTIONIST, PopulationDensity.FEW, Util.newHashMapOfValues(new Value<>(Subspecies.HORSE_MORPH, SubspeciesSpawnRarity.TEN))));
			}
			return Util.newArrayListOfValues(new Population(false, PopulationType.RECEPTIONIST, PopulationDensity.ONE, Util.newHashMapOfValues(new Value<>(Subspecies.HORSE_MORPH, SubspeciesSpawnRarity.TEN))));
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType DOMINION_EXPRESS_STORAGE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"储存区",
			"仓库一半的面积用于临时存放货物。",
			"dominion/dominionExpress/crates",
			PresetColour.BASE_ORANGE,
			DominionExpress.STORAGE,
			Darkness.ALWAYS_LIGHT, Encounter.DOMINION_EXPRESS, "与“御城速递”的仓库") {
		@Override
		public List<Population> getPopulation() {
			return DOMINION_EXPRESS_CORRIDOR.getPopulation();
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType DOMINION_EXPRESS_OFFICE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"办公室",
			"“御城速递”的日常运作由这些办公室负责。",
			"dominion/dominionExpress/office",
			PresetColour.BASE_BLUE_LIGHT,
			DominionExpress.OFFICE,
			Darkness.ALWAYS_LIGHT,
			null, "于“御城速递”的仓库") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(false, PopulationType.OFFICE_WORKER, PopulationDensity.OCCASIONAL, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType DOMINION_EXPRESS_FILLY_STATION = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"[style.Mule]奖品站",
			"在仓库走廊一侧的一个小凹槽里，有一台外形奇特的奥术自动售货机，上面清楚地标明这是“[style.Mule]奖品站”。",
			"dominion/dominionExpress/fillyStation",
			PresetColour.BASE_PINK_LIGHT,
			DominionExpress.FILLY_STATION,
			Darkness.ALWAYS_LIGHT,
			null, "于“御城速递”的仓库")
		.initWeatherImmune();
	
	public static final AbstractPlaceType DOMINION_EXPRESS_OFFICE_STABLE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"马场主办公室",
			"负责照顾和管理半人马奴隶的办公室位于仓库走廊的最远处。",
			"dominion/dominionExpress/officeStable",
			PresetColour.BASE_TAN,
			DominionExpress.OFFICE_STABLE,
			Darkness.ALWAYS_LIGHT,
			null, "于“御城速递”仓库的马场主办公室")
	.initWeatherImmune();

	public static final AbstractPlaceType DOMINION_EXPRESS_STABLES = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"马厩",
			"这座单层的大型附属建筑内有大量马厩，每个马厩都足够容纳一名半人马奴隶。",
			"dominion/dominionExpress/stables",
			PresetColour.BASE_BROWN,
			DominionExpress.STABLES,
			Darkness.ALWAYS_LIGHT,
			null, "于“御城速递”仓库的马厩中") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.SLAVE, PopulationDensity.NUMEROUS, Util.newHashMapOfValues(new Value<>(Subspecies.CENTAUR, SubspeciesSpawnRarity.TEN))));
		}
	}.initWeatherImmune();
	
	
	
	// Harpy Nests:
	
	public static final AbstractPlaceType HARPY_NESTS_WALKWAYS = new AbstractPlaceType(
			WorldRegion.HARPY_NESTS,
			"通道",
			"哈比巢穴之间通过御城区住宅楼顶上修建的狭窄木制栈道相连。",
			null,
			PresetColour.BASE_BLACK,
			HarpyNestsDialogue.WALKWAY,
			Darkness.ALWAYS_LIGHT, Encounter.HARPY_NEST_WALKWAYS, "于哈比之巢") {
		@Override
		public boolean isDangerous() {
			return !Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_HARPY_PACIFICATION) || Main.game.getCurrentWeather()==Weather.MAGIC_STORM;
		}
		@Override
		public List<Population> getPopulation() {
			if(Main.game.getCurrentWeather() == Weather.MAGIC_STORM) {
				return super.getPopulation();
			} else {
				return Util.newArrayListOfValues(new Population(true, PopulationType.HARPY, PopulationDensity.NUMEROUS, Subspecies.getWorldSpecies(WorldType.HARPY_NEST, this, false, false)));
			}
		}
	}.initSexNotBlockedFromCharacterPresent();
	
	public static final AbstractPlaceType HARPY_NESTS_WALKWAYS_BRIDGE = new AbstractPlaceType(
			WorldRegion.HARPY_NESTS,
			"通行桥",
			"在这里和那里，桥梁横跨下方的街道，将一组栈道与另一组栈道连接起来。",
			"dominion/harpyNests/bridge",
			PresetColour.BASE_GREY,
			HarpyNestsDialogue.WALKWAY_BRIDGE,
			Darkness.ALWAYS_LIGHT, Encounter.HARPY_NEST_WALKWAYS, "于哈比之巢") {
		@Override
		public boolean isDangerous() {
			return !Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_HARPY_PACIFICATION) || Main.game.getCurrentWeather()==Weather.MAGIC_STORM;
		}
		@Override
		public List<Population> getPopulation() {
			return HARPY_NESTS_WALKWAYS.getPopulation();
		}
	}.initSexNotBlockedFromCharacterPresent();

	public static final AbstractPlaceType HARPY_NESTS_ENTRANCE_ENFORCER_POST = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"执法者岗哨",
			"为了维持无数争吵不休的哈比之间的和平，需要一个人员充足的执法者前哨站。",
			"dominion/harpyNests/exit",
			PresetColour.BASE_RED,
			HarpyNestsDialogue.ENTRANCE_ENFORCER_POST,
			Darkness.ALWAYS_LIGHT,
			null, "于哈比之巢") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.ENFORCER, PopulationDensity.NUMEROUS, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true, Subspecies.HUMAN)));
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType HARPY_NESTS_HELENAS_NEST = new AbstractPlaceType(
			WorldRegion.HARPY_NESTS,
			"海伦娜的巢",
			"美艳绝伦的哈比族长海伦娜统治着最大的哈比之巢。",
			"dominion/harpyNests/nestHelena",
			PresetColour.BASE_GOLD,
			HarpyNestHelena.HELENAS_NEST_EXTERIOR,
			Darkness.ALWAYS_LIGHT,
			null, "于海伦娜的巢"){
		@Override
		public List<Population> getPopulation() {
			return HARPY_NESTS_WALKWAYS.getPopulation();
		}
	};
	
	public static final AbstractPlaceType HARPY_NESTS_HARPY_NEST_RED = new AbstractPlaceType(
			WorldRegion.HARPY_NESTS,
			"哈比之巢",
			"狄安娜的巢穴主要由愤怒的红色哈比巢穴组成；它们羽毛的颜色是为了模仿它们虐待狂首领的样子。",
			"dominion/harpyNests/nestRed",
			PresetColour.BASE_CRIMSON,
			null,
			Darkness.ALWAYS_LIGHT,
			null, "于狄安娜的巢"){
		@Override
		public List<Population> getPopulation() {
			return HARPY_NESTS_WALKWAYS.getPopulation();
		}
		@Override
		protected DialogueNode getBaseDialogue(Cell cell) {
			return DialogueManager.getDialogueFromId("innoxia_places_dominion_harpy_nests_dominant_exterior");
		}
	};
	
	public static final AbstractPlaceType HARPY_NESTS_HARPY_NEST_PINK = new AbstractPlaceType(
			WorldRegion.HARPY_NESTS,
			"哈比之巢",
			"莱克西的巢穴里有很多哈比巢穴里的雄性，每个雄性都希望能和自己喜欢性爱的主人做爱。",
			"dominion/harpyNests/nestPink",
			PresetColour.BASE_PINK_LIGHT,
			null,
			Darkness.ALWAYS_LIGHT,
			null, "于莱克西的巢"){
		@Override
		public List<Population> getPopulation() {
			return HARPY_NESTS_WALKWAYS.getPopulation();
		}
		@Override
		protected DialogueNode getBaseDialogue(Cell cell) {
			return DialogueManager.getDialogueFromId("innoxia_places_dominion_harpy_nests_nympho_exterior");
		}
	};
	
	public static final AbstractPlaceType HARPY_NESTS_HARPY_NEST_YELLOW = new AbstractPlaceType(
			WorldRegion.HARPY_NESTS,
			"哈比之巢",
			"布里塔尼的巢穴里有相当多的浅金色羽毛、胸大无脑的哈比。",
			"dominion/harpyNests/nestYellow",
			PresetColour.BASE_YELLOW_LIGHT,
			null,
			Darkness.ALWAYS_LIGHT,
			null, "于布里塔尼的巢"){
		@Override
		public List<Population> getPopulation() {
			return HARPY_NESTS_WALKWAYS.getPopulation();
		}
		@Override
		protected DialogueNode getBaseDialogue(Cell cell) {
			return DialogueManager.getDialogueFromId("innoxia_places_dominion_harpy_nests_bimbo_exterior");
		}
	};
	
			
	// Lilaya's home (ground floor):
	
	public static final AbstractPlaceType LILAYA_HOME_CORRIDOR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"走廊",
			"莉莱雅家的每条走廊中央都铺着干净整洁的红地毯，墙上挂着精美的油画和大理石雕刻的半身像。",
			null,
			PresetColour.BASE_GREY,
			LilayaHomeGeneric.CORRIDOR,
			Darkness.ALWAYS_LIGHT,
			Encounter.LILAYAS_HOME_CORRIDOR,
			"莉莱雅的家中"
		).initItemsPersistInTile()
		.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_ROOM_WINDOW_GROUND_FLOOR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"房间",
			"包括这间客房在内，一楼的外层房间都有窗户，可以看到御城区的街道，或是房子周围的私人小巷。",
			"dominion/lilayasHome/room",
			PresetColour.BASE_GREY,
			LilayaHomeGeneric.ROOM_WINDOW,
			Darkness.ALWAYS_LIGHT,
			null,
			"莉莱雅的家中") {
		@Override
		protected DialogueNode getBaseDialogue(Cell cell) {
			if(cell!=null) {
				for(AbstractPlaceUpgrade pu : cell.getPlace().getPlaceUpgrades()) {
					if(pu.getRoomDialogue(cell)!=null) {
						return pu.getRoomDialogue(cell);
					}
				}
			}
			return LilayaHomeGeneric.ROOM_WINDOW;
		}
		@Override
		public ArrayList<AbstractPlaceUpgrade> getStartingPlaceUpgrades() {
			return Util.newArrayListOfValues(PlaceUpgrade.LILAYA_EMPTY_ROOM);
		}
		@Override
		public ArrayList<AbstractPlaceUpgrade> getAvailablePlaceUpgrades(Set<AbstractPlaceUpgrade> upgrades) {
			if(upgrades.contains(PlaceUpgrade.LILAYA_GUEST_ROOM)) {
				return PlaceUpgrade.getGuestRoomUpgrades();
				
			} else if(upgrades.contains(PlaceUpgrade.LILAYA_SLAVE_ROOM)) {
				return PlaceUpgrade.getSlaveQuartersUpgradesSingle();
				
			} else if(upgrades.contains(PlaceUpgrade.LILAYA_SLAVE_ROOM_DOUBLE)) {
				return PlaceUpgrade.getSlaveQuartersUpgradesDouble();
				
			} else if(upgrades.contains(PlaceUpgrade.LILAYA_SLAVE_ROOM_QUADRUPLE)) {
				return PlaceUpgrade.getSlaveQuartersUpgradesQuadruple();
				
			} else if(upgrades.contains(PlaceUpgrade.LILAYA_MILKING_ROOM)) {
				return PlaceUpgrade.getMilkingUpgrades();
				
			} else if(upgrades.contains(PlaceUpgrade.LILAYA_OFFICE)) {
				return PlaceUpgrade.getOfficeUpgrades();
				
			} else if(upgrades.contains(PlaceUpgrade.LILAYA_SPA)) {
				return PlaceUpgrade.getSpaUpgrades();
				
			} else if(upgrades.contains(PlaceUpgrade.LILAYA_DINING_HALL)) {
				return PlaceUpgrade.getDiningHallUpgrades();
				
			} else if(upgrades.contains(PlaceUpgrade.LILAYA_SLAVE_LOUNGE)) {
				return PlaceUpgrade.getSlaveLoungeUpgrades();
				
			} else if(upgrades.contains(PlaceUpgrade.LILAYA_DRESSING_ROOM)) {
				return PlaceUpgrade.getDressingRoomUpgrades();
			}
			
			return PlaceUpgrade.getCoreRoomUpgrades();
		}
		@Override
		public boolean isAbleToBeUpgraded() {
			return true;
		}
		@Override
		public String getPlaceNameAppendFormat(int count) {
			return " G-"+String.format("%02d", count);
		}
	}.initItemsPersistInTile()
	.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_ROOM_GARDEN_GROUND_FLOOR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"花园房",
			"一楼的内层房间均通过庭院门与私人花园相连。",
			"dominion/lilayasHome/room",
			PresetColour.BASE_GREY,
			LilayaHomeGeneric.ROOM_GARDEN_GROUND_FLOOR,
			Darkness.ALWAYS_LIGHT,
			null, "莉莱雅的家中") {
		@Override
		protected DialogueNode getBaseDialogue(Cell cell) {
			if(cell!=null) {
				for(AbstractPlaceUpgrade pu : cell.getPlace().getPlaceUpgrades()) {
					if(pu.getRoomDialogue(cell)!=null) {
						return pu.getRoomDialogue(cell);
					}
				}
			}
			return LilayaHomeGeneric.ROOM_GARDEN_GROUND_FLOOR;
		}
		@Override
		public ArrayList<AbstractPlaceUpgrade> getStartingPlaceUpgrades() {
			return Util.newArrayListOfValues(PlaceUpgrade.LILAYA_EMPTY_ROOM);
		}
		@Override
		public ArrayList<AbstractPlaceUpgrade> getAvailablePlaceUpgrades(Set<AbstractPlaceUpgrade> upgrades) {
			return LILAYA_HOME_ROOM_WINDOW_GROUND_FLOOR.getAvailablePlaceUpgrades(upgrades);
		}
		@Override
		public boolean isAbleToBeUpgraded() {
			return true;
		}
		@Override
		public String getPlaceNameAppendFormat(int count) {
			return " GG-"+String.format("%02d", count);
		}
	}.initItemsPersistInTile()
	.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_ROOM_WINDOW_FIRST_FLOOR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"房间",
			"二楼的外层房间，包括这间，都有窗户，可以看到御城区的街道，或是房子周围的私人小巷。",
			"dominion/lilayasHome/room",
			PresetColour.BASE_GREY,
			LilayaHomeGeneric.ROOM_WINDOW,
			Darkness.ALWAYS_LIGHT,
			null, "莉莱雅的家中") {
		@Override
		protected DialogueNode getBaseDialogue(Cell cell) {
			if(cell!=null) {
				for(AbstractPlaceUpgrade pu : cell.getPlace().getPlaceUpgrades()) {
					if(pu.getRoomDialogue(cell)!=null) {
						return pu.getRoomDialogue(cell);
					}
				}
			}
			return LilayaHomeGeneric.ROOM_WINDOW;
		}
		@Override
		public ArrayList<AbstractPlaceUpgrade> getStartingPlaceUpgrades() {
			return Util.newArrayListOfValues(PlaceUpgrade.LILAYA_EMPTY_ROOM);
		}
		@Override
		public ArrayList<AbstractPlaceUpgrade> getAvailablePlaceUpgrades(Set<AbstractPlaceUpgrade> upgrades) {
			return LILAYA_HOME_ROOM_WINDOW_GROUND_FLOOR.getAvailablePlaceUpgrades(upgrades);
		}
		@Override
		public boolean isAbleToBeUpgraded() {
			return true;
		}
		@Override
		public String getPlaceNameAppendFormat(int count) {
			return " F-"+String.format("%02d", count);
		}
	}.initItemsPersistInTile()
	.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_ROOM_GARDEN_FIRST_FLOOR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"花园房",
			"二楼的包括这间房的内层房间都有窗户，可以俯瞰私人花园。",
			"dominion/lilayasHome/room",
			PresetColour.BASE_GREY,
			LilayaHomeGeneric.ROOM_GARDEN,
			Darkness.ALWAYS_LIGHT,
			null, "莉莱雅的家中") {
		@Override
		protected DialogueNode getBaseDialogue(Cell cell) {
			if(cell!=null) {
				for(AbstractPlaceUpgrade pu : cell.getPlace().getPlaceUpgrades()) {
					if(pu.getRoomDialogue(cell)!=null) {
						return pu.getRoomDialogue(cell);
					}
				}
			}
			return LilayaHomeGeneric.ROOM_GARDEN;
		}
		@Override
		public ArrayList<AbstractPlaceUpgrade> getStartingPlaceUpgrades() {
			return Util.newArrayListOfValues(PlaceUpgrade.LILAYA_EMPTY_ROOM);
		}
		@Override
		public ArrayList<AbstractPlaceUpgrade> getAvailablePlaceUpgrades(Set<AbstractPlaceUpgrade> upgrades) {
			return LILAYA_HOME_ROOM_WINDOW_GROUND_FLOOR.getAvailablePlaceUpgrades(upgrades);
		}
		@Override
		public boolean isAbleToBeUpgraded() {
			return true;
		}
		@Override
		public String getPlaceNameAppendFormat(int count) {
			return " FG-"+String.format("%02d", count);
		}
	}.initItemsPersistInTile()
	.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_DUNGEON_CELL = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"地牢隔间",
			"莉莱雅地牢的牢房设计得狭窄而不舒适。",
			"dominion/lilayasHome/roomSlave",
			PresetColour.BASE_GREY,//BASE_MAGENTA
			LilayaHomeGeneric.DUNGEON_CELL,
			Darkness.ALWAYS_LIGHT,
			null,
			"于莉莱雅的地牢") {
		@Override
		protected DialogueNode getBaseDialogue(Cell cell) {
			if(cell!=null) {
				for(AbstractPlaceUpgrade pu : cell.getPlace().getPlaceUpgrades()) {
					if(pu.getRoomDialogue(cell)!=null) {
						return pu.getRoomDialogue(cell);
					}
				}
			}
			return LilayaHomeGeneric.DUNGEON_CELL;
		}
		@Override
		public ArrayList<AbstractPlaceUpgrade> getStartingPlaceUpgrades() {
			return Util.newArrayListOfValues(PlaceUpgrade.LILAYA_DUNGEON_CELL);
		}
		@Override
		public ArrayList<AbstractPlaceUpgrade> getAvailablePlaceUpgrades(Set<AbstractPlaceUpgrade> upgrades) {
			return PlaceUpgrade.getDungeonCellUpgrades();
		}
		@Override
		public boolean isAbleToBeUpgraded() {
			return true;
		}
		@Override
		public String getPlaceNameAppendFormat(int count) {
			return " D-"+String.format("%02d", count);
		}
	}.initItemsPersistInTile()
	.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_ARTHUR_ROOM = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"亚瑟的房间",
			"由一间储藏室改建而成，里面住着莉莱雅曾经的情人和同事亚瑟。",
			"dominion/lilayasHome/roomArthur",
			PresetColour.BASE_BLUE_STEEL,
			RoomArthur.ROOM_ARTHUR,
			Darkness.ALWAYS_LIGHT,
			null, "亚瑟的房间内"
			).initItemsPersistInTile()
			.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_BIRTHING_ROOM = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"房间",
			"你不太清楚为什么莉莱雅会有一个专门的产房，但你猜想它一定曾经用于奥术研究。",
			"dominion/lilayasHome/roomBirthing",
			PresetColour.BASE_PINK,
			LilayaHomeGeneric.BIRTHING_ROOM,
			Darkness.ALWAYS_LIGHT,
			null, "莉莱雅的家中"
			).initItemsPersistInTile()
			.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_KITCHEN = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"厨房",
			"在房子的后面，有一个设备齐全的大厨房。",
			"dominion/lilayasHome/kitchen",
			PresetColour.BASE_TAN,
			LilayaHomeGeneric.KITCHEN,
			Darkness.ALWAYS_LIGHT,
			null, "莉莱雅的厨房内"
			).initItemsPersistInTile()
			.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_LIBRARY = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"图书馆",
			"一楼的角落有一间巨大的图书馆，卷帙繁浩如烟海。",
			"dominion/lilayasHome/library",
			PresetColour.BASE_TEAL,
			Library.LIBRARY,
			Darkness.ALWAYS_LIGHT,
			null, "于莉莱雅的图书馆") {
		@Override
		public void applyInventoryInit(CharacterInventory inventory) {
			inventory.addItem(Main.game.getItemGen().generateItem(ItemType.getLoreBook(Subspecies.HALF_DEMON)));
		}
	}.initItemsPersistInTile()
	.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_STAIR_UP = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"楼梯",
			"这个楼梯连接着莉莱雅家的一楼和二楼，中途还有一个小的辅助楼梯间。",
			"dominion/lilayasHome/stairsUp",
			PresetColour.BASE_GREEN_LIGHT,
			LilayaHomeGeneric.STAIRCASE_UP,
			Darkness.ALWAYS_LIGHT,
			null, "莉莱雅的家中"
			).initItemsPersistInTile()
			.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_STAIR_UP_SECONDARY = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"楼梯",
			"这个楼梯虽然比大宅入口处的主楼梯小一些，但连接莉莱雅家一楼和二楼的功能是一样的。",
			"dominion/lilayasHome/stairsUpSecondary",
			PresetColour.BASE_GREEN_LIME,
			LilayaHomeGeneric.STAIRCASE_UP_SECONDARY,
			Darkness.ALWAYS_LIGHT,
			null, "莉莱雅的家中"
			).initItemsPersistInTile()
			.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_ENTRANCE_HALL = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"门厅",
			"宏伟的门厅墙壁上挂满了精美的油画和大理石半身雕像，双层高的天花板上悬挂着巨大的水晶吊灯。",
			"dominion/lilayasHome/entranceHall",
			PresetColour.BASE_RED,
			LilayaHomeGeneric.ENTRANCE_HALL,
			Darkness.ALWAYS_LIGHT,
			null, "莉莱雅的家中"
			).initItemsPersistInTile()
			.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_LAB = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"莉莱雅的实验室",
			"一楼角落的一个房间被改造成了专门的实验室，莉莱雅整天泡在这里。",
			"dominion/lilayasHome/lab",
			PresetColour.BASE_GREEN_LIME,
			Lab.LAB,
			Darkness.ALWAYS_LIGHT,
			null, "在莉莱雅的实验室") {
//		@Override
//		public void applyInventoryInit(CharacterInventory inventory) {
//			inventory.addClothing(Main.game.getItemGen().generateClothing("innoxia_scientist_safety_goggles", false));
//		}
	}.initItemsPersistInTile()
	.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_GARDEN = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"花园",
			"这座私家花园四面环绕着莉莱雅家的围墙。",
			null,
			PresetColour.BASE_GREEN,
			LilayaHomeGeneric.GARDEN,
			Darkness.DAYLIGHT,
			null, "莉莱雅的花园中"
			).initItemsPersistInTile()
			.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_GREEN);
	
	public static final AbstractPlaceType LILAYA_HOME_FOUNTAIN = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"喷泉",
			"在花园的正中央，一个巨大而华丽的喷泉自顾自地欢快地汩汩喷洒着。",
			"dominion/lilayasHome/fountain",
			PresetColour.BASE_BLUE_LIGHT,
			LilayaHomeGeneric.FOUNTAIN,
			Darkness.DAYLIGHT,
			null, "莉莱雅的花园中"
			).initItemsPersistInTile()
			.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_GREEN)
			.initAquatic(Aquatic.MIXED);

	public static final AbstractPlaceType LILAYA_HOME_UNDER_CONSTRUCTION = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"建筑工地",
			"莉莱雅宅邸的这一区域目前正在扩建，所以就叫建筑工地啰……",
			"dominion/lilayasHome/construction",
			PresetColour.BASE_BROWN,
			LilayaSpa.SPA_CONSTRUCTION,
			Darkness.ALWAYS_LIGHT,
			null, "于莉莱雅的水疗中心"
			) {
		@Override
		public List<Population> getPopulation() {
			List<Population> pop = new ArrayList<>();
			if(Main.game.isWorkTime()) {
				pop.add(new Population(true, PopulationType.CONSTRUCTION_WORKER, PopulationDensity.SEVERAL, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
			}
			return pop;
		}
	}.initItemsPersistInTile()
	.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_SPA = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"水疗池",
			"私人水疗中心的核心部分是一系列水池，池中装满了从地热温泉中汲取的温水。",
			"dominion/lilayasHome/roomSpa",
			PresetColour.BASE_TEAL,
			LilayaSpa.SPA_CORE,
			Darkness.ALWAYS_LIGHT,
			null, "于莉莱雅的水疗中心"
			).initItemsPersistInTile()
			.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_SPA_POOL = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"游泳池",
			"这里还安装了一个大型室内游泳池。",
			"dominion/lilayasHome/roomSpaPool",
			PresetColour.BASE_BLUE_LIGHT,
			LilayaSpa.SPA_POOL,
			Darkness.ALWAYS_LIGHT,
			null, "于莉莱雅的水疗中心"
			).initItemsPersistInTile();
	
	public static final AbstractPlaceType LILAYA_HOME_SPA_SAUNA = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"桑拿",
			"该区域还建有大型桑拿房和蒸汽房。",
			"dominion/lilayasHome/roomSpaSauna",
			PresetColour.BASE_BROWN,
			LilayaSpa.SPA_SAUNA,
			Darkness.ALWAYS_LIGHT,
			null, "于莉莱雅的水疗中心"
			).initItemsPersistInTile();
	
	
	
	// Lilaya's home (first floor):

	public static final AbstractPlaceType LILAYA_HOME_ROOM_LILAYA = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"莉莱雅的房间",
			"莉莱雅的房间位于二楼的一个角落，与萝丝的房间相邻……",
			"dominion/lilayasHome/roomLilaya",
			PresetColour.BASE_CRIMSON,
			LilayasRoom.ROOM_LILAYA,
			Darkness.ALWAYS_LIGHT,
			null, "莉莱雅的家中"
			).initItemsPersistInTile()
			.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_ROOM_ROSE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"萝丝的房间",
			"萝丝的房间位于二楼的一个角落，与莉莱雅的房间相邻……",
			"dominion/lilayasHome/roomRose",
			PresetColour.BASE_PINK,
			LilayaHomeGeneric.ROOM_ROSE,
			Darkness.ALWAYS_LIGHT,
			null, "莉莱雅的家中"
			).initItemsPersistInTile()
			.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_ROOM_PLAYER = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"你的房间",
			"莉莱雅免费赠送给你的房间；这是御城区唯一一个可以让你真正无忧无虑地休息的地方。",
			"dominion/lilayasHome/roomPlayer",
			PresetColour.BASE_AQUA,
			RoomPlayer.ROOM,
			Darkness.ALWAYS_LIGHT,
			null, "于你的房间内"
			) {
				@Override
				public ArrayList<AbstractPlaceUpgrade> getAvailablePlaceUpgrades(Set<AbstractPlaceUpgrade> upgrades) {
					return Util.newArrayListOfValues(
							PlaceUpgrade.LILAYA_PLAYER_ROOM_BED);
				}
				@Override
				public boolean isAbleToBeUpgraded() {
					return true;
				}
			}.initItemsPersistInTile()
			.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_STAIR_DOWN = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"楼梯",
			"这个楼梯连接着莉莱雅家的一楼和二楼，中途还有一个小的辅助楼梯间。",
			"dominion/lilayasHome/stairsDown",
			PresetColour.BASE_RED,
			LilayaHomeGeneric.STAIRCASE_DOWN,
			Darkness.ALWAYS_LIGHT,
			null, "莉莱雅的家中"
			).initItemsPersistInTile()
			.initWeatherImmune();

	public static final AbstractPlaceType LILAYA_HOME_STAIR_DOWN_SECONDARY = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"楼梯",
			"这个楼梯虽然比大宅入口处的主楼梯小一些，但连接莉莱雅家一楼和二楼的功能是一样的。",
			"dominion/lilayasHome/stairsDownSecondary",
			PresetColour.BASE_RED_LIGHT,
			LilayaHomeGeneric.STAIRCASE_DOWN_SECONDARY,
			Darkness.ALWAYS_LIGHT,
			null, "莉莱雅的家中"
			).initItemsPersistInTile()
			.initWeatherImmune();
	

	
	
	// Zaranix's home (ground floor):
	
	public static final AbstractPlaceType ZARANIX_GF_CORRIDOR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"走廊",
			"扎拉尼克斯家的走廊里摆放着许多精美的油画、软垫椅和工艺精湛的橱柜。",
			null,
			PresetColour.BASE_GREY,
			ZaranixHomeGroundFloor.CORRIDOR,
			Darkness.ALWAYS_LIGHT,
			null, "于扎拉尼克斯的家"){
		@Override
		public DialogueNode getDialogue(Cell c, boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeGroundFloorRepeat.CORRIDOR;
				
			} else {
				return ZaranixHomeGroundFloor.CORRIDOR;
			}
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType ZARANIX_GF_STAIRS = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"楼梯",
			"这段楼梯连接着扎拉尼克斯家的一楼和二楼。",
			"dominion/zaranixHome/stairsDown",
			PresetColour.BASE_GREEN_LIGHT,
			ZaranixHomeGroundFloor.STAIRS,
			Darkness.ALWAYS_LIGHT,
			null, "于扎拉尼克斯的家"){
		@Override
		public DialogueNode getDialogue(Cell c, boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeGroundFloorRepeat.STAIRS;
				
			} else {
				return ZaranixHomeGroundFloor.STAIRS;
			}
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType ZARANIX_GF_ENTRANCE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"入口",
			"一盏由奥术驱动的巨大水晶吊灯在门厅投射出明亮的光芒，四周的墙壁上挂满了金色画框的精美油画。",
			"dominion/zaranixHome/entranceHall",
			PresetColour.BASE_RED,
			ZaranixHomeGroundFloor.ENTRANCE,
			Darkness.ALWAYS_LIGHT,
			null, "于扎拉尼克斯的家"){
		@Override
		public DialogueNode getDialogue(Cell c, boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeGroundFloorRepeat.ENTRANCE;
				
			} else {
				return ZaranixHomeGroundFloor.ENTRANCE;
			}
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType ZARANIX_GF_LOUNGE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"休息室",
			"房间中央的矮桌旁摆放着几张沙发和椅子，许多书柜和橱柜在贴着碎花壁纸的墙壁上排成一行。",
			"dominion/zaranixHome/lounge",
			PresetColour.BASE_ORANGE,
			ZaranixHomeGroundFloor.LOUNGE,
			Darkness.ALWAYS_LIGHT,
			null, "于扎拉尼克斯的家"){
		@Override
		public DialogueNode getDialogue(Cell c, boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeGroundFloorRepeat.LOUNGE;
				
			} else {
				return ZaranixHomeGroundFloor.LOUNGE;
			}
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType ZARANIX_GF_ROOM = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"房间",
			"这个房间的门是锁着的，里面也没有人的声音。",
			"dominion/zaranixHome/room",
			PresetColour.BASE_GREY,
			ZaranixHomeGroundFloor.ROOM,
			Darkness.ALWAYS_LIGHT,
			null, "于扎拉尼克斯家的房间"){
		@Override
		public DialogueNode getDialogue(Cell c, boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeGroundFloorRepeat.ROOM;
				
			} else {
				return ZaranixHomeGroundFloor.ROOM;
			}
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType ZARANIX_GF_MAID = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"走廊",
			"一个象牙色皮肤的魅魔，穿着浅粉色的女仆服，正在忙着给这个区域除尘。",
			null,
			PresetColour.BASE_GREY,
			ZaranixHomeGroundFloor.CORRIDOR_MAID,
			Darkness.ALWAYS_LIGHT,
			null, "于扎拉尼克斯的家"){
		@Override
		public DialogueNode getDialogue(Cell c, boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeGroundFloorRepeat.CORRIDOR;
			} else {
				return ZaranixHomeGroundFloor.CORRIDOR_MAID;
			}
		}
		@Override
		public String getTooltipDescription() {
			if(isDangerous()) {
				return tooltipDescription;
			} else {
				return ZARANIX_GF_CORRIDOR.getTooltipDescription();
			}
		}
		@Override
		public boolean isDangerous() {
			return !Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE);
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType ZARANIX_GF_GARDEN_ROOM = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"房间",
			"一间连接着花园和扎拉尼克斯家的其他部分的无趣房间。",
			"dominion/zaranixHome/room",
			PresetColour.BASE_GREY,
			ZaranixHomeGroundFloor.GARDEN_ROOM,
			Darkness.ALWAYS_LIGHT,
			null, "于扎拉尼克斯家的房间"){
		@Override
		public DialogueNode getDialogue(Cell c, boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeGroundFloorRepeat.GARDEN_ROOM;
				
			} else {
				return ZaranixHomeGroundFloor.GARDEN_ROOM;
			}
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType ZARANIX_GF_GARDEN = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"花园",
			"一个种植着各种奇形怪状植物的花园。",
			"dominion/zaranixHome/garden",
			PresetColour.BASE_GREEN,
			ZaranixHomeGroundFloor.GARDEN,
			Darkness.DAYLIGHT,
			null, "于扎拉尼克斯的花园"){
		@Override
		public DialogueNode getDialogue(Cell c, boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeGroundFloorRepeat.GARDEN;
				
			} else {
				return ZaranixHomeGroundFloor.GARDEN;
			}
		}
	};
	
	public static final AbstractPlaceType ZARANIX_GF_GARDEN_ENTRY = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"花园",
			"这个特殊的区域紧挨着栅栏，把扎拉尼克斯的花园和御城区的街道隔开。",
			"dominion/zaranixHome/entranceHall",
			PresetColour.BASE_GREEN,
			ZaranixHomeGroundFloor.GARDEN_ENTRY,
			Darkness.DAYLIGHT,
			null, "于扎拉尼克斯的花园"){
		@Override
		public DialogueNode getDialogue(Cell c, boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeGroundFloorRepeat.GARDEN_ENTRY;
				
			} else {
				return ZaranixHomeGroundFloor.GARDEN_ENTRY;
			}
		}
	};
	
	
	
	// Zaranix's home (first floor):
	
	public static final AbstractPlaceType ZARANIX_FF_CORRIDOR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"走廊",
			"扎拉尼克斯家的走廊里摆放着许多精美的油画、软垫椅和工艺精湛的橱柜。",
			null,
			PresetColour.BASE_GREY,
			ZaranixHomeFirstFloor.CORRIDOR,
			Darkness.ALWAYS_LIGHT,
			null, "于扎拉尼克斯的家"){
		@Override
		public DialogueNode getDialogue(Cell c, boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeFirstFloorRepeat.CORRIDOR;
				
			} else {
				return ZaranixHomeFirstFloor.CORRIDOR;
			}
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType ZARANIX_FF_STAIRS = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"楼梯",
			"这段楼梯连接着扎拉尼克斯家的一楼和二楼。",
			"dominion/zaranixHome/stairsDown",
			PresetColour.BASE_RED,
			ZaranixHomeFirstFloor.STAIRS,
			Darkness.ALWAYS_LIGHT,
			null, "于扎拉尼克斯的家"){
		@Override
		public DialogueNode getDialogue(Cell c, boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeFirstFloorRepeat.STAIRS;
				
			} else {
				return ZaranixHomeFirstFloor.STAIRS;
			}
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType ZARANIX_FF_OFFICE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"扎拉尼克斯的房间",
			"这里原来是扎拉尼克斯的办公室，现在已经被改造成了一间小型实验室。",
			"dominion/zaranixHome/roomZaranix",
			PresetColour.BASE_GREEN_LIME,
			ZaranixHomeFirstFloor.ZARANIX_ROOM,
			Darkness.ALWAYS_LIGHT,
			null, "于扎拉尼克斯的家"){
		@Override
		public DialogueNode getDialogue(Cell c, boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeFirstFloorRepeat.ZARANIX_ROOM;
				
			} else {
				return ZaranixHomeFirstFloor.ZARANIX_ROOM;
			}
		}
		@Override
		public boolean isDangerous() {
			return !Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE);
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType ZARANIX_FF_ROOM = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"房间",
			"这个房间的门是锁着的，里面也没有人的声音。",
			"dominion/zaranixHome/room",
			PresetColour.BASE_GREY,
			ZaranixHomeFirstFloor.ROOM,
			Darkness.ALWAYS_LIGHT,
			null, "于扎拉尼克斯家的房间"){
		@Override
		public DialogueNode getDialogue(Cell c, boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeFirstFloorRepeat.ROOM;
				
			} else {
				return ZaranixHomeFirstFloor.ROOM;
			}
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType ZARANIX_FF_MAID = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"走廊",
			"一个象牙色皮肤的魅魔，穿着浅粉色的女仆服，正在忙着给这个区域除尘。",
			null,
			PresetColour.BASE_RED,
			ZaranixHomeFirstFloor.CORRIDOR_MAID,
			Darkness.ALWAYS_LIGHT,
			null, "于扎拉尼克斯的家"){
		@Override
		public DialogueNode getDialogue(Cell c, boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeFirstFloorRepeat.CORRIDOR;
				
			} else {
				return ZaranixHomeFirstFloor.CORRIDOR_MAID;
			}
		}
		@Override
		public String getTooltipDescription() {
			if(isDangerous()) {
				return tooltipDescription;
			} else {
				return ZARANIX_FF_CORRIDOR.getTooltipDescription();
			}
		}
		@Override
		public boolean isDangerous() {
			return !Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE);
		}
	}.initWeatherImmune();
	
	
	
	
	// Angel's Kiss:

	public static final AbstractPlaceType ANGELS_KISS_CORRIDOR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"走廊",
			"天使之吻的走廊铺着浓酒红色的地毯，墙壁一半深色木板覆盖，另一半则是绘有浅蓝色花卉的墙纸。",
			null,
			PresetColour.BASE_GREY,
			RedLightDistrict.ANGELS_KISS_CORRIDOR,
			Darkness.ALWAYS_LIGHT,
			null, "“天使之吻”内"
			).initWeatherImmune();
	
	public static final AbstractPlaceType ANGELS_KISS_ENTRANCE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"门厅",
			"高高的天花板上悬挂着一盏金色的吊灯，柔和的白光照亮了门厅长长的红木柜台。",
			"dominion/angelsKiss/entrance",
			PresetColour.BASE_RED,
			RedLightDistrict.ANGELS_KISS_ENTRANCE,
			Darkness.ALWAYS_LIGHT,
			null, "“天使之吻”内"
			).initWeatherImmune();
	
	public static final AbstractPlaceType ANGELS_KISS_STAIRCASE_UP = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"楼梯",
			"这个楼梯连接着天使之吻的一楼和二楼。",
			"dominion/angelsKiss/stairsUp",
			PresetColour.BASE_GREEN_LIGHT,
			RedLightDistrict.ANGELS_KISS_STAIRS_UP,
			Darkness.ALWAYS_LIGHT,
			null, "“天使之吻”内"
		).initWeatherImmune();
	
	public static final AbstractPlaceType ANGELS_KISS_STAIRCASE_DOWN = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"楼梯",
			"这个楼梯连接着天使之吻的二楼和一楼。",
			"dominion/angelsKiss/stairsDown",
			PresetColour.BASE_RED,
			RedLightDistrict.ANGELS_KISS_STAIRS_DOWN,
			Darkness.ALWAYS_LIGHT,
			null, "“天使之吻”内"
			).initWeatherImmune();
	
	public static final AbstractPlaceType ANGELS_KISS_BEDROOM = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"卧室",
			"这些卧室是酒店开展业务的地方，因此每间卧室都有一张干净的特大号床。",
			"dominion/angelsKiss/bedroom",
			PresetColour.BASE_PINK,
			RedLightDistrict.ANGELS_KISS_BEDROOM,
			Darkness.ALWAYS_LIGHT,
			null, "“天使之吻”内"
			).initWeatherImmune();
	
	public static final AbstractPlaceType ANGELS_KISS_BEDROOM_BUNNY = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"兔兔的卧室",
			"这间卧室里住着顺从妓女“兔兔”，她和姐姐“耷耷”长得很像。",
			"dominion/angelsKiss/bedroomBunny",
			PresetColour.BASE_PINK_LIGHT,
			RedLightDistrict.ANGELS_KISS_BEDROOM_BUNNY,
			Darkness.ALWAYS_LIGHT,
			null, "在兔兔的卧室里"
			).initWeatherImmune();
	
	public static final AbstractPlaceType ANGELS_KISS_BEDROOM_LOPPY = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"耷耷的卧室",
			"这间卧室里住着支配妓女“耷耷”，她和妹妹“兔兔”长得很像。",
			"dominion/angelsKiss/bedroomLoppy",
			PresetColour.BASE_PURPLE,
			RedLightDistrict.ANGELS_KISS_BEDROOM_LOPPY,
			Darkness.ALWAYS_LIGHT,
			null, "在耷耷的卧室里"
			).initWeatherImmune();
	
	public static final AbstractPlaceType ANGELS_KISS_OFFICE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"安吉尔的办公室",
			"作为红灯区里“执法者认可的管理中心”，安吉尔在这个房间里处理所有需要她处理的文书工作。",
			"dominion/angelsKiss/office",
			PresetColour.BASE_BLUE_LIGHT,
			RedLightDistrict.ANGELS_KISS_OFFICE,
			Darkness.ALWAYS_LIGHT,
			null, "于安吉尔的办公室"
			).initWeatherImmune();
	
	
	
	
	// Shopping arcade:
	
	public static final AbstractPlaceType SHOPPING_ARCADE_PATH = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"商场",
			"穿过购物中心的主干道两侧是各式各样的商店。",
			null,
			PresetColour.BASE_BLACK,
			ShoppingArcadeDialogue.ARCADE,
			Darkness.ALWAYS_LIGHT,
			null, "于购物中心") {
		@Override
		public List<Population> getPopulation() {
			List<Population> pop = new ArrayList<>();
			if(Main.game.isExtendedWorkTime()) {
				pop.add(new Population(true, PopulationType.CROWD, PopulationDensity.DENSE, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
			} else {
				pop.add(new Population(true, PopulationType.PERSON, PopulationDensity.COUPLE, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
			}
			pop.add(new Population(true, PopulationType.ENFORCER, PopulationDensity.SEVERAL, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true, Subspecies.HUMAN)));
			return pop;
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType SHOPPING_ARCADE_GENERIC_SHOP = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"店铺",
			"这是商场里众多不起眼的商店之一，没有任何值得你花时间或金钱去逛逛的东西。",
			"dominion/shoppingArcade/genericShop",
			PresetColour.BASE_BLACK,
			ShoppingArcadeDialogue.GENERIC_SHOP,
			Darkness.ALWAYS_LIGHT,
			null, "于购物中心") {
		@Override
		public List<Population> getPopulation() {
			if(Main.game.isExtendedWorkTime()) {
				return Util.newArrayListOfValues(new Population(true, PopulationType.SHOPPER, PopulationDensity.NUMEROUS, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
			} else {
				return new ArrayList<>();
			}
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType SHOPPING_ARCADE_RALPHS_SHOP = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"拉尔夫小吃店",
			"一家专门出售食品、饮料和其他杂物的商店。“拉尔夫小吃店”的店长是一位肌肉发达、棕色头发的马男，他非常友善。",
			"dominion/shoppingArcade/ralphShop",
			PresetColour.BASE_TEAL,
			RalphsSnacks.EXTERIOR,
			Darkness.ALWAYS_LIGHT,
			null, "在他的店内"
			).initWeatherImmune();
	
	public static final AbstractPlaceType SHOPPING_ARCADE_NYANS_SHOP = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"妮安服装店",
			"两层楼高的 “妮安服装店” 是整个商场最大的商店。",
			"dominion/shoppingArcade/nyanShop",
			PresetColour.BASE_ROSE,
			ClothingEmporium.EXTERIOR,
			Darkness.ALWAYS_LIGHT,
			null,
			"在她的店内") {
		@Override
		public List<Population> getPopulation() {
			if(Main.game.isHourBetween(9, 20) && !Main.game.getCurrentDialogueNode().isTravelDisabled()) { // Travel disabled indicates that the player is in the storeroom with Nyan
				return Util.newArrayListOfValues(new Population(true, PopulationType.SHOPPER, PopulationDensity.DOZENS, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
			} else {
				return new ArrayList<>();
			}
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType SHOPPING_ARCADE_VICKYS_SHOP = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"奥术艺术",
			"“奥术艺术”专卖奥术武器和相关用品，由长相特别凶猛的女狼人“维姬”经营。",
			"dominion/shoppingArcade/vickyShop",
			PresetColour.BASE_MAGENTA,
			ArcaneArts.EXTERIOR,
			Darkness.ALWAYS_LIGHT,
			null, "在她的店内"
			).initWeatherImmune();

	public static final AbstractPlaceType SHOPPING_ARCADE_KATES_SHOP = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"魅魔的秘密",
			"这家美容院的窗户被木板封住，油漆剥落，乍一看似乎已经荒废，但仔细一看，门上还挂着 “营业中” 的牌子。",
			"dominion/shoppingArcade/kateShop",
			PresetColour.BASE_PINK,
			SuccubisSecrets.EXTERIOR,
			Darkness.ALWAYS_LIGHT,
			null, "在她的美容院里"
			).initWeatherImmune();

	public static final AbstractPlaceType SHOPPING_ARCADE_ASHLEYS_SHOP = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"梦中爱侣",
			"你在御城区看到的唯一一家专营赠礼的商店。",
			"dominion/shoppingArcade/ashleyShop",
			PresetColour.BASE_LILAC_LIGHT,
			DreamLover.EXTERIOR,
			Darkness.ALWAYS_LIGHT,
			null, "在她店内"
			).initWeatherImmune();

	public static final AbstractPlaceType SHOPPING_ARCADE_ANTIQUES = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"古玩店",
			"这是一家古董店，出售的物品不拘一格，有吱吱作响的过时家具、过时奥术仪器……总之还有很多过时东西。",
			"dominion/shoppingArcade/antiques",
			PresetColour.BASE_BROWN,
			ShoppingArcadeDialogue.ANTIQUES,
			Darkness.ALWAYS_LIGHT,
			null, "古董店里"
			).initWeatherImmune();
	
	public static final AbstractPlaceType SHOPPING_ARCADE_RESTAURANT = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"橡木林荫",
			"“橡木林荫”的消费人群主要是来往购物中心的顾客中较为富庶的那些。该餐厅最基础的只包含三道菜的套餐也价格惊人，要将近一千火币。",
			"dominion/shoppingArcade/restaurant",
			PresetColour.BASE_GREEN_DARK,
			ShoppingArcadeDialogue.RESTAURANT,
			Darkness.ALWAYS_LIGHT,
			null, "在“橡木林荫”") {
		@Override
		public List<Population> getPopulation() {
			if(Main.game.getHourOfDay()>=18) {
				return Util.newArrayListOfValues(new Population(true, PopulationType.DINER, PopulationDensity.NUMEROUS, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
			} else {
				return new ArrayList<>();
			}
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType SHOPPING_ARCADE_PIXS_GYM = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"皮克斯操练场",
			"“皮克斯操练场” 是一个巨大的多层健身房，一个精力充沛的边牧女是这家店的老板。",
			"dominion/shoppingArcade/gym",
			PresetColour.BASE_GOLD,
			PixsPlayground.GYM_EXTERIOR,
			Darkness.ALWAYS_LIGHT,
			null, "皮克斯操练场的健身房内"
			).initWeatherImmune();

	public static final AbstractPlaceType SHOPPING_ARCADE_ENTRANCE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"退出",
			"这扇巨大的玻璃门可以让你回到御城区的街道上",
			"dominion/shoppingArcade/exit",
			PresetColour.BASE_RED,
			ShoppingArcadeDialogue.ENTRY,
			Darkness.ALWAYS_LIGHT,
			null, "购物中心内"
			).initWeatherImmune();

	public static final AbstractPlaceType SHOPPING_ARCADE_TOILETS = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"厕所",
			"购物中心入口附近有一些公共厕所，非常方便。",
			"dominion/shoppingArcade/toilets",
			PresetColour.BASE_BLUE_LIGHT,
			ShoppingArcadeDialogue.TOILETS,
			Darkness.ALWAYS_LIGHT,
			null, "于购物中心的公共厕所") {
		@Override
		public List<Population> getPopulation() {
			if(Main.game.isExtendedWorkTime()) {
				return Util.newArrayListOfValues(new Population(true, PopulationType.SHOPPER, PopulationDensity.COUPLE, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
			} else {
				return new ArrayList<>();
			}
		}
	}.initWeatherImmune();
	
	
	
	// Supplier Depot:
	
	public static final AbstractPlaceType TEXTILE_WAREHOUSE_CORRIDOR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"走廊",
			"一条光线充足的走廊贯穿整个建筑，通向仓库的存储区和纺织区。",
			null,
			PresetColour.BASE_BLACK,
			KaysWarehouse.CORRIDOR,
			Darkness.ALWAYS_LIGHT,
			null,
			"于凯记纺织") {
		@Override
		public String getTooltipDescription() {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.RELATIONSHIP_NYAN_HELP)) {
				return "解决了杜宾犬的问题后，大楼后面的走廊也进行了翻新，现在既干净又明亮。";
			} else {
				return tooltipDescription;
			}
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType TEXTILE_WAREHOUSE_ENTRANCE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"接待区",
			"仓库接待处的工作人员是一个看起来情绪低落的犬女，她似乎对履行职责一点也不感兴趣。",
			"dominion/textilesWarehouse/exit",
			PresetColour.BASE_RED,
			KaysWarehouse.RECEPTION,
			Darkness.ALWAYS_LIGHT,
			null,
			"于凯记纺织") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(false, PopulationType.RECEPTIONIST, PopulationDensity.ONE, Util.newHashMapOfValues(new Value<>(Subspecies.RABBIT_MORPH, SubspeciesSpawnRarity.TEN))));
		}
		@Override
		public String getTooltipDescription() {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.RELATIONSHIP_NYAN_HELP)) {
				return "解决了杜宾犬的问题后，接待处那位郁郁寡欢的女工作人员似乎开心了许多，并热心地为游客提供帮助。";
			} else {
				return tooltipDescription;
			}
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType TEXTILE_WAREHOUSE_STORAGE_ROOM = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"储物间",
			"储藏室里堆满了无数箱子，每个箱子里都装满了附魔的服饰。",
			"dominion/textilesWarehouse/storage",
			PresetColour.BASE_RED,
			KaysWarehouse.STORAGE_AREA,
			Darkness.ALWAYS_LIGHT,
			null,
			"于凯记纺织"
			) {
		@Override
		public Colour getColour() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kayCratesSearched)
					|| !Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.RELATIONSHIP_NYAN_HELP, Quest.RELATIONSHIP_NYAN_3_STOCK_ISSUES_DOBERMANNS)) {
				return PresetColour.BASE_RED;
			} else {
				return PresetColour.BASE_GREEN;
			}
		}
		@Override
		public String getSVGString(Set<AbstractPlaceUpgrade> upgrades) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kayCratesSearched)
					|| !Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.RELATIONSHIP_NYAN_HELP, Quest.RELATIONSHIP_NYAN_3_STOCK_ISSUES_DOBERMANNS)) {
				return SVGString;
			} else {
				return getSVGOverride("dominion/textilesWarehouse/storage", PresetColour.BASE_GREEN);
			}
		}
	}.initItemsPersistInTile()
	.initWeatherImmune();
	
	public static final AbstractPlaceType TEXTILE_WAREHOUSE_ENCHANTING = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"织机",
			"这里的数十名纺织工人正在操作大量看似古老的奥术纺织机。",
			"dominion/textilesWarehouse/enchanting",
			PresetColour.GENERIC_ARCANE,
			KaysWarehouse.WEAVING_MACHINES,
			Darkness.ALWAYS_LIGHT,
			null,
			"于凯记纺织"
			) {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(
					new Population(true, PopulationType.TEXTILE_WORKER, PopulationDensity.NUMEROUS,
							Util.newHashMapOfValues(
									new Value<>(Subspecies.RABBIT_MORPH, SubspeciesSpawnRarity.TEN),
									new Value<>(Subspecies.CAT_MORPH, SubspeciesSpawnRarity.TEN),
									new Value<>(Subspecies.DOG_MORPH, SubspeciesSpawnRarity.TEN),
									new Value<>(Subspecies.FOX_MORPH, SubspeciesSpawnRarity.TEN),
									new Value<>(Subspecies.HORSE_MORPH, SubspeciesSpawnRarity.TEN),
									new Value<>(Subspecies.COW_MORPH, SubspeciesSpawnRarity.TEN),
									new Value<>(Subspecies.HUMAN, SubspeciesSpawnRarity.TEN))));
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType TEXTILE_WAREHOUSE_OVERSEER_STATION = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"监督站",
			"一组台阶通向夹层，在夹层上有一个监工站，可以俯瞰整个仓库楼层。",
			"dominion/textilesWarehouse/overseer_station",
			PresetColour.BASE_GREY_DARK,
			KaysWarehouse.OVERSEER_STATION,
			Darkness.ALWAYS_LIGHT,
			null,
			"于凯记纺织"
			) {
		@Override
		public List<Population> getPopulation() {
			if(Main.game.getWorlds().get(WorldType.TEXTILES_WAREHOUSE).getCell(PlaceType.TEXTILE_WAREHOUSE_OFFICE).isTravelledTo()) {
				return Util.newArrayListOfValues(new Population(false, PopulationType.TEXTILE_WORKER, PopulationDensity.ONE, Util.newHashMapOfValues(new Value<>(Subspecies.FOX_MORPH, SubspeciesSpawnRarity.TEN))));
			} else {
				return super.getPopulation();
			}
		}
		@Override
		public boolean isDangerous() {
			return !Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.RELATIONSHIP_NYAN_HELP, Quest.RELATIONSHIP_NYAN_3_STOCK_ISSUES_DOBERMANNS);
		}
		@Override
		public String getTooltipDescription() {
			if(Main.game.getWorlds().get(WorldType.TEXTILES_WAREHOUSE).getCell(PlaceType.TEXTILE_WAREHOUSE_OFFICE).isTravelledTo()) {
				return "解决了杜宾犬的问题后，凯的办公室外的监工站现在由一名纺织工人负责。";
			} else {
				return tooltipDescription;
			}
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType TEXTILE_WAREHOUSE_OFFICE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"凯的办公室",
			"凯的办公室可以通过监工站进入，虽然面积不大，但布置得非常雅致。",
			"dominion/textilesWarehouse/office",
			PresetColour.BASE_BLUE_LIGHT,
			KaysWarehouse.OFFICE,
			Darkness.ALWAYS_LIGHT,
			null,
			"于凯记纺织").initWeatherImmune();
	
	
	
	// Slaver Alley:
	
	public static final AbstractPlaceType SLAVER_ALLEY_PATH = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"小巷",
			"贯穿奴隶巷的小巷与御城区其他地方的小巷完全不同，它们繁华、整洁，最重要的是非常安全。",
			null,
			PresetColour.BASE_BLACK,
			SlaverAlleyDialogue.ALLEYWAY,
			Darkness.ALWAYS_LIGHT,
			null, "于奴隶巷") {
		@Override
		public List<Population> getPopulation() {
			List<Population> pop = new ArrayList<>();
			if(Main.game.isExtendedWorkTime()) {
				pop.add(new Population(true, PopulationType.CROWD, PopulationDensity.SPARSE, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
			} else {
				pop.add(new Population(true, PopulationType.PERSON, PopulationDensity.COUPLE, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
			}
			pop.add(new Population(false, PopulationType.PRIVATE_SECURITY_GUARD, PopulationDensity.OCCASIONAL, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
			return pop;
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);

	public static final AbstractPlaceType SLAVER_ALLEY_STALL_FEMALES = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"女士风味",
			"一家专门出售训练有素、顺从听话的女奴的商店。",
			"dominion/slaverAlley/marketStallFemale",
			PresetColour.BASE_PINK_LIGHT,
			SlaverAlleyDialogue.MARKET_STALL_FEMALE,
			Darkness.ALWAYS_LIGHT,
			null, "于奴隶巷") {
		@Override
		public List<Population> getPopulation() {
			return SLAVER_ALLEY_PATH.getPopulation();
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);

	public static final AbstractPlaceType SLAVER_ALLEY_STALL_MALES = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"铁&钢",
			"从体力劳动者到养眼的模特，这家商店专门出售各种男奴。",
			"dominion/slaverAlley/marketStallMale",
			PresetColour.BASE_BLUE_STEEL,
			SlaverAlleyDialogue.MARKET_STALL_MALE,
			Darkness.ALWAYS_LIGHT,
			null, "于奴隶巷") {
		@Override
		public List<Population> getPopulation() {
			return SLAVER_ALLEY_PATH.getPopulation();
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);

	public static final AbstractPlaceType SLAVER_ALLEY_STALL_ANAL = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"请走后门",
			"这家商店专门出售训练有素、喜欢肛交的奴隶，是围绕着玛莱尔淫荡雕像的三家商店之一。",
			"dominion/slaverAlley/marketStallAnal",
			PresetColour.BASE_ORANGE,
			SlaverAlleyDialogue.MARKET_STALL_ANAL,
			Darkness.ALWAYS_LIGHT,
			null, "于奴隶巷") {
		@Override
		public List<Population> getPopulation() {
			return SLAVER_ALLEY_PATH.getPopulation();
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);

	public static final AbstractPlaceType SLAVER_ALLEY_STALL_VAGINAL = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"白百合",
			"“白百合”是围绕着堕落天使玛莱尔雕像的三家商店之一，专门出售女性处女。",
			"dominion/slaverAlley/marketStallVaginal",
			PresetColour.BASE_PINK,
			SlaverAlleyDialogue.MARKET_STALL_VAGINAL,
			Darkness.ALWAYS_LIGHT,
			null, "于奴隶巷") {
		@Override
		public List<Population> getPopulation() {
			return SLAVER_ALLEY_PATH.getPopulation();
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);

	public static final AbstractPlaceType SLAVER_ALLEY_STALL_ORAL = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"声音万岁",
			"“声音万岁”专门出售训练有素、喜欢口交的奴隶，是玛莱尔雕像周围的三家专卖店之一。",
			"dominion/slaverAlley/marketStallOral",
			PresetColour.BASE_BLUE_LIGHT,
			SlaverAlleyDialogue.MARKET_STALL_ORAL,
			Darkness.ALWAYS_LIGHT,
			null, "于奴隶巷") {
		@Override
		public List<Population> getPopulation() {
			return SLAVER_ALLEY_PATH.getPopulation();
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);

	public static final AbstractPlaceType SLAVER_ALLEY_STATUE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"堕天使雕像",
			"这尊堕落天使玛莱尔的雕像矗立在高高的基座之上，展现了她高度堕落、令人难以置信的淫荡形象。",
			"dominion/slaverAlley/marketStallStatue",
			PresetColour.BASE_BLACK,
			SlaverAlleyDialogue.MARKET_STALL_STATUE,
			Darkness.ALWAYS_LIGHT,
			null, "于奴隶巷") {
		@Override
		public List<Population> getPopulation() {
			return SLAVER_ALLEY_PATH.getPopulation();
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);

	public static final AbstractPlaceType SLAVER_ALLEY_MARKET_STALL_EXCLUSIVE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"奴隶租赁商店",
			"奴隶巷中的许多商店并不直接出售奴隶，而是将他们出租给那些希望填补临时劳动力缺口的企业。",
			"dominion/slaverAlley/marketStallExclusive",
			PresetColour.BASE_GREY,
			SlaverAlleyDialogue.MARKET_STALL_EXCLUSIVE,
			Darkness.ALWAYS_LIGHT,
			null, "于奴隶巷") {
		@Override
		public List<Population> getPopulation() {
			return SLAVER_ALLEY_PATH.getPopulation();
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);

	public static final AbstractPlaceType SLAVER_ALLEY_MARKET_STALL_BULK = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"财团交易所",
			"迄今为止，“财团交易所”是整个奴隶巷最大的商店，由一个强大的奴隶集团控制，拒绝与非会员做生意。",
			"dominion/slaverAlley/marketStallBulk",
			PresetColour.BASE_BLUE,
			SlaverAlleyDialogue.MARKET_STALL_BULK,
			Darkness.ALWAYS_LIGHT,
			null, "于奴隶巷") {
		@Override
		public List<Population> getPopulation() {
			return SLAVER_ALLEY_PATH.getPopulation();
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);
	
	public static final AbstractPlaceType SLAVER_ALLEY_CAFE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"小餐馆",
			"奴隶巷内散布着许多咖啡馆，为购物者提供了休息和补充能量的场所。",
			"dominion/slaverAlley/marketStallCafe",
			PresetColour.BASE_BROWN,
			SlaverAlleyDialogue.MARKET_STALL_CAFE,
			Darkness.ALWAYS_LIGHT,
			null, "于奴隶巷") {
		@Override
		public List<Population> getPopulation() {
			List<Population> pop = new ArrayList<>();
			if(Main.game.isExtendedWorkTime()) {
				pop.add(new Population(true, PopulationType.PERSON, PopulationDensity.NUMEROUS, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
			} else {
				pop.add(new Population(true, PopulationType.PERSON, PopulationDensity.FEW, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
			}
			return pop;
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);
	
	public static final AbstractPlaceType SLAVER_ALLEY_CAFE_2 = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"小餐馆",
			"奴隶巷内散布着许多咖啡馆，为购物者提供了休息和补充能量的场所。",
			"dominion/slaverAlley/marketStallCafe",
			PresetColour.BASE_BROWN,
			SlaverAlleyDialogue.MARKET_STALL_CAFE,
			Darkness.ALWAYS_LIGHT,
			null, "于奴隶巷") {
		@Override
		public List<Population> getPopulation() {
			List<Population> pop = new ArrayList<>();
			if(Main.game.isExtendedWorkTime()) {
				pop.add(new Population(true, PopulationType.PERSON, PopulationDensity.NUMEROUS, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
			} else {
				pop.add(new Population(true, PopulationType.PERSON, PopulationDensity.FEW, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
			}
			return pop;
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);

	
	public static final AbstractPlaceType SLAVER_ALLEY_CAFE_3 = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"小餐馆",
			"奴隶巷内散布着许多咖啡馆，为购物者提供了休息和补充能量的场所。",
			"dominion/slaverAlley/marketStallCafe",
			PresetColour.BASE_BROWN,
			SlaverAlleyDialogue.MARKET_STALL_CAFE,
			Darkness.ALWAYS_LIGHT,
			null, "于奴隶巷") {
		@Override
		public List<Population> getPopulation() {
			List<Population> pop = new ArrayList<>();
			if(Main.game.isExtendedWorkTime()) {
				pop.add(new Population(true, PopulationType.PERSON, PopulationDensity.NUMEROUS, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
			} else {
				pop.add(new Population(true, PopulationType.PERSON, PopulationDensity.FEW, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
			}
			return pop;
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);

	
	public static final AbstractPlaceType SLAVER_ALLEY_CAFE_4 = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"小餐馆",
			"奴隶巷内散布着许多咖啡馆，为购物者提供了休息和补充能量的场所。",
			"dominion/slaverAlley/marketStallCafe",
			PresetColour.BASE_BROWN,
			SlaverAlleyDialogue.MARKET_STALL_CAFE,
			Darkness.ALWAYS_LIGHT,
			null, "于奴隶巷") {
		@Override
		public List<Population> getPopulation() {
			List<Population> pop = new ArrayList<>();
			if(Main.game.isExtendedWorkTime()) {
				pop.add(new Population(true, PopulationType.PERSON, PopulationDensity.NUMEROUS, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
			} else {
				pop.add(new Population(true, PopulationType.PERSON, PopulationDensity.FEW, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
			}
			return pop;
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);
	
	public static final AbstractPlaceType SLAVER_ALLEY_AUCTIONING_BLOCK = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"拍卖台",
			"广阔的广场中央有一个巨大的木制平台，公开拍卖就在这个平台上举行。",
			"dominion/slaverAlley/auctionBlock",
			PresetColour.BASE_GOLD,
			SlaverAlleyDialogue.AUCTION_BLOCK,
			Darkness.ALWAYS_LIGHT,
			null, "于奴隶巷") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(false, PopulationType.CROWD, PopulationDensity.DENSE, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);

	public static final AbstractPlaceType SLAVER_ALLEY_PUBLIC_STOCKS = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"公共颈手枷",
			"奴隶巷入口处摆放着一系列公用颈手枷，提醒人们不听话的奴隶会有什么下场。",
			"dominion/slaverAlley/stocks",
			PresetColour.BASE_TAN,
			SlaverAlleyDialogue.PUBLIC_STOCKS,
			Darkness.ALWAYS_LIGHT,
			null, "于奴隶巷的颈手枷") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(false, PopulationType.CROWD, PopulationDensity.SPARSE, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);
	
	public static final AbstractPlaceType SLAVER_ALLEY_BOUNTY_HUNTERS = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"锈项圈酒馆",
			"在这家酒馆里，赏金猎人可以签约追踪逃跑的奴隶的单子。",
			"dominion/slaverAlley/bountyHunters",
			PresetColour.BASE_COPPER,
			SlaverAlleyDialogue.BOUNTY_HUNTERS,
			Darkness.ALWAYS_LIGHT,
			null,
			"于奴隶巷"){
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.PERSON, PopulationDensity.NUMEROUS, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);
	
	public static final AbstractPlaceType SLAVER_ALLEY_SLAVERY_ADMINISTRATION = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"奴隶管理局",
			"所有与奴隶所有权相关事务的主要管理中心。",
			"dominion/slaverAlley/slaveryAdministration",
			PresetColour.BASE_PURPLE,
			SlaveryAdministration.SLAVERY_ADMINISTRATION_EXTERIOR,
			Darkness.ALWAYS_LIGHT,
			null, "于奴隶巷"){
		@Override
		public ArrayList<AbstractPlaceUpgrade> getStartingPlaceUpgrades() {
			return Util.newArrayListOfValues(PlaceUpgrade.SLAVERY_ADMINISTRATION_CELLS);
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);
	
	public static final AbstractPlaceType SLAVER_ALLEY_SCARLETTS_SHOP = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"斯嘉丽的商店",
			"",
			"dominion/slaverAlley/scarlettsStall",
			PresetColour.BASE_CRIMSON,
			ScarlettsShop.SCARLETTS_SHOP_EXTERIOR,
			Darkness.ALWAYS_LIGHT,
			null, "于奴隶巷"){
		@Override
		public Colour getColour() {
			if(Main.game.getPlayer().isQuestFailed(QuestLine.ROMANCE_HELENA)) {
				return PresetColour.BASE_BLACK;
			} else if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_3_C_EXTERIOR_DECORATOR)) {
				return PresetColour.BASE_GOLD;
			} else if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_3_A_EXTERIOR_DECORATOR)) {
				return PresetColour.BASE_GREY;
			}
			return PresetColour.BASE_CRIMSON;
		}
		@Override
		public String getSVGString(Set<AbstractPlaceUpgrade> upgrades) {
			return getSVGOverride("dominion/slaverAlley/scarlettsStall", getColour());
		}
		@Override
		public String getName() {
			if(Main.game.isStarted()) {
				if(Main.game.getPlayer().isQuestFailed(QuestLine.ROMANCE_HELENA)) {
					return "废弃的商店";
					
				} else if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_3_C_EXTERIOR_DECORATOR)) {
					return "海伦娜精品店";
					
				} else if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_3_A_EXTERIOR_DECORATOR)) {
					return "无名商店";
				}
			}
			return "斯嘉丽的商店";
		}
		@Override
		public DialogueNode getDialogue(Cell c, boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressLessThan(QuestLine.MAIN, Quest.MAIN_1_F_SCARLETTS_FATE)) { // Scarlett owns the shop:
				return ScarlettsShop.SCARLETTS_SHOP_EXTERIOR;
				
			} else { // Helena owns the shop:
				return ScarlettsShop.HELENAS_SHOP_EXTERIOR;
			}
		}
		@Override
		public String getTooltipDescription() {
			if(Main.game.getPlayer().isQuestFailed(QuestLine.ROMANCE_HELENA)) {
				return "在你拒绝把斯嘉丽卖给海伦娜之后，这家奴隶商店就被她遗弃了……";
				
			} else if(Main.game.getPlayer().isQuestProgressLessThan(QuestLine.MAIN, Quest.MAIN_1_F_SCARLETTS_FATE)) {
				return "一间由哈比斯嘉丽经营的奴隶商店。不像奴隶巷的其他商店，她的商店绝对没有奴隶可以出售……";
				
			} else {
				return "斯嘉丽的族长，海伦娜接管了这家奴隶商店的管理工作。";
			}
		}
		@Override
		public List<Population> getPopulation() {
			if(Main.game.isStarted()) {
				if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_6_ADVERTISING)) {
					if(Main.game.getNpc(Helena.class).getLocationPlace().getPlaceType()==PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP) {
						return Util.newArrayListOfValues(
								new Population(true, PopulationType.HARPY, PopulationDensity.SEVERAL, Subspecies.getWorldSpecies(WorldType.HARPY_NEST, this, false, false)),
								new Population(true, PopulationType.SHOPPER, PopulationDensity.FEW, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)),
								new Population(true, PopulationType.FAN, PopulationDensity.SEVERAL, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
					} else {
						return Util.newArrayListOfValues(new Population(true, PopulationType.FAN, PopulationDensity.COUPLE, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
					}
					
				} else if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_3_C_EXTERIOR_DECORATOR) && !Main.game.getPlayer().isQuestFailed(QuestLine.ROMANCE_HELENA)) {
					if(Main.game.getNpc(Helena.class).getLocationPlace().getPlaceType()==PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP) {
						return Util.newArrayListOfValues(new Population(true, PopulationType.FAN, PopulationDensity.NUMEROUS, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
					} else {
						return Util.newArrayListOfValues(new Population(true, PopulationType.FAN, PopulationDensity.COUPLE, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
					}
				}
			}
			return new ArrayList<>();
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);
	
	public static final AbstractPlaceType SLAVER_ALLEY_ENTRANCE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"大门口",
			"奴隶巷只有一个出入口，由一对马男把守，他们密切注意是否有奴隶逃跑。",
			"dominion/slaverAlley/exit",
			PresetColour.BASE_RED,
			SlaverAlleyDialogue.GATEWAY,
			Darkness.ALWAYS_LIGHT,
			null, "于奴隶巷") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(
					new Population(true, PopulationType.PERSON, PopulationDensity.FEW, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)),
					new Population(true, PopulationType.PRIVATE_SECURITY_GUARD, PopulationDensity.COUPLE, Util.newHashMapOfValues(new Value<>(Subspecies.HORSE_MORPH, SubspeciesSpawnRarity.TEN))));
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);

	public static final AbstractPlaceType SLAVER_ALLEY_DESERTED_ALLEYWAY = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"空寂的小巷",
			"一条狭窄的小巷蜿蜒曲折，穿过几家店铺后，突然来到一个死胡同。",
			"dominion/slaverAlley/desertedAlleyway",
			PresetColour.BASE_BLACK,
			SlaverAlleyDialogue.DESERTED_ALLEYWAY,
			Darkness.DAYLIGHT,
			null, "于奴隶巷").initWeatherImmune(Weather.MAGIC_STORM);
	
	
	// Bounty hunter lodge:

	public static final AbstractPlaceType BOUNTY_HUNTER_LODGE_ENTRANCE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"入口",
			"酒馆的正门是一扇饱经风霜的木门。",
			"dominion/slaverAlley/bountyHunterLodge/exit",
			PresetColour.BASE_RED,
			BountyHunterLodge.ENTRANCE,
			Darkness.ALWAYS_LIGHT,
			null,
			"于“锈项圈酒馆”")
		.initWeatherImmune(Weather.MAGIC_STORM);
	
	public static final AbstractPlaceType BOUNTY_HUNTER_LODGE_FLOOR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"主层",
			"旅店这一层大部分面积都摆放着木制的大桌子，许多赏金猎人和不三不四的家伙都落座于此。",
			null,
			PresetColour.BASE_BLACK,
			BountyHunterLodge.FLOOR,
			Darkness.ALWAYS_LIGHT,
			null,
			"于“锈项圈酒馆”"){
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(false, PopulationType.CROWD, PopulationDensity.SPARSE, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
		}
		@Override
		public boolean isLoiteringEnabledOverride() {
			return true;
		}
		@Override
		public boolean isLoiteringEnabled() {
			return true;
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);
	
	public static final AbstractPlaceType BOUNTY_HUNTER_LODGE_BOUNTY_BOARD = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"悬赏版",
			"正门边有一块巨大的木板，上边张贴着不少悬赏令。",
			"dominion/slaverAlley/bountyHunterLodge/bountyBoard",
			PresetColour.CLOTHING_BLUE_GREY,
			BountyHunterLodge.BOUNTY_BOARD,
			Darkness.ALWAYS_LIGHT,
			null,
			"于“锈项圈酒馆”"){
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.PERSON, PopulationDensity.NUMEROUS, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);
	
	public static final AbstractPlaceType BOUNTY_HUNTER_LODGE_BAR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"吧台",
			"长长的吧台总是人满为患，资助着旅店的客人们面前摆满了各色酒精饮品。",
			"dominion/slaverAlley/bountyHunterLodge/bar",
			PresetColour.BASE_ORANGE,
			BountyHunterLodge.BAR,
			Darkness.ALWAYS_LIGHT,
			null,
			"于“锈项圈酒馆”"){
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.PERSON, PopulationDensity.NUMEROUS, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
		}
		@Override
		public boolean isLoiteringEnabledOverride() {
			return true;
		}
		@Override
		public boolean isLoiteringEnabled() {
			return true;
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);

	public static final AbstractPlaceType BOUNTY_HUNTER_LODGE_SEATING = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"内嵌式座椅",
			"在旅店主层的墙边，有许多嵌入墙内的座位区。",
			"dominion/slaverAlley/bountyHunterLodge/seatingArea",
			PresetColour.BASE_BROWN,
			BountyHunterLodge.SEATING,
			Darkness.ALWAYS_LIGHT,
			null,
			"于“锈项圈酒馆”"){
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.PERSON, PopulationDensity.FEW, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);

	public static final AbstractPlaceType BOUNTY_HUNTER_LODGE_STAIRS = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"楼梯",
			"一段狭小的楼梯，通向旅店的二楼。",
			"dominion/slaverAlley/bountyHunterLodge/stairsUp",
			PresetColour.BASE_GREEN_LIGHT,
			BountyHunterLodge.STAIRS,
			Darkness.ALWAYS_LIGHT,
			null,
			"于“锈项圈酒馆”")
		.initWeatherImmune(Weather.MAGIC_STORM);
	
	// First floor:
	
	public static final AbstractPlaceType BOUNTY_HUNTER_LODGE_UPSTAIRS_CORRIDOR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"走廊",
			"一条狭窄的走廊，连接着旅店二楼的各个房间。",
			null,
			PresetColour.BASE_BLACK,
			BountyHunterLodge.UPSTAIRS_CORRIDOR,
			Darkness.ALWAYS_LIGHT,
			null,
			"于“锈项圈酒馆”") {
		@Override
		public boolean isLoiteringEnabledOverride() {
			return true;
		}
		@Override
		public boolean isLoiteringEnabled() {
			return true;
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);

	public static final AbstractPlaceType BOUNTY_HUNTER_LODGE_UPSTAIRS_STAIRS = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"楼梯",
			"一段狭小的楼梯，通向旅店的一楼。",
			"dominion/slaverAlley/bountyHunterLodge/stairsDown",
			PresetColour.BASE_RED_LIGHT,
			BountyHunterLodge.UPSTAIRS_STAIRS,
			Darkness.ALWAYS_LIGHT,
			null,
			"于“锈项圈酒馆”")
		.initWeatherImmune(Weather.MAGIC_STORM);

	public static final AbstractPlaceType BOUNTY_HUNTER_LODGE_UPSTAIRS_ROOM = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"房间",
			"这间房间和其他的一样，已经被租住出去了……",
			"dominion/slaverAlley/bountyHunterLodge/room",
			PresetColour.BASE_TEAL,
			BountyHunterLodge.UPSTAIRS_ROOM,
			Darkness.ALWAYS_LIGHT,
			null,
			"于“锈项圈酒馆”")
		.initWeatherImmune(Weather.MAGIC_STORM);

	public static final AbstractPlaceType BOUNTY_HUNTER_LODGE_UPSTAIRS_ROOM_DOBERMANNS = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"房间",
			"这间房间和其他的一样，已经被租住出去了……",
			"dominion/slaverAlley/bountyHunterLodge/room",
			PresetColour.BASE_TEAL,
			BountyHunterLodge.UPSTAIRS_ROOM,
			Darkness.ALWAYS_LIGHT,
			null,
			"于“锈项圈酒馆”")
		.initWeatherImmune(Weather.MAGIC_STORM);

	public static final AbstractPlaceType BOUNTY_HUNTER_LODGE_UPSTAIRS_ROOM_SHADOW_SILENCE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"房间",
			"这间房间和其他的一样，已经被租住出去了……",
			"dominion/slaverAlley/bountyHunterLodge/room",
			PresetColour.BASE_TEAL,
			BountyHunterLodge.UPSTAIRS_ROOM,
			Darkness.ALWAYS_LIGHT,
			null,
			"于“锈项圈酒馆”")
		.initWeatherImmune(Weather.MAGIC_STORM);

	
	// Watering hole:
	
	public static final AbstractPlaceType WATERING_HOLE_ENTRANCE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"入口",
			"“饮水洼夜店”的入口，一对斑马男打手在看门。",
			"dominion/nightLife/exit",
			PresetColour.BASE_RED,
			NightlifeDistrict.WATERING_HOLE_ENTRANCE,
			Darkness.ALWAYS_LIGHT,
			null, "于“饮水洼夜店”") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(false, PopulationType.CROWD, PopulationDensity.DENSE, Subspecies.getWorldSpecies(WorldType.NIGHTLIFE_CLUB, this, false)));
		}
		@Override
		public boolean isFurniturePresent() {
			return true;
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType WATERING_HOLE_MAIN_AREA = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"饮水洼夜店",
			"这家夜店里人头攒动，人人都忙着豪饮、谈天、调情。",
			null,
			PresetColour.BASE_BLUE_LIGHT,
			NightlifeDistrict.WATERING_HOLE_MAIN,
			Darkness.ALWAYS_LIGHT,
			null, "于“饮水洼夜店”") {
		@Override
		public List<Population> getPopulation() {
			return WATERING_HOLE_ENTRANCE.getPopulation();
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType WATERING_HOLE_SEATING_AREA = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"座位区",
			"夜店墙边设置的区域，相对安静，来寻欢作乐的家伙可以在这里坐下来好好交谈。",
			"dominion/nightLife/seatingArea",
			PresetColour.BASE_BROWN,
			NightlifeDistrict.WATERING_HOLE_SEATING,
			Darkness.ALWAYS_LIGHT,
			null, "于“饮水洼夜店”") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(false, PopulationType.CROWD, PopulationDensity.SPARSE, Subspecies.getWorldSpecies(WorldType.NIGHTLIFE_CLUB, this, false)));
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType WATERING_HOLE_VIP_AREA = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"VIP区",
			"夜店的VIP区有着数个半环形的包间，每个包间内部都摆着一张锃光瓦亮的黑色大理石桌和一条曲线型的真皮长沙发，两个体格健壮的狮子打手守在此处。",
			"dominion/nightLife/vipArea",
			PresetColour.BASE_PURPLE,
			NightlifeDistrict.WATERING_HOLE_VIP,
			Darkness.ALWAYS_LIGHT,
			null, "于“饮水洼夜店”") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.VIP, PopulationDensity.SEVERAL,
					Util.newHashMapOfValues(
							new Value<>(Subspecies.getSubspeciesFromId("innoxia_panther_subspecies_lion"), SubspeciesSpawnRarity.TEN),
							new Value<>(Subspecies.HORSE_MORPH_ZEBRA, SubspeciesSpawnRarity.TEN))));
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType WATERING_HOLE_BAR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"吧台",
			"夜店的吧台永远都是人满为患，数个斑马和狮子女应侍接连不断的上着酒。",
			"dominion/nightLife/bar",
			PresetColour.BASE_ORANGE,
			NightlifeDistrict.WATERING_HOLE_BAR,
			Darkness.ALWAYS_LIGHT,
			null, "于“饮水洼夜店”") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(false, PopulationType.CROWD, PopulationDensity.DENSE, Subspecies.getWorldSpecies(WorldType.NIGHTLIFE_CLUB, this, false)));
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType WATERING_HOLE_DANCE_FLOOR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"舞蹈台",
			"中央的舞蹈台总是整个夜店最活跃、最吵闹的区域。",
			"dominion/nightLife/danceFloor",
			PresetColour.BASE_PINK_DEEP,
			NightlifeDistrict.WATERING_HOLE_DANCE_FLOOR,
			Darkness.ALWAYS_LIGHT,
			null, "于“饮水洼夜店”") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(false, PopulationType.CROWD, PopulationDensity.DENSE, Subspecies.getWorldSpecies(WorldType.NIGHTLIFE_CLUB, this, false)));
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType WATERING_HOLE_TOILETS = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"厕所",
			"夜店的厕所格外大，有着许多隔间和水池。",
			"dominion/nightLife/toilets",
			PresetColour.BASE_BLUE_LIGHT,
			NightlifeDistrict.WATERING_HOLE_TOILETS,
			Darkness.ALWAYS_LIGHT,
			null, "于“饮水洼夜店”的厕所") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.PERSON, PopulationDensity.SEVERAL, Subspecies.getWorldSpecies(WorldType.NIGHTLIFE_CLUB, this, false)));
		}
	}.initWeatherImmune();
	
	
	// Daddy's apartment:
	
	public static final AbstractPlaceType DADDY_APARTMENT_ENTRANCE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"门厅",
			"这间公寓的门厅。",
			"dominion/daddy/entranceHall",
			PresetColour.BASE_GREEN,
			DaddyDialogue.PLACE_ENTRANCE_HALL,
			Darkness.ALWAYS_LIGHT,
			null, "于爹地公寓的门厅"
		).initWeatherImmune();

	public static final AbstractPlaceType DADDY_APARTMENT_LOUNGE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"休息室",
			"这间公寓的休息室。",
			"dominion/daddy/lounge",
			PresetColour.BASE_ORANGE,
			DaddyDialogue.PLACE_LOUNGE,
			Darkness.ALWAYS_LIGHT,
			null, "于爹地公寓的休息室"
		).initWeatherImmune();

	public static final AbstractPlaceType DADDY_APARTMENT_KITCHEN = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"厨房",
			"这间公寓的厨房",
			"dominion/daddy/kitchen",
			PresetColour.BASE_TAN,
			DaddyDialogue.PLACE_KITCHEN,
			Darkness.ALWAYS_LIGHT,
			null, "于爹地公寓的厨房"
		).initWeatherImmune();

	public static final AbstractPlaceType DADDY_APARTMENT_BEDROOM = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"卧室",
			"这间公寓的卧室",
			"dominion/daddy/bedroom",
			PresetColour.BASE_CRIMSON,
			DaddyDialogue.PLACE_BEDROOM,
			Darkness.ALWAYS_LIGHT,
			null, "于爹地的卧室"
		).initWeatherImmune();
	
	
	// Helena's apartment:
	
	public static final AbstractPlaceType HELENA_APARTMENT_HALLWAY = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"走廊",
			"宽敞的走廊铺满了地毯，连接着海伦娜公寓的各个房间。",
			null,
			PresetColour.BASE_BLACK,
			HelenaApartment.PLACE_HALLWAY,
			Darkness.ALWAYS_LIGHT,
			null, "于海伦娜公寓的走廊"
		) {
			@Override
			public List<Population> getPopulation() {
				return Util.newArrayListOfValues(new Population(false, PopulationType.MAID, PopulationDensity.OCCASIONAL, Util.newHashMapOfValues(new Value<>(Subspecies.HARPY, SubspeciesSpawnRarity.TEN))));
			}
		}.initWeatherImmune();
	
	public static final AbstractPlaceType HELENA_APARTMENT_BALCONY = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"阳台",
			"这座巨大的木质阳台俯瞰着御城区的街道。",
			null,
			PresetColour.BASE_BLUE_LIGHT,
			HelenaApartment.PLACE_BALCONY,
			Darkness.ALWAYS_LIGHT,
			null, "于海伦娜公寓的阳台"
		).initMapBackgroundColour(PresetColour.MAP_BACKGROUND_BLUE)
		.initWeatherImmune();
	
	public static final AbstractPlaceType HELENA_APARTMENT_ENTRANCE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"门厅",
			"海伦娜的顶级公寓的门厅，格外宽敞，现代风格的装饰也很有品味。",
			"dominion/helenaApartment/entranceHall",
			PresetColour.BASE_GREEN,
			HelenaApartment.PLACE_ENTRANCE_HALL,
			Darkness.ALWAYS_LIGHT,
			null, "于海伦娜公寓的门厅"
		) {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(false, PopulationType.MAID, PopulationDensity.ONE, Util.newHashMapOfValues(new Value<>(Subspecies.HARPY, SubspeciesSpawnRarity.TEN))));
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType HELENA_APARTMENT_HELENA_BEDROOM = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"海伦娜的卧室",
			"海伦娜的卧室大到离谱，装饰极其奢华。",
			"dominion/helenaApartment/bedroomHelena",
			PresetColour.BASE_GOLD,
			HelenaApartment.PLACE_HELENA_BEDROOM,
			Darkness.ALWAYS_LIGHT,
			null, "于海伦娜的卧室"
		).initWeatherImmune();
	
	public static final AbstractPlaceType HELENA_APARTMENT_SCARLETT_BEDROOM = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"斯嘉丽的卧室",
			"斯嘉丽的卧室大到离谱，装饰极其奢华。",
			"dominion/helenaApartment/bedroomScarlett",
			PresetColour.BASE_CRIMSON,
			HelenaApartment.PLACE_SCARLETT_BEDROOM,
			Darkness.ALWAYS_LIGHT,
			null, "于斯嘉丽的卧室"
		).initWeatherImmune();
	
	public static final AbstractPlaceType HELENA_APARTMENT_BEDROOM = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"客房",
			"有几间空闲的卧室，可以供在此过夜的访客入住。",
			"dominion/helenaApartment/bedroom",
			PresetColour.BASE_YELLOW,
			HelenaApartment.PLACE_BEDROOM,
			Darkness.ALWAYS_LIGHT,
			null, "于海伦娜卧室的一间客房"
		).initWeatherImmune();
	
	public static final AbstractPlaceType HELENA_APARTMENT_BATHROOM = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"配套浴室",
			"海伦娜公寓的每间卧室都有配套的浴室。",
			"dominion/helenaApartment/bathroom",
			PresetColour.BASE_BLUE_LIGHT,
			HelenaApartment.PLACE_BATHROOM,
			Darkness.ALWAYS_LIGHT,
			null, "于海伦娜公寓的一间浴室里"
		).initWeatherImmune();
	
	public static final AbstractPlaceType HELENA_APARTMENT_OFFICE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"书房",
			"海伦娜的私人书房，坐落于公寓的正中间，她经常在此思考和打发时间。",
			"dominion/helenaApartment/office",
			PresetColour.BASE_BROWN,
			HelenaApartment.PLACE_OFFICE,
			Darkness.ALWAYS_LIGHT,
			null, "于海伦娜公寓的书房"
		).initWeatherImmune();
	
	public static final AbstractPlaceType HELENA_APARTMENT_KITCHEN = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"厨房",
			"海伦娜公寓的厨房，由一位技艺高超的专业厨师负责。",
			"dominion/helenaApartment/kitchen",
			PresetColour.BASE_ORANGE,
			HelenaApartment.PLACE_KITCHEN,
			Darkness.ALWAYS_LIGHT,
			null, "于海伦娜公寓的厨房"
		) {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(false, PopulationType.CHEF, PopulationDensity.ONE, Util.newHashMapOfValues(new Value<>(Subspecies.HARPY, SubspeciesSpawnRarity.TEN))));
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType HELENA_APARTMENT_DINING_ROOM = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"餐厅",
			"在宽敞的餐厅中，摆着一张长桌，周围摆放着十几张椅子，格外惹眼。",
			"dominion/helenaApartment/diningRoom",
			PresetColour.BASE_BLUE_STEEL,
			HelenaApartment.PLACE_DINING_ROOM,
			Darkness.ALWAYS_LIGHT,
			null, "于海伦娜公寓的餐厅"
		) {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(false, PopulationType.MAID, PopulationDensity.ONE, Util.newHashMapOfValues(new Value<>(Subspecies.HARPY, SubspeciesSpawnRarity.TEN))));
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType HELENA_APARTMENT_LOUNGE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"休息室",
			"海伦娜公寓内巨大的开放式休息室，摆放着许多舒适的沙发。",
			"dominion/helenaApartment/lounge",
			PresetColour.BASE_INDIGO,
			HelenaApartment.PLACE_LOUNGE,
			Darkness.ALWAYS_LIGHT,
			null, "于海伦娜公寓的休息室"
		) {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.MAID, PopulationDensity.COUPLE, Util.newHashMapOfValues(new Value<>(Subspecies.HARPY, SubspeciesSpawnRarity.TEN))));
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType HELENA_APARTMENT_HOT_TUB = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"热水浴缸",
			"阳台的一角，摆放着一座奥术驱动的巨型热水浴缸。",
			"dominion/helenaApartment/hotTub",
			PresetColour.BASE_RED_LIGHT,
			HelenaApartment.PLACE_HOT_TUB,
			Darkness.ALWAYS_LIGHT,
			null, "于海伦娜公寓阳台上的热水浴缸"
		).initMapBackgroundColour(PresetColour.MAP_BACKGROUND_BLUE)
		.initWeatherImmune();
	
	

	// Helena's apartment:
	
	public static final AbstractPlaceType NYAN_APARTMENT_HALLWAY = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"走廊",
			"宽敞的走廊铺满了地毯，连接着妮安公寓的各个房间。",
			null,
			PresetColour.BASE_BLACK,
			NyanApartment.HALLWAY,
			Darkness.ALWAYS_LIGHT,
			null,
			"于妮安公寓的走廊"
		).initWeatherImmune();
	
	public static final AbstractPlaceType NYAN_APARTMENT_ENTRANCE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"门厅",
			"妮安公寓的门厅装饰不算华丽，但大小已经足以安置一间衣帽间了。",
			"dominion/nyanApartment/entranceHall",
			PresetColour.BASE_RED,
			NyanApartment.ENTRANCE_HALL,
			Darkness.ALWAYS_LIGHT,
			null,
			"于妮安公寓的门厅"
		).initWeatherImmune();
	
	public static final AbstractPlaceType NYAN_APARTMENT_NYAN_BEDROOM = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"妮安的卧室",
			"妮安的卧室以柔和的颜色为主，摆满了成套的爱情小说和毛绒玩具，只要进入其中就能感到格外宁静。",
			"dominion/nyanApartment/bedroomNyan",
			PresetColour.BASE_PINK_LIGHT,
			NyanApartment.NYAN_BEDROOM,
			Darkness.ALWAYS_LIGHT,
			null,
			"于妮安的卧室"
		).initWeatherImmune();
	
	public static final AbstractPlaceType NYAN_APARTMENT_SPARE_BEDROOM = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"闲置卧室",
			"妮安卧室的正对面，就有一间家具齐全的空卧室。",
			"dominion/nyanApartment/bedroom",
			PresetColour.BASE_LILAC,
			NyanApartment.SPARE_BEDROOM,
			Darkness.ALWAYS_LIGHT,
			null,
			"于妮安公寓的闲置卧室"
		).initWeatherImmune();
	
	public static final AbstractPlaceType NYAN_APARTMENT_ENSUITE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"配套浴室",
			"妮安的卧室里有一间配套浴室，其中一半都被一座巨大的浴缸占据。",
			"dominion/nyanApartment/bathroom",
			PresetColour.BASE_AQUA,
			NyanApartment.ENSUITE,
			Darkness.ALWAYS_LIGHT,
			null,
			"于妮安公寓的配套浴室"
		).initWeatherImmune();

	public static final AbstractPlaceType NYAN_APARTMENT_BATHROOM = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"浴室",
			"这间浴室十分狭小，只有马桶和洗手池。",
			"dominion/nyanApartment/toilet",
			PresetColour.BASE_BLUE_LIGHT,
			NyanApartment.BATHROOM,
			Darkness.ALWAYS_LIGHT,
			null,
			"于妮安公寓的浴室"
		).initWeatherImmune();
	
	public static final AbstractPlaceType NYAN_APARTMENT_KITCHEN = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"厨房",
			"妮安的开放式厨房跟餐厅连成一体，空间比较宽敞。透过窗户可以看到一部分街道。",
			"dominion/nyanApartment/kitchen",
			PresetColour.BASE_ORANGE,
			NyanApartment.KITCHEN,
			Darkness.ALWAYS_LIGHT,
			null,
			"于妮安公寓的厨房"
		).initWeatherImmune();
	
	public static final AbstractPlaceType NYAN_APARTMENT_DINING_ROOM = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"餐厅",
			"妮安的开放式餐厅与厨房连成一体，有几套橱柜，已经自然要有的桌子椅子。透过窗户可以看到一部分街道。",
			"dominion/nyanApartment/diningRoom",
			PresetColour.BASE_BLUE_STEEL,
			NyanApartment.DINING_ROOM,
			Darkness.ALWAYS_LIGHT,
			null,
			"于妮安公寓的餐厅"
		).initWeatherImmune();
	
	public static final AbstractPlaceType NYAN_APARTMENT_LOUNGE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"休息室",
			"妮安公寓宽敞的休息室里有几张豪华的沙发，上面都摆放着五颜六色的坐垫。",
			"dominion/nyanApartment/lounge",
			PresetColour.BASE_INDIGO,
			NyanApartment.LOUNGE,
			Darkness.ALWAYS_LIGHT,
			null,
			"于妮安公寓的休息室"
		).initWeatherImmune();
	
	
	
	// Submission:

	public static final AbstractPlaceType SUBMISSION_ENTRANCE = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"执法者检查点",
			"为了防止小恶魔肆无忌惮地闯入御城区，屈城区的每一处入口都设置了戒备森严的执法者检查站。",
			"submission/submissionExit",
			PresetColour.BASE_BROWN,
			SubmissionGenericPlaces.SEWER_ENTRANCE,
			Darkness.ALWAYS_LIGHT,
			null, "于屈城区") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.ENFORCER, PopulationDensity.NUMEROUS,
					Util.newHashMapOfValues(
							new Value<>(Subspecies.ALLIGATOR_MORPH, SubspeciesSpawnRarity.FIVE),
							new Value<>(Subspecies.CAT_MORPH, SubspeciesSpawnRarity.TEN),
							new Value<>(Subspecies.DOG_MORPH, SubspeciesSpawnRarity.TEN),
							new Value<>(Subspecies.FOX_MORPH, SubspeciesSpawnRarity.FIVE),
							new Value<>(Subspecies.HORSE_MORPH, SubspeciesSpawnRarity.FIVE),
							new Value<>(Subspecies.RABBIT_MORPH, SubspeciesSpawnRarity.FIVE),
							new Value<>(Subspecies.RAT_MORPH, SubspeciesSpawnRarity.FIVE),
							new Value<>(Subspecies.WOLF_MORPH, SubspeciesSpawnRarity.TEN))));
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType SUBMISSION_WALKWAYS = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"通道",
			"遍布屈城区的地下水路上，沿着灰色石墙建起了时常维护的木质通道。",
			null,
			PresetColour.BASE_BLACK,
			SubmissionGenericPlaces.WALKWAYS,
			Darkness.ALWAYS_LIGHT,
			null, "于屈城区") {
		@Override
		public List<Population> getPopulation() {
			List<Population> pop = new ArrayList<>();
			if(Main.game.isExtendedWorkTime()) {
				pop.add(new Population(true, PopulationType.CROWD, PopulationDensity.SPARSE, Subspecies.getWorldSpecies(WorldType.SUBMISSION, this, false)));
			} else {
				pop.add(new Population(true, PopulationType.PERSON, PopulationDensity.COUPLE, Subspecies.getWorldSpecies(WorldType.SUBMISSION, this, false)));
			}
			pop.addAll(SUBMISSION_ENTRANCE.getPopulation());
			return pop;
		}
	}.initWeatherImmune()
	.initAquatic(Aquatic.MIXED);

	public static final AbstractPlaceType SUBMISSION_TUNNELS = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"隧道",
			"阴暗的凹室和宽阔的管道口使这黑暗幽闭的隧道成为埋伏的绝佳地点……",
			"submission/tunnelsIcon",
			PresetColour.BASE_BLACK,
			SubmissionGenericPlaces.TUNNEL,
			Darkness.ALWAYS_DARK, Encounter.SUBMISSION_TUNNELS, "与屈城区"
			).initDangerous()
			.initWeatherImmune()
			.initSexNotBlockedFromCharacterPresent();

	public static final AbstractPlaceType SUBMISSION_BAT_CAVERNS = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"蝙蝠洞窟",
			"屈城区的通道在这里到了尽头，经过一处幽深阴暗的开口后，有一段石台阶通向下方的蝙蝠洞窟。",
			"submission/batCaverns",
			PresetColour.BASE_BLUE,
			SubmissionGenericPlaces.BAT_CAVERNS,
			Darkness.ALWAYS_LIGHT,
			null, "于屈城区"
			).initWeatherImmune()
			.initAquatic(Aquatic.MIXED)
			.initSexNotBlockedFromCharacterPresent();
	
	public static final AbstractPlaceType SUBMISSION_RAT_WARREN = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"鼠窟",
			"鼠窟的入口就在这片区域，是一道石拱门，被一扇沉重的橡木门封住了。",
			"submission/ratWarren",
			PresetColour.BASE_BROWN_DARK,
			SubmissionGenericPlaces.RAT_WARREN,
			Darkness.ALWAYS_DARK,
			null, "于屈城区"
			).initWeatherImmune();
	
	public static final AbstractPlaceType SUBMISSION_GAMBLING_DEN = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"赌场",
			"“赌场”是整个屈城区最声名远扬的好去处，通常都是御城区访客的首要目标。",
			"submission/gamblingDen",
			PresetColour.BASE_GOLD,
			SubmissionGenericPlaces.GAMBLING_DEN,
			Darkness.ALWAYS_LIGHT,
			null, "于屈城区"
			) {
		@Override
		public List<Population> getPopulation() {
			return SUBMISSION_WALKWAYS.getPopulation();
		}
	}.initWeatherImmune()
	.initAquatic(Aquatic.MIXED);
	
	public static final AbstractPlaceType SUBMISSION_LILIN_PALACE = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"莉西丝的宫殿",
			"莉琳长老莉西丝的宫殿位于屈城区的最角落。",
			"submission/lilinPalace",
			PresetColour.BASE_PURPLE,
			SubmissionGenericPlaces.LILIN_PALACE,
			Darkness.ALWAYS_LIGHT,
			null, "于屈城区"
			).initMapBackgroundColour(PresetColour.MAP_BACKGROUND_DARK)
			.initWeatherImmune()
			.initTeleportPermissions(TeleportPermissions.NONE);
	
	public static final AbstractPlaceType SUBMISSION_LILIN_PALACE_GATE = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"莉西丝的宫殿大门",
			"宫殿大门由一群半恶魔把守。",
			"submission/gate",
			PresetColour.BASE_PURPLE_LIGHT,
			SubmissionGenericPlaces.LILIN_PALACE_GATE,
			Darkness.ALWAYS_LIGHT,
			null, "于屈城区") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.GUARD, PopulationDensity.NUMEROUS,
					Util.newHashMapOfValues(new Value<>(Subspecies.HALF_DEMON, SubspeciesSpawnRarity.TEN))));
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_DARK)
	.initWeatherImmune()
	.initTeleportPermissions(TeleportPermissions.NONE);
	
	public static final AbstractPlaceType SUBMISSION_LILIN_PALACE_CAVERN = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"洞窟",
			"莉西丝的宫殿位于一个巨大洞穴的远端，洞穴的地面一直向下倾斜，一直延伸到前门。",
			null,
			PresetColour.BASE_GREY,
			SubmissionGenericPlaces.LILIN_PALACE_CAVERN,
			Darkness.ALWAYS_DARK,
			null, "于屈城区"
			).initMapBackgroundColour(PresetColour.MAP_BACKGROUND_DARK)
			.initWeatherImmune();
	
	
	
	
	// Alpha succubus imp fortress:
	
	public static final AbstractPlaceType SUBMISSION_IMP_FORTRESS_ALPHA = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"小恶魔要塞",
			"在一个巨大的地下洞穴中间，有一个简陋的、筑有围墙的要塞，要塞建在一个凸起的岩石堆上。",
			"submission/impFortress1",
			PresetColour.BASE_CRIMSON,
			SubmissionGenericPlaces.IMP_FORTRESS_ALPHA,
			Darkness.ALWAYS_LIGHT,
			null, "于屈城区") {
		@Override
		public String getSVGString(Set<AbstractPlaceUpgrade> upgrades) {
			if(Main.game.getNpc(FortressAlphaLeader.class).getWorldLocation()!=WorldType.IMP_FORTRESS_ALPHA) {
				return getSVGOverride("submission/impFortress1", PresetColour.BASE_GREEN_LIGHT);
			}
			return getSVGOverride("submission/impFortress1", PresetColour.BASE_CRIMSON);
		}
	}.initDangerous()
	.initWeatherImmune();
	
	public static final AbstractPlaceType SUBMISSION_IMP_TUNNELS_ALPHA = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"小恶魔隧道",
			"这些隧道尤其危险，因为它们是一群充满敌意的游荡小恶魔的巢穴。",
			"submission/impTunnels1Icon",
			PresetColour.BASE_RED,
			SubmissionGenericPlaces.TUNNEL,
			Darkness.ALWAYS_DARK, Encounter.SUBMISSION_TUNNELS, "于屈城区") {
		@Override
		public String getSVGString(Set<AbstractPlaceUpgrade> upgrades) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressAlphaDefeated)) {
				return getSVGOverride("submission/impTunnels1Icon", PresetColour.BASE_GREY);
			}
			return getSVGOverride("submission/impTunnels1Icon", PresetColour.BASE_RED);
		}
	}.initDangerous()
	.initWeatherImmune()
	.initSexNotBlockedFromCharacterPresent();

	public static final AbstractPlaceType FORTRESS_ALPHA_ENTRANCE = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"大门口",
			"要塞的前门是返回屈城区的通道。",
			"submission/impFortress/entrance",
			PresetColour.BASE_RED,
			ImpFortressDialogue.ENTRANCE,
			Darkness.ALWAYS_LIGHT,
			null, "于小恶魔首领要塞"
			).initWeatherImmune();
	
	public static final AbstractPlaceType FORTRESS_ALPHA_COURTYARD = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"庭院",
			"大门与要塞的木质主楼之间，只有一个荒凉、肮脏的庭院。",
			null,
			PresetColour.BASE_BLACK,
			ImpFortressDialogue.COURTYARD,
			Darkness.ALWAYS_LIGHT,
			null, "于小恶魔首领要塞"
			).initWeatherImmune();
	
	public static final AbstractPlaceType FORTRESS_ALPHA_KEEP = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"主楼",
			"这座要塞的统治者居住在一座简陋的主楼里。",
			"submission/impFortress/keep",
			PresetColour.BASE_CRIMSON,
			ImpFortressDialogue.KEEP,
			Darkness.ALWAYS_LIGHT,
			null, "于小恶魔首领要塞"
			).initDangerous()
			.initWeatherImmune();

	
	
	// Imp citadel:
	
	public static final AbstractPlaceType SUBMISSION_IMP_FORTRESS_DEMON = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"小恶魔城堡",
			"天花板高的巨大石墙构成了强大的小恶魔城堡的外围防御工事。",
			"submission/impFortress2",
			PresetColour.BASE_PURPLE,
			SubmissionGenericPlaces.IMP_FORTRESS_DEMON,
			Darkness.ALWAYS_LIGHT,
			null, "于屈城区") {
		@Override
		public String getSVGString(Set<AbstractPlaceUpgrade> upgrades) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressDemonDefeated)) {
				return getSVGOverride("submission/impFortress2", PresetColour.BASE_GREEN_LIGHT);
			}
			return getSVGOverride("submission/impFortress2", PresetColour.BASE_PURPLE_DARK);
		}
	}.initDangerous()
	.initWeatherImmune();
	
	public static final AbstractPlaceType SUBMISSION_IMP_TUNNELS_DEMON = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"小恶魔隧道",
			"这些隧道尤其危险，因为它们是一群充满敌意的游荡小恶魔的巢穴。",
			"submission/impTunnels2Icon",
			PresetColour.BASE_PURPLE,
			SubmissionGenericPlaces.TUNNEL,
			Darkness.ALWAYS_DARK, Encounter.SUBMISSION_TUNNELS, "于屈城区") {
		@Override
		public String getSVGString(Set<AbstractPlaceUpgrade> upgrades) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressDemonDefeated)) {
				return getSVGOverride("submission/impTunnels2Icon", PresetColour.BASE_GREY);
			}
			return getSVGOverride("submission/impTunnels2Icon", PresetColour.BASE_PURPLE);
		}
	}.initDangerous()
	.initWeatherImmune()
	.initSexNotBlockedFromCharacterPresent();
	
	public static final AbstractPlaceType FORTRESS_DEMON_ENTRANCE = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"大门口",
			"巨大的石门是连接城堡和洞穴的唯一通道。",
			"submission/impFortress/entrance",
			PresetColour.BASE_RED,
			ImpCitadelDialogue.ENTRANCE,
			Darkness.ALWAYS_LIGHT,
			null, "于暗夜塞壬的城堡") {
		@Override
		public List<Population> getPopulation() {
			if(ImpCitadelDialogue.isImpsDefeated() || ImpCitadelDialogue.isDefeated()) {
				return new ArrayList<>();
			}
			return Util.newArrayListOfValues(new Population(true, PopulationType.GUARD, PopulationDensity.NUMEROUS,
					Util.newHashMapOfValues(
							new Value<>(Subspecies.IMP_ALPHA, SubspeciesSpawnRarity.THREE),
							new Value<>(Subspecies.IMP, SubspeciesSpawnRarity.TEN))));
		}
		@Override
		public Darkness getDarkness() {
			if(ImpCitadelDialogue.isDefeated()) {
				return Darkness.ALWAYS_DARK;
			}
			return Darkness.ALWAYS_LIGHT;
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType FORTRESS_DEMON_COURTYARD = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"庭院",
			"主堡与外围防御工事之间有一个宽阔的庭院。",
			null,
			PresetColour.BASE_BLACK,
			ImpCitadelDialogue.COURTYARD,
			Darkness.ALWAYS_LIGHT,
			null, "于暗夜塞壬的城堡") {
		@Override
		public List<Population> getPopulation() {
			return FORTRESS_DEMON_ENTRANCE.getPopulation();
		}
		@Override
		public Darkness getDarkness() {
			return FORTRESS_DEMON_ENTRANCE.getDarkness();
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType FORTRESS_DEMON_WELL = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"水井",
			"对于任何要塞的守军来说，像这口井这样的无限淡水资源都是无价之宝。",
			"submission/impFortress/well",
			PresetColour.BASE_BLUE_LIGHT,
			ImpCitadelDialogue.WELL,
			Darkness.ALWAYS_LIGHT,
			null, "于暗夜塞壬的城堡") {
		@Override
		public List<Population> getPopulation() {
			return FORTRESS_DEMON_ENTRANCE.getPopulation();
		}
		@Override
		public Darkness getDarkness() {
			return FORTRESS_DEMON_ENTRANCE.getDarkness();
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType FORTRESS_DEMON_KEEP = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"主楼",
			"城堡的主楼是在洞穴的一面墙壁的坚硬岩面上挖掘和雕刻出来的。",
			"submission/impFortress/keep",
			PresetColour.BASE_PURPLE,
			ImpCitadelDialogue.KEEP,
			Darkness.ALWAYS_LIGHT,
			null, "于暗夜塞壬的城堡") {
		@Override
		public boolean isDangerous() {
			return Main.game.getPlayer().isQuestProgressLessThan(QuestLine.MAIN, Quest.MAIN_2_C_SIRENS_FALL);
		}
		@Override
		public Darkness getDarkness() {
			return FORTRESS_DEMON_ENTRANCE.getDarkness();
		}
	}.initDangerous()
	.initWeatherImmune();
	
	public static final AbstractPlaceType FORTRESS_DEMON_CELLS = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"监狱",
			"这些囚室凿在岩洞的墙壁上，是关押囚犯的地方。",
			"submission/impFortress/cells",
			PresetColour.BASE_TEAL,
			ImpCitadelDialogue.CELLS,
			Darkness.ALWAYS_LIGHT,
			null, "于暗夜塞壬的城堡") {
		@Override
		public List<Population> getPopulation() {
			if(ImpCitadelDialogue.isImpsDefeated() || ImpCitadelDialogue.isDefeated()) {
				return new ArrayList<>();
			}
			return Util.newArrayListOfValues(new Population(true, PopulationType.GUARD, PopulationDensity.FEW,
					Util.newHashMapOfValues(
							new Value<>(Subspecies.IMP_ALPHA, SubspeciesSpawnRarity.THREE),
							new Value<>(Subspecies.IMP, SubspeciesSpawnRarity.TEN))));
		}
		@Override
		public Darkness getDarkness() {
			return FORTRESS_DEMON_ENTRANCE.getDarkness();
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType FORTRESS_LAB = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"实验室",
			"庭院的一侧建有一个大型石结构建筑，其唯一目的就是作为一个专门的实验室。",
			"submission/impFortress/laboratory",
			PresetColour.BASE_GREEN_LIME,
			ImpCitadelDialogue.LABORATORY,
			Darkness.ALWAYS_LIGHT,
			null, "于暗夜塞壬的城堡") {
		@Override
		public List<Population> getPopulation() {
			return FORTRESS_DEMON_CELLS.getPopulation();
		}
		@Override
		public Darkness getDarkness() {
			return FORTRESS_DEMON_ENTRANCE.getDarkness();
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType FORTRESS_DEMON_TREASURY = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"藏宝库",
			"每个城堡的统治者都需要一个可以安全存放珍贵物品的地方。",
			"submission/impFortress/treasury",
			PresetColour.BASE_GOLD,
			ImpCitadelDialogue.TREASURY,
			Darkness.ALWAYS_LIGHT,
			null, "于暗夜塞壬的城堡") {
		@Override
		public List<Population> getPopulation() {
			return FORTRESS_DEMON_CELLS.getPopulation();
		}
		@Override
		public Darkness getDarkness() {
			return FORTRESS_DEMON_ENTRANCE.getDarkness();
		}
	}.initWeatherImmune();
	
	
	
	// Female seducer imp fortress:
	
	public static final AbstractPlaceType SUBMISSION_IMP_FORTRESS_FEMALES = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"小恶魔要塞",
			"在一个巨大的地下洞穴中间，有一个简陋的、筑有围墙的要塞，要塞建在一个凸起的岩石堆上。",
			"submission/impFortress3",
			PresetColour.BASE_PINK,
			SubmissionGenericPlaces.IMP_FORTRESS_FEMALES,
			Darkness.ALWAYS_LIGHT,
			null, "于屈城区") {
		@Override
		public String getSVGString(Set<AbstractPlaceUpgrade> upgrades) {
			if(Main.game.getNpc(FortressFemalesLeader.class).getWorldLocation()!=WorldType.IMP_FORTRESS_FEMALES) {
				return getSVGOverride("submission/impFortress3", PresetColour.BASE_GREEN_LIGHT);
			}
			return getSVGOverride("submission/impFortress3", PresetColour.BASE_PINK);
		}
	}.initDangerous()
	.initWeatherImmune();
	
	public static final AbstractPlaceType SUBMISSION_IMP_TUNNELS_FEMALES = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"小恶魔隧道",
			"这些隧道尤其危险，因为它们是一群充满敌意的游荡小恶魔的巢穴。",
			"submission/impTunnels3Icon",
			PresetColour.BASE_PINK_LIGHT,
			SubmissionGenericPlaces.TUNNEL,
			Darkness.ALWAYS_DARK, Encounter.SUBMISSION_TUNNELS, "于屈城区") {
		@Override
		public String getSVGString(Set<AbstractPlaceUpgrade> upgrades) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressFemalesDefeated)) {
				return getSVGOverride("submission/impTunnels3Icon", PresetColour.BASE_GREY);
			}
			return getSVGOverride("submission/impTunnels3Icon", PresetColour.BASE_PINK_LIGHT);
		}
	}.initDangerous()
	.initWeatherImmune()
	.initSexNotBlockedFromCharacterPresent();

	public static final AbstractPlaceType FORTRESS_FEMALES_ENTRANCE = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"大门口",
			"要塞的前门是返回屈城区的通道。",
			"submission/impFortress/entrance",
			PresetColour.BASE_RED,
			ImpFortressDialogue.ENTRANCE,
			Darkness.ALWAYS_LIGHT,
			null, "于女性小恶魔要塞"
			).initWeatherImmune();
	
	public static final AbstractPlaceType FORTRESS_FEMALES_COURTYARD = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"庭院",
			"大门与要塞的木质主楼之间，只有一个荒凉、肮脏的庭院。",
			null,
			PresetColour.BASE_BLACK,
			ImpFortressDialogue.COURTYARD,
			Darkness.ALWAYS_LIGHT,
			null, "于女性小恶魔要塞"
			).initWeatherImmune();
	
	public static final AbstractPlaceType FORTRESS_FEMALES_KEEP = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"主楼",
			"这座要塞的统治者居住在一座简陋的主楼里。",
			"submission/impFortress/keep",
			PresetColour.BASE_PINK,
			ImpFortressDialogue.KEEP,
			Darkness.ALWAYS_LIGHT,
			null, "于女性小恶魔要塞"
			).initDangerous()
			.initWeatherImmune();

	
	
	// Incubus imp fortress:
	
	public static final AbstractPlaceType SUBMISSION_IMP_FORTRESS_MALES = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"小恶魔要塞",
			"在一个巨大的地下洞穴中间，有一座建在高高的岩石堆上的围墙要塞。",
			"submission/impFortress4",
			PresetColour.BASE_BLUE,
			SubmissionGenericPlaces.IMP_FORTRESS_MALES,
			Darkness.ALWAYS_LIGHT,
			null, "于屈城区") {
		@Override
		public String getSVGString(Set<AbstractPlaceUpgrade> upgrades) {
			if(Main.game.getNpc(FortressMalesLeader.class).getWorldLocation()!=WorldType.IMP_FORTRESS_MALES) {
				return getSVGOverride("submission/impFortress4", PresetColour.BASE_GREEN_LIGHT);
			}
			return getSVGOverride("submission/impFortress4", PresetColour.BASE_BLUE);
		}
	}.initDangerous()
	.initWeatherImmune();
	
	public static final AbstractPlaceType SUBMISSION_IMP_TUNNELS_MALES = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"小恶魔隧道",
			"这些隧道尤其危险，因为它们是一群充满敌意的游荡小恶魔的巢穴。",
			"submission/impTunnels4Icon",
			PresetColour.BASE_BLUE_LIGHT,
			SubmissionGenericPlaces.TUNNEL,
			Darkness.ALWAYS_DARK, Encounter.SUBMISSION_TUNNELS, "于屈城区") {
		@Override
		public String getSVGString(Set<AbstractPlaceUpgrade> upgrades) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressMalesDefeated)) {
				return getSVGOverride("submission/impTunnels4Icon", PresetColour.BASE_GREY);
			}
			return getSVGOverride("submission/impTunnels4Icon", PresetColour.BASE_BLUE_LIGHT);
		}
	}.initDangerous()
	.initWeatherImmune()
	.initSexNotBlockedFromCharacterPresent();

	public static final AbstractPlaceType FORTRESS_MALES_ENTRANCE = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"大门口",
			"要塞的前门是返回屈城区的通道。",
			"submission/impFortress/entrance",
			PresetColour.BASE_RED,
			ImpFortressDialogue.ENTRANCE,
			Darkness.ALWAYS_LIGHT,
			null, "于男性小恶魔要塞"
			).initWeatherImmune();
	
	public static final AbstractPlaceType FORTRESS_MALES_COURTYARD = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"庭院",
			"大门与要塞的木制主楼之间有一个荒废的庭院，院子里摆放着许多箭靶和稻草假人。",
			null,
			PresetColour.BASE_BLACK,
			ImpFortressDialogue.COURTYARD,
			Darkness.ALWAYS_LIGHT,
			null, "于男性小恶魔要塞"
			).initWeatherImmune();
	
	public static final AbstractPlaceType FORTRESS_MALES_KEEP = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"主楼",
			"这座小恶魔要塞的主楼是一座具有鲜明日式风格的单层建筑。",
			"submission/impFortress/keep",
			PresetColour.BASE_BLUE,
			ImpFortressDialogue.KEEP,
			Darkness.ALWAYS_LIGHT,
			null, "于男性小恶魔要塞"
			).initDangerous()
			.initWeatherImmune();
	
	
	
	
	// Lyssieth's palace:
	
	public static final AbstractPlaceType LYSSIETH_PALACE_CORRIDOR = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"走廊",
			"莉西丝宫殿宽阔的走廊就像你想象中莉莉丝的直系女儿的宫殿一样奢华。",
			null,
			PresetColour.BASE_GREY,
			LyssiethPalaceDialogue.CORRIDOR,
			Darkness.ALWAYS_LIGHT,
			null, "于莉西丝的宫殿") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.MAID, PopulationDensity.COUPLE,
					Util.newHashMapOfValues(
							new Value<>(Subspecies.HUMAN, SubspeciesSpawnRarity.THREE),
							new Value<>(Subspecies.HALF_DEMON, SubspeciesSpawnRarity.TEN))));
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType LYSSIETH_PALACE_WINDOWS = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"窗户",
			"主入口大厅左右两侧的走廊上有一扇扇狭长的窗户，从这些窗户可以俯瞰外面的洞穴。",
			null,
			PresetColour.BASE_GREY_DARK,
			LyssiethPalaceDialogue.WINDOWS,
			Darkness.ALWAYS_LIGHT,
			null, "于莉西丝的宫殿") {
		@Override
		public List<Population> getPopulation() {
			return LYSSIETH_PALACE_CORRIDOR.getPopulation();
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_DARK)
	.initWeatherImmune();
	
	public static final AbstractPlaceType LYSSIETH_PALACE_ENTRANCE = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"入口",
			"莉西丝宫殿的入口大厅奢华得令人咋舌，与阴暗单调的外观形成了鲜明对比。",
			"submission/lyssiethsPalace/entrance",
			PresetColour.BASE_RED,
			LyssiethPalaceDialogue.ENTRANCE,
			Darkness.ALWAYS_LIGHT,
			null, "于莉西丝的宫殿") {
		@Override
		public List<Population> getPopulation() {
			return LYSSIETH_PALACE_CORRIDOR.getPopulation();
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType LYSSIETH_PALACE_ROOM = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"房间",
			"在这些客厅里，可以看到长毛绒沙发、雕刻奢华的茶几，甚至还有几架钢琴。",
			"submission/lyssiethsPalace/lounge",
			PresetColour.BASE_PINK,
			LyssiethPalaceDialogue.ROOM,
			Darkness.ALWAYS_LIGHT,
			null, "于莉西丝的宫殿"
			) {
		@Override
		public DialogueNode getBaseDialogue(Cell cell) {
			if(Main.game.getCharactersPresent().contains(Main.game.getNpc(Elizabeth.class))) {
				return DialogueManager.getDialogueFromId("acexp_submission_palace_elizabeth");
			}
			return LyssiethPalaceDialogue.ROOM;
		}
		@Override
		public List<Population> getPopulation() {
			return LYSSIETH_PALACE_CORRIDOR.getPopulation();
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType LYSSIETH_PALACE_HALL = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"大厅",
			"在宫殿的每个侧翼，都有一个长长的、装饰奢华的餐厅，莉西丝用这个来招待她特别重要的客人。",
			"submission/lyssiethsPalace/hall",
			PresetColour.BASE_ORANGE_LIGHT,
			LyssiethPalaceDialogue.HALL,
			Darkness.ALWAYS_LIGHT,
			null, "于莉西丝的宫殿"
			) {
		@Override
		public List<Population> getPopulation() {
			return LYSSIETH_PALACE_CORRIDOR.getPopulation();
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType LYSSIETH_PALACE_OFFICE = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"莉西丝的办公室",
			"在莉西丝装潢豪华的办公室里，几乎总能看到她扮演重要高管的身影。",
			"submission/lyssiethsPalace/office",
			PresetColour.BASE_GOLD,
			LyssiethPalaceDialogue.LYSSIETH_OFFICE_ENTER,
			Darkness.ALWAYS_LIGHT,
			null, "于莉西丝的宫殿"
			).initWeatherImmune();
	
	public static final AbstractPlaceType LYSSIETH_PALACE_SIREN_OFFICE = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"梅拉克西丝的办公室",
			"通往莉西丝的必经之路已被改建成一间办公室兼等候室，工作人员正是她的女儿梅拉克西丝。",
			"submission/lyssiethsPalace/officeSiren",
			PresetColour.BASE_CRIMSON,
			LyssiethPalaceDialogue.SIREN_OFFICE,
			Darkness.ALWAYS_LIGHT,
			null, "于莉西丝的宫殿") {
		@Override
		public List<Population> getPopulation() {
			if(Main.game.getNpc(DarkSiren.class).getHomeWorldLocation()!=WorldType.LYSSIETH_PALACE) {
				return Util.newArrayListOfValues(new Population(false, PopulationType.RECEPTIONIST, PopulationDensity.ONE,
						Util.newHashMapOfValues(
								new Value<>(Subspecies.HALF_DEMON, SubspeciesSpawnRarity.TEN))));
			}
			return super.getPopulation();
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType LYSSIETH_PALACE_STAIRS_1 = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"楼梯",
			"这些楼梯通向一楼的房间，莉西丝和她的员工们在这里拥有自己的私人卧室。",
			"submission/lyssiethsPalace/staircase",
			PresetColour.BASE_GREEN,
			LyssiethPalaceDialogue.STAIRCASE,
			Darkness.ALWAYS_LIGHT,
			null, "于莉西丝的宫殿") {
		@Override
		public List<Population> getPopulation() {
			return LYSSIETH_PALACE_CORRIDOR.getPopulation();
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType LYSSIETH_PALACE_STAIRS_2 = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"楼梯",
			"这些楼梯通向一楼的房间，莉西丝和她的员工们在这里拥有自己的私人卧室。",
			"submission/lyssiethsPalace/staircase",
			PresetColour.BASE_GREEN,
			LyssiethPalaceDialogue.STAIRCASE,
			Darkness.ALWAYS_LIGHT,
			null, "于莉西丝的宫殿") {
		@Override
		public List<Population> getPopulation() {
			return LYSSIETH_PALACE_CORRIDOR.getPopulation();
		}
	}.initWeatherImmune();
	
	
	
	// Bat caverns:

	public static final AbstractPlaceType BAT_CAVERN_ENTRANCE = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"旋转楼梯",
			"这条蜿蜒绵长的阶梯由坚硬的岩石凿成，一直通向屈城区的主通道。",
			"submission/batCaverns/cavernStaircase",
			PresetColour.BASE_GREEN,
			BatCaverns.STAIRCASE,
			Darkness.ALWAYS_LIGHT,
			null, "于蝙蝠洞窟"
			).initWeatherImmune();
	
	public static final AbstractPlaceType BAT_CAVERN_DARK = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"阴暗洞窟",
			"蝙蝠洞窟内漆黑一片，令人压抑，但整个地面都长满了散发着柔和光芒的苔藓。",
			null,
			PresetColour.BASE_GREY,
			BatCaverns.CAVERN_DARK,
			Darkness.ALWAYS_DARK,
			Encounter.BAT_CAVERN,
			"于蝙蝠洞窟"
			).initDangerous()
			.initWeatherImmune();
	
	public static final AbstractPlaceType BAT_CAVERN_LIGHT = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"生物微光照亮的洞窟",
			"在洞穴的这个特殊区域，苔藓地毯在各种形状、大小和颜色的生物发光真菌中茁壮成长，并形成了蜿蜒穿过该区域的蔓生路径。",
			"submission/batCaverns/cavernBioluminescent",
			PresetColour.BASE_AQUA,
			BatCaverns.CAVERN_LIGHT,
			Darkness.ALWAYS_LIGHT, Encounter.BAT_CAVERN, "于蝙蝠洞窟"
			) {
		@Override
		public List<Population> getPopulation() {
			if(Main.game.getCharactersPresent().contains(Main.game.getNpc(Elle.class))) {
				return Util.newArrayListOfValues(new Population(true, PopulationType.GANG_MEMBER, PopulationDensity.SEVERAL, Util.newHashMapOfValues(
						new Value<>(Subspecies.RAT_MORPH, SubspeciesSpawnRarity.TEN),
						new Value<>(Subspecies.ALLIGATOR_MORPH, SubspeciesSpawnRarity.TEN),
						new Value<>(Subspecies.DOG_MORPH, SubspeciesSpawnRarity.TEN))));
			}
			return super.getPopulation();
		}
	}.initDangerous()
	.initWeatherImmune();
	
	public static final AbstractPlaceType BAT_CAVERN_RIVER = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"地下河",
			"一条缓慢流淌的地下河在蝙蝠洞窟中穿行，发光地衣发出的微弱光线无法穿透黑暗的深处。",
			"submission/batCaverns/cavernRiver",
			PresetColour.BASE_BLUE,
			BatCaverns.RIVER,
			Darkness.ALWAYS_DARK, Encounter.BAT_CAVERN, "于蝙蝠洞窟"
			).initDangerous()
			.initWeatherImmune()
			.initAquatic(Aquatic.MIXED);
	
	public static final AbstractPlaceType BAT_CAVERN_RIVER_CROSSING = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"蘑菇桥",
			"一对树干大小的生物发光的蘑菇被精心塑造成水平编织的模式，以形成一座跨越河流深处的活生生的桥梁。",
			"submission/batCaverns/cavernBridge",
			PresetColour.BASE_TEAL,
			BatCaverns.RIVER_BRIDGE,
			Darkness.ALWAYS_DARK, Encounter.BAT_CAVERN, "于蝙蝠洞窟"
			).initDangerous()
			.initWeatherImmune()
			.initAquatic(Aquatic.MIXED);
	
	public static final AbstractPlaceType BAT_CAVERN_RIVER_END = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"地下河尽头",
			"在水边小路的尽头，河水一泻而下，落入漆黑的深渊。为了防止有人被卷入深不见底的深渊，在河边建起了一道细密的金属网。",
			"submission/batCaverns/cavernRiverEnd",
			PresetColour.BASE_BLUE_DARK,
			BatCaverns.RIVER_END,
			Darkness.ALWAYS_DARK, Encounter.BAT_CAVERN, "于蝙蝠洞窟"
			).initDangerous()
			.initWeatherImmune()
			.initAquatic(Aquatic.MIXED);
	
	public static final AbstractPlaceType BAT_CAVERN_SLIME_QUEEN_LAIR = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"史莱姆湖",
			"一个巨大的地下湖泊被两岸的生物发光菌林照亮。在静静的黑色湖水中央，坐落着一个长满蘑菇的小岛。",
			"submission/batCaverns/cavernLake",
			PresetColour.BASE_PINK_LIGHT,
			BatCaverns.SLIME_LAKE,
			Darkness.ALWAYS_LIGHT,
			Encounter.BAT_CAVERN,
			"于史莱姆湖边"
			).initDangerous()
			.initWeatherImmune()
			.initAquatic(Aquatic.MIXED);

	public static final AbstractPlaceType BAT_CAVERN_SHAFT = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"通向御城区的竖井",
			"天花板上有一个蜿蜒曲折的大竖井，直接连接着蝙蝠洞窟和御城区。",
			"submission/batCaverns/cavernShaft",
			PresetColour.BASE_GREEN,
			BatCaverns.SHAFT,
			Darkness.DAYLIGHT,
			null,
			"于蝙蝠洞窟"
			).initWeatherImmune();
	
	// HLF Quest places:
	
	public static final AbstractPlaceType BAT_CAVERNS_REBEL_BASE_ENTRANCE_EXTERIOR = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"隐藏的洞穴入口",
			"一个神秘的人工洞穴的入口，以前隐藏在一扇紧闭的石门后面。",
			"submission/rebelBase/entrance",
			PresetColour.BASE_RED,
			BatCaverns.REBEL_BASE_ENTRANCE_EXTERIOR,
			Darkness.ALWAYS_DARK,
			Encounter.BAT_CAVERN,
			"于神秘人工洞穴的入口边"
			).initDangerous()
			.initWeatherImmune();
        
       public static final AbstractPlaceType BAT_CAVERNS_REBEL_BASE_ENTRANCE_HANDLE = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"奇怪的拉杆",
			"岩石上凸出一个奇怪的拉杆。",
			"submission/rebelBase/entrance",
			PresetColour.BASE_GREY,
			BatCaverns.REBEL_BASE_ENTRANCE_HANDLE,
			Darkness.ALWAYS_DARK,
			Encounter.BAT_CAVERN,
			"于蝙蝠洞窟"
			).initDangerous()
			.initWeatherImmune();
	
	// Slime queen's island tower:

	public static final AbstractPlaceType SLIME_QUEENS_LAIR_CORRIDOR = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"走廊",
			"走廊中间铺着厚厚的酒红色和金色相间的地毯，两侧的石墙上挂着厚厚的织物挂毯。",
			null,
			PresetColour.BASE_GREY,
			SlimeQueensLair.CORRIDOR,
			Darkness.ALWAYS_LIGHT,
			null, "于史莱姆女王之塔中"
			).initWeatherImmune();
	
	public static final AbstractPlaceType SLIME_QUEENS_LAIR_ENTRANCE = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"门厅",
			"一扇沉重的铁栏杆橡木门，是人们进出这座塔楼的通道。",
			"submission/slimeQueensLair/entranceHall",
			PresetColour.BASE_RED,
			SlimeQueensLair.ENTRANCE,
			Darkness.ALWAYS_LIGHT,
			null, "于史莱姆女王之塔中"
			).initWeatherImmune();
	
	public static final AbstractPlaceType SLIME_QUEENS_LAIR_STAIRS_UP = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"旋转楼梯",
			"一条狭窄的螺旋楼梯通向塔楼的第二层。",
			"submission/slimeQueensLair/staircase",
			PresetColour.BASE_GREEN,
			SlimeQueensLair.STAIRCASE_UP,
			Darkness.ALWAYS_LIGHT,
			null, "于史莱姆女王之塔中"
			).initWeatherImmune();
	
	public static final AbstractPlaceType SLIME_QUEENS_LAIR_STAIRS_DOWN = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"旋转楼梯",
			"一条狭窄的螺旋楼梯通向塔楼一层。",
			"submission/slimeQueensLair/staircase",
			PresetColour.BASE_RED,
			SlimeQueensLair.STAIRCASE_DOWN,
			Darkness.ALWAYS_LIGHT,
			null, "于史莱姆女王之塔中"
			).initWeatherImmune();
	
	public static final AbstractPlaceType SLIME_QUEENS_LAIR_ROOM = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"卧室",
			"这间卧室是塔楼的一名或多名守卫休息的地方，里面摆放着一张整齐的四柱床和常见的卧室家具。",
			"submission/slimeQueensLair/room",
			PresetColour.BASE_BLUE_LIGHT,
			SlimeQueensLair.ROOM,
			Darkness.ALWAYS_LIGHT,
			null, "于史莱姆女王之塔中"
			).initWeatherImmune();
	
	public static final AbstractPlaceType SLIME_QUEENS_LAIR_STORAGE_VATS = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"蒸馏厂",
			"位于这个房间的巨大蒸馏装置不仅是屈城区中所有转化粘液的来源，也是流行饮料“史莱姆畅饮”的来源。",
			"submission/slimeQueensLair/storageVats",
			PresetColour.BASE_ORANGE,
			SlimeQueensLair.STORAGE_VATS,
			Darkness.ALWAYS_LIGHT,
			null, "于史莱姆女王之塔中") {
		@Override
		public void applyInventoryInit(CharacterInventory inventory) {
			for(int i=0; i<15; i++) {
				inventory.addItem(Main.game.getItemGen().generateItem("innoxia_race_slime_slime_quencher"));
			}
			for(int i=0; i<5; i++) {
				inventory.addItem(Main.game.getItemGen().generateItem("innoxia_race_slime_biojuice_canister"));
			}
		}
	}.initItemsPersistInTile()
	.initWeatherImmune();
	
	public static final AbstractPlaceType SLIME_QUEENS_LAIR_ENTRANCE_GUARDS = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"护卫岗哨",
			"这个区域有一个齐胸高的木制路障，从墙壁到墙壁都是木制的。",
			"submission/slimeQueensLair/guards",
			PresetColour.BASE_RED,
			SlimeQueensLair.GUARD_POST,
			Darkness.ALWAYS_LIGHT,
			null, "于史莱姆女王之塔中"
			) {
		@Override
		public boolean isDangerous() {
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slimeGuardsDefeated) && !Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_FOUR);
		}
	}.initDangerous()
	.initWeatherImmune();
	
	public static final AbstractPlaceType SLIME_QUEENS_LAIR_ROYAL_GUARD = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"皇家护卫岗哨",
			"一只强大的紫红色孵化史莱姆守卫着这条特殊的走廊。",
			"submission/slimeQueensLair/royalGuards",
			PresetColour.BASE_PURPLE,
			SlimeQueensLair.ROYAL_GUARD_POST,
			Darkness.ALWAYS_LIGHT,
			null, "于史莱姆女王之塔中"
			) {
		@Override
		public boolean isDangerous() {
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slimeRoyalGuardDefeated) && !Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_FOUR);
		}
	}.initDangerous()
	.initWeatherImmune();
	
	public static final AbstractPlaceType SLIME_QUEENS_LAIR_SLIME_QUEEN = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"寝宫",
			"史莱姆女王的卧室里有一张巨大的四柱床和一个巨大的浴缸，浴缸里充满了大量半透明的粉红色液体。",
			"submission/slimeQueensLair/bedChamber",
			PresetColour.BASE_PINK,
			SlimeQueensLair.BED_CHAMBER,
			Darkness.ALWAYS_LIGHT,
			null, "于史莱姆女王之塔中"
			).initWeatherImmune();
	
	
	
	
	// Gambling Den:
	
	public static final AbstractPlaceType GAMBLING_DEN_CORRIDOR = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"赌场",
			"赌场宽敞的中庭墙壁上摆放着数不清的老虎机，中央摆放着许多桌椅。",
			null,
			PresetColour.BASE_BLACK,
			GamblingDenDialogue.CORRIDOR,
			Darkness.ALWAYS_LIGHT,
			null, "于赌场") {
		@Override
		public List<Population> getPopulation() {
			Map<AbstractSubspecies, SubspeciesSpawnRarity> popComponent = new HashMap<>(Subspecies.getWorldSpecies(WorldType.SUBMISSION, this, false));
			Subspecies.getWorldSpecies(WorldType.DOMINION, this, false).forEach((key, value) -> popComponent.merge(key, value, (v1, v2) -> v1));
			popComponent.remove(Subspecies.IMP);
			popComponent.remove(Subspecies.IMP_ALPHA);
			return Util.newArrayListOfValues(new Population(true, PopulationType.CROWD, PopulationDensity.SPARSE, popComponent));
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType GAMBLING_DEN_ENTRANCE = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"入口",
			"赌场正门的入口总是敞开着，方便众多顾客进出。",
			"submission/gamblingDen/entrance",
			PresetColour.BASE_GREEN,
			GamblingDenDialogue.ENTRANCE,
			Darkness.ALWAYS_LIGHT,
			null, "于赌场") {
		@Override
		public List<Population> getPopulation() {
			return GAMBLING_DEN_CORRIDOR.getPopulation();
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType GAMBLING_DEN_OFFICE = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"阿克塞尔的卧室",
			"阿克塞尔的办公室位于正门旁边，不使用时会上锁。",
			"submission/gamblingDen/office",
			PresetColour.BASE_ORANGE,
			GamblingDenDialogue.OFFICE,
			Darkness.ALWAYS_LIGHT,
			null, "于赌场") {
	}.initWeatherImmune();
	
	public static final AbstractPlaceType GAMBLING_DEN_TRADER = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"罗克西小店",
			"“罗克西小店”是一家价格偏高的当铺，它所提供的商品在御城区可以以更低的价格买到。",
			"submission/gamblingDen/trader",
			PresetColour.BASE_TEAL,
			RoxysShop.TRADER_EXTERIOR,
			Darkness.ALWAYS_LIGHT,
			null, "于赌场"
			).initWeatherImmune();
	
	public static final AbstractPlaceType GAMBLING_DEN_GAMBLING = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"骰子扑克赌桌",
			"骰子扑克是赌场最吸引人的游戏之一，为这种游戏准备的许多赌桌几乎总是座无虚席。",
			"submission/gamblingDen/gambling",
			PresetColour.BASE_GOLD,
			GamblingDenDialogue.GAMBLING,
			Darkness.ALWAYS_LIGHT,
			null, "于赌场") {
		@Override
		public List<Population> getPopulation() {
			return GAMBLING_DEN_CORRIDOR.getPopulation();
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType GAMBLING_DEN_PREGNANCY_ROULETTE = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"怀孕轮盘赌",
			"“怀孕轮盘赌”游戏由马女伊波娜在一个镶嵌在墙壁上的长木头柜台后面主持。",
			"submission/gamblingDen/referee",
			PresetColour.BASE_PINK,
			PregnancyRoulette.PREGNANCY_ROULETTE,
			Darkness.ALWAYS_LIGHT,
			null, "于赌场") {
		@Override
		public List<Population> getPopulation() {
			return GAMBLING_DEN_CORRIDOR.getPopulation();
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType GAMBLING_DEN_PREGNANCY = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"配种台",
			"自愿参与“怀孕轮盘赌”的男性在此处进行赌局。",
			"submission/gamblingDen/normalPregnancy",
			PresetColour.BASE_BLUE_LIGHT,
			GamblingDenDialogue.PREGNANCY_ROULETTE_MALE_STALLS,
			Darkness.ALWAYS_LIGHT,
			null, "于赌场"
			).initWeatherImmune();
	
	public static final AbstractPlaceType GAMBLING_DEN_FUTA_PREGNANCY = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"扶她配种台",
			"自愿参与“怀孕轮盘赌”的扶她在此处进行赌局。",
			"submission/gamblingDen/futaPregnancy",
			PresetColour.BASE_PINK_LIGHT,
			GamblingDenDialogue.PREGNANCY_ROULETTE_FUTA_STALLS,
			Darkness.ALWAYS_LIGHT,
			null, "于赌场"
			).initWeatherImmune();
	
	
	
	
	// Rat warrens:

	public static final AbstractPlaceType RAT_WARRENS_CORRIDOR_LEFT = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"蜿蜒的走廊",
			"鼠窟内蜿蜒的走廊根据洞窟的宽度和地势变化极大。",
			null,
			PresetColour.BASE_BLACK,
			RatWarrensDialogue.CORRIDOR,
			Darkness.ALWAYS_LIGHT,
			null, "于鼠窟") {
		@Override
		public boolean isDangerous() {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile) && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft);
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType RAT_WARRENS_CHECKPOINT_LEFT = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"蜿蜒的走廊",
			"鼠窟内蜿蜒的走廊根据洞窟的宽度和地势变化极大。",
			null,
			PresetColour.BASE_BLACK,
			RatWarrensDialogue.CORRIDOR,
			Darkness.ALWAYS_LIGHT,
			null, "于鼠窟") {
		@Override
		public boolean isDangerous() {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile)
					&& (!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft) || !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre));
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType RAT_WARRENS_CORRIDOR = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"蜿蜒的走廊",
			"鼠窟内蜿蜒的走廊根据洞窟的宽度和地势变化极大。",
			null,
			PresetColour.BASE_BLACK,
			RatWarrensDialogue.CORRIDOR,
			Darkness.ALWAYS_LIGHT,
			null, "于鼠窟") {
		@Override
		public boolean isDangerous() {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile) && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre);
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType RAT_WARRENS_CORRIDOR_RIGHT = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"蜿蜒的走廊",
			"鼠窟内蜿蜒的走廊根据洞窟的宽度和地势变化极大。",
			null,
			PresetColour.BASE_BLACK,
			RatWarrensDialogue.CORRIDOR,
			Darkness.ALWAYS_LIGHT,
			null, "于鼠窟") {
		@Override
		public DialogueNode getDialogue(Cell c, boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isCaptive()) {
				return VengarCaptiveDialogue.CORRIDOR;
			}
			return super.getDialogue(c, withRandomEncounter, forceEncounter);
		}
		@Override
		public boolean isDangerous() {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile) && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedRight);
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType RAT_WARRENS_CHECKPOINT_RIGHT = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"蜿蜒的走廊",
			"鼠窟内蜿蜒的走廊根据洞窟的宽度和地势变化极大。",
			null,
			PresetColour.BASE_BLACK,
			RatWarrensDialogue.CORRIDOR,
			Darkness.ALWAYS_LIGHT,
			null, "于鼠窟") {
		@Override
		public boolean isDangerous() {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile)
					&& (!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre) || !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedRight));
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType RAT_WARRENS_ENTRANCE = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"入口",
			"鼠窟的入口总有至少两个帮派成员在站岗。",
			"submission/ratWarrens/entrance",
			PresetColour.BASE_GREEN,
			RatWarrensDialogue.ENTRANCE,
			Darkness.ALWAYS_LIGHT,
			null, "于鼠窟") {
		@Override
		public boolean isDangerous() {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile) && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre);
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType RAT_WARRENS_DORMITORY_LEFT = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"宿舍",
			"阴暗潮湿的房间里，墙边靠着一张双层床，几把桌椅散落在屋中央。",
			"submission/ratWarrens/dormitory",
			PresetColour.BASE_BROWN,
			RatWarrensDialogue.DORMITORY,
			Darkness.ALWAYS_LIGHT,
			null, "于鼠窟") {
		@Override
		public List<Population> getPopulation() {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft)) {
				if(Main.game.isExtendedWorkTime()) {
					return Util.newArrayListOfValues(new Population(true, PopulationType.GANG_MEMBER, PopulationDensity.FEW, Util.newHashMapOfValues(new Value<>(Subspecies.RAT_MORPH, SubspeciesSpawnRarity.TEN))));
				} else {
					return Util.newArrayListOfValues(new Population(true, PopulationType.GANG_MEMBER, PopulationDensity.SEVERAL, Util.newHashMapOfValues(new Value<>(Subspecies.RAT_MORPH, SubspeciesSpawnRarity.TEN))));
				}
			}
			return new ArrayList<>();
		}
		@Override
		public boolean isDangerous() {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile) && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft);
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType RAT_WARRENS_DORMITORY_RIGHT = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"宿舍",
			"阴暗潮湿的房间里，墙边靠着一张双层床，几把桌椅散落在屋中央。",
			"submission/ratWarrens/dormitory",
			PresetColour.BASE_BROWN,
			RatWarrensDialogue.DORMITORY,
			Darkness.ALWAYS_LIGHT,
			null, "于鼠窟") {
		@Override
		public List<Population> getPopulation() {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedRight)) {
				if(Main.game.isExtendedWorkTime()) {
					return Util.newArrayListOfValues(new Population(true, PopulationType.GANG_MEMBER, PopulationDensity.FEW, Util.newHashMapOfValues(new Value<>(Subspecies.RAT_MORPH, SubspeciesSpawnRarity.TEN))));
				} else {
					return Util.newArrayListOfValues(new Population(true, PopulationType.GANG_MEMBER, PopulationDensity.SEVERAL, Util.newHashMapOfValues(new Value<>(Subspecies.RAT_MORPH, SubspeciesSpawnRarity.TEN))));
				}
			}
			return new ArrayList<>();
		}
		@Override
		public boolean isDangerous() {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile) && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedRight);
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType RAT_WARRENS_DICE_DEN = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"骰子赌场",
			"帮派成员喝酒赌博的地方。",
			"submission/ratWarrens/diceDen",
			PresetColour.BASE_COPPER,
			RatWarrensDialogue.DICE_DEN,
			Darkness.ALWAYS_LIGHT,
			null, "于鼠窟") {
		@Override
		public List<Population> getPopulation() {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre)) {
				if(Main.game.isExtendedWorkTime()) {
					return Util.newArrayListOfValues(new Population(true, PopulationType.GANG_MEMBER, PopulationDensity.SEVERAL, Util.newHashMapOfValues(new Value<>(Subspecies.RAT_MORPH, SubspeciesSpawnRarity.TEN))));
				} else {
					return Util.newArrayListOfValues(new Population(true, PopulationType.GANG_MEMBER, PopulationDensity.COUPLE, Util.newHashMapOfValues(new Value<>(Subspecies.RAT_MORPH, SubspeciesSpawnRarity.TEN))));
				}
			}
			return new ArrayList<>();
		}
		@Override
		public boolean isDangerous() {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile) && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre);
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType RAT_WARRENS_MILKING_ROOM = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"产奶间",
			"对于不幸被文加团伙绑架的人来说，这是作为人类的最后一站。",
			"submission/ratWarrens/stocks",
			PresetColour.BASE_MAGENTA,
			RatWarrensDialogue.MILKING_ROOM,
			Darkness.ALWAYS_LIGHT,
			null, "于鼠窟") {
		@Override
		public DialogueNode getDialogue(Cell c, boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isCaptive()) {
				dialogue = RatWarrensCaptiveDialogue.CAPTIVE_NIGHT;
			} else {
				dialogue = RatWarrensDialogue.MILKING_ROOM;
			}
			return super.getDialogue(c, withRandomEncounter, forceEncounter);
		}
		@Override
		public boolean isDangerous() {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile) && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft);
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType RAT_WARRENS_MILKING_STORAGE = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"储奶处",
			"此处储藏着大量接奶的金属桶。每个都贴好了盛放的这种那种的体液类型，还标记了口味。",
			"submission/ratWarrens/milkingRoom",
			PresetColour.BASE_YELLOW_LIGHT,
			RatWarrensDialogue.MILKING_STORAGE,
			Darkness.ALWAYS_LIGHT,
			null, "于鼠窟") {
		@Override
		public boolean isDangerous() {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile) && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft);
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType RAT_WARRENS_VENGARS_HALL = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"文加的主厅",
			"宏伟的石制主厅，由数不清的长条木头支架搭成，远处是高高在上的王座。",
			"submission/ratWarrens/vengarsHall",
			PresetColour.BASE_PURPLE,
			RatWarrensDialogue.VENGARS_HALL,
			Darkness.ALWAYS_LIGHT,
			null, "于鼠窟") {
		@Override
		public AbstractEncounter getEncounterType() {
			if(Main.game.getPlayer().isCaptive()) {
				return Encounter.VENGAR_CAPTIVE_HALL;
			}
			return null;
		}
		@Override
		public DialogueNode getDialogue(Cell c, boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isCaptive()) {
				dialogue = VengarCaptiveDialogue.VENGARS_HALL;
			} else {
				dialogue = RatWarrensDialogue.VENGARS_HALL;
			}
			return super.getDialogue(c, withRandomEncounter, forceEncounter);
		}
		@Override
		public List<Population> getPopulation() {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre)) {
				return Util.newArrayListOfValues(new Population(true, PopulationType.GANG_MEMBER, PopulationDensity.NUMEROUS, Util.newHashMapOfValues(new Value<>(Subspecies.RAT_MORPH, SubspeciesSpawnRarity.TEN))));
			}
			return new ArrayList<>();
		}
		@Override
		public boolean isDangerous() {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile);
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType RAT_WARRENS_PRIVATE_BEDCHAMBERS = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"私人包厢",
			"文加和他的保镖专享的私人卧室包房，与主厅毗连。",
			"submission/ratWarrens/bedroom",
			PresetColour.BASE_PURPLE_LIGHT,
			RatWarrensDialogue.VENGARS_BEDROOM,
			Darkness.ALWAYS_LIGHT,
			null,
			"于鼠窟") {
		@Override
		public AbstractEncounter getEncounterType() {
			if(Main.game.getPlayer().isCaptive()) {
				return Encounter.VENGAR_CAPTIVE_BEDROOM;
			}
			return null;
		}
		@Override
		public DialogueNode getDialogue(Cell c, boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isCaptive()) {
				dialogue = VengarCaptiveDialogue.VENGARS_BEDROOM;
			} else {
				dialogue = RatWarrensDialogue.VENGARS_BEDROOM;
			}
			return super.getDialogue(c, withRandomEncounter, forceEncounter);
		}
		@Override
		public boolean isDangerous() {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile);
		}
	}.initWeatherImmune();

	// HLF Quest places:
	
    public static final AbstractPlaceType REBEL_BASE_ENTRANCE = new AbstractPlaceType(
 			WorldRegion.SUBMISSION,
			"入口",
			"唯一进出洞穴的路巧妙地藏在一扇紧闭的石门后。",
			"submission/rebelBase/entrance",
			PresetColour.BASE_RED,
			RebelBase.REBEL_BASE_ENTRANCE,
			Darkness.ALWAYS_DARK,
			null,
			"于神秘人工洞穴")
	            .initWeatherImmune();
    
    public static final AbstractPlaceType REBEL_BASE_CORRIDOR = new AbstractPlaceType(
 			WorldRegion.SUBMISSION,
			"走廊",
			"由质量堪忧的木头架子支起的人工洞穴。",
			null,
			PresetColour.BASE_BLACK,
			RebelBase.REBEL_BASE_CORRIDOR,
			Darkness.ALWAYS_DARK,
			null,
			"于神秘人工洞穴")
	            .initWeatherImmune();
    
    public static final AbstractPlaceType REBEL_BASE_SLEEPING_AREA = new AbstractPlaceType(
 			WorldRegion.SUBMISSION,
			"废弃的宿舍区",
			"长期空置的房间，里面有许多早已弃用的床。",
			"submission/rebelBase/cache1",
			PresetColour.BASE_BLUE,
			RebelBase.REBEL_BASE_SLEEPING_AREA,
			Darkness.ALWAYS_DARK,
			Encounter.REBEL_BASE,
			"于神秘人工洞穴")
	            .initWeatherImmune();
    
    public static final AbstractPlaceType REBEL_BASE_SLEEPING_AREA_SEARCHED = new AbstractPlaceType(
 			WorldRegion.SUBMISSION,
			"废弃的宿舍区",
			"长期空置的房间，里面有许多早已弃用的床。",
			"submission/rebelBase/cache1",
			PresetColour.BASE_GREY,
			RebelBase.REBEL_BASE_SLEEPING_AREA_SEARCHED,
			Darkness.ALWAYS_DARK,
			null,
			"于神秘人工洞穴")
	            .initWeatherImmune();
    
    public static final AbstractPlaceType REBEL_BASE_COMMON_AREA = new AbstractPlaceType(
 			WorldRegion.SUBMISSION,
			"废弃的日常区",
			"日常区，摆着稀稀拉拉的家具。",
			"submission/rebelBase/cache2",
			PresetColour.BASE_ORANGE,
			RebelBase.REBEL_BASE_COMMON_AREA,
			Darkness.ALWAYS_DARK,
			null,
			"于神秘人工洞穴")
	            .initWeatherImmune();
    
    public static final AbstractPlaceType REBEL_BASE_COMMON_AREA_SEARCHED = new AbstractPlaceType(
 			WorldRegion.SUBMISSION,
			"废弃的日常区",
			"日常区，摆着稀稀拉拉的家具。",
			"submission/rebelBase/cache2",
			PresetColour.BASE_GREY,
			RebelBase.REBEL_BASE_COMMON_AREA_SEARCHED,
			Darkness.ALWAYS_DARK,
			null,
			"于神秘人工洞穴")
	            .initWeatherImmune();

    public static final AbstractPlaceType REBEL_BASE_ARMORY = new AbstractPlaceType(
 			WorldRegion.SUBMISSION,
			"部分塌方的房间",
			"一个房间，砖石瓦砾散落一地。",
			"submission/rebelBase/cache3",
			PresetColour.BASE_GREEN,
			RebelBase.REBEL_BASE_ARMORY,
			Darkness.ALWAYS_DARK,
			null,
			"于神秘人工洞穴")
	            .initWeatherImmune();
    
    public static final AbstractPlaceType REBEL_BASE_ARMORY_SEARCHED = new AbstractPlaceType(
 			WorldRegion.SUBMISSION,
			"部分塌方的房间",
			"一个房间，砖石瓦砾散落一地。",
			"submission/rebelBase/cache3",
			PresetColour.BASE_GREY,
			RebelBase.REBEL_BASE_ARMORY_SEARCHED,
			Darkness.ALWAYS_DARK,
			null,
			"于神秘人工洞穴")
	            .initWeatherImmune();
    
     public static final AbstractPlaceType REBEL_BASE_CAVED_IN_ROOM = new AbstractPlaceType(
 			WorldRegion.SUBMISSION,
			"多数塌方的房间",
			"一个房间，空余断壁残垣。",
			"submission/rebelBase/cavein",
			PresetColour.BASE_GREY_DARK,
			RebelBase.REBEL_BASE_CAVED_IN_ROOM,
			Darkness.ALWAYS_DARK,
			null,
			"于神秘人工洞穴")
	            .initWeatherImmune();
	
	
	
	// World map tiles:

	public static final AbstractGlobalPlaceType WORLD_MAP_THICK_JUNGLE = new AbstractGlobalPlaceType(
			WorldRegion.JUNGLE,
			"浓密的丛林",
			null,
			"愈向丛林深处行进，植被愈密，从而让充满野性的危险猎食者可以轻松隐匿自己的身形……",
			new Colour(Util.newColour(0x6b8f7e)), null, null, "于丛林中") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous();
	
	public static final AbstractGlobalPlaceType WORLD_MAP_JUNGLE = new AbstractGlobalPlaceType(
			WorldRegion.JUNGLE,
			"丛林",
			null,
			"覆盖着稀疏的热带植被，是各种的丛林动物化形的家园，但他们之中并非所有都那么友好。",
			new Colour(Util.newColour(0x8fbfa8)), null, null, "于丛林中") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous();
	
	public static final AbstractGlobalPlaceType WORLD_MAP_JUNGLE_CITY = new AbstractGlobalPlaceType(
			WorldRegion.JUNGLE_CITY,
			"伊察阿克",
			null,
			"伊察阿克是一个广大的玛雅风格城市，作为文明的最后堡垒，位于北部辽阔而荒野的丛林之外。",
			new Colour(Util.newColour(0xb377b0)), null, null, "于伊察阿克城外") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	};

	public static final AbstractGlobalPlaceType WORLD_MAP_FOOTHILLS = new AbstractGlobalPlaceType(
			WorldRegion.MOUNTAINS,
			"山麓",
			null,
			"随着地势升高，海拔逐渐抬升，最终通向位于皎月山脉脚下连绵起伏的丘陵。",
			PresetColour.BASE_BLACK, null, null, "于皎月山脉的山麓") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous();
	
	public static final AbstractGlobalPlaceType WORLD_MAP_MOUNTAINS = new AbstractGlobalPlaceType(
			WorldRegion.MOUNTAINS,
			"山脉",
			null,
			"一直延伸向西方的山脉被称为“皎月山脉”，是许多高山动物化形的家园。",
			PresetColour.BASE_GREY_DARK, null, null, "于皎月山脉") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous();
	
	public static final AbstractGlobalPlaceType WORLD_MAP_SNOWY_MOUNTAINS = new AbstractGlobalPlaceType(
			WorldRegion.MOUNTAINS,
			"顶峰",
			null,
			"皎月山脉的最高峰白雪皑皑，是某些充满野性和侵略性种族的居所……",
			PresetColour.BASE_GREY_LIGHT, null, null, "于皎月山脉") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous();

	public static final AbstractGlobalPlaceType WORLD_MAP_SNOWY_VALLEY = new AbstractGlobalPlaceType(
			WorldRegion.SNOW,
			"风雪峡谷",
			null,
			"这个用于避风的山谷经常迎来大雪，是许多北极种族的家园。",
			new Colour(Util.newColour(0xeeeeee)), null, null, "于风雪峡谷") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous();
	
	public static final AbstractGlobalPlaceType WORLD_MAP_GLACIAL_LAKE = new AbstractGlobalPlaceType(
			WorldRegion.SNOW,
			"塞尔克湖",
			null,
			"风雪峡谷的西边，有一片有时会结冰的巨大湖泊。",
			new Colour(Util.newColour(0xbbf0f1)), null, null, "于塞尔克湖") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous()
	.initAquatic(Aquatic.MIXED);

	public static final AbstractGlobalPlaceType WORLD_MAP_DOMINION = new AbstractGlobalPlaceType(
			WorldRegion.DOMINION,
			"御城区城郊",
			"莉莉斯王国的首都，御城区正是这位魅魔女王的权力宝座。",
			"global/dominion",
			PresetColour.BASE_PURPLE,
			new Colour(Util.newColour(0x826B85)),
			FieldsDialogue.DOMINION_EXTERIOR,
			null, "于御城区城郊") {
		@Override
		protected DialogueNode getBaseDialogue(Cell cell) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.leftDominionFirstTime)) {
				return FieldsDialogue.DOMINION_EXTERIOR;
			} else {
				return DialogueManager.getDialogueFromId("innoxia_places_fields_leaving_dominion_start");
			}
		}
		@Override
		public AbstractWorldType getGlobalLinkedWorldType() {
			return WorldType.DOMINION;
		}
		@Override
		public List<Population> getPopulation() {
			List<Population> pop = new ArrayList<>();
			
			if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
				pop.add(new Population(true, PopulationType.PERSON, PopulationDensity.COUPLE, Subspecies.getDominionStormImmuneSpecies(true)));
				pop.add(new Population(false, PopulationType.ENFORCER, PopulationDensity.OCCASIONAL, Subspecies.getDominionStormImmuneSpecies(true, Subspecies.HUMAN)));
			} else {
				pop.add(new Population(true, PopulationType.PERSON, PopulationDensity.SEVERAL, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
				pop.add(new Population(false, PopulationType.ENFORCER, PopulationDensity.OCCASIONAL, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true, Subspecies.HUMAN)));
				pop.add(new Population(false, PopulationType.CENTAUR_CARTS, PopulationDensity.OCCASIONAL, Util.newHashMapOfValues(new Value<>(Subspecies.CENTAUR, SubspeciesSpawnRarity.TEN))));
			}
			
			return pop;
		}
	}.initAquatic(Aquatic.MIXED);

	public static final AbstractGlobalPlaceType WORLD_MAP_GRASSLANDS = new AbstractGlobalPlaceType(
			WorldRegion.FIELDS,
			"绿原荒野",//"global/grassland",
			null,
			"绿原荒野是各色种族的家园，其中绝大多数和他们居住的土地一样，充满野性，未被驯服。",
			new Colour(Util.newColour(0x688255)),
			FieldsDialogue.GRASSLAND_WILDERNESS,
			null, "于弗洛伊田野的绿原荒野") {
		@Override
		public AbstractEncounter getEncounterType() {
			return CECILIA_AMBUSH;
		}
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous();
	
	public static final AbstractGlobalPlaceType WORLD_MAP_FIELDS = new AbstractGlobalPlaceType(
			WorldRegion.FIELDS,
			"弗洛伊田野",
			null,
			"环绕着御城区的农田被称为“弗洛伊田野”，主要居住着农场动物化形。",
			new Colour(Util.newColour(0xB9E3A1)),
			FieldsDialogue.FOLOI_FIELDS,
			null, "于弗洛伊田野") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous();
	
	public static final AbstractGlobalPlaceType WORLD_MAP_FOREST = new AbstractGlobalPlaceType(
			WorldRegion.WOODLAND,
			"森林",
			"包围着弗洛伊田野的茂密森林危机四伏，这里是狼、狐、熊等野性的猎食者化形的居所。",
			"global/forest",
			new Colour(Util.newColour(0x51A468)),
			new Colour(Util.newColour(0x5E685E)),
			FieldsDialogue.FOLOI_FOREST,
			null, "于弗洛伊田野覆盖着森林的区域") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous();

	public static final AbstractGlobalPlaceType WORLD_MAP_FIELDS_CITY = new AbstractGlobalPlaceType(
			WorldRegion.FIELD_CITY,
			"伊利斯",
			"弗洛伊田野上最大也是最繁荣的定居点，伊利斯是妖狐和其他居住在山脉上的种族贸易的枢纽。",
			"global/elis",
			new Colour(Util.newColour(0xd544ae)),
			new Colour(Util.newColour(0x859871)),
			FieldsDialogue.ELIS,
			null, "于伊利斯城外") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	};
	
	public static final AbstractGlobalPlaceType WORLD_MAP_RIVER = new AbstractGlobalPlaceType(
			WorldRegion.RIVER,
			"胡布尔河",
			"胡布尔河自西向东流经御城区，最终流入无尽之海。河流的这一段作为弗洛伊田野的边界，比较安全。",
			"global/river",
			new Colour(Util.newColour(0x61BDFF)),
			new Colour(Util.newColour(0x98B4CD)),
			FieldsDialogue.RIVER_HUBUR,
			null, "于胡布尔河") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initAquatic(Aquatic.MIXED)
	.initDangerous();

	public static final AbstractGlobalPlaceType WORLD_MAP_WILD_RIVER = new AbstractGlobalPlaceType(
			WorldRegion.RIVER,
			"胡布尔河(荒野)",
			null,
			"远离御城区后，胡布尔河就不再适合下水，充满了危险，有许多淡水种族居住其中。",
			new Colour(Util.newColour(0xc1f1ee)),
			DialogueManager.getDialogueFromId("innoxia_places_shinrin_highlands_global_river"),
			null,
			"于胡布尔河") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous()
	.initAquatic(Aquatic.MIXED);

	public static final AbstractGlobalPlaceType WORLD_MAP_YOUKO_FOREST = new AbstractGlobalPlaceType(
			WorldRegion.YOUKO_FOREST,
			"心林高地",
			null,
			"心林高地是一片被森林覆盖的低矮丘陵，越往西边地势越高。行踪难觅的妖狐居住于此，向这片区域施展了令传送术失效的法术。",
			PresetColour.MAP_BACKGROUND_SHINRIN_HIGHLANDS,
			DialogueManager.getDialogueFromId("innoxia_places_shinrin_highlands_global_shinrin_highlands"),
			null,
			"于心林高地") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
				@Override
				public TeleportPermissions getTeleportPermissions() {
					return TeleportPermissions.NONE;
				}
	}.initDangerous();

	public static final AbstractGlobalPlaceType WORLD_MAP_SEA = new AbstractGlobalPlaceType(
			WorldRegion.SEA,
			"无尽之海",
			null,
			"居住在莉莉丝王国的水生种族不愿远离海岸，所以海洋才似乎是无穷无尽的。",
			PresetColour.BASE_BLUE_DARK, null, null, "于无尽之海") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous()
	.initAquatic(Aquatic.WATER_SURFACE);
	
	public static final AbstractGlobalPlaceType WORLD_MAP_SEA_CITY = new AbstractGlobalPlaceType(
			WorldRegion.SEA_CITY,
			"莱昂内斯",
			null,
			"莱昂内斯这座水下城市位于东海岸之外，当然，非水生种族难以前往。",
			new Colour(Util.newColour(0x8264b0)), null, null, "于莱昂内斯城外") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initAquatic(Aquatic.WATER_UNDER);

	public static final AbstractGlobalPlaceType WORLD_MAP_ARID_GRASSLAND = new AbstractGlobalPlaceType(
			WorldRegion.SAVANNAH,
			"荒漠草原",
			null,
			"向南方走去，保留着野性的草原逐渐变得干旱，但这里正是狮子、豹子、斑马的安身立命之处。",
			PresetColour.BASE_YELLOW_LIGHT, null, null, "于荒漠草原") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous();
	
	public static final AbstractGlobalPlaceType WORLD_MAP_ARID_SAVANNAH = new AbstractGlobalPlaceType(
			WorldRegion.SAVANNAH,
			"稀树草原",
			null,
			"这个地区分布着开阔树冠的稀疏林地，居住着与荒漠草原相同的种族。",
			PresetColour.BASE_TAN, null, null, "于稀树草原") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous();

	public static final AbstractGlobalPlaceType WORLD_MAP_DESERT = new AbstractGlobalPlaceType(
			WorldRegion.DESERT,
			"沙漠",
			null,
			"荒漠草原再向南，所有植被都已干枯，只留下一片炎热荒芜的废土。",
			new Colour(Util.newColour(0xffe7a7)), null, null, "于沙漠中") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous();
	
	public static final AbstractGlobalPlaceType WORLD_MAP_SAND_DUNES = new AbstractGlobalPlaceType(
			WorldRegion.DESERT,
			"沙丘",
			null,
			"沙漠南缘有着大片沙丘，栖息着许多危险的种族。",
			new Colour(Util.newColour(0xffdb7a)), null, null, "于沙丘中") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous();
	
	public static final AbstractGlobalPlaceType WORLD_MAP_DESERT_CITY = new AbstractGlobalPlaceType(
			WorldRegion.DESERT_CITY,
			"提尼斯",
			null,
			"提尼斯是一座古埃及风格的城市，莉莉丝王国最南端的定居点，并以其著名的奥术学府而闻名。",
			new Colour(Util.newColour(0xd5445e)), null, null, "于提尼斯城外") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	};

	public static final AbstractGlobalPlaceType WORLD_MAP_VOLCANO = new AbstractGlobalPlaceType(
			WorldRegion.VOLCANO,
			"龙息火山",
			null,
			"一座巨大的火山，不断涌出炽热的岩浆。尽管名中有龙，但在这里睹见真龙同样是一件罕事，和莉莉丝王国的其他地方一样。",
			PresetColour.BASE_ORANGE, null, null, "于龙息火山") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous();
	
	public static final AbstractGlobalPlaceType WORLD_MAP_LAVA_FLOWS = new AbstractGlobalPlaceType(
			WorldRegion.VOLCANO,
			"岩浆缓流",
			null,
			"从火山口涌出的岩浆缓缓向着南方流淌。",
			PresetColour.BASE_BLACK, null, null, "于岩浆缓流处") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous();
	
	
	
	private static List<AbstractPlaceType> allPlaceTypes = new ArrayList<>();
	private static Map<AbstractPlaceType, String> placeToIdMap = new HashMap<>();
	private static Map<String, AbstractPlaceType> idToPlaceMap = new HashMap<>();

	public static List<AbstractPlaceType> getAllPlaceTypes() {
		return allPlaceTypes;
	}
	
	public static AbstractPlaceType getPlaceTypeFromId(String id) {
		id = id.replaceAll("ALEXA", "HELENA");
		id = id.replaceAll("SUPPLIER_DEPOT", "TEXTILE_WAREHOUSE");
		
		if(id.equals("ZARANIX_FF_BEDROOM")) {
			id = "ZARANIX_FF_OFFICE";
			
		} else if(id.equals("LILAYA_HOME_ROOM_WINDOW_GROUND_FLOOR_SLAVE")
				|| id.equals("LILAYA_HOME_ROOM_WINDOW_GROUND_FLOOR_MILKING")) {
			id = "LILAYA_HOME_ROOM_WINDOW_GROUND_FLOOR";
			
		} else if(id.equals("LILAYA_HOME_ROOM_GARDEN_GROUND_FLOOR_SLAVE")
				|| id.equals("LILAYA_HOME_ROOM_GARDEN_GROUND_FLOOR_MILKING")) {
			id = "LILAYA_HOME_ROOM_GARDEN_GROUND_FLOOR";
			
		} else if(id.equals("LILAYA_HOME_ROOM_WINDOW_FIRST_FLOOR_SLAVE")
				|| id.equals("LILAYA_HOME_ROOM_WINDOW_FIRST_FLOOR_MILKING")) {
			id = "LILAYA_HOME_ROOM_WINDOW_FIRST_FLOOR";
			
		} else if(id.equals("LILAYA_HOME_ROOM_GARDEN_FIRST_FLOOR_SLAVE")
				|| id.equals("LILAYA_HOME_ROOM_GARDEN_FIRST_FLOOR_MILKING")) {
			id = "LILAYA_HOME_ROOM_GARDEN_FIRST_FLOOR";
			
		} else if(id.equals("DOMINION_EXIT_TO_JUNGLE")) {
			id = "DOMINION_EXIT_EAST";
		} else if(id.equals("DOMINION_EXIT_TO_DESERT")) {
			id = "DOMINION_EXIT_SOUTH";
		} else if(id.equals("DOMINION_EXIT_TO_FIELDS")) {
			id = "DOMINION_EXIT_NORTH";
		} else if(id.equals("DOMINION_EXIT_TO_SEA")) {
			id = "DOMINION_EXIT_WEST";
			
		} else if(id.equals("SHOPPING_ARCADE_SUPPLIER_DEPOT")) {
			id = "SHOPPING_ARCADE_RESTAURANT";
			
		} else if(id.equals("innoxia_fields_elis_town_tavern_seedy")) {
			id = "innoxia_fields_elis_town_tavern_alley";
		}
		
		id = Util.getClosestStringMatch(id, idToPlaceMap.keySet());
		return idToPlaceMap.get(id);
	}

	public static String getIdFromPlaceType(AbstractPlaceType placeType) {
		return placeToIdMap.get(placeType);
	}
	
	static {
		// Modded place types:
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/maps", "placeTypes", null);
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				try {
					String id = innerEntry.getKey().replace("_placeTypes", "");
					AbstractPlaceType placeType = new AbstractPlaceType(innerEntry.getValue(), entry.getKey(), id, true) {};
					allPlaceTypes.add(placeType);
					placeToIdMap.put(placeType, id);
					idToPlaceMap.put(id, placeType);
//					System.out.println("modded PT: "+innerEntry.getKey());
				} catch(Exception ex) {
					System.err.println("Loading modded place type failed at 'PlaceType'. File path: "+innerEntry.getValue().getAbsolutePath());
										ex.printStackTrace(System.err);
				}
			}
		}
		
		// External res place types:
		
		Map<String, Map<String, File>> filesMap = Util.getExternalFilesById("res/maps", "placeTypes", null);
		for(Entry<String, Map<String, File>> entry : filesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				try {
					String id = innerEntry.getKey().replace("_placeTypes", "");
					AbstractPlaceType placeType = new AbstractPlaceType(innerEntry.getValue(), entry.getKey(), id, false) {};
					allPlaceTypes.add(placeType);
					placeToIdMap.put(placeType, id);
					idToPlaceMap.put(id, placeType);
//					System.out.println("res PT: "+innerEntry.getKey()+" | "+id);
				} catch(Exception ex) {
					System.err.println("Loading place type failed at 'PlaceType'. File path: "+innerEntry.getValue().getAbsolutePath());
										ex.printStackTrace(System.err);
				}
			}
		}

		// Hard-coded place types (all those up above):
		
		Field[] fields = PlaceType.class.getFields();
		
		for(Field f : fields) {
			if(AbstractPlaceType.class.isAssignableFrom(f.getType())) {
				AbstractPlaceType placeType;
				try {
					placeType = ((AbstractPlaceType) f.get(null));

					placeToIdMap.put(placeType, f.getName());
					idToPlaceMap.put(f.getName(), placeType);
					allPlaceTypes.add(placeType);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
