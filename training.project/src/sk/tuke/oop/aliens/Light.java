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
public class Light extends AbstractActor {

    private Animation lightAnimationOn, lightAnimationOff;
    private boolean lightState, electricity;

   // private Reactor reactor = new Reactor();
    public Light() {
        lightState = false;
        electricity = false;

        lightAnimationOn = new Animation("resources/images/light_on.png", 16, 16, 10);
        // animation
        lightAnimationOff = new Animation("resources/images/light_off.png", 16, 16, 10);
        setAnimation(lightAnimationOff);
    }

    public void toggle() {
        if (electricity) {
            if (lightState) {
                lightState = false;
                updateLightAnimation();
            } else {
                lightState = true;
               updateLightAnimation();
            }
        }
    }
    
    private void updateLightAnimation(){
        if (lightState && electricity) {
            setAnimation(lightAnimationOn);
        }
        else {
            setAnimation(lightAnimationOff);
        }
    }

    public void setElectricityFlow(boolean electricity) {
        this.electricity = electricity;
        updateLightAnimation();
        
    }
}