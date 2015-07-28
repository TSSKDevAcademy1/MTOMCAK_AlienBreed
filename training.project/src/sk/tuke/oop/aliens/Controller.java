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
public class Controller extends AbstractActor {

    private Reactor reactor = new Reactor();
    private Animation controller;

    public Controller(Reactor reactor) {
        controller = new Animation("resources/images/switch.png", 16, 16, 10);
        setAnimation(controller);  
        this.reactor = reactor;
    } 
}