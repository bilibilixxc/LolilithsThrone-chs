package com.lilithsthrone.LolificationProject.ModCommon;

import com.lilithsthrone.main.Main;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class OptionInputListener {
    String inputID;
    Runnable inputEvent;
    Runnable endInput;

    public OptionInputListener(String inputID, Runnable inputEvent, Runnable endInput) {
        this.inputID = inputID;
        this.inputEvent = inputEvent;
        this.endInput = endInput;
    }

    public byte handleInput(KeyEvent event) {
        if ((boolean) Main.mainController.getWebEngine().executeScript("document.getElementById('" + inputID + "') === document.activeElement")) {
            inputEvent.run();
            if (event.getCode() == KeyCode.ENTER) {
                endInput.run();
                return 2;
            }
            return 1;
        }
        return 0;
    }
}
