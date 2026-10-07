package com.lilithsthrone.game.dialogue.places.dominion.lilayashome;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueManager;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.RenderingEngine;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldRegion;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.78
 * @version 0.4.3.4
 * @author Innoxia, Rfpnj
 */
public class Library {
	
	private enum LibraryAisle {
		DEMON,
		DOMINION,
		FIELDS,
		JUNGLE,
		MOUNTAIN,
		SEA,
		DESERT;
	}
	
	private static Set<AbstractSubspecies> getAisleSubspecies(LibraryAisle aisle) {
		Set<AbstractSubspecies> aisleSubspecies = new HashSet<>();

		for(AbstractSubspecies subspecies : Subspecies.getAllSubspecies()) {
			boolean add = false;
			if(subspecies==Subspecies.FOX_ASCENDANT
					|| subspecies==Subspecies.FOX_ASCENDANT_ARCTIC
					|| subspecies==Subspecies.FOX_ASCENDANT_FENNEC) {
				add = aisle==LibraryAisle.FIELDS;
				
			} else {
				List<WorldRegion> mostCommonRegion = subspecies.getMostCommonWorldRegions();
				if(mostCommonRegion.isEmpty()) {
					mostCommonRegion.add(WorldRegion.DOMINION);
				}
				boolean demonic = subspecies.getRace()==Race.DEMON || subspecies.getRace()==Race.ANGEL || subspecies.getRace()==Race.ELEMENTAL;
				switch(aisle) {
					case DEMON:
						add = demonic;
						break;
					case DESERT:
						add = !demonic && (mostCommonRegion.contains(WorldRegion.SAVANNAH) || mostCommonRegion.contains(WorldRegion.DESERT) || mostCommonRegion.contains(WorldRegion.DESERT_CITY) || mostCommonRegion.contains(WorldRegion.VOLCANO));
						break;
					case DOMINION:
						add = !demonic && (mostCommonRegion.contains(WorldRegion.DOMINION) || mostCommonRegion.contains(WorldRegion.SUBMISSION));
						break;
					case FIELDS:
						add = !demonic && (mostCommonRegion.contains(WorldRegion.WOODLAND) || mostCommonRegion.contains(WorldRegion.FIELDS) || mostCommonRegion.contains(WorldRegion.FIELD_CITY) || mostCommonRegion.contains(WorldRegion.RIVER));
						break;
					case JUNGLE:
						add = !demonic && (mostCommonRegion.contains(WorldRegion.JUNGLE) || mostCommonRegion.contains(WorldRegion.JUNGLE_CITY));
						break;
					case MOUNTAIN:
						add = !demonic && (mostCommonRegion.contains(WorldRegion.MOUNTAINS) || mostCommonRegion.contains(WorldRegion.YOUKO_FOREST) || mostCommonRegion.contains(WorldRegion.SNOW));
						break;
					case SEA:
						add = !demonic && (mostCommonRegion.contains(WorldRegion.SEA) || mostCommonRegion.contains(WorldRegion.SEA_CITY));
						break;
				}
			}
			
			if(add) {
				aisleSubspecies.add(subspecies);
			}
		}
		
		return aisleSubspecies;
	}
	
