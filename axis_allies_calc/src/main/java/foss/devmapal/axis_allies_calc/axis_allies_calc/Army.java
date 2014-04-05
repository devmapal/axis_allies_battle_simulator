package foss.devmapal.axis_allies_calc.axis_allies_calc;

import java.io.Serializable;

/**
 * Created by devmapal on 4/4/14.
 */
public class Army implements Serializable {
    public int infantry,
               artillery,
               tanks,
               fighters,
               bombers,
               battleships,
               destroyers,
               aircraft_carriers,
               transports;
    public boolean antiaircraft_gun;

    public void Army(int infantry,
                     int artillery,
                     int tanks,
                     int fighters,
                     int bombers,
                     int battleships,
                     int destroyers,
                     int aircraft_carriers,
                     int transports,
                     boolean antiaircraft_gun) {
        this.infantry = infantry;
        this.artillery = artillery;
        this.tanks = tanks;
        this.fighters = fighters;
        this.bombers = bombers;
        this.battleships = battleships;
        this.destroyers = destroyers;
        this.aircraft_carriers = aircraft_carriers;
        this.transports = transports;
        this.antiaircraft_gun = antiaircraft_gun;
    }

    public void  Army() {
        this.Army(0, 0, 0, 0, 0, 0, 0, 0, 0, false);
    }
}
