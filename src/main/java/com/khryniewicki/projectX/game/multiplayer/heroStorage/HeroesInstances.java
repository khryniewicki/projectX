package com.khryniewicki.projectX.game.multiplayer.heroStorage;

import com.khryniewicki.projectX.config.messageHandler.Message;
import com.khryniewicki.projectX.game.heroes.character.SuperHero;
import com.khryniewicki.projectX.game.heroes.factory.CharacterFactory;
import com.khryniewicki.projectX.game.heroes.factory.WizardFactory;
import com.khryniewicki.projectX.game.multiplayer.MultiplayerInitializer;
import com.khryniewicki.projectX.game.multiplayer.WebsocketInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class HeroesInstances {
    private SuperHero hero;
    private SuperHero mock;
    private CharacterFactory characterFactory;

    private HeroesInstances() {
        characterFactory=new WizardFactory();
    }
    public static HeroesInstances getInstance() {
        return HELPER.INSTANCE;
    }


    public SuperHero getHero() {
        return hero;
    }
    public SuperHero getMock() {
        return mock;
    }

    public void setHero() {
        this.hero = getWizardType();
    }

    public SuperHero getWizardType() {
        return characterFactory.create(MultiplayerInitializer.inputText);
    }

    public void setMock() {
        WebsocketInitializer websocketInstance = WebsocketInitializer.getWebsocketInstance();

        MapWithHeroes instance = MapWithHeroes.getINSTANCE();

        String sessionId = websocketInstance.getSessionId();
        Map<String, Message> mapWithHeroes = instance.getMapWithHeroes();

        for (Map.Entry<String, Message> hero : mapWithHeroes.entrySet()) {
            if (!hero.getKey().equals(sessionId)) {
                String heroType = hero.getValue().getContent();
                this.mock = characterFactory.create(heroType);
            }
        }
    }


    private static class HELPER {

        public static final HeroesInstances INSTANCE = new HeroesInstances();

    }
}