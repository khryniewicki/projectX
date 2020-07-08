package com.khryniewicki.projectX.game.heroes.wizards;

import com.khryniewicki.projectX.game.attack.spells.spell_instances.BasicSpell;
import com.khryniewicki.projectX.game.attack.spells.spell_instances.UltimateSpell;
import com.khryniewicki.projectX.game.attack.spells.spellbook.Fire;
import com.khryniewicki.projectX.game.attack.spells.spellbook.Ice;
import com.khryniewicki.projectX.game.heroes.character.SuperHero;
import com.khryniewicki.projectX.utils.HeroUtil;


public class IceWizard extends SuperHero {

    public IceWizard() {
        setPosition();
        setTexture();
        setSpellBasis();
        setMesh();
        setProperties();
    }

    @Override
    public void setTexture() {
        setTexture(HeroUtil.ICE_WIZARD_IDLE);
        setHeroIdle(HeroUtil.ICE_WIZARD_IDLE);
        setHeroRight(HeroUtil.ICE_WIZARD_RUN);
        setHeroLeft(HeroUtil.ICE_WIZARD_RUN);
        setHeroUp(HeroUtil.ICE_WIZARD_RUN);
        setHeroDown(HeroUtil.ICE_WIZARD_RUN);
    }


    @Override
    public void setProperties() {
        setName("IceWizard");
        setHero_standard_offset(0.2f);
        setHero_top_offset(0.5f);
        setMana(100);
        setLife(100);

    }

    @Override
    public void setSpellBasis() {
        setBasicSpell(new BasicSpell(new Fire()));
        setUltimateSpell(new UltimateSpell(new Ice()));
    }


}
