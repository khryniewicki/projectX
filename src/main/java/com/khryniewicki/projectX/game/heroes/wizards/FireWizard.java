package com.khryniewicki.projectX.game.heroes.wizards;

import com.khryniewicki.projectX.game.attack.spells.spell_instances.Fire;
import com.khryniewicki.projectX.game.heroes.character.SuperHero;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.HeroStartingPosition;
import com.khryniewicki.projectX.math.Vector;
import com.khryniewicki.projectX.utils.HeroUtil;
import lombok.Data;

@Data
public class FireWizard extends SuperHero {

    public FireWizard() {
        setPosition();
        setSpell();
        setMesh();
        setTexture();
        setProperties();
    }

    @Override
    public void setSpell() {
        setSpell(new Fire());
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
    public void setPosition() {
        setPosition(new Vector());
    }

    @Override
    public void setProperties() {
        setName("FireWizard");
        setHero_standard_offset(0.2f);
        setHero_top_offset(0.5f);
        setMana(100);
        setLife(100);
    }

    @Override
    public void setMesh() {
        setMesh(createHero());
    }

    @Override
    public void setHeroIdl() {
        setTexture(HeroUtil.FIRE_WIZARD_IDLE);
    }

    @Override
    public void setHeroRun() {
        setTexture(HeroUtil.FIRE_WIZARD_RUN);
    }
}