	public static final DialogueNode LIBRARY = new DialogueNode("", "", false) {
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			List<NPC> charactersPresent = Main.game.getNonCompanionCharactersPresent();

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/library", "LIBRARY"));
			
			if(!charactersPresent.isEmpty()) {
				for(NPC slave : charactersPresent) {
					UtilText.nodeContentSB.append(LilayaHomeGeneric.getSlavePresentDescription(slave,
							"甚至懒得假装[npc.sheIs]在工作。",
							"似乎在心猿意马地重新排列书籍。",
							"目前正在清理货架上的灰尘，确保一切井然有序。",
							"目前正在重新整理其中一个货架。",
							"正在尽职尽责地为图书馆现有的所有书籍编制目录。"));
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getResponseTabTitle(int index) {
			if(index==0) {
				return "图书馆";
				
			} else if(index==1) {
				return "快速旅行";
				
			} else if(index==2) {
				return "法术";
				
			} else if(index==3) {
				return "种族";
			}
			
			return null;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==0) {
				List<NPC> charactersPresent = Main.game.getNonCompanionCharactersPresent();
				
				if (index == 1) {
					if(Main.game.getCurrentDialogueNode()==ARCANE_AROUSAL) {
						return new Response("奥术狂欲", "你已经在读这本书了！", null);
					}
					return new Response("奥术狂欲", "一本皮面装订的大部头，似乎能让人了解奥术的运作方式。", ARCANE_AROUSAL) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.readBook1, true);
						}
					};

				} else if (index == 2) {
					if(Main.game.getCurrentDialogueNode()==LILITHS_DYNASTY) {
						return new Response("莉莉丝的王朝", "你已经在读这本书了！", null);
					}
					return new Response("莉莉丝的王朝", "莉莉丝，何许人也？这本精装书或许能带我们一探究竟。", LILITHS_DYNASTY) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.readBook2, true);
						}
					};

				} else if (index == 3) {
					if(Main.game.getCurrentDialogueNode()==DOMINION_HISTORY) {
						return new Response("御城区通史", "你已经在读这本书了！", null);
					}
					return new Response("御城区通史", "一本平装书，描述了这座城市诞生以来的历史纪事。", DOMINION_HISTORY) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.readBook3, true);
						}
					};

				} else if (index == 4) {
					if(Main.game.getCurrentDialogueNode()==PREGNANCY_INFO) {
						return new Response("好孕临门", "你已经在读这本书了！", null);
					}
					return new Response("好孕临门", "小开本的书籍，里面记载了很多怀孕相关的知识。", PREGNANCY_INFO) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.readBook4, true);
						}
					};

				} else if(index==5) {
					if(Main.game.getCurrentDialogueNode()==FERAL_HISTORY) {
						return new Response("兽态人的兴与衰", "你已经在读这本书了！", null);
					}
					return new Response("兽态人的兴与衰", "一本精装书，详细描述了兽态转化在莉莉丝王国社会中的历史故事。", FERAL_HISTORY) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.readBook5, true);
						}
					};
					
				} else if (index == 6) {
					if(Main.game.getCurrentDialogueNode()==DOMINION_MAP) {
						return new Response("城市地图", "你已经在查看御城区的地图了！", null);
					}
					return new Response("城市地图", "一面墙上挂着一幅巨大的、装裱好的御城区地图。给它拍张照。", DOMINION_MAP) {
						@Override
						public void effects() {
							Cell[][] grid = Main.game.getWorlds().get(WorldType.DOMINION).getGrid();
							for(int i=0; i<grid.length; i++) {
								for(int j=0; j<grid[0].length; j++) {
									grid[i][j].setDiscovered(true);
								}
							}
						}
					};
	
				} else if (index == 7 && Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLAVERY, Quest.SIDE_SLAVER_NEED_RECOMMENDATION)) {
					if(Main.game.getCurrentDialogueNode()==SLAVERY_HISTORY) {
						return new Response("以人为产", "你已经在读这本书了！", null);
					}
					return new Response("以人为产", "一本厚厚的精装书，详细介绍了莉莉丝王国奴隶制的历史和合法性。", SLAVERY_HISTORY) {
						@Override
						public void effects() {
							Main.game.appendToTextStartStringBuilder(UtilText.parseFromXMLFile("places/dominion/lilayasHome/library", "SLAVERY_HISTORY"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.readBookSlavery, true);
						}
					};
	
				} else if (index == 8 && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.readBookSlavery)) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.getDialogueFlagValueFromId("acexp_dungeon_found"))) {
						return new Response("莉莱雅的地牢",
								"拉出《莉莱雅的肮脏秘密》这本厚书，打开通往莉莱雅地牢的秘密通道。",
								DialogueManager.getDialogueFromId("acexp_dominion_lilaya_dungeon_stairsUp")) {
							@Override
							public void effects() {
								Main.game.appendToTextStartStringBuilder(UtilText.parseFromXMLFile("places/dominion/lilayasHome/library", "DUNGEON_OPENS"));
								Main.game.appendToTextStartStringBuilder(UtilText.parseFromXMLFile("acexp/dominion/lilaya_dungeon", "DUNGEON_ENTRY"));
								Main.game.getPlayer().setLocation(WorldType.getWorldTypeFromId("acexp_dungeon"), PlaceType.getPlaceTypeFromId("acexp_dungeon_stairs"), false);
							}
						};
						
					} else {
						return new Response("莉莱雅的肮脏秘密", "一本印有“莉莱雅的肮脏秘密”的大部头引起了你的注意……", DUNGEON_TRIGGER) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.getDialogueFlagValueFromId("acexp_dungeon_found"), true);
							}
						};
					}
	
				} else if(index>=9 && index-9<charactersPresent.size()) {
					NPC slave = charactersPresent.get(index-9);
					return LilayaHomeGeneric.interactWithNPC(slave);
				}
				
			} else if(responseTab==1) {
				return LilayaHomeGeneric.getLilayasHouseFastTravelResponses(index);
			
			} else if(responseTab==2) {
				List<Spell> spells = Main.game.getPlayer().getSpells();
				Spell spell = null;
				
				if (index == 0) {
					if(spells.size()>=15) {
						spell = spells.get(14);
					} else {
						return null;
					}

				} else if (index < 15 && index-1 < spells.size()) {
					spell = spells.get(index-1);
					
				} else if (index >= 15 && index < spells.size()) {
					spell = spells.get(index);
				}
				
				if(spell!=null) {
					return getSpellResponse(spell);
				}
				
			} else if(responseTab==3) {
				if (index == 1) {
					return new Response("太古生物", "图书馆中专门收藏有关恶魔和天使种族书籍的区域。", ELDER_RACES);

				} else if (index == 2) {
					return new Response("御城区的种族", "图书馆的一部分专门用来收藏与城市中主要种族有关的书籍。", DOMINION_RACES);

				} else if (index == 3) {
					return new Response("弗洛伊田野", "图书馆有一部分专门用来收藏有关被称为 “弗洛伊田野” 的地区的书籍。", FIELDS_BOOKS);

				} else if (index == 4) {
					return new Response("山脉", "图书馆有一部分专门用来收藏有关被称为 “皎月山脉” 地区的书籍。", MOUNTAIN_BOOKS);

				} else if (index == 5) {
					return new Response("无尽之海", "图书馆有一部分专门用来收藏有关 “无尽之海” 的书籍。", SEA_BOOKS);

				} else if (index == 6) {
					return new Response("丛林", "图书馆有一部分专门用来收藏有关丛林地区的书籍。", JUNGLE_BOOKS);

				} else if (index == 7) {
					return new Response("沙漠", "图书馆的一部分专门用来收藏有关莉莉丝王国南部沙漠的书籍。", DESERT_BOOKS);
				}
			}
			
			return null;
		}
	};
	
	private static Response getSpellResponse(Spell spell) {
		return new Response(spell.getName(), "阅读了解法术'"+spell.getName()+"'。", SPELL_BOOK) {
			@Override
			public void effects() {
				Main.game.getTextEndStringBuilder().append(ItemType.getSpellBookType(spell).getEffects().get(0).applyEffect(Main.game.getPlayer(), Main.game.getPlayer(), 0));
			}
		};
	}

	public static final DialogueNode SPELL_BOOK = new DialogueNode("", "", false) {
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public String getResponseTabTitle(int index) {
			return LIBRARY.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return LIBRARY.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ARCANE_AROUSAL = new DialogueNode("", "", false) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/library", "ARCANE_AROUSAL");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LIBRARY.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return LIBRARY.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode LILITHS_DYNASTY = new DialogueNode("", "", false) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/library", "LILITHS_DYNASTY");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LIBRARY.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int lore) {
			return LIBRARY.getResponse(responseTab, lore);
		}
	};
	
	public static final DialogueNode DOMINION_HISTORY = new DialogueNode("", "", false) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/library", "DOMINION_HISTORY");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LIBRARY.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int lore) {
			return LIBRARY.getResponse(responseTab, lore);
		}
	};
	
	public static final DialogueNode PREGNANCY_INFO = new DialogueNode("", "", false) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/library", "PREGNANCY_INFO");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LIBRARY.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return LIBRARY.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode FERAL_HISTORY = new DialogueNode("", "", false) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/library", "FERAL_HISTORY");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LIBRARY.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return LIBRARY.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode DOMINION_MAP = new DialogueNode("", "", false) {
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/library", "DOMINION_MAP"));
			sb.append(RenderingEngine.ENGINE.getFullMap(WorldType.DOMINION, false, false));
			return sb.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LIBRARY.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return LIBRARY.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode SLAVERY_HISTORY = new DialogueNode("", "", false) {
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LIBRARY.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return LIBRARY.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode DUNGEON_TRIGGER = new DialogueNode("", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/library", "DUNGEON_TRIGGER");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("莉莱雅的地牢",
						"沿着螺旋楼梯前往莉莱雅的地牢。",
						DialogueManager.getDialogueFromId("acexp_dominion_lilaya_dungeon_stairsUp")) {
					@Override
					public void effects() {
						Main.game.appendToTextStartStringBuilder(UtilText.parseFromXMLFile("acexp/dominion/lilaya_dungeon", "DUNGEON_ENTRY"));
						Main.game.getPlayer().setLocation(WorldType.getWorldTypeFromId("acexp_dungeon"), PlaceType.getPlaceTypeFromId("acexp_dungeon_stairs"), false);
					}
				};
			} else if(index==2) {
				return new Response("以后再说", "决定暂时不去莉莱雅的地牢……", DUNGEON_TRIGGER_BACK);
			}
			return null;
		}
	};

	public static final DialogueNode DUNGEON_TRIGGER_BACK = new DialogueNode("", "", false, true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/library", "DUNGEON_TRIGGER_BACK");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LIBRARY.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return LIBRARY.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ELDER_RACES = new DialogueNode("", "", false) {
		@Override
		public String getContent() {
			return "<p>"
						+ "走过其中一条过道，你发现自己被深紫色石头雕刻而成的货架所包围。当你经过时，抛光的表面在头顶灯的照射下闪闪发光。"
						+ "当你触摸这种奇怪的材料时，性快感闪电般贯穿了你的身体。你感到又惊又怕。"
					+ "</p>"
					+ "<p>"
						+ "这些书看起来都是关于恶魔的八卦和老掉牙的轶事，大部分都没什么看头。不过，还是有几本勾起了你的兴趣……"
					+ "</p>";
		}

		@Override
		public String getResponseTabTitle(int index) {
			return LIBRARY.getResponseTabTitle(index);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==3) {
				List<Response> raceResponses = new ArrayList<>();
				Set<AbstractItemType> booksAdded = new HashSet<>();
				for(AbstractSubspecies subspecies : getAisleSubspecies(LibraryAisle.DEMON)) {
					if(booksAdded.add(ItemType.getLoreBook(subspecies))) {
						raceResponses.add(bookResponse(ELDER_RACES, subspecies));
					}
				}
				if(index == 0) {
					return new Response("返回", "回到种族目录。", LIBRARY);
					
				} else if(index>0 && index-1<raceResponses.size()) {
					return raceResponses.get(index-1);
				}
				
				return null;
				
			} else {
				return LIBRARY.getResponse(responseTab, index);
			}
		}
	
	};
	
	public static final DialogueNode DOMINION_RACES = new DialogueNode("", "", false) {
		@Override
		public String getContent() {
			return "<p>"
						+ "你走过一条通道，发现自己被一堆厚实的木头架子围住了，这些木头架子是被凸出来的钢铁横梁连在一起的。"
						+ "这儿很多书都在讲御城区的地方历史，虽然对历史研究者来说应该很有趣，但对你来说就没什么营养了。"
					+ "</p>"
					+ "<p>"
						+ "你敏锐地发现，有几个货架看起来有点与众不同。"
						+ "最上面的架子似乎是用树枝装饰而成，就像鸟巢一样。"
						+ "最靠近地板的架子表面非常光滑，就像木头变成了石头。"
						+ "摸起来好像很湿润。"
					+ "</p>"
					+ "<p>"
						+ "虽然这些书大部分都没什么可读的，但有几本详细介绍了城市里的各种族群，你考虑是不是应该读一读这几本……"
					+ "</p>";
		}

		@Override
		public String getResponseTabTitle(int index) {
			return LIBRARY.getResponseTabTitle(index);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==3) {
				List<Response> raceResponses = new ArrayList<>();
				Set<AbstractItemType> booksAdded = new HashSet<>();
				for(AbstractSubspecies subspecies : getAisleSubspecies(LibraryAisle.DOMINION)) {
					if(booksAdded.add(ItemType.getLoreBook(subspecies))) {
						raceResponses.add(bookResponse(DOMINION_RACES, subspecies));
					}
				}
				if(index == 0) {
					return new Response("返回", "回到种族目录。", LIBRARY);
					
				} else if(index>0 && index-1<raceResponses.size()) {
					return raceResponses.get(index-1);
				}
				
				return null;
				
			} else {
				return LIBRARY.getResponse(responseTab, index);
			}
		}
	
	};
	
	public static final DialogueNode FIELDS_BOOKS = new DialogueNode("", "", false) {
		@Override
		public String getContent() {
			return "<p>"
						+ "你漫步在图书馆走廊，发现这儿空气比平时清新多了。"
						+ "你心想，这大概是一种奥术附魔。于是你开始观察四周的书架，想找到蛛丝马迹。"
					+ "</p>"
					+ "<p>"
						+ "乍一看，它们似乎是用草皮块制作的，但仔细一看，就会发现它们其实是经过巧妙雕刻的木块。"
						+ "有个架子上的草皮像是被雪覆盖了，还能感到它散发出丝丝寒气。"
						+ "果不其然，这儿的书籍都是和弗洛伊田野以及生活在那里的种族有关。这一区域的田园气质也是因此配置的吧。"
					+ "</p>";
		}

		@Override
		public String getResponseTabTitle(int index) {
			return LIBRARY.getResponseTabTitle(index);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==3) {
				List<Response> raceResponses = new ArrayList<>();
				Set<AbstractItemType> booksAdded = new HashSet<>();
				for(AbstractSubspecies subspecies : getAisleSubspecies(LibraryAisle.FIELDS)) {
					if(booksAdded.add(ItemType.getLoreBook(subspecies))) {
						raceResponses.add(bookResponse(FIELDS_BOOKS, subspecies));
					}
				}
				if(index == 0) {
					return new Response("返回", "回到种族目录。", LIBRARY);
					
				} else if(index>0 && index-1<raceResponses.size()) {
					return raceResponses.get(index-1);
				}
				
				return null;
				
			} else {
				return LIBRARY.getResponse(responseTab, index);
			}
		}
	};

	public static final DialogueNode JUNGLE_BOOKS = new DialogueNode("", "", false) {
		@Override
		public String getContent() {
			return "<p>"
						+ "你发现自己[pc.walking]过的通道被施加了奥术附魔，弥漫着闷热潮湿的气氛。"
						+ "两边的架子是由巨大的、有生命的深色木块拼接而成的，表面还不时长出一些小巧的热带植物。"
					+ "</p>"
					+ "<p>"
						+ "再次感谢奇妙的法术，这样的环境也并没有损坏陈列的许多书籍。你匆匆扫了几眼，发现它们都是丛林主题的。"
						+ "走到过道中途，你看见一些记载了北方丛林种族的书，要不要稍微停下来读一读呢……"
					+ "</p>";
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LIBRARY.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==3) {
				List<Response> raceResponses = new ArrayList<>();
				Set<AbstractItemType> booksAdded = new HashSet<>();
				for(AbstractSubspecies subspecies : getAisleSubspecies(LibraryAisle.JUNGLE)) {
					if(booksAdded.add(ItemType.getLoreBook(subspecies))) {
						raceResponses.add(bookResponse(JUNGLE_BOOKS, subspecies));
					}
				}
				if(index == 0) {
					return new Response("返回", "回到种族目录。", LIBRARY);
					
				} else if(index>0 && index-1<raceResponses.size()) {
					return raceResponses.get(index-1);
				}
				
				return null;
				
			} else {
				return LIBRARY.getResponse(responseTab, index);
			}
		}
	};

	public static final DialogueNode MOUNTAIN_BOOKS = new DialogueNode("", "", false) {
		@Override
		public String getContent() {
			return "<p>"
						+ "你[pc.Walking]在莉莱雅图书馆的众多过道中，忽然感觉氧气变得稀薄，空气也开始发干发冷。"
						+ "你相信这是某种奥术附魔的影响，于是环顾了一下两边的书架，发现它们都是从粗野的岩石里凿出的。"
						+ "书架上摆放的书籍似乎都与山区有关。这些奥术附魔一定是在努力渲染高海拔的气氛。"
					+ "</p>"
					+ "<p>"
						+ "手边的书籍描写了许多居住在皎月山脉的种族，要不要稍微停下来读一读呢……"
					+ "</p>";
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LIBRARY.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==3) {
				List<Response> raceResponses = new ArrayList<>();
				Set<AbstractItemType> booksAdded = new HashSet<>();
				for(AbstractSubspecies subspecies : getAisleSubspecies(LibraryAisle.MOUNTAIN)) {
					if(booksAdded.add(ItemType.getLoreBook(subspecies))) {
						raceResponses.add(bookResponse(MOUNTAIN_BOOKS, subspecies));
					}
				}
				if(index == 0) {
					return new Response("返回", "回到种族目录。", LIBRARY);
					
				} else if(index>0 && index-1<raceResponses.size()) {
					return raceResponses.get(index-1);
				}
				
				return null;
				
			} else {
				return LIBRARY.getResponse(responseTab, index);
			}
		}
	};
	
	public static final DialogueNode SEA_BOOKS = new DialogueNode("", "", false) {
		@Override
		public String getContent() {
			return "<p>"
						+ "你[pc.walk]在莉莱雅图书馆众多书籍中的一条过道上，隐隐约约听到拍打海岸的浪潮声。"
						+ "呼吸着沁人心脾的咸咸海味，你知道这是奥术发挥的效果。"
						+ "书架上摆放着看似活珊瑚和漂流木的物品，你可以根据这些线索推断出这个过道是专门用来摆放有关海洋主题的书籍的。"
					+ "</p>"
					+ "<p>"
						+ "你停下脚步，仔细观察了架上的书脊，发现有几本和无尽之海的众多种族有关，你在考虑要不要花点时间稍微读读……"
					+ "</p>";
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LIBRARY.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==3) {
				List<Response> raceResponses = new ArrayList<>();
				Set<AbstractItemType> booksAdded = new HashSet<>();
				for(AbstractSubspecies subspecies : getAisleSubspecies(LibraryAisle.SEA)) {
					if(booksAdded.add(ItemType.getLoreBook(subspecies))) {
						raceResponses.add(bookResponse(SEA_BOOKS, subspecies));
					}
				}
				if(index == 0) {
					return new Response("返回", "回到种族目录。", LIBRARY);
					
				} else if(index>0 && index-1<raceResponses.size()) {
					return raceResponses.get(index-1);
				}
				
				return null;
				
			} else {
				return LIBRARY.getResponse(responseTab, index);
			}
		}
	};
	
	public static final DialogueNode DESERT_BOOKS = new DialogueNode("", "", false) {
		@Override
		public String getContent() {
			return "<p>"
						+ "[pc.Walking]在图书馆的过道上，你不禁觉得，比起往常，这儿的空气未免也太干太热了。"
						+ "这好像是为了配合书架主题而施放的奥术法术。因为架上这些书和莉莉丝王国的南部沙漠有关。"
						+ "书架本身也与这一主题相吻合，由厚厚的大理石块砌成，表面刻有奇特的象形文字。"
					+ "</p>"
					+ "<p>"
						+ "在过道中途停下，你随意挑了几本书来翻看，发现它们与沙漠中发现的种族有关。"
						+ "你不确定它们对你有没有用，决定稍稍驻足，把它们通读一遍……"
					+ "</p>";
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LIBRARY.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==3) {
				List<Response> raceResponses = new ArrayList<>();
				Set<AbstractItemType> booksAdded = new HashSet<>();
				for(AbstractSubspecies subspecies : getAisleSubspecies(LibraryAisle.DESERT)) {
					if(booksAdded.add(ItemType.getLoreBook(subspecies))) {
						raceResponses.add(bookResponse(DESERT_BOOKS, subspecies));
					}
				}
				if(index == 0) {
					return new Response("返回", "回到种族目录。", LIBRARY);
					
				} else if(index>0 && index-1<raceResponses.size()) {
					return raceResponses.get(index-1);
				}
				
				return null;
				
			} else {
				return LIBRARY.getResponse(responseTab, index);
			}
		}
	};
	
	private static Response bookResponse(DialogueNode nodeToReturnTo, AbstractSubspecies subspecies) {
		AbstractItemType book = ItemType.getLoreBook(subspecies);

		if(Main.getProperties().isAdvancedRaceKnowledgeDiscovered(subspecies)) {
			return new Response(book.getName(false), book.getDescription(), nodeToReturnTo) {
				@Override
				public void effects() {
					Main.game.getTextEndStringBuilder().append(book.getEffects().get(0).applyEffect(Main.game.getPlayer(), Main.game.getPlayer(), 1));
				}
			};
			
		} else {
			return new Response(book.getName(false), "你还没发现这本书呢！", null);
		}
	}
}
