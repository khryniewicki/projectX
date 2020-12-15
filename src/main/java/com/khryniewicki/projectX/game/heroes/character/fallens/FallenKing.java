package com.khryniewicki.projectX.game.heroes.character.fallens;

import com.khryniewicki.projectX.game.attack.spells.spell_instances.BasicSpellInstance;
import com.khryniewicki.projectX.game.attack.spells.spell_instances.UltimateSpellInstance;
import com.khryniewicki.projectX.game.attack.spells.spellbook.blackKing.BlackFire;
import com.khryniewicki.projectX.game.attack.spells.spellbook.blackKing.Skull;
import com.khryniewicki.projectX.game.heroes.character.properties.SuperHero;
import com.khryniewicki.projectX.graphics.textures.HeroTextures;


public class FallenKing extends SuperHero {

    public FallenKing() {
        setPosition();
        setSpellBasis();
        setMesh();
        setTexture();
        setProperties();
    }

    @Override
    public void setSpellBasis() {
        setBasicSpellInstance(new BasicSpellInstance(new BlackFire()));
        setUltimateSpellInstance(new UltimateSpellInstance(new Skull()));
    }

    @Override
    public void setTexture() {
        setTexture(HeroTextures.FALLENIDLE3);
        setHeroIdle(HeroTextures.FALLENIDLE3);
        setHeroRight(HeroTextures.FALLENRUN3);
        setHeroLeft(HeroTextures.FALLENRUN3);
        setHeroUp(HeroTextures.FALLENRUN3);
        setHeroDown(HeroTextures.FALLENRUN3);
        setHeroAttack(HeroTextures.FALLENATTACK3);
    }


    @Override
    public void setProperties() {
        setName("FallenKing");
        setHero_left_offset(0.4f);
        setHero_top_offset(0.5f);
        setHero_right_offset(0.2f);
        setHero_bottom_offset(0.2f);
        setMana(100);
        setLife(100);
        setSIZE(1.1F);
    }


}
