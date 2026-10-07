package com.lilithsthrone.game.sex;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.dialogue.utils.UtilText;

/**
 * @since 0.1.65
 * @version 0.4
 * @author Innoxia
 */
public enum PregnancyDescriptor {
	
	ALREADY_PREGNANT {
		@Override
		public String getDescriptor(GameCharacter characterBeingImpregnated, GameCharacter characterProvidingCum, boolean directSexInsemination) {
			boolean isSlime = characterBeingImpregnated.getBodyMaterial()==BodyMaterial.SLIME;
			boolean selfcest = characterBeingImpregnated.equals(characterProvidingCum);
			boolean cumInPussy = isCumInPussy(characterBeingImpregnated, characterProvidingCum);
			
			StringBuilder sb = new StringBuilder();
			
			sb.append("<p class='centre noPad'>");
			if(characterBeingImpregnated.isPlayer() && cumInPussy){
				if(characterProvidingCum==null) {
					sb.append("你感受到精液");
					if(isSlime) {
						sb.append("在你粘液制成的身体中缓缓散开来，为了让你的核心受孕而进发着，");
					} else {
						sb.append("进入了[pc.pussy+]深处");
					}
					sb.append("，但由于[style.boldSex(你已经怀孕了，所以不必担心)]！");
					
				} else {
					sb.append("你感受到");
					if(selfcest) {
						sb.append("自己[npc.cum+]");
					} else {
						sb.append("[npc2.namePos][npc2.cum+]");
					}
					if(isSlime) {
						sb.append("在你粘液制成的身体中缓缓散开来，为了让你的核心受孕而进发着，");
					} else {
						sb.append("进入了[pc.pussy+]的深处");
					}
					sb.append("，但由于[style.boldSex(你已经怀孕了，所以不必担心)]！");
				}
				
			} else {
				if(characterProvidingCum==null) {
					sb.append("[npc.NameIsFull]已经怀孕了，[style.boldSex(所以没有可能");
					if(selfcest) {
						sb.append("再让自己怀上了");
					} else {
						sb.append("再让[npc.she]受孕了");
					}
					sb.append(")]！");
					
				} else {
					sb.append("[npc.NameIsFull]已经怀孕了，[style.boldSex(所以没有可能");
					if(directSexInsemination) {
						if(selfcest) {
							sb.append("再让自己怀上了");
						} else {
							sb.append("[npc2.nameHas]再让[npc.herHim]怀上了");
						}
					} else {
						if(selfcest) {
							sb.append("再让自己受孕了");
						} else {
							sb.append("[npc2.nameHas]再让[npc.herHim]受孕了");
						}
					}
					sb.append(")]！");
				}
			}
			sb.append("</p>");
			
			if(characterProvidingCum!=null) {
				return UtilText.parse(characterBeingImpregnated, characterProvidingCum, sb.toString());
			} else {
				return UtilText.parse(characterBeingImpregnated, sb.toString());
			}
		}
	},

