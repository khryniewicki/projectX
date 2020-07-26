package com.khryniewicki.projectX.game.heroes.wizards;

import com.khryniewicki.projectX.game.attack.spells.spell_instances.BasicSpellInstance;
import com.khryniewicki.projectX.game.attack.spells.spell_instances.UltimateSpellInstance;
import com.khryniewicki.projectX.game.attack.spells.spellbook.Fire;
import com.khryniewicki.projectX.game.attack.spells.spellbook.Ice;
import com.khryniewicki.projectX.game.heroes.character.SuperHero;
import com.khryniewicki.projectX.utils.HeroUtil;


public class FireWizard extends SuperHero {

    public FireWizard() {
        setPosition();
        setSpellBasis();
        setMesh();
        setTexture();
        setProperties();
    }

    @Override
    public void setSpellBasis() {
        setBasicSpellInstance(new BasicSpellInstance(new Fire()));
        setUltimateSpellInstance(new UltimateSpellInstance(new Ice()));
    }

    @Override
    public void setTexture() {
        setTexture(HeroUtil.FIRE_WIZARD_IDLE);
        setHeroIdle(HeroUtil.FIRE_WIZARD_IDLE);
        setHeroRight(HeroUtil.FIRE_WIZARD_RUN);
        setHeroLeft(HeroUtil.FIRE_WIZARD_RUN);
        setHeroUp(HeroUtil.FIRE_WIZARD_RUN);
        setHeroDown(HeroUtil.FIRE_WIZARD_RUN);
    }


    @Override
    public void setProperties() {
        setName("FireWizard");
        setHero_left_offset(0.2f);
        setHero_top_offset(0.5f);
        setMana(100);
        setLife(100);

    }


}
