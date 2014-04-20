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

    public Army(int infantry,
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

    public Army() {
        this(0, 0, 0, 0, 0, 0, 0, 0, 0, false);
    }

    public Army(Army a) {
        this.infantry = a.infantry;
        this.artillery = a.artillery;
        this.tanks = a.tanks;
        this.fighters = a.fighters;
        this.bombers = a.bombers;
        this.battleships = a.battleships;
        this.destroyers = a.destroyers;
        this.aircraft_carriers = a.aircraft_carriers;
        this.transports = a.transports;
        this.antiaircraft_gun = a.antiaircraft_gun;
    }

    public int land_battle_units() {
        return infantry + artillery + tanks + fighters + bombers;
    }

    public int naval_battle_units() {
        return fighters + bombers + battleships + destroyers + aircraft_carriers + transports;
    }

    public Integer get(String name) {
        switch(name) {
            case Infantry.name:
                return infantry;
            case Artillery.name:
                return artillery;
            case Tank.name:
                return tanks;
            case Fighter.name:
                return fighters;
            case Bomber.name:
                return bombers;
            case Battleship.name:
                return battleships;
            case Destroyer.name:
                return destroyers;
            case Aircraftcarrier.name:
                return aircraft_carriers;
            case Transport.name:
                return transports;
            case AntiaircraftGun.name:
                return antiaircraft_gun ? 1 : 0;
        }
        return null;
    }

    public void set(String name, int count) {
        switch(name) {
            case Infantry.name:
                infantry = count;
                break;
            case Artillery.name:
                artillery = count;
                break;
            case Tank.name:
                tanks = count;
                break;
            case Fighter.name:
                fighters = count;
                break;
            case Bomber.name:
                bombers = count;
                break;
            case Battleship.name:
                battleships = count;
                break;
            case Destroyer.name:
                destroyers = count;
                break;
            case Aircraftcarrier.name:
                aircraft_carriers = count;
                break;
            case Transport.name:
                transports = count;
                break;
            case AntiaircraftGun.name:
                antiaircraft_gun = (count != 0);
                break;
        }
    }
}