	ALREADY_PREGNANT_EGGS {
		@Override
		public String getDescriptor(GameCharacter characterBeingImpregnated, GameCharacter characterProvidingCum, boolean directSexInsemination) {
			boolean isSlime = characterBeingImpregnated.getBodyMaterial()==BodyMaterial.SLIME;
			boolean selfcest = characterBeingImpregnated.equals(characterProvidingCum);
			boolean cumInPussy = isCumInPussy(characterBeingImpregnated, characterProvidingCum);
			
			StringBuilder sb = new StringBuilder();
			
			sb.append("<p class='centre noPad'>");
			if(characterBeingImpregnated.isPlayer() && cumInPussy){
				if(characterProvidingCum==null) {
					sb.append("你感受到精液");
					if(isSlime) {
						sb.append("在你粘液制成的身体中缓缓散开来，为了让你的核心受孕而进发着，");
					} else {
						sb.append("进入了[pc.pussy+]深处");
					}
					sb.append("，但由于[style.boldSex(你的子宫内装满了受精卵，所以不必担心)]！");
					
				} else {
					sb.append("你感受到");
					if(selfcest) {
						sb.append("自己[npc.cum+]");
					} else {
						sb.append("[npc2.namePos][npc2.cum+]");
					}
					if(isSlime) {
						sb.append("在你粘液制成的身体中缓缓散开来，为了让你的核心受孕而进发着，");
					} else {
						sb.append("进入了[pc.pussy+]的深处");
					}
					sb.append("，但由于[style.boldSex(你的子宫内装满了受精卵，所以不必担心)]！");
				}
				
			} else {
				if(characterProvidingCum==null) {
					sb.append("[npc.NamePos]的子宫内装满了受精卵，[style.boldSex(所以没有可能");
					if(selfcest) {
						sb.append("再让自己怀上了");
					} else {
						sb.append("再让[npc.she]受孕了");
					}
					sb.append(")]！");
					
				} else {
					sb.append("[npc.NamePos]的子宫内装满了受精卵，[style.boldSex(所以没有可能");
					if(directSexInsemination) {
						if(selfcest) {
							sb.append("再让自己怀上了");
						} else {
							sb.append("[npc2.nameHas]再让[npc.herHim]怀上了");
						}
					} else {
						if(selfcest) {
							sb.append("再让自己受孕了");
						} else {
							sb.append("[npc2.nameHas]再让[npc.herHim]受孕了");
						}
					}
					sb.append(")]！");
				}
			}
			sb.append("</p>");
			
			if(characterProvidingCum!=null) {
				return UtilText.parse(characterBeingImpregnated, characterProvidingCum, sb.toString());
			} else {
				return UtilText.parse(characterBeingImpregnated, sb.toString());
			}
		}
	},
	
	NO_CHANCE {
		@Override
		public String getDescriptor(GameCharacter characterBeingImpregnated, GameCharacter characterProvidingCum, boolean directSexInsemination) {
			boolean isSlime = characterBeingImpregnated.getBodyMaterial()==BodyMaterial.SLIME;
			boolean selfcest = characterBeingImpregnated.equals(characterProvidingCum);
			boolean cumInPussy = isCumInPussy(characterBeingImpregnated, characterProvidingCum);
			
			StringBuilder sb = new StringBuilder();
			
			sb.append("<p class='centre noPad'>");
			if(!cumInPussy) {
				sb.append("尽管[npc.namePos]的子宫内还没有其他物体，[style.boldSex(但却没有可能");
				if(characterProvidingCum==null) {
					if(selfcest) {
						sb.append("再让自己怀上了");
					} else {
						sb.append("再让[npc.she]受孕了");
					}
					sb.append(")]！");
					
				} else {
					if(directSexInsemination) {
						if(selfcest) {
							sb.append("再让自己怀上了");
						} else {
							sb.append("[npc2.nameHas]再让[npc.herHim]怀上了");
						}
					} else {
						if(selfcest) {
							sb.append("再让自己受孕了");
						} else {
							sb.append("[npc2.nameHas]再让[npc.herHim]受孕了");
						}
					}
					sb.append(")]！");
				}
				
			} else if(characterBeingImpregnated.isPlayer()){
				if(characterProvidingCum==null) {
					sb.append("虽然感受到了精液");
					if(isSlime) {
						sb.append("在你粘液制成的身体中缓缓散开来，为了让你的核心受孕而进发着，");
					} else {
						sb.append("进入了子宫深处");
					}
					sb.append("，但你却感觉[style.boldSex(自己并不会因此怀孕)]。");
					
				} else {
					sb.append("虽然感受到了");
					if(selfcest) {
						sb.append("自己[npc.cum+]");
					} else {
						sb.append("[npc2.namePos][npc2.cum+]");
					}
					if(isSlime) {
						sb.append("在你粘液制成的身体中缓缓散开来，为了让你的核心受孕而进发着，");
					} else {
						sb.append("进入了子宫深处");
					}
					sb.append("，但你却感觉[style.boldSex(自己并不会因此怀孕)]。");
				}
				
			} else {
				if(characterProvidingCum==null) {
					sb.append("尽管[npc.her]");
					if(isSlime) {
						sb.append("粘液构成的躯体");
					} else {
						sb.append("的子宫");
					}
					if(selfcest) {
						sb.append("装满了自己的子种汁，[style.boldSex(但却并没有可能让自己受孕)]。");
					} else {
						sb.append("装满了精液，[style.boldSex(但却并没有可能受孕)]。");
					}
					
				} else {
					sb.append("尽管[npc.her]");
					if(isSlime) {
						sb.append("粘液构成的躯体");
					} else {
						sb.append("的子宫");
					}
					if(selfcest) {
						sb.append("装满了自己的子种汁，[style.boldSex(但却并没有可能让自己受孕)]。");
					} else {
						sb.append("装满了[npc2.namePos][npc2.cum+]，[style.boldSex(但却并没有可能受孕)]。");
					}
				}
			}
			sb.append("</p>");
			
			if(characterProvidingCum!=null) {
				return UtilText.parse(characterBeingImpregnated, characterProvidingCum, sb.toString());
			} else {
				return UtilText.parse(characterBeingImpregnated, sb.toString());
			}
		}
	},
	
