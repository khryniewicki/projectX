package com.khryniewicki.projectX.services.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class HeroDto implements Serializable, BaseDto {
    private String heroType;
    private Integer life;
    private Float mana;
    private Float positionX;
    private Float positionY;
    private BaseDtoType type;
    private String sessionId;

    public HeroDto(Builder builder) {
        this.heroType = builder.heroType;
        this.life = builder.life;
        this.mana = builder.mana;
        this.positionX = builder.positionX;
        this.positionY = builder.positionY;
        this.type = BaseDtoType.HERO;
    }

    public static class Builder {
        private String heroType;
        private Integer life;
        private Float mana;
        private Float positionX;
        private Float positionY;

        public Builder heroType(String heroType) {
            this.heroType = heroType;
            return this;
        }

        public Builder life(Integer life) {
            this.life = life;
            return this;
        }

        public Builder mana(Float mana) {
            this.mana = mana;
            return this;
        }

        public Builder positionX(Float positionX) {
            this.positionX = positionX;
            return this;
        }

        public Builder positionY(Float positionY) {
            this.positionY = positionY;
            return this;
        }

        public HeroDto build() {
            return new HeroDto(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HeroDto heroDto = (HeroDto) o;
        return Objects.equals(heroType, heroDto.heroType) &&
                Objects.equals(life, heroDto.life) &&
                Objects.equals(mana, heroDto.mana) &&
                Objects.equals(positionX, heroDto.positionX) &&
                Objects.equals(positionY, heroDto.positionY) &&
                type == heroDto.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(heroType, life, mana, positionX, positionY, type);
    }

    @Override
    public String toString() {
        return "HeroDto{" +
                "heroType='" + heroType + '\'' +
                ", life=" + life +
                ", mana=" + mana +
                ", positionX=" + positionX +
                ", positionY=" + positionY +
                ", type='" + type + '\'' +
                ", sessionId='" + sessionId + '\'' +
                '}';
    }
}
