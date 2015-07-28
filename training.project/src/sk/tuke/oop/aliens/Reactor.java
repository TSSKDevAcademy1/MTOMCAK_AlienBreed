/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.tuke.oop.aliens;

import sk.tuke.oop.aliens.actor.AbstractActor;
import sk.tuke.oop.framework.Animation;

/**
 *
 * @author galilei-05
 */
public class Reactor extends AbstractActor {

    private int temperature;
    private Animation reactorAnimationOn, reactorAnimationOff;
    private Animation reactorAnimationBroken, reactorReady;
    private int damage;
    private boolean state;

    public Reactor() {
        // initialize values
        temperature = 0;
        damage = 0;
        state = false;

        // Reactor ON
        reactorAnimationOn = new Animation("resources/images/reactor_on.png", 80, 80, 100);
        // play animation repeatedly
        reactorAnimationOn.setPingPong(true);

        // Reactor OFF
        reactorAnimationOff = new Animation("resources/images/reactor_hot.png", 80, 80, 50);
        reactorAnimationOff.setPingPong(true);

        // Reactor Broken
        reactorAnimationBroken = new Animation("resources/images/reactor_broken.png", 80, 80, 100);
        reactorAnimationBroken.setPingPong(true);

        // Reactor OFF and ready
        reactorReady = new Animation("resources/images/reactor.png", 80, 80, 100);
        setAnimation(reactorReady);
    }

    /**
     * @return the temperature
     */
    public float getTemperature() {
        return temperature;
    }

    /**
     * @param temperature the temperature to set
     */
    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    /**
     * @return the damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * @param damage the damage to set
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    private void updateAnimation() {
        // check state and select specify reactor image using current temperature
        if (isRunning()) {

            if (temperature < 2000) {
                setAnimation(reactorAnimationOn);
            }
            if (temperature > 4000) {
                setAnimation(reactorAnimationOff);
            }
            if (temperature > 5000) {
                damage = 100;
                setAnimation(reactorAnimationBroken);
            }
        } else {
            setAnimation(reactorReady);
        }
    }

    public void increaseTemperature(int addTemperature) {
        if (state) {
            if (temperature >= 2000 && temperature <= 5000 && addTemperature > 0) {

                if (damage >= 50) {
                    temperature += 2 * addTemperature;
                } else {
                    temperature += addTemperature;
                }

                damage = (int) (temperature / 30.0 - 200.0 / 3.0);
                updateAnimation();

            } else {
                temperature += addTemperature;
                updateAnimation();
            }
        }
    }

    public void decreaseTemperature(int subTemperature) {
        if (temperature < 5000 && temperature > 0 && subTemperature > 0) {

            if (damage >= 50) {
                temperature -= (int) subTemperature / 2;
            } else {
                temperature -= subTemperature;
            }
            updateAnimation();
        }
    }

    public void repair(Hammer hammer) {
        if (hammer != null) {
            // reactor temperature repair
            if (temperature > 1000) {
                temperature = 1000;
            }
            // reactor damage repair
            if (damage >= 50) {
                damage -= 50;
            } else {
                damage = 0;
            }

            // refresh state of reactor
            updateAnimation();
        }
    }

    // Turn ON reactor
    public void turnOn(Light light) {
        state = true;
        getAnimation().start();
        updateAnimation();
        light.toggle();
    }

    // Turno OFF reactor
    public void TurnOff(Light light) {
        removeLight(light);
        getAnimation().stop();
        state = false;
    }

    // check state of reactor
    public boolean isRunning() {
        return state;
    }
    
    // add light for reactor
    public void addLight(Light light) {

        if (isRunning() && damage < 50) {
            light.setElectricityFlow(true);
        }
    }
    // remove light from reactor
    public void removeLight(Light light) {
            if (isRunning()){
            light.setElectricityFlow(false);
        }
    }

}