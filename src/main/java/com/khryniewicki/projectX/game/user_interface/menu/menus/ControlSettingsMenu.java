package com.khryniewicki.projectX.game.user_interface.menu.menus;

import com.khryniewicki.projectX.game.user_interface.menu.buttons.ButtonTransferObject;
import com.khryniewicki.projectX.game.user_interface.symbols.MenuSymbol;
import com.khryniewicki.projectX.utils.Buttons;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyChangeEvent;
import java.util.Collections;

@Slf4j
@Getter
@Setter
public class ControlSettingsMenu extends MenuImp {

    private static final ControlSettingsMenu instance = new ControlSettingsMenu();

    public static ControlSettingsMenu getInstance() {
        return instance;
    }

    private ControlSettingsMenu() {
        super();
        start();
    }

    @Override
    public void init() {
        MenuSymbol returnButton = Buttons.RETURN_BUTTON2;
        returnButton.setClassName(this.getClass().getName());
        super.setButtons(Collections.singletonList(returnButton));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        ButtonTransferObject bto = (ButtonTransferObject) evt.getNewValue();
        String btnName = bto.getName();
        setButtonTransferObject(bto);
        log.info(btnName);
        if (btnName.equals("Return2")) {
            MainMenu mainMenu = MainMenu.getInstance();
            mainMenu.render();
        }
    }

}
