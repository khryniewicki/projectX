package com.khryniewicki.projectX.game.attack;

import com.khryniewicki.projectX.game.Map.Level;
import com.khryniewicki.projectX.graphics.Texture;
import com.khryniewicki.projectX.services.SpellReceiveService;

public class SpellMock extends Spell implements UltraSpell {
    private UltraSpell ultraSpell;
    private Float tmpPositionX;
    private Float tmpPositionY;
    private Float distanceX;
    private Float distanceY;
    private Texture throwingSpellTexture;

    public SpellMock(UltraSpell ultraSpell) {
        this.ultraSpell = ultraSpell;
        setPosition();
    }


    private Boolean changeMockPosition() {

        if (tmpPositionX != null && tmpPositionX == SpellReceiveService.spellMockPositionX) {
            if (tmpPositionY != null && tmpPositionY == SpellReceiveService.spellMockPositionY) {
                return false;
            }
        }
        if (SpellReceiveService.spellMockPositionX != null) {
            tmpPositionX = SpellReceiveService.spellMockPositionX;
            tmpPositionY = SpellReceiveService.spellMockPositionY;
            setRelativeX(SpellReceiveService.spellMockPositionX);
            setRelativeY(SpellReceiveService.spellMockPositionY);
            System.out.println("MockX: "+getHeroPositionX()+"MockY"+getHeroPositionY());
            setDistanceX(SpellReceiveService.spellMockPositionX - getHeroPositionX());
            setDistanceY(SpellReceiveService.spellMockPositionY - getHeroPositionY());
            setSpell(-Math.signum(getDistanceY()), -Math.signum(getDistanceX()), getThrowingSpellTexture());
            setPositionX(getHeroPositionX());
            setPositionY(getHeroPositionY());
            setPositionZ(1f);
            System.out.println(ultraSpell.getPosition().toString());



        }
        return true;
    }

    @Override
    public void update() {
        getMousePosition();
        spellCasting();
    }

    @Override
    public void getMousePosition() {
        changeMockPosition();
    }

    @Override
    public void spellCasting() {
        ultraSpell.spellCasting();
    }
    @Override
    public void setDistanceX(Float distanceX) {
        ultraSpell.setDistanceX(distanceX);
    }
    @Override
    public void setDistanceY(Float distanceY) {
        ultraSpell.setDistanceY(distanceY);
    }
    //
//    @Override
//    public void setProperties() {
//        ultraSpell.setProperties();
//    }
//
//    @Override
//    public void setThrowingSpellTexture() {
//        ultraSpell.setThrowingSpellTexture();
//    }
//
//    @Override
//    public void setConsumedSpellTexture() {
//        ultraSpell.setConsumedSpellTexture();
//    }
//
//    @Override
//    public void setTexture() {
//        ultraSpell.setTexture();
//    }
//
//    @Override
//    public void setMesh() {
//        ultraSpell.setMesh();
//    }
//
//    @Override
//    public Vector getPosition() {
//        return ultraSpell.getPosition();
//    }
//
//
    @Override
    public void render() {
        ultraSpell.render();
    }

//    @Override
//    public void castingSpell() {
//        ultraSpell.castingSpell();
//    }

    @Override
    public void setPositionX(Float positionX) {
        ultraSpell.setPositionX(positionX);
    }

    @Override
    public void setPositionY(Float positionY) {
        ultraSpell.setPositionY(positionY);

    }

    @Override
    public void setPositionZ(Float positionZ) {
        ultraSpell.setPositionZ(positionZ);
    }

    @Override
    public Float getHeroPositionX() {
        return Level.heroMock.getPosition().x;
    }

    @Override
    public Float getHeroPositionY() {

        return Level.heroMock.getPosition().y;
    }

    @Override
    public void setRelativeX(Float relativeX) {
        ultraSpell.setRelativeX(relativeX);
    }

    @Override
    public void setRelativeY(Float relativeY) {
        ultraSpell.setRelativeY(relativeY);

    }

    @Override
    public void setProperties() {

    }

    //
    @Override
    public void setPosition() {

    }
    @Override
    public void setSpell(Float indexHeight, Float indexWidth, Texture texture){
        ultraSpell.setSpell(indexHeight,indexWidth,texture);
    }

    public Float getDistanceX() {
        return ultraSpell.getDistanceX();
    }

    public Float getDistanceY() {
        return ultraSpell.getDistanceY();
    }

    public Texture getThrowingSpellTexture() {
        return ultraSpell.getThrowingSpellTexture();
    }

}