	LOW_CHANCE {
		@Override
		public String getDescriptor(GameCharacter characterBeingImpregnated, GameCharacter characterProvidingCum, boolean directSexInsemination) {
			boolean isSlime = characterBeingImpregnated.getBodyMaterial()==BodyMaterial.SLIME;
			boolean selfcest = characterBeingImpregnated.equals(characterProvidingCum);
			boolean cumInPussy = isCumInPussy(characterBeingImpregnated, characterProvidingCum);
			
			StringBuilder sb = new StringBuilder();
			
			sb.append("<p class='centre noPad'>");
			if(!cumInPussy) {
				sb.append("伴随着一声喘息，");
				if(characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)) {
					sb.append("[npc.name]意识到[style.boldSex(若是自己还没有怀孕，那或许这次就有一点可能受孕了)]！");
				} else {
					sb.append("[npc.name]意识到[style.boldSex(自己有一点可能要怀孕了)]！");
				}
				
			} else if(characterBeingImpregnated.isPlayer()){
				sb.append("你感受到");
				if(selfcest) {
					sb.append("自己[npc.cum+]");
				} else {
					if(characterProvidingCum==null) {
						sb.append("精液");
					} else {
						sb.append("[npc2.namePos][npc2.cum+]");
					}
				}
				if(isSlime) {
					sb.append("在你粘液制成的身体中缓缓散开来，为了让你的核心受孕而进发着，");
				} else {
					sb.append("进入了子宫深处");
				}
				if(characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)) {
					sb.append("你意识到[style.boldSex(若是自己还没有怀孕，那或许这次就有一点可能受孕了)]！");
				} else {
					sb.append("你意识到[style.boldSex(自己有一点可能要怀孕了)]！");
				}
				
			} else {
				sb.append("在[npc.her]");
				if(isSlime) {
					sb.append("粘液构成的躯体");
				} else {
					sb.append("的子宫");
				}
				if(selfcest) {
					sb.append("充满了自己的子种汁后，");
				} else {
					if(characterProvidingCum==null) {
						sb.append("充满了精液后，");
					} else {
						sb.append("充满了[npc2.namePos][npc2.cum+]后，");
					}
				}
				if(characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)) {
					sb.append("[npc.name]意识到[style.boldSex(若是自己还没有怀孕，那或许这次就有一点可能受孕了)]！");
				} else {
					sb.append("[npc.name]意识到[style.boldSex(自己有可能要怀孕了)]！");
				}
			}
			sb.append("</p>");
			
			if(characterProvidingCum!=null) {
				return UtilText.parse(characterBeingImpregnated, characterProvidingCum, sb.toString());
			} else {
				return UtilText.parse(characterBeingImpregnated, sb.toString());
			}
		}
	},
	
	AVERAGE_CHANCE {
		@Override
		public String getDescriptor(GameCharacter characterBeingImpregnated, GameCharacter characterProvidingCum, boolean directSexInsemination) {
			boolean isSlime = characterBeingImpregnated.getBodyMaterial()==BodyMaterial.SLIME;
			boolean selfcest = characterBeingImpregnated.equals(characterProvidingCum);
			boolean cumInPussy = isCumInPussy(characterBeingImpregnated, characterProvidingCum);

			StringBuilder sb = new StringBuilder();
			
			sb.append("<p class='centre noPad'>");
			if(!cumInPussy) {
				sb.append("伴随着一声喘息，");
				if(characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)) {
					sb.append("[npc.name]意识到[style.boldSex(若是自己还没有怀孕，那或许这次就有可能受孕了)]！");
				} else {
					sb.append("[npc.name]意识到[style.boldSex(自己有可能要怀孕了)]！");
				}
				
			} else if(characterBeingImpregnated.isPlayer()){
				sb.append("你感受到");
				if(selfcest) {
					sb.append("自己[npc.cum+]");
				} else {
					if(characterProvidingCum==null) {
						sb.append("精液");
					} else {
						sb.append("[npc2.namePos][npc2.cum+]");
					}
				}
				if(isSlime) {
					sb.append("在你粘液制成的身体中缓缓散开来，为了让你的核心受孕而进发着，");
				} else {
					sb.append("进入了子宫深处，");
				}
				if(characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)) {
					sb.append("你意识到[style.boldSex(若是自己还没有怀孕，那或许这次就有可能受孕了)]！");
				} else {
					sb.append("你意识到[style.boldSex(自己有可能要怀孕了)]！");
				}
				
			} else {
				sb.append("在[npc.her]");
				if(isSlime) {
					sb.append("粘液构成的躯体");
				} else {
					sb.append("的子宫");
				}
				if(selfcest) {
					sb.append("充满了自己的子种汁后，");
				} else {
					if(characterProvidingCum==null) {
						sb.append("充满了精液后，");
					} else {
						sb.append("充满了[npc2.namePos][npc2.cum+]后，");
					}
				}
				if(characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)) {
					sb.append("[npc.name]意识到[style.boldSex(若是自己还没有怀孕，那或许这次就有可能受孕了)]！");
				} else {
					sb.append("[npc.name]意识到[style.boldSex(自己有可能要怀孕了)]！");
				}
			}
			sb.append("</p>");
			
			if(characterProvidingCum!=null) {
				return UtilText.parse(characterBeingImpregnated, characterProvidingCum, sb.toString());
			} else {
				return UtilText.parse(characterBeingImpregnated, sb.toString());
			}
		}
	},
	
	HIGH_CHANCE {
		@Override
		public String getDescriptor(GameCharacter characterBeingImpregnated, GameCharacter characterProvidingCum, boolean directSexInsemination) {
			boolean isSlime = characterBeingImpregnated.getBodyMaterial()==BodyMaterial.SLIME;
			boolean selfcest = characterBeingImpregnated.equals(characterProvidingCum);
			boolean cumInPussy = isCumInPussy(characterBeingImpregnated, characterProvidingCum);

			StringBuilder sb = new StringBuilder();
			
			sb.append("<p class='centre noPad'>");
			if(!cumInPussy) {
				sb.append("伴随着一声喘息，");
				if(characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)) {
					sb.append("[npc.name]意识到[style.boldSex(若是自己还没有怀孕，那或许这次就很有可能受孕了)]！");
				} else {
					sb.append("[npc.name]意识到[style.boldSex(自己很有可能要怀孕了)]！");
				}
				
			} else if(characterBeingImpregnated.isPlayer()){
				sb.append("你感受到");
				if(selfcest) {
					sb.append("自己[npc.cum+]");
				} else {
					if(characterProvidingCum==null) {
						sb.append("精液");
					} else {
						sb.append("[npc2.namePos][npc2.cum+]");
					}
				}
				if(isSlime) {
					sb.append("在你粘液制成的身体中缓缓散开来，为了让你的核心受孕而进发着，");
				} else {
					sb.append("进入了子宫深处，");
				}
				if(characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)) {
					sb.append("你意识到[style.boldSex(若是自己还没有怀孕，那或许这次就很有可能受孕了)]！");
				} else {
					sb.append("你意识到[style.boldSex(自己很有可能要怀孕了)]！");
				}
				
			} else {
				sb.append("在[npc.her]");
				if(isSlime) {
					sb.append("粘液构成的躯体");
				} else {
					sb.append("的子宫");
				}
				if(selfcest) {
					sb.append("充满了自己的子种汁后，");
				} else {
					if(characterProvidingCum==null) {
						sb.append("充满了精液后，");
					} else {
						sb.append("充满了[npc2.namePos][npc2.cum+]后，");
					}
				}
				if(characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)) {
					sb.append("[npc.Name]意识到[style.boldSex(若是自己还没有怀孕，那或许这次就很有可能受孕了)]！");
				} else {
					sb.append("[npc.name]意识到[style.boldSex([npc.she]很有可能要怀孕了)]！");
				}
			}
			sb.append("</p>");
			
			if(characterProvidingCum!=null) {
				return UtilText.parse(characterBeingImpregnated, characterProvidingCum, sb.toString());
			} else {
				return UtilText.parse(characterBeingImpregnated, sb.toString());
			}
		}
	},
	
	CERTAINTY {
		@Override
		public String getDescriptor(GameCharacter characterBeingImpregnated, GameCharacter characterProvidingCum, boolean directSexInsemination) {
			boolean isSlime = characterBeingImpregnated.getBodyMaterial()==BodyMaterial.SLIME;
			boolean selfcest = characterBeingImpregnated.equals(characterProvidingCum);
			boolean cumInPussy = isCumInPussy(characterBeingImpregnated, characterProvidingCum);

			StringBuilder sb = new StringBuilder();
			
			sb.append("<p class='centre noPad'>");
			if(!cumInPussy) {
				sb.append("伴随着一声喘息，");
				if(characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)) {
					sb.append("[npc.name]意识到[style.boldSex(若是自己还没有怀孕，那这次肯定就受孕了)]！");
				} else {
					sb.append("[npc.name]意识到[style.boldSex(自己肯定要怀孕了)]！");
				}
				
			} else if(characterBeingImpregnated.isPlayer()){
				sb.append("你感受到");
				if(selfcest) {
					sb.append("自己[npc.cum+]");
				} else {
					if(characterProvidingCum==null) {
						sb.append("精液");
					} else {
						sb.append("[npc2.namePos][npc2.cum+]");
					}
				}
				if(isSlime) {
					sb.append("在你粘液制成的身体中缓缓散开来，为了让你的核心受孕而进发着，");
				} else {
					sb.append("进入了子宫深处，");
				}
				if(characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)) {
					sb.append("你意识到[style.boldSex(若是自己还没有怀孕，那这次肯定就受孕了)]！");
				} else {
					sb.append("你意识到[style.boldSex(自己肯定要怀孕了)]！");
				}
				
			} else {
				sb.append("在[npc.her]");
				if(isSlime) {
					sb.append("粘液构成的躯体");
				} else {
					sb.append("的子宫");
				}
				if(selfcest) {
					sb.append("充满了自己的子种汁后，");
				} else {
					if(characterProvidingCum==null) {
						sb.append("充满了精液后，");
					} else {
						sb.append("充满了[npc2.namePos][npc2.cum+]后，");
					}
				}
				if(characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)) {
					sb.append("[npc.name]意识到[style.boldSex(若是自己还没有怀孕，那这次肯定就受孕了)]！");
				} else {
					sb.append("[npc.name]意识到[style.boldSex(自己肯定要怀孕了)]！");
				}
			}
			sb.append("</p>");
			
			if(characterProvidingCum!=null) {
				return UtilText.parse(characterBeingImpregnated, characterProvidingCum, sb.toString());
			} else {
				return UtilText.parse(characterBeingImpregnated, sb.toString());
			}
		}
	};
	
	private static boolean isCumInPussy(GameCharacter characterBeingImpregnated, GameCharacter characterProvidingCum) {
		return characterBeingImpregnated.getFluidsStoredInOrifice(SexAreaOrifice.VAGINA).stream().anyMatch(f -> {
			try {
				return f.isCum() && f.getFluidCharacter() == characterProvidingCum;
			} catch (Exception e) {
				System.err.println("Fluid Stored Exception");
				e.printStackTrace();
				return false;
			}
		});
	}
	
	public abstract String getDescriptor(GameCharacter characterBeingImpregnated, GameCharacter characterProvidingCum, boolean directSexInsemination);
	
	public static PregnancyDescriptor getPregnancyDescriptorBasedOnProbability(float probability) {
		if (probability <= 0) {
			return PregnancyDescriptor.NO_CHANCE;
		} else if (probability <= 0.15f) {
			return PregnancyDescriptor.LOW_CHANCE;
		} else if (probability <= 0.3f) {
			return PregnancyDescriptor.AVERAGE_CHANCE;
		} else if (probability < 1) {
			return PregnancyDescriptor.HIGH_CHANCE;
		} else {
			return PregnancyDescriptor.CERTAINTY;
		}
	}
}
