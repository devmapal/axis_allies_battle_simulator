package foss.devmapal.axis_allies_calc.axis_allies_calc;

import java.io.Serializable;

/**
 * Created by devmapal on 4/4/14.
 */
public class Army implements Serializable {
    public int[] units;

    public Army(int infantry,
                     int artillery,
                     int tanks,
                     int fighters,
                     int bombers,
                     int battleships,
                     int destroyers,
                     int aircraft_carriers,
                     int transports,
                     int submarines,
                     int antiaircraft_gun) {
        units = new int[11];
        units[Infantry.id] = infantry;
        units[Artillery.id] = artillery;
        units[Tank.id] = tanks;
        units[Fighter.id] = fighters;
        units[Bomber.id] = bombers;
        units[Battleship.id] = battleships;
        units[Destroyer.id] = destroyers;
        units[Aircraftcarrier.id] = aircraft_carriers;
        units[Transport.id] = transports;
        units[Submarine.id] = submarines;
        units[AntiaircraftGun.id] = antiaircraft_gun;
    }

    public Army() {
        this(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    }

    public Army(Army a) {
        units = new int[11];
        units[Infantry.id] = a.units[Infantry.id];
        units[Artillery.id] = a.units[Artillery.id];
        units[Tank.id] = a.units[Tank.id];
        units[Fighter.id] = a.units[Fighter.id];
        units[Bomber.id] = a.units[Bomber.id];
        units[Battleship.id] = a.units[Battleship.id];
        units[Destroyer.id] = a.units[Destroyer.id];
        units[Aircraftcarrier.id] = a.units[Aircraftcarrier.id];
        units[Transport.id] = a.units[Transport.id];
        units[Submarine.id] = a.units[Submarine.id];
        units[AntiaircraftGun.id] = a.units[AntiaircraftGun.id];
    }

    public void add(Army a) {
        units[Infantry.id] += a.get_infantry();
        units[Artillery.id] += a.get_artillery();
        units[Tank.id] += a.get_tanks();
        units[Fighter.id] += a.get_fighters();
        units[Bomber.id] += a.get_bombers();
        units[Battleship.id] += a.get_battleships();
        units[Destroyer.id] += a.get_destroyers();
        units[Aircraftcarrier.id] += a.get_aircraftcarriers();
        units[Transport.id] += a.get_transports();
        units[Submarine.id] += a.get_submarines();
        units[AntiaircraftGun.id] += a.get_antiaircraftguns();
    }

    public int land_battle_units() {
        return units[Infantry.id] + units[Artillery.id] + units[Tank.id] + units[Fighter.id] + units[Bomber.id];
    }

    public int naval_battle_units() {
        return units[Fighter.id] + units[Bomber.id] + units[Battleship.id] + units[Destroyer.id] + units[Aircraftcarrier.id] + units[Transport.id] + units[Submarine.id];
    }

    public int get(int id) {
        return units[id];
    }

    public void set(int id, int count) {
        units[id] = count;
    }
    
    public void set_infantry(int infantry) {
        units[Infantry.id] = infantry;
    }
    public int get_infantry() {
        return units[Infantry.id];
    }

    public void set_artillery(int artillery) {
        units[Artillery.id] = artillery;
    }
    public int get_artillery() {
        return units[Artillery.id];
    }

    public void set_tanks(int tanks) {
        units[Tank.id] = tanks;
    }
    public int get_tanks() {
        return units[Tank.id];
    }

    public void set_fighters(int fighters) {
        units[Fighter.id] = fighters;
    }
    public int get_fighters() {
        return units[Fighter.id];
    }

    public void set_bombers(int bombers) {
        units[Bomber.id] = bombers;
    }
    public int get_bombers() {
        return units[Bomber.id];
    }

    public void set_battleships(int battleships) {
        units[Battleship.id] = battleships;
    }
    public int get_battleships() {
        return units[Battleship.id];
    }

    public void set_destroyers(int destroyers) {
        units[Destroyer.id] = destroyers;
    }
    public int get_destroyers() {
        return units[Destroyer.id];
    }

    public void set_aircraftcarriers(int aircraftcarriers) {
        units[Aircraftcarrier.id] = aircraftcarriers;
    }
    public int get_aircraftcarriers() {
        return units[Aircraftcarrier.id];
    }

    public void set_transports(int transports) {
        units[Transport.id] = transports;
    }
    public int get_transports() {
        return units[Transport.id];
    }

    public void set_submarines(int submarines) {
        units[Submarine.id] = submarines;
    }
    public int get_submarines() {
        return units[Submarine.id];
    }

    public void set_antiaircraftguns(int antiaircraftguns) {
        units[AntiaircraftGun.id] = antiaircraftguns;
    }
    public int get_antiaircraftguns() {
        return units[AntiaircraftGun.id];
    }
}
