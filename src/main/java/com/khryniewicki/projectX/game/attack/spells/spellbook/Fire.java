package com.khryniewicki.projectX.game.attack.spells.spellbook;

import com.khryniewicki.projectX.game.attack.spells.spell_settings.Spell;
import com.khryniewicki.projectX.game.attack.spells.spell_settings.SpellTexture;
import com.khryniewicki.projectX.utils.SpellUtil;


public class Fire extends Spell {

    public Fire() {
        setProperties();
        setTexture();
    }

    @Override
    public void setProperties() {
        setName("FireBall");
        setBasic(true);
        setCastingSpeed(0.3f);
        setSpellDuration(3000L);
        setPowerAttack(10);
        setManaConsumed(10);
    }

    @Override
    public void setTexture() {
        setMissleSpell(new SpellTexture(SpellUtil.FIRE, 1.0f));
        setExecutedSpell(new SpellTexture(SpellUtil.FIREBALL, 1.0f));
        setIcon(SpellUtil.FIREICON);
        setFadedIcon(SpellUtil.FIREICONFADED);

    }

}

