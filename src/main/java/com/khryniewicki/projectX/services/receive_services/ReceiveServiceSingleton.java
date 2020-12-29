package com.khryniewicki.projectX.services.receive_services;

import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.Position;
import com.khryniewicki.projectX.services.dto.HeroDto;
import com.khryniewicki.projectX.services.dto.SpellDto;

public class ReceiveServiceSingleton {
    private final HeroReceiveService heroReceiveService;
    private final SpellReceiveService spellReceiveService;

    private ReceiveServiceSingleton() {
        this.heroReceiveService = new HeroReceiveService();
        this.spellReceiveService = new SpellReceiveService();
    }

    //hero receive service
    public Position get_hero_mock_position() {
        return heroReceiveService.getMockPosition();
    }

    public Integer get_hero_mock_life() {
        return heroReceiveService.getMockLife();
    }

    public Float get_hero_mock_mana() {
        return heroReceiveService.getMockMana();
    }

    public void set_hero_mock(HeroDto heroDTO) {
        heroReceiveService.set_hero_mock(heroDTO);
    }

    //spell receive service
    public Position get_spell_mock_target(boolean isBasic) {
        return isBasic ? get_basic_spell_mock_target() : get_ultimate_spell_mock_target();
    }

    public Position get_basic_spell_mock_target() {
        return spellReceiveService.getBasicSpellTarget();
    }

    public Position get_ultimate_spell_mock_target() {
        return spellReceiveService.getUltimateSpellTarget();
    }

    public void set_spell_mock(SpellDto spell_dto) {
        spellReceiveService.set_spell_mock(spell_dto);
    }

    public void reset() {
        heroReceiveService.reset();
        spellReceiveService.reset();
    }


    public static ReceiveServiceSingleton getInstance() {
        return HELPER.INSTANCE;
    }

    private static class HELPER {
        private final static ReceiveServiceSingleton INSTANCE = new ReceiveServiceSingleton();
    }
}
