package com.lilithsthrone.game.settings;

import javafx.scene.input.KeyCode;

/**
 * @since 0.1.61
 * @version 0.3.1
 * @author Innoxia
 */
public enum KeyboardAction {

	MENU("菜单", new KeyCodeWithModifiers(KeyCode.ESCAPE), null),

	FULL_SCREEN("全屏开关", new KeyCodeWithModifiers(KeyCode.F11), null),
	
	MOVE_NORTH("向北移动", new KeyCodeWithModifiers(KeyCode.W), new KeyCodeWithModifiers(KeyCode.UP)),
	MOVE_WEST("向西移动", new KeyCodeWithModifiers(KeyCode.A), new KeyCodeWithModifiers(KeyCode.LEFT)),
	MOVE_SOUTH("向南移动", new KeyCodeWithModifiers(KeyCode.S), new KeyCodeWithModifiers(KeyCode.DOWN)),
	MOVE_EAST("向东移动", new KeyCodeWithModifiers(KeyCode.D), new KeyCodeWithModifiers(KeyCode.RIGHT)),
	
	MOVE_RESPONSE_CURSOR_NORTH("反应指针向上", new KeyCodeWithModifiers(KeyCode.W, false, true), new KeyCodeWithModifiers(KeyCode.UP, false, true)),
	MOVE_RESPONSE_CURSOR_WEST("反应指针向左", new KeyCodeWithModifiers(KeyCode.A, false, true), new KeyCodeWithModifiers(KeyCode.LEFT, false, true)),
	MOVE_RESPONSE_CURSOR_SOUTH("反应指针向下", new KeyCodeWithModifiers(KeyCode.S, false, true), new KeyCodeWithModifiers(KeyCode.DOWN, false, true)),
	MOVE_RESPONSE_CURSOR_EAST("反应指针向右", new KeyCodeWithModifiers(KeyCode.D, false, true), new KeyCodeWithModifiers(KeyCode.RIGHT, false, true)),

	QUICKSAVE("快速存档", new KeyCodeWithModifiers(KeyCode.F5), null),
	QUICKLOAD("快速读档", new KeyCodeWithModifiers(KeyCode.F9), null),

	MENU_SELECT("选择", new KeyCodeWithModifiers(KeyCode.SPACE), new KeyCodeWithModifiers(KeyCode.ENTER)),
	INVENTORY("物品栏", new KeyCodeWithModifiers(KeyCode.I), new KeyCodeWithModifiers(KeyCode.TAB)),
	JOURNAL("手机", new KeyCodeWithModifiers(KeyCode.P), new KeyCodeWithModifiers(KeyCode.J)),
	MAP("地图", new KeyCodeWithModifiers(KeyCode.M), null),
	CHARACTERS("角色", new KeyCodeWithModifiers(KeyCode.C), null),
	ZOOM("放大", new KeyCodeWithModifiers(KeyCode.Z), null),

	SCROLL_UP("上滑", new KeyCodeWithModifiers(KeyCode.PAGE_UP), null),
	SCROLL_DOWN("下滑", new KeyCodeWithModifiers(KeyCode.PAGE_DOWN), null),

	RESPOND_1("反应1", new KeyCodeWithModifiers(KeyCode.DIGIT1), null),
	RESPOND_2("反应2", new KeyCodeWithModifiers(KeyCode.DIGIT2), null),
	RESPOND_3("反应3", new KeyCodeWithModifiers(KeyCode.DIGIT3), null),
	RESPOND_4("反应4", new KeyCodeWithModifiers(KeyCode.DIGIT4), null),
	RESPOND_5("反应5", new KeyCodeWithModifiers(KeyCode.DIGIT5), null),
	RESPOND_6("反应6", new KeyCodeWithModifiers(KeyCode.DIGIT1, false, true), null),
	RESPOND_7("反应7", new KeyCodeWithModifiers(KeyCode.DIGIT2, false, true), null),
	RESPOND_8("反应8", new KeyCodeWithModifiers(KeyCode.DIGIT3, false, true), null),
	RESPOND_9("反应9", new KeyCodeWithModifiers(KeyCode.DIGIT4, false, true), null),
	RESPOND_10("反应10", new KeyCodeWithModifiers(KeyCode.DIGIT5, false, true), null),
	RESPOND_11("反应11", new KeyCodeWithModifiers(KeyCode.DIGIT1, true, false), null),
	RESPOND_12("反应12", new KeyCodeWithModifiers(KeyCode.DIGIT2, true, false), null),
	RESPOND_13("反应13", new KeyCodeWithModifiers(KeyCode.DIGIT3, true, false), null),
	RESPOND_14("反应14", new KeyCodeWithModifiers(KeyCode.DIGIT4, true, false), null),
	RESPOND_0("反应15", new KeyCodeWithModifiers(KeyCode.DIGIT5, true, false), new KeyCodeWithModifiers(KeyCode.BACK_SPACE)),

	RESPOND_NEXT_TAB("反应栏下一个", new KeyCodeWithModifiers(KeyCode.E, false, true), null),
	RESPOND_PREVIOUS_TAB("反应栏上一个", new KeyCodeWithModifiers(KeyCode.Q, false, true), null),
	
	RESPOND_NEXT_PAGE("反应栏下一页", new KeyCodeWithModifiers(KeyCode.E), null),
	RESPOND_PREVIOUS_PAGE("反应栏下一页", new KeyCodeWithModifiers(KeyCode.Q), null);

	private String name;
	private KeyCodeWithModifiers primaryDefault, secondaryDefault;

	private KeyboardAction(String name, KeyCodeWithModifiers primaryDefault, KeyCodeWithModifiers secondaryDefault) {
		this.name = name;
		this.primaryDefault = primaryDefault;
		this.secondaryDefault = secondaryDefault;
	}

	public String getName() {
		return name;
	}

	public KeyCodeWithModifiers getPrimaryDefault() {
		return primaryDefault;
	}

	public KeyCodeWithModifiers getSecondaryDefault() {
		return secondaryDefault;
	}
